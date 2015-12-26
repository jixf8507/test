package com.qx.eakay.controller.monitor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qx.common.tools.MyDateUtil;
import com.qx.common.tools.StringTools;
import com.qx.eakay.controller.BaseController;
import com.qx.eakay.dao.order.OrderDao;
import com.qx.eakay.login.SessionUserBO;
import com.qx.eakay.model.merchant.SitePO;
import com.qx.eakay.model.monitor.CarContorlCmd;
import com.qx.eakay.model.monitor.CarModel;
import com.qx.eakay.model.monitor.SiteResoucePO;
import com.qx.eakay.model.order.OrderPO;
import com.qx.eakay.service.monitor.MainMonitorService;
import com.qx.eakay.service.order.OrderService;
import com.qx.eakay.service.order.OtherOrderService;
import com.qx.eakay.socket.DeviceSocket;
import com.qx.eakay.util.ChargeStaticUtil;
import com.qx.eakay.util.MSG;

@Controller
@RequestMapping("/monitor/main")
public class MainMonitorController extends BaseController {

	@Autowired
	private MainMonitorService mainMonitorService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OtherOrderService otherOrderService;
	@Autowired
	private OrderDao orderDao;

	/**
	 * 进入监控中心页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("page.htm")
	public ModelAndView page(HttpSession session) throws Exception {
		String city = this.getSessionUser(session).getCity();
		return new ModelAndView("monitor/main", "city", city);
	}

	/**
	 * 进入监控中心列表页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("carDeviceList.htm")
	public ModelAndView carDeviceList() throws Exception {
		return new ModelAndView("monitor/carDeviceMonitorList");
	}

	/**
	 * 进入车载设备监控内容页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("carDevice.htm")
	public ModelAndView carDevice(Integer id) throws Exception {
		SitePO sitePo = new SitePO();
		sitePo.setId(id);
		return new ModelAndView("monitor/carDeviceMonitor", "sitePo", sitePo);
		// return new ModelAndView("monitor/carDeviceMonitorList");
	}

	/**
	 * 进入充电监控页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("stake.htm")
	public ModelAndView stake(HttpSession session) throws Exception {
		String city = this.getSessionUser(session).getCity();
		return new ModelAndView("monitor/stakeMonitor", "city", city);
	}

	/**
	 * 跳转到运营监控界面
	 */
	@RequestMapping("operationMonitoring.htm")
	public ModelAndView operationMonitoring() {

		return new ModelAndView("monitor/operationMonitoring");
	}

	/**
	 * 动态加载租赁点资源数据
	 * 
	 * @param paraData
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "ajaxSitesResouce.htm")
	@ResponseBody
	public Map<String, SiteResoucePO> ajaxSitesResouce(String paraData,
			HttpSession session) {
		paraData = StringTools.decodeMethod(paraData);
		// 动态查询条件参数
		JSONObject jsonObject = JSONObject.fromObject(paraData);
		SessionUserBO sessionUser = this.getSessionUser(session);
		jsonObject.put("merchantId", sessionUser.getMerchantId());
		if (!sessionUser.isManagerUser()) {
			jsonObject.put("siteId", sessionUser.getMerchantUserPO()
					.getSiteId());
		}
		return mainMonitorService.sitesResouces(jsonObject);
	}

	/**
	 * 车辆调度,公务用车发送还车命令(修改收费状态)
	 * 
	 * @param cmd
	 * @param orderType
	 * @return
	 */
	@RequestMapping(value = "ajaxSendOtherCarCmd.htm")
	@ResponseBody
	public MSG ajaxSendOtherCarCmd(CarContorlCmd cmd, Integer orderType,
			HttpSession session) {
		// 发送还车命令
		MSG msg = ajaxSendCarCmd(cmd, orderType);
		// 更改订单为收费状态
		/*
		 * OrderPO orderPO = new OrderPO();
		 * orderPO.setId(Integer.parseInt(cmd.getBookID())); SessionUserBO
		 * sessionUser = this.getSessionUser(session);
		 * orderPO.setMenForCharge(sessionUser.getName());
		 * msg.setSuccess(orderDao.updateForNoCharge(orderPO));
		 */
		return msg;
	}

	@RequestMapping(value = "ajaxSendCarCmd.htm")
	@ResponseBody
	public MSG ajaxSendCarCmd(CarContorlCmd cmd, Integer orderType) {
		MSG msg = new MSG();
		cmd.setCheckOut("123456");
		Integer orderId = Integer.parseInt(cmd.getBookID());

		OrderPO order = orderService.getOrderById(orderId);
		if ("已取车".equals(order.getOrderStatus())) {
			return new MSG("当前租车订单已取消还车！", 1);
		}
		if ("已还车".equals(order.getOrderStatus())
				&& "已关门".equals(order.getReturnCarConfirm())) {
			return new MSG("当前租车订单已还车审查！", 1);
		}
		if (!"已还车".equals(order.getOrderStatus())) {
			return new MSG("当前租车订单" + order.getOrderStatus() + "！", 1);
		}

		OrderPO orderPO = null;
		// 车辆调度，公务用车
		if (orderType == 3 || orderType == 4) {
			orderPO = otherOrderService.getOrder(orderId);
			// 用户租车，企业租车
		} else {
			orderPO = orderService.getOrder(orderId);
		}

		Integer customerID = orderPO.getCustomerId();
		Integer relityReturnSiteId = orderPO.getRelityReturnSiteId();
		Integer car_ID = orderPO.getCarId();
		String carid = orderPO.getCarPO().getCarNumber();
		String merchantID = orderPO.getMerchantId() + "";

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("data", cmd);
		map.put("customerID", customerID);
		map.put("orderID", orderId);
		map.put("car_ID", car_ID);
		map.put("carid", carid);
		map.put("merchantID", merchantID);
		map.put("relityReturnSiteId", relityReturnSiteId);

		msg = DeviceSocket.sendCarContorlCmd(map);
		return msg;
	}

	@RequestMapping(value = "ajaxSendCarCmdOpen.htm")
	@ResponseBody
	public MSG ajaxSendCarCmdOpen(CarContorlCmd cmd, Integer orderType) {
		MSG msg = new MSG();
		cmd.setCheckOut("123456");
		Integer orderId = Integer.parseInt(cmd.getBookID());

		OrderPO orderPO = null;
		// 车辆调度，公务用车
		if (orderType == 3 || orderType == 4) {
			orderPO = otherOrderService.getOrder(orderId);
			// 用户租车，企业租车
		} else {
			orderPO = orderService.getOrder(orderId);
		}

		Integer customerID = orderPO.getCustomerId();

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("data", cmd);
		map.put("customerID", customerID);

		msg = DeviceSocket.sendCarContorlCmd(map);
		return msg;
	}

	@RequestMapping(value = "ajaxCarDeviceStatus.htm")
	@ResponseBody
	public Map<String, CarModel> ajaxCarDeviceStatus() {
		return DeviceSocket.getDeviceNowMap();
	}

	@RequestMapping(value = "ajaxStakeDeviceStatus.htm")
	@ResponseBody
	public Map<String, Object> ajaxStakeDeviceStatus(HttpSession session) {
		Integer merchantId = this.getSessionUser(session).getMerchantId();
		return ChargeStaticUtil.getMerchantDeviceStatus(merchantId);
	}

	@RequestMapping(value = "ajaxSystem.htm")
	@ResponseBody
	public Map<String, Object> ajaxSystem(HttpSession session) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("merchantId", this.getSessionUser(session)
				.getMerchantId());
		jsonObject.put("type", "1");

		Map<String, Object> resultMap = new HashMap<>();

		resultMap.put("kms", mainMonitorService.statisticsOrderKms(jsonObject));

		jsonObject
				.put("realityReturnTime", MyDateUtil.getDayString(new Date()));
		resultMap.put("kms_jt",
				mainMonitorService.statisticsOrderKms(jsonObject));
		return resultMap;
	}

	@RequestMapping(value = "ajaxDaySystem.htm")
	@ResponseBody
	public Map<String, Object> ajaxDaySystem(HttpSession session) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("merchantId", this.getSessionUser(session)
				.getMerchantId());
		Date date = MyDateUtil.getDaytoCurrDay(new Date(), -30);
		jsonObject.put("beginTime", MyDateUtil.getDayString(date));
		jsonObject.put("endTime", MyDateUtil.getCurDate() + " 23:59:59");

		Map<String, Object> resultMap = new HashMap<>();

		resultMap.put("data",
				mainMonitorService.statisticsDayOrderKms(jsonObject));

		return resultMap;
	}

	/**
	 * 监控资源
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("resource.htm")
	public ModelAndView resource() throws Exception {
		return new ModelAndView("monitor/mainResource");
	}

	/**
	 * 监控地图
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("map.htm")
	public ModelAndView map(HttpSession session) throws Exception {
		String city = this.getSessionUser(session).getCity();
		return new ModelAndView("monitor/mainMap", "city", city);
	}

	/**
	 * 租赁点取车次数统计
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("siteGet.htm")
	public ModelAndView siteGet() throws Exception {
		return new ModelAndView("monitor/siteGet");
	}

	/**
	 * 租赁点还车次数统计
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("siteReturn.htm")
	public ModelAndView siteReturn() throws Exception {
		return new ModelAndView("monitor/siteReturn");
	}

	/**
	 * 租赁点收费统计
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("siteCharge.htm")
	public ModelAndView siteCharge() throws Exception {
		return new ModelAndView("monitor/siteCharge");
	}

	/**
	 * 租赁点取还车时间段统计
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getReturnTime.htm")
	public ModelAndView getReturnTime() throws Exception {
		return new ModelAndView("monitor/getReturnTime");
	}

	/**
	 * 月收益统计
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("monthCharge.htm")
	public ModelAndView monthCharge() throws Exception {
		return new ModelAndView("monitor/monthCharge");
	}

	/**
	 * 日收益统计
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("dayCharge.htm")
	public ModelAndView dayCharge() throws Exception {
		return new ModelAndView("monitor/dayCharge");
	}

	/**
	 * 租赁次数分时段统计(柱状图)
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("timeColum.htm")
	public ModelAndView timeColum() throws Exception {
		return new ModelAndView("monitor/timeColum");
	}

	/**
	 * 租赁次数分时段统计(饼状图)
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("timePie.htm")
	public ModelAndView timePie() throws Exception {
		return new ModelAndView("monitor/timePie");
	}

	/**
	 * 今日取车还车时间段分布
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("today.htm")
	public ModelAndView today() throws Exception {
		return new ModelAndView("monitor/today");
	}
}

package com.qx.eakay.controller.order;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qx.eakay.controller.BaseController;
import com.qx.eakay.dao.car.CarDeviceDao;
import com.qx.eakay.login.SessionUserBO;
import com.qx.eakay.model.car.CarDevice;
import com.qx.eakay.model.car.CarPO;
import com.qx.eakay.model.car.CarPO.CarStatus;
import com.qx.eakay.model.monitor.CarContorlCmd;
import com.qx.eakay.model.order.OrderPO;
import com.qx.eakay.service.car.CarService;
import com.qx.eakay.service.order.OrderService;
import com.qx.eakay.service.order.OtherOrderService;
import com.qx.eakay.util.MSG;

/**
 * 车辆调度、公务用车
 * 
 * @author sdf
 * @date 2015年10月12日
 */
@Controller
@RequestMapping("/order/other/getApply")
public class GetOtherApplyController extends BaseController {

	@Autowired
	private OtherOrderService orderService;
	@Autowired
	private OrderService oorderService;
	@Autowired
	private CarService carService;
	@Autowired
	private CarDeviceDao carDeviceDao;

	/**
	 * 取车申请
	 * 
	 * @param cId
	 * @param orderId
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getCarEdite.htm")
	public ModelAndView getCarEdite(Integer cId, Integer orderType)
			throws Exception {
		OrderPO orderPO = new OrderPO();
		CarPO car = carService.getCar(cId);
		orderPO.setCarPO(car);
		orderPO.setCarId(car.getId());
		orderPO.setGsitePO(car.getSitePO());
		orderPO.setRelityGetSiteId(car.getCurSiteId());
		orderPO.setOrderType(orderType);

		CarDevice carDevice = carDeviceDao.carDeviceByCarId(cId);
		// 有设备
		if (carDevice != null) {
			orderPO.setSurplusKmsForGet(carDevice.getSurplusKms());
			orderPO.setKmsForGet(carDevice.getCurKms());
			// 无设备
		} else {
			orderPO.setSurplusKmsForGet(car.getSurplusKms());
			orderPO.setKmsForGet(car.getKms());
		}

		return new ModelAndView("otherOrder/getCarEdite", "orderPO", orderPO);

	}

	/**
	 * 判断取车条件
	 * 
	 * @param orderPO
	 * @return
	 */
	@RequestMapping(value = "saveGetCar.htm")
	@ResponseBody
	public MSG checkGetCar(OrderPO orderPO, HttpSession session) {
		SessionUserBO sessionUser = this.getSessionUser(session);
		orderPO.setMerchantId(sessionUser.getMerchantId());
		orderPO.setMenForGet(sessionUser.getName());

		orderPO.setCustomerTable("merchant_user");
		orderPO.setPriceTypeId(0);
		orderPO.setPriceTypeTableName("");
		orderPO.setPriceTypeName("");

		CarPO carPO = carService.getCar(orderPO.getCarId());

		if (!CarStatus.空闲.name().equals(carPO.getStatus())) {
			return new MSG("该车辆已被使用，请更换车辆！", 1);
		}
		// 保存取车信息
		return saveGetCar(orderPO, session);
	}

	/**
	 * 保存取车信息
	 * 
	 * @param orderPO
	 * @return
	 */
	public MSG saveGetCar(OrderPO orderPO, HttpSession session) {
		MSG msg = orderService.saveOrderForGet(orderPO);
		Integer orderId = msg.getCode();
		Integer orderType = orderPO.getOrderType();
		CarDevice carDevice = carDeviceDao.carDeviceByCarId(orderPO.getCarId());
		// 有设备，发送取车命令
		if (carDevice != null) {
			if ("故障".equals(carDevice.getStatus())) {
				// 取消订单
				// oorderService.cancel(orderId);//////////////////////////////////////////////////
				return new MSG("设备故障，取车失败！", 1);
			}
			CarContorlCmd cmd = new CarContorlCmd();
			return oorderService.ajaxSendCarCmd(cmd, orderId, orderType);
		}
		return msg;
	}

}

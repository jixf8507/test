package com.qx.eakay.controller.order;

import java.math.BigDecimal;

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
import com.qx.eakay.model.merchant.MerchantPO;
import com.qx.eakay.model.monitor.CarContorlCmd;
import com.qx.eakay.model.order.OrderPO;
import com.qx.eakay.model.order.OrderPO.OrderStatus;
import com.qx.eakay.service.car.CarService;
import com.qx.eakay.service.customer.CustomerService;
import com.qx.eakay.service.merchant.MerchantService;
import com.qx.eakay.service.monitor.MainMonitorService;
import com.qx.eakay.service.order.OrderService;
import com.qx.eakay.util.MSG;

/**
 * 普通客户、企业租车
 * 
 * @author jixf
 * @date 2015年7月14日
 */
@Controller
@RequestMapping("/order/getApply")
public class GetApplyController extends BaseController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private CarService carService;
	@Autowired
	private MainMonitorService mainMonitorService;
	@Autowired
	private CarDeviceDao carDeviceDao;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private MerchantService merchantService;

	/**
	 * 进入取车申请页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("page.htm")
	public ModelAndView page() throws Exception {
		return new ModelAndView("order/getApply");
	}

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
	public ModelAndView getCarEdite(Integer cId, Integer orderId,
			String tableName) throws Exception {
		OrderPO orderPO;
		if (orderId == null) {
			orderPO = new OrderPO();
			CarPO car = carService.getCar(cId);
			orderPO.setCarPO(car);
			orderPO.setCarId(car.getId());
			orderPO.setGsitePO(car.getSitePO());
			orderPO.setRelityGetSiteId(car.getCurSiteId());

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

		} else {
			orderPO = orderService.getOrder(orderId);
		}
		orderPO.setPriceTypeTableName(tableName);
		return new ModelAndView("order/getCarEdite", "orderPO", orderPO);

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

		Integer customerId = orderPO.getCustomerId();
		Integer merchantId = orderPO.getMerchantId();
		int count = 0;

		// 长租套餐，单位会员
		if ("month_lease_price_type".equals(orderPO.getPriceTypeTableName())) {
			orderPO.setCustomerTable("merchant_enterprise_customer");
			orderPO.setOrderType(2);
			// count = orderService.findUnitCuserUseOrders(customerId);

		}
		// 分时套餐，普通客户
		if ("wh_hours_price_type".equals(orderPO.getPriceTypeTableName())) {
			orderPO.setCustomerTable("customer");
			orderPO.setOrderType(1);
			count = orderService.findCuserUseOrders(customerId, merchantId);
		}

		CarPO carPO = carService.getCar(orderPO.getCarId());
		OrderPO order = orderService.getOrder(orderPO.getId());

		// 预约取车
		if (order != null) {
			if (count > 1) {
				return new MSG("该客户当前有未结束的租赁业务，请更换客户！", 1);
			}
			
			if (!"已预约".equals(order.getOrderStatus())) {
				return new MSG("当前租车订单"+order.getOrderStatus()+"！", 1);
			}
			
			return saveGetCar(orderPO, session);
		}

		if (!CarStatus.空闲.name().equals(carPO.getStatus())) {
			return new MSG("该车辆已被使用，请更换车辆！", 1);
		}

		if (count > 0) {
			return new MSG("该客户当前有未结束的租赁业务，请更换客户！", 1);
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
				orderService.cancel(orderId, orderPO.getOrderType());
				return new MSG("设备故障，取车失败！", 1);
			}
			CarContorlCmd cmd = new CarContorlCmd();
			// 修改订单为已预约
			orderService.updateStatus(OrderStatus.已预约, "等待审查", orderId);
			return orderService.ajaxSendCarCmd(cmd, orderId, orderType);
		}
		return msg;
	}

	/**
	 * 预约取车
	 * 
	 * @param orderPO
	 * @return
	 */
	@RequestMapping(value = "getBookCar.htm")
	@ResponseBody
	public MSG getBookCar(Integer orderId, Integer carId) {
		MSG msg = new MSG();

		OrderPO order = orderService.getOrder(orderId);

		if (!"已预约".equals(order.getOrderStatus())) {
			return new MSG("当前租车订单"+order.getOrderStatus()+"！", 1);
		}
		
		Integer orderType = order.getOrderType();
		CarDevice carDevice = carDeviceDao.carDeviceByCarId(carId);
		// 有设备，发送取车命令
		if (carDevice != null) {
			 if ("故障".equals(carDevice.getStatus())) {
			 // 取消订单
//			 orderService.cancel(orderId, orderType);
			 return new MSG("设备故障，取车失败！", 1);
			 }
			CarContorlCmd cmd = new CarContorlCmd();
			msg = orderService.ajaxSendBookCarCmd(cmd, orderId, orderType);
		} else {
			// 修改订单为已取车
			msg.setSuccess(orderService.updateStatus(OrderStatus.已取车, "等待审查",
					orderId));
		}
		return msg;
	}

	/**
	 * 判断保证金
	 * 
	 * @param orderPO
	 * @return
	 */
	@RequestMapping(value = "moneyOfassure.htm")
	@ResponseBody
	public MSG moneyOfassure(BigDecimal moneyOfassure,
			BigDecimal cmoneyOfassure, HttpSession session) {
		MSG msg = new MSG();
		// 获取商家租车所需保证金
		SessionUserBO sessionUser = this.getSessionUser(session);
		Integer merchantId = sessionUser.getMerchantId();
		MerchantPO merchantPO = merchantService.getMerchant(merchantId);
		boolean flag = "是".equals(merchantPO.getAgreeSystemMoney());
		if (!flag) {
			if (moneyOfassure.compareTo(merchantPO.getRentalMoney()) < 0) {
				msg.setSuccess(false);
				msg.setInfo("保证金不足！(所需保证金:" + merchantPO.getRentalMoney()
						+ ")");
			}
		} else {
			if (moneyOfassure.compareTo(merchantPO.getRentalMoney()) < 0
					&& cmoneyOfassure.compareTo(merchantPO.getRentalMoney()) < 0) {
				msg.setSuccess(false);
				msg.setInfo("保证金不足！(所需保证金:" + merchantPO.getRentalMoney()
						+ ")");
			}
		}
		return msg;
	}

}

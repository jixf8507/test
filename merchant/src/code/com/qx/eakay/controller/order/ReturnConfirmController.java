package com.qx.eakay.controller.order;

import java.math.BigDecimal;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qx.eakay.controller.BaseController;
import com.qx.eakay.dao.car.CarDeviceDao;
import com.qx.eakay.model.car.CarDevice;
import com.qx.eakay.model.car.CarPO;
import com.qx.eakay.model.order.OrderPO;
import com.qx.eakay.service.car.CarDetailService;
import com.qx.eakay.service.car.CarService;
import com.qx.eakay.service.order.OrderService;
import com.qx.eakay.service.order.OtherOrderService;
import com.qx.eakay.util.MSG;

/**
 * 
 * @author jixf
 * @date 2015年7月14日
 */
@Controller
@RequestMapping("/order/returnConfirm")
public class ReturnConfirmController extends BaseController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private CarDetailService carDetailService;
	@Autowired
	private OtherOrderService otherOrderService;
	@Autowired
	private CarService carService;
	@Autowired
	private CarDeviceDao carDeviceDao;

	/**
	 * 进入还车审核页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("page.htm")
	public ModelAndView page() throws Exception {
		return new ModelAndView("order/returnConfirm");
	}

	/**
	 * 用户租车，企业租车还车审核编辑
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("edite.htm")
	public ModelAndView edite(Integer id, String currentCarStatus)
			throws Exception {
		OrderPO orderPO = orderService.getOrder(id);
		Integer carId = orderPO.getCarId();

		ModelMap modelMap = new ModelMap();
		orderPO = orderService.countKmsCost(orderPO.getId(),
				orderPO.getKmsForReturn());
		orderPO.setCurrentCarStatus(currentCarStatus);

		CarPO car = carService.getCar(carId);
		CarDevice carDevice = carDeviceDao.carDeviceByCarId(carId);
		// 有设备
		if (carDevice != null) {
			orderPO.setSurplusKmsForReturn(carDevice.getSurplusKms());
			orderPO.setKmsForReturn(carDevice.getCurKms());
			// 无设备
		} else {
			orderPO.setSurplusKmsForReturn(car.getSurplusKms());
			orderPO.setKmsForReturn(car.getKms());
		}
		modelMap.put("orderPO", orderPO);
		modelMap.put("carDetails",
				carDetailService.findDetails(orderPO.getCarId()));
		modelMap.put("carPO", carService.getCar(orderPO.getCarId()));// 查找车辆当前码表公里数
		return new ModelAndView("order/returnConfirmEdite", "modelMap",
				modelMap);
	}

	/**
	 * 车辆调度，公务用车还车审核编辑
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("editeOther.htm")
	public ModelAndView editeOther(Integer id, String currentCarStatus,
			Integer orderType) throws Exception {
		OrderPO orderPO = otherOrderService.getOrder(id);
		Integer carId = orderPO.getCarId();

		ModelMap modelMap = new ModelMap();
		orderPO.setCurrentCarStatus(currentCarStatus);

		CarPO car = carService.getCar(carId);
		CarDevice carDevice = carDeviceDao.carDeviceByCarId(carId);
		// 有设备
		if (carDevice != null) {
			orderPO.setSurplusKmsForReturn(carDevice.getSurplusKms());
			orderPO.setKmsForReturn(carDevice.getCurKms());
			// 无设备
		} else {
			orderPO.setSurplusKmsForReturn(car.getSurplusKms());
			orderPO.setKmsForReturn(car.getKms());
		}
		modelMap.put("orderPO", orderPO);
		modelMap.put("carDetails",
				carDetailService.findDetails(orderPO.getCarId()));
		modelMap.put("carPO", carService.getCar(orderPO.getCarId()));// 查找车辆当前码表公里数
		return new ModelAndView("otherOrder/returnConfirmEdite", "modelMap",
				modelMap);
	}

	/**
	 * 保存还车审核
	 * 
	 * @param orderPO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveEdite.htm")
	@ResponseBody
	public MSG saveEdite(OrderPO orderPO, String[] components,
			String[] statuss, String[] describes, int[] ids, HttpSession session)
			throws Exception {
		MSG msg = new MSG();

		OrderPO order = orderService.getOrderById(orderPO.getId());
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

		orderPO.setMenForReturn(this.getSessionUser(session).getName());
		// if (ids != null) {
		// for (int i = 0; i < ids.length; i++) {
		// CarDetailPO detailPO = new CarDetailPO();
		// detailPO.setComponent(components[i]);
		// detailPO.setDescribe(describes[i]);
		// detailPO.setStatus(statuss[i]);
		// detailPO.setId(ids[i]);
		// orderPO.getCarPO().getCarDetails().add(detailPO);
		// }
		// }
		msg.setSuccess(orderService.saveReturnConfirmEdite(orderPO));
		return msg;
	}

	/**
	 * 计算费用
	 * 
	 * @param orderPO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("countKmsCost.htm")
	@ResponseBody
	public HashMap<String, Object> countKmsCost(Integer orderId,
			float kmsForReturn) throws Exception {
		OrderPO orderCost = orderService.countKmsCost(orderId, kmsForReturn);

		BigDecimal useCost = orderCost.getUseCost();
		BigDecimal totalCost = orderCost.getTotalCost();
		Float perKms = orderCost.getPerKms();
		BigDecimal perKmsCost = orderCost.getPerKmsCost();
		BigDecimal perKmsCost1 = orderCost.getPerKmsCost1();

		HashMap<String, Object> result = new HashMap<>();
		result.put("useCost", useCost);
		result.put("totalCost", totalCost);
		result.put("perKms", perKms);
		result.put("perKmsCost", perKmsCost);
		result.put("perKmsCost1", perKmsCost1);

		MSG msg = new MSG();
		msg.setSuccess(true);
		result.put("msg", msg);

		return result;
	}

}

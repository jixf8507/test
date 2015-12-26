package com.qx.eakay.controller.order;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qx.eakay.controller.BaseController;
import com.qx.eakay.model.order.OrderPO;
import com.qx.eakay.service.car.CarDetailService;
import com.qx.eakay.service.car.CarService;
import com.qx.eakay.service.order.OrderService;
import com.qx.eakay.service.order.OtherOrderService;
import com.qx.eakay.service.order.UnitOrderService;
import com.qx.eakay.util.MSG;

/**
 * 取车审核
 * 
 * @author jixf
 * @date 2015年7月13日
 */
@Controller
@RequestMapping("/order/getConfirm")
public class GetConfirmController extends BaseController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private CarDetailService carDetailService;
	@Autowired
	private UnitOrderService unitOrderService;
	@Autowired
	private OtherOrderService otherOrderService;
	@Autowired
	private CarService carService;

	/**
	 * 进入取车审核页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("page.htm")
	public ModelAndView page() throws Exception {
		return new ModelAndView("order/getConfirm");
	}

	/**
	 * 进入取车审核页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("edite.htm")
	public ModelAndView edite(Integer id, Integer orderType) throws Exception {
		ModelMap modelMap = new ModelMap();
		OrderPO orderPO = null;
		// 车辆调度，公务用车
		if (orderType == 3 || orderType == 4) {
			orderPO = otherOrderService.getOrder(id);
			// 用户租车，企业租车
		} else {
			orderPO = orderService.getOrder(id);
		}
		modelMap.put("orderPO", orderPO);
		// modelMap.put("carDetails",
		// carDetailService.findDetails(orderPO.getCarId()));
		modelMap.put("carPO", carService.getCar(orderPO.getCarId()));// 查找车辆当前码表公里数
		return new ModelAndView("order/getConfirmEdite", "modelMap", modelMap);
	}

	/**
	 * 保存取车审核
	 * 
	 * @param orderPO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveEdite.htm")
	@ResponseBody
	public MSG saveEdite(OrderPO orderPO, HttpSession session) throws Exception {
		MSG msg = new MSG();
		
		OrderPO order = orderService.getOrderById(orderPO.getId());
		
		if (!"已取车".equals(order.getOrderStatus())) {
			return new MSG("当前租车订单"+order.getOrderStatus()+"！", 1);
		}
		
		if (orderPO.getScheReturnTime() == null
				|| orderPO.getScheReturnTime().equals("")) {
			orderPO.setScheReturnTime(orderPO.getReaGetTime());
		}
		orderPO.setMenForGet(this.getSessionUser(session).getName());
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
		msg.setSuccess(orderService.saveGetConfirmEdite(orderPO));
		return msg;
	}

}

package com.qx.eakay.controller.customer;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qx.common.base.PageHelp;
import com.qx.common.base.PageResults;
import com.qx.common.tools.JSONTools;
import com.qx.common.tools.StringTools;
import com.qx.eakay.controller.BaseController;
import com.qx.eakay.model.customer.CustomerCarsPO;
import com.qx.eakay.service.customer.CustomerCarsService;
import com.qx.eakay.util.MSG;

@Controller
@RequestMapping("/customer/cars")
public class CustomerCarsController extends BaseController {

	@Autowired
	private CustomerCarsService carsService;

	/**
	 * 进入客户信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("page.htm")
	public ModelAndView page() throws Exception {
		return new ModelAndView("customer/customerCars");
	}

	/**
	 * 进入添加用户车辆列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("add.htm")
	public ModelAndView add() throws Exception {
		return new ModelAndView("customer/customerCarsAdd");
	}
	

	
	

	/**
	 * 保存新增用户
	 * 
	 * @param customerPO
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveAdd.htm")
	public ModelAndView saveAdd(CustomerCarsPO customerCarsPO) throws Exception {
		carsService.createCustomerCars(customerCarsPO);
		return new ModelAndView("redirect:/system/message/success.htm");
	}
	
	/**
	 * 通过按钮保存新增用户
	 * 
	 * @param customerPO
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveAdd2.htm")
	@ResponseBody
	public MSG saveAdd2(CustomerCarsPO customerCarsPO) throws Exception {
		MSG msg =new MSG();
		msg.setSuccess(carsService.createCustomerCars(customerCarsPO));
		return msg;
	}
	

	/**
	 * 进入编辑用户信息界面
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("edite.htm")
	public ModelAndView edite(Integer id, HttpSession session) throws Exception {
		CustomerCarsPO customerCarsPO = carsService.getCustomerCars(id, this
				.getSessionUser(session).getMerchantId());
		return new ModelAndView("customer/customerCarsEdite", "customerCarsPO",
				customerCarsPO);
	}

	/**
	 * 保存修改用户信息
	 * 
	 * @param customerPO
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "saveEdite.htm")
	@ResponseBody
	public MSG saveEdite(CustomerCarsPO customerCarsPO) {
		MSG msg = new MSG();
		boolean bool = carsService.updateCustomerCars(customerCarsPO);
		msg.setSuccess(bool);
		return msg;
	}

	@RequestMapping(value = "delete.htm")
	@ResponseBody
	public MSG delete(Integer id) {
		MSG msg = new MSG();
		boolean bool = carsService.deleteCustomerCars(id);
		msg.setSuccess(bool);
		return msg;
	}

	/**
	 * 分页查询客户信息
	 * 
	 * @param aoData
	 * @param paraData
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "ajaxData.htm")
	@ResponseBody
	public PageResults ajaxData(String aoData, String paraData,
			HttpSession session) {
		JSONObject jsonObject = getJsonPara(paraData, session);
		// jquery.datatables 分页查询的参数
		JSONArray jsonArray = JSONArray.fromObject(aoData);
		PageHelp pageHelp = JSONTools.toPageHelp(jsonArray);

		PageResults pageResults = carsService.findCustomerCarsPage(jsonObject,
				pageHelp.getiDisplayLength(), pageHelp.getiDisplayStart());
		pageResults.setsEcho(pageHelp.getsEcho());
		return pageResults;
	}

	/**
	 * 得到请求参数条件
	 * 
	 * @param paraData
	 * @param session
	 * @return
	 */
	private JSONObject getJsonPara(String paraData, HttpSession session) {
		paraData = StringTools.decodeMethod(paraData); // 动态查询条件参数
		JSONObject jsonObject = JSONObject.fromObject(paraData);
		jsonObject.put("merchantId", this.getSessionUser(session)
				.getMerchantId());
		return jsonObject;
	}

	/**
	 * 导出下载客户信息到Excel表格
	 * 
	 * @param paraData
	 * @param session
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("exportToExcel.htm")
	public ModelAndView exportToExcel(String paraData,
			HttpServletResponse response, HttpSession session) throws Exception {
		JSONObject jsonObject = getJsonPara(paraData, session);
		carsService.excelExportCustomerCars(response, jsonObject);
		return null;
	}

}

package com.qx.eakay.controller.customer;

import java.util.List;
import java.util.Map;

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
import com.qx.eakay.login.SessionUserBO;
import com.qx.eakay.model.customer.CustomerPO;
import com.qx.eakay.service.customer.CustomerCarsService;
import com.qx.eakay.service.customer.CustomerService;
import com.qx.eakay.service.customer.CustomerUnitService;
import com.qx.eakay.service.merchant.MerchantFamilyService;
import com.qx.eakay.util.MSG;

/**
 * 
 * @author jixf
 * @date 2015年7月8日
 */
@Controller
@RequestMapping("/customer/customer")
public class CustomerController extends BaseController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CustomerCarsService carsService;
	
	@Autowired
	private CustomerUnitService customerUnitService;
	
	@Autowired
	private MerchantFamilyService merchantFamilyService;
	
	/**
	 * 进入客户详细信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("detail.htm")
	public ModelAndView detail(Integer id,HttpSession session) throws Exception {
		
		SessionUserBO sessionUser = this.getSessionUser(session);
		CustomerPO customerPO = customerService.getCustomer(id,
				sessionUser.getMerchantId());
		return new ModelAndView("customer/customerDetail","customerPO",customerPO);
	}

	/**
	 * 进入客户信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("page.htm")
	public ModelAndView page() throws Exception {
		return new ModelAndView("customer/customer");
	}

	/**
	 * 进入用户信息管理页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("manager.htm")
	public ModelAndView manager() throws Exception {
		return new ModelAndView("customer/customerManager");
	}

	/**
	 * 进入新增用户页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("add.htm")
	public ModelAndView add() throws Exception {
		return new ModelAndView("customer/customerAdd");
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
	public ModelAndView saveAdd(CustomerPO customerPO, HttpSession session)
			throws Exception {
		SessionUserBO sessionUser = this.getSessionUser(session);
		customerPO.getAccountPO().setMerchantId(sessionUser.getMerchantId());
		customerPO.setVerifyMan(sessionUser.getName());
		customerPO.getAccountPO().setTransactUser(sessionUser.getName());
		customerService.createCustomer(customerPO);
		return new ModelAndView("redirect:/system/message/success.htm");
	}
	
	
	/**
	 * 通过按钮的形式保存新增用户
	 * 
	 * @param customerPO
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveAdd2.htm")
	@ResponseBody
	public MSG saveAdd2(CustomerPO customerPO, HttpSession session)
			throws Exception {
		MSG msg =new MSG();
		SessionUserBO sessionUser = this.getSessionUser(session);
		customerPO.getAccountPO().setMerchantId(sessionUser.getMerchantId());
		customerPO.setVerifyMan(sessionUser.getName());
		customerPO.getAccountPO().setTransactUser(sessionUser.getName());
		msg.setSuccess(customerService.createCustomer(customerPO));
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
		SessionUserBO sessionUser = this.getSessionUser(session);
		CustomerPO customerPO = customerService.getCustomer(id,
				sessionUser.getMerchantId());
		return new ModelAndView("customer/customerEdite", "customerPO",
				customerPO);
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
	public MSG saveEdite(CustomerPO customerPO, HttpSession session) {
		MSG msg = new MSG();
		SessionUserBO sessionUser = this.getSessionUser(session);
		customerPO.setVerifyMan(sessionUser.getName());
		boolean bool = customerService.updateCustomer(customerPO);
		msg.setSuccess(bool);
		return msg;
	}

	/**
	 * 检测手机号码是否存在
	 * 
	 * @param phone
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "checkPhone.htm")
	@ResponseBody
	public MSG checkPhone(String phone, Integer id) {
		MSG msg = new MSG();
		boolean bool = customerService.checkPhone(phone, id);
		msg.setSuccess(bool);
		return msg;
	}

	/**
	 * 检测用户租赁卡号是否存在
	 * 
	 * @param phone
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "checkCardNO.htm")
	@ResponseBody
	public MSG checkCardNO(String cardNO, Integer id, HttpSession session) {
		SessionUserBO sessionUser = this.getSessionUser(session);
		MSG msg = new MSG();
		boolean bool = customerService.checkCardNO(cardNO,
				sessionUser.getMerchantId(), id);
		msg.setSuccess(bool);
		return msg;
	}
	
	/**
	 * 检测身份证是否存在
	 * 
	 * @param idCard
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "checkIdCard.htm")
	@ResponseBody
	public MSG checkIdCard(String idCard, Integer id ) {
		MSG msg = new MSG();
		boolean bool = customerService.checkIdCard(idCard, id);
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

		PageResults pageResults = customerService.findCustomerPage(jsonObject,
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
		paraData = StringTools.decodeMethod(paraData);
		// 动态查询条件参数
		JSONObject jsonObject = JSONObject.fromObject(paraData);
		SessionUserBO sessionUser = this.getSessionUser(session);
		jsonObject.put("merchantId", sessionUser.getMerchantId());
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
	public ModelAndView exportToExcel(String paraData, HttpSession session,
			HttpServletResponse response) throws Exception {
		JSONObject jsonObject = getJsonPara(paraData, session);
		customerService.excelExportCustomer(response, jsonObject);
		return null;
	}
	
	/**
	 * 导出企业用户信息到Excel表格
	 * 
	 * @param paraData
	 * @param session
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("exportUnitToExcel.htm")
	public ModelAndView exportUnitToExcel(String paraData, HttpSession session,
			HttpServletResponse response) throws Exception {
		JSONObject jsonObject = getJsonPara(paraData, session);
		customerUnitService.excelExportUnitCustomer(response, jsonObject);
		return null;
	}
	
	
	/**
	 * 导出家庭用户信息到Excel表格
	 * 
	 * @param paraData
	 * @param session
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("exportFamilyToExcel.htm")
	public ModelAndView exportFamilyToExcel(String paraData, HttpSession session,
			HttpServletResponse response) throws Exception {
		JSONObject jsonObject = getJsonPara(paraData, session);
		merchantFamilyService.excelExportFamilyCustomer(response, jsonObject);
		return null;
	}
	
	
	
	

	@RequestMapping(value = "ajaxCustomer.htm")
	@ResponseBody
	public List<Map<String, Object>> ajaxCustomer(HttpSession session) {
		JSONObject jsonObject = new JSONObject();
		SessionUserBO sessionUser = this.getSessionUser(session);
		jsonObject.put("merchantId", sessionUser.getMerchantId());
		jsonObject.put("status", "正常");
		return customerService.findCustomers(jsonObject);
	}

}

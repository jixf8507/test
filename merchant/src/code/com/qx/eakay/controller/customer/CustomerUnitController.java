package com.qx.eakay.controller.customer;

import java.util.ArrayList;
import java.util.List;

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
import com.qx.eakay.model.customer.AccountRecordPO;
import com.qx.eakay.model.customer.UnitCustomerPO;
import com.qx.eakay.service.customer.CustomerCarsService;
import com.qx.eakay.service.customer.CustomerUnitService;
import com.qx.eakay.util.MSG;

/**
 * 单位会员管理
 * 
 * @author sdf
 * @date 2015年10月6日
 */
@Controller
@RequestMapping("/customer/unit")
public class CustomerUnitController extends BaseController {

	@Autowired
	private CustomerUnitService unitService;

	@Autowired
	private CustomerCarsService carsService;

	/**
	 * 进入单位会员管理页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("manager.htm")
	public ModelAndView manager() throws Exception {
		return new ModelAndView("customer/unitCustomerManager");
	}

	/**
	 * 进入新增单位会员页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("add.htm")
	public ModelAndView add() throws Exception {
		return new ModelAndView("customer/unitCustomerAdd");
	}

	/**
	 * 保存新增单位会员
	 * 
	 * @param unitCustomerPO
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveAdd.htm")
	@ResponseBody
	public MSG saveAdd(UnitCustomerPO unitCustomerPO, HttpSession session) {
		MSG msg = new MSG();
		SessionUserBO sessionUser = this.getSessionUser(session);
		unitCustomerPO.setMerchantId(sessionUser.getMerchantId());
		unitCustomerPO.setTransactUser(sessionUser.getName());
		boolean bool = unitService.createCustomer(unitCustomerPO);
		msg.setSuccess(bool);
		return msg;
	}

	/**
	 * 分页查询单位会员信息
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
		JSONArray jsonArray = JSONArray.fromObject(aoData);
		PageHelp pageHelp = JSONTools.toPageHelp(jsonArray);

		PageResults pageResults = unitService.findUnitCustomerPage(jsonObject,
				pageHelp.getiDisplayLength(), pageHelp.getiDisplayStart());
		pageResults.setsEcho(pageHelp.getsEcho());
		return pageResults;
	}

	/**
	 * 分页查询单位会员交易信息
	 * 
	 * @param aoData
	 * @param paraData
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "ajaxTradeData.htm")
	@ResponseBody
	public PageResults ajaxTradeData(String aoData, String paraData,
			HttpSession session) {
		JSONObject jsonObject = getJsonPara(paraData, session);
		JSONArray jsonArray = JSONArray.fromObject(aoData);
		PageHelp pageHelp = JSONTools.toPageHelp(jsonArray);

		PageResults pageResults = unitService.findUnitCustomerTradePage(
				jsonObject, pageHelp.getiDisplayLength(),
				pageHelp.getiDisplayStart());
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
	 * 导出下载单位会员交易信息到Excel表格
	 * 
	 * @param paraData
	 * @param session
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("exportTradeToExcel.htm")
	public ModelAndView exportTradeToExcel(String paraData,
			HttpSession session, HttpServletResponse response) throws Exception {
		JSONObject jsonObject = getJsonPara(paraData, session);
		unitService.excelExportUnitCustomerTrade(response, jsonObject);
		return null;
	}

	/**
	 * 导出下载单位会员信息到Excel表格
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
		unitService.excelExportUnitCustomer(response, jsonObject);
		return null;
	}

	/**
	 * 进入编辑单位会员信息界面
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("edite.htm")
	public ModelAndView edite(Integer id, HttpSession session) throws Exception {
		UnitCustomerPO unitCustomerPO = unitService.getUnitCustomer(id);
		return new ModelAndView("customer/unitCustomerEdite", "unitCustomerPO",
				unitCustomerPO);
	}

	/**
	 * 保存修改单位会员信息
	 * 
	 * @param customerPO
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "saveEdite.htm")
	@ResponseBody
	public MSG saveEdite(UnitCustomerPO unitCustomerPO, HttpSession session) {
		MSG msg = new MSG();
		SessionUserBO sessionUser = this.getSessionUser(session);
		unitCustomerPO.setMerchantId(sessionUser.getMerchantId());
		unitCustomerPO.setTransactUser(sessionUser.getName());
		boolean bool = unitService.updateCustomer(unitCustomerPO);
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
		boolean bool = unitService.checkPhone(phone, id);
		msg.setSuccess(bool);
		return msg;
	}

	/**
	 * 检测企业名称是否已存在
	 * 
	 * @param phone
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "checkName.htm")
	@ResponseBody
	public MSG checkName(String name, Integer id) {
		MSG msg = new MSG();
		boolean bool = unitService.checkName(name, id);
		msg.setSuccess(bool);
		return msg;
	}

	/**
	 * 进入单位会员详细信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("detail.htm")
	public ModelAndView detail(Integer id) throws Exception {
		UnitCustomerPO unitCustomerPO = unitService.getUnitCustomer(id);
		return new ModelAndView("customer/unitCustomerDetail",
				"unitCustomerPO", unitCustomerPO);
	}

	/**
	 * 更改单位会员状态 (正常)
	 * 
	 * @param status
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "changeStatus.htm")
	@ResponseBody
	public MSG changeStatus(String status, Integer id,HttpSession session) {
		SessionUserBO sessionUser = this.getSessionUser(session);
		return unitService.changeStatus(status,sessionUser.getName(),id);
	}

	/**
	 * 更改单位会员状态（注销，冻结）
	 * 
	 * @param status
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "changeStatusSubmit.htm")
	@ResponseBody
	public MSG changeStatusSubmit(UnitCustomerPO unitCustomerPO,HttpSession session) {
		SessionUserBO sessionUser = this.getSessionUser(session);
		unitCustomerPO.setDeleteUser(sessionUser.getName());
		return unitService.changeStatusSubmit(unitCustomerPO);
	}

	/**
	 * 进入单位会员退款页面
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("refund.htm")
	public ModelAndView refund(Integer id) throws Exception {
		UnitCustomerPO unitCustomerPO = unitService.getUnitCustomer(id);
		return new ModelAndView("customer/unitCustomerRefund", "unitCustomerPO",
				unitCustomerPO);
	}
	
	/**
	 * 保存单位会员退款
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveRefund.htm")
	@ResponseBody
	public MSG saveRefund(AccountRecordPO recordPO, HttpSession session) {
		MSG msg = new MSG();
		SessionUserBO sessionUser = this.getSessionUser(session);
		recordPO.setTransactUser(sessionUser.getName());
		boolean bool = unitService.refundUnitCustomerAccount(recordPO);
		msg.setSuccess(bool);
		return msg;
	}
	
	/**
	 * 保存单位会员批量添加用户
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveCustomerAdd.htm")
	@ResponseBody
	public MSG saveCustomerAdd(String customerIds,Integer id) {
		MSG msg = new MSG();
		List<Integer> customerId = new ArrayList<>();
		if(customerIds==""||customerIds.length()==0){
			customerId.add(0);
		}else{
			String[] customer = customerIds.split(",");
			for (String cid : customer) {
				customerId.add(Integer.parseInt(cid));
			}
		}
		msg.setSuccess(unitService.saveCustomerAdd(customerId,id));
		return msg;
		
	}

	/**
	 * 分页查询单位的用户信息
	 * 
	 * @param aoData
	 * @param paraData
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "ajaxCustomerData.htm")
	@ResponseBody
	public PageResults ajaxCustomerData(String aoData, String paraData,
			HttpSession session) {
		JSONObject jsonObject = getJsonPara(paraData, session);
		JSONArray jsonArray = JSONArray.fromObject(aoData);
		PageHelp pageHelp = JSONTools.toPageHelp(jsonArray);

		PageResults pageResults = unitService.findUnitUnitCustomerPage(jsonObject,
				pageHelp.getiDisplayLength(), pageHelp.getiDisplayStart());
		pageResults.setsEcho(pageHelp.getsEcho());
		return pageResults;
	}

	/**
	 * 导出下载单位的用户信息到Excel表格
	 * 
	 * @param paraData
	 * @param session
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("exportCustomerToExcel.htm")
	public ModelAndView exportCustomerToExcel(String paraData, HttpSession session,
			HttpServletResponse response) throws Exception {
		JSONObject jsonObject = getJsonPara(paraData, session);
		unitService.excelExportUnitUnitCustomer(response, jsonObject);
		return null;
	}
	
	/**
	 * 移除商家客户
	 * 
	 * @param status
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delCustomer.htm")
	@ResponseBody
	public MSG delCustomer(Integer enterpriseId, Integer id) {
		MSG msg = new MSG();
		msg.setSuccess(unitService.delCustomer(enterpriseId,id));
		return msg;
	}
	
	
}

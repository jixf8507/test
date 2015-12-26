package com.qx.eakay.controller.customer;

import java.math.BigDecimal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qx.eakay.controller.BaseController;
import com.qx.eakay.login.SessionUserBO;
import com.qx.eakay.model.customer.AccountPO;
import com.qx.eakay.model.customer.AccountPO.AccountStatus;
import com.qx.eakay.model.customer.AccountRecordPO;
import com.qx.eakay.service.customer.CustomerAccountService;
import com.qx.eakay.util.MSG;

/**
 * 
 * @author jixf
 * @date 2015年7月9日
 */
@Controller
@RequestMapping("/customer/account")
public class CustomerAccountController extends BaseController {

	@Autowired
	private CustomerAccountService accountService;

	/**
	 * 进入用户注销冻结页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("page.htm")
	public ModelAndView page() throws Exception {
		return new ModelAndView("customer/customerAccount");
	}

	/**
	 * 进入余额充值界面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("balanceCharge.htm")
	public ModelAndView balanceCharge() throws Exception {
		return new ModelAndView("customer/balanceCharge");
	}

	/**
	 * 提交保存账户余额充值
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveBalanceCharge.htm")
	public ModelAndView saveBalanceCharge(AccountRecordPO accountRecordPO,
			HttpSession session) throws Exception {
		SessionUserBO sessionUser = this.getSessionUser(session);
		accountRecordPO.setTransactUser(sessionUser.getName());
		accountService.saveBalanceCharge(accountRecordPO);
		return new ModelAndView("redirect:/system/message/success.htm");
	}
	
	/**
	 * 提交保存账户余额充值(充电卡充值)
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveCardBalanceCharge.htm")
	@ResponseBody
	public MSG saveCardBalanceCharge(AccountRecordPO accountRecordPO,
			HttpSession session) throws Exception {
		MSG msg = new MSG();
		SessionUserBO sessionUser = this.getSessionUser(session);
		accountRecordPO.setTransactUser(sessionUser.getName());
		msg.setSuccess(accountService.saveBalanceCharge(accountRecordPO));
		return msg;
	}

	/**
	 * 进入保证金充值界面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("assureCharge.htm")
	public ModelAndView assureCharge() throws Exception {
		return new ModelAndView("customer/assureCharge");
	}

	/**
	 * 提交保存保证金充值
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveAssureCharge.htm")
	public ModelAndView saveAssureCharge(AccountRecordPO accountRecordPO,
			HttpSession session) throws Exception {
		SessionUserBO sessionUser = this.getSessionUser(session);
		accountRecordPO.setTransactUser(sessionUser.getName());
		accountService.saveAssureCharge(accountRecordPO);
		return new ModelAndView("redirect:/system/message/success.htm");
	}

	/**
	 * 更改设备状态
	 * 
	 * @param status
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "changeStatus.htm")
	@ResponseBody
	public MSG changeStatus(AccountStatus status, Integer id,
			HttpSession session) {
		SessionUserBO sessionUser = this.getSessionUser(session);
		return accountService.updateAccountStatus(status,
				sessionUser.getName(), id);
	}

	/**
	 * 进入用户退款页面
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("refund.htm")
	public ModelAndView refund(Integer id) throws Exception {
		AccountPO accountPO = accountService.getAccount(id);
		return new ModelAndView("customer/accountRefund", "accountPO",
				accountPO);
	}

	/**
	 * 保证金退款
	 * 
	 * @param accountRecordPO
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "saveRefund.htm")
	@ResponseBody
	public MSG saveRefund(AccountRecordPO accountRecordPO, HttpSession session) {
		MSG msg = new MSG();
		SessionUserBO sessionUser = this.getSessionUser(session);
		accountRecordPO.setAddBalance(new BigDecimal(0)
				.subtract(accountRecordPO.getAddBalance()));
		accountRecordPO.setTransactUser(sessionUser.getName());
		boolean bool = accountService.refundCustomerAccount(accountRecordPO);
		msg.setSuccess(bool);
		return msg;
	}

	/**
	 * 进入用户补卡页面
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("repair.htm")
	public ModelAndView repairCard(Integer id) throws Exception {
		AccountPO accountPO = accountService.getAccount(id);
		return new ModelAndView("customer/accountRepair", "accountPO",
				accountPO);
	}

	/**
	 * 用户补卡
	 * 
	 * @param accountRecordPO
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "saveRepair.htm")
	@ResponseBody
	public MSG saveRepair(AccountPO accountPO, Integer siteId,
			HttpSession session) {
		MSG msg = new MSG();
		SessionUserBO sessionUser = this.getSessionUser(session);
		boolean bool = accountService.repairCustomerAccount(accountPO,
				sessionUser.getName(), siteId);
		msg.setSuccess(bool);
		return msg;
	}

	/**
	 * 冻结页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "freeze.htm")
	public ModelAndView freeze(Integer id) {
		ModelMap modelMap = new ModelMap();
		modelMap.put("id", id);
		return new ModelAndView("customer/customerFreeze", "modelMap", modelMap);
	}

	/**
	 * 注销页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "cancel.htm")
	public ModelAndView cancel(Integer id) {
		ModelMap modelMap = new ModelMap();
		modelMap.put("id", id);
		return new ModelAndView("customer/customerCancel", "modelMap", modelMap);
	}

	/**
	 * 更改状态（冻结）
	 * 
	 * @param status
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "changeStatusSubmit.htm")
	@ResponseBody
	public MSG changeStatusSubmit(AccountPO accountPO,HttpSession session) {
		SessionUserBO sessionUser = this.getSessionUser(session);
		accountPO.setDeletedUser(sessionUser.getName());
		return accountService.changeStatusSubmit(accountPO);
	}
	
	
	
	/**
	 * 更改状态（退款）
	 * 
	 * @param status
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "backMoneySubmit.htm")
	@ResponseBody
	public MSG backMoneySubmit(AccountPO accountPO,HttpSession session) {
		SessionUserBO sessionUser = this.getSessionUser(session);
		accountPO.setDeletedUser(sessionUser.getName());
		return accountService.backMoneySubmit(accountPO);
	}

}

package com.qx.eakay.controller.api.stake;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qx.eakay.controller.BaseController;
import com.qx.eakay.dao.customer.CustomerMessageDao;
import com.qx.eakay.dao.stake.StakeCardInfoDao;
import com.qx.eakay.model.customer.AccountRecordPO;
import com.qx.eakay.model.customer.AccountRecordPO.AccountRecordType;
import com.qx.eakay.model.stake.StakeCardInfoPO;
import com.qx.eakay.service.customer.CustomerAccountService;
import com.qx.eakay.util.MSG;

/**
 * 充电卡充值
 * 
 * @author sdf
 * @date 2015年12月9日
 */
@Controller
@RequestMapping("/api/stakeCardCharge")
public class StakeCardChargeApiController extends BaseController {

	@Autowired
	private CustomerAccountService accountService;
	@Autowired
	private CustomerMessageDao customerMessageDao;
	@Autowired
	private StakeCardInfoDao cardInfoDao;

	/**
	 * 保存账户余额充值(充电卡充值，联机)
	 * 
	 * @param request
	 * @param accountId
	 *            客户账户ID
	 * @param userName
	 *            经办人
	 * @param addBalance
	 *            充值金额
	 * @param describe
	 *            付费方式
	 * @param siteId
	 *            付费租赁点
	 * @param ticket
	 *            票据编号
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save.htm")
	@ResponseBody
	public MSG saveCardBalanceCharge(HttpServletRequest request)
			throws Exception {
		MSG msg = new MSG();
		String accountId = request.getParameter("accountId");
		String userName = request.getParameter("userName");
		String addBalance = request.getParameter("addBalance");
		String describe = request.getParameter("describe");
		String siteId = request.getParameter("siteId");
		String ticket = request.getParameter("ticket");

		if (accountId == null || "".equals(accountId)) {
			return new MSG("客户账户ID为空", 1);
		}
		if (userName == null || "".equals(userName)) {
			return new MSG("经办人为空", 1);
		}
		if (addBalance == null || "".equals(addBalance)) {
			return new MSG("充值金额为空", 1);
		}
		if (describe == null || "".equals(describe)) {
			return new MSG("付费方式为空", 1);
		}
		if (siteId == null || "".equals(siteId)) {
			return new MSG("付费租赁点为空", 1);
		}
		if (ticket == null) {
			return new MSG("票据编号为空", 1);
		}

		AccountRecordPO accountRecordPO = new AccountRecordPO();

		accountRecordPO.setAccountId(Integer.parseInt(accountId));
		accountRecordPO.setTransactUser(userName);
		accountRecordPO.setAddBalance(new BigDecimal(addBalance));
		accountRecordPO.setDescribe(describe);
		accountRecordPO.setSiteId(Integer.parseInt(siteId));
		accountRecordPO.setTicket(ticket);

		boolean flag = accountService.saveBalanceCharge(accountRecordPO);
 
		if (flag) {
			msg.setSuccess(flag);
			msg.setInfo("充值成功");
		} else {
			return new MSG("充值失败", 1);
		}
		return msg;
	}

	/**
	 * 增加充值消息(电子钱包充值，脱机)
	 * 
	 * @param request
	 * @param userName
	 *            经办人
	 * @param cardID
	 *            内置卡号
	 * @param balance
	 *            充电卡余额
	 * @param addBalance
	 *            充值金额
	 * @param describe
	 *            付费方式
	 * @param siteId
	 *            付费租赁点
	 * @param ticket
	 *            票据编号
	 * @param merchantId
	 *            商家ID
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveMessage.htm")
	@ResponseBody
	public MSG saveMessage(HttpServletRequest request) throws Exception {
		MSG msg = new MSG();
		String siteId = request.getParameter("siteId");
		String userName = request.getParameter("userName");
		String cardID = request.getParameter("cardID");
		String addBalance = request.getParameter("addBalance");
		String balance = request.getParameter("balance");
		String merchantId = request.getParameter("merchantId");
		String ticket = request.getParameter("ticket");
		String describe = request.getParameter("describe");

		if (userName == null || "".equals(userName)) {
			return new MSG("经办人为空", 1);
		}
		if (siteId == null || "".equals(siteId)) {
			return new MSG("付费租赁点为空", 1);
		}
		if (cardID == null || "".equals(cardID)) {
			return new MSG("内置卡号为空", 1);
		}
		if (merchantId == null || "".equals(merchantId)) {
			return new MSG("商家ID为空", 1);
		}
		if (balance == null || "".equals(balance)) {
			return new MSG("充电卡余额为空", 1);
		}
		if (addBalance == null || "".equals(addBalance)) {
			return new MSG("充值金额为空", 1);
		}
		if (ticket == null) {
			return new MSG("票据编号为空", 1);
		}
		
		// 查找客户ID
		StakeCardInfoPO cardInfoPO = cardInfoDao.findByCardID(cardID,Integer.parseInt(merchantId));
		if (cardInfoPO == null) {
			return new MSG("该充电卡未绑定客户", 1);
		} else {
			Integer customerId = cardInfoPO.customerPO.getId();
			Integer cardInfoId = cardInfoPO.getId();

			AccountRecordPO accountRecordPO = new AccountRecordPO();

			accountRecordPO.setCustomerTable("customer");
			accountRecordPO.setAccountTable("stake_cardinfo");
			accountRecordPO.setAccountId(cardInfoId);
			accountRecordPO.setCustomerId(customerId);
			accountRecordPO.setSiteId(Integer.parseInt(siteId));
			accountRecordPO.setOldBalance(new BigDecimal(balance));
			accountRecordPO.setAddBalance(new BigDecimal(addBalance));
			accountRecordPO.setNewBalance(new BigDecimal(balance)
					.add(new BigDecimal(addBalance)));
			accountRecordPO.setTransactUser(userName);
			accountRecordPO.setDescribe(describe);
			accountRecordPO.setTicket(ticket);
			accountRecordPO.setType(AccountRecordType.电子钱包充值);

			boolean flag = accountService.saveStakeCardBalanceCharge(
					accountRecordPO, cardID);

			if (flag) {
				msg.setSuccess(true);
				msg.setInfo("充值成功");
			} else {
				return new MSG("充值失败", 1);
			}
		}
		return msg;
	}

}

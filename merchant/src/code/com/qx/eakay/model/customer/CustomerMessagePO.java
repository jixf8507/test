package com.qx.eakay.model.customer;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import com.qx.common.tools.MyDateUtil;
import com.qx.eakay.model.order.OrderPO;

public class CustomerMessagePO {

	private static enum Types {
		订单通知, 付款通知, 账户充值
	}

	private Integer id;
	private Integer customerId;
	private String content;
	private String type;
	private String status;
	private Timestamp createdTime;

	private CustomerMessagePO() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	/**
	 * 取消订单通知
	 * 
	 * @param orderId
	 *            订单编号ID
	 * @param customerId
	 *            用户ID
	 * @return
	 */
	public static CustomerMessagePO createOrderCancelMsg(Integer orderId,
			Integer customerId) {
		// 创建取消通知
		CustomerMessagePO msg = new CustomerMessagePO();
		msg.setCustomerId(customerId);
		msg.setContent("由于您没有在规定时间范围内前来取车，您的订单[ZCDD_" + orderId
				+ "]已被系统自动取消,请重新选择车辆。给您用车带来不便，望请见谅！");
		msg.setType(Types.订单通知.name());
		return msg;
	}
	
	/**
	 * 取消订单通知
	 * 
	 * @param orderId
	 *            订单编号ID
	 * @param customerId
	 *            用户ID
	 * @return
	 */
	public static CustomerMessagePO createOrderCancelReturnMsg(Integer orderId,
			Integer customerId) {
		// 创建取消通知
		CustomerMessagePO msg = new CustomerMessagePO();
		msg.setCustomerId(customerId);
		msg.setContent("由于您没有在规定时间范围内关门还车，您的订单[ZCDD_" + orderId
				+ "]已被系统自动取消还车，请重新还车！");
		msg.setType(Types.订单通知.name());
		return msg;
	}
	
	/**
	 * 取消充电订单通知
	 * 
	 * @param orderId
	 *            订单编号ID
	 * @param customerId
	 *            用户ID
	 * @return
	 */
	public static CustomerMessagePO createChargeOrderCancelMsg(Integer orderId,
			Integer customerId) {
		// 创建取消通知
		CustomerMessagePO msg = new CustomerMessagePO();
		msg.setCustomerId(customerId);
		msg.setContent("由于您没有在规定时间范围内前来充电，您的充电订单[CDDD_" + orderId
				+ "]已被系统自动取消,请重新预约充电。给您充电带来不便，望请见谅！");
		msg.setType(Types.订单通知.name());
		return msg;
	}

	/**
	 * 租赁付费消息通知
	 * 
	 * @param orderPO
	 */
	public static CustomerMessagePO createChargeMsg(OrderPO orderPO) {
		CustomerMessagePO customerMessagePO = new CustomerMessagePO();
		customerMessagePO.setCustomerId(orderPO.getCustomerId());
		customerMessagePO.setContent("您于" + MyDateUtil.dateToString(new Date())
				+ "支付了租赁费用:" + orderPO.getTotalCost() + "元!");
		customerMessagePO.setType(Types.付款通知.name());
		return customerMessagePO;
	}

	/**
	 * 生成充值付费消息通知
	 * 
	 * @param accountRecordPO
	 * @return
	 */
	public static CustomerMessagePO createBalanceChargeMsg(
			AccountRecordPO accountRecordPO) {
		CustomerMessagePO msg = new CustomerMessagePO();
		msg.setCustomerId(accountRecordPO.getCustomerId());
		msg.setContent("您于" + MyDateUtil.getCurrentTime() + "成功充值了"
				+ accountRecordPO.getAddBalance() + "元！");
		msg.setType(Types.账户充值.name());
		return msg;
	}

	/**
	 * 增加充值消息(充电卡充值，脱机)
	 * 
	 * @param customerId2
	 * @param cardID
	 * @param addBalance
	 * @return
	 */
	public static CustomerMessagePO createStakeCardChargeMsg(
			Integer customerId, String cardID, BigDecimal addBalance, BigDecimal balance) {
		CustomerMessagePO msg = new CustomerMessagePO();
		msg.setCustomerId(customerId);
		msg.setContent("您于" + MyDateUtil.getCurrentTime() + "成功充值了"
				+ addBalance + "元！当前充电卡 [" + cardID + "] 余额为：" + balance);
		msg.setType(Types.账户充值.name());
		return msg;
	}

}

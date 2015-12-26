package com.qx.eakay.service.stake;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qx.common.base.BaseExport;
import com.qx.common.base.PageResults;
import com.qx.eakay.dao.customer.AccountDao;
import com.qx.eakay.dao.customer.AccountRecordDao;
import com.qx.eakay.dao.customer.CustomerMessageDao;
import com.qx.eakay.dao.stake.StakeRechargeCostDao;
import com.qx.eakay.export.stake.StakeChargeCostExport;
import com.qx.eakay.model.customer.AccountPO;
import com.qx.eakay.model.customer.AccountRecordPO;
import com.qx.eakay.model.customer.CustomerMessagePO;
import com.qx.eakay.model.customer.AccountRecordPO.AccountRecordType;
import com.qx.eakay.model.stake.StakeRechargeCostPO;
import com.qx.eakay.model.stake.StakeRechargeCostPO.OrderStatus;
import com.qx.eakay.service.BaseService;
import com.qx.eakay.util.MSG;

/**
 * 
 * @author jixf
 * @date 2015年7月16日
 */
@Service
public class StakeRechargeCostService extends BaseService {

	@Autowired
	private StakeRechargeCostDao rechargeCostDao;
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private AccountRecordDao accountRecordDao;
	@Autowired
	private CustomerMessageDao customerMessageDao;

	/**
	 * 分页查找充电设备
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findRechargeCostPage(JSONObject jsonObject,
			int pageSize, int iDisplayStart) {
		return rechargeCostDao.findRechargeCostPage(jsonObject, pageSize,
				iDisplayStart);
	}

	/**
	 * 导出充电设备列表
	 * 
	 * @param response
	 * @param jsonObject
	 */
	public void excelExportRechargeCost(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export = new StakeChargeCostExport();
		List<Map<String, Object>> list = findRechargeCost(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 条件搜索充电设备
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findRechargeCost(JSONObject jsonObject) {
		return rechargeCostDao.findRechargeCost(jsonObject);
	}

	/**
	 * 查找充电订单信息
	 * 
	 * @param id
	 * @return
	 */
	public StakeRechargeCostPO getStakeRechargeCost(Integer id) {
		return rechargeCostDao.get(id);
	}

	/**
	 * 结算充电订单（刷卡充电方式）
	 * 
	 * @param id
	 * @param transactUser
	 * @return
	 */
	@Transactional
	public MSG chargeOrder(Integer id, String transactUser) {
		MSG msg = new MSG();
		// 订单信息
		StakeRechargeCostPO costPO = rechargeCostDao.get(id);
		if ("已付费".equals(costPO.getOrderStatus())) {
			return new MSG("订单已经完成收费，不能重复收费！", 1001);
		}

		deductCustomerBalance(costPO, transactUser);
		// 修改订单为已付费
		msg.setSuccess(rechargeCostDao.updateStatus(OrderStatus.已付费, id));
		return msg;
	}

	/**
	 * 扣除用户账户余额
	 * 
	 * @param orderPO
	 */
	private void deductCustomerBalance(StakeRechargeCostPO costPO,
			String transactUser) {
		BigDecimal addBalance = new BigDecimal(0).subtract(costPO.getCost());
		Integer accountId = costPO.getCustomerPO().getAccountPO().getId();
		Integer siteId = Integer.parseInt(costPO.getSite_code());

		AccountPO accountPO = accountDao.get(accountId);

		// 创建用户账户付费记录
		AccountRecordPO recordPO = AccountRecordPO.createAddBalanceRecord(
				accountPO, addBalance, AccountRecordType.充电消费, null,
				transactUser, siteId, "");

		accountRecordDao.create(recordPO);
		// 修改账户余额
		accountDao.addBalance(recordPO.getAddBalance(), accountPO.getId());
	}

	/**
	 * 查找超时订单
	 * 
	 * @return
	 */
	public List<Map<String, Object>> findOutTimeChargeOrder() {
		return rechargeCostDao.findOutTimeChargeOrder();
	}

	/**
	 * 取消超时（24h）的订单
	 * 
	 * @param orders
	 */
	@Transactional
	public void cancelChargeOrders(List<Map<String, Object>> orders) {
		if (orders == null || orders.isEmpty()) {
			return;
		}

		List<Integer> orderIds = new ArrayList<>();
		List<CustomerMessagePO> messagers = new ArrayList<>();
		for (Map<String, Object> order : orders) {
			Integer orderId = Integer.parseInt(order.get("id") + "");
			Integer customerId = Integer.parseInt(order.get("customerId") + "");

			orderIds.add(orderId);

			CustomerMessagePO msg = CustomerMessagePO.createChargeOrderCancelMsg(
					orderId, customerId);
			messagers.add(msg);
		}

		// 批量修改订单状态
		rechargeCostDao.batchUpdateStaus(OrderStatus.已取消, orderIds);
		logger.info("批量修改订单状态: orderIds: " + orderIds);
		
		// 批量插入客户消息
		customerMessageDao.batchCreateMsg(messagers);
	}
}

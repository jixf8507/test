package com.qx.eakay.service.customer;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qx.common.base.BaseExport;
import com.qx.common.base.PageResults;
import com.qx.eakay.dao.customer.AccountRecordDao;
import com.qx.eakay.dao.customer.CustomerUnitDao;
import com.qx.eakay.dao.merchant.MerchantAccountRecordDao;
import com.qx.eakay.dao.merchant.MerchantDao;
import com.qx.eakay.dao.order.OrderDao;
import com.qx.eakay.export.customer.CustomerExport;
import com.qx.eakay.export.customer.UnitCustomerAccountRecordExport;
import com.qx.eakay.export.customer.UnitCustomerExport;
import com.qx.eakay.model.customer.AccountRecordPO;
import com.qx.eakay.model.customer.AccountRecordPO.AccountRecordType;
import com.qx.eakay.model.customer.UnitCustomerPO;
import com.qx.eakay.model.merchant.MerchantAccountRecordPO;
import com.qx.eakay.model.merchant.MerchantPO;
import com.qx.eakay.service.BaseService;
import com.qx.eakay.util.MSG;

/**
 * 单位会员管理
 * 
 * @author sdf
 * @date 2015年10月6日
 */
@Service
public class CustomerUnitService extends BaseService {

	@Autowired
	private CustomerUnitDao unitDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private AccountRecordDao accountRecordDao;
	@Autowired
	private MerchantDao merchantDao;
	@Autowired
	private MerchantAccountRecordDao merchantAccountRecordDao;

	/**
	 * 保存新增单位会员
	 * 
	 * @param unitCustomerPO
	 * @return
	 */
	public boolean createCustomer(UnitCustomerPO unitCustomerPO) {
		Integer unitCustomerId = unitDao.create(unitCustomerPO);
		return unitCustomerId > 0;
	}

	/**
	 * 分页查询单位会员信息列表
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findUnitCustomerPage(JSONObject jsonObject,
			int pageSize, int iDisplayStart) {
		return unitDao
				.findUnitCustomerPage(jsonObject, pageSize, iDisplayStart);
	}

	/**
	 * 条件搜索单位会员信息
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public List<Map<String, Object>> findUnitCustomers(JSONObject jsonObject) {
		return unitDao.findUnitCustomers(jsonObject);
	}

	/**
	 * 导出单位会员信息
	 * 
	 * @param response
	 * @param jsonObject
	 */
	public void excelExportUnitCustomer(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export = new UnitCustomerExport();
		List<Map<String, Object>> list = findUnitCustomers(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 分页查询单位会员交易信息
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findUnitCustomerTradePage(JSONObject jsonObject,
			int pageSize, int iDisplayStart) {
		return unitDao.findUnitCustomerTradePage(jsonObject, pageSize,
				iDisplayStart);
	}

	/**
	 * 条件搜索单位会员交易信息
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public List<Map<String, Object>> findUnitCustomersTrade(
			JSONObject jsonObject) {
		return unitDao.findUnitCustomersTrade(jsonObject);
	}

	/**
	 * 导出下载单位会员交易信息
	 * 
	 * @param response
	 * @param jsonObject
	 */
	public void excelExportUnitCustomerTrade(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export = new UnitCustomerAccountRecordExport();
		List<Map<String, Object>> list = findUnitCustomersTrade(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 修改单位会员信息
	 * 
	 * @param unitCustomerPO
	 * @return
	 */
	public boolean updateCustomer(UnitCustomerPO unitCustomerPO) {
		return unitDao.update(unitCustomerPO);
	}

	/**
	 * 根据用户ID查询单位会员
	 * 
	 * @param id
	 * @return
	 */
	public UnitCustomerPO getUnitCustomer(Integer id) {
		return unitDao.getUnitCustomer(id);
	}

	/**
	 * 检测手机号码是否已存在
	 * 
	 * @param phone
	 * @param id
	 * @return
	 */
	public boolean checkPhone(String phone, Integer id) {
		UnitCustomerPO unitCustomerPO = unitDao.getByPhone(phone);
		if (unitCustomerPO == null) {
			return true;
		}
		if (unitCustomerPO.getId().equals(id)) {
			return true;
		}
		if (unitCustomerPO.getStatus().equals("注销")) {
			return true;
		}
		return false;
	}

	/**
	 * 检测企业名称是否已存在
	 * 
	 * @param cardNO
	 * @param id
	 * @return
	 */
	public boolean checkName(String name, Integer id) {
		UnitCustomerPO unitCustomerPO = unitDao.getByName(name);
		if (unitCustomerPO == null) {
			return true;
		}
		if (unitCustomerPO.getId().equals(id)) {
			return true;
		}
		if (unitCustomerPO.getStatus().equals("注销")) {
			return true;
		}
		return false;
	}

	/**
	 * 更改单位会员状态
	 * 
	 * @param string
	 * 
	 * @param accountPO
	 * @return
	 */
	public MSG changeStatus(String status, String deleteUser, Integer id) {
		MSG msg = new MSG();
		// if (!"正常".equals(status)&&orderDao.findUnitCuserUseOrders(id) > 0) {
		// msg.setSuccess(false);
		// msg.setInfo("该客户还有租赁车辆业务未结束，请先结束！");
		// return msg;
		// }
		UnitCustomerPO unitCustomerPO = unitDao.getUnitCustomer(id);
		unitCustomerPO.setStatus(status);
		unitCustomerPO.setDeleteUser(deleteUser);
		unitCustomerPO.setDescribe("");
		unitCustomerPO.setOther("");
		unitCustomerPO.setSubStatus("正常");
		msg.setSuccess(unitDao.changeStatus(unitCustomerPO));
		return msg;
	}

	/**
	 * 更改单位会员状态（注销，冻结）
	 * 
	 * @param accountPO
	 * @return
	 */
	public MSG changeStatusSubmit(UnitCustomerPO unitCustomerPO) {
		MSG msg = new MSG();
		Integer id = unitCustomerPO.getId();
		if (orderDao.findUnitCuserUseOrders(id) > 0) {
			msg.setSuccess(false);
			msg.setInfo("该客户还有租赁车辆业务未结束，请先结束！");
			return msg;
		}
		unitCustomerPO.setSubStatus("正常");
		msg.setSuccess(unitDao.changeStatus(unitCustomerPO));
		return msg;
	}

	/**
	 * 单位会员注销卡退款
	 * 
	 * @param recordPO
	 * @return
	 */
	@Transactional
	public boolean refundUnitCustomerAccount(AccountRecordPO recordPO) {
		// 得到单位会员
		UnitCustomerPO unitCustomerPO = unitDao.getUnitCustomer(recordPO
				.getId());

		// 创建的帐户保证金退还交易明细
		AccountRecordPO clearAssureRecord = AccountRecordPO
				.createClearUnitAssureRecord(unitCustomerPO,
						AccountRecordType.保证金退款, "保证金退款",
						recordPO.getTransactUser());
		accountRecordDao.create(clearAssureRecord);
		// 清空账户中的保证金余额
		unitDao.addUnitAssure(
				new BigDecimal(0).subtract(unitCustomerPO.getMoneyOfassure()),
				unitCustomerPO.getId());

		// 创建账户余额退款明细记录
		AccountRecordPO clearBalanceRecord = AccountRecordPO
				.createClearUnitBalanceRecord(unitCustomerPO,
						AccountRecordType.余额退款, "余额退款",
						recordPO.getTransactUser());
		accountRecordDao.create(clearBalanceRecord);

		// 清空账户余额
		unitDao.addUnitBalance(
				new BigDecimal(0).subtract(unitCustomerPO.getBalance()),
				unitCustomerPO.getId());

		// 修改商家账户的余额
		this.updateMerchantAccountBalance(unitCustomerPO.getBalance(),
				AccountRecordType.余额退款, recordPO.getTransactUser(), -1,
				unitCustomerPO.getMerchantId());
		// 更改账户的退款状态
		// return unitDao.changeStatus(AccountStatus.注销, "已退款",
		// unitCustomerPO.getId());
		unitCustomerPO.setStatus("注销");
		unitCustomerPO.setSubStatus("已退款");

		return unitDao.changeStatus(unitCustomerPO);
	}

	/**
	 * 更改商家账户的余额
	 * 
	 * @param accountRecordPO
	 * @param accountPO
	 */
	private void updateMerchantAccountBalance(BigDecimal addBalance,
			AccountRecordType type, String transactUser, Integer siteId,
			Integer merchantId) {
		// 查询得到商家账户
		MerchantPO merchantPO = merchantDao.get(merchantId);
		// 创建商家账户交易记录记录
		MerchantAccountRecordPO accountRecordPO = MerchantAccountRecordPO
				.createAddBalanceRecord(merchantPO, addBalance, type,
						transactUser, siteId);
		merchantAccountRecordDao.create(accountRecordPO);
		// 修改商家账户余额
		merchantDao.addBalance(addBalance, new BigDecimal(0),
				merchantPO.getId());
	}

	/**
	 * 保存单位会员批量添加用户
	 * 
	 * @param customerId
	 * @param id
	 * @return
	 */
	@Transactional
	public boolean saveCustomerAdd(List<Integer> customerId, Integer id) {
		// 删除原来关系
//		unitDao.delRelation(id);
		if (customerId.get(0) != 0) {
			// 保存关系
			unitDao.saveCustomerAdd(customerId, id);
		}
		return true;
	}

	/**
	 * 分页查询单位的用户信息
	 * 
	 * @param jsonObject
	 * @param getiDisplayLength
	 * @param getiDisplayStart
	 * @return
	 */
	public PageResults findUnitUnitCustomerPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return unitDao.findUnitUnitCustomerPage(jsonObject, pageSize, iDisplayStart);
	}

	/**
	 * 导出下载单位的用户信息
	 * 
	 * @param response
	 * @param jsonObject
	 */
	public void excelExportUnitUnitCustomer(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export = new CustomerExport();
		List<Map<String, Object>> list = findUnitUnitCustomers(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 条件查询单位的用户信息
	 * 
	 * @param jsonObject
	 * @return
	 */
	private List<Map<String, Object>> findUnitUnitCustomers(
			JSONObject jsonObject) {
		return unitDao.findUnitUnitCustomers(jsonObject);
	}

	/**
	 * 移除商家客户
	 * 
	 * @param enterpriseId
	 * @param id
	 * @return
	 */
	public boolean delCustomer(Integer enterpriseId, Integer id) {
		return unitDao.delCustomer(enterpriseId,id);
	}


}

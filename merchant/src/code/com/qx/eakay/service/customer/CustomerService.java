package com.qx.eakay.service.customer;

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
import com.qx.eakay.dao.customer.CustomerDao;
import com.qx.eakay.export.customer.CustomerCancelExport;
import com.qx.eakay.export.customer.CustomerExport;
import com.qx.eakay.export.customer.CustomerFreezeExport;
import com.qx.eakay.model.customer.AccountPO;
import com.qx.eakay.model.customer.CustomerPO;
import com.qx.eakay.service.BaseService;

/**
 * 
 * @author jixf
 * @date 2015年7月8日
 */
@Service
public class CustomerService extends BaseService {

	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private AccountDao accountDao;

	/**
	 * 分页查询用户信息列表
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findCustomerPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return customerDao
				.findCustomerPage(jsonObject, pageSize, iDisplayStart);
	}

	/**
	 * 条件搜索用户信息
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public List<Map<String, Object>> findCustomers(JSONObject jsonObject) {
		return customerDao.findCustomers(jsonObject);
	}

	/**
	 * 导出违章记录信息
	 * 
	 * @param response
	 * @param jsonObject
	 */
	public void excelExportCustomer(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export;
		String status = jsonObject.get("status") + "";
		Object subStatus = jsonObject.get("subStatus");
		if ("冻结".equals(status)) {
			export = new CustomerFreezeExport();
		} else if ("正常".equals(status) && subStatus == null) {
			export = new CustomerExport();
		} else {
			export = new CustomerCancelExport();
		}
		List<Map<String, Object>> list = findCustomers(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 保存新增用户信息
	 * 
	 * @param customerPO
	 * @return
	 */
	@Transactional
	public boolean createCustomer(CustomerPO customerPO) {
		// 创建用户
		Integer customerId = customerDao.create(customerPO);
		// 创建用户帐号
		AccountPO accountPO = customerPO.getAccountPO();
		accountPO.setCustomerId(customerId);
		if (accountPO.getCardNO() == null) {
			accountPO.setCardNO(customerPO.getIdCard());
		}
		return this.accountDao.create(accountPO) > 0;
	}

	/**
	 * 根据用户ID查询用户
	 * 
	 * @param id
	 * @return
	 */
	public CustomerPO getCustomer(Integer accountId, Integer merchantId) {

		// 查询用户账户
		AccountPO account = accountDao.get(accountId);
		// 查询用户
		CustomerPO customerPO = customerDao.get(account.getCustomerId());
		customerPO.setAccountPO(account);
		return customerPO;
	}

	/**
	 * 修改用户信息
	 * 
	 * @param customerPO
	 * @return
	 */
	@Transactional
	public boolean updateCustomer(CustomerPO customerPO) {
		// 修改用户信息
		customerDao.update(customerPO);
		// 修改帐号信息

		if (customerPO.getAccountPO().getCardNO() == null) {
			customerPO.getAccountPO().setCardNO(customerPO.getIdCard());
		}
		return accountDao.update(customerPO.getAccountPO());
	}

	/**
	 * 检测手机号码是否已存在
	 * 
	 * @param phone
	 * @param id
	 * @return
	 */
	public boolean checkPhone(String phone, Integer id) {
		CustomerPO customerPO = customerDao.getByPhone(phone);
		if (customerPO == null) {
			return true;
		}
		if (customerPO.getId().equals(id)) {
			return true;
		}
		return false;
	}

	/**
	 * 检测租赁卡号是否已存在
	 * 
	 * @param cardNO
	 * @param id
	 * @return
	 */
	public boolean checkCardNO(String cardNO, Integer merchantId, Integer id) {
		AccountPO accountPO = accountDao.getByCardNO(cardNO, merchantId);
		if (accountPO == null) {
			return true;
		}
		if (accountPO.getId().equals(id)) {
			return true;
		}
		return false;
	}

	/**
	 * 检测身份证是否存在
	 * 
	 * @param idCard
	 * @param merchantId
	 * @param id
	 * @return
	 */
	public boolean checkIdCard(String idCard, Integer id) {
		CustomerPO customerPO = customerDao.getByIdCard(idCard);
		if (customerPO == null) {
			return true;
		}
		if (customerPO.getId().equals(id)) {
			return true;
		}
		return false;
	}

	/**
	 * 查询客户信息
	 * 
	 * @param phone
	 * @param merchantId
	 * @return
	 */
	public CustomerPO getCustomerByPhone(String phone, Integer merchantId) {
		return customerDao.getCustomerAccountByPhone(phone, merchantId);
	}

}

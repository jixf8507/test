package com.qx.eakay.service.customer;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qx.common.base.PageResults;
import com.qx.eakay.dao.customer.AccountDao;
import com.qx.eakay.dao.customer.CustomerDao;
import com.qx.eakay.model.customer.AccountPO;
import com.qx.eakay.model.customer.CustomerPO;
import com.qx.eakay.service.BaseService;

/**
 * 
 * @author jixf
 * @date 2015年7月9日
 */
@Service
public class CustomerPotentialService extends BaseService {

	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private AccountDao accountDao;

	/**
	 * 分页查找潜在用户信息
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findCustomerPotentialPage(JSONObject jsonObject,
			int pageSize, int iDisplayStart) {
		return customerDao.findCustomerPotentialPage(jsonObject, pageSize,
				iDisplayStart);
	}

	/**
	 * 获取用户信息
	 * 
	 * @param id
	 * @return
	 */
	public CustomerPO getCustomer(Integer id) {
		return customerDao.get(id);
	}

	/**
	 * 创建用户在商家中的账户
	 * 
	 * @param accountPO
	 * @return
	 */
	@Transactional
	public boolean createAccount(AccountPO accountPO) {
		accountDao.create(accountPO);
		return customerDao.updateStatus("已通过", accountPO.getCustomerId());
	}

}

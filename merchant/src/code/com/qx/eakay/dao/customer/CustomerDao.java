package com.qx.eakay.dao.customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.qx.common.base.PageResults;
import com.qx.common.dao.BaseDao;
import com.qx.common.sql.JSONSqlMapping;
import com.qx.eakay.db.creator.CustomerCreator;
import com.qx.eakay.db.extractor.CustomerExtractor;
import com.qx.eakay.model.customer.AccountPO;
import com.qx.eakay.model.customer.CustomerPO;

/**
 * 
 * @author jixf
 * @date 2015年7月8日
 */
@Repository
public class CustomerDao extends BaseDao {

	@Resource(name = "customer_select_sql")
	private JSONSqlMapping customerSelectSQL;

	/**
	 * 分页查询客户信息列表
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findCustomerPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return this.findPageByJSONSqlMapping(customerSelectSQL, jsonObject,
				pageSize, iDisplayStart);
	}

	/**
	 * 条件搜索用户信息列表
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findCustomers(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(customerSelectSQL, jsonObject);
	}

	@Resource(name = "customer_potential_select_sql")
	private JSONSqlMapping customerPotentialSelectSQL;

	/**
	 * 分页查询潜在用户信息列表
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findCustomerPotentialPage(JSONObject jsonObject,
			int pageSize, int iDisplayStart) {
		return this.findPageByJSONSqlMapping(customerPotentialSelectSQL,
				jsonObject, pageSize, iDisplayStart);
	}

	@Resource(name = "customer_statistics_site_sql")
	private JSONSqlMapping customerStatisticsSelectSQL;

	/**
	 * 租赁点开户统计
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> statisticsSiteCount(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(customerStatisticsSelectSQL,
				jsonObject);
	}

	@Resource(name = "customer_statistics_site_cancle_sql")
	private JSONSqlMapping customerStatisticsCancleSelectSQL;

	/**
	 * 租赁点注销统计
	 */
	public List<Map<String, Object>> statisticsSiteCountByCancel(
			JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(customerStatisticsCancleSelectSQL,
				jsonObject);
	}

	@Resource(name = "customer_statistics_day_sql")
	private JSONSqlMapping statisticsDaySQL;

	/**
	 * 每日办卡统计
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> statisticsDay(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(statisticsDaySQL, jsonObject);
	}

	/**
	 * 新增保存用户
	 * 
	 * @param customerPO
	 * @return
	 */
	public Integer create(CustomerPO customerPO) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.getJdbcTemplate().update(
				new CustomerCreator(INSERT_SQL, customerPO), keyHolder);
		System.out.println(keyHolder.getKey().intValue() + "主键");
		return keyHolder.getKey().intValue();
	}

	/**
	 * 获取用户信息
	 * 
	 * @param id
	 * @return
	 */
	public CustomerPO get(Integer id) {
		return this.getJdbcTemplate().query(GET_SQL, new Object[] { id },
				new CustomerExtractor());
	}

	/**
	 * 根据手机号码查找用户
	 * 
	 * @param phone
	 * @return
	 */
	public CustomerPO getByPhone(String phone) {
		return this.getJdbcTemplate().query(GET_BY_PHONE,
				new Object[] { phone }, new CustomerExtractor());
	}

	/**
	 * 根据身份证查找用户
	 * 
	 * @param idCard
	 * @return
	 */
	public CustomerPO getByIdCard(String idCard) {
		return this.getJdbcTemplate().query(GET_BY_IDCARD,
				new Object[] { idCard }, new CustomerExtractor());
	}

	/**
	 * 修改用户信息
	 * 
	 * @param customerPO
	 * @return
	 */
	public boolean update(CustomerPO customerPO) {
		Integer count = this.getJdbcTemplate().update(UPDATE_SQL,
				customerPO.getIdCard(), customerPO.getName(),
				customerPO.getPhone(), customerPO.getSex(),
				customerPO.getAddress(), customerPO.getIdCardImg(),
				customerPO.getDriveCardImg(), customerPO.getVerifyMan(),
				customerPO.getWorkUnit(), customerPO.getId());
		return count > 0;
	}

	/**
	 * 更改用户的状态
	 * 
	 * @param status
	 * @param id
	 * @return
	 */
	public boolean updateStatus(String status, Integer id) {
		Integer count = this.getJdbcTemplate().update(UPDATE_STATUS_SQL,
				status, new Timestamp(System.currentTimeMillis()), id);
		return count > 0;
	}

	/**
	 * 条件搜索用户列表
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findTypes(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(customerSelectSQL, jsonObject);
	}

	// 插入用户SQL
	private static final String INSERT_SQL = "insert into customer (idCard,name,phone,loginPassword,sex,address,moneyOfassure,payPassword,"
			+ "idCardImg,driveCardImg,verifyMan,verifyTime,status,createdTime,workUnit) values (?,?,?,?,?,?,?,?,?,?,?,?,'已通过',?,?)";
	// 根据电话号码查找用户的SQL
	private static final String GET_BY_PHONE = "select * from  customer where phone=?";
	// 根据身份证查找用户的SQL
	private static final String GET_BY_IDCARD = "select * from  customer where idCard=?";
	// 根据ID查找用户的SQL
	private static final String GET_SQL = "select * from  customer where id=?";
	// 修改用户的SQL
	private static final String UPDATE_SQL = "update customer set idCard=?,name=?,phone=?,sex=?,"
			+ "address=?,idCardImg=?,driveCardImg=?,verifyMan=?,workUnit=? where id=?";
	// 根据电话号码查找用户的SQL
	private static final String UPDATE_STATUS_SQL = "update customer set status=?,verifyTime=? where id=?";
	// 获取客户账户信息的SQL
	private static final String GET_CUSTOMER_ACCOUNT = "select c.*,a.id as accountId,a.balance as abalance,a.moneyOfassure as amoneyOfassure,a.status as astatus from customer c "
			+ "inner join account a on (a.customerId=c.id) where a.status != '注销' and c.id=?";
	// 获取客户账户信息的SQL
	private static final String GET_CUSTOMER_ACCOUNT_BY_PHONE = "select c.*,a.id as accountId,a.balance as abalance,a.moneyOfassure as amoneyOfassure,a.status as astatus from customer c "
			+ "inner join account a on (a.customerId=c.id) where a.status != '注销' and c.phone=? and a.merchantId=?";

	/**
	 * 获取客户账户信息
	 * 
	 * @param customerId
	 * @return
	 */
	public CustomerPO getCustomerAccount(Integer customerId) {
		return this.getJdbcTemplate().query(GET_CUSTOMER_ACCOUNT,
				new Object[] { customerId },
				new ResultSetExtractor<CustomerPO>() {
					@Override
					public CustomerPO extractData(ResultSet rs)
							throws SQLException, DataAccessException {
						return createCustomerAccountFromResultSet(rs);
					}
				});
	}

	/**
	 * 获取客户账户信息
	 * 
	 * @param phone
	 * @param merchantId
	 * @return
	 */
	public CustomerPO getCustomerAccountByPhone(String phone, Integer merchantId) {
		return this.getJdbcTemplate().query(GET_CUSTOMER_ACCOUNT_BY_PHONE,
				new Object[] { phone, merchantId },
				new ResultSetExtractor<CustomerPO>() {
					@Override
					public CustomerPO extractData(ResultSet rs)
							throws SQLException, DataAccessException {
						return createCustomerAccountFromResultSet(rs);
					}
				});
	}

	private CustomerPO createCustomerAccountFromResultSet(ResultSet rs)
			throws SQLException {
		CustomerPO customerPO = null;
		if (rs.next()) {
			customerPO = new CustomerPO();
			customerPO.setId(rs.getInt("id"));
			customerPO.setIdCard(rs.getString("idCard"));
			customerPO.setName(rs.getString("name"));
			customerPO.setPhone(rs.getString("phone"));
			customerPO.setSex(rs.getString("sex"));
			customerPO.setAddress(rs.getString("address"));
			customerPO.setStatus(rs.getString("status"));
			// 客户账户信息
			AccountPO accountPO = new AccountPO();
			accountPO.setId(rs.getInt("accountId"));
			accountPO.setBalance(rs.getBigDecimal("abalance"));
			accountPO.setMoneyOfassure(rs.getBigDecimal("aMoneyOfassure"));
			accountPO.setStatus(rs.getString("astatus"));
			customerPO.setAccountPO(accountPO);
		}
		return customerPO;
	}

}

package com.qx.eakay.dao.customer;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.qx.common.base.PageResults;
import com.qx.common.dao.BaseDao;
import com.qx.common.sql.JSONSqlMapping;
import com.qx.eakay.db.creator.UnitCustomerCreator;
import com.qx.eakay.db.extractor.UnitCustomerExtractor;
import com.qx.eakay.model.customer.UnitCustomerPO;

/**
 * 单位会员管理
 * 
 * @author sdf
 * @date 2015年10月6日
 */
@Repository
public class CustomerUnitDao extends BaseDao {

	@Resource(name = "UNIT_CUSTOMER_SELECT_SQL")
	private JSONSqlMapping unitCustomerSelectSQL;
	
	@Resource(name = "UNIT_UNITCUSTOMER_SELECT_SQL")
	private JSONSqlMapping unitUnitCustomerSelectSQL;

	@Resource(name = "UNIT_CUSTOMER_ACCOUNT_SELECT_SQL")
	private JSONSqlMapping unitCustomerAccountSelectSQL;

	/**
	 * 分页查询单位会员列表
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findUnitCustomerPage(JSONObject jsonObject,
			int pageSize, int iDisplayStart) {
		return this.findPageByJSONSqlMapping(unitCustomerSelectSQL, jsonObject,
				pageSize, iDisplayStart);
	}

	/**
	 * 条件搜索用户信息列表
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findUnitCustomers(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(unitCustomerSelectSQL, jsonObject);
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
		return this.findPageByJSONSqlMapping(unitCustomerAccountSelectSQL,
				jsonObject, pageSize, iDisplayStart);
	}

	/**
	 * 条件搜索用户交易信息列表
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findUnitCustomersTrade(
			JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(unitCustomerAccountSelectSQL,
				jsonObject);
	}

	/**
	 * 保存新增单位会员
	 * 
	 * @param unitCustomerPO
	 * @return
	 */
	public Integer create(UnitCustomerPO unitCustomerPO) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.getJdbcTemplate().update(
				new UnitCustomerCreator(INSERT_SQL, unitCustomerPO), keyHolder);
		return keyHolder.getKey().intValue();
	}

	/**
	 * 修改单位会员信息
	 * 
	 * @param unitCustomerPO
	 * @return
	 */
	public boolean update(UnitCustomerPO unitCustomerPO) {
		Integer count = this.getJdbcTemplate().update(UPDATE_SQL,
				unitCustomerPO.getMerchantId(),
				unitCustomerPO.getEnterpriseName(),
				unitCustomerPO.getContactPerson(),
				unitCustomerPO.getContactPhone(), unitCustomerPO.getAddress(),
				unitCustomerPO.getTransactUser(),
				unitCustomerPO.getBankCardNO(), unitCustomerPO.getBankType(),
				unitCustomerPO.getId());
		return count > 0;
	}

	/**
	 * 根据用户ID查询单位会员
	 * 
	 * @param id
	 * @return
	 */
	public UnitCustomerPO getUnitCustomer(Integer id) {
		return this.getJdbcTemplate().query(GET_SQL, new Object[] { id },
				new UnitCustomerExtractor());
	}

	/**
	 * 根据手机号码查找用户
	 * 
	 * @param phone
	 * @return
	 */
	public UnitCustomerPO getByPhone(String phone) {
		return this.getJdbcTemplate().query(GET_BY_PHONE,
				new Object[] { phone }, new UnitCustomerExtractor());
	}

	/**
	 * 根据企业名称查找用户
	 * 
	 * @param name
	 * @return
	 */
	public UnitCustomerPO getByName(String name) {
		return this.getJdbcTemplate().query(GET_BY_NAME, new Object[] { name },
				new UnitCustomerExtractor());
	}

	/**
	 * 更改单位会员账户保证金
	 * 
	 * @param addBalance
	 * @param customerId
	 * @return
	 */
	public boolean addUnitAssure(BigDecimal addCost, Integer id) {
		return this.getJdbcTemplate().update(ADD_ASSURE_SQL, addCost, id) > 0;
	}

	/**
	 * 更改单位会员账户余额
	 * 
	 * @param addBalance
	 * @param customerId
	 * @return
	 */
	public boolean addUnitBalance(BigDecimal addCost, Integer id) {
		return this.getJdbcTemplate().update(ADD_BALANCE_SQL, addCost, id) > 0;
	}

	/**
	 * 更改单位会员状态
	 * 
	 * @param status
	 * @param id
	 * @return
	 */
	public boolean changeStatus(UnitCustomerPO unitCustomerPO) {
		return this.getJdbcTemplate().update(UPDATE_STATUS_SQL,
				unitCustomerPO.getStatus(), unitCustomerPO.getSubStatus(),
				unitCustomerPO.getDescribe() + unitCustomerPO.getOther(),
				unitCustomerPO.getDeleteUser(), unitCustomerPO.getId()) > 0;
	}

	/**
	 * 保存单位会员批量添加用户
	 * 
	 * @param customerId
	 * @param id
	 * @return
	 */
	public boolean saveCustomerAdd(final List<Integer> customerId,
			final Integer id) {

		int[] ids = this.getJdbcTemplate().batchUpdate(
				INSERT_ENTERPRISE_CUSTOMER_RELATION_SQL,
				new BatchPreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						ps.setInt(1, id);
						ps.setInt(2, customerId.get(i));
					}

					@Override
					public int getBatchSize() {
						return customerId.size();
					}
				});
		return ids.length == customerId.size();

	}

	// 删除单位会员与用户关系SQL
	private static final String DELETE_ENTERPRISE_CUSTOMER_RELATION_SQL = "delete from enterprise_customer_relation where enterpriseId=?";
	// 保存单位会员添加用户SQL
	private static final String INSERT_ENTERPRISE_CUSTOMER_RELATION_SQL = "insert into enterprise_customer_relation (enterpriseId,customerId) values (?,?)";
	// 插入单位会员SQL
	private static final String INSERT_SQL = "insert into merchant_enterprise_customer (merchantId,enterpriseName,"
			+ "contactPerson,contactPhone,address,balance,moneyOfassure,status,transactUser,createdTime,bankCardNO,bankType)"
			+ " values (?,?,?,?,?,0,0,'正常',?,?,?,?)";
	// 修改单位会员的SQL
	private static final String UPDATE_SQL = "update merchant_enterprise_customer set merchantId=?,enterpriseName=?,contactPerson=?,"
			+ "contactPhone=?,address=?,transactUser=?,bankCardNO=?,bankType=? where id=?";
	// 根据ID查找单位会员的SQL
	private static final String GET_SQL = "select * from  merchant_enterprise_customer where id=?";
	// 根据电话号码查找单位会员的SQL
	private static final String GET_BY_PHONE = "select * from merchant_enterprise_customer where contactPhone=?";
	// 根据企业名称查找单位会员的SQL
	private static final String GET_BY_NAME = "select * from merchant_enterprise_customer where enterpriseName=?";
	// 更改单位会员账户保证金的SQL
	private static final String ADD_ASSURE_SQL = "update merchant_enterprise_customer set moneyOfassure = moneyOfassure + ? where id=?";
	// 更改单位会员账户余额的SQL
	private static final String ADD_BALANCE_SQL = "update merchant_enterprise_customer set balance = balance + ? where id=?";
	// 更改单位会员状态的SQL
	private static final String UPDATE_STATUS_SQL = "update merchant_enterprise_customer set status=?,subStatus = ?,"
			+ "`describe`=?,deletedTime=now(),deleteUser=? where id=?";
	// 移除商家客户的SQL
	private static final String DELETE_CUSTOMER_SQL = "delete from enterprise_customer_relation where enterpriseId=? and customerId=? ";

	/**
	 * 删除原来单位会员与用户关系
	 * 
	 * @param id
	 */
	public boolean delRelation(Integer id) {
		return this.getJdbcTemplate().update(
				DELETE_ENTERPRISE_CUSTOMER_RELATION_SQL, id) > 0;
	}

	/**
	 * 分页查询单位的用户信息
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findUnitUnitCustomerPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return this.findPageByJSONSqlMapping(unitUnitCustomerSelectSQL, jsonObject,
				pageSize, iDisplayStart);
	}

	/**
	 * 条件查询单位的用户信息
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findUnitUnitCustomers(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(unitUnitCustomerSelectSQL,jsonObject);
	}

	/**
	 * 移除商家客户
	 * 
	 * @param enterpriseId
	 * @param id
	 * @return
	 */
	public boolean delCustomer(Integer enterpriseId, Integer id) {
		return this.getJdbcTemplate().update(DELETE_CUSTOMER_SQL,enterpriseId,id) > 0;
	}



}

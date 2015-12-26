package com.qx.eakay.dao.customer;

import java.sql.ResultSet;
import java.sql.SQLException;
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
import com.qx.eakay.db.creator.AccountRecordCreator;
import com.qx.eakay.model.customer.AccountRecordPO;

/**
 * 
 * @author jixf
 * @date 2015年7月9日
 */
@Repository
public class AccountRecordDao extends BaseDao {

	@Resource(name = "customer_accountRecord_select_sql")
	private JSONSqlMapping accountRecordSQL;

	/**
	 * 分页查询用户帐号交易明细
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findAccountRecordPage(JSONObject jsonObject,
			int pageSize, int iDisplayStart) {
		return this.findPageByJSONSqlMapping(accountRecordSQL, jsonObject,
				pageSize, iDisplayStart);
	}

	/**
	 * 条件搜索用户帐户交易明细
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findAccountRecord(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(accountRecordSQL, jsonObject);
	}
	
	
	
	
	
	@Resource(name = "customer_accountCancel_select_sql")
	private JSONSqlMapping CountAccountCancelSQL;
	/**
	 * @author xuwei
	 * 条件查找用户注销数量，数据库中account表中的所有状态为注销的用户：
	 * 按照每天的时间分组查找当天用户的注册，注销数量
	 */
	public List<Map<String, Object>> getCountByCancel(JSONObject jsonObject){
		return this.findListByJSONSqlMapping(CountAccountCancelSQL, jsonObject);
	}
	
	
	@Resource(name = "customer_accountRegiste_select_sql")
	private JSONSqlMapping CountAccountRegisteSQL;
	/**
	 * @author xuwei
	 * 条件查找用户开户数量，包括在数据库中存在的account表中的所有数据：
	 * 按照每天的时间分组查找当天用户的注册，注销，冻结等所有状态的用户数量
	 */
	public List<Map<String, Object>> getCountByRegiste(JSONObject jsonObject){
		return this.findListByJSONSqlMapping(CountAccountRegisteSQL, jsonObject);		
	}
	
	
	
	
	

	@Resource(name = "customer_refundrecord_sql")
	private JSONSqlMapping refundRecordSQL;

	/**
	 * 分页查询用户帐号退款明细
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findRefudnRecordPage(JSONObject jsonObject,
			int pageSize, int iDisplayStart) {
		return this.findPageByJSONSqlMapping(refundRecordSQL, jsonObject,
				pageSize, iDisplayStart);
	}

	/**
	 * 条件搜索用户帐户退款明细
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findRefudnRecord(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(refundRecordSQL, jsonObject);
	}

	/**
	 * 新增用户帐号交易记录
	 * 
	 * @param accountRecordPO
	 *            账户交易记录
	 * @return
	 */
	public Integer create(AccountRecordPO accountRecordPO) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.getJdbcTemplate().update(
				new AccountRecordCreator(INSERT_SQL, accountRecordPO),
				keyHolder);
		System.out.println(keyHolder.getKey().intValue() + "主键");
		return keyHolder.getKey().intValue();
	}

	// 插入用户SQL
	private static final String INSERT_SQL = "insert into account_record(customerId,oldBalance,addBalance,newBalance,transactUser,createdTime,type,accountId,siteId,`describe`,customerTable,ticket,accountTable) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
	// 根据ID查找交易记录的SQL
	private static final String GET_ACCOUNT_RECORD_SQL = "select r.id,r.ticket,r.checkUser,r.checkDescribe,r.status,r.customerId,r.addBalance,r.transactUser,r.createdTime,r.`describe` from account_record r where r.id=? ";
	// 核对时修改的SQL
	private static final String UPDATE_FOR_CHECK_SQL = "update account_record set checkUser=?,checkDescribe=?,ticket=?,status='已入账' where id=? ";

	/**
	 * 查找交易记录
	 * 
	 * @param id
	 * @return
	 */
	public AccountRecordPO getAccountRecord(Integer id) {
		return this.getJdbcTemplate().query(GET_ACCOUNT_RECORD_SQL,
				new Object[] { id }, new ResultSetExtractor<AccountRecordPO>() {
					@Override
					public AccountRecordPO extractData(ResultSet rs)
							throws SQLException, DataAccessException {
						return createAccountRecordFromResultSet(rs);
					}
				});
	}

	protected AccountRecordPO createAccountRecordFromResultSet(ResultSet rs)
			throws SQLException {
		AccountRecordPO accountRecordPO = null;
		if (rs.next()) {
			accountRecordPO = new AccountRecordPO();
			accountRecordPO.setId(rs.getInt("id"));
			accountRecordPO.setTicket(rs.getString("ticket"));
			accountRecordPO.setCheckUser(rs.getString("checkUser"));
			accountRecordPO.setCheckDescribe(rs.getString("checkDescribe"));
			accountRecordPO.setStatus(rs.getString("status"));
			accountRecordPO.setCustomerId(rs.getInt("CustomerId"));
			accountRecordPO.setAddBalance(rs.getBigDecimal("addBalance"));
			accountRecordPO.setTransactUser(rs.getString("transactUser"));
			accountRecordPO.setCreatedTime(rs.getTimestamp("createdTime"));
			accountRecordPO.setDescribe(rs.getString("describe"));
		}
		return accountRecordPO;
	}

	/**
	 * 保存核对充值收费信息
	 * 
	 * @param accountRecordPO
	 * @return
	 */
	public boolean checkForCharge(AccountRecordPO accountRecordPO) {
		int count = this.getJdbcTemplate().update(UPDATE_FOR_CHECK_SQL,
				accountRecordPO.getCheckUser(),
				accountRecordPO.getCheckDescribe(),
				accountRecordPO.getTicket(), accountRecordPO.getId());
		return count > 0;
	}

}

package com.qx.eakay.dao.customer;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.qx.common.dao.BaseDao;
import com.qx.eakay.db.creator.AccountCreator;
import com.qx.eakay.db.extractor.AccountExtractor;
import com.qx.eakay.model.customer.AccountPO;
import com.qx.eakay.model.customer.AccountPO.AccountStatus;

@Repository
public class AccountDao extends BaseDao {

	/**
	 * 创建用户账户
	 * 
	 * @param carPO
	 * @return
	 */
	public int create(AccountPO accountPO) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.getJdbcTemplate().update(
				new AccountCreator(INSERT_SQL, accountPO), keyHolder);
		System.out.println(keyHolder.getKey().intValue() + "主键");
		return keyHolder.getKey().intValue();
	}

	/**
	 * 根据ID查找用户帐号
	 * 
	 * @param id
	 * @return
	 */
	public AccountPO get(Integer id) {
		return this.getJdbcTemplate().query(GET_SQL, new Object[] { id },
				new AccountExtractor());
	}

	/**
	 * 根据用户ID查询帐号
	 * 
	 * @param customerId
	 * @return
	 */
	public AccountPO getByCustomerId(Integer customerId, Integer merchantId) {
		return this.getJdbcTemplate()
				.query(GET_BY_CUSTOMERID_SQL,
						new Object[] { customerId, merchantId },
						new AccountExtractor());
	}

	/**
	 * 根据卡号查找用户帐号
	 * 
	 * @param id
	 * @return
	 */
	public AccountPO getByCardNO(String cardNO, Integer merchantId) {
		return this.getJdbcTemplate().query(GET_BY_CARDNO,
				new Object[] { cardNO, merchantId }, new AccountExtractor());
	}

	/**
	 * 修改帐号信息
	 * 
	 * @param accountPO
	 * @return
	 */
	public boolean update(AccountPO accountPO) {
		int count = this.getJdbcTemplate().update(UPDATE_SQL,
				accountPO.getCardNO(), accountPO.getBankCardNO(),
				accountPO.getBankType(), accountPO.getId());
		return count > 0;
	}

	/**
	 * 账户余额充值
	 * 
	 * @param addCost
	 *            增加金额
	 * @param id
	 *            帐号ID
	 * @return
	 */
	public boolean addBalance(BigDecimal addCost, Integer id) {
		int count = this.getJdbcTemplate().update(ADD_BALANCE_SQL, addCost, id);
		return count > 0;
	}
	
	/**
	 * 清空冻结资金
	 */
	public boolean clearFreezeBalance(BigDecimal cutFreezeBalance,Integer id) {
		int count = this.getJdbcTemplate().update(CLEAR_FREEZE_BALANCE_SQL,cutFreezeBalance, id);
		return count > 0;
	}
	
	

	/**
	 * 保证金充值
	 * 
	 * @param addCost
	 *            增加金额
	 * @param id
	 *            帐号id
	 * @return
	 */
	public boolean addAssure(BigDecimal addCost, Integer id) {
		int count = this.getJdbcTemplate().update(ADD_ASSURE_SQL, addCost, id);
		return count > 0;
	}

	/**
	 * 更改账户的状态
	 * 
	 * @param status
	 * @param subStatus
	 * @param id
	 * @return
	 */
	public boolean updateStatus(AccountStatus status, String subStatus,String deletedUser,
			Integer id) {
		int count = this.getJdbcTemplate().update(UPDATE_STATUS_SQL,
				status.name(), subStatus, "",
				new Timestamp(System.currentTimeMillis()),deletedUser, id);
		return count > 0;
	}

	// 插入用户帐号SQL
	private static final String INSERT_SQL = "insert into account (customerId,merchantId,cardNO,status,bankCardNO,bankType,siteId,createdTime,deletedTime,transactUser) values (?,?,?,'"
			+ AccountStatus.正常.name() + "',?,?,?,?,?,?)";
	// 根据电话号码查找用户帐号的SQL
	private static final String GET_BY_CARDNO = "select * from account where cardNO=? and merchantId=? and status='"+AccountStatus.正常.name()+"'";
	// 根据ID查找用户帐号的SQL
	private static final String GET_SQL = "select * from account where id=?";
	// 根据ID查找用户帐号的SQL
	private static final String GET_BY_CUSTOMERID_SQL = "select * from  account where customerId=? and merchantId=? and status!='"+AccountStatus.注销.name()+"'";
	// 修改用户帐号的SQL
	private static final String UPDATE_SQL = "update account set cardNO=?,bankCardNO=?,bankType=? where id=?";
	// 添加帐号余额SQL
	private static final String ADD_BALANCE_SQL = "update account set balance = balance+? where id=?";
	// 清空冻结资金
	private static final String CLEAR_FREEZE_BALANCE_SQL = "update account set freezeBalance =freezeBalance-? where id=?";
	// 添加帐号余额SQL
	private static final String ADD_ASSURE_SQL = "update account set moneyOfassure = moneyOfassure+? where id=?";
	// 修改帐号状态SQL
	private static final String UPDATE_STATUS_SQL = "update account set status = ?,subStatus = ?,`describe` = ?,deletedTime=?,deletedUser=? where id=?";
	// 修改帐号状态SQL
	private static final String UPDATE_SUB_STATUS_SQL = "update account set  subStatus = ?,`describe` = ?,deletedTime=?,deletedUser=?,moneyOfassure =moneyOfassure-?,freezeBalance =freezeBalance+? where id=?";

	/**
	 * 更改状态（注销，冻结）
	 * 
	 * @param accountPO
	 * @return
	 */
	public boolean changeStatusSubmit(AccountPO accountPO) {
		int count = this.getJdbcTemplate().update(UPDATE_STATUS_SQL,
				accountPO.getStatus(), "正常",
				accountPO.getDescribe() + accountPO.getOther(),
				new Timestamp(System.currentTimeMillis()),accountPO.getDeletedUser(), accountPO.getId());
		return count > 0;
	}
	
	/**
	 * 退款
	 * 
	 * @param accountPO
	 * @return
	 */
	public boolean backMoneySubmit(AccountPO accountPO) {
		int count = this.getJdbcTemplate().update(UPDATE_SUB_STATUS_SQL,"申请退款",
				accountPO.getDescribe() + accountPO.getOther(),
				new Timestamp(System.currentTimeMillis()),accountPO.getDeletedUser(),accountPO.getMoneyOfassure(),accountPO.getMoneyOfassure(), accountPO.getId());
		return count > 0;
	}
	
}

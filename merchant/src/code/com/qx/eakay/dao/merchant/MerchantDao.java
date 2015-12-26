package com.qx.eakay.dao.merchant;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.qx.common.base.SqlHelp;
import com.qx.common.dao.BaseDao;
import com.qx.common.sql.JSONSqlMapping;
import com.qx.eakay.db.extractor.MerchantExtractor;
import com.qx.eakay.model.merchant.MerchantPO;

/**
 * 商家数据库操作
 * 
 * @author Administrator
 * 
 */
@Repository
public class MerchantDao extends BaseDao {

	/**
	 * 根据登录邮箱查找商家
	 * 
	 * @param email
	 * @return
	 */
	public MerchantPO findByEmail(String email) {
		return this.getJdbcTemplate().query(GET_BY_EMAIL_SQL,
				new Object[] { email }, new MerchantExtractor());
	}

	/**
	 * 根据id查找商家
	 * 
	 * @param email
	 * @return
	 */
	public MerchantPO get(Integer id) {
		return this.getJdbcTemplate().query(GET_BY_ID_SQL, new Object[] { id },
				new MerchantExtractor());
	}

	@Resource(name = "merchant_moudles_select_sql")
	private JSONSqlMapping merchantMoudelsSelectSQL;

	/**
	 * 查找商家的功能模块列表
	 * 
	 * @return
	 */
	public List<Map<String, Object>> findMerchantMoudles(String systemName) {
		return this.findListBySQL(
				new SqlHelp(merchantMoudelsSelectSQL).getSql(),
				new Object[] { systemName });
	}

	/**
	 * 添加商家帐号的余额
	 * 
	 * @param addCost
	 *            账户余额增加金额
	 * @param addFreezeCost
	 *            帐号冻结资金增加金额
	 * @param id
	 *            商家ID
	 * @return
	 */
	public boolean addBalance(BigDecimal addCost, BigDecimal addFreezeCost,
			Integer id) {
		int count = this.getJdbcTemplate().update(ADD_BALANCE_SQL, addCost,
				addFreezeCost, id);
		return count > 0;
	}

	/**
	 * 修改密码
	 * 
	 * @param password
	 *            新密码
	 * @param id
	 *            商家ID
	 * @return
	 */
	public boolean updatePassword(String password, Integer id) {
		int count = this.getJdbcTemplate().update(UPDATE_PASSWORD_SQL,
				password, id);
		return count > 0;
	}

	/**
	 * 保存商家账户信息
	 * 
	 * @param merchantPO
	 * @return
	 */
	public boolean saveEdit(MerchantPO merchantPO) {
		int count = this.getJdbcTemplate().update(UPDATE_SQL,
				merchantPO.getMerchantName(), merchantPO.getCorporation(),
				merchantPO.getMobilePhone(), merchantPO.getHotTel(),
				merchantPO.getIdCard(), merchantPO.getAlipayAccount(),
				merchantPO.getAlipayAccountName(),
				merchantPO.getBankTypeName(), merchantPO.getAgreeSystemMoney(),
				merchantPO.getRentalMoney(), merchantPO.getLinkUrl(),
				merchantPO.getProvince(), merchantPO.getCity(),
				merchantPO.getId());
		return count > 0;
	}

	// 根据ID查找商家的SQL
	private static final String GET_BY_ID_SQL = "SELECT m.* from merchant m where m.id=?";
	// 修改商家的登录密码SQL
	private static final String UPDATE_PASSWORD_SQL = "update merchant  set loginPassword=? where id=?";
	// 根据登录邮箱查找商家的SQL
	private static final String GET_BY_EMAIL_SQL = "SELECT m.* from merchant m where m.email=?";
	// 修改商家信息的SQL
	private static final String UPDATE_SQL = "update merchant set merchantName=?,corporation=?,mobilePhone=?,hotTel=?,"
			+ "idCard=?,alipayAccount=?,alipayAccountName=?,bankTypeName=? ,agreeSystemMoney=?,rentalMoney=?,linkUrl=?,province=?,city=? where id=?";
	// 增加商家帐号的金额SQL
	private static final String ADD_BALANCE_SQL = "update merchant  set balance=balance+?,freezeBalance=freezeBalance+? where id=?";

}

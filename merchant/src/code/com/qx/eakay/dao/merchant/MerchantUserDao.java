package com.qx.eakay.dao.merchant;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.qx.common.base.PageResults;
import com.qx.common.base.SqlHelp;
import com.qx.common.dao.BaseDao;
import com.qx.common.sql.JSONSqlMapping;
import com.qx.eakay.db.creator.MerchantUserCreator;
import com.qx.eakay.db.extractor.MerchantUserExtractor;
import com.qx.eakay.db.extractor.MerchantUserSignExtractor;
import com.qx.eakay.model.merchant.MerchantUserPO;
import com.qx.eakay.model.merchant.MerchantUserSignPO;

/**
 * 商家用户数据库操作方法
 * 
 * @author Administrator
 * 
 */
@Repository("merchantUserDAO")
public class MerchantUserDao extends BaseDao {

	@Resource(name = "merchant_user_select_sql")
	private JSONSqlMapping userSelectSQL;

	@Resource(name = "merchant_user_sign_select_sql")
	private JSONSqlMapping userSignSelectSQL;

	/**
	 * 分页查询商家工作人员
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findUserPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return this.findPageByJSONSqlMapping(userSelectSQL, jsonObject,
				pageSize, iDisplayStart);
	}

	/**
	 * 条件查询商家工作人员
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findUsers(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(userSelectSQL, jsonObject);
	}

	/**
	 * 根据登录邮箱查找商家用户对象
	 * 
	 * @param email
	 * @return
	 */
	public MerchantUserPO findByEmail(String email) {
		return this.getJdbcTemplate().query(GET_BY_EMAIL_SQL,
				new Object[] { email }, new MerchantUserExtractor());
	}

	/**
	 * 修改商家用户的登录密码
	 * 
	 * @param password
	 * @param id
	 * @return
	 */
	public boolean updatePsd(String password, Integer id) {
		int count = this.getJdbcTemplate().update(PASSWORD_UPDATE_SQL,
				password, id);
		return count > 0;
	}

	@Resource(name = "merchant_user_moudles_select_sql")
	private JSONSqlMapping merchantUserMoudlesSelectSql;

	/**
	 * 查找指定用户ID的功能模块菜单
	 * 
	 * @param userId
	 * @return
	 */
	public List<Map<String, Object>> findMerchantUserMoudles(Integer userId,
			String systemName) {
		return this.findListBySQL(
				new SqlHelp(merchantUserMoudlesSelectSql).getSql(),
				new Object[] { userId, systemName });
	}

	@Resource(name = "merchant_user_moudles_join_sql")
	private JSONSqlMapping merchantUserMoudlesJoinSql;

	/**
	 * 查询商家菜单列表
	 * 
	 * @param userId
	 * @return
	 */
	public List<Map<String, Object>> findMerchantMoudles(Integer userId,
			String systemName) {
		return this.findListBySQL(
				new SqlHelp(merchantUserMoudlesJoinSql).getSql(), new Object[] {
						userId, systemName });
	}

	/**
	 * 根据ID获取商家工作人员
	 * 
	 * @param id
	 * @return
	 */
	public MerchantUserPO get(Integer id) {
		return this.getJdbcTemplate().query(GET_SQL, new Object[] { id },
				new MerchantUserExtractor());
	}

	/**
	 * 新增商家工作人员
	 * 
	 * @param merchantUserPO
	 * @return
	 */
	public Integer create(MerchantUserPO merchantUserPO) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.getJdbcTemplate().update(
				new MerchantUserCreator(INSERT_SQL, merchantUserPO), keyHolder);
		System.out.println(keyHolder.getKey().intValue() + "主键");
		return keyHolder.getKey().intValue();
	}

	/**
	 * 修改商家工作人员信息
	 * 
	 * @param merchantUserPO
	 * @return
	 */
	public boolean update(MerchantUserPO merchantUserPO) {
		int count = this.getJdbcTemplate().update(UPDATE_SQL,
				merchantUserPO.getEmail(), merchantUserPO.getMobilePhone(),
				merchantUserPO.getUserName(), merchantUserPO.getSiteId(),
				merchantUserPO.getRights(), merchantUserPO.getId());
		return count > 0;
	}

	/**
	 * 删除商家工作人员
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(Integer id) {
		int count = this.getJdbcTemplate().update(DELETE_SQL, id);
		return count > 0;
	}

	// 根据登录邮箱查找商家的SQL
	private static final String GET_SQL = "SELECT * from merchant_user where id=?";
	// 根据登录邮箱查找商家的SQL
	private static final String GET_BY_EMAIL_SQL = "SELECT * from merchant_user where email=? and `status`!='删除'";
	// 修改商家用户登录密码的SQL
	private static final String PASSWORD_UPDATE_SQL = "update merchant_user set password=? where id=?";
	// 新增商家工作人员的SQL
	private static final String INSERT_SQL = "insert into merchant_user (email,mobilePhone,userName,siteId,password,rights,merchantId) values (?,?,?,?,?,?,?)";
	// 新增商家工作人员的SQL
	private static final String UPDATE_SQL = "update merchant_user set email=?,mobilePhone=?,userName=?,siteId=?,rights=? where id=?";
	// 根据登录邮箱查找商家的SQL
	private static final String DELETE_SQL = "update  merchant_user set `status`='删除' where id=?";
	// 根据签到id查找签到信息的SQL
	private static final String GET_USER_SIGN_SQL = "SELECT us.*,s.siteName as siteName,ss.siteName as outSiteName from merchant_user_sign us left join site s on (us.siteId=s.id) left join site ss on (us.outSiteId=ss.id) where us.id=?";

	/**
	 * 员工签到列表
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findUserSignPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return this.findPageByJSONSqlMapping(userSignSelectSQL, jsonObject,
				pageSize, iDisplayStart);
	}

	/**
	 * 条件查询商家工作人员
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findUserSign(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(userSignSelectSQL, jsonObject);
	}

	/**
	 * 获取签到信息
	 * 
	 * @param id
	 * @return
	 */
	public MerchantUserSignPO getMerchantUserSign(Integer id) {
		return this.getJdbcTemplate().query(GET_USER_SIGN_SQL,
				new Object[] { id }, new MerchantUserSignExtractor());
	}

	@Resource(name = "merchant_user_get_car_sql")
	private JSONSqlMapping getCarListSQL;

	/**
	 * 取车操作记录
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findGetCarList(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(getCarListSQL, jsonObject);
	}

	@Resource(name = "merchant_user_return_car_sql")
	private JSONSqlMapping returnCarListSql;

	/**
	 * 还车操作记录
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findReturnCarList(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(returnCarListSql, jsonObject);
	}

	@Resource(name = "merchant_user_create_customer_sql")
	private JSONSqlMapping createCustomerListSql;

	/**
	 * 开户操作记录
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findCreateCustomerList(
			JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(createCustomerListSql, jsonObject);
	}

}

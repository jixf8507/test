package com.qx.eakay.dao.merchant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.qx.common.base.PageResults;
import com.qx.common.base.SqlHelp;
import com.qx.common.dao.BaseDao;
import com.qx.common.sql.JSONSqlMapping;
import com.qx.eakay.model.merchant.MerchantFamilyPO;

/**
 * 
 * @author Administrator
 * @date 2015-11-24 11:49:00
 */
@Repository
public class MerchantFamilyDao extends BaseDao {
	/**
	 *条件分页查询sql
	 * 
	 */
	@Resource(name = "merchant_family_select_sql")
	private JSONSqlMapping merchantFamilySelectSQL;
	/**
	 * 条件分页查询
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findMerchantFamilyPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return this.findPageByJSONSqlMapping(merchantFamilySelectSQL, jsonObject, pageSize,
				iDisplayStart);
	}
	
	
	
	
	/**
	 *家庭成员列表条件分页查询sql
	 * 
	 */
	@Resource(name = "merchant_family_member_select_sql")
	private JSONSqlMapping merchantFamilyMemberSelectSQL;
	/**
	 *家庭成员列表分页查询
	 */
	public PageResults findMerchantFamilyMemberPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return this.findPageByJSONSqlMapping(merchantFamilyMemberSelectSQL, jsonObject, pageSize,
				iDisplayStart);
	}
	

	/**
	 * 条件搜索
	 */
	public List<Map<String, Object>> findMerchantFamilyByJSONObject(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(merchantFamilySelectSQL, jsonObject);
	}
	
	/**
	 * 根据id查找merchantFamily的sql
	 */
	@Resource(name = "merchant_family_get_sql")
	private JSONSqlMapping merchantFamilyGetSQL;
	/**
	 * 根据id查找merchantFamily信息
	 * 
	 * @param id
	 * @return
	 */
	public MerchantFamilyPO getMerchantFamilyPO(Integer id) {
		return this.getJdbcTemplate().query( new SqlHelp(merchantFamilyGetSQL).getSql(),
				new Object[] { id }, new ResultSetExtractor<MerchantFamilyPO>() {
					@Override
					public MerchantFamilyPO extractData(ResultSet rs)
							throws SQLException, DataAccessException {
						if (rs.next()) {
							MerchantFamilyPO merchantFamilyPO = new MerchantFamilyPO();
		 								merchantFamilyPO.setId(rs.getInt("id"));
										merchantFamilyPO.setHouseholder(rs.getString("householder"));
										merchantFamilyPO.setHomeaddress(rs.getString("homeAddress"));
										merchantFamilyPO.setHomephone(rs.getString("homePhone"));
		 								merchantFamilyPO.setCustomerid(rs.getInt("customerId"));
		 								merchantFamilyPO.setMerchantid(rs.getInt("merchantId"));
										merchantFamilyPO.setCreatedtime(rs.getDate("createdTime"));
										merchantFamilyPO.setStatus(rs.getString("status"));
							return merchantFamilyPO;
						}
						return null;
					}
				});
	}
	/*
		public MerchantFamilyPO getMerchantFamilyPO(Integer id) {
		return this.getJdbcTemplate().query(merchantFamilyGetSQL.getSqlHelp().getSql(),
				new Object[] { id }, new MerchantFamilyExtractor ());
	}
	
	
	
	*/
	
	
	
	/**
	 * 增加一条merchantFamilysql
	 */
	@Resource(name = "merchant_family_insert_sql")
	private JSONSqlMapping merchantFamilyInsertSQL;
	/**
	 * 增加一条merchantFamily信息
	 */
	public Integer createMerchantFamilyPO(final MerchantFamilyPO merchantFamilyPO) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps = con.prepareStatement( new SqlHelp(merchantFamilyInsertSQL).getSql(),
						PreparedStatement.RETURN_GENERATED_KEYS);
				Timestamp now = new Timestamp(System.currentTimeMillis());
				int i = 1;
							ps.setString(i++, merchantFamilyPO.getHouseholder());
							ps.setString(i++, merchantFamilyPO.getHomeaddress());
							ps.setString(i++, merchantFamilyPO.getHomephone());
			 				ps.setInt(i++, merchantFamilyPO.getCustomerid());
			 				ps.setInt(i++, merchantFamilyPO.getMerchantid());
							ps.setTimestamp(i++, now);	
				return ps;
			}
		}, keyHolder);
		System.out.println(keyHolder.getKey().intValue() + "主键");
		return keyHolder.getKey().intValue();
	}
	
	/*
		public Integer createMerchantFamilyPO(final MerchantFamilyPO merchantFamilyPO) {
				KeyHolder keyHolder = new GeneratedKeyHolder();
				this.getJdbcTemplate().update(new MerchantFamilyCreator(INSERT_SQL, merchantFamilyPO),keyHolder);
				System.out.println(keyHolder.getKey().intValue() + "主键");
				return keyHolder.getKey().intValue();
		}
	
	*/
	
	
	/**
	 * 修改merchantFamily的sql
	 */
	@Resource(name = "merchant_family_update_sql")
	private JSONSqlMapping merchantFamilyUpdateSQL;
	/**
	 * 修改merchantFamily信息
	 * SQL语句根据需要编写
	 * @param merchantFamilyPO
	 * @return
	 */
	public boolean updateMerchantFamilyPO(MerchantFamilyPO merchantFamilyPO) {
		int count = this.getJdbcTemplate().update(
				 		new SqlHelp(merchantFamilyUpdateSQL).getSql()
						,merchantFamilyPO.getHouseholder()
						,merchantFamilyPO.getHomeaddress()
						,merchantFamilyPO.getHomephone()
						,merchantFamilyPO.getCustomerid()
						,merchantFamilyPO.getMerchantid()
						,merchantFamilyPO.getCreatedtime()
						,merchantFamilyPO.getId()
				);
		return count > 0;
	}
	
	
	/**
	 * 冻结家庭
	 */
	public boolean updateFamilyStatus(MerchantFamilyPO merchantFamilyPO) {
			int count = this.getJdbcTemplate().update(
					FREEZEN_FAMILY
					,merchantFamilyPO.getStatus()
					,merchantFamilyPO.getId()
			);
			return count > 0;
	}
	
	
	
	
	/**
	 * 删除merchantFamily信息的sql
	 * 
	 */
	@Resource(name = "merchant_family_delete_sql")
	private JSONSqlMapping deletemerchantFamilySql;
	/**
	 * 删除merchantFamily信息(此处为真正从数据库中删除数据)
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteMerchantFamily(Integer id) {
		int count = this.getJdbcTemplate().update(
				new SqlHelp(deletemerchantFamilySql).getSql(), id);
		return count > 0;
	}

	
	/**
	 * 取得家庭的图片地址
	 */
	@Resource(name = "merchant_family_img_get_sql")
	private JSONSqlMapping merchantFamilyImgGetSQL;
	public List<Map<String, Object>> findMerchantFamilyImg(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(merchantFamilyImgGetSQL, jsonObject);
	}
	
	
	
	@Resource(name = "merchant_family_customer_select_sql")
	private JSONSqlMapping merchantFamilyCustomerSelectSQL;
	/**
	 * 分页查询家庭的用户
	 * @author sdf
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findMerchantFamilyCustomerPage(JSONObject jsonObject,
			int pageSize, int iDisplayStart) {
		return this.findPageByJSONSqlMapping(merchantFamilyCustomerSelectSQL,
				jsonObject, pageSize, iDisplayStart);
	}
	
	
	
	
	
	
	
	
	@Resource(name = "merchant_family_customer_nostatus_select_sql")
	private JSONSqlMapping merchantFamilyCustomerNostatusSelectSql;
	/**
	 * 条件查询家庭的用户
	 * @author xuwei
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public List<Map<String, Object>> findFamilyCustomer(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(merchantFamilyCustomerNostatusSelectSql, jsonObject);
	}
	/**
	 *  分页查询家庭的用户无重复记录
	 * @author xuwei
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findFamilyCustomerByPage(JSONObject jsonObject,
			int pageSize, int iDisplayStart) {
		return this.findPageByJSONSqlMapping(merchantFamilyCustomerNostatusSelectSql,
				jsonObject, pageSize, iDisplayStart);
	}
	
	/**
	 * 冻结家庭
	 */
	private static final String FREEZEN_FAMILY="UPDATE  merchant_family SET `status`=? WHERE id=?";

}

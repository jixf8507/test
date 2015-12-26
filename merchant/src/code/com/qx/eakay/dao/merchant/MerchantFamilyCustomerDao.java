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
import com.qx.eakay.model.merchant.MerchantFamilyCustomerPO;

/**
 *
 * @author Administrator
 * @date 2015-11-26 16:37:27
 */
@Repository
public class MerchantFamilyCustomerDao extends BaseDao {

		/**
		 *条件分页查询sql
		 * 家庭，用户关系查询
		 */
		@Resource(name = "merchant_family_relation_select_sql")
		private JSONSqlMapping merchantFamilyCustomerSelectSQL;
		/**
		 * 条件分页查询
		 * 
		 * @param jsonObject
		 * @param pageSize
		 * @param iDisplayStart
		 * @return
		 */
		public PageResults findMerchantFamilyCustomerPage(JSONObject jsonObject, int pageSize,
				int iDisplayStart) {
			return this.findPageByJSONSqlMapping(merchantFamilyCustomerSelectSQL, jsonObject, pageSize,
					iDisplayStart);
		}
		
		/**
		 * 条件搜索
		 */
		public List<Map<String, Object>> findMerchantFamilyCustomerByJSONObject(JSONObject jsonObject) {
			return this.findListByJSONSqlMapping(merchantFamilyCustomerSelectSQL, jsonObject);
		}
		
		
		
		/**
		 * 根据id查找merchantFamilyCustomer的sql
		 */
		@Resource(name = "merchant_family_customer_get_sql")
		private JSONSqlMapping merchantFamilyCustomerGetSQL;
		/**
		 * 根据id查找merchantFamilyCustomer信息
		 * 
		 * @param id
		 * @return
		 */
		public MerchantFamilyCustomerPO getMerchantFamilyCustomerPO(Integer id) {
			return this.getJdbcTemplate().query(new SqlHelp(merchantFamilyCustomerGetSQL).getSql(),
					new Object[] { id }, new ResultSetExtractor<MerchantFamilyCustomerPO>() {
						@Override
						public MerchantFamilyCustomerPO extractData(ResultSet rs)
								throws SQLException, DataAccessException {
							if (rs.next()) {
								MerchantFamilyCustomerPO merchantFamilyCustomerPO = new MerchantFamilyCustomerPO();
			 								merchantFamilyCustomerPO.setId(rs.getInt("id"));
			 								merchantFamilyCustomerPO.setFamilyid(rs.getInt("familyId"));
			 								merchantFamilyCustomerPO.setCustomerid(rs.getInt("customerId"));
								return merchantFamilyCustomerPO;
							}
							return null;
						}
					});
		}
		/*
			public MerchantFamilyCustomerPO getMerchantFamilyCustomerPO(Integer id) {
			return this.getJdbcTemplate().query(merchantFamilyCustomerGetSQL.getSqlHelp().getSql(),
					new Object[] { id }, new MerchantFamilyCustomerExtractor ());
		}
		
		
		
		*/
		
		
		
		/**
		 * 增加一条merchantFamilyCustomersql
		 */
		@Resource(name = "merchant_family_customer_insert_sql")
		private JSONSqlMapping merchantFamilyCustomerInsertSQL;
		/**
		 * 增加一条merchantFamilyCustomer信息
		 */
		public Integer createMerchantFamilyCustomerPO(final MerchantFamilyCustomerPO merchantFamilyCustomerPO) {
			KeyHolder keyHolder = new GeneratedKeyHolder();
			this.getJdbcTemplate().update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection con)
						throws SQLException {
					PreparedStatement ps = con.prepareStatement(new SqlHelp(merchantFamilyCustomerInsertSQL).getSql(),
							PreparedStatement.RETURN_GENERATED_KEYS);
					Timestamp now = new Timestamp(System.currentTimeMillis());
					int i = 1;
				 				ps.setInt(i++, merchantFamilyCustomerPO.getFamilyid());
				 				ps.setInt(i++, merchantFamilyCustomerPO.getCustomerid());
					return ps;
				}
			}, keyHolder);
			System.out.println(keyHolder.getKey().intValue() + "主键");
			return keyHolder.getKey().intValue();
		}
		
		/*
			public Integer createMerchantFamilyCustomerPO(final MerchantFamilyCustomerPO merchantFamilyCustomerPO) {
					KeyHolder keyHolder = new GeneratedKeyHolder();
					this.getJdbcTemplate().update(new MerchantFamilyCustomerCreator(INSERT_SQL, merchantFamilyCustomerPO),keyHolder);
					System.out.println(keyHolder.getKey().intValue() + "主键");
					return keyHolder.getKey().intValue();
			}
		
		*/
		
		
		/**
		 * 修改merchantFamilyCustomer的sql
		 */
		@Resource(name = "merchant_family_customer_update_sql")
		private JSONSqlMapping merchantFamilyCustomerUpdateSQL;
		/**
		 * 修改merchantFamilyCustomer信息
		 * SQL语句根据需要编写
		 * @param merchantFamilyCustomerPO
		 * @return
		 */
		public boolean updateMerchantFamilyCustomerPO(MerchantFamilyCustomerPO merchantFamilyCustomerPO) {
			int count = this.getJdbcTemplate().update(
					new SqlHelp(merchantFamilyCustomerUpdateSQL).getSql()
							,merchantFamilyCustomerPO.getFamilyid()
							,merchantFamilyCustomerPO.getCustomerid()
					);
			return count > 0;
		}
		
		
		public boolean updateMerchantFamilyCustomerId(Integer customerId,Integer familyId,Integer newCustomerId) {
			int count = this.getJdbcTemplate().update(UODATE_CUSTOMER_ID
					,newCustomerId
					,familyId
					,customerId);
	
			return count > 0;
		}
		
		
		
		/**
		 * 删除merchantFamilyCustomer信息的sql
		 * 
		 */
		@Resource(name = "merchant_family_customer_delete_sql")
		private JSONSqlMapping deletemerchantFamilyCustomerSql;
		/**
		 * 删除merchantFamilyCustomer信息(此处为真正从数据库中删除数据)
		 * 
		 * @param id
		 * @return
		 */
		public boolean deleteMerchantFamilyCustomer(Integer id) {
			int count = this.getJdbcTemplate().update(
					new SqlHelp(deletemerchantFamilyCustomerSql).getSql(), id);
			return count > 0;
		}
		
		/**
		 * 根据familyId删除merchantFamilyCustomer信息(此处为真正从数据库中删除数据)
		 * 
		 * @param id
		 * @return
		 */
		public boolean deleteMerchantFamilyCustomerByFamilyId(Integer id) {
			int count = this.getJdbcTemplate().update(
					DELETE_BY_FAMILY_ID, id);
			return count > 0;
		}
		
		/**
		 * 根据customerId去删除家庭成员关系
		 */
		@Resource(name = "merchant_family_customer_delete_by_customerid_sql")
		private JSONSqlMapping deletemerchantFamilyCustomerByCustomerId;
		public boolean deleteMerchantFamilyCustomerByCustomerId(Integer familyId,Integer customerId) {
			int count = this.getJdbcTemplate().update(
					new SqlHelp(deletemerchantFamilyCustomerByCustomerId).getSql(), familyId,customerId);
			return count > 0;
		}
		
		//更新家庭关系的customerId
		private static final String UODATE_CUSTOMER_ID="UPDATE merchant_family_customer  SET  customerId=? WHERE familyId=? AND customerId=?";

		private static final String DELETE_BY_FAMILY_ID="DELETE FROM merchant_family_customer WHERE familyId= ?";
}

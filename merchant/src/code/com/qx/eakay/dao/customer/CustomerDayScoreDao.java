package com.qx.eakay.dao.customer;

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
import com.qx.eakay.model.customer.CustomerDayScorePO;

/**
 *
 * @author Administrator
 * @date 2015-12-10 11:11:32
 */
@Repository
public class CustomerDayScoreDao extends BaseDao {

		/**
		 *条件分页查询sql
		 * 
		 */
		@Resource(name = "customer_day_score_select_sql")
		private JSONSqlMapping customerDayScoreSelectSQL;
		/**
		 * 条件分页查询
		 * 
		 * @param jsonObject
		 * @param pageSize
		 * @param iDisplayStart
		 * @return
		 */
		public PageResults findCustomerDayScorePage(JSONObject jsonObject, int pageSize,
				int iDisplayStart) {
			return this.findPageByJSONSqlMapping(customerDayScoreSelectSQL, jsonObject, pageSize,
					iDisplayStart);
		}
		
		/**
		 * 条件搜索
		 */
		public List<Map<String, Object>> findCustomerDayScoreByJSONObject(JSONObject jsonObject) {
			return this.findListByJSONSqlMapping(customerDayScoreSelectSQL, jsonObject);
		}
		
		
		
		/**
		 * 根据id查找customerDayScore的sql
		 */
		@Resource(name = "customer_day_score_get_sql")
		private JSONSqlMapping customerDayScoreGetSQL;
		/**
		 * 根据id查找customerDayScore信息
		 * 
		 * @param id
		 * @return
		 */
		public CustomerDayScorePO getCustomerDayScorePO(Integer id) {
			return this.getJdbcTemplate().query(new SqlHelp(customerDayScoreGetSQL).getSql(),
					new Object[] { id }, new ResultSetExtractor<CustomerDayScorePO>() {
						@Override
						public CustomerDayScorePO extractData(ResultSet rs)
								throws SQLException, DataAccessException {
							if (rs.next()) {
								CustomerDayScorePO customerDayScorePO = new CustomerDayScorePO();
			 								customerDayScorePO.setId(rs.getInt("id"));
			 								customerDayScorePO.setCustomerid(rs.getInt("customerId"));
											customerDayScorePO.setDay(rs.getString("day"));
			 								customerDayScorePO.setAddscore(rs.getInt("addScore"));
			 								customerDayScorePO.setReducedscore(rs.getInt("reducedScore"));
			 								customerDayScorePO.setTotlescore(rs.getInt("totleScore"));
			 								customerDayScorePO.setRegisteredscore(rs.getInt("registeredScore"));
			 								customerDayScorePO.setCertifiedscore(rs.getInt("certifiedScore"));
			 								customerDayScorePO.setAssurescore(rs.getInt("assureScore"));
			 								customerDayScorePO.setPayscore(rs.getInt("payScore"));
			 								customerDayScorePO.setCartimescore(rs.getInt("carTimeScore"));
			 								customerDayScorePO.setRefereescore(rs.getInt("refereeScore"));
			 								customerDayScorePO.setBerefereescore(rs.getInt("beRefereeScore"));
			 								customerDayScorePO.setPeccancyscore(rs.getInt("peccancyScore"));
			 								customerDayScorePO.setDamagescore(rs.getInt("damageScore"));
											customerDayScorePO.setCreatedtime(rs.getDate("createdTime"));
								return customerDayScorePO;
							}
							return null;
						}
					});
		}
		/*
			public CustomerDayScorePO getCustomerDayScorePO(Integer id) {
			return this.getJdbcTemplate().query(customerDayScoreGetSQL.getSqlHelp().getSql(),
					new Object[] { id }, new CustomerDayScoreExtractor ());
		}
		
		
		
		*/
		
		
		
		/**
		 * 增加一条customerDayScoresql
		 */
		@Resource(name = "customer_day_score_insert_sql")
		private JSONSqlMapping customerDayScoreInsertSQL;
		/**
		 * 增加一条customerDayScore信息
		 */
		public Integer createCustomerDayScorePO(final CustomerDayScorePO customerDayScorePO) {
			KeyHolder keyHolder = new GeneratedKeyHolder();
			this.getJdbcTemplate().update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection con)
						throws SQLException {
					PreparedStatement ps = con.prepareStatement(new SqlHelp(customerDayScoreInsertSQL).getSql(),
							PreparedStatement.RETURN_GENERATED_KEYS);
					Timestamp now = new Timestamp(System.currentTimeMillis());
					int i = 1;
				 				ps.setInt(i++, customerDayScorePO.getCustomerid());
								ps.setString(i++, customerDayScorePO.getDay());
				 				ps.setInt(i++, customerDayScorePO.getAddscore());
				 				ps.setInt(i++, customerDayScorePO.getReducedscore());
				 				ps.setInt(i++, customerDayScorePO.getTotlescore());
				 				ps.setInt(i++, customerDayScorePO.getRegisteredscore());
				 				ps.setInt(i++, customerDayScorePO.getCertifiedscore());
				 				ps.setInt(i++, customerDayScorePO.getAssurescore());
				 				ps.setInt(i++, customerDayScorePO.getPayscore());
				 				ps.setInt(i++, customerDayScorePO.getCartimescore());
				 				ps.setInt(i++, customerDayScorePO.getRefereescore());
				 				ps.setInt(i++, customerDayScorePO.getBerefereescore());
				 				ps.setInt(i++, customerDayScorePO.getPeccancyscore());
				 				ps.setInt(i++, customerDayScorePO.getDamagescore());
								ps.setTimestamp(i++, new Timestamp(customerDayScorePO.getCreatedtime().getTime()));	
					return ps;
				}
			}, keyHolder);
			System.out.println(keyHolder.getKey().intValue() + "主键");
			return keyHolder.getKey().intValue();
		}
		
		/*
			public Integer createCustomerDayScorePO(final CustomerDayScorePO customerDayScorePO) {
					KeyHolder keyHolder = new GeneratedKeyHolder();
					this.getJdbcTemplate().update(new CustomerDayScoreCreator(INSERT_SQL, customerDayScorePO),keyHolder);
					System.out.println(keyHolder.getKey().intValue() + "主键");
					return keyHolder.getKey().intValue();
			}
		
		*/
		
		
		/**
		 * 修改customerDayScore的sql
		 */
		@Resource(name = "customer_day_score_update_sql")
		private JSONSqlMapping customerDayScoreUpdateSQL;
		/**
		 * 修改customerDayScore信息
		 * SQL语句根据需要编写
		 * @param customerDayScorePO
		 * @return
		 */
		public boolean updateCustomerDayScorePO(CustomerDayScorePO customerDayScorePO) {
			int count = this.getJdbcTemplate().update(
					new SqlHelp(customerDayScoreUpdateSQL).getSql()
							,customerDayScorePO.getCustomerid()
							,customerDayScorePO.getDay()
							,customerDayScorePO.getAddscore()
							,customerDayScorePO.getReducedscore()
							,customerDayScorePO.getTotlescore()
							,customerDayScorePO.getRegisteredscore()
							,customerDayScorePO.getCertifiedscore()
							,customerDayScorePO.getAssurescore()
							,customerDayScorePO.getPayscore()
							,customerDayScorePO.getCartimescore()
							,customerDayScorePO.getRefereescore()
							,customerDayScorePO.getBerefereescore()
							,customerDayScorePO.getPeccancyscore()
							,customerDayScorePO.getDamagescore()
							,customerDayScorePO.getCreatedtime()
					);
			return count > 0;
		}
		
		
		
		/**
		 * 删除customerDayScore信息的sql
		 * 
		 */
		@Resource(name = "customer_day_score_delete_sql")
		private JSONSqlMapping deletecustomerDayScoreSql;
		/**
		 * 删除customerDayScore信息(此处为真正从数据库中删除数据)
		 * 
		 * @param id
		 * @return
		 */
		public boolean deleteCustomerDayScore(Integer id) {
			int count = this.getJdbcTemplate().update(
					new SqlHelp(deletecustomerDayScoreSql).getSql(), id);
			return count > 0;
		}
		
		
		/**
		 * 批量插入数据
		 */
	
	

	
}

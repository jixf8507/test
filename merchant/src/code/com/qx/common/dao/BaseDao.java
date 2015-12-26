package com.qx.common.dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.qx.common.base.PageResults;
import com.qx.common.base.SqlHelp;
import com.qx.common.sql.JSONSqlMapping;

@Repository
public class BaseDao {
	protected Logger logger = Logger.getLogger(getClass());

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	protected PageResults findPageByJSONSqlMapping(JSONSqlMapping sp,
			JSONObject jsonObject, int pageSize, int iDisplayStart) {
		sp.setJsonObject(jsonObject);
		return findPageBySqlHelp(new SqlHelp(sp), pageSize, iDisplayStart);
	}

	protected List<Map<String, Object>> findListByJSONSqlMapping(
			JSONSqlMapping sp, JSONObject jsonObject) {
		sp.setJsonObject(jsonObject);
		return this.findListBySQLHelp(new SqlHelp(sp));
	}

	/**
	 * 条件查询
	 * 
	 * @param sqlHelp
	 *            SQL查询帮助类
	 * @return
	 */
	protected List<Map<String, Object>> findListBySQLHelp(SqlHelp sqlHelp) {
		return this.findListBySQL(sqlHelp.getSql(), sqlHelp.getArgs());
	}

	/**
	 * 条件查询
	 * 
	 * @param sql
	 *            查询SQL
	 * @param args
	 *            查询参数
	 * @return
	 */
	protected List<Map<String, Object>> findListBySQL(String sql, Object[] args) {
		logger.info("JDBC sql: " + sql);
		return this.jdbcTemplate.query(sql, args,
				new RowMapper<Map<String, Object>>() {
					@Override
					public Map<String, Object> mapRow(ResultSet rs, int num)
							throws SQLException {
						Map<String, Object> map = new HashMap<String, Object>();
						ResultSetMetaData metaData = rs.getMetaData();
						int columnCount = metaData.getColumnCount();
						for (int i = 1; i <= columnCount; i++) {
							String columnName = metaData.getColumnLabel(i);
							Object value = rs.getObject(i);
							map.put(columnName, value == null ? "" : value + "");
						}
						return map;
					}
				});
	}

	/**
	 * 分页查询方法
	 * 
	 * @param sqlHelp
	 *            SQL帮助类
	 * @param pageSize
	 *            每页条数
	 * @param iDisplayStart
	 *            起始行
	 * @return
	 */
	protected PageResults findPageBySqlHelp(SqlHelp sqlHelp, int pageSize,
			int iDisplayStart) {
		return this.findPageBySql(sqlHelp.getSql(), sqlHelp.getArgs(),
				pageSize, iDisplayStart);
	}

	/**
	 * 分页查询
	 * 
	 * @param sql
	 *            查询SQL语句
	 * @param args
	 *            查询参数
	 * @param pageSize
	 *            每页条数
	 * @param iDisplayStart
	 *            起始条数
	 * @return
	 */
	protected PageResults findPageBySql(String sql, Object[] args,
			final int pageSize, final int iDisplayStart) {
		final PageResults page = new PageResults();
		int count = this.findCount(getCountSQL(sql), args);
		logger.info("记录数 count: " + count);
		sql = this.getLimitSQL(sql);
		args = ArrayUtils.add(args, args.length, iDisplayStart);
		args = ArrayUtils.add(args, args.length, pageSize);
		List<Map<String, Object>> list = this.findListBySQL(sql, args);
		page.setAaData(list);
		page.setiTotalDisplayRecords(count);
		page.setiTotalRecords(count);
		return page;
	}

	/**
	 * 查询条数
	 * 
	 * @param sql
	 * @param obj
	 * @return
	 */
	protected int findCount(final String sql, Object[] obj) {
		return this.getJdbcTemplate().queryForObject(sql, obj, Integer.class);
	}

	/**
	 * 拼接分页查询的SQL
	 * 
	 * @param sql
	 * @return
	 */
	private String getLimitSQL(String sql) {
		return "select * from (" + sql + ") tb limit ?,?";
	}

	/**
	 * 记录数查询的SQL
	 * 
	 * @param sql
	 * @return
	 */
	private String getCountSQL(String sql) {
		return "select count(*) from (" + sql + ") tb ";
	}

}

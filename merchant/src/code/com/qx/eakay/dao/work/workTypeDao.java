package com.qx.eakay.dao.work;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.qx.common.base.PageResults;
import com.qx.common.base.SqlHelp;
import com.qx.common.dao.BaseDao;
import com.qx.common.sql.JSONSqlMapping;
import com.qx.eakay.db.extractor.WorkTypeExtractor;
import com.qx.eakay.model.work.WorkOrderPO;
import com.qx.eakay.model.work.workTypePO;

@Repository
public class workTypeDao extends BaseDao {

	@Resource(name = "work_Type_select_sql")
	private JSONSqlMapping workTypeselectSQL;

	/**
	 * 分页查询平台客户信息
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return this.findPageByJSONSqlMapping(workTypeselectSQL, jsonObject,
				pageSize, iDisplayStart);
	}

	/**
	 * 条件搜索违章记录列表
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findPeccancyRecords(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(workTypeselectSQL, jsonObject);
	}

	@Resource(name = "work_User_select_sql")
	private JSONSqlMapping workUserselectSQL;

	/**
	 * 查询所有的负责人
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findUser(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return this.findPageByJSONSqlMapping(workUserselectSQL, jsonObject,
				pageSize, iDisplayStart);
	}

	/**
	 * 保存新增的工单类型sql
	 */
	@Resource(name = "work_type_insert_sql")
	private JSONSqlMapping worktypeinsertSQL;

	public Integer saveAdd(final workTypePO worktypePO) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps = con.prepareStatement(new SqlHelp(
						worktypeinsertSQL).getSql(),
						PreparedStatement.RETURN_GENERATED_KEYS);
				Timestamp now = new Timestamp(System.currentTimeMillis());
				int i = 1;
				ps.setString(i++, worktypePO.getTypeName());
				ps.setString(i++, worktypePO.getWorkDes());
				ps.setInt(i++, worktypePO.getUserId());
				ps.setTimestamp(i++, now);
				return ps;
			}
		}, keyHolder);
		System.out.println(keyHolder.getKey().intValue() + "主键");
		return keyHolder.getKey().intValue();
	}

	/**
	 * 根据id查找工单的信息
	 * 
	 * @param id
	 * @return
	 */
	public workTypePO getworkTypePO(Integer id) {
		return this.getJdbcTemplate().query(selecttypesql, new Object[] { id },
				new WorkTypeExtractor());
	}

	@Resource(name = "work_type_update_sql")
	private JSONSqlMapping worktypeUpdateSQL;

	/**
	 * 
	 * 保存修改的工单类型sql
	 * 
	 * */
	
		public boolean updateWorkType(workTypePO worktypePO) {
			return this.getJdbcTemplate().update(WORKORDERupdateSQL,
					 worktypePO.getTypeName(),worktypePO.getWorkDes(),
					worktypePO.getUserId(),  worktypePO.getId())>0;
	}

	/**
	 * 
	 * 
	 * 删除工单类型记录
	 * */
	@Resource(name = "work_Type_delete_sql")
	private JSONSqlMapping deletworkTypeSql;

	public boolean delete(Integer id) {
		int count = this.getJdbcTemplate().update(
				new SqlHelp(deletworkTypeSql).getSql(), id);
		return count > 0;
	}

	/**
	 * 动态加载工单类型
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findWorkType(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(workTypeselectSQL, jsonObject);
	}

	private static final String selecttypesql = "SELECT t.*,mu.userName,mu.mobilePhone FROM merchant_work_type t left join merchant_user mu on t.userId=mu.id where t.id=?";
	private  static final  String WORKORDERupdateSQL="update `merchant_work_type` set typeName=?,workDes=?,userId=? where id=? 	";

}
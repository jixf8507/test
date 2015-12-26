package com.qx.eakay.dao.work;

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
import com.qx.eakay.db.creator.WorkOrderCreator;
import com.qx.eakay.db.extractor.WorkOrderExtractor;
import com.qx.eakay.model.work.WorkOrderPO;

/**
 * 工单管理
 * 
 * @author sdf
 * @date 2015年12月16日
 */
@Repository
public class WorkOrderDao extends BaseDao {

	@Resource(name = "WORK_ORDER_SELECT_SQL")
	private JSONSqlMapping workOrderSelectSQL;

	/**
	 * 分页查询工单列表
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return this.findPageByJSONSqlMapping(workOrderSelectSQL, jsonObject,
				pageSize, iDisplayStart);
	}

	/**
	 * 条件查询工单列表
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findList(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(workOrderSelectSQL, jsonObject);
	}

	/**
	 * 保存新增工单发布
	 * 
	 * @param workOrderPO
	 * @return
	 */
	public Integer saveAdd(WorkOrderPO workOrderPO) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.getJdbcTemplate().update(
				new WorkOrderCreator(INSERT_SQL, workOrderPO), keyHolder);
		System.out.println(keyHolder.getKey().intValue() + "主键");
		return keyHolder.getKey().intValue();
	}

	/**
	 * 获取工单信息
	 * 
	 * @param id
	 * @return
	 */
	public WorkOrderPO getWorkOrder(Integer id) {
		return this.getJdbcTemplate().query(GET_SQL, new Object[] { id },
				new WorkOrderExtractor());
	}

	/**
	 * 保存工单处理
	 * 
	 * @param workOrderPO
	 * @return
	 */
	public boolean saveEdite(WorkOrderPO workOrderPO) {
		return this.getJdbcTemplate().update(UPDATE_HANDLE_SQL,
				workOrderPO.getUpdatedTime(), workOrderPO.getWorkStatus(),
				workOrderPO.getTransactDes(), workOrderPO.getId()) > 0;
	}

	/**
	 * 保存修改后的sql
	 * 
	 * @return
	 */
	public int updateWorkwork(WorkOrderPO workOrderPO) {
		return this.getJdbcTemplate().update(WORKORDERupdateSQL,
				workOrderPO.getWorkName(), workOrderPO.getTypeName(),
				workOrderPO.getUrgency(), workOrderPO.getCreatedTime(),
				workOrderPO.getTransactUser(), workOrderPO.getWorkDes(),
				workOrderPO.getUserId(), workOrderPO.getId());
	}
	
	/**
	 * 
	 * 
	 * 删除工单类型记录
	 * */
	@Resource(name = "work_order_delete_sql")
	private JSONSqlMapping deletworkorderSql;

	public boolean delete(Integer id) {
		int count = this.getJdbcTemplate().update(
				new SqlHelp(deletworkorderSql).getSql(), id);
		return count > 0;
	}
	

	// 新增工单SQL
	private static final String INSERT_SQL = "insert into merchant_user_work (userId,workName,typeName,workStatus,urgency,"
			+ "createdTime,transactUser,updatedTime,workDes) values (?,?,?,?,?,?,?,?,?)";
	// 根据ID查找工单信息的SQL
	private static final String GET_SQL = "select w.*,u.userName,u.mobilePhone from merchant_user_work w "
			+ "left join merchant_user u on (u.id = w.userId) where w.id=?";
	// 修改工单处理信息的SQL
	private static final String UPDATE_HANDLE_SQL = "update merchant_user_work set updatedTime=?,workStatus=?,transactDes=? where id = ?";
	// 修改工单
	private static final String WORKORDERupdateSQL = "update `merchant_user_work` set workName=?,typeName=?,urgency=?,createdTime=?,transactUser=?,workDes=?,userId=? where id=? ";

}

package com.qx.eakay.dao.order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.google.gson.JsonObject;
import com.qx.common.base.PageResults;
import com.qx.common.base.SqlHelp;
import com.qx.common.dao.BaseDao;
import com.qx.common.sql.JSONSqlMapping;
import com.qx.eakay.dao.customer.AccountDao;
import com.qx.eakay.db.creator.PeccancyRecordCreator;
import com.qx.eakay.model.car.CarDetailPO;
import com.qx.eakay.model.customer.AccountPO.AccountStatus;
import com.qx.eakay.model.order.OrderPeccancyRecordPO;
import com.qx.eakay.util.MSG;
import com.qx.msg.CrMsgDao;

/**
 * 
 * @author jixf
 * @date 2015年7月8日
 */
@Repository
public class PeccancyRecordDao extends BaseDao {

	@Autowired
	private CrMsgDao crMsgDao;
	@Resource(name = "order_peccancyRecord_select_sql")
	private JSONSqlMapping peccancyRecordSelectSQL;

	/**
	 * 分页查询违章记录列表
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findPeccancyRecordPage(JSONObject jsonObject,
			int pageSize, int iDisplayStart) {
		return this.findPageByJSONSqlMapping(peccancyRecordSelectSQL,
				jsonObject, pageSize, iDisplayStart);
	}

	/**
	 * 条件搜索违章记录列表
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findPeccancyRecords(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(peccancyRecordSelectSQL,
				jsonObject);
	}

	public Integer create(OrderPeccancyRecordPO peccancyRecordPO) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.getJdbcTemplate().update(
				new PeccancyRecordCreator(INSERT_SQL, peccancyRecordPO),
				keyHolder);
		System.out.println(keyHolder.getKey().intValue() + "主键");
		return keyHolder.getKey().intValue();
	}

	// 插入车辆违章记录SQL
	private static final String INSERT_SQL = "insert into order_peccancy_record (orderId,peccancyTime,address,info,img,"
			+ "payCost,payStatus,checkMan,createdTime) values(?,?,?,?,?,?,?,?,now())";

	// 修改车辆违章记录SQL
	private static final String UPDATE_SQL = "update order_peccancy_record set orderId = ?,peccancyTime = ?,address = ?,"
			+ "info = ?,img = ?,payCost = ?,payStatus = ?,checkMan = ? where id = ? ";

	// 查找车辆违章记录SQL
	private static final String SELECT_SQL = "select pr.id,pr.address,pr.info,pr.payCost,"
			+ "pr.payStatus,pr.checkMan,o.id as orderId,o.schemingGetTime,o.schemingReturnTime,ci.`name`,ci.phone,car.carNumber,pr.peccancyTime "
			+ "from order_peccancy_record pr,`order` o,customer ci,car car "
			+ "where pr.orderId=o.id and o.customerId=ci.id and o.carId=car.id and pr.id = ? ";

	// 将上传文件插入数据库
	private static final String save_insert_SQL = "insert into order_peccancy_record (orderId,peccancyTime,address,info,img,payCost,payStatus,checkMan,createdTime) values(?,?,?,?,?,?,?,?,now())";

	// 更新账户的状态
	private static final String UPDATE_STATUS_SQL = "update account set status=? where id=?";

	/*
	 * 根据id查找违章记录
	 * 
	 * @param id
	 * 
	 * @return
	 */
	public OrderPeccancyRecordPO getById(Integer id) {
		List<OrderPeccancyRecordPO> list = this.getJdbcTemplate().query(
				SELECT_SQL, new Object[] { id },
				new RowMapper<OrderPeccancyRecordPO>() {
					@Override
					public OrderPeccancyRecordPO mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						OrderPeccancyRecordPO orderPeccancyRecord = new OrderPeccancyRecordPO();
						orderPeccancyRecord.setId(rs.getInt("id"));
						orderPeccancyRecord.setOrderId(rs.getInt("orderId"));
						orderPeccancyRecord.setAddress(rs.getString("address"));
						orderPeccancyRecord.setInfo(rs.getString("info"));
						orderPeccancyRecord.setPayCost(rs
								.getBigDecimal("payCost"));
						orderPeccancyRecord.setPayStatus(rs
								.getString("payStatus"));
						orderPeccancyRecord.setCheckMan(rs
								.getString("checkMan"));
						orderPeccancyRecord.setPeccancyTime(rs
								.getString("peccancyTime"));
						orderPeccancyRecord.setName(rs.getString("name"));
						orderPeccancyRecord.setPhone(rs.getString("phone"));
						orderPeccancyRecord.setCarNumber(rs
								.getString("carNumber"));
						orderPeccancyRecord.setSchemingGetTime(rs
								.getTimestamp("schemingGetTime"));
						orderPeccancyRecord.setSchemingReturnTime(rs
								.getTimestamp("schemingReturnTime"));
						return orderPeccancyRecord;
					}
				});
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	
	@Resource(name = "peccancyRecord_Send_Message_sql")
	private JSONSqlMapping peccancyRecordSendMessageSQL;
	/**
	 * 查询两次违章的用户并返回发送成功
	 * 
	 * @param jsonObject
	 * @return
	 */
	public boolean peccancyRecordSendMessage() {
		List<Map<String, Object>> list = this.findListByJSONSqlMapping(
				peccancyRecordSendMessageSQL, null);
		String content = "亲，您因两次以上违章，账户已被冻结，暂时不能租车，请去网点激活账户哦！";
		for (Map<String, Object> map : list) {
				if (map.get("status").equals("正常")) {
					this.getJdbcTemplate().update(UPDATE_STATUS_SQL, "冻结",
							(Integer.valueOf((String) map.get("accountId"))));
					crMsgDao.send(content, (String) map.get("phone"));
					return true;
				}
			
		}
		return false;
	}

	/**
	 * 保存修改违章记录
	 * 
	 * @param peccancyRecordPO
	 * @return
	 */
	public boolean editPeccancyRecord(OrderPeccancyRecordPO peccancyRecordPO) {
		Integer count = this.getJdbcTemplate().update(UPDATE_SQL,
				peccancyRecordPO.getOrderId(),
				peccancyRecordPO.getPeccancyTime(),
				peccancyRecordPO.getAddress(), peccancyRecordPO.getInfo(),
				peccancyRecordPO.getImg(), peccancyRecordPO.getPayCost(),
				peccancyRecordPO.getPayStatus(),
				peccancyRecordPO.getCheckMan(), peccancyRecordPO.getId());
		return count > 0;
	}

	/**
	 * 根据用户上传文件查询是否存在订单
	 * 
	 * 
	 * */
	@Resource(name = "Select_Is_Exist_Order")
	private JSONSqlMapping SelectIsExistOrder;

	public List<Map<String, Object>> selectOrder(JSONObject jsonObject) {
		List<Map<String, Object>> list = findListByJSONSqlMapping(
				SelectIsExistOrder, jsonObject);
		if (null != list && list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	/**
	 * 将用户成功上传的记录插入到数据库
	 * 
	 * @param peccancyRecordPO
	 * @return
	 */
	public int save_insert_PeccancyRecord(OrderPeccancyRecordPO peccancyRecordPO) {

		KeyHolder keyHolder = new GeneratedKeyHolder();
		return this.getJdbcTemplate().update(
				new PeccancyRecordCreator(save_insert_SQL, peccancyRecordPO),
				keyHolder);
	}

}

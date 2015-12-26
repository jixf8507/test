package com.qx.eakay.dao.order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.qx.common.base.PageResults;
import com.qx.common.dao.BaseDao;
import com.qx.common.sql.JSONSqlMapping;
import com.qx.eakay.db.creator.OrderCreator;
import com.qx.eakay.model.car.CarPO;
import com.qx.eakay.model.merchant.MerchantUserPO;
import com.qx.eakay.model.merchant.SitePO;
import com.qx.eakay.model.order.OrderPO;
import com.qx.eakay.model.order.OrderPO.OrderStatus;

/**
 * 车辆调度、公务用车
 * 
 * @author sdf
 * @date 2015年10月12日
 */
@Repository
public class OtherOrderDao extends BaseDao {

	@Resource(name = "OTHER_ORDER_SELECT_SQL")
	private JSONSqlMapping otherOrderSelectSQL;
	
	@Resource(name = "TABLE_OTHER_ORDER_SELECT_SQL")
	private JSONSqlMapping tableOtherOrderSelectSQL;

	/**
	 * 新增租赁订单
	 * 
	 * @param orderPO
	 * @return
	 */
	public int create(OrderPO orderPO) {
		if (orderPO.getScheReturnTime() == null
				|| orderPO.getScheReturnTime().equals("")) {
			orderPO.setSchemingReturnTime(orderPO.getRealityGetTime());
		}
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.getJdbcTemplate().update(new OrderCreator(INSERT_SQL, orderPO),
				keyHolder);
		System.out.println(keyHolder.getKey().intValue() + "主键");
		return keyHolder.getKey().intValue();
	}

	/**
	 * 查找租赁订单
	 * 
	 * @param id
	 * @return
	 */
	public OrderPO get(Integer id) {
		return this.getJdbcTemplate().query(GET_SQL, new Object[] { id },
				new ResultSetExtractor<OrderPO>() {
					@Override
					public OrderPO extractData(ResultSet rs)
							throws SQLException, DataAccessException {
						return createOtherOrderFromResultSet(rs);
					}
				});
	}

	private OrderPO createOtherOrderFromResultSet(ResultSet rs)
			throws SQLException {
		OrderPO orderPO = null;
		if (rs.next()) {
			orderPO = new OrderPO();
			orderPO.setId(rs.getInt("id"));
			orderPO.setCustomerId(rs.getInt("customerId"));
			orderPO.setCarId(rs.getInt("carId"));
			orderPO.setMerchantId(rs.getInt("merchantId"));
			orderPO.setPriceTypeId(rs.getInt("priceTypeId"));
			orderPO.setPriceTypeName(rs.getString("priceTypeTableName"));
			orderPO.setOrderStatus(rs.getString("orderStatus"));
			orderPO.setSubOrderStatus(rs.getString("subOrderStatus"));
			orderPO.setUseCost(rs.getBigDecimal("useCost"));
			orderPO.setTotalCost(rs.getBigDecimal("totalCost"));
			orderPO.setSchemingGetTime(rs.getTimestamp("schemingGetTime"));
			orderPO.setSchemingReturnTime(rs.getTimestamp("schemingReturnTime"));
			orderPO.setRealityGetTime(rs.getTimestamp("realityGetTime"));
			orderPO.setRealityReturnTime(rs.getTimestamp("realityReturnTime"));
			orderPO.setSocForGet(rs.getBigDecimal("socForGet"));
			orderPO.setSocForReturn(rs.getBigDecimal("socForReturn"));
			orderPO.setKmsForGet(rs.getFloat("kmsForGet"));
			orderPO.setKmsForReturn(rs.getFloat("kmsForReturn"));
			orderPO.setRelityGetSiteId(rs.getInt("relityGetSiteId"));
			orderPO.setRelityReturnSiteId(rs.getInt("relityReturnSiteId"));
			orderPO.setMenForGet(rs.getString("menForGet"));
			orderPO.setMenForReturn(rs.getString("menForReturn"));
			orderPO.setMenForCharge(rs.getString("menForCharge"));
			orderPO.setOtherDescribe(rs.getString("otherDescribe"));
			orderPO.setGetCarStatus(rs.getString("getCarStatus"));
			orderPO.setReturnCarStatus(rs.getString("returnCarStatus"));
			orderPO.setUseTime(rs.getString("useTime"));
			orderPO.setPerKms(rs.getFloat("perKms"));
			orderPO.setPerKmsCost(rs.getBigDecimal("perKmsCost"));
			orderPO.setOtherCost(rs.getBigDecimal("otherCost"));
			orderPO.setOrderDescribe(rs.getString("orderDescribe"));
			orderPO.setOrderType(rs.getInt("orderType"));
			orderPO.setCustomerId(rs.getInt("customerId"));
			orderPO.setMerchantId(rs.getInt("merchantId"));
			orderPO.setSurplusKmsForGet(rs.getFloat("surplusKmsForGet"));
			orderPO.setSurplusKmsForReturn(rs.getFloat("surplusKmsForReturn"));
			orderPO.setTicket(rs.getString("ticket"));
			orderPO.setPayment(rs.getString("payment"));
			orderPO.setMaintenanceCost(rs.getBigDecimal("maintenanceCost"));
			// 车辆信息
			CarPO carPO = new CarPO();
			carPO.setCarNumber(rs.getString("carNumber"));
			carPO.setSoc(rs.getBigDecimal("soc"));
			orderPO.setCarPO(carPO);
			// 企业会员信息
			MerchantUserPO merchantUserPO = new MerchantUserPO();
			merchantUserPO.setMobilePhone(rs.getString("mobilePhone"));
			merchantUserPO.setUserName(rs.getString("userName"));
			merchantUserPO.setRights(rs.getString("rights"));
			orderPO.setMerchantUserPO(merchantUserPO);
			// 取车租赁点
			SitePO gsitePO = new SitePO();
			gsitePO.setSiteName(rs.getString("gsiteName"));
			orderPO.setGsitePO(gsitePO);
			// 还车租赁点
			SitePO rsitePO = new SitePO();
			rsitePO.setSiteName(rs.getString("rsiteName"));
			orderPO.setRsitePO(rsitePO);
		}
		return orderPO;
	}

	/**
	 * 更改订单的状态
	 * 
	 * @param orderStatus
	 * @param subOrderStatus
	 * @param id
	 * @return
	 */
	public boolean updateStatus(OrderStatus orderStatus, String subOrderStatus,
			String returnCarConfirm, Integer id) {
		int count = this.getJdbcTemplate().update(UPDATE_STATUS_SQL,
				orderStatus.name(), subOrderStatus, returnCarConfirm, id);
		return count > 0;
	}

	/**
	 * 分页查询订单列表
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findOrderPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return this.findPageByJSONSqlMapping(otherOrderSelectSQL, jsonObject,
				pageSize, iDisplayStart);
	}

	/**
	 * 分页查询订单列表（只查询表格显示内容）
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findTableOrderPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return this.findPageByJSONSqlMapping(tableOtherOrderSelectSQL, jsonObject,
				pageSize, iDisplayStart);
	}

	/**
	 * 条件搜索订单列表
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findOrders(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(otherOrderSelectSQL, jsonObject);
	}

	// 新增订单SQL
	private static final String INSERT_SQL = "insert into `order` (customerId,carId,merchantId,priceTypeId,priceTypeTableName,orderStatus,subOrderStatus,createdTime,kmsForGet,"
			+ "imgForGet,socForGet,kmsForReturn,imgForReturn,useCost,totalCost,backCost,backDescribe,socForReturn,schemingGetTime,"
			+ "schemingReturnTime,realityGetTime,schemingGetSiteId,schemingReturnSiteId,relityGetSiteId,earlyGetCost,earlyReturnCost,laterGetCost,"
			+ "laterReturnCost,otherCost,insuranceCost,priceTypeName,menForGet,relityReturnSiteId,customerTable,orderType,orderDescribe,surplusKmsForGet,surplusKmsForReturn)"
			+ "	values(?,?,?,?,?,'"
			+ OrderStatus.已取车.name()
			+ "','正常',?,?,?,?,?,?,0,0,0,'',?,?,?,?,?,?,?,0,0,0,0,0,0,?,?,?,?,?,?,?,?)";
	// 根据ID查找订单的SQL
	private static final String GET_SQL = "select o.*,c.carNumber,c.soc,mu.mobilePhone,mu.userName,mu.rights,gs.siteName as gsiteName,rs.siteName as rsiteName,"
			+ "TIME_FORMAT(TIMEDIFF(o.realityReturnTime,o.realityGetTime),'%H:%i') useTime"
			+ " from `order` o INNER JOIN car c on o.carId=c.id INNER JOIN merchant_user mu on o.customerId=mu.id left JOIN site gs on o.relityGetSiteId =gs.id"
			+ "	left JOIN site rs on o.relityReturnSiteId = rs.id where o.id=?";
	// 修改订单状态的SQL
	private static final String UPDATE_STATUS_SQL = "update `order` set orderStatus=?,subOrderStatus=?,returnCarConfirm=? where id=?";

}

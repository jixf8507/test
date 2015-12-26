package com.qx.eakay.dao.order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.qx.common.base.PageResults;
import com.qx.common.dao.BaseDao;
import com.qx.common.sql.JSONSqlMapping;
import com.qx.eakay.db.creator.OrderCreator;
import com.qx.eakay.db.extractor.OrderExtractor;
import com.qx.eakay.model.car.CarPO;
import com.qx.eakay.model.customer.UnitCustomerPO;
import com.qx.eakay.model.merchant.CouponPo;
import com.qx.eakay.model.merchant.SitePO;
import com.qx.eakay.model.order.OrderPO;
import com.qx.eakay.model.order.OrderPO.OrderStatus;
import com.qx.eakay.model.price.MonthLeasePriceTypePO;
import com.qx.eakay.model.price.WhHoursPriceTypePO;

@Repository
public class OrderDao extends BaseDao {

	@Resource(name = "order_select_sql")
	private JSONSqlMapping orderSelectSQL;

	@Resource(name = "table_order_select_sql")
	private JSONSqlMapping tableOrderSelectSQL;

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
		return this.findPageByJSONSqlMapping(orderSelectSQL, jsonObject,
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
		return this.findPageByJSONSqlMapping(tableOrderSelectSQL, jsonObject,
				pageSize, iDisplayStart);
	}

	/**
	 * 条件搜索订单列表
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findOrders(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(orderSelectSQL, jsonObject);
	}

	@Resource(name = "order_chargeRecord_sql")
	private JSONSqlMapping chargeListSQL;

	public PageResults findChargeRecordPage(JSONObject jsonObject,
			int pageSize, int iDisplayStart) {
		return this.findPageByJSONSqlMapping(chargeListSQL, jsonObject,
				pageSize, iDisplayStart);
	}

	/**
	 * 条件搜索订单列表
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findChargeRecord(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(chargeListSQL, jsonObject);
	}

	@Resource(name = "order_statistics_site_sql")
	private JSONSqlMapping statisticsSiteSQL;

	/**
	 * 租赁点租赁统计
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> statisticsSite(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(statisticsSiteSQL, jsonObject);
	}

	@Resource(name = "order_statistics_day_sql")
	private JSONSqlMapping statisticsDaySQL;

	/**
	 * 每日租赁统计
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> statisticsDay(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(statisticsDaySQL, jsonObject);
	}

	@Resource(name = "order_statistics_day_get_sql")
	private JSONSqlMapping statisticsDayGetSQL;

	/**
	 * 每日租赁取车统计
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> statisticsDayGet(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(statisticsDayGetSQL, jsonObject);
	}

	@Resource(name = "order_statistics_month_sql")
	private JSONSqlMapping statisticsMonthSQL;

	/**
	 * 没有租赁统计
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> statisticsMonth(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(statisticsMonthSQL, jsonObject);
	}

	@Resource(name = "order_monitor_site_sql")
	private JSONSqlMapping monitorSiteSQL;

	/**
	 * 监控租赁点当前订单
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> monitorSiteOrder(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(monitorSiteSQL, jsonObject);
	}

	@Resource(name = "other_order_monitor_site_sql")
	private JSONSqlMapping otherMonitorSiteSQL;

	/**
	 * 监控租赁点当前订单(公务用车,车辆调度)
	 * 
	 * @param jsonObject
	 * @return
	 */
	public PageResults otherMonitorSiteSQL(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return this.findPageByJSONSqlMapping(otherMonitorSiteSQL, jsonObject,
				pageSize, iDisplayStart);
	}

	@Resource(name = "order_outtime_select_sql")
	private JSONSqlMapping findOutTimeOrderSQL;

	/**
	 * 查找超时未取车订单
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findOutTimeOrder() {
		return this.findListByJSONSqlMapping(findOutTimeOrderSQL, null);
	}

	@Resource(name = "order_outReturn_select_sql")
	private JSONSqlMapping findOutReturnOrderSQL;

	/**
	 * 查找超时未取车订单
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findOutReturnTimeOrder() {
		return this.findListByJSONSqlMapping(findOutReturnOrderSQL, null);
	}

	@Resource(name = "order_kms_statistics_select_sql")
	private JSONSqlMapping statisticsOrderKmsSQL;

	/**
	 * 统计租赁行驶公里数
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> statisticsOrderKms(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(statisticsOrderKmsSQL, jsonObject);
	}

	@Resource(name = "day_order_kms_statistics_select_sql")
	private JSONSqlMapping statisticsDayOrderKmsSQL;

	/**
	 * 统计租赁每日行驶公里数
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> statisticsDayOrderKms(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(statisticsDayOrderKmsSQL,
				jsonObject);
	}

	/**
	 * 查找租赁订单(只查询order)
	 * 
	 * @param id
	 * @return
	 */
	public OrderPO getOrderById(Integer id) {
		return this.getJdbcTemplate().query(GET_BY_ORDERID_SQL,
				new Object[] { id }, new ResultSetExtractor<OrderPO>() {
					@Override
					public OrderPO extractData(ResultSet rs)
							throws SQLException, DataAccessException {
						return createOrderFromResultSet(rs);
					}
				});

	}

	private OrderPO createOrderFromResultSet(ResultSet rs) throws SQLException {
		OrderPO orderPO = null;
		if (rs.next()) {
			orderPO = new OrderPO();
			orderPO.setOrderStatus(rs.getString("orderStatus"));
			orderPO.setReturnCarConfirm(rs.getString("returnCarConfirm"));
		}
		return orderPO;
	}

	/**
	 * 查找租赁订单
	 * 
	 * @param id
	 * @return
	 */
	public OrderPO get(Integer id) {
		// 普通客户租赁订单
		OrderPO orderPO = this.getJdbcTemplate().query(GET_SQL,
				new Object[] { id }, new OrderExtractor());
		// 企业用户租赁订单
		if (orderPO == null) {
			return this.getJdbcTemplate().query(GET_UNIT_SQL,
					new Object[] { id }, new ResultSetExtractor<OrderPO>() {
						@Override
						public OrderPO extractData(ResultSet rs)
								throws SQLException, DataAccessException {
							return createUnitOrderFromResultSet(rs);
						}
					});
		}
		return orderPO;
	}

	private OrderPO createUnitOrderFromResultSet(ResultSet rs)
			throws SQLException {
		OrderPO orderPO = null;
		if (rs.next()) {
			orderPO = new OrderPO();
			orderPO.setId(rs.getInt("id"));
			orderPO.setCustomerId(rs.getInt("customerId"));
			orderPO.setCarId(rs.getInt("carId"));
			orderPO.setMerchantId(rs.getInt("merchantId"));
			orderPO.setPriceTypeId(rs.getInt("priceTypeId"));
			orderPO.setPriceTypeTableName(rs.getString("priceTypeTableName"));
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
			orderPO.setCheckUser(rs.getString("checkUser"));
			orderPO.setCheckDescribe(rs.getString("checkDescribe"));
			orderPO.setMaintenanceCost(rs.getBigDecimal("maintenanceCost"));
			orderPO.setCouponId(rs.getInt("couponId"));
			orderPO.setCouponCost(rs.getBigDecimal("couponCost"));
			// 企业会员信息
			UnitCustomerPO unitCustomerPO = new UnitCustomerPO();
			unitCustomerPO.setEnterpriseName(rs.getString("enterpriseName"));
			unitCustomerPO.setContactPerson(rs.getString("contactPerson"));
			unitCustomerPO.setContactPhone(rs.getString("contactPhone"));
			unitCustomerPO.setBalance(rs.getBigDecimal("balance"));
			orderPO.setUnitCustomerPO(unitCustomerPO);
			// 车辆信息
			CarPO carPO = new CarPO();
			carPO.setCarNumber(rs.getString("carNumber"));
			carPO.setSoc(rs.getBigDecimal("soc"));
			orderPO.setCarPO(carPO);
			// 取车租赁点
			SitePO gsitePO = new SitePO();
			gsitePO.setSiteName(rs.getString("gsiteName"));
			orderPO.setGsitePO(gsitePO);
			// 还车租赁点
			SitePO rsitePO = new SitePO();
			rsitePO.setSiteName(rs.getString("rsiteName"));
			orderPO.setRsitePO(rsitePO);
			// 租赁收费套餐
			WhHoursPriceTypePO priceTypePO = new WhHoursPriceTypePO();
			priceTypePO.setTypeName(rs.getString("typeName"));
			orderPO.setWhHoursPriceTypePO(priceTypePO);
			// 租赁收费套餐
			MonthLeasePriceTypePO monthPriceTypePO = new MonthLeasePriceTypePO();
			monthPriceTypePO.setTypeName(rs.getString("typeName"));
			orderPO.setMonthPriceTypePO(monthPriceTypePO);
			// 优惠券
			CouponPo couponPo = new CouponPo();
			couponPo.setCouponName(rs.getString("couponName"));
			couponPo.setBalance(rs.getBigDecimal("coBalance"));
			couponPo.setCouponType(rs.getInt("couponType"));
			orderPO.setCouponPo(couponPo);
		}
		return orderPO;
	}

	/**
	 * 新增租赁订单
	 * 
	 * @param orderPO
	 * @return
	 */
	public int create(OrderPO orderPO) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.getJdbcTemplate().update(new OrderCreator(INSERT_SQL, orderPO),
				keyHolder);
		System.out.println(keyHolder.getKey().intValue() + "主键");
		return keyHolder.getKey().intValue();
	}

	/**
	 * 取车时修改订单内容
	 * 
	 * @param orderPO
	 * @return
	 */
	public boolean updateForGet(OrderPO orderPO) {
		int count = this.getJdbcTemplate().update(UPDATE_FOR_GET_SQL,
				orderPO.getRealityGetTime(), orderPO.getKmsForGet(),
				orderPO.getKmsForGet(), orderPO.getMenForGet(),
				orderPO.getRelityGetSiteId(), orderPO.getPriceTypeId(),
				orderPO.getPriceTypeName(), orderPO.getSurplusKmsForGet(),
				orderPO.getSchemingReturnTime(), orderPO.getId());
		return count > 0;
	}

	/**
	 * 还车时修改订单内容
	 * 
	 * @param orderPO
	 * @return
	 */
	public boolean updateForReturn(OrderPO orderPO) {
		int count = this.getJdbcTemplate().update(UPDATE_FOR_RETRUN_SQL,
				orderPO.getRealityReturnTime(),
				orderPO.getRelityReturnSiteId(), orderPO.getMenForReturn(),
				orderPO.getId());
		return count > 0;
	}

	/**
	 * 收费修改订单内容
	 * 
	 * @param orderPO
	 * @return
	 */
	public boolean updateForCharge(OrderPO orderPO) {
		int count = this.getJdbcTemplate()
				.update(UPDATE_FOR_CHARGE_SQL, orderPO.getOtherCost(),
						orderPO.getOtherDescribe(), orderPO.getTotalCost(),
						orderPO.getMenForCharge(), orderPO.getPayment(),
						orderPO.getTicket(), orderPO.getMaintenanceCost(),
						orderPO.getUseCost(), orderPO.getCouponId(),
						orderPO.getCouponCost(), orderPO.getId());
		return count > 0;
	}

	/**
	 * 核对时修改订单内容
	 * 
	 * @param orderPO
	 * @return
	 */
	public boolean checkForCharge(OrderPO orderPO) {
		int count = this.getJdbcTemplate().update(UPDATE_FOR_CHECK_SQL,
				orderPO.getCheckUser(), orderPO.getCheckDescribe(),
				orderPO.getTicket(), orderPO.getId());
		return count > 0;
	}

	/**
	 * 车辆调度、公务用车更改订单为收费状态（不收费）
	 * 
	 * @param orderPO
	 * @return
	 */
	public boolean updateForNoCharge(OrderPO orderPO) {
		return this.getJdbcTemplate().update(UPDATE_FOR_NO_CHARGE_SQL,
				orderPO.getMenForCharge(), orderPO.getId()) > 0;
	}

	/**
	 * 租赁现金收费
	 * 
	 * @param orderPO
	 * @return
	 */
	public boolean updateForCashCharge(OrderPO orderPO) {
		return this.getJdbcTemplate().update(UPDATE_FOR_CASH_CHARGE_SQL,
				orderPO.getMenForCharge(), orderPO.getPayment(),
				orderPO.getTicket(), orderPO.getUseCost(),
				orderPO.getTotalCost(), orderPO.getCouponId(),
				orderPO.getCouponCost(), orderPO.getId()) > 0;
	}

	/**
	 * 更改订单的租赁费用
	 * 
	 * @param orderPO
	 * @return
	 */
	public boolean updateCost(OrderPO orderPO) {
		int count = this.getJdbcTemplate().update(UPDATE_FOR_COST_SQL,
				orderPO.getUseCost(), orderPO.getTotalCost(),
				orderPO.getPerKms(), orderPO.getPerKmsCost(),
				orderPO.getOtherCost(), orderPO.getOtherDescribe(),
				orderPO.getMaintenanceCost(), orderPO.getId());
		return count > 0;
	}

	/**
	 * 取车审核修改订单内容
	 * 
	 * @param orderPO
	 * @return
	 */
	public boolean updateForGetConfirm(OrderPO orderPO) {
		int count = this.getJdbcTemplate().update(UPDATE_FOR_GETCONFIRM_SQL,
				orderPO.getGetCarStatus(), orderPO.getMenForGet(),
				orderPO.getKmsForGet(), orderPO.getKmsForGet(),
				orderPO.getSurplusKmsForGet(), orderPO.getOrderDescribe(),
				orderPO.getScheReturnTime(), orderPO.getId());
		return count > 0;
	}

	/**
	 * 还车审核修改订单内容
	 * 
	 * @param orderPO
	 * @return
	 */
	public boolean updateForReturnConfirm(OrderPO orderPO) {
		int count = this.getJdbcTemplate().update(UPDATE_FOR_RETURNCONFIRM_SQL,
				orderPO.getReturnCarConfirm(), orderPO.getMenForReturn(),
				orderPO.getReturnCarStatus(), orderPO.getKmsForReturn(),
				orderPO.getRelityReturnSiteId(),
				orderPO.getSurplusKmsForReturn(), orderPO.getId());
		return count > 0;
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
	 * 批量更新订单状态
	 * 
	 * @param orderStatus
	 * @param subOrderStatus
	 * @param oIds
	 * @return
	 */
	public boolean batchUpdateStaus(final OrderStatus orderStatus,
			final String subOrderStatus, final List<Integer> oIds) {
		int[] ids = this.getJdbcTemplate().batchUpdate(UPDATE_STATUS_SQL,
				new BatchPreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						ps.setString(1, orderStatus.name());
						ps.setString(2, subOrderStatus);
						ps.setString(3, "等待审查");
						ps.setInt(4, oIds.get(i));
					}

					@Override
					public int getBatchSize() {
						return oIds.size();
					}
				});
		return ids.length != oIds.size();
	}

	/**
	 * 查找用户未结束的租赁业务套餐
	 * 
	 * @param accountId
	 * @return
	 */
	public int findCuserUseOrders(Integer customerId, Integer merchantId) {
		return this.findCount(CUSTOMER_USE_ORDER_SQL, new Object[] {
				customerId, merchantId });
	}

	/**
	 * 查找单位会员未结束的租赁业务套餐
	 * 
	 * @param accountId
	 * @return
	 */
	public int findUnitCuserUseOrders(Integer id) {
		return this.findCount(CUSTOMER_UNIT_USE_ORDER_SQL, new Object[] { id });
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
	private static final String GET_BY_ORDERID_SQL = "select o.orderStatus,o.returnCarConfirm from `order` o where o.id=?";
	// 根据ID查找订单的SQL
	private static final String GET_SQL = "select o.*,c.carNumber,c.soc,ci.name,ci.phone,ci.idCard,gs.siteName as gsiteName,rs.siteName as rsiteName,a.cardNO,a.balance,a.id as accountId,"
			+ "a.moneyOfassure as accountMoneyOfassure,h.typeName,TIME_FORMAT(TIMEDIFF(o.realityReturnTime,o.realityGetTime),'%H:%i') useTime,o.couponId,co.balance as coBalance,co.couponName,co.couponType"
			+ " from `order` o INNER JOIN car c on o.carId=c.id INNER JOIN customer ci on o.customerId=ci.id left JOIN site gs on o.relityGetSiteId =gs.id"
			+ "	left JOIN site rs on o.relityReturnSiteId = rs.id "
			+ "	left JOIN coupon co on o.couponId = co.id "
			+ " inner JOIN vs_price_type h on (o.priceTypeId = h.id and o.priceTypeTableName = h.priceTypeTableName)"
			+ " left join account a on a.customerId=ci.id and a.merchantId=o.merchantId and a.`status`='正常' where o.orderType=1 and o.id=?";
	// 取车修改订单内容的SQL
	private static final String UPDATE_FOR_GET_SQL = "update `order` set realityGetTime=?,kmsForGet=?,kmsForReturn=?,menForGet=?,relityGetSiteId=?,priceTypeId=?,priceTypeName=?,surplusKmsForGet=?,schemingReturnTime=?,orderStatus='已取车',subOrderStatus='正常' where id=?";
	// 还车修改订单内容的SQL
	private static final String UPDATE_FOR_RETRUN_SQL = "update `order` set realityReturnTime=?,relityReturnSiteId=?,menForReturn=?,orderStatus='"
			+ OrderStatus.已还车.name()
			+ "',subOrderStatus='正常',returnCarConfirm='等待审查' where id=?";
	// 收费修改订单内容的SQL
	private static final String UPDATE_FOR_CHARGE_SQL = "update `order` set otherCost=?,otherDescribe=?,totalCost=?,orderStatus='"
			+ OrderStatus.已付费.name()
			+ "',subOrderStatus='正常',menForCharge=?,payment=?,ticket=?,maintenanceCost=?,useCost=?,couponId=?,couponCost=? where id=? and orderStatus='"
			+ OrderStatus.已还车.name() + "'";
	// 收费修改订单内容的SQL
	private static final String UPDATE_FOR_COST_SQL = "update `order` set useCost=?,totalCost=?,perKms=?,perKmsCost=?,otherCost=?,otherDescribe=?,maintenanceCost=? where id=?";
	// 取车审核修改订单内容的SQL
	private static final String UPDATE_FOR_GETCONFIRM_SQL = "update `order` set getCarStatus=?,menForGet=?,kmsForGet=?,kmsForReturn=?,surplusKmsForGet=?,orderDescribe=?,schemingReturnTime=? where id=?";
	// 还车审核修改订单内容的SQL
	private static final String UPDATE_FOR_RETURNCONFIRM_SQL = "update `order` set returnCarConfirm=?,menForReturn=?,returnCarStatus=?,kmsForReturn=?,relityReturnSiteId=?,surplusKmsForReturn=?,checkReturnTime=now() where id=?";
	// 修改订单状态的SQL
	private static final String UPDATE_STATUS_SQL = "update `order` set orderStatus=?,subOrderStatus=?,returnCarConfirm=? where id=?";
	// 查找用户未结束的租赁业务套餐的SQL
	private static final String CUSTOMER_USE_ORDER_SQL = "select count(*) from `order` o where o.customerId=? and o.merchantId=? and o.orderStatus not in('已付费','已取消')";
	// 查找单位会员未结束的租赁业务套餐的SQL
	private static final String CUSTOMER_UNIT_USE_ORDER_SQL = "select count(*) from `order` o INNER JOIN merchant_enterprise_customer c on (o.customerId=c.id AND o.customerTable='merchant_enterprise_customer')where c.id=? and o.orderStatus not in('已付费','已取消')";

	// 根据ID查找企业租车订单的SQL
	private static final String GET_UNIT_SQL = "select o.*,c.carNumber,c.soc,ci.enterpriseName,ci.contactPerson,ci.contactPhone,ci.balance,"
			+ " gs.siteName as gsiteName,rs.siteName as rsiteName,o.couponId,co.balance as coBalance,co.couponName,co.couponType,"
			+ " h.typeName,TIME_FORMAT(TIMEDIFF(o.realityReturnTime,o.realityGetTime),'%H:%i') useTime"
			+ " from `order` o INNER JOIN car c on o.carId=c.id INNER JOIN merchant_enterprise_customer ci "
			+ " on o.customerId=ci.id left JOIN site gs on o.relityGetSiteId =gs.id"
			+ "	left JOIN site rs on o.relityReturnSiteId = rs.id "
			+ "	left JOIN coupon co on o.couponId = co.id "
			+ " inner JOIN vs_price_type h on (o.priceTypeId = h.id and o.priceTypeTableName = h.priceTypeTableName) where o.orderType=2 and o.id=?";
	// 车辆调度、公务用车更改订单为收费状态（不收费）的SQL
	private static final String UPDATE_FOR_NO_CHARGE_SQL = "update `order` set orderStatus='"
			+ OrderStatus.已付费.name()
			+ "',"
			+ "subOrderStatus='正常',menForCharge=? where id=? and orderStatus='"
			+ OrderStatus.已还车.name() + "'";
	// 车辆调度、公务用车更改订单为收费状态的SQL
	private static final String UPDATE_FOR_CASH_CHARGE_SQL = "update `order` set orderStatus='"
			+ OrderStatus.已付费.name()
			+ "',"
			+ "subOrderStatus='正常',menForCharge=?,payment=?,ticket=?,useCost=?,totalCost=?,couponId=?,couponCost=?  where id=? and orderStatus='"
			+ OrderStatus.已还车.name() + "'";
	// 核对时修改订单内容的SQL
	private static final String UPDATE_FOR_CHECK_SQL = "update `order` set subOrderStatus='已入账',checkUser=?,checkDescribe=?,ticket=? where id=? and orderStatus='"
			+ OrderStatus.已付费.name() + "'";

}

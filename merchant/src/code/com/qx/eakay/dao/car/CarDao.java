package com.qx.eakay.dao.car;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.qx.common.base.PageResults;
import com.qx.common.dao.BaseDao;
import com.qx.common.sql.JSONSqlMapping;
import com.qx.eakay.db.creator.CarCreator;
import com.qx.eakay.db.extractor.CarExtractor;
import com.qx.eakay.model.car.CarPO;
import com.qx.eakay.model.car.CarPO.CarStatus;

/**
 * 车辆数据库操作
 * 
 * @author Administrator
 * 
 */
@Repository
public class CarDao extends BaseDao {

	@Resource(name = "car_select_sql")
	private JSONSqlMapping carSelectSQL;
	
	@Resource(name = "ONLY_CAR_SELECT_SQL")
	private JSONSqlMapping onlyCarSelectSQL;

	/**
	 * 分页查找车辆列表（关联order表）
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findCarsPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return this.findPageByJSONSqlMapping(carSelectSQL, jsonObject,
				pageSize, iDisplayStart);
	}

	/**
	 * 分页查询车辆状态信息
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findOnlyCarsPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return this.findPageByJSONSqlMapping(onlyCarSelectSQL, jsonObject,
				pageSize, iDisplayStart);
	}
	
	/**
	 * 条件查找车辆列表
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findCars(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(carSelectSQL, jsonObject);
	}

	/**
	 * 创建车辆
	 * 
	 * @param carPO
	 * @return
	 */
	public int create(CarPO carPO) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.getJdbcTemplate().update(new CarCreator(INSERT_SQL, carPO),
				keyHolder);
		System.out.println(keyHolder.getKey().intValue() + "主键");
		return keyHolder.getKey().intValue();
	}

	/**
	 * 修改车辆
	 * 
	 * @param carPO
	 * @return
	 */
	public boolean update(CarPO carPO) {
		int count = this.getJdbcTemplate().update(UPDATE_SQL,
				carPO.getCarNumber(), carPO.getCarTypeId(), carPO.getColor(),
				carPO.getDescribe(), carPO.getChargeDuration(),
				carPO.getBigImgs(), carPO.getLittleImgs(), carPO.getBigIcon(),
				carPO.getLittleIcon(), carPO.getCurSiteId(),
				carPO.getNsuranceRange(), carPO.getCheckYearDate(),
				carPO.getVin(), carPO.getId());
		return count > 0;
	}

	/**
	 * 根据ID查找车辆
	 * 
	 * @param id
	 * @return
	 */
	public CarPO get(Integer id) {
		return this.getJdbcTemplate().query(GET_SQL, new Object[] { id },
				new CarExtractor());
	}

	public CarPO getByNumber(String carNumber) {
		return this.getJdbcTemplate().query(GET_BY_CARNUMBER,
				new Object[] { carNumber }, new CarExtractor());
	}

	/**
	 * 根据车辆识别号查找车辆
	 * 
	 * @param vin
	 * @return
	 */
	public CarPO getByVin(String vin) {
		return this.getJdbcTemplate().query(GET_BY_VIN, new Object[] { vin },
				new CarExtractor());
	}

	/**
	 * 删除车辆
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(Integer id) {
		int count = this.getJdbcTemplate().update(DELETE_SQL, id);
		return count > 0;
	}

	@Resource(name = "car_no_device_sql")
	private JSONSqlMapping noDeviceCars;

	/**
	 * 查找没有安装车载设备的车辆
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findNoDeviceCars(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(noDeviceCars, jsonObject);
	}

	@Resource(name = "car_position_select_sql")
	private JSONSqlMapping carPointSelectSQL;

	/**
	 * 条件搜索车辆坐标位置
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findCarPoints(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(carPointSelectSQL, jsonObject);
	}

	/**
	 * 查找没有安装车载设备的车辆
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findPriceTypeCars(JSONObject jsonObject) {
		String sql = "select c.id,c.carNumber,c.curSiteId,cpr.priceTypeId as priceId"
				+ " from car c LEFT JOIN car_price_type_relation cpr on cpr.carId=c.id "
				+ "and cpr.priceTypeTableName=? and cpr.priceTypeId=?"
				+ " where 1=1 ";
		Object[] args = new Object[] {jsonObject.get("tableName"), jsonObject.get("priceTypeId") };
		if (jsonObject.get("merchantId") != null) {
			args = ArrayUtils.add(args, args.length,
					jsonObject.get("merchantId"));
			sql += " and c.merchantId =? ";
		}
		if (jsonObject.get("siteId") != null) {
			args = ArrayUtils.add(args, args.length, jsonObject.get("siteId"));
			sql += " and c.curSiteId=?";
		}
		sql += " order by c.id desc";
		return this.findListBySQL(sql, args);
	}

	/**
	 * 更改车辆当前状态
	 * 
	 * @param status
	 * @param id
	 * @return
	 */
	public boolean updateStatus(CarStatus status, Integer id) {
		int count = this.getJdbcTemplate().update(UPDATE_STATUS_SQL,
				status.name(), id);
		return count > 0;
	}
	
	/**
	 * 更改车辆当前状态和描述
	 * 
	 * @param status
	 * @param id
	 * @return
	 */
	public boolean updateStatusAndDescribe(CarPO carPO, Integer id) {
		int count = this.getJdbcTemplate().update(UPDATE_STATUS_DESCRIBE_SQL,
				carPO.getStatus(),carPO.getDescribe(), id);
		return count > 0;
	}

	/**
	 * 批量修改车辆的状态
	 * 
	 * @param status
	 * @param carIds
	 * @return
	 */
	public boolean batchUpdateStatus(final CarStatus status,
			final List<Integer> carIds) {
		int[] ids = this.getJdbcTemplate().batchUpdate(UPDATE_STATUS_SQL,
				new BatchPreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						ps.setString(1, status.name());
						ps.setInt(2, carIds.get(i));
					}

					@Override
					public int getBatchSize() {
						return carIds.size();
					}
				});
		return ids.length != carIds.size();
	}

	/**
	 * 更改车辆当前所在租赁点
	 * 
	 * @param siteId
	 *            当前所在租赁点ID
	 * @param status
	 *            当前车辆状态
	 * @param kms
	 *            当前码表公里数
	 * @param id
	 *            车辆ID
	 * @return
	 */
	public boolean updateCurSite(Integer siteId, CarStatus status, Float kms,Float soc,
			Integer id) {
		int count = this.getJdbcTemplate().update(UPDATE_CURSITE_SQL, siteId,
				status.name(), kms,soc, id);
		return count > 0;
	}

	@Resource(name = "car_use_statistics_sql")
	private JSONSqlMapping carUseStatisticsSQL;

	/**
	 * 分页查找车辆使用统计
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findCarUsesPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return this.findPageByJSONSqlMapping(carUseStatisticsSQL, jsonObject,
				pageSize, iDisplayStart);
	}

	@Resource(name = "car_device_monitor_select_sql")
	private JSONSqlMapping carDevicesSelectSQL;

	/**
	 * 查询车辆的设备状态
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findCarDevices(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(carDevicesSelectSQL, jsonObject);
	}

	/**
	 * 条件查找车辆使用统计
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findCarUses(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(carUseStatisticsSQL, jsonObject);
	}

	/**
	 * 批量发布车辆
	 * 
	 * @param flag
	 * @param carIds
	 * @return
	 */
	public boolean batchPublish(final String flag, final List<Integer> carIds) {
		int[] ids = this.getJdbcTemplate().batchUpdate(UPDATE_FLAG_SQL,
				new BatchPreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						ps.setString(1, flag);
						ps.setInt(2, carIds.get(i));
					}

					@Override
					public int getBatchSize() {
						return carIds.size();
					}
				});
		return ids.length == carIds.size();
	}

	// 插入车辆SQL
	private static final String INSERT_SQL = "insert into car (carNumber,carTypeId,color,`describe`,chargeDuration,bigImgs,littleImgs,bigIcon,littleIcon,createdTime,belongSiteId,curSiteId,merchantId,nsuranceRange,checkYearDate,flag,vin) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,'编辑',?)";
	// 根据车牌查找车辆的SQL
	private static final String GET_BY_CARNUMBER = "select *,'' siteName from  car c where carNumber=?";
	// 根据车牌查找车辆的SQL
	private static final String GET_BY_VIN = "select *,'' as siteName from  car c  where vin=?";
	// 根据ID查找车辆的SQL
	private static final String GET_SQL = "select c.*,s.siteName from  car c INNER JOIN site s on c.curSiteId =s.id where c.id=?";
	// 删除车辆的SQL
	private static final String DELETE_SQL = "delete from  car where id=?";
	// 修改车辆状态的SQL
	private static final String UPDATE_STATUS_SQL = "update car set status=? where id=?";
	// 修改车辆当前所在租赁点的SQL
	private static final String UPDATE_CURSITE_SQL = "update car set curSiteId=?,status=?, kms=?,surplusKms=? where id=?";
	// 修改车辆当前所在租赁点的SQL
	private static final String UPDATE_SQL = "update car set carNumber=?,carTypeId=?,color=?,`describe`=?,chargeDuration=?,bigImgs=?,littleImgs=?,bigIcon=?,littleIcon=?,curSiteId=?,nsuranceRange=?,checkYearDate=?,vin=? where id=?";
	// 修改车辆状态和描述的SQL
	private static final String UPDATE_STATUS_DESCRIBE_SQL = "update car set status=?,`describe`=? where id=?";
	// 修改车辆状态的SQL
	private static final String UPDATE_FLAG_SQL = "update car set flag=? where id=?";


}

package com.qx.eakay.dao.car;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.qx.common.base.PageResults;
import com.qx.common.dao.BaseDao;
import com.qx.common.sql.JSONSqlMapping;
import com.qx.eakay.db.creator.CarBatteryCreator;
import com.qx.eakay.db.extractor.CarBatteryExtractor;
import com.qx.eakay.model.car.CarBatteryPO;

@Repository
public class CarBatteryDao extends BaseDao {

	@Resource(name = "car_battery_select_sql")
	private JSONSqlMapping batterySelectSQL;

	/**
	 * 分页查找车辆电池列表
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findBatteryPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return this.findPageByJSONSqlMapping(batterySelectSQL, jsonObject,
				pageSize, iDisplayStart);
	}

	/**
	 * 条件查找车辆电池列表
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findBatterys(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(batterySelectSQL, jsonObject);
	}

	/**
	 * 创建车辆
	 * 
	 * @param carPO
	 * @return
	 */
	public int create(CarBatteryPO batteryPO) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.getJdbcTemplate().update(
				new CarBatteryCreator(INSERT_SQL, batteryPO), keyHolder);
		System.out.println(keyHolder.getKey().intValue() + "主键");
		return keyHolder.getKey().intValue();
	}

	/**
	 * 根据ID查找电池
	 * 
	 * @param id
	 * @return
	 */
	public CarBatteryPO get(Integer id) {
		return this.getJdbcTemplate().query(GET_SQL, new Object[] { id },
				new CarBatteryExtractor());
	}

	/**
	 * 根据VIN码查找电池
	 * 
	 * @param vin
	 * @return
	 */
	public CarBatteryPO findByVIN(String vin) {
		return this.getJdbcTemplate().query(GET_BY_VIN, new Object[] { vin },
				new CarBatteryExtractor());
	}

	/**
	 * 删除车辆电池
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(Integer id) {
		int count = this.getJdbcTemplate().update(DELETE_SQL, id);
		return count > 0;
	}

	/**
	 * 更改电池信息
	 * 
	 * @param batteryPO
	 * @return
	 */
	public boolean update(CarBatteryPO batteryPO) {
		int count = this.getJdbcTemplate().update(UPDATE_SQL,
				batteryPO.getCarId(), batteryPO.getVin(),
				batteryPO.getBatteryType(), batteryPO.getMaxVoltag(),
				batteryPO.getMaxCurrent(), batteryPO.getMinVoltage(),
				batteryPO.getMinVoltage(), batteryPO.getMinCurrent(),
				batteryPO.getMinTemp(), batteryPO.getId());
		return count > 0;
	}

	// 插入车辆电池SQL
	private static final String INSERT_SQL = "insert into car_battery (carId,VIN,batteryType,maxVoltag,maxCurrent,maxTemp,minVoltage,minCurrent,minTemp,merchantId,createdTime) values (?,?,?,?,?,?,?,?,?,?,?)";
	// 根据车牌查找车辆电池的SQL
	private static final String GET_BY_VIN = "select b.*,'' carNumber from  car_battery b where b.vin=?";
	// 根据ID查找车辆电池的SQL
	private static final String GET_SQL = "select b.*,c.carNumber from  car_battery b LEFT JOIN car c on c.id=b.carId where b.id=?";
	// 删除车辆电池的SQL
	private static final String DELETE_SQL = "delete from  car_battery where id=?";
	// 修改车辆电池的SQL
	private static final String UPDATE_SQL = "update car_battery set carId=?,VIN=?,batteryType=?,maxVoltag=?,maxCurrent=?,maxTemp=?,minVoltage=?,minCurrent=?,minTemp=? where id=?";

}

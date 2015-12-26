package com.qx.eakay.dao.car;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.qx.common.base.PageResults;
import com.qx.common.dao.BaseDao;
import com.qx.common.sql.JSONSqlMapping;
import com.qx.eakay.db.creator.CarSimCreator;
import com.qx.eakay.db.extractor.CarDeviceSimExtractor;
import com.qx.eakay.model.car.CarDevice;
import com.qx.eakay.model.car.SimPO;

/**
 * 车载设备
 * 
 * @author jixf
 * @date 2015年7月6日
 */
@Repository
public class CarDeviceDao extends BaseDao {

	@Resource(name = "car_device_select_sql")
	private JSONSqlMapping selectSql;

	@Resource(name = "car_device_sim_select_sql")
	private JSONSqlMapping selectSimSql;

	/**
	 * 分页查询车辆设备
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findDevicesPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return this.findPageByJSONSqlMapping(selectSql, jsonObject, pageSize,
				iDisplayStart);
	}

	/**
	 * 条件搜索车载设备列表
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findDevices(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(selectSql, jsonObject);
	}

	/**
	 * 分页查询车载设备卡信息
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findSimPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return this.findPageByJSONSqlMapping(selectSimSql, jsonObject,
				pageSize, iDisplayStart);
	}

	/**
	 * 条件搜索车载设备卡列表
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findSims(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(selectSimSql, jsonObject);
	}

	/**
	 * 修改车辆的ID
	 * 
	 * @param carId
	 * @param id
	 * @return
	 */
	public boolean updateCarId(Integer carId, String carTableName, Integer id) {
		int count = this.getJdbcTemplate().update(UPDATE_CARID_SQL, carId,
				carTableName, id);
		return count > 0;
	}

	/**
	 * 查找设备编号
	 * 
	 * @param id
	 *            carId
	 * @return
	 */
	public CarDevice carDeviceByCarId(Integer id) {
		List<CarDevice> list = this.getJdbcTemplate().query(SELECT_CARID_SQL,
				new Object[] { id }, new RowMapper<CarDevice>() {
					@Override
					public CarDevice mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						CarDevice customer = new CarDevice();
						customer.setId(rs.getInt("id"));
						customer.setDeviceNO(rs.getString("deviceNO"));
						customer.setStatus(rs.getString("status"));
						customer.setSurplusKms(rs.getFloat("surplusKms"));
						customer.setCurKms(rs.getFloat("curKms"));
						return customer;
					}
				});
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 保存新增车载设备卡
	 * 
	 * @param simPO
	 * @return
	 */
	public boolean saveAdd(SimPO simPO) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.getJdbcTemplate().update(new CarSimCreator(INSERT_SIM_SQL, simPO),
				keyHolder);
		return keyHolder.getKey().intValue() > 0;
	}

	/**
	 * 查找车载设备卡
	 * 
	 * @param simCard
	 * @return
	 */
	public SimPO findBySimCard(String simCard) {
		return this.getJdbcTemplate().query(SELECT_BY_SIMCARD_SQL,
				new Object[] { simCard }, new CarDeviceSimExtractor());
	}

	/**
	 * 查找车载设备卡
	 * 
	 * @param id
	 * @return
	 */
	public SimPO findSim(Integer id) {
		return this.getJdbcTemplate().query(GET_SQL, new Object[] { id },
				new CarDeviceSimExtractor());
	}

	/**
	 * 删除车载设备卡
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteSim(Integer id) {
		return this.getJdbcTemplate().update(DELETE_SIM_SQL, id) > 0;
	}

	// 修改设备所在车辆的SQL
	private static final String UPDATE_CARID_SQL = "update car_device set carId=?,carTableName=? where id=?";
	// 查找设备所在车辆的SQL
	private static final String SELECT_CARID_SQL = "SELECT id,deviceNO,status,surplusKms,curKms from car_device where carId=? and carTableName = 'car'";
	// 新增车载设备卡的SQL
	private static final String INSERT_SIM_SQL = "insert into sim (simCard,facilitator,flowOfMonth,deviceId,status,merchantId,iccid) values (?,?,?,0,'正常',?,?)";
	// 根据ID查找车载设备卡的SQL
	private static final String GET_SQL = " select s.*,d.deviceNO from sim s left join car_device d on (s.deviceId=d.id) where s.id=?";
	// 根据simCard查找车载设备卡的SQL
	private static final String SELECT_BY_SIMCARD_SQL = "select s.*,d.deviceNO from sim s left join car_device d on (s.deviceId=d.id) where s.simCard=? and s.`status`!='删除'";
	// 删除车载设备卡的SQL
	private static final String DELETE_SIM_SQL = "update sim set status='删除',deviceId=0 where id=?";
	// 修改车载设备卡的SQL
	private static final String UPDATE_SIM_SQL = "update sim set simCard=?,facilitator=?,flowOfMonth=?,merchantId=?,iccid=? where id=?";
	// 修改设备的安装设备卡的SQL
	private static final String UPDATE_DEVICE_SIM_SQL = "update sim set deviceId=? where id=?";
	// 重置设备卡设备id的SQL
	private static final String UPDATE_DEVICE_ID_SQL = "update sim set deviceId=0 where deviceId=?";

	/**
	 * 修改车载设备卡
	 * 
	 * @param simPO
	 * @return
	 */
	public boolean saveEdite(SimPO simPO) {
		return this.getJdbcTemplate().update(UPDATE_SIM_SQL,
				simPO.getSimCard(), simPO.getFacilitator(),
				simPO.getFlowOfMonth(), simPO.getMerchantId(),
				simPO.getIccid(), simPO.getId()) > 0;
	}

	/**
	 * 修改设备的安装设备卡
	 * 
	 * @param id
	 * @param deviceId
	 * @return
	 */
	public boolean updateSim(Integer id, Integer deviceId) {
		return this.getJdbcTemplate().update(UPDATE_DEVICE_SIM_SQL, deviceId,
				id) > 0;
	}

	/**
	 * 重置设备卡设备id
	 * 
	 * @param deviceId
	 */
	public boolean updateDeviceId(Integer deviceId) {
		return this.getJdbcTemplate().update(UPDATE_DEVICE_ID_SQL, deviceId) > 0;
	}

	/**
	 * 修改车载设备续航里程
	 * 
	 * @param surplusKmss
	 * @param deviceIds
	 * @return
	 */
	public boolean batchCountSurplusKms(final List<Integer> surplusKmss,
			final List<Integer> deviceIds) {
		int[] ids = this.getJdbcTemplate().batchUpdate(COUNT_SURPLUSKMS_SQL,
				new BatchPreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						ps.setInt(1, surplusKmss.get(i));
						ps.setInt(2, deviceIds.get(i));
					}

					@Override
					public int getBatchSize() {
						return deviceIds.size();
					}
				});
		return ids.length != deviceIds.size();

	}

	/**
	 * 修改车辆续航里程,soc
	 * 
	 * @param carIds
	 * @param surplusKmss
	 * @param socs
	 * @return 
	 */
	public boolean batchUpdateCar(final List<Integer> carIds, final List<Float> surplusKmss,
			final List<Float> socs) {

		int[] ids = this.getJdbcTemplate().batchUpdate(UPDATE_CAR_SQL,
				new BatchPreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						ps.setFloat(1, surplusKmss.get(i));
						ps.setFloat(2, socs.get(i));
						ps.setInt(3, carIds.get(i));
					}

					@Override
					public int getBatchSize() {
						return carIds.size();
					}
				});
		return ids.length != carIds.size();

	}
	

	// 修改车载设备续航里程的SQL
	private static final String COUNT_SURPLUSKMS_SQL = "update car_device set surplusKms=? where id=?";
	// 修改车辆续航里程,soc的SQL
	private static final String UPDATE_CAR_SQL = "update car set surplusKms=?,soc=? where id=?";
	
}

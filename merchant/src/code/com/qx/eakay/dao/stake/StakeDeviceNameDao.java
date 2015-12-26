package com.qx.eakay.dao.stake;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.qx.common.base.PageResults;
import com.qx.common.dao.BaseDao;
import com.qx.common.sql.JSONSqlMapping;
import com.qx.eakay.db.creator.StakeDeviceCreator;
import com.qx.eakay.db.extractor.StakeDevicenExtractor;
import com.qx.eakay.model.stake.StakeDeviceNamePO;

/**
 * 
 * @author jixf
 * @date 2015年7月15日
 */
/**
 * @author Administrator
 * 
 */
@Repository
public class StakeDeviceNameDao extends BaseDao {

	@Resource(name = "stake_device_select_sql")
	private JSONSqlMapping deviceSelectSQL;

	/**
	 * 分页查找充电设备列表
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findDevicesPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return this.findPageByJSONSqlMapping(deviceSelectSQL, jsonObject,
				pageSize, iDisplayStart);
	}

	/**
	 * 条件查找充电设备列表
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findDevices(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(deviceSelectSQL, jsonObject);
	}

	/**
	 * 创建充电设备
	 * 
	 * @param carPO
	 * @return
	 */
	public int create(StakeDeviceNamePO deviceNamePO) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.getJdbcTemplate().update(
				new StakeDeviceCreator(INSERT_SQL, deviceNamePO), keyHolder);
		System.out.println(keyHolder.getKey().intValue() + "主键");
		return keyHolder.getKey().intValue();
	}

	/**
	 * 批量创建充电设备(1托30)
	 * 
	 * @param ports
	 * @return
	 */
	public boolean batchCreate(final StakeDeviceNamePO stake,
			final Integer[] arr) {
		int[] ids = this.getJdbcTemplate().batchUpdate(INSERT_SQL,
				new BatchPreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						ps.setString(1, stake.getSiteCode());
						ps.setString(2, stake.getAreaCode());
						ps.setString(3, stake.getChargePort());
						ps.setString(4, stake.getDeviceNo());
						ps.setString(5, stake.getDeviceTypeName());
						ps.setString(6, stake.getManufacturer());
						ps.setString(7, stake.getRegionA());
						ps.setString(8, stake.getRegionB());
						ps.setString(9, stake.getDeviceKind());
						ps.setInt(10, stake.getDeviceType());
						ps.setString(11, stake.getChargerType());
						ps.setString(12, stake.getSpec());
						ps.setInt(13, stake.getDeviceNum());
						ps.setString(14, stake.getFactoryNo() + "-" + (i + 1));
						ps.setTimestamp(15,
								new Timestamp(System.currentTimeMillis()));
						ps.setString(16, stake.getCanAddress());
						ps.setString(17, stake.getIpAddress());
						ps.setString(18, stake.getSafetyLevle());
						ps.setFloat(19, stake.getFixedVoltage());
						ps.setInt(20, stake.getServerPort());
						ps.setInt(21, stake.getClientPort());
						ps.setString(22, stake.getStatus());
						ps.setBigDecimal(23, stake.getDeviceVersion());
						ps.setString(24, stake.getChargeStatus());
						ps.setString(25, stake.getFlag());
						ps.setInt(26, stake.getMerchantId());
						ps.setFloat(27, stake.getLng());
						ps.setFloat(28, stake.getLat());
						ps.setString(29, stake.getAddress());
						ps.setString(30, stake.getImgUrl());
						ps.setString(31, stake.getIsAllowParking());
						ps.setString(32, stake.getParkType());
						ps.setFloat(33, stake.getDevicePower());
						ps.setString(34, stake.getFactoryNo());
						ps.setString(35, stake.getDeviceTypeNo());
						ps.setString(36, stake.getNameplate());
					}

					@Override
					public int getBatchSize() {
						return arr.length;
					}
				});
		return ids.length == arr.length;
	}

	/**
	 * 根据ID查找充电设备
	 * 
	 * @param id
	 * @return
	 */
	public StakeDeviceNamePO get(Integer id) {
		return this.getJdbcTemplate().query(GET_SQL, new Object[] { id },
				new StakeDevicenExtractor());
	}

	/**
	 * 根据设备编号码查找充电设备
	 * 
	 * @param factoryNo
	 * @return
	 */
	public StakeDeviceNamePO findByFactoryNo(String factoryNo) {
		return this.getJdbcTemplate().query(GET_BY_FACTORYNO,
				new Object[] { factoryNo }, new StakeDevicenExtractor());
	}

	/**
	 * 根据设备铭牌查找充电设备
	 * 
	 * @param nameplate
	 * @return
	 */
	public StakeDeviceNamePO findByNameplate(String nameplate) {
		return this.getJdbcTemplate().query(GET_BY_NAMEPLATE,
				new Object[] { nameplate }, new StakeDevicenExtractor());
	}

	/**
	 * 根据设备名称查找充电设备
	 * 
	 * @param deviceNo
	 * @return
	 */
	public StakeDeviceNamePO findByDeviceNo(String deviceNo) {
		return this.getJdbcTemplate().query(GET_BY_DEVICENO,
				new Object[] { deviceNo }, new StakeDevicenExtractor());
	}

	/**
	 * 删除充电设备
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(Integer id) {
		int count = this.getJdbcTemplate().update(DELETE_SQL, id);
		return count > 0;
	}

	/**
	 * 更改充电设备
	 * 
	 * @param batteryPO
	 * @return
	 */
	public boolean update(StakeDeviceNamePO deviceNamePO) {
		int count = this.getJdbcTemplate().update(UPDATE_SQL,
				deviceNamePO.getSiteCode(), deviceNamePO.getAreaCode(),
				deviceNamePO.getChargePort(), deviceNamePO.getDeviceNo(),
				deviceNamePO.getDeviceTypeName(),
				deviceNamePO.getManufacturer(), deviceNamePO.getFactoryNo(),
				deviceNamePO.getAddress(), deviceNamePO.getLng(),
				deviceNamePO.getLat(), deviceNamePO.getImgUrl(),
				deviceNamePO.getIsAllowParking(), deviceNamePO.getParkType(),
				deviceNamePO.getStatus(), deviceNamePO.getFixedVoltage(),
				deviceNamePO.getDevicePower(), deviceNamePO.getDeviceKind(),
				deviceNamePO.getDeviceType(), deviceNamePO.getFactoryNo(),
				deviceNamePO.getNameplate(), deviceNamePO.getId());
		return count > 0;
	}

	// 插入充电设备SQL
	private static final String INSERT_SQL = "insert into stake_deviceName (site_code,area_code,chargePort,"
			+ "deviceNo,deviceTypeName,manufacturer,regionA,regionB,deviceKind,deviceType,chargerType,spec,deviceNum,"
			+ "factoryNo,startTime,canAddress,ipAddress,safetyLevle,fixedVoltage,serverPort,clientPort,status,deviceVersion,"
			+ "chargeStatus,flag,merchantId,lng,lat,address,imgUrl,isAllowParking,parkType,devicePower,groupNo,deviceTypeNo,nameplate) "
			+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	// 根据设备编号查找充电设备的SQL
	private static final String GET_BY_FACTORYNO = "select *,'' siteName,'' area_name from stake_deviceName where factoryNo=?";
	// 根据铭牌查找充电设备的SQL
	private static final String GET_BY_NAMEPLATE = "select *,'' siteName,'' area_name from stake_deviceName where groupNo = factoryNo and nameplate=?";
	// 根据设备名称查找充电设备的SQL
	private static final String GET_BY_DEVICENO = "select *,'' siteName,'' area_name from stake_deviceName where groupNo = factoryNo and deviceNo=?";
	// 根据ID查找充电设备的SQL
	private static final String GET_SQL = "select *,'' siteName,'' area_name from stake_deviceName where id=?";
	// 根据充电套餐查找充电设备的SQL
	private static final String SELECT_PRICE_STAKE_SQL = "select *,'' siteName,'' area_name from stake_devicename where priceid = ?";
	// 删除充电设备的SQL
	private static final String DELETE_SQL = "update stake_deviceName set flag='删除' where id=?";
	// 修改充电设备的SQL
	private static final String UPDATE_SQL = "update stake_deviceName set site_code =?,area_code=?,chargePort=?,deviceNo=?,"
			+ "deviceTypeName=?,manufacturer=?,factoryNo=?,address=?,lng=?,lat=?,imgUrl=?,isAllowParking=?,parkType=?,"
			+ "status=?,fixedVoltage=?,devicePower=?,deviceKind=?,deviceType=?,groupNo=?,nameplate=? where id =?";

	// 单独修改子充电桩的SQL
	private static final String UPDATE_CHILD_SQL = "update stake_deviceName set lng=?,lat=?,fixedVoltage=?,"
			+ "devicePower=?,status=?,isAllowParking=?,imgUrl=? where id=?";
	// 修改子充电桩的SQL
	private static final String UPDATE_CHILD_SQL1 = "update stake_deviceName set site_code =?,area_code=?,chargePort=?,deviceNo=?,"
			+ "deviceTypeName=?,manufacturer=?,factoryNo=?,address=?,imgUrl=?,isAllowParking=?,parkType=?,"
			+ "status=?,fixedVoltage=?,devicePower=?,deviceKind=?,deviceType=?,groupNo=?,nameplate=? where id =?";
	// 修改充电设备的SQL
	private static final String UPDATE_All_SQL = "update stake_deviceName set site_code =?,area_code=?,chargePort=?,deviceNo=?,"
			+ "deviceTypeName=?,manufacturer=?,address=?,imgUrl=?,isAllowParking=?,parkType=?,"
			+ "status=?,fixedVoltage=?,devicePower=?,deviceKind=?,deviceType=?,nameplate=? where groupNo = ?";
	// 根据ID查找充电设备的SQL(子桩子修改页面)
	private static final String GET_CHILD_SQL = "select d.*,s.siteName,ss.area_name from stake_deviceName d "
			+ "left join site s on (d.site_code = s.id) left join stake_sitearea ss on (d.area_code = ss.area_code) where d.id=?";
	// 删除充电设备的SQL
	private static final String DELETE_All_SQL = "update stake_deviceName set flag='删除' where groupNo=?";
	// 删除子充电设备的SQL
	private static final String DELETE_CHILD_SQL = "delete from stake_deviceName where groupNo!=factoryNo and groupNo=?";
	// 查询子桩子id
	private static final String GET_All_SQL = "select id from stake_deviceName where groupNo != factoryNo and groupNo=?";

	/**
	 * 根据ID查找充电设备(子桩子修改页面)
	 * 
	 * @param id
	 * @return
	 */
	public StakeDeviceNamePO getChild(Integer id) {
		return this.getJdbcTemplate().query(GET_CHILD_SQL, new Object[] { id },
				new StakeDevicenExtractor());
	}

	/**
	 * 修改子充电桩
	 * 
	 * @param deviceNamePO
	 * @return
	 */
	public boolean updateChild(StakeDeviceNamePO deviceNamePO) {
		return this.getJdbcTemplate().update(UPDATE_CHILD_SQL,
				deviceNamePO.getLng(), deviceNamePO.getLat(),
				deviceNamePO.getFixedVoltage(), deviceNamePO.getDevicePower(),
				deviceNamePO.getStatus(), deviceNamePO.getIsAllowParking(),
				deviceNamePO.getImgUrl(), deviceNamePO.getId()) > 0;
	}

	/**
	 * 批量修改主、子充电桩(根据groupNo)
	 * 
	 * @param deviceNamePO
	 * @return
	 */
	public boolean batchUpdate(StakeDeviceNamePO deviceNamePO) {
		return this.getJdbcTemplate().update(UPDATE_All_SQL,
				deviceNamePO.getSiteCode(), deviceNamePO.getAreaCode(),
				deviceNamePO.getChargePort(), deviceNamePO.getDeviceNo(),
				deviceNamePO.getDeviceTypeName(),
				deviceNamePO.getManufacturer(), deviceNamePO.getAddress(),
				deviceNamePO.getImgUrl(), deviceNamePO.getIsAllowParking(),
				deviceNamePO.getParkType(), deviceNamePO.getStatus(),
				deviceNamePO.getFixedVoltage(), deviceNamePO.getDevicePower(),
				deviceNamePO.getDeviceKind(), deviceNamePO.getDeviceType(),
				deviceNamePO.getNameplate(), deviceNamePO.getFactoryNo()) > 0;
	}

	/**
	 * 删除充电桩（根据groupNo）
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteOther(String groupNo) {
		return this.getJdbcTemplate().update(DELETE_All_SQL, groupNo) > 0;
	}

	/**
	 * 获取充电桩（根据groupNo）
	 * 
	 * @param groupNo
	 * @return
	 */
	public List<Map<String, Object>> getByGroupNo(String groupNo) {
		return this.getJdbcTemplate().queryForList(GET_All_SQL, groupNo);
	}

	/**
	 * 批量修改子充电桩
	 * 
	 * @param deviceNamePO
	 * @param stakeIds
	 * @return
	 */
	public boolean batchUpdateChild(final StakeDeviceNamePO deviceNamePO,
			final Integer[] stakeIds) {
		int[] ids = this.getJdbcTemplate().batchUpdate(UPDATE_CHILD_SQL1,
				new BatchPreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						ps.setString(1, deviceNamePO.getSiteCode());
						ps.setString(2, deviceNamePO.getAreaCode());
						ps.setString(3, deviceNamePO.getChargePort());
						ps.setString(4, deviceNamePO.getDeviceNo());
						ps.setString(5, deviceNamePO.getDeviceTypeName());
						ps.setString(6, deviceNamePO.getManufacturer());
						ps.setString(7, deviceNamePO.getFactoryNo() + "-"
								+ (i + 1));
						ps.setString(8, deviceNamePO.getAddress());
						ps.setString(9, deviceNamePO.getImgUrl());
						ps.setString(10, deviceNamePO.getIsAllowParking());
						ps.setString(11, deviceNamePO.getParkType());
						ps.setString(12, deviceNamePO.getStatus());
						ps.setFloat(13, deviceNamePO.getFixedVoltage());
						ps.setFloat(14, deviceNamePO.getDevicePower());
						ps.setString(15, deviceNamePO.getDeviceKind());
						ps.setInt(16, deviceNamePO.getDeviceType());
						ps.setString(17, deviceNamePO.getFactoryNo());
						ps.setString(18, deviceNamePO.getNameplate());
						ps.setInt(19, stakeIds[i]);
					}

					@Override
					public int getBatchSize() {
						return stakeIds.length;
					}
				});
		return ids.length == stakeIds.length;
	}

	/**
	 * 删除子充电桩
	 * 
	 * @param groupNo
	 */
	public boolean deleteChild(String groupNo) {
		return this.getJdbcTemplate().update(DELETE_CHILD_SQL, groupNo) > 0;
	}

	/**
	 * 查找充电桩
	 * 
	 * @param merchantId
	 * @return
	 */
	public StakeDeviceNamePO getStakePriceByPriceId(Integer priceId) {
		return this.getJdbcTemplate().query(SELECT_PRICE_STAKE_SQL,
				new Object[] { priceId }, new StakeDevicenExtractor());
	}

}

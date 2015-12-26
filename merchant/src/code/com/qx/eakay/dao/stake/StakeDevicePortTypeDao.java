package com.qx.eakay.dao.stake;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.qx.common.dao.BaseDao;
import com.qx.common.sql.JSONSqlMapping;
import com.qx.eakay.model.stake.StakeDeviceNamePO;

/**
 * 
 * @author jixf
 * @date 2015年7月15日
 */
@Repository
public class StakeDevicePortTypeDao extends BaseDao {

	@Resource(name = "stake_device_port_type_select_sql")
	private JSONSqlMapping typeSelectSQL;

	/**
	 * 查找设备端口分类
	 * 
	 * @return
	 */
	public List<Map<String, Object>> findPortTypes(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(typeSelectSQL, jsonObject);
	}

	/**
	 * 查询充电桩端口
	 * 
	 * @param typename
	 * @return
	 */
	public List<Map<String, Object>> findPorts(String typename) {
		return this.findListBySQL(SELECT_SQL, new Object[] { typename });
	}

	// 查询充电桩端口SQL
	public static final String SELECT_SQL = "select * from stake_devicePort_type where typename=?";
	// 查找充电设备类型参数SQL
	public static final String SELECT_DEVICE_NAME_TYPE_SQL = "select * from stake_devicename_type where deviceTypeName=? and chargePort=?";

	/**
	 * 查找充电设备类型参数
	 * 
	 * @param deviceNamePO
	 * @return
	 */
	public StakeDeviceNamePO getDeviceNameType(StakeDeviceNamePO deviceNamePO) {
		return this.getJdbcTemplate().query(
				SELECT_DEVICE_NAME_TYPE_SQL,
				new Object[] { deviceNamePO.getDeviceTypeName(),
						deviceNamePO.getChargePort() },
				new ResultSetExtractor<StakeDeviceNamePO>() {
					@Override
					public StakeDeviceNamePO extractData(ResultSet rs)
							throws SQLException, DataAccessException {
						return createDeviceNameTypeFromResultSet(rs);
					}
				});
	}

	private StakeDeviceNamePO createDeviceNameTypeFromResultSet(ResultSet rs)
			throws SQLException {
		StakeDeviceNamePO stakeDeviceNamePO = null;
		if (rs.next()) {
			stakeDeviceNamePO = new StakeDeviceNamePO();
			stakeDeviceNamePO.setParkType(rs.getString("parkType"));
			stakeDeviceNamePO.setChargePort(rs.getString("chargePort"));
			stakeDeviceNamePO.setDeviceTypeName(rs.getString("deviceTypeName"));
			stakeDeviceNamePO.setDeviceType(rs.getInt("deviceType"));
		}
		return stakeDeviceNamePO;
	}

}

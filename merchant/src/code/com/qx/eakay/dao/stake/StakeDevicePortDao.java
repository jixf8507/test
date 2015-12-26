package com.qx.eakay.dao.stake;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.qx.common.dao.BaseDao;
import com.qx.common.sql.JSONSqlMapping;
import com.qx.eakay.model.stake.StakeDevicePortPO;

@Repository
public class StakeDevicePortDao extends BaseDao {

	@Resource(name = "stake_device_port_select_sql")
	private JSONSqlMapping devicePortsSelectSQL;

	/**
	 * 条件查找充电设备充电端口列表
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findDevicePorts(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(devicePortsSelectSQL, jsonObject);
	}

	/**
	 * 删除设备的充电端口
	 * 
	 * @param deviceId
	 * @return
	 */
	public boolean delete(Integer deviceId) {
		int count = this.getJdbcTemplate().update(DELETE_SQL, deviceId);
		return count > 0;
	}

	/**
	 * 批量创建充电设备充电口
	 * 
	 * @param ports
	 * @return
	 */
	public boolean batchCreate(final List<StakeDevicePortPO> ports) {
		int[] ids = this.getJdbcTemplate().batchUpdate(INSERT_SQL,
				new BatchPreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						StakeDevicePortPO port = ports.get(i);
						ps.setString(1, port.getName());
						ps.setString(2, "正常");
						ps.setString(3, port.getPos());
						ps.setString(4, port.getCurrents());
						ps.setString(5, port.getGrooveNo());
						ps.setString(6, port.getFactoryNo());
						ps.setString(7, port.getPortorder());
						ps.setString(8, port.getMemo());
					}

					@Override
					public int getBatchSize() {
						return ports.size();
					}
				});
		return ids.length == ports.size();
	}

	// 删除端口
	private static final String DELETE_SQL = "delete from stake_devicePort where factoryNo=(select factoryNo from stake_devicename where id=?)";
	// 新增端口
	private static final String INSERT_SQL = "insert into stake_devicePort (name,status,pos,currents,grooveNo,factoryNo,portorder,memo) values(?,?,?,?,?,?,?,?)";

	
	
	/******* 1托30 *******/

	/**
	 * 删除设备的充电端口
	 * 
	 * @param factoryNo
	 * @return
	 */
	public boolean deleteOther(String factoryNo) {
		return this.getJdbcTemplate().update(DELETE_OTHER_SQL, factoryNo) > 0;
	}

	// 删除端口
	private static final String DELETE_OTHER_SQL = "delete from stake_devicePort where factoryNo like ?";
}

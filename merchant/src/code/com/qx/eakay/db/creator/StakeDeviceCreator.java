package com.qx.eakay.db.creator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.qx.eakay.model.stake.StakeDeviceNamePO;

public class StakeDeviceCreator implements PreparedStatementCreator {
	private String sql;
	private StakeDeviceNamePO deviceNamePO;

	public StakeDeviceCreator(String sql, StakeDeviceNamePO deviceNamePO) {
		this.sql = sql;
		this.deviceNamePO = deviceNamePO;
	}

	@Override
	public PreparedStatement createPreparedStatement(Connection conn)
			throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql,
				PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setString(1, deviceNamePO.getSiteCode());
		ps.setString(2, deviceNamePO.getAreaCode());
		ps.setString(3, deviceNamePO.getChargePort());
		ps.setString(4, deviceNamePO.getDeviceNo());
		ps.setString(5, deviceNamePO.getDeviceTypeName());
		ps.setString(6, deviceNamePO.getManufacturer());
		ps.setString(7, deviceNamePO.getRegionA());
		ps.setString(8, deviceNamePO.getRegionB());
		ps.setString(9, deviceNamePO.getDeviceKind());
		ps.setInt(10, deviceNamePO.getDeviceType());
		ps.setString(11, deviceNamePO.getChargerType());
		ps.setString(12, deviceNamePO.getSpec());
		ps.setInt(13, deviceNamePO.getDeviceNum());
		ps.setString(14, deviceNamePO.getFactoryNo());
		ps.setTimestamp(15, new Timestamp(System.currentTimeMillis()));
		ps.setString(16, deviceNamePO.getCanAddress());
		ps.setString(17, deviceNamePO.getIpAddress());
		ps.setString(18, deviceNamePO.getSafetyLevle());
		ps.setFloat(19, deviceNamePO.getFixedVoltage());
		ps.setInt(20, deviceNamePO.getServerPort());
		ps.setInt(21, deviceNamePO.getClientPort());
		ps.setString(22, deviceNamePO.getStatus());
		ps.setBigDecimal(23, deviceNamePO.getDeviceVersion());
		ps.setString(24, deviceNamePO.getChargeStatus());
		ps.setString(25, deviceNamePO.getFlag());
		ps.setInt(26, deviceNamePO.getMerchantId());
		ps.setFloat(27, deviceNamePO.getLng());
		ps.setFloat(28, deviceNamePO.getLat());
		ps.setString(29, deviceNamePO.getAddress());
		ps.setString(30, deviceNamePO.getImgUrl());
		ps.setString(31, deviceNamePO.getIsAllowParking());
		ps.setString(32, deviceNamePO.getParkType());
		ps.setFloat(33, deviceNamePO.getDevicePower());
		ps.setString(34, deviceNamePO.getFactoryNo());
		ps.setString(35, deviceNamePO.getDeviceTypeNo());
		ps.setString(36, deviceNamePO.getNameplate());
		return ps;
	}

}

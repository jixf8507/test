package com.qx.eakay.db.creator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.qx.eakay.model.car.CarBatteryPO;

public class CarBatteryCreator implements PreparedStatementCreator {

	private String sql;
	private CarBatteryPO batteryPO;

	public CarBatteryCreator(String sql, CarBatteryPO batteryPO) {
		this.sql = sql;
		this.batteryPO = batteryPO;
	}

	@Override
	public PreparedStatement createPreparedStatement(Connection conn)
			throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql,
				PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setInt(1, batteryPO.getCarId());
		ps.setString(2, batteryPO.getVin());
		ps.setString(3, batteryPO.getBatteryType());
		ps.setFloat(4, batteryPO.getMaxVoltag());
		ps.setFloat(5, batteryPO.getMaxCurrent());
		ps.setFloat(6, batteryPO.getMaxTemp());
		ps.setFloat(7, batteryPO.getMinVoltage());
		ps.setFloat(8, batteryPO.getMinCurrent());
		ps.setFloat(9, batteryPO.getMinTemp());
		ps.setInt(10, batteryPO.getMerchantId());
		ps.setTimestamp(11, new Timestamp(System.currentTimeMillis()));
		return ps;
	}

}

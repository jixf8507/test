package com.qx.eakay.db.creator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.qx.eakay.model.car.CarPO;

public class CarCreator implements PreparedStatementCreator {

	private String sql;
	private CarPO carPO;

	public CarCreator(String sql, CarPO carPO) {
		this.sql = sql;
		this.carPO = carPO;
	}

	@Override
	public PreparedStatement createPreparedStatement(Connection conn)
			throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql,
				PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setString(1, carPO.getCarNumber());
		ps.setInt(2, carPO.getCarTypeId());
		ps.setString(4, carPO.getDescribe());
		ps.setString(3, carPO.getColor());
		ps.setInt(5, carPO.getChargeDuration());
		ps.setString(6, carPO.getBigImgs());
		ps.setString(7, carPO.getLittleImgs());
		ps.setString(8, carPO.getBigIcon());
		ps.setString(9, carPO.getLittleIcon());
		ps.setTimestamp(10, new Timestamp(System.currentTimeMillis()));
		ps.setInt(11, carPO.getCurSiteId());
		ps.setInt(12, carPO.getCurSiteId());
		ps.setInt(13, carPO.getMerchantId());
		ps.setString(14, carPO.getNsuranceRange());
		ps.setString(15, carPO.getCheckYearDate());
		ps.setString(16, carPO.getVin());
		return ps;
	}

}

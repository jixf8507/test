package com.qx.eakay.db.creator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.qx.eakay.model.parking.ParkingSpacePO;

/**
 * 
 * @author jixf
 * @date 2015年7月16日
 */
public class ParkingSpaceCreator implements PreparedStatementCreator {

	private String sql;
	private ParkingSpacePO spacePO;

	public ParkingSpaceCreator(String sql, ParkingSpacePO spacePO) {
		this.sql = sql;
		this.spacePO = spacePO;
	}

	@Override
	public PreparedStatement createPreparedStatement(Connection conn)
			throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql,
				PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setInt(1, spacePO.getParkingId());
		ps.setString(2, spacePO.getSpaceNO());
		ps.setString(3, spacePO.getArea());
		ps.setFloat(4, spacePO.getLng());
		ps.setFloat(5, spacePO.getLat());
		ps.setString(6, spacePO.getAddress());
		ps.setString(7, spacePO.getElectric());
		ps.setString(8, spacePO.getIsCharge());
		ps.setString(9, spacePO.getFactoryNo());
		ps.setString(10, spacePO.getPosition());
		ps.setString(11, spacePO.getGrooveNo());
		ps.setString(12, spacePO.getIsLock());
		return ps;
	}

}

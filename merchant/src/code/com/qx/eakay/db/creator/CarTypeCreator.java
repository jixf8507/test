package com.qx.eakay.db.creator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.qx.eakay.model.car.CarTypePO;

public class CarTypeCreator implements PreparedStatementCreator {

	private String sql;
	private CarTypePO carTypePO;

	public CarTypeCreator(String sql, CarTypePO carTypePO) {
		this.sql = sql;
		this.carTypePO = carTypePO;
	}

	@Override
	public PreparedStatement createPreparedStatement(Connection conn)
			throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql,
				PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setString(1, carTypePO.getTypeName());
		ps.setInt(2, carTypePO.getCarManufacturerId());
		ps.setInt(3, carTypePO.getSeatCount());
		ps.setString(4, carTypePO.getColor());
		ps.setString(5, carTypePO.getEnergy());
		return ps;
	}

}

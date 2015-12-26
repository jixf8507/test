package com.qx.eakay.db.creator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.qx.eakay.model.car.CarManufacturerPO;

/**
 * 
 * @author jixf
 * @date 2015年6月30日
 */
public class CarManufacturerCreator implements PreparedStatementCreator {

	private String sql;
	private CarManufacturerPO carManufacturerPO;

	public CarManufacturerCreator(String sql,
			CarManufacturerPO carManufacturerPO) {
		this.sql = sql;
		this.carManufacturerPO = carManufacturerPO;
	}

	@Override
	public PreparedStatement createPreparedStatement(Connection conn)
			throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql,
				PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setString(1, carManufacturerPO.getManufacturerName());
		ps.setString(2, carManufacturerPO.getFullName());
		ps.setString(3, carManufacturerPO.getAddress());
		ps.setString(4, carManufacturerPO.getLinkman());
		ps.setString(5, carManufacturerPO.getLinkphone());
		ps.setInt(6, carManufacturerPO.getMerchantId());
		ps.setString(7, carManufacturerPO.getEmail());
		return ps;
	}

}

package com.qx.eakay.db.creator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.qx.eakay.model.car.SimPO;

public class CarSimCreator implements PreparedStatementCreator {

	private String sql;
	private SimPO simPO;

	public CarSimCreator(String sql, SimPO simPO) {
		this.sql = sql;
		this.simPO = simPO;
	}

	@Override
	public PreparedStatement createPreparedStatement(Connection conn)
			throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql,
				PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setString(1, simPO.getSimCard());
		ps.setString(2, simPO.getFacilitator());
		ps.setString(3, simPO.getFlowOfMonth());
//		ps.setInt(4, simPO.getDeviceId());
		ps.setInt(4, simPO.getMerchantId());
		ps.setString(5, simPO.getIccid());
		return ps;
	}

}

package com.qx.eakay.db.creator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.qx.eakay.model.customer.UnitCustomerPO;

public class UnitCustomerCreator implements PreparedStatementCreator {

	private String sql;
	private UnitCustomerPO unitCustomerPO;

	public UnitCustomerCreator(String sql, UnitCustomerPO unitCustomerPO) {
		this.sql = sql;
		this.unitCustomerPO = unitCustomerPO;
	}
	
	@Override
	public PreparedStatement createPreparedStatement(Connection conn)
			throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql,
				PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setInt(1, unitCustomerPO.getMerchantId());
		ps.setString(2, unitCustomerPO.getEnterpriseName());
		ps.setString(3, unitCustomerPO.getContactPerson());
		ps.setString(4, unitCustomerPO.getContactPhone());
		ps.setString(5, unitCustomerPO.getAddress());
		ps.setString(6, unitCustomerPO.getTransactUser());
		ps.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
		ps.setString(8, unitCustomerPO.getBankCardNO());
		ps.setString(9, unitCustomerPO.getBankType());
		return ps;
	}

}

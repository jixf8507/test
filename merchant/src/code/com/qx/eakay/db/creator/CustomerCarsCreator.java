package com.qx.eakay.db.creator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.qx.eakay.model.customer.CustomerCarsPO;

public class CustomerCarsCreator implements PreparedStatementCreator {

	private String sql;
	private CustomerCarsPO customerCarsPO;

	public CustomerCarsCreator(String sql, CustomerCarsPO customerCarsPO) {
		this.sql = sql;
		this.customerCarsPO = customerCarsPO;
	}

	@Override
	public PreparedStatement createPreparedStatement(Connection conn)
			throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql,
				PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setString(1, customerCarsPO.getCarNumber());
		ps.setString(2, customerCarsPO.getCarType());
		ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
		ps.setInt(4, customerCarsPO.getCustomerId());
		ps.setString(5, customerCarsPO.getVin());
		return ps;
	}
}

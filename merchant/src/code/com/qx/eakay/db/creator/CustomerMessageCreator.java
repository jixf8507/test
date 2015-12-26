package com.qx.eakay.db.creator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.qx.eakay.model.customer.CustomerMessagePO;

public class CustomerMessageCreator implements PreparedStatementCreator {
	private String sql;
	private CustomerMessagePO customerMessagePO;

	public CustomerMessageCreator(String sql,
			CustomerMessagePO customerMessagePO) {
		this.sql = sql;
		this.customerMessagePO = customerMessagePO;
	}

	@Override
	public PreparedStatement createPreparedStatement(Connection conn)
			throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql,
				PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setInt(1, customerMessagePO.getCustomerId());
		ps.setString(2, customerMessagePO.getContent());
		ps.setString(3, customerMessagePO.getType());
		ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
		return ps;
	}
}

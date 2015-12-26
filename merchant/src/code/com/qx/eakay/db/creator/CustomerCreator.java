package com.qx.eakay.db.creator;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.qx.eakay.model.customer.CustomerPO;

public class CustomerCreator implements PreparedStatementCreator {

	private static final String SIMPLE_PASSWORD = "123456";
	// private static final String SIMPLE_PASSWORD = MD5Util.MD516("123456");

	private String sql;
	private CustomerPO customerPO;

	public CustomerCreator(String sql, CustomerPO customerPO) {
		this.sql = sql;
		this.customerPO = customerPO;
	}

	@Override
	public PreparedStatement createPreparedStatement(Connection conn)
			throws SQLException {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		PreparedStatement ps = conn.prepareStatement(sql,
				PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setString(1, customerPO.getIdCard());
		ps.setString(2, customerPO.getName());
		ps.setString(3, customerPO.getPhone());
		ps.setString(4, SIMPLE_PASSWORD);
		ps.setString(5, customerPO.getSex());
		ps.setString(6, customerPO.getAddress());
		ps.setBigDecimal(7, new BigDecimal(0));
		ps.setString(8, SIMPLE_PASSWORD);
		ps.setString(9, customerPO.getIdCardImg());
		ps.setString(10, customerPO.getDriveCardImg());
		ps.setString(11, customerPO.getVerifyMan());
		ps.setTimestamp(12, now);
		ps.setTimestamp(13, now);
		ps.setString(14, customerPO.getWorkUnit());
		return ps;
	}

}

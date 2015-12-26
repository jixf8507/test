package com.qx.eakay.db.creator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.qx.eakay.model.customer.AccountPO;

public class AccountCreator implements PreparedStatementCreator {

	private String sql;
	private AccountPO accountPO;

	public AccountCreator(String sql, AccountPO accountPO) {
		this.sql = sql;
		this.accountPO = accountPO;
	}

	@Override
	public PreparedStatement createPreparedStatement(Connection conn)
			throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql,
				PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setInt(1, accountPO.getCustomerId());
		ps.setInt(2, accountPO.getMerchantId());
		ps.setString(3, accountPO.getCardNO());
		ps.setString(4, accountPO.getBankCardNO());
		ps.setString(5, accountPO.getBankType());
		ps.setInt(6, accountPO.getSiteId());
		Timestamp now = new Timestamp(System.currentTimeMillis());
		ps.setTimestamp(7, now);
		ps.setTimestamp(8, now);
		ps.setString(9, accountPO.getTransactUser());
		return ps;
	}
}

package com.qx.eakay.db.creator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.qx.eakay.model.customer.AccountRecordPO;

public class AccountRecordCreator implements PreparedStatementCreator {

	private String sql;
	private AccountRecordPO accountRecordPO;

	public AccountRecordCreator(String sql, AccountRecordPO accountRecordPO) {
		this.sql = sql;
		this.accountRecordPO = accountRecordPO;
	}

	@Override
	public PreparedStatement createPreparedStatement(Connection conn)
			throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql,
				PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setInt(1, accountRecordPO.getCustomerId());
		ps.setBigDecimal(2, accountRecordPO.getOldBalance());
		ps.setBigDecimal(3, accountRecordPO.getAddBalance());
		ps.setBigDecimal(4, accountRecordPO.getNewBalance());
		ps.setString(5, accountRecordPO.getTransactUser());
		ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
		ps.setString(7, accountRecordPO.getType().name());
		ps.setInt(8, accountRecordPO.getAccountId());
		ps.setInt(9, accountRecordPO.getSiteId());
		ps.setString(10, accountRecordPO.getDescribe());
		ps.setString(11, accountRecordPO.getCustomerTable());
		ps.setString(12, accountRecordPO.getTicket());
		ps.setString(13, accountRecordPO.getAccountTable());
		return ps;
	}

}

package com.qx.eakay.db.creator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.qx.eakay.model.merchant.MerchantAccountRecordPO;

public class MerchantAccountRecordCreator implements PreparedStatementCreator {

	private String sql;
	private MerchantAccountRecordPO accountRecordPO;

	public MerchantAccountRecordCreator(String sql,
			MerchantAccountRecordPO accountRecordPO) {
		this.sql = sql;
		this.accountRecordPO = accountRecordPO;
	}

	@Override
	public PreparedStatement createPreparedStatement(Connection conn)
			throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql,
				PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setInt(1, accountRecordPO.getMerchantId());
		ps.setBigDecimal(2, accountRecordPO.getOldBalance());
		ps.setBigDecimal(3, accountRecordPO.getAddBalance());
		ps.setBigDecimal(4, accountRecordPO.getNewBalance());
		ps.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
		ps.setString(6, accountRecordPO.getType().name());
		ps.setString(7, accountRecordPO.getTransactUser());
		ps.setInt(8, accountRecordPO.getSiteId());
		return ps;
	}

}

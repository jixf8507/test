package com.qx.eakay.db.creator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.qx.eakay.model.merchant.MerchantUserPO;

public class MerchantUserCreator implements PreparedStatementCreator {

	private String sql;
	private MerchantUserPO merchantUserPO;

	public MerchantUserCreator(String sql, MerchantUserPO merchantUserPO) {
		this.sql = sql;
		this.merchantUserPO = merchantUserPO;
	}

	@Override
	public PreparedStatement createPreparedStatement(Connection conn)
			throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql,
				PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setString(1, merchantUserPO.getEmail());
		ps.setString(2, merchantUserPO.getMobilePhone());
		ps.setString(3, merchantUserPO.getUserName());
		ps.setInt(4, merchantUserPO.getSiteId());
		ps.setString(5, merchantUserPO.getPassword());
		ps.setString(6, merchantUserPO.getRights());
		ps.setInt(7, merchantUserPO.getMerchantId());
		return ps;
	}
}

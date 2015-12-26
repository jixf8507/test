package com.qx.eakay.db.creator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.qx.eakay.model.stake.StakeCardInfoPO;
import com.qx.eakay.util.MD5Util;

public class StakeCardInfoCreator implements PreparedStatementCreator {
	private static final String SIMPLE_PWD = MD5Util.MD516("123456");

	private String sql;
	private StakeCardInfoPO cardInfoPO;

	public StakeCardInfoCreator(String sql, StakeCardInfoPO cardInfoPO) {
		this.sql = sql;
		this.cardInfoPO = cardInfoPO;
	}

	@Override
	public PreparedStatement createPreparedStatement(Connection conn)
			throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql,
				PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setInt(1, cardInfoPO.getMerchantId());
		ps.setString(2, cardInfoPO.getCardID());
		ps.setString(3, cardInfoPO.getCardNumber());
		ps.setString(4, SIMPLE_PWD);
		ps.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
		ps.setString(6, cardInfoPO.getCarId());
		ps.setString(7, cardInfoPO.getCarTableName());
		ps.setInt(8, cardInfoPO.getCustomerId());
		return ps;
	}
}

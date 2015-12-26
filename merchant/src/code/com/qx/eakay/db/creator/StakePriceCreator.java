package com.qx.eakay.db.creator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.qx.eakay.model.stake.StakePricePO;

public class StakePriceCreator implements PreparedStatementCreator {

	private String sql;
	private StakePricePO stakePricePO;

	public StakePriceCreator(String sql, StakePricePO stakePricePO) {
		this.sql = sql;
		this.stakePricePO = stakePricePO;
	}
	
	@Override
	public PreparedStatement createPreparedStatement(Connection conn)
			throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql,
				PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setInt(1, stakePricePO.getMerchantId());
		ps.setString(2, stakePricePO.getName());
		ps.setString(3, stakePricePO.getHour1());
		ps.setString(4, stakePricePO.getMinute1());
		ps.setString(5, stakePricePO.getHour2());
		ps.setString(6, stakePricePO.getMinute2());
		ps.setString(7, stakePricePO.getHour3());
		ps.setString(8, stakePricePO.getMinute3());
		ps.setString(9, stakePricePO.getHour4());
		ps.setString(10, stakePricePO.getMinute4());
		ps.setBigDecimal(11, stakePricePO.getPriceA());
		ps.setBigDecimal(12, stakePricePO.getPriceB());
		ps.setBigDecimal(13, stakePricePO.getPriceC());
		ps.setBigDecimal(14, stakePricePO.getPriceD());
		ps.setTimestamp(15, new Timestamp(System.currentTimeMillis()));
		ps.setString(16, stakePricePO.getMemo());
		return ps;
	}
}

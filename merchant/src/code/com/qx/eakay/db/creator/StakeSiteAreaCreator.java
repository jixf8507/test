package com.qx.eakay.db.creator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.qx.eakay.model.stake.StakeSiteAreaPO;

public class StakeSiteAreaCreator implements PreparedStatementCreator {

	private String sql;
	private StakeSiteAreaPO areaPO;

	public StakeSiteAreaCreator(String sql, StakeSiteAreaPO areaPO) {
		this.sql = sql;
		this.areaPO = areaPO;
	}

	@Override
	public PreparedStatement createPreparedStatement(Connection conn)
			throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql,
				PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setString(1, areaPO.getSiteCode());
		ps.setString(2, areaPO.getAreaCode());
		ps.setString(3, areaPO.getAreaName());
		Timestamp now = new Timestamp(System.currentTimeMillis());
		ps.setTimestamp(4, now);
		ps.setTimestamp(5, now);
		return ps;
	}

}

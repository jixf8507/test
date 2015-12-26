package com.qx.eakay.db.creator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.qx.eakay.model.merchant.SitePO;

/**
 * 
 * @author jixf
 * @date 2015年7月11日
 */
public class SiteCreator implements PreparedStatementCreator {
	private String sql;
	private SitePO sitePO;

	public SiteCreator(String sql, SitePO sitePO) {
		this.sql = sql;
		this.sitePO = sitePO;
	}

	@Override
	public PreparedStatement createPreparedStatement(Connection conn)
			throws SQLException {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		PreparedStatement ps = conn.prepareStatement(sql,
				PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setString(1, sitePO.getSiteName());
		ps.setString(2, sitePO.getPhone());
		ps.setString(3, sitePO.getAddress());
		ps.setString(4, sitePO.getFlag());
		ps.setString(5, sitePO.getPrincipal());
		ps.setFloat(6, sitePO.getLat());
		ps.setFloat(7, sitePO.getLng());
		ps.setInt(8, sitePO.getMerchantId());
		ps.setTimestamp(9, now);
		ps.setTimestamp(10, now);
		ps.setInt(11, sitePO.getType());
		ps.setString(12, sitePO.getImgUrl());
		ps.setString(13, sitePO.getProvince());
		ps.setString(14, sitePO.getCity());
		return ps;
	}
}

package com.qx.eakay.db.creator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.qx.eakay.model.asset.AssetUsePO;

public class AssetUseCreator implements PreparedStatementCreator {

	private String sql;
	private AssetUsePO assetUsePO;

	public AssetUseCreator(String sql, AssetUsePO assetUsePO) {
		this.sql = sql;
		this.assetUsePO = assetUsePO;
	}
	
	@Override
	public PreparedStatement createPreparedStatement(Connection conn)
			throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql,
				PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setString(1, assetUsePO.getUseDate());
		ps.setInt(2, assetUsePO.getSiteId());
		ps.setInt(3, assetUsePO.getUserId());
		ps.setInt(4, assetUsePO.getMerchantId());
		ps.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
		ps.setString(6, assetUsePO.getTransactUser());
		ps.setString(7, assetUsePO.getRemarks());
		return ps;
	}

}

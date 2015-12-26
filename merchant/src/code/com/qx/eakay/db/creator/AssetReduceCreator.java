package com.qx.eakay.db.creator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.qx.eakay.model.asset.AssetReducePO;

public class AssetReduceCreator implements PreparedStatementCreator {

	private String sql;
	private AssetReducePO assetReducePO;

	public AssetReduceCreator(String sql, AssetReducePO assetReducePO) {
		this.sql = sql;
		this.assetReducePO = assetReducePO;
	}
	
	@Override
	public PreparedStatement createPreparedStatement(Connection conn)
			throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql,
				PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setInt(1, assetReducePO.getMerchantId());
		ps.setString(2, assetReducePO.getReduceDate());
		ps.setString(3, assetReducePO.getReduceReason());
		ps.setString(4, assetReducePO.getReduceStatus());
		ps.setString(5, assetReducePO.getApplyUser());
		ps.setString(6, assetReducePO.getTransactUser());
		ps.setString(7, assetReducePO.getRemarks());
		ps.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
		return ps;
	}

}

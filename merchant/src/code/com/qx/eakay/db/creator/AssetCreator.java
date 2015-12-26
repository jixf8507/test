package com.qx.eakay.db.creator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.qx.eakay.model.asset.AssetPO;

public class AssetCreator implements PreparedStatementCreator {

	private String sql;
	private AssetPO assetPO;

	public AssetCreator(String sql, AssetPO assetPO) {
		this.sql = sql;
		this.assetPO = assetPO;
	}

	@Override
	public PreparedStatement createPreparedStatement(Connection conn)
			throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql,
				PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setInt(1, assetPO.getCategoryId());
		ps.setString(2, assetPO.getAssetsName());
		ps.setInt(3, assetPO.getMerchantId());
		ps.setString(4, assetPO.getModel());
		ps.setString(5, assetPO.getIncreasingMode());
		ps.setString(6, assetPO.getUnit());
		ps.setString(7, assetPO.getPurchaseDate());
		ps.setTimestamp(8,  new Timestamp(System.currentTimeMillis()));
		ps.setInt(9, assetPO.getManufacturId());
		ps.setString(10, assetPO.getTransactUser());
		ps.setBigDecimal(11, assetPO.getFee());
		return ps;
	}

}

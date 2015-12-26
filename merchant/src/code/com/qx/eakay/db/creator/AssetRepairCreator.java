package com.qx.eakay.db.creator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.qx.eakay.model.asset.AssetRepairPO;

public class AssetRepairCreator implements PreparedStatementCreator {

	private String sql;
	private AssetRepairPO assetRepairPO;

	public AssetRepairCreator(String sql, AssetRepairPO assetRepairPO) {
		this.sql = sql;
		this.assetRepairPO = assetRepairPO;
	}
	
	@Override
	public PreparedStatement createPreparedStatement(Connection conn)
			throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql,
				PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setString(1, assetRepairPO.getRepairDate());
		ps.setString(2, assetRepairPO.getRepairReason());
		ps.setInt(3, assetRepairPO.getManufacturerId());
		ps.setString(4, assetRepairPO.getApplyUser());
		ps.setString(5, assetRepairPO.getTransactUser());
		ps.setBigDecimal(6, assetRepairPO.getFee());
		ps.setInt(7, assetRepairPO.getMerchantId());
		ps.setString(8, assetRepairPO.getRemarks());
		ps.setTimestamp(9, new Timestamp(System.currentTimeMillis()));
		return ps;
	}

}

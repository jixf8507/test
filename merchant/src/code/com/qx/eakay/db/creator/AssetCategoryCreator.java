package com.qx.eakay.db.creator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.qx.eakay.model.asset.AssetCategoryPO;

public class AssetCategoryCreator implements PreparedStatementCreator {

	private String sql;
	private AssetCategoryPO categoryPO;

	public AssetCategoryCreator(String sql, AssetCategoryPO categoryPO) {
		this.sql = sql;
		this.categoryPO = categoryPO;
	}

	@Override
	public PreparedStatement createPreparedStatement(Connection conn)
			throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql,
				PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setString(1, categoryPO.getName());
		ps.setInt(2, categoryPO.getMerchantId());
		return ps;
	}

}

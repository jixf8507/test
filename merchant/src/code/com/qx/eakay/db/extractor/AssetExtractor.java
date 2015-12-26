package com.qx.eakay.db.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.qx.eakay.model.asset.AssetPO;

public class AssetExtractor implements ResultSetExtractor<AssetPO> {

	@Override
	public AssetPO extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		if (rs.next()) {
			return new AssetPO(rs);
		}
		return null;
	}

}

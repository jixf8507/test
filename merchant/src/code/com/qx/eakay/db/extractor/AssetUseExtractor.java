package com.qx.eakay.db.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.qx.eakay.model.asset.AssetUsePO;

public class AssetUseExtractor implements ResultSetExtractor<AssetUsePO> {

	@Override
	public AssetUsePO extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		if (rs.next()) {
			return new AssetUsePO(rs);
		}
		return null;
	}

}

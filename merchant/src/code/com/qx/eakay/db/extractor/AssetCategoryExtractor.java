package com.qx.eakay.db.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.qx.eakay.model.asset.AssetCategoryPO;

public class AssetCategoryExtractor implements ResultSetExtractor<AssetCategoryPO> {

	@Override
	public AssetCategoryPO extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		if (rs.next()) {
			return new AssetCategoryPO(rs);
		}
		return null;
	}

}

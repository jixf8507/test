package com.qx.eakay.db.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.qx.eakay.model.asset.AssetReducePO;

public class AssetReduceExtractor implements ResultSetExtractor<AssetReducePO> {

	@Override
	public AssetReducePO extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		if (rs.next()) {
			return new AssetReducePO(rs);
		}
		return null;
	}

}

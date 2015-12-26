package com.qx.eakay.db.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.qx.eakay.model.asset.AssetRepairPO;

public class AssetRepairExtractor implements ResultSetExtractor<AssetRepairPO> {

	@Override
	public AssetRepairPO extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		if (rs.next()) {
			return new AssetRepairPO(rs);
		}
		return null;
	}

}

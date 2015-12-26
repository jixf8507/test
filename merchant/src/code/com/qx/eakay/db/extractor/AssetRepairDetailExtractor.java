package com.qx.eakay.db.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.qx.eakay.model.asset.AssetRepairDetailPO;

public class AssetRepairDetailExtractor implements ResultSetExtractor<AssetRepairDetailPO> {

	@Override
	public AssetRepairDetailPO extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		if (rs.next()) {
			return new AssetRepairDetailPO(rs);
		}
		return null;
	}

}

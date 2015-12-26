package com.qx.eakay.db.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.qx.eakay.model.stake.StakeRechargeCostPO;

public class StakeRechargeCostExtractor implements ResultSetExtractor<StakeRechargeCostPO> {

	@Override
	public StakeRechargeCostPO extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		if (rs.next()) {
			return new StakeRechargeCostPO(rs);
		}
		return null;
	}

}

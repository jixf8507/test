package com.qx.eakay.db.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.qx.eakay.model.stake.StakeSiteAreaPO;

public class StakeSiteAreaExtractor implements
		ResultSetExtractor<StakeSiteAreaPO> {

	@Override
	public StakeSiteAreaPO extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		if (rs.next()) {
			return new StakeSiteAreaPO(rs);
		}
		return null;
	}

}

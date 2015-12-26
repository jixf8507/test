package com.qx.eakay.db.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.qx.eakay.model.stake.StakeCardInfoPO;

public class StakeCardInfoExtractor implements
		ResultSetExtractor<StakeCardInfoPO> {

	@Override
	public StakeCardInfoPO extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		if (rs.next()) {
			return new StakeCardInfoPO(rs);
		}
		return null;
	}

}

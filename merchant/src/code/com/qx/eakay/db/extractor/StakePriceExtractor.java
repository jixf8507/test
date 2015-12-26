package com.qx.eakay.db.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.qx.eakay.model.stake.StakePricePO;

public class StakePriceExtractor implements ResultSetExtractor<StakePricePO> {

	@Override
	public StakePricePO extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		if (rs.next()) {
			return new StakePricePO(rs);
		}
		return null;
	}

}

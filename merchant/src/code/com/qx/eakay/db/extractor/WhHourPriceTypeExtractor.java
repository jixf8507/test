package com.qx.eakay.db.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.qx.eakay.model.price.WhHoursPriceTypePO;

public class WhHourPriceTypeExtractor implements
		ResultSetExtractor<WhHoursPriceTypePO> {
	@Override
	public WhHoursPriceTypePO extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		if (rs.next()) {
			return new WhHoursPriceTypePO(rs);
		}
		return null;
	}
}

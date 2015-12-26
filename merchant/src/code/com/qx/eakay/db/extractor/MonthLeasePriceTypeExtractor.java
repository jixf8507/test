package com.qx.eakay.db.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.qx.eakay.model.price.MonthLeasePriceTypePO;

public class MonthLeasePriceTypeExtractor implements
		ResultSetExtractor<MonthLeasePriceTypePO> {
	@Override
	public MonthLeasePriceTypePO extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		if (rs.next()) {
			return new MonthLeasePriceTypePO(rs);
		}
		return null;
	}
}

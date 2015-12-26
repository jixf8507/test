package com.qx.eakay.db.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.qx.eakay.model.customer.UnitCustomerPO;

public class UnitCustomerExtractor implements ResultSetExtractor<UnitCustomerPO> {

	@Override
	public UnitCustomerPO extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		if (rs.next()) {
			return new UnitCustomerPO(rs);
		}
		return null;
	}

}

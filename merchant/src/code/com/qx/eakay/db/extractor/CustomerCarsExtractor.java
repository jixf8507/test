package com.qx.eakay.db.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.qx.eakay.model.customer.CustomerCarsPO;

public class CustomerCarsExtractor implements
		ResultSetExtractor<CustomerCarsPO> {

	@Override
	public CustomerCarsPO extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		if (rs.next()) {
			return new CustomerCarsPO(rs);
		}
		return null;
	}

}

package com.qx.eakay.db.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.qx.eakay.model.customer.CustomerPO;

public class CustomerExtractor implements ResultSetExtractor<CustomerPO> {

	@Override
	public CustomerPO extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		if (rs.next()) {
			return new CustomerPO(rs);
		}
		return null;
	}

}

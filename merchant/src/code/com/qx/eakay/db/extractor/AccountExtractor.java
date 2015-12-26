package com.qx.eakay.db.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.qx.eakay.model.customer.AccountPO;

public class AccountExtractor implements ResultSetExtractor<AccountPO> {

	@Override
	public AccountPO extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		if (rs.next()) {
			return new AccountPO(rs);
		}
		return null;
	}

}

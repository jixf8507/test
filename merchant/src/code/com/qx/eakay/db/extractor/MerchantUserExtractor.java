package com.qx.eakay.db.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.qx.eakay.model.merchant.MerchantUserPO;

public class MerchantUserExtractor implements
		ResultSetExtractor<MerchantUserPO> {

	@Override
	public MerchantUserPO extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		if (rs.next()) {
			return new MerchantUserPO(rs);
		}
		return null;
	}
}

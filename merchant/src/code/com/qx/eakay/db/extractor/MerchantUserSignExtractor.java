package com.qx.eakay.db.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.qx.eakay.model.merchant.MerchantUserSignPO;

public class MerchantUserSignExtractor implements
		ResultSetExtractor<MerchantUserSignPO> {

	@Override
	public MerchantUserSignPO extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		if (rs.next()) {
			return new MerchantUserSignPO(rs);
		}
		return null;
	}
}

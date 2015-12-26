package com.qx.eakay.db.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.qx.eakay.model.merchant.SitePO;

public class SiteExtractor implements ResultSetExtractor<SitePO> {
	@Override
	public SitePO extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		if (rs.next()) {
			return new SitePO(rs);
		}
		return null;
	}
}

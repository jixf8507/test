package com.qx.eakay.db.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.qx.eakay.model.work.workTypePO;

public class WorkTypeExtractor implements ResultSetExtractor<workTypePO> {

	@Override
	public workTypePO extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		if (rs.next()) {
			return new workTypePO(rs);
		}
		return null;
	}

}

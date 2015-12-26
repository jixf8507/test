package com.qx.eakay.db.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.qx.eakay.model.sys.SysFileUrlsPO;

public class FileUrlsExtractor implements ResultSetExtractor<SysFileUrlsPO> {

	@Override
	public SysFileUrlsPO extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		if (rs.next()) {
			return new SysFileUrlsPO(rs);
		}
		return null;
	}

}

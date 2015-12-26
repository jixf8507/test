package com.qx.common.db.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.qx.common.model.BasePO;

public abstract class BaseExtractor<T extends BasePO> implements
		ResultSetExtractor<T> {

	@Override
	public T extractData(ResultSet rs) throws SQLException, DataAccessException {
		if (rs.next()) {
			return getModel(rs);
		}
		return null;
	}

	public abstract T getModel(ResultSet rs) throws SQLException;

}

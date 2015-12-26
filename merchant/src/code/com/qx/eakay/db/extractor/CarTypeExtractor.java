package com.qx.eakay.db.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.qx.eakay.model.car.CarTypePO;

public class CarTypeExtractor implements ResultSetExtractor<CarTypePO> {

	@Override
	public CarTypePO extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		if (rs.next()) {
			return new CarTypePO(rs);
		}
		return null;
	}

}

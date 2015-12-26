package com.qx.eakay.db.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.qx.eakay.model.car.CarPO;

public class CarExtractor implements ResultSetExtractor<CarPO> {

	@Override
	public CarPO extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		if (rs.next()) {
			return new CarPO(rs);
		}
		return null;
	}

}

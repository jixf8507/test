package com.qx.eakay.db.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.qx.eakay.model.car.CarBatteryPO;

public class CarBatteryExtractor implements ResultSetExtractor<CarBatteryPO> {

	@Override
	public CarBatteryPO extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		if (rs.next()) {
			return new CarBatteryPO(rs);
		}
		return null;
	}

}

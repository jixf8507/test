package com.qx.eakay.db.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.qx.eakay.model.car.CarManufacturerPO;

/**
 * 
 * @author jixf
 * @date 2015年6月30日
 */
public class CarManufacturerExtractor implements
		ResultSetExtractor<CarManufacturerPO> {

	@Override
	public CarManufacturerPO extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		if (rs.next()) {
			return new CarManufacturerPO(rs);
		}
		return null;
	}

}

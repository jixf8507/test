package com.qx.eakay.db.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.qx.eakay.model.parking.ParkingSpacePO;

public class ParkingSpaceExtrator implements ResultSetExtractor<ParkingSpacePO> {

	@Override
	public ParkingSpacePO extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		if (rs.next()) {
			return new ParkingSpacePO(rs);
		}
		return null;
	}

}

package com.qx.eakay.db.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.qx.eakay.model.car.SimPO;

/**
 * 
 * @author sdf
 * @date 2015年10月31日
 */
public class CarDeviceSimExtractor implements
		ResultSetExtractor<SimPO> {

	@Override
	public SimPO extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		if (rs.next()) {
			return new SimPO(rs);
		}
		return null;
	}

}

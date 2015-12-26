package com.qx.eakay.db.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.qx.eakay.model.stake.StakeDeviceNamePO;

public class StakeDevicenExtractor implements ResultSetExtractor<StakeDeviceNamePO> {

	@Override
	public StakeDeviceNamePO extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		if (rs.next()) {
			return new StakeDeviceNamePO(rs);
		}
		return null;
	}

}

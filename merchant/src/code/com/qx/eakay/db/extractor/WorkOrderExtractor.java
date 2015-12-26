package com.qx.eakay.db.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.qx.eakay.model.work.WorkOrderPO;

public class WorkOrderExtractor implements ResultSetExtractor<WorkOrderPO> {

	@Override
	public WorkOrderPO extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		if (rs.next()) {
			return new WorkOrderPO(rs);
		}
		return null;
	}

}

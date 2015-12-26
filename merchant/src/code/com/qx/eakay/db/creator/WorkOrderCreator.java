package com.qx.eakay.db.creator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.qx.eakay.model.work.WorkOrderPO;
import com.qx.eakay.model.work.WorkOrderPO.workStatus;

public class WorkOrderCreator implements PreparedStatementCreator {

	private String sql;
	private WorkOrderPO workOrderPO;

	public WorkOrderCreator(String sql, WorkOrderPO workOrderPO) {
		this.sql = sql;
		this.workOrderPO = workOrderPO;
	}

	@Override
	public PreparedStatement createPreparedStatement(Connection conn)
			throws SQLException {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		PreparedStatement ps = conn.prepareStatement(sql,
				PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setInt(1, workOrderPO.getUserId());
		ps.setString(2, workOrderPO.getWorkName());
		ps.setString(3, workOrderPO.getTypeName());
		ps.setString(4, workStatus.待处理.name());
		ps.setString(5, workOrderPO.getUrgency());
		ps.setTimestamp(6, now);
		ps.setString(7, workOrderPO.getTransactUser());
		ps.setTimestamp(8, now);
		ps.setString(9, workOrderPO.getWorkDes());

		return ps;
	}

}

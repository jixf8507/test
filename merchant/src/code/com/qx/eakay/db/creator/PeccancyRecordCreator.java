package com.qx.eakay.db.creator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.qx.eakay.model.order.OrderPeccancyRecordPO;

public class PeccancyRecordCreator implements PreparedStatementCreator {

	private String sql;
	private OrderPeccancyRecordPO peccancyRecordPO;

	public PeccancyRecordCreator(String sql,
			OrderPeccancyRecordPO peccancyRecordPO) {
		this.sql = sql;
		this.peccancyRecordPO = peccancyRecordPO;
	}

	@Override
	public PreparedStatement createPreparedStatement(Connection conn)
			throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql,
				PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setInt(1, peccancyRecordPO.getOrderId());
		ps.setString(2, peccancyRecordPO.getPeccancyTime());
		ps.setString(3, peccancyRecordPO.getAddress());
		ps.setString(4, peccancyRecordPO.getInfo());
		ps.setString(5, peccancyRecordPO.getImg());
		ps.setBigDecimal(6, peccancyRecordPO.getPayCost());
		ps.setString(7, peccancyRecordPO.getPayStatus());
		ps.setString(8, peccancyRecordPO.getCheckMan());
//		ps.setInt(9, peccancyRecordPO.getMerchantId());
		return ps;
	}

}

package com.qx.eakay.db.creator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.qx.eakay.model.price.MonthLeasePriceTypePO;

public class MonthLeasePriceTypeCreator implements PreparedStatementCreator {

	private String sql;
	private MonthLeasePriceTypePO monthLeasePriceTypePO;

	public MonthLeasePriceTypeCreator(String sql, MonthLeasePriceTypePO monthLeasePriceTypePO) {
		this.sql = sql;
		this.monthLeasePriceTypePO = monthLeasePriceTypePO;
	}

	@Override
	public PreparedStatement createPreparedStatement(Connection conn)
			throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql,
				PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setInt(1, monthLeasePriceTypePO.getMerchantId());
		ps.setString(2, monthLeasePriceTypePO.getTypeName());
		ps.setBigDecimal(3, monthLeasePriceTypePO.getMonthFee());
		ps.setBigDecimal(4, monthLeasePriceTypePO.getDayFee());
		ps.setBigDecimal(5, monthLeasePriceTypePO.getHourFee());
		return ps;
	}

}

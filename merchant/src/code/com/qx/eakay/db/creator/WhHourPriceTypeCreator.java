package com.qx.eakay.db.creator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.qx.eakay.model.price.WhHoursPriceTypePO;

public class WhHourPriceTypeCreator implements PreparedStatementCreator {

	private String sql;
	private WhHoursPriceTypePO priceTypePO;

	public WhHourPriceTypeCreator(String sql, WhHoursPriceTypePO priceTypePO) {
		this.sql = sql;
		this.priceTypePO = priceTypePO;
	}

	@Override
	public PreparedStatement createPreparedStatement(Connection conn)
			throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql,
				PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setInt(1, priceTypePO.getMerchantId());
		ps.setString(2, priceTypePO.getTypeName());
		ps.setBigDecimal(3, priceTypePO.getOneHoursCost());
		ps.setBigDecimal(4, priceTypePO.getOneHoursCost());
		ps.setBigDecimal(5, priceTypePO.getOneHoursCost());
		ps.setBigDecimal(6, priceTypePO.getOneHoursCost());
		ps.setBigDecimal(7, priceTypePO.getOneHoursCost());
		ps.setBigDecimal(8, priceTypePO.getMaxCostForDay());
		ps.setBigDecimal(9, priceTypePO.getNightCost());
		ps.setFloat(10, priceTypePO.getPerHoursKmsForDay());
		ps.setBigDecimal(11, priceTypePO.getPerKmsPrice());
		ps.setFloat(12, priceTypePO.getMaxKmsForNight());
		ps.setTimestamp(13, priceTypePO.getStartTimeForDay());
		ps.setTimestamp(14, priceTypePO.getEndTimeForDay());
		ps.setFloat(15, priceTypePO.getMaxKmsForDay());
		ps.setInt(16, priceTypePO.getMaxDays());
		ps.setInt(17, priceTypePO.getMinDays());
		ps.setInt(18, priceTypePO.getPrepayment());
		ps.setTimestamp(19, priceTypePO.getStartTimeForWork());
		ps.setTimestamp(20, priceTypePO.getEndTimeForWork());
		ps.setInt(21, priceTypePO.getReservedMinute());
		return ps;
	}

}

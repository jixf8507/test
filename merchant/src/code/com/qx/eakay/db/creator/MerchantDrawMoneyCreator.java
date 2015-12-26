package com.qx.eakay.db.creator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.qx.eakay.model.merchant.MerchantDrawMoneyPO;

/**
 * 
 * @author jixf
 * @date 2015年7月11日
 */
public class MerchantDrawMoneyCreator implements PreparedStatementCreator {
	private String sql;
	private MerchantDrawMoneyPO drawMoneyPO;

	public MerchantDrawMoneyCreator(String sql, MerchantDrawMoneyPO drawMoneyPO) {
		this.sql = sql;
		this.drawMoneyPO = drawMoneyPO;
	}

	@Override
	public PreparedStatement createPreparedStatement(Connection conn)
			throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql,
				PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setBigDecimal(1, drawMoneyPO.getMoney());
		ps.setString(2, drawMoneyPO.getStatus());
		ps.setInt(3, drawMoneyPO.getMerchantId());
		ps.setString(4, drawMoneyPO.getCheckMan());
		ps.setString(5, drawMoneyPO.getCheckRemarks());
		ps.setString(6, drawMoneyPO.getRemarks());
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		ps.setTimestamp(7, timestamp);
		ps.setTimestamp(8, timestamp);
		return ps;
	}
}

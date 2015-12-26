package com.qx.eakay.db.creator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.qx.eakay.model.merchant.CouponPo;

public class CouponCreator implements PreparedStatementCreator {
	private String sql;
	private CouponPo couponPo;
	
	
	
	public CouponCreator(String sql, CouponPo couponPo) {
		this.sql = sql;
		this.couponPo = couponPo;
	}



	@Override
	/**
	 * 	`Id`,
		`couponName`,
		`Balance`,
		`merchantId`,
		`createdTime`,
		`couponNo`,
		`Status`,
		`toDate`,
		`createdUser`
	 */
	public PreparedStatement createPreparedStatement(Connection conn)
			throws SQLException {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		PreparedStatement ps = conn.prepareStatement(sql,
				PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setString(1, couponPo.getCouponName());
		ps.setBigDecimal(2, couponPo.getBalance());
		ps.setInt(3, couponPo.getMerchantId());
		ps.setTimestamp(4, now);
		ps.setString(5, couponPo.getCouponNo());
		ps.setString(6, couponPo.getStatus());
		ps.setTimestamp(7, new Timestamp(couponPo.getToDate().getTime()));
		ps.setString(8, couponPo.getCreatedUser());
		ps.setString(9, couponPo.getDescribe());
		ps.setInt(10, couponPo.getCouponType());
		return ps;
	}

}

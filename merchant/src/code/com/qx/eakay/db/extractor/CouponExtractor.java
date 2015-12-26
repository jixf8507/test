package com.qx.eakay.db.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.qx.eakay.model.merchant.CouponPo;


public class CouponExtractor implements ResultSetExtractor<CouponPo> {

	@Override
	public CouponPo extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		if (rs.next()) {
			return new CouponPo(rs);
		}
		return null;
	}

}

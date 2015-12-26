package com.qx.eakay.db.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.qx.eakay.model.order.OrderPO;

public class OrderExtractor implements ResultSetExtractor<OrderPO> {

	@Override
	public OrderPO extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		if (rs.next()) {
			return new OrderPO(rs);
		}
		return null;
	}

}

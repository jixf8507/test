package com.qx.eakay.db.creator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.qx.eakay.model.order.OrderPO;

public class OrderCreator implements PreparedStatementCreator {

	private String sql;
	private OrderPO orderPO;

	public OrderCreator(String sql, OrderPO orderPO) {
		this.sql = sql;
		this.orderPO = orderPO;
	}

	@Override
	public PreparedStatement createPreparedStatement(Connection conn)
			throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql,
				PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setInt(1, orderPO.getCustomerId());
		ps.setInt(2, orderPO.getCarId());
		ps.setInt(3, orderPO.getMerchantId());
		ps.setInt(4, orderPO.getPriceTypeId());
		ps.setString(5, orderPO.getPriceTypeTableName());
		ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
		ps.setFloat(7, orderPO.getKmsForGet());
		ps.setString(8, orderPO.getImgForGet());
		ps.setBigDecimal(9, orderPO.getSocForGet());
		ps.setFloat(10, orderPO.getKmsForGet());
		ps.setString(11, orderPO.getImgForReturn());
		ps.setBigDecimal(12, orderPO.getSocForReturn());
		ps.setTimestamp(13, orderPO.getRealityGetTime());
		ps.setTimestamp(14, orderPO.getSchemingReturnTime());//
		ps.setTimestamp(15, orderPO.getRealityGetTime());
		ps.setInt(16, orderPO.getRelityGetSiteId());
		ps.setInt(17, orderPO.getRelityGetSiteId());
		ps.setInt(18, orderPO.getRelityGetSiteId());
		ps.setString(19, orderPO.getPriceTypeName());
		ps.setString(20, orderPO.getMenForGet());
		ps.setInt(21, orderPO.getRelityGetSiteId());
		ps.setString(22, orderPO.getCustomerTable());
		ps.setInt(23, orderPO.getOrderType());
		ps.setString(24, orderPO.getOrderDescribe());
		ps.setFloat(25, orderPO.getSurplusKmsForGet());
		ps.setFloat(26, orderPO.getSurplusKmsForGet());
		return ps;
	}

}

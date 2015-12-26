package com.qx.eakay.model.price;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.qx.eakay.model.order.OrderPO;

/**
 * 车辆租赁收费套餐
 * 
 * @author jixf
 * @date 2015年8月20日
 */
public abstract class CarLeasePriceType {

	protected Integer id;
	protected Integer merchantId;
	protected String typeName;
	protected String flag;

	public CarLeasePriceType() {

	}

	public CarLeasePriceType(ResultSet rs) throws SQLException {
		this.id = rs.getInt("id");
		this.merchantId = rs.getInt("merchantId");
		this.typeName = rs.getString("typeName");

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * 根据订单计算租赁费用
	 * 
	 * @param orderPO
	 *            车辆租赁订单
	 * @return 计算完费用的租赁订单
	 */
	public abstract OrderPO countCostForOrder(OrderPO orderPO);

}

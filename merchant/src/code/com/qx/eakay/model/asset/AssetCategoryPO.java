package com.qx.eakay.model.asset;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 资产种类
 * 
 * @author Administrator
 *
 */
public class AssetCategoryPO {
	private Integer id;
	private String name;
	private Integer merchantId;
	
	public AssetCategoryPO() {
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}
	
	
	public AssetCategoryPO(ResultSet rs) throws SQLException {
		this.id = rs.getInt("id");
		this.name = rs.getString("name");
		this.merchantId = rs.getInt("merchantId");
	}

}

package com.qx.eakay.model.asset;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 资产维修详细
 * 
 * @author Administrator
 *
 */
public class AssetRepairDetailPO {

	private Integer id;
	private Integer repairId;
	private Integer assetsId;
	private String repairReason;
	private String repairStatus;
	private BigDecimal fee;
	private String accessories;
	
	private String assetsName;
	
	public AssetRepairDetailPO(){
		
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRepairId() {
		return repairId;
	}

	public void setRepairId(Integer repairId) {
		this.repairId = repairId;
	}

	public Integer getAssetsId() {
		return assetsId;
	}

	public void setAssetsId(Integer assetsId) {
		this.assetsId = assetsId;
	}

	public String getRepairReason() {
		return repairReason;
	}

	public void setRepairReason(String repairReason) {
		this.repairReason = repairReason;
	}

	public String getRepairStatus() {
		return repairStatus;
	}

	public void setRepairStatus(String repairStatus) {
		this.repairStatus = repairStatus;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public String getAccessories() {
		return accessories;
	}

	public void setAccessories(String accessories) {
		this.accessories = accessories;
	}
	
	
	public String getAssetsName() {
		return assetsName;
	}

	public void setAssetsName(String assetsName) {
		this.assetsName = assetsName;
	}


	public AssetRepairDetailPO(ResultSet rs) throws SQLException {
		this.id = rs.getInt("id");
		this.repairId = rs.getInt("repairId");
		this.assetsId = rs.getInt("assetsId");
		this.repairReason = rs.getString("repairReason");
		this.repairStatus = rs.getString("repairStatus");
		this.fee = rs.getBigDecimal("fee");
		this.accessories = rs.getString("accessories");
		this.assetsName = rs.getString("assetsName");
		
	}
}

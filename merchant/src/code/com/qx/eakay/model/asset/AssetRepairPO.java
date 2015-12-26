package com.qx.eakay.model.asset;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.qx.eakay.model.car.CarManufacturerPO;

/**
 * 资产维修
 * 
 * @author Administrator
 *
 */
public class AssetRepairPO {
	
	private Integer id;
	private String repairDate;
	private String repairReason;
	private Integer manufacturerId;
	private String applyUser;
	private String transactUser;
	private BigDecimal fee;
	private Integer merchantId;
	private String remarks;
	private Timestamp createdTime;
	
	private List<AssetRepairDetailPO> detailPOs;
	
	//供应商
	public CarManufacturerPO manufacturerPO = new CarManufacturerPO();
	
	public AssetRepairPO() {
		detailPOs = new ArrayList<>();
	}
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getRepairDate() {
		return repairDate;
	}

	public void setRepairDate(String repairDate) {
		this.repairDate = repairDate;
	}

	public String getRepairReason() {
		return repairReason;
	}

	public void setRepairReason(String repairReason) {
		this.repairReason = repairReason;
	}

	public Integer getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(Integer manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	public String getApplyUser() {
		return applyUser;
	}

	public void setApplyUser(String applyUser) {
		this.applyUser = applyUser;
	}

	public String getTransactUser() {
		return transactUser;
	}

	public void setTransactUser(String transactUser) {
		this.transactUser = transactUser;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public CarManufacturerPO getManufacturerPO() {
		return manufacturerPO;
	}

	public void setManufacturerPO(CarManufacturerPO manufacturerPO) {
		this.manufacturerPO = manufacturerPO;
	}
	
	public List<AssetRepairDetailPO> getDetailPOs() {
		return detailPOs;
	}

	public void setDetailPOs(List<AssetRepairDetailPO> detailPOs) {
		this.detailPOs = detailPOs;
	}

	public AssetRepairPO(ResultSet rs) throws SQLException {
		this.id = rs.getInt("id");
		this.repairDate = rs.getString("repairDate");
		this.repairReason = rs.getString("repairReason");
		this.manufacturerId = rs.getInt("manufacturerId");
		this.applyUser = rs.getString("applyUser");
		this.transactUser = rs.getString("transactUser");
		this.fee = rs.getBigDecimal("fee");
		this.merchantId = rs.getInt("merchantId");
		this.remarks = rs.getString("remarks");
		this.createdTime = rs.getTimestamp("createdTime");
		
		// 供应商信息
		this.manufacturerPO = new CarManufacturerPO();
		this.manufacturerPO.setManufacturerName(rs.getString("manufacturerName"));
		this.manufacturerPO.setFullName(rs.getString("fullName"));
		this.manufacturerPO.setAddress(rs.getString("address"));
		this.manufacturerPO.setLinkman(rs.getString("linkman"));
		this.manufacturerPO.setEmail(rs.getString("email"));
	}
}

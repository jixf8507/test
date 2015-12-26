package com.qx.eakay.model.car;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.qx.eakay.model.merchant.SitePO;

/**
 * 车辆
 * 
 * @author jixf
 * @date 2015年7月2日
 */
public class CarPO {

	public static enum CarStatus {
		空闲, 使用, 故障, 充电, 其他
	}

	private Integer id;
	private String carNumber;
	private Integer carTypeId;
	private String color;
	private String describe;
	private Integer chargeDuration;
	private String bigImgs;
	private String littleImgs;
	private String bigIcon;
	private String littleIcon;
	private Date createdTime;
	private Integer belongSiteId;
	private Integer curSiteId;
	private String status;
	private String flag;
	private Integer merchantId;
	private String nsuranceRange;
	private String checkYearDate;
	private Float kms;
	private String vin;
	private BigDecimal soc;
	private Float surplusKms;

	public Float getSurplusKms() {
		return surplusKms;
	}

	public void setSurplusKms(Float surplusKms) {
		this.surplusKms = surplusKms;
	}

	private SitePO sitePO;
	private List<CarDetailPO> carDetails = new ArrayList<>();

	public CarPO() {

	}

	public CarPO(ResultSet rs) throws SQLException {
		id = rs.getInt("id");
		carNumber = rs.getString("carNumber");
		belongSiteId = rs.getInt("belongSiteId");
		carTypeId = rs.getInt("carTypeId");
		chargeDuration = rs.getInt("chargeDuration");
		color = rs.getString("color");
		curSiteId = rs.getInt("curSiteId");
		describe = rs.getString("describe");
		bigIcon = rs.getString("bigIcon");
		nsuranceRange = rs.getString("nsuranceRange");
		checkYearDate = rs.getString("checkYearDate");
		this.kms = rs.getFloat("kms");
		this.status = rs.getString("status");
		this.vin = rs.getString("vin");
		this.soc = rs.getBigDecimal("soc") ;
		this.surplusKms = rs.getFloat("surplusKms") ;
		// 当前租赁点信息
		this.sitePO = new SitePO();
		this.sitePO.setSiteName(rs.getString("siteName"));
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public Integer getCarTypeId() {
		return carTypeId;
	}

	public void setCarTypeId(Integer carTypeId) {
		this.carTypeId = carTypeId;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public Integer getChargeDuration() {
		return chargeDuration;
	}

	public void setChargeDuration(Integer chargeDuration) {
		this.chargeDuration = chargeDuration;
	}

	public String getBigImgs() {
		return bigImgs;
	}

	public void setBigImgs(String bigImgs) {
		this.bigImgs = bigImgs;
	}

	public String getLittleImgs() {
		return littleImgs;
	}

	public void setLittleImgs(String littleImgs) {
		this.littleImgs = littleImgs;
	}

	public String getBigIcon() {
		return bigIcon;
	}

	public void setBigIcon(String bigIcon) {
		this.bigIcon = bigIcon;
	}

	public String getLittleIcon() {
		return littleIcon;
	}

	public void setLittleIcon(String littleIcon) {
		this.littleIcon = littleIcon;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Integer getBelongSiteId() {
		return belongSiteId;
	}

	public void setBelongSiteId(Integer belongSiteId) {
		this.belongSiteId = belongSiteId;
	}

	public Integer getCurSiteId() {
		return curSiteId;
	}

	public void setCurSiteId(Integer curSiteId) {
		this.curSiteId = curSiteId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	public String getNsuranceRange() {
		return nsuranceRange;
	}

	public void setNsuranceRange(String nsuranceRange) {
		this.nsuranceRange = nsuranceRange;
	}

	public String getCheckYearDate() {
		return checkYearDate;
	}

	public void setCheckYearDate(String checkYearDate) {
		this.checkYearDate = checkYearDate;
	}

	public SitePO getSitePO() {
		return sitePO;
	}

	public void setSitePO(SitePO sitePO) {
		this.sitePO = sitePO;
	}

	public Float getKms() {
		return kms;
	}

	public void setKms(Float kms) {
		this.kms = kms;
	}

	public List<CarDetailPO> getCarDetails() {
		return carDetails;
	}

	public void setCarDetails(List<CarDetailPO> carDetails) {
		this.carDetails = carDetails;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public BigDecimal getSoc() {
		return soc;
	}

	public void setSoc(BigDecimal soc) {
		this.soc = soc;
	}

}

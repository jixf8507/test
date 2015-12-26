package com.qx.eakay.model.merchant;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * 租赁点表映射实体对象
 * 
 * @author jixf
 * @2014-9-22
 */

public class SitePO {
	/**
	 * 主键
	 */
	private Integer id = null;

	/**
	 * 租赁点名称
	 */
	private String siteName = null;
	/**
	 * 电话
	 */
	private String phone = null;
	/**
	 * 具体地址
	 */
	private String address = null;
	/**
	 * 租赁点状态.(正常,删除)
	 */
	private String flag = "正常";
	/**
	 * 创建时间
	 */
	private Date createdTime = null;
	/**
	 * 修改时间
	 */
	private Date updatedTime = null;
	/**
	 * 经度
	 */
	private Float lat = 0f;
	/**
	 * 纬度
	 */
	private Float lng = 0f;
	/**
	 * 负责人
	 */
	private String principal;

	private Integer merchantId;

	private Integer type = 0;
	private Integer[] types;

	private String imgUrl;

	private String province;
	private String city;

	public SitePO() {
	}

	public SitePO(ResultSet rs) throws SQLException {
		id = rs.getInt("id");
		siteName = rs.getString("siteName");
		merchantId = rs.getInt("merchantId");
		address = rs.getString("address");
		phone = rs.getString("phone");
		principal = rs.getString("principal");
		lat = rs.getFloat("lat");
		lng = rs.getFloat("lng");
		type = rs.getInt("type");
		imgUrl = rs.getString("imgUrl");
		province = rs.getString("province");
		city = rs.getString("city");
	}

	@Override
	public String toString() {
		return "SitePO [address=" + address + ", createdTime=" + createdTime
				+ ", flag=" + flag + ", id=" + id + ", lat=" + lat + ", lng="
				+ lng + ", phone=" + phone + ", principal=" + principal
				+ ", siteName=" + siteName + ", updatedTime=" + updatedTime
				+ "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Float getLat() {
		return lat;
	}

	public void setLat(Float lat) {
		this.lat = lat;
	}

	public Float getLng() {
		return lng;
	}

	public void setLng(Float lng) {
		this.lng = lng;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer[] getTypes() {
		return types;
	}

	public void setTypes(Integer[] types) {
		this.types = types;
		this.type = 0;
		for (Integer type : types) {
			this.type += type;
		}
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}

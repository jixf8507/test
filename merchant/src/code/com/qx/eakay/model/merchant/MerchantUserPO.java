package com.qx.eakay.model.merchant;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.qx.eakay.util.MD5Util;

/**
 * 
 * @author Administrator
 * 
 */
public class MerchantUserPO {

	private static final String SIMPLE_PASSWORD = "123456";
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 登录邮箱号
	 */
	private String email;
	/**
	 * 登录密码
	 */
	private String password = MD5Util.MD516(SIMPLE_PASSWORD);
	/**
	 * 手机号码
	 */
	private String mobilePhone;
	/**
	 * 用户名称
	 */
	private String userName;
	/**
	 * 用户组ID
	 */
	private Integer siteId;

	private String rights;

	private Integer merchantId;

	public MerchantUserPO() {
	}

	/**
	 * 将数据库查询结果封装成商家用户对象
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public MerchantUserPO(ResultSet rs) throws SQLException {
		this.id = rs.getInt("id");
		this.email = rs.getString("email");
		this.userName = rs.getString("userName");
		this.siteId = rs.getInt("siteId");
		this.password = rs.getString("password");
		this.mobilePhone = rs.getString("mobilePhone");
		this.rights = rs.getString("rights");
		this.merchantId = rs.getInt("merchantId");
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public String getRights() {
		return rights;
	}

	public void setRights(String rights) {
		this.rights = rights;
	}

	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

}

package com.qx.eakay.login;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qx.eakay.model.merchant.MerchantPO;
import com.qx.eakay.model.merchant.MerchantUserPO;

/**
 * 登录用户对象
 * 
 * @author Administrator
 * 
 */
public class SessionUserBO {

	/**
	 * 系统名称
	 */
	private String systemName = "eakay";

	/**
	 * 商家所在城市
	 */
	private String city;

	// 权限类型
	public static enum RightsTypes {
		商家, 商家管理员, 站点管理员, 站点工作人员
	}

	/**
	 * 商家ID
	 */
	private Integer merchantId;
	/**
	 * 用户名称
	 */
	private String name;
	/**
	 * 当前菜单编号
	 */
	private String currenMenuCode;
	/**
	 * 用户权限
	 */
	private String rightsName;
	/**
	 * 商家对象
	 */
	private MerchantPO merchantPO;
	/**
	 * 商家用户
	 */
	private MerchantUserPO merchantUserPO;

	/**
	 * 一级菜单编号
	 */
	private String fstMenuCode;
	/**
	 * 二级菜单编号
	 */
	private String scdMenuCode;
	/**
	 * 三级菜单编号
	 */
	private String thdMenuCode;

	/**
	 * 一级功能模块菜单
	 */
	private List<Map<String, Object>> fmenus = new ArrayList<>();

	/**
	 * 二级功能模块菜单
	 */
	private Map<String, List<Map<String, Object>>> scdMenuMap = new HashMap<>();

	/**
	 * 三级功能模块菜单
	 */
	private Map<String, List<Map<String, Object>>> thdMenuMap = new HashMap<>();

	public SessionUserBO() {

	}

	public SessionUserBO(String systemName) {
		this.systemName = systemName;
	}

	/**
	 * 设置用户的功能菜单
	 * 
	 * @param menus
	 */
	public void setMoudlesMunes(List<Map<String, Object>> menus) {
		for (Map<String, Object> map : menus) {
			String level = map.get("level") + "";
			String pid = map.get("menuCode") + "";
			String[] codes = pid.split("_");
			map.put("moduleUrl",
					map.get("moduleUrl") + "menuCode=" + map.get("menuCode"));
			if (level.equals("0")) {
				fmenus.add(map);
			} else if (level.equals("1")) {
				String pCode = codes[0];
				List<Map<String, Object>> level1List = scdMenuMap.get(pCode);
				if (level1List == null) {
					level1List = new ArrayList<>();
					scdMenuMap.put(pCode, level1List);
				}
				scdMenuMap.get(pCode).add(map);
			} else if (level.equals("2")) {
				String pCode = codes[0] + "_" + codes[1];
				List<Map<String, Object>> level2List = thdMenuMap.get(pCode);
				if (level2List == null) {
					level2List = new ArrayList<>();
					thdMenuMap.put(pCode, level2List);
				}
				thdMenuMap.get(pCode).add(map);
			}
		}
	}

	/**
	 * 获取一级功能菜单列表
	 * 
	 * @return
	 */
	public List<Map<String, Object>> getFmenus() {
		return fmenus;
	}

	/**
	 * 获取二级功能菜单列表
	 * 
	 * @param pid
	 * @return
	 */
	public Map<String, List<Map<String, Object>>> getScdMenuMap() {
		return scdMenuMap;
	}

	/**
	 * 获取三级功能菜单列表
	 * 
	 * @param pId
	 * @return
	 */

	public Map<String, List<Map<String, Object>>> getThdMenuMap() {
		return thdMenuMap;
	}

	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRightsName() {
		return rightsName;
	}

	public void setRightsName(String rightsName) {
		this.rightsName = rightsName;
	}

	public MerchantPO getMerchantPO() {
		return merchantPO;
	}

	public void setMerchantPO(MerchantPO merchantPO) {
		this.merchantPO = merchantPO;
	}

	public MerchantUserPO getMerchantUserPO() {
		return merchantUserPO;
	}

	public void setMerchantUserPO(MerchantUserPO merchantUserPO) {
		this.merchantUserPO = merchantUserPO;
	}

	public String getCurrenMenuCode() {
		return currenMenuCode;
	}

	public void setCurrenMenuCode(String currenMenuCode) {
		this.currenMenuCode = currenMenuCode;
		if (currenMenuCode != null && !currenMenuCode.equals("")) {
			String[] codes = currenMenuCode.split("_");
			switch (codes.length) {
			case 3:
				this.fstMenuCode = codes[0];
				this.scdMenuCode = codes[0] + "_" + codes[1];
				this.thdMenuCode = currenMenuCode;
				break;
			case 2:
				this.fstMenuCode = codes[0];
				this.scdMenuCode = currenMenuCode;
				this.thdMenuCode = null;
				break;
			default:
				this.fstMenuCode = currenMenuCode;
				this.scdMenuCode = null;
				this.thdMenuCode = null;
				break;
			}
		}
	}

	public String getFstMenuCode() {
		return this.fstMenuCode;
	}

	public String getScdMenuCode() {
		return this.scdMenuCode;
	}

	public String getThdMenuCode() {
		return this.thdMenuCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * 是否管理员
	 * 
	 * @return
	 */
	public boolean isManagerUser() {
		if (RightsTypes.商家.name().equals(this.rightsName)
				|| RightsTypes.商家管理员.name().equals(this.rightsName)) {
			return true;
		}
		return false;
	}

	public String getSystemName() {
		return systemName;
	}

}

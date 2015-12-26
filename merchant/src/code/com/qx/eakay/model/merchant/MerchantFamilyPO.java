package com.qx.eakay.model.merchant;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.qx.eakay.model.sys.SysFileUrlsPO;

/**
*ps.setInteger(i++, merchantFamilyPO.getId());
*ps.setString(i++, merchantFamilyPO.getHouseholder());
*ps.setString(i++, merchantFamilyPO.getHomeaddress());
*ps.setString(i++, merchantFamilyPO.getHomephone());
*ps.setInteger(i++, merchantFamilyPO.getCustomerid());
*ps.setInteger(i++, merchantFamilyPO.getMerchantid());
*ps.setDate(i++, merchantFamilyPO.getCreatedtime());

*merchantFamilyPO.setId(rs.getInteger("id"));
*merchantFamilyPO.setHouseholder(rs.getString("householder"));
*merchantFamilyPO.setHomeaddress(rs.getString("homeAddress"));
*merchantFamilyPO.setHomephone(rs.getString("homePhone"));
*merchantFamilyPO.setCustomerid(rs.getInteger("customerId"));
*merchantFamilyPO.setMerchantid(rs.getInteger("merchantId"));
*merchantFamilyPO.setCreatedtime(rs.getDate("createdTime"));

*,merchantFamilyPO.getId()
*,merchantFamilyPO.getHouseholder()
*,merchantFamilyPO.getHomeaddress()
*,merchantFamilyPO.getHomephone()
*,merchantFamilyPO.getCustomerid()
*,merchantFamilyPO.getMerchantid()
*,merchantFamilyPO.getCreatedtime()
 */

/**
 * @author Administrator
 * @date 2015-11-24 11:49:00
 */
public class MerchantFamilyPO  {
	
		public  static enum MerchantFamilyType{
			正常,冻结,删除
		}

			private Integer id;
		/**
		 *  户主
		 */
			private String householder;
		/**
		 *  家庭住址
		 */
			private String homeaddress;
		/**
		 *  家庭电话
		 */
			private String homephone;
		/**
		 *  客户ID
		 */
			private Integer customerid;
		/**
		 *  商家ID
		 */
			private Integer merchantid;
		/**
		 *  创建时间
		 */
			private Date createdtime;
			private String createdtimeStr;
			
		/**
		 * 状态
		 */
			
			private String status;
	
	
		/**
		 * 家庭图片
		 * @param rs
		 * @throws SQLException
		 */
			
		private SysFileUrlsPO sysFileUrlsPO;
	
		
		/**
		 * 家庭 关联customer，关联account的保证金字段moneyofassure
		 * @return
		 */
		private String moneyOfAssure;





		public String getMoneyOfAssure() {
			return moneyOfAssure;
		}





		public void setMoneyOfAssure(String moneyOfAssure) {
			this.moneyOfAssure = moneyOfAssure;
		}





		public SysFileUrlsPO getSysFileUrlsPO() {
			return sysFileUrlsPO;
		}





		public void setSysFileUrlsPO(SysFileUrlsPO sysFileUrlsPO) {
			this.sysFileUrlsPO = sysFileUrlsPO;
		}





	public MerchantFamilyPO (ResultSet rs) throws SQLException {
				this.id = rs.getInt("id");
				this.householder = rs.getString("householder");
				this.homeaddress = rs.getString("homeAddress");
				this.homephone = rs.getString("homePhone");
				this.customerid = rs.getInt("customerId");
				this.merchantid = rs.getInt("merchantId");
				this.createdtime = rs.getDate("createdTime");
				this.status = rs.getString("status");
	}
	
	
	
	
	
	public MerchantFamilyPO() {

	}
	
	
	
			public Integer getId() {
				return id;
			}
			public void setId(Integer id) {
				this.id = id;
			}
	/**
	 *  户主
	 */
	
			public String getHouseholder() {
				return householder;
			}
			public void setHouseholder(String householder) {
				this.householder = householder;
			}
	/**
	 *  家庭住址
	 */
	
			public String getHomeaddress() {
				return homeaddress;
			}
			public void setHomeaddress(String homeaddress) {
				this.homeaddress = homeaddress;
			}
	/**
	 *  家庭电话
	 */
	
			public String getHomephone() {
				return homephone;
			}
			public void setHomephone(String homephone) {
				this.homephone = homephone;
			}
	/**
	 *  客户ID
	 */
	
			public Integer getCustomerid() {
				return customerid;
			}
			public void setCustomerid(Integer customerid) {
				this.customerid = customerid;
			}
	/**
	 *  商家ID
	 */
	
			public Integer getMerchantid() {
				return merchantid;
			}
			public void setMerchantid(Integer merchantid) {
				this.merchantid = merchantid;
			}
	/**
	 *  创建时间
	 */
	
			public Date getCreatedtime() {
				return createdtime;
			}
			public String getCreatedtimeStr() {
				return createdtimeStr;
			}
			public void setCreatedtime(Date createdtime) {
				this.createdtime = createdtime;
			}
			public void setCreatedtimeStr (String createdtimeStr) {
				this.createdtimeStr = createdtimeStr;
			}

			public String getStatus() {
				return status;
			}
			public void setStatus(String status) {
				this.status = status;
			}





			@Override
			public String toString() {
				return "MerchantFamilyPO [id=" + id + ", householder="
						+ householder + ", homeaddress=" + homeaddress
						+ ", homephone=" + homephone + ", customerid="
						+ customerid + ", merchantid=" + merchantid
						+ ", createdtime=" + createdtime + ", createdtimeStr="
						+ createdtimeStr + ", status=" + status
						+ ", sysFileUrlsPO=" + sysFileUrlsPO
						+ ", moneyOfAssure=" + moneyOfAssure + "]";
			}





		



		
			
}
package com.qx.eakay.model.merchant;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
*ps.setInteger(i++, merchantFamilyCustomerPO.getId());
*ps.setInteger(i++, merchantFamilyCustomerPO.getFamilyid());
*ps.setInteger(i++, merchantFamilyCustomerPO.getCustomerid());

*merchantFamilyCustomerPO.setId(rs.getInteger("id"));
*merchantFamilyCustomerPO.setFamilyid(rs.getInteger("familyId"));
*merchantFamilyCustomerPO.setCustomerid(rs.getInteger("customerId"));

*,merchantFamilyCustomerPO.getId()
*,merchantFamilyCustomerPO.getFamilyid()
*,merchantFamilyCustomerPO.getCustomerid()
 */

/**
 * @author Administrator
 * @date 2015-11-26 16:37:27
 */
public class MerchantFamilyCustomerPO  {

			private Integer id;
			private Integer familyid;
			private Integer customerid;
	
	
	
	
	
	public MerchantFamilyCustomerPO (ResultSet rs) throws SQLException {
				this.id = rs.getInt("id");
				this.familyid = rs.getInt("familyId");
				this.customerid = rs.getInt("customerId");
	}
	
	
	
	
	
	public MerchantFamilyCustomerPO() {

	}
	
			public Integer getId() {
				return id;
			}
			public void setId(Integer id) {
				this.id = id;
			}
	
			public Integer getFamilyid() {
				return familyid;
			}
			public void setFamilyid(Integer familyid) {
				this.familyid = familyid;
			}
	
			public Integer getCustomerid() {
				return customerid;
			}
			public void setCustomerid(Integer customerid) {
				this.customerid = customerid;
			}
}
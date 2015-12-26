package com.qx.eakay.model.customer;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
*ps.setInteger(i++, customerDayScorePO.getId());
*ps.setInteger(i++, customerDayScorePO.getCustomerid());
*ps.setString(i++, customerDayScorePO.getDay());
*ps.setInteger(i++, customerDayScorePO.getAddscore());
*ps.setInteger(i++, customerDayScorePO.getReducedscore());
*ps.setInteger(i++, customerDayScorePO.getTotlescore());
*ps.setInteger(i++, customerDayScorePO.getRegisteredscore());
*ps.setInteger(i++, customerDayScorePO.getCertifiedscore());
*ps.setInteger(i++, customerDayScorePO.getAssurescore());
*ps.setInteger(i++, customerDayScorePO.getPayscore());
*ps.setInteger(i++, customerDayScorePO.getCartimescore());
*ps.setInteger(i++, customerDayScorePO.getRefereescore());
*ps.setInteger(i++, customerDayScorePO.getBerefereescore());
*ps.setInteger(i++, customerDayScorePO.getPeccancyscore());
*ps.setInteger(i++, customerDayScorePO.getDamagescore());
*ps.setDate(i++, customerDayScorePO.getCreatedtime());

*customerDayScorePO.setId(rs.getInteger("id"));
*customerDayScorePO.setCustomerid(rs.getInteger("customerId"));
*customerDayScorePO.setDay(rs.getString("day"));
*customerDayScorePO.setAddscore(rs.getInteger("addScore"));
*customerDayScorePO.setReducedscore(rs.getInteger("reducedScore"));
*customerDayScorePO.setTotlescore(rs.getInteger("totleScore"));
*customerDayScorePO.setRegisteredscore(rs.getInteger("registeredScore"));
*customerDayScorePO.setCertifiedscore(rs.getInteger("certifiedScore"));
*customerDayScorePO.setAssurescore(rs.getInteger("assureScore"));
*customerDayScorePO.setPayscore(rs.getInteger("payScore"));
*customerDayScorePO.setCartimescore(rs.getInteger("carTimeScore"));
*customerDayScorePO.setRefereescore(rs.getInteger("refereeScore"));
*customerDayScorePO.setBerefereescore(rs.getInteger("beRefereeScore"));
*customerDayScorePO.setPeccancyscore(rs.getInteger("peccancyScore"));
*customerDayScorePO.setDamagescore(rs.getInteger("damageScore"));
*customerDayScorePO.setCreatedtime(rs.getDate("createdTime"));

*,customerDayScorePO.getId()
*,customerDayScorePO.getCustomerid()
*,customerDayScorePO.getDay()
*,customerDayScorePO.getAddscore()
*,customerDayScorePO.getReducedscore()
*,customerDayScorePO.getTotlescore()
*,customerDayScorePO.getRegisteredscore()
*,customerDayScorePO.getCertifiedscore()
*,customerDayScorePO.getAssurescore()
*,customerDayScorePO.getPayscore()
*,customerDayScorePO.getCartimescore()
*,customerDayScorePO.getRefereescore()
*,customerDayScorePO.getBerefereescore()
*,customerDayScorePO.getPeccancyscore()
*,customerDayScorePO.getDamagescore()
*,customerDayScorePO.getCreatedtime()
 */

/**
 * @author Administrator
 * @date 2015-12-10 11:11:31
 */
public class CustomerDayScorePO  {

			private Integer id;
		/**
		 *  客户ID
		 */
			private Integer customerid;
		/**
		 *  日期
		 */
			private String day;
		/**
		 *  每日增加的信誉积分
		 */
			private Integer addscore;
		/**
		 *  每日减少的信誉积分
		 */
			private Integer reducedscore;
		/**
		 *  每日总的信誉积分
		 */
			private Integer totlescore;
		/**
		 *  注册获取积分
		 */
			private Integer registeredscore;
		/**
		 *  通过审核获取的积分
		 */
			private Integer certifiedscore;
		/**
		 *  缴纳保证金获取的积分
		 */
			private Integer assurescore;
		/**
		 *  租赁付费获取的信誉额度
		 */
			private Integer payscore;
		/**
		 *  用车时长获取的积分
		 */
			private Integer cartimescore;
		/**
		 *  推荐用户获取的信誉积分
		 */
			private Integer refereescore;
		/**
		 *  被推荐注册获取的信誉积分
		 */
			private Integer berefereescore;
		/**
		 *  违章获取的积分（负数）
		 */
			private Integer peccancyscore;
		/**
		 *  车损获取的积分（负数）
		 */
			private Integer damagescore;
		/**
		 *  创建记录时间
		 */
			private Date createdtime;
			private String createdtimeStr;
	
	
	
	
	
	public CustomerDayScorePO (ResultSet rs) throws SQLException {
				this.id = rs.getInt("id");
				this.customerid = rs.getInt("customerId");
				this.day = rs.getString("day");
				this.addscore = rs.getInt("addScore");
				this.reducedscore = rs.getInt("reducedScore");
				this.totlescore = rs.getInt("totleScore");
				this.registeredscore = rs.getInt("registeredScore");
				this.certifiedscore = rs.getInt("certifiedScore");
				this.assurescore = rs.getInt("assureScore");
				this.payscore = rs.getInt("payScore");
				this.cartimescore = rs.getInt("carTimeScore");
				this.refereescore = rs.getInt("refereeScore");
				this.berefereescore = rs.getInt("beRefereeScore");
				this.peccancyscore = rs.getInt("peccancyScore");
				this.damagescore = rs.getInt("damageScore");
				this.createdtime = rs.getDate("createdTime");
	}
	
	
	
	
	
	public CustomerDayScorePO() {

	}
	
			public Integer getId() {
				return id;
			}
			public void setId(Integer id) {
				this.id = id;
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
	 *  日期
	 */
	
			public String getDay() {
				return day;
			}
			public void setDay(String day) {
				this.day = day;
			}
	/**
	 *  每日增加的信誉积分
	 */
	
			public Integer getAddscore() {
				return addscore;
			}
			public void setAddscore(Integer addscore) {
				this.addscore = addscore;
			}
	/**
	 *  每日减少的信誉积分
	 */
	
			public Integer getReducedscore() {
				return reducedscore;
			}
			public void setReducedscore(Integer reducedscore) {
				this.reducedscore = reducedscore;
			}
	/**
	 *  每日总的信誉积分
	 */
	
			public Integer getTotlescore() {
				return totlescore;
			}
			public void setTotlescore(Integer totlescore) {
				this.totlescore = totlescore;
			}
	/**
	 *  注册获取积分
	 */
	
			public Integer getRegisteredscore() {
				return registeredscore;
			}
			public void setRegisteredscore(Integer registeredscore) {
				this.registeredscore = registeredscore;
			}
	/**
	 *  通过审核获取的积分
	 */
	
			public Integer getCertifiedscore() {
				return certifiedscore;
			}
			public void setCertifiedscore(Integer certifiedscore) {
				this.certifiedscore = certifiedscore;
			}
	/**
	 *  缴纳保证金获取的积分
	 */
	
			public Integer getAssurescore() {
				return assurescore;
			}
			public void setAssurescore(Integer assurescore) {
				this.assurescore = assurescore;
			}
	/**
	 *  租赁付费获取的信誉额度
	 */
	
			public Integer getPayscore() {
				return payscore;
			}
			public void setPayscore(Integer payscore) {
				this.payscore = payscore;
			}
	/**
	 *  用车时长获取的积分
	 */
	
			public Integer getCartimescore() {
				return cartimescore;
			}
			public void setCartimescore(Integer cartimescore) {
				this.cartimescore = cartimescore;
			}
	/**
	 *  推荐用户获取的信誉积分
	 */
	
			public Integer getRefereescore() {
				return refereescore;
			}
			public void setRefereescore(Integer refereescore) {
				this.refereescore = refereescore;
			}
	/**
	 *  被推荐注册获取的信誉积分
	 */
	
			public Integer getBerefereescore() {
				return berefereescore;
			}
			public void setBerefereescore(Integer berefereescore) {
				this.berefereescore = berefereescore;
			}
	/**
	 *  违章获取的积分（负数）
	 */
	
			public Integer getPeccancyscore() {
				return peccancyscore;
			}
			public void setPeccancyscore(Integer peccancyscore) {
				this.peccancyscore = peccancyscore;
			}
	/**
	 *  车损获取的积分（负数）
	 */
	
			public Integer getDamagescore() {
				return damagescore;
			}
			public void setDamagescore(Integer damagescore) {
				this.damagescore = damagescore;
			}
	/**
	 *  创建记录时间
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
}
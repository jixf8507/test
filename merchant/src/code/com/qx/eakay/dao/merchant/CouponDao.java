package com.qx.eakay.dao.merchant;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.qx.common.base.PageResults;
import com.qx.common.dao.BaseDao;
import com.qx.common.sql.JSONSqlMapping;
import com.qx.eakay.db.creator.CouponCreator;
import com.qx.eakay.db.extractor.CouponExtractor;
import com.qx.eakay.model.merchant.CouponPo;
import com.qx.eakay.model.merchant.CouponPo.CouponPoStatus;

/**
 * 优惠卷对的数据库操作
 * 
 * @author Administrator
 * 
 */

@Repository
public class CouponDao extends BaseDao {
	Logger log = LoggerFactory.getLogger(CouponDao.class);

	@Resource(name = "coupon_select_sql")
	private JSONSqlMapping couponSelectSQL;

	/**
	 * 分页查询优惠券
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findCouponPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return this.findPageByJSONSqlMapping(couponSelectSQL, jsonObject,
				pageSize, iDisplayStart);
	}

	/**
	 * 条件搜索优惠券
	 */
	public List<Map<String, Object>> findCoupon(JSONObject jsonObject) {
		return this.findListByJSONSqlMapping(couponSelectSQL, jsonObject);
	}

	/**
	 * 根据ID查找优惠券
	 * 
	 * @param id
	 * @return
	 */
	public CouponPo get(Integer id) {
		return this.getJdbcTemplate().query(GET_SQL, new Object[] { id },
				new CouponExtractor());
	}

	/**
	 * 新增优惠券
	 * 
	 * @param sitePO
	 * @return
	 */
	public boolean create(CouponPo couponPo) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.getJdbcTemplate().update(new CouponCreator(INSERT_SQL, couponPo),
				keyHolder);
		System.out.println(keyHolder.getKey().intValue() + "主键");
		return keyHolder.getKey().intValue() > 0;
	}

	/**
	 * 删除优惠券
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(Integer id, String status) {
		int count = this.getJdbcTemplate().update(DELETE_SQL, status, id);
		return count > 0;
	}

	/**
	 * 更改优惠券
	 * 
	 * @param couponPo
	 * @return
	 */
	public boolean update(CouponPo couponPo) {
		int count = this.getJdbcTemplate().update(UPDATE_SQL,
				couponPo.getCouponName(), couponPo.getBalance(),
				couponPo.getCustomerId(), couponPo.getMerchantId(),
				couponPo.getCreatedTime(), couponPo.getCouponNo(),
				couponPo.getStatus(), couponPo.getToDate(),
				couponPo.getCreatedUser(), couponPo.getId());
		return count > 0;
	}
	
	/**
	 * 绑定用户到优惠券
	 * 
	 * @param couponPo
	 * @return
	 */
	public boolean bindCustomer(CouponPo couponPo) {
		int count = this.getJdbcTemplate().update(BIND_CUSTOMER_SQL,couponPo.getCustomerId(),couponPo.getId());
		return count > 0;
	}

	/**
	 * 查找超时优惠券
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Map<String, Object>> findOutTimeCoupon(Timestamp currentDate) {
		return this.findListBySQL(FIND_OUT_TIME_COUPON,
				new Object[] { currentDate });
	}

	/**
	 * 批量更新优惠券状态
	 * 
	 * @param orderStatus
	 * @param subOrderStatus
	 * @param oIds
	 * @return
	 */
	public boolean batchUpdateStaus(final List<Integer> cIds) {
		int[] ids = this.getJdbcTemplate().batchUpdate(OUT_TIME_SQL,
				new BatchPreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						ps.setString(1, CouponPoStatus.过期 + "");
						ps.setInt(2, cIds.get(i));
						ps.setString(3, CouponPoStatus.待用 + "");
					}

					@Override
					public int getBatchSize() {
						return cIds.size();
					}
				});
		return ids.length != cIds.size();
	}

	// 更改优惠券SQL
	private static final String UPDATE_SQL = "UPDATE coupon co SET  co.couponName = ?, co.Balance = ?, co.customerId = ?, co.merchantId = ?, co.createdTime = ?, co.couponNo = ?, co.status = ? , co.toDate = ? , co.createdUser = ? WHERE co.id = ?";
	// 根据id查找优惠券
	private static final String GET_SQL = "SELECT * FROM  coupon co WHERE co.id=?";
	// 增加优惠券
	private static final String INSERT_SQL = "INSERT INTO coupon (`couponName`, `Balance`,  `merchantId`, `createdTime`, `couponNo`, `Status`, `toDate`,`createdUser`,`describe`,`couponType`) VALUES (?, ?, ?, ?, ?, ?, ? , ? ,? ,?)";
	// 作废优惠券
	private static final String DELETE_SQL = "UPDATE coupon set `status`=? WHERE id = ?";
	// 使用优惠券
	private static final String USE_SQL = "UPDATE coupon set `status`=?,customerId=? WHERE id = ?";
	// 过期待使用状态的优惠券
	private static final String OUT_TIME_SQL = "UPDATE coupon set `status`=? WHERE id = ? AND `status`=?";
	// 找到过期的优惠券
	private static final String FIND_OUT_TIME_COUPON = "SELECT * FROM coupon c WHERE 1=1 AND c.toDate < ?";
	
	// 绑定用户到优惠券
	private static final String BIND_CUSTOMER_SQL = "UPDATE coupon co SET  co.customerId = ? WHERE co.id = ?";
	
	
	/**
	 * 使用优惠券
	 * 
	 * @param customerId
	 * @param id
	 * @param status
	 * @return
	 */
	public boolean useCoupon(Integer customerId, Integer id, String status) {
		return this.getJdbcTemplate().update(USE_SQL, status, customerId, id) > 0;
	}

}

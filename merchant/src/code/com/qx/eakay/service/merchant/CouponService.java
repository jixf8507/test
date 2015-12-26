package com.qx.eakay.service.merchant;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qx.common.base.BaseExport;
import com.qx.common.base.PageResults;
import com.qx.eakay.dao.merchant.CouponDao;
import com.qx.eakay.export.coupon.CouponExport;
import com.qx.eakay.model.merchant.CouponPo;
import com.qx.eakay.model.merchant.CouponPoExtend;
import com.qx.eakay.service.BaseService;

@Service
public class CouponService extends BaseService{
	
	Logger log = LoggerFactory.getLogger(CouponService.class);
	
	@Autowired
	private CouponDao couponDao;
	/**
	 * 分页查询租赁点
	 * 
	 * @param jsonObject
	 * @param pageSize
	 * @param iDisplayStart
	 * @return
	 */
	public PageResults findCouponPage(JSONObject jsonObject, int pageSize,
			int iDisplayStart) {
		return couponDao.findCouponPage(jsonObject, pageSize, iDisplayStart);
	}
	
	/**
	 * 根据ID查找优惠券
	 * 
	 * @param id
	 * @return
	 */
	public CouponPo get(Integer id) {
		return couponDao.get(id);
	}
	
	/**
	 * 新增优惠券
	 * 
	 * @param sitePO
	 * @return
	 */
	public Boolean create(CouponPo couponPo) {
		return couponDao.create(couponPo);
	}
	
	/**
	 * 批量新增优惠券
	 */
	@Transactional
	public Boolean createByNumber(CouponPoExtend couponPoExtend){
		try {
			
			/**
			 * 循环次数批量插入数据
			 */
			for (int i = 0; i < couponPoExtend.getNumber(); i++) {
				this.create(couponPoExtend);
			}
			return true;
		} catch (Exception e) {
			logger.error("*******************新增优惠券失败****************");
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return false;
	}
	
	
	/**
	 * 删除优惠券
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(Integer id,String status) {
			return couponDao.delete(id,status);
	}
	
	/**
	 * 批量作废优惠券
	 */
	@Transactional
	 public boolean batchDelete(List<Integer> ids, String status) {
		try {
			for (Integer id : ids) {
				couponDao.delete(id,status);
			}
			return true;
		} catch (Exception e) {
			logger.error("*******************批量删除优惠券失败****************");
			log.error(e.getMessage());
			e.printStackTrace();
			
		}
		return false;	
	}
	
	
	/**
	 * 更改优惠券
	 * @param couponPo
	 * @return
	 */
	public boolean update(CouponPo couponPo) {
		return couponDao.update(couponPo);
	}
	
	/**
	 *绑定用户到优惠券
	 * @param couponPo
	 * @return
	 */
	public boolean bindCustomer(CouponPo couponPo) {
		return couponDao.bindCustomer(couponPo);
	}
	
	
		
	
	
	/**
	 * 查找超时优惠券
	 * 
	 * @param orders
	 * @return
	 */
	public List<Map<String, Object>> findOutTimeCoupon(Timestamp currentDate) {
		return couponDao.findOutTimeCoupon(currentDate);
	}
	
	
	/**
	 * 过期超时优惠券
	 * @param orders
	 */
	@Transactional
	public void outCoupon(List<Map<String, Object>> coupons){
		
		List<Integer> couponIds = new ArrayList<>();
		for (Map<String, Object> coupon : coupons) {
			Integer couponId = Integer.parseInt(coupon.get("id") + "");
			couponIds.add(couponId);
		}
		
		//批量改优惠券状态为过期
		couponDao.batchUpdateStaus(couponIds);
	}
	
	/**
	 * 导出待使用优惠券
	 * 
	 * @param response
	 * @param jsonObject
	 */
	public void excelCouponPage(HttpServletResponse response,
			JSONObject jsonObject) {
		BaseExport export = new CouponExport();
		List<Map<String, Object>> list = couponDao.findCoupon(jsonObject);
		try {
			export.toExcel(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 使用优惠券
	 * 
	 * @param customerId
	 * @param id
	 * @param string
	 * @return
	 */
	public boolean useCoupon(Integer customerId, Integer id, String status) {
		return couponDao.useCoupon(customerId,id,status);
	}
	
	
	
}


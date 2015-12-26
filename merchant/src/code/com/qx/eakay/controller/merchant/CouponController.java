package com.qx.eakay.controller.merchant;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qx.common.base.PageHelp;
import com.qx.common.base.PageResults;
import com.qx.common.tools.JSONTools;
import com.qx.common.tools.StringTools;
import com.qx.eakay.controller.BaseController;
import com.qx.eakay.login.SessionUserBO;
import com.qx.eakay.model.merchant.CouponPo;
import com.qx.eakay.model.merchant.CouponPoExtend;
import com.qx.eakay.service.merchant.CouponService;
import com.qx.eakay.util.MSG;

@Controller
@RequestMapping("/merchant/coupon")
public class CouponController extends BaseController {
	@Autowired
	private CouponService couponService;

	/**
	 * 进入优惠券管理页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("page.htm")
	public ModelAndView page() throws Exception {
		return new ModelAndView("merchant/couponManager");
	}
	
	/**
	 * 跳转到绑定用户界面，单选用户，绑定用户给优惠券。
	 */
	@RequestMapping("bindCustomer.htm")
	public ModelAndView bindCustomerPage(){
		return new ModelAndView("merchant/bindCustomer");
	}
	
	
	/**
	 * 绑定用户给优惠券
	 */
	@RequestMapping("doBindCustomer.htm")
	@ResponseBody
	public MSG doBindCustomer(Integer customerId,Integer couponId){
		MSG msg = new MSG();
		//将优惠券的customerId给update。
//		CouponPo couponPo=new CouponPo();
		CouponPo couponPo=couponService.get(couponId);
		couponPo.setCustomerId(customerId);
		msg.setSuccess(couponService.bindCustomer(couponPo));
		return msg;
	}

	
	/**
	 * 分页查询优惠券
	 * @param aoData
	 * @param paraData
	 * @param session
	 * @return
	 */
	@RequestMapping(value="ajaxData.htm")
	@ResponseBody
	public PageResults ajaxData(String aoData, String paraData,
			HttpSession session) {
		JSONObject jsonObject = getJsonPara(paraData, session);
		// jquery.datatables 分页查询的参数
		JSONArray jsonArray = JSONArray.fromObject(aoData);
		PageHelp pageHelp = JSONTools.toPageHelp(jsonArray);
		PageResults pageResults = couponService.findCouponPage(jsonObject,
				pageHelp.getiDisplayLength(), pageHelp.getiDisplayStart());
		pageResults.setsEcho(pageHelp.getsEcho());
		return pageResults;
	}

	/**
	 * 得到请求参数条件
	 * 
	 * @param paraData
	 * @param session
	 * @return
	 */
	private JSONObject getJsonPara(String paraData, HttpSession session) {
		paraData = StringTools.decodeMethod(paraData);
		// 动态查询条件参数
		JSONObject jsonObject = JSONObject.fromObject(paraData);
		SessionUserBO sessionUser = this.getSessionUser(session);
		jsonObject.put("merchantId", sessionUser.getMerchantId());
		return jsonObject;
	}
	
	
	/**
	 * 跳转到新增优惠券页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("add.htm")
	public ModelAndView add() throws Exception {
		return new ModelAndView("merchant/couponAdd");
	}
	
	/**
	 * 批量新增优惠券
	 * 
	 * @param sitePO
	 * @return
	 */
	@RequestMapping("saveAdd.htm")
	@ResponseBody
	public MSG add(CouponPoExtend couponPoExtend,HttpSession session) {
		MSG msg =new MSG();
		SessionUserBO sessionUser = this.getSessionUser(session);
		couponPoExtend.setMerchantId(sessionUser.getMerchantId());
		couponPoExtend.setCreatedUser(sessionUser.getName());
		msg.setSuccess(couponService.createByNumber(couponPoExtend));
		return msg;
	}
	
	
	/**
	 * 根据ID查找优惠券
	 * 
	 * @param id
	 * @return
	 */
	public CouponPo get(Integer id) {
		return couponService.get(id);
	}
	
	
	
	
	/**
	 * 作废优惠券
	 * 
	 * @param id
	 * @return
	 */
	 @RequestMapping("delete.htm")
	 @ResponseBody
	public MSG delete(Integer id,String status) {
		MSG msg = new MSG();
		msg.setSuccess(couponService.delete(id,status));
		return msg;
	}
	 
	 
	 /**
	  * 批量作废优惠券
	  */
	 @RequestMapping("batchDelete.htm")
	 @ResponseBody
	public MSG batchDelete(String idsJson) {
		MSG msg = new MSG();
		String[] idsStrs = idsJson.split(",");
		List<Integer> ids = new ArrayList<Integer>();
		for (String id : idsStrs) {
			ids.add(Integer.parseInt(id));
		}
		msg.setSuccess(couponService.batchDelete(ids, "作废"));
		return msg;
	}
	
	
	
	/**
	 * 更改优惠券
	 * @param couponPo
	 * @return
	 */
	public boolean update(CouponPo couponPo) {
		return couponService.update(couponPo);
	}
	
	
	/**
	 * 导出待使用到Excel表格
	 * 
	 * @param paraData
	 * @param session
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("exportToExcel.htm")
	public ModelAndView exportToExcel(String paraData, HttpSession session,
			HttpServletResponse response) throws Exception {
		JSONObject jsonObject = getJsonPara(paraData, session);
		couponService.excelCouponPage(response, jsonObject);
		return null;
	}
	
	
	
	

}

package com.qx.eakay.controller.price;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qx.common.tools.StringTools;
import com.qx.eakay.controller.BaseController;
import com.qx.eakay.login.SessionUserBO;
import com.qx.eakay.service.price.PriceTypeRelationService;
import com.qx.eakay.util.MSG;

/**
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/price/typeRelation")
public class PriceTypeRelationController extends BaseController {

	@Autowired
	private PriceTypeRelationService priceTypeRelationService;

	/**
	 * 动态加载车辆的套餐列表
	 * 
	 * @param carId
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "ajaxCarTypes.htm")
	@ResponseBody
	public List<Map<String, Object>> ajaxCarTypes(String paraData,
			HttpSession session) {
		paraData = StringTools.decodeMethod(paraData);
		// 动态查询条件参数
		JSONObject jsonObject = JSONObject.fromObject(paraData);

		SessionUserBO sessionUser = this.getSessionUser(session);
		jsonObject.put("merchantId", sessionUser.getMerchantId());
		
		return priceTypeRelationService.findPriceByCarId(jsonObject);
	}
//	/**
//	 * 动态加载车辆的套餐列表
//	 * 
//	 * @param carId
//	 * @param session
//	 * @return
//	 */
//	@RequestMapping(value = "ajaxCarTypes.htm")
//	@ResponseBody
//	public List<Map<String, Object>> ajaxCarTypes(Integer carId,String tableName,
//			HttpSession session) {
//		return priceTypeRelationService.findPriceByCarId(carId,tableName, this
//				.getSessionUser(session).getMerchantId());
//	}

	/**
	 * 保存车辆收费套餐
	 * 
	 * @param carId
	 *            车辆ID
	 * @param typeId
	 * @return
	 */
	@RequestMapping(value = "saveCarTypes.htm")
	@ResponseBody
	public MSG saveCarTypes(Integer carId, Integer[] typeId,String tableName) {
		MSG msg = new MSG();
		boolean bool = priceTypeRelationService.saveRelation(carId, typeId, tableName);
		msg.setSuccess(bool);
		return msg;
	}

	@RequestMapping("savePriceCars.htm")
	@ResponseBody
	public MSG savePriceCars(Integer id, String carId, String tableName) throws Exception {
		String[] arr = carId.split(",");
		List<Integer> cids = new ArrayList<>();
		for (String cid : arr) {
			try {
				cids.add(Integer.parseInt(cid));
			} catch (Exception e) {

			}
		}
		MSG msg = new MSG();
		boolean bool = priceTypeRelationService.savePerceRelation(id, cids, tableName);
		msg.setSuccess(bool);
		return msg;
	}
}

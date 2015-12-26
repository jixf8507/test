package com.qx.eakay.controller.price;

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
import com.qx.eakay.model.price.MonthLeasePriceTypePO;
import com.qx.eakay.service.price.LongPriceTypeService;
import com.qx.eakay.util.MSG;

/**
 * 长租收费套餐
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/price/priceType/long")
public class LongPriceTypeController extends BaseController {

	@Autowired
	private LongPriceTypeService longPriceTypeService;

	
	/**
	 * 长租收费套餐页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("page.htm")
	public ModelAndView longPage() throws Exception {
		return new ModelAndView("price/longPriceType");
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
	 * 分页查询车辆状态信息
	 * 
	 * @param aoData
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "ajaxData.htm")
	@ResponseBody
	public PageResults longAjaxData(String aoData, String paraData,
			HttpSession session) {
		JSONObject jsonObject = getJsonPara(paraData, session);

		// jquery.datatables 分页查询的参数
		JSONArray jsonArray = JSONArray.fromObject(aoData);
		PageHelp pageHelp = JSONTools.toPageHelp(jsonArray);

		PageResults pageResults = longPriceTypeService.findLongTypesPage(jsonObject,
				pageHelp.getiDisplayLength(), pageHelp.getiDisplayStart());
		pageResults.setsEcho(pageHelp.getsEcho());
		return pageResults;
	}

	/**
	 * 导出下载车辆信息到Excel表格
	 * 
	 * @param paraData
	 * @param session
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("exportToExcel.htm")
	public ModelAndView longExportToExcel(String paraData, HttpSession session,
			HttpServletResponse response) throws Exception {
		JSONObject jsonObject = getJsonPara(paraData, session);
		longPriceTypeService.excelExportLongTypes(response, jsonObject); 
		return null;
	}
	
	/**
	 * 新增长租收费套餐界面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("add.htm")
	public ModelAndView longAdd() throws Exception {
		return new ModelAndView("price/longPriceTypeAdd");
	}
	
	/**
	 * 保存新增长租收费套餐
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveAdd.htm")
	public ModelAndView longSaveAdd(MonthLeasePriceTypePO monthLeasePriceTypePO,
			HttpSession session) throws Exception {
		monthLeasePriceTypePO.setMerchantId(this.getSessionUser(session)
				.getMerchantId());
		longPriceTypeService.createLongPriceType(monthLeasePriceTypePO);
		return new ModelAndView("redirect:/system/message/success.htm");
	}
	
	
	/**
	 * 按钮保存新增长租收费套餐
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveAdd2.htm")
	@ResponseBody
	public MSG longSaveAdd2(MonthLeasePriceTypePO monthLeasePriceTypePO,
			HttpSession session) throws Exception {
		MSG msg =new MSG();
		monthLeasePriceTypePO.setMerchantId(this.getSessionUser(session)
				.getMerchantId());
		msg.setSuccess(longPriceTypeService.createLongPriceType(monthLeasePriceTypePO));
		return msg;
	}
	
	/**
	 * 删除长租收费套餐
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("delete.htm")
	@ResponseBody
	public MSG longDelete(Integer id) throws Exception {
		MSG msg = new MSG();
		boolean bool = longPriceTypeService.deleteLongPriceType(id);
		msg.setSuccess(bool);
		return msg;
	}
	
	/**
	 * 检查长租套餐名称是否唯一
	 * 
	 * @param typeName
	 *            套餐名称
	 * @param longPriceTypeId
	 *            套餐id
	 * @param response
	 */
	@RequestMapping("checkTypeNameOnly.htm")
	@ResponseBody
	public MSG checkTypeNameOnly(String typeName) {
		MSG msg = new MSG();
		boolean bool = longPriceTypeService.checkTypeNameOnly(typeName);
		msg.setSuccess(bool);
		return msg;
	}

}

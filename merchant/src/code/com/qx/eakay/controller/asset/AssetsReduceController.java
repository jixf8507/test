package com.qx.eakay.controller.asset;

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
import com.qx.eakay.model.asset.AssetReducePO;
import com.qx.eakay.service.asset.AssetsReduceService;
import com.qx.eakay.util.MSG;

/**
 * 资产减少管理
 * 
 * @author sdf
 * @date 2015年9月25日
 */
@Controller
@RequestMapping("/asset/reduce")
public class AssetsReduceController extends BaseController {

	@Autowired
	private AssetsReduceService reduceService;

	/**
	 * 进入资产减少信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("manage.htm")
	public ModelAndView page(HttpSession session) throws Exception {
		return new ModelAndView("asset/assetReduceManage");
	}

	/**
	 * 进入修改资产减少页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("edit.htm")
	public ModelAndView edit(Integer id, HttpSession session) throws Exception {
		AssetReducePO assetReducePO = reduceService.getReduce(id);
		return new ModelAndView("asset/assetReduceEdit", "assetReducePO",
				assetReducePO);
	}

	/**
	 * 保存修改资产减少信息
	 * 
	 * @param customerPO
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "saveEdit.htm")
	@ResponseBody
	public MSG saveEdit(AssetReducePO assetReducePO, HttpSession session) {
		SessionUserBO sessionUser = this.getSessionUser(session);
		assetReducePO.setMerchantId(sessionUser.getMerchantId());
		assetReducePO.setTransactUser(sessionUser.getName());
		MSG msg = new MSG();
		msg.setSuccess(reduceService.updateAssetReduce(assetReducePO));
		return msg;
	}

	/**
	 * 进入新增资产减少页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("add.htm")
	public ModelAndView add(HttpSession session) throws Exception {
		return new ModelAndView("asset/assetReduceAdd");
	}

	/**
	 * 保存新增资产减少
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveAdd.htm")
	@ResponseBody
	public MSG saveAdd(AssetReducePO assetReducePO, Integer[] assetId,
			HttpSession session) {
		MSG msg = new MSG();
		SessionUserBO sessionUser = this.getSessionUser(session);
		assetReducePO.setTransactUser(sessionUser.getName());
		assetReducePO.setMerchantId(sessionUser.getMerchantId());
		msg.setSuccess(reduceService.createAssetReduce(assetReducePO, assetId));
		return msg;
	}

	/**
	 * 分页查询资产减少信息
	 * 
	 * @param aoData
	 * @param paraData
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "ajaxData.htm")
	@ResponseBody
	public PageResults ajaxData(String aoData, String paraData,
			HttpSession session) {
		JSONObject jsonObject = getJsonPara(paraData, session);
		// jquery.datatables 分页查询的参数
		JSONArray jsonArray = JSONArray.fromObject(aoData);
		PageHelp pageHelp = JSONTools.toPageHelp(jsonArray);

		PageResults pageResults = reduceService.findReducePage(jsonObject,
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
	 * 导出下载资产减少信息到Excel表格
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
		reduceService.excelAssetReducePage(response, jsonObject);
		return null;
	}

	/**
	 * 导出下载资产减少详细信息到Excel表格
	 * 
	 * @param paraData
	 * @param session
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("exportDetailToExcel.htm")
	public ModelAndView exportDetailToExcel(String paraData,
			HttpSession session, HttpServletResponse response) throws Exception {
		JSONObject jsonObject = getJsonPara(paraData, session);
		reduceService.excelAssetReduceDetailPage(response, jsonObject);
		return null;
	}

	/**
	 * 进入资产减少详细信息页面
	 * 
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("detail.htm")
	public ModelAndView detail(HttpSession session, Integer id)
			throws Exception {
		AssetReducePO assetReducePO = reduceService.getReduce(id);
		return new ModelAndView("asset/assetReduceDetail", "assetReducePO",
				assetReducePO);
	}

	/**
	 * 分页查询资产减少详细信息
	 * 
	 * @param aoData
	 * @param paraData
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "ajaxDetailData.htm")
	@ResponseBody
	public PageResults ajaxDetailData(String aoData, String paraData,
			HttpSession session) {
		JSONObject jsonObject = getJsonPara(paraData, session);

		JSONArray jsonArray = JSONArray.fromObject(aoData);
		PageHelp pageHelp = JSONTools.toPageHelp(jsonArray);

		PageResults pageResults = reduceService.findRepairDetailPage(
				jsonObject, pageHelp.getiDisplayLength(),
				pageHelp.getiDisplayStart());
		pageResults.setsEcho(pageHelp.getsEcho());
		return pageResults;
	}

}

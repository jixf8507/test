package com.qx.eakay.controller.asset;

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
import com.qx.eakay.model.asset.AssetPO;
import com.qx.eakay.service.asset.AssetsManageService;
import com.qx.eakay.util.MSG;

/**
 * 资产信息管理
 * 
 * @author sdf
 * @date 2015年9月23日
 */
@Controller
@RequestMapping("/asset/manage")
public class AssetsManageController extends BaseController {
	
	@Autowired
	private AssetsManageService manageService;

	/**
	 * 进入资产信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("page.htm")
	public ModelAndView page(HttpSession session) throws Exception {
		return new ModelAndView("asset/assetPage");
	}

	/**
	 * 进入资产管理页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("manage.htm")
	public ModelAndView manage(HttpSession session) throws Exception {
		return new ModelAndView("asset/assetManage");
	}

	/**
	 * 进入修改资产页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("edit.htm")
	public ModelAndView edit(Integer id, HttpSession session) throws Exception {
		AssetPO assetPO = manageService.getAsset(id);
		return new ModelAndView("asset/assetEdit", "assetPO", assetPO);
	}

	/**
	 * 进入资产详细信息页面
	 * 
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("detail.htm")
	public ModelAndView detail(HttpSession session,Integer id) throws Exception {
		AssetPO assetPO = manageService.getAsset(id);
		return new ModelAndView("asset/assetDetail","assetPO",assetPO);
	}

	/**
	 * 保存修改资产信息
	 * 
	 * @param customerPO
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "saveEdit.htm")
	@ResponseBody
	public MSG saveEdit(AssetPO assetPO, HttpSession session) {
		SessionUserBO sessionUser = this.getSessionUser(session);
		assetPO.setMerchantId(sessionUser.getMerchantId());
		assetPO.setTransactUser(sessionUser.getName());
		MSG msg = new MSG();
		msg.setSuccess(manageService.updateAsset(assetPO));
		return msg;
	}

	/**
	 * 进入新增资产页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("add.htm")
	public ModelAndView add(HttpSession session) throws Exception {
		return new ModelAndView("asset/assetAdd");
	}

	/**
	 * 保存新增资产
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveAdd.htm")
	@ResponseBody
	public MSG saveAdd(AssetPO assetPO, HttpSession session) throws Exception {
		MSG msg = new MSG();
		SessionUserBO sessionUser = this.getSessionUser(session);
		assetPO.setMerchantId(sessionUser.getMerchantId());
		assetPO.setTransactUser(sessionUser.getName());
		
		msg.setSuccess(manageService.createAsset(assetPO));
		return msg;
	}

	/**
	 * 分页查询资产信息
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

		PageResults pageResults = manageService.findAssetPage(jsonObject,
				pageHelp.getiDisplayLength(), pageHelp.getiDisplayStart());
		pageResults.setsEcho(pageHelp.getsEcho());
		return pageResults;
	}
	
	/**
	 * 分页查询资产使用信息
	 * 
	 * @param aoData
	 * @param paraData
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "ajaxUseData.htm")
	@ResponseBody
	public PageResults ajaxUseData(String aoData, String paraData,
			HttpSession session) {
		JSONObject jsonObject = getJsonPara(paraData, session);
		// jquery.datatables 分页查询的参数
		JSONArray jsonArray = JSONArray.fromObject(aoData);
		PageHelp pageHelp = JSONTools.toPageHelp(jsonArray);
		
		PageResults pageResults = manageService.findAssetUsePage(jsonObject,
				pageHelp.getiDisplayLength(), pageHelp.getiDisplayStart());
		pageResults.setsEcho(pageHelp.getsEcho());
		return pageResults;
	}
	
	/**
	 * 分页查询资产维修信息
	 * 
	 * @param aoData
	 * @param paraData
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "ajaxRepairData.htm")
	@ResponseBody
	public PageResults ajaxRepairData(String aoData, String paraData,
			HttpSession session) {
		JSONObject jsonObject = getJsonPara(paraData, session);
		// jquery.datatables 分页查询的参数
		JSONArray jsonArray = JSONArray.fromObject(aoData);
		PageHelp pageHelp = JSONTools.toPageHelp(jsonArray);
		
		PageResults pageResults = manageService.findAssetRepairPage(jsonObject,
				pageHelp.getiDisplayLength(), pageHelp.getiDisplayStart());
		pageResults.setsEcho(pageHelp.getsEcho());
		return pageResults;
	}
	
	/**
	 * 分页查询资产减少信息
	 * 
	 * @param aoData
	 * @param paraData
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "ajaxReduceData.htm")
	@ResponseBody
	public PageResults ajaxReduceData(String aoData, String paraData,
			HttpSession session) {
		JSONObject jsonObject = getJsonPara(paraData, session);
		// jquery.datatables 分页查询的参数
		JSONArray jsonArray = JSONArray.fromObject(aoData);
		PageHelp pageHelp = JSONTools.toPageHelp(jsonArray);
		
		PageResults pageResults = manageService.findAssetReducePage(jsonObject,
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
	 * 导出下载资产信息到Excel表格
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
		manageService.excelAssetPage(response, jsonObject);
		return null;
	}
	
	/**
	 * 导出下载资产使用信息到Excel表格
	 * 
	 * @param paraData
	 * @param session
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("exportUseToExcel.htm")
	public ModelAndView exportUseToExcel(String paraData, HttpSession session,
			HttpServletResponse response) throws Exception {
		JSONObject jsonObject = getJsonPara(paraData, session);
		manageService.excelAssetUsePage(response, jsonObject);
		return null;
	}
	
	/**
	 * 导出下载资产维修信息到Excel表格
	 * 
	 * @param paraData
	 * @param session
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("exportRepairToExcel.htm")
	public ModelAndView findAssetRepair(String paraData, HttpSession session,
			HttpServletResponse response) throws Exception {
		JSONObject jsonObject = getJsonPara(paraData, session);
		manageService.excelAssetRepair(response, jsonObject);
		return null;
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
	@RequestMapping("exportReduceToExcel.htm")
	public ModelAndView exportReduceToExcel(String paraData, HttpSession session,
			HttpServletResponse response) throws Exception {
		JSONObject jsonObject = getJsonPara(paraData, session);
		manageService.excelAssetReducePage(response, jsonObject);
		return null;
	}

	/**
	 * 批量修改资产状态
	 * 
	 * @param customerPO
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "batchUpdateAssetFlag.htm")
	@ResponseBody
	public MSG batchUpdateAssetFlag(String flag,String assetIds) {
		MSG msg = new MSG();
		String[] ids = assetIds.split(",");
		List<Integer> listIds = new ArrayList<>();
		for (String id : ids) {
			listIds.add(Integer.parseInt(id));
		}
		boolean bool = manageService.batchUpdateAssetFlag(flag,listIds);
		msg.setSuccess(bool);
		return msg;
	}
	
}

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
import com.qx.eakay.model.asset.AssetUseDetailPO;
import com.qx.eakay.model.asset.AssetUsePO;
import com.qx.eakay.service.asset.AssetsManageService;
import com.qx.eakay.service.asset.AssetsUseService;
import com.qx.eakay.util.MSG;

/**
 * 资产领用管理
 * 
 * @author sdf
 * @date 2015年9月24日
 */
@Controller
@RequestMapping("/asset/use")
public class AssetsUseController extends BaseController {
	
	@Autowired
	private AssetsUseService useService;
	
	@Autowired
	private AssetsManageService manageService;

	/**
	 * 进入资产领用信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("manage.htm")
	public ModelAndView page(HttpSession session) throws Exception {
		return new ModelAndView("asset/assetUseManage");
	}

	/**
	 * 进入修改资产领用页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("edit.htm")
	public ModelAndView edit(Integer id, HttpSession session) throws Exception {
		AssetUsePO assetUsePO = useService.getUse(id);
		return new ModelAndView("asset/assetUseEdit", "assetUsePO", assetUsePO);
	}

	/**
	 * 保存修改资产领用信息
	 * 
	 * @param customerPO
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "saveEdit.htm")
	@ResponseBody
	public MSG saveEdit(AssetUsePO assetUsePO, HttpSession session) {
		SessionUserBO sessionUser = this.getSessionUser(session);
		assetUsePO.setMerchantId(sessionUser.getMerchantId());
		assetUsePO.setTransactUser(sessionUser.getName());
		MSG msg = new MSG();
		msg.setSuccess(useService.updateUse(assetUsePO));
		return msg;
	}

	/**
	 * 进入新增资产领用页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("add.htm")
	public ModelAndView add(HttpSession session) throws Exception {
		return new ModelAndView("asset/assetUseAdd");
	}

	/**
	 * 保存新增资产领用
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveAdd.htm")
	@ResponseBody
	public MSG saveAdd(AssetUsePO assetUsePO,Integer[] assetId, HttpSession session){
		MSG msg = new MSG();
		SessionUserBO sessionUser = this.getSessionUser(session);
		assetUsePO.setTransactUser(sessionUser.getName());
		assetUsePO.setMerchantId(sessionUser.getMerchantId());
		msg.setSuccess(useService.createAssetUse(assetUsePO,assetId));
		return msg;
	}

	/**
	 * 分页查询资产领用信息
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

		PageResults pageResults = useService.findUsePage(jsonObject,
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
	 * 导出下载资产领用信息到Excel表格
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
		useService.excelAssetUsePage(response, jsonObject);
		return null;
	}
	
	/**
	 * 导出下载资产领用详细信息到Excel表格
	 * 
	 * @param paraData
	 * @param session
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("exportDetailToExcel.htm")
	public ModelAndView exportDetailToExcel(String paraData, HttpSession session,
			HttpServletResponse response) throws Exception {
		JSONObject jsonObject = getJsonPara(paraData, session);
		useService.excelAssetUseDetailPage(response, jsonObject);
		return null;
	}

	/**
	 * 进入资产领用详细信息页面
	 * 
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("detail.htm")
	public ModelAndView detail(HttpSession session,Integer id) throws Exception {
		AssetUsePO assetUsePO = useService.getUse(id);
		return new ModelAndView("asset/assetUseDetail","assetUsePO",assetUsePO);
	}

	/**
	 * 分页查询资产领用详细信息
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

		PageResults pageResults = useService.findUseDetailPage(jsonObject,
				pageHelp.getiDisplayLength(), pageHelp.getiDisplayStart());
		pageResults.setsEcho(pageHelp.getsEcho());
		return pageResults;
	}
	
	/**
	 * 批量修改资产状态
	 * 
	 * @param customerPO
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "updateAllAssetFlag.htm")
	@ResponseBody
	public MSG batchUpdateAssetFlag(String flag,Integer useId) {
		MSG msg = new MSG();
		//查找资产id
		List<AssetUseDetailPO> list = useService.getUseDetail(useId);
		
		List<Integer> listIds = new ArrayList<>();
		
		for(int i=0; i<list.size(); i++){
			   listIds.add(list.get(i).getAssetsId());
		   } 
		//修改资产状态
		boolean bool = manageService.batchUpdateAssetFlag(flag,listIds);
		//修改领用单状态
		boolean booll = useService.updateAssetUseFlag("归还",useId);
		msg.setSuccess(bool&&booll);
		
		return msg;
	}
	
	
}

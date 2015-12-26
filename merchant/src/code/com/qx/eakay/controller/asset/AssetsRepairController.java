package com.qx.eakay.controller.asset;

import java.math.BigDecimal;

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
import com.qx.eakay.model.asset.AssetRepairDetailPO;
import com.qx.eakay.model.asset.AssetRepairPO;
import com.qx.eakay.service.asset.AssetsRepairService;
import com.qx.eakay.util.MSG;

/**
 * 资产维修管理
 * 
 * @author sdf
 * @date 2015年9月25日
 */
@Controller
@RequestMapping("/asset/repair")
public class AssetsRepairController extends BaseController {
	@Autowired
	private AssetsRepairService repairService;

	/**
	 * 进入资产维修信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("manage.htm")
	public ModelAndView page(HttpSession session) throws Exception {
		return new ModelAndView("asset/assetRepairManage");
	}

	/**
	 * 进入修改资产维修页面
	 * 
	 * @return
	 * @throws Exception
	 */
	 @RequestMapping("edit.htm")
	 public ModelAndView edit(Integer id, HttpSession session) throws Exception {
		 AssetRepairPO assetRepairPO = repairService.getRepair(id);
		 return new ModelAndView("asset/assetRepairEdit", "assetRepairPO",
		 assetRepairPO);
	 }

	/**
	 * 保存修改资产维修信息
	 * 
	 * @param customerPO
	 * @param session
	 * @return
	 */
	 @RequestMapping(value = "saveEdit.htm")
	 @ResponseBody
	 public MSG saveEdit(AssetRepairPO assetRepairPO, HttpSession session) {
		 SessionUserBO sessionUser = this.getSessionUser(session);
		 assetRepairPO.setMerchantId(sessionUser.getMerchantId());
		 assetRepairPO.setTransactUser(sessionUser.getName());
		 MSG msg = new MSG();
		 msg.setSuccess(repairService.updateAssetRepair(assetRepairPO));
		 return msg;
	 }
	 
	 /**
	  * 进入修改资产维修详细页面
	  * 
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("editDetail.htm")
	 public ModelAndView editDetail(Integer id, HttpSession session) throws Exception {
		 AssetRepairDetailPO assetRepairDetailPO = repairService.getRepairDetail(id);
		 return new ModelAndView("asset/assetRepairEditDetail", "assetRepairDetailPO",
				 assetRepairDetailPO);
	 }
	 
	 /**
	  * 保存修改资产维修详细信息
	  * 
	  * @param AssetRepairDetailPO
	  * @return
	  */
	 @RequestMapping(value = "saveEditDetail.htm")
	 @ResponseBody
	 public MSG saveEditDetail(AssetRepairDetailPO assetRepairDetailPO) {
		 MSG msg = new MSG();
		 msg.setSuccess(repairService.updateAssetRepairDetail(assetRepairDetailPO));
		 return msg;
	 }

	/**
	 * 进入新增资产维修页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("add.htm")
	public ModelAndView add(HttpSession session) throws Exception {
		return new ModelAndView("asset/assetRepairAdd");
	}
	

	/**
	 * 保存新增资产维修
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveAdd.htm")
	@ResponseBody
	public MSG saveAdd(AssetRepairPO assetRepairPO, Integer[] assetId,
			String[] repairReasonDetail, String[] repairStatus, BigDecimal[] feeDetail,
			String[] accessories, HttpSession session) throws Exception {
		MSG msg = new MSG();
		SessionUserBO sessionUser = this.getSessionUser(session);
		assetRepairPO.setMerchantId(sessionUser.getMerchantId());
		assetRepairPO.setTransactUser(sessionUser.getName());

		if (assetId != null) {
			for (int i = 0; i < assetId.length; i++) {
				AssetRepairDetailPO detailPO = new AssetRepairDetailPO();
				detailPO.setAssetsId(assetId[i]);
				detailPO.setRepairReason(repairReasonDetail[i]);
				detailPO.setRepairStatus(repairStatus[i]);
				detailPO.setFee(feeDetail[i]);
				detailPO.setAccessories(accessories[i]);
				assetRepairPO.getDetailPOs().add(detailPO);
			}
		}
		
		msg.setSuccess(repairService.createAssetRepair(assetRepairPO, assetId));
		return msg;
	}

	/**
	 * 分页查询资产维修信息
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

		PageResults pageResults = repairService.findRepairPage(jsonObject,
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
	 * 导出下载资产维修信息到Excel表格
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
		repairService.excelAssetRepairPage(response, jsonObject);
		return null;
	}

	/**
	 * 导出下载资产维修详细信息到Excel表格
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
		repairService.excelAssetRepairDetailPage(response, jsonObject);
		return null;
	}

	/**
	 * 进入资产维修详细信息页面
	 * 
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("detail.htm")
	public ModelAndView detail(HttpSession session, Integer id)
			throws Exception {
		AssetRepairPO assetRepairPO = repairService.getRepair(id);
		return new ModelAndView("asset/assetRepairDetail", "assetRepairPO",
				assetRepairPO);
	}

	/**
	 * 分页查询资产维修详细信息
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

		PageResults pageResults = repairService.findRepairDetailPage(
				jsonObject, pageHelp.getiDisplayLength(),
				pageHelp.getiDisplayStart());
		pageResults.setsEcho(pageHelp.getsEcho());
		return pageResults;
	}

}

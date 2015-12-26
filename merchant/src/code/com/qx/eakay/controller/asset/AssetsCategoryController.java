package com.qx.eakay.controller.asset;

import java.util.List;
import java.util.Map;

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
import com.qx.eakay.model.asset.AssetCategoryPO;
import com.qx.eakay.service.asset.AssetsCategoryService;
import com.qx.eakay.util.MSG;

/**
 * 资产种类管理
 * 
 * @author sdf
 * @date 2015年9月23日
 */
@Controller
@RequestMapping("/asset/category")
public class AssetsCategoryController extends BaseController {

	@Autowired
	private AssetsCategoryService categoryService;

	/**
	 * 进入资产种类页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("manage.htm")
	public ModelAndView manage(HttpSession session) throws Exception {
		return new ModelAndView("asset/assetCategoryManage");
	}

	/**
	 * 分页查询资产种类
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

		PageResults pageResults = categoryService.findCategoryPage(jsonObject,
				pageHelp.getiDisplayLength(), pageHelp.getiDisplayStart());
		pageResults.setsEcho(pageHelp.getsEcho());
		return pageResults;
	}

	/**
	 * 动态加载资产种类
	 * 
	 * @param paraData
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "ajaxCategoryData.htm")
	@ResponseBody
	public List<Map<String, Object>> ajaxCategory(String paraData,
			HttpSession session) {
		JSONObject jsonObject = getJsonPara(paraData, session);
		return categoryService.findCategory(jsonObject);
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
		if (!sessionUser.isManagerUser()) {
			jsonObject.put("siteId", sessionUser.getMerchantUserPO()
					.getSiteId());
		}
		return jsonObject;
	}

	/**
	 * 导出下载资产种类到Excel表格
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
		categoryService.excelAssetPage(response, jsonObject);
		return null;
	}

	/**
	 * 进入编辑资产种类界面
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("edit.htm")
	public ModelAndView edit(Integer id) throws Exception {
		AssetCategoryPO categoryPO = categoryService.getCategory(id);
		return new ModelAndView("asset/assetCategoryEdit", "categoryPO",
				categoryPO);
	}

	/**
	 * 保存修改用户信息
	 * 
	 * @param categoryPO
	 * @return
	 */
	@RequestMapping(value = "saveEdite.htm")
	@ResponseBody
	public MSG saveEdite(AssetCategoryPO categoryPO) {
		MSG msg = new MSG();
		msg.setSuccess(categoryService.updateCategory(categoryPO));
		return msg;
	}

	/**
	 * 进入新增资产种类界面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("add.htm")
	public ModelAndView add(Integer id) throws Exception {
		return new ModelAndView("asset/assetCategoryAdd");
	}

	/**
	 * 保存新增资产种类
	 * 
	 * @param categoryPO
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "saveAdd.htm")
	@ResponseBody
	public MSG saveAdd(AssetCategoryPO categoryPO, HttpSession session){
		MSG msg = new MSG();
		SessionUserBO sessionUser = this.getSessionUser(session);
		categoryPO.setMerchantId(sessionUser.getMerchantId());
		msg.setSuccess(categoryService.createCategory(categoryPO));
		return msg;
	}
	
	/**
	 * 删除资产种类
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete.htm")
	@ResponseBody
	public MSG delete(Integer id) {
		MSG msg = new MSG();
		boolean bool = categoryService.delete(id);
		msg.setSuccess(bool);
		return msg;
	}
	
	/**
	 * 判断资产种类是否唯一
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "checkOnly.htm")
	@ResponseBody
	public MSG checkOnly(String name,Integer id) {
		MSG msg = new MSG();
		boolean bool = categoryService.check(name,id);
		msg.setSuccess(bool);
		return msg;
	}

}

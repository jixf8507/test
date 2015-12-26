package com.qx.eakay.controller.merchant;

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
import com.qx.eakay.model.merchant.SitePO;
import com.qx.eakay.service.car.CarService;
import com.qx.eakay.service.merchant.SiteService;
import com.qx.eakay.util.MSG;

/**
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/merchant/site")
public class SiteController extends BaseController {

	@Autowired
	private SiteService siteService;
	@Autowired
	private CarService carService;

	/**
	 * 进入租赁点管理页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("page.htm")
	public ModelAndView page() throws Exception {
		return new ModelAndView("merchant/site");
	}

	/**
	 * 进入租赁点添加页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("add.htm")
	public ModelAndView add() throws Exception {
		return new ModelAndView("merchant/siteAdd");
	}

	/**
	 * 通过左侧导航栏提交保存新增租赁点
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveAdd.htm")
	public ModelAndView saveAdd(SitePO sitePO, HttpSession session)
			throws Exception {
		sitePO.setMerchantId(this.getSessionUser(session).getMerchantId());
		siteService.createSite(sitePO);
		return new ModelAndView("redirect:/system/message/success.htm");
	}
	
	/**
	 * 通过新增服务中心按钮提交保存新增租赁点
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveAdd2.htm")
	@ResponseBody
	public MSG saveAdd2(SitePO sitePO, HttpSession session)
			throws Exception {
//		sitePO.setMerchantId(this.getSessionUser(session).getMerchantId());
//		siteService.createSite(sitePO);
//		return new ModelAndView("redirect:/system/message/success.htm");
		MSG msg = new MSG();
		sitePO.setMerchantId(this.getSessionUser(session).getMerchantId());
		msg.setSuccess(siteService.createSite(sitePO));
		return msg;
	}

	/**
	 * 进入租赁点编辑页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("edite.htm")
	public ModelAndView edite(Integer id) throws Exception {
		SitePO sitePO = siteService.getSite(id);
		return new ModelAndView("merchant/siteEdite", "sitePO", sitePO);
	}

	/**
	 * 提交保存编辑租赁点
	 * 
	 * @param sitePO
	 * @return
	 */
	@RequestMapping(value = "saveEdite.htm")
	@ResponseBody
	public MSG saveEdite(SitePO sitePO) {
		MSG msg = new MSG();
		msg.setSuccess(siteService.updateSite(sitePO));
		return msg;
	}

	/**
	 * 删除租赁点
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete.htm")
	@ResponseBody
	public MSG delete(Integer id) {
		MSG msg = new MSG();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("curSiteId", id);
		List<Map<String, Object>> list = carService.findCars(jsonObject);
		if (list.size() > 0) {
			msg.setSuccess(false);
			msg.setInfo("该服务中心下有车辆，请先移除车辆！");
			return msg;
		}
		msg.setSuccess(siteService.deleteSite(id));
		return msg;
	}

	/**
	 * 验证租赁点名称是否已经存在
	 * 
	 * @param siteName
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "checkSiteName.htm")
	@ResponseBody
	public MSG checkSiteName(String siteName, Integer id, HttpSession session) {
		MSG msg = new MSG();
		msg.setSuccess(siteService.checkSiteName(siteName,
				this.getSessionUser(session).getMerchantId(), id));
		return msg;
	}

	@RequestMapping(value = "ajaxData.htm")
	@ResponseBody
	public PageResults ajaxData(String aoData, String paraData,
			HttpSession session) {
		JSONObject jsonObject = getJsonPara(paraData, session);

		// jquery.datatables 分页查询的参数
		JSONArray jsonArray = JSONArray.fromObject(aoData);
		PageHelp pageHelp = JSONTools.toPageHelp(jsonArray);

		PageResults pageResults = siteService.findSitePage(jsonObject,
				pageHelp.getiDisplayLength(), pageHelp.getiDisplayStart());
		pageResults.setsEcho(pageHelp.getsEcho());
		return pageResults;
	}
	


	
	/**
	 * 加载type为“租赁点”的site数据到车载设备监控页面，判断当用户是站点工作人员的时候，只加载这个人员所在站点的数据。
	 */
	@RequestMapping(value = "ajaxDeviceMonitorList.htm")
	@ResponseBody
	public PageResults ajaxDeviceMonitorList(String aoData, String paraData,
			HttpSession session) {
		JSONObject jsonObject = getJsonPara(paraData, session);
		SessionUserBO sessionUser = this.getSessionUser(session);
		jsonObject.put("merchantId", sessionUser.getMerchantId());
		if (!sessionUser.isManagerUser()) {
			jsonObject.put("siteId", sessionUser.getMerchantUserPO()
					.getSiteId());
		}
			
		// jquery.datatables 分页查询的参数
		JSONArray jsonArray = JSONArray.fromObject(aoData);
		PageHelp pageHelp = JSONTools.toPageHelp(jsonArray);

		PageResults pageResults = siteService.findSitePageByType(jsonObject,
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
	 * 导出下载租赁点到Excel表格
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
		siteService.excelExportSites(response, jsonObject);
		return null;
	}

	/**
	 * 动态加载租赁点
	 * 
	 * @param paraData
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "ajaxSites.htm")
	@ResponseBody
	public List<Map<String, Object>> ajaxSites(String paraData,
			HttpSession session) {
		paraData = StringTools.decodeMethod(paraData);
		// 动态查询条件参数
		JSONObject jsonObject = JSONObject.fromObject(paraData);

		SessionUserBO sessionUser = this.getSessionUser(session);
		jsonObject.put("merchantId", sessionUser.getMerchantId());
		if (!sessionUser.isManagerUser()) {
			jsonObject.put("siteId", sessionUser.getMerchantUserPO()
					.getSiteId());
		}
		return siteService.findSites(jsonObject);
	}
	
	/**
	 * 动态查询省
	 * 
	 * @return
	 */
	@RequestMapping(value = "ajaxProvinces.htm")
	@ResponseBody
	public List<Map<String, Object>> ajaxProvinces() {
		return siteService.findProvinces();
	}

	/**
	 * 动态加载市
	 * 
	 * @param proID
	 * @return
	 */
	@RequestMapping(value = "ajaxCitys.htm")
	@ResponseBody
	public List<Map<String, Object>> ajaxCitys(String paraData) {
		paraData = StringTools.decodeMethod(paraData);
		JSONObject jsonObject = JSONObject.fromObject(paraData);
		String pName = jsonObject.get("pName") + "";
		List<Map<String, Object>> citys = siteService.findCitys(pName);
		return citys;
	}

}

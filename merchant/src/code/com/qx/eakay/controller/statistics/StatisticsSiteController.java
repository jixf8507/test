package com.qx.eakay.controller.statistics;

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
import com.qx.common.highcharts.ChartsBO;
import com.qx.common.highcharts.PointData;
import com.qx.common.tools.JSONTools;
import com.qx.common.tools.StringTools;
import com.qx.eakay.controller.BaseController;
import com.qx.eakay.login.SessionUserBO;
import com.qx.eakay.model.merchant.SitePO;
import com.qx.eakay.service.statistics.StatisticsSiteService;

@Controller
@RequestMapping("/statistics/site")
public class StatisticsSiteController extends BaseController {

	@Autowired
	private StatisticsSiteService siteService;

	/**
	 * 进入租赁次数统计页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("page.htm")
	public ModelAndView page() throws Exception {
		return new ModelAndView("statistics/siteLease");
	}

	@RequestMapping("charts.htm")
	public ModelAndView charts(Integer id, String siteName) throws Exception {
		SitePO sitePO = new SitePO();
		sitePO.setId(id);
		sitePO.setSiteName(StringTools.decodeMethod(siteName));
		return new ModelAndView("statistics/siteLeaseCharts", "sitePO", sitePO);
	}

	@RequestMapping(value = "ajaxData.htm")
	@ResponseBody
	public PageResults ajaxData(String aoData, String paraData,
			HttpSession session) {
		JSONObject jsonObject = getJsonPara(paraData, session);
		// jquery.datatables 分页查询的参数
		JSONArray jsonArray = JSONArray.fromObject(aoData);
		PageHelp pageHelp = JSONTools.toPageHelp(jsonArray);

		PageResults pageResults = siteService.findSiteCostPage(jsonObject,
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
	 * 导出下载租赁点收费统计信息
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
		siteService.excelExportSiteCosts(response, jsonObject);
		return null;
	}

	/**
	 * 统计每天的租赁情况
	 * 
	 * @param paraData
	 * @return
	 */
	@RequestMapping(value = "ajaxSiteHourCount.htm")
	@ResponseBody
	public ChartsBO<Integer> ajaxSiteHourCount(String paraData, HttpSession session) {
		JSONObject jsonObject = getJsonPara(paraData, session);
		
		ChartsBO<Integer> chartbo = siteService.statisticsHourCount(jsonObject);
		return chartbo;
	}
	
	/**
	 * 动态加载租赁点取车次数
	 * 
	 * @param paraData
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "ajaxSiteGetCountColumn.htm")
	@ResponseBody
	public ChartsBO<PointData> ajaxSiteGet(String paraData,
			HttpSession session) {
		JSONObject jsonObject = getJsonPara(paraData, session);
		
		return siteService.ajaxSiteGet(jsonObject);
	}
	
	/**
	 * 动态加载租赁点还车次数
	 * 
	 * @param paraData
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "ajaxSiteReturnCountColumn.htm")
	@ResponseBody
	public ChartsBO<PointData> ajaxSiteReturn(String paraData,
			HttpSession session) {
		JSONObject jsonObject = getJsonPara(paraData, session);
		
		return siteService.ajaxSiteReturn(jsonObject);
	}

}

package com.qx.eakay.controller.api.merchant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qx.eakay.controller.BaseController;
import com.qx.eakay.service.merchant.SiteService;
import com.qx.eakay.util.MSG;

/**
 * 租赁点接口
 * 
 * @author sdf
 * @date 2015年12月9日
 */
@Controller
@RequestMapping("/api/merchant/site")
public class SiteApiController extends BaseController {

	@Autowired
	private SiteService siteService;

	/**
	 * 获取租赁点信息
	 * 
	 * @param request
	 * @param merchantId
	 *            商家ID
	 * @param siteId
	 *            租赁点ID
	 * @return
	 */
	@RequestMapping(value = "querySites.htm")
	@ResponseBody
	public HashMap<String, Object> ajaxSites(HttpServletRequest request) {
		MSG msg = new MSG();
		List<Map<String, Object>> siteList = new ArrayList<Map<String, Object>>();
		HashMap<String, Object> result = new HashMap<>();
		String merchantId = request.getParameter("merchantId");
		String siteId = request.getParameter("siteId");

		if (merchantId == null || "".equals(merchantId)) {
			msg.setSuccess(false);
			msg.setInfo("商家ID为空");
		} else {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("merchantId", Integer.parseInt(merchantId));
			
			if(siteId != null && !"".equals(siteId)){
				jsonObject.put("siteId", Integer.parseInt(siteId));
			}

			List<Map<String, Object>> list = siteService.findSites(jsonObject);

			if (list != null && list.size() > 0) {
				
				for(Map<String, Object> site : list){
					Map<String, Object> map = new HashMap<String, Object>();
					// 租赁点信息
					map.put("siteId", Integer.parseInt(site.get("id")+""));
					map.put("siteName", site.get("siteName"));
					siteList.add(map);
				}
			}
			result.put("site", siteList);
			msg.setSuccess(true);
			msg.setInfo("获取租赁点信息成功");
		}
		result.put("msg", msg);
		return result;
	}
	
	

}

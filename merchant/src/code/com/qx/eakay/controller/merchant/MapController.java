package com.qx.eakay.controller.merchant;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qx.eakay.controller.BaseController;

/**
 * 百度地图
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/merchant/map")
public class MapController extends BaseController {

	/**
	 * 进入地图页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("page.htm")
	public ModelAndView page(HttpSession session,String lng,String lat) throws Exception {
		Map<String,Object> map = new HashMap<String, Object>();
		String city = this.getSessionUser(session).getCity();
		map.put("city", city);
		map.put("lng", lng);
		map.put("lat", lat);
		return new ModelAndView("map/getPosition", "map", map);
	}

}

package com.qx.common.tools;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.qx.common.base.PageHelp;

/**
 * JSON工具类
 * 
 * @author Administrator
 * 
 */
public class JSONTools {

	/**
	 * 从SJON数组中提取分页参数
	 * 
	 * @param jsonArray
	 * @return
	 */
	public static PageHelp toPageHelp(JSONArray jsonArray) {
		PageHelp pageHelp = new PageHelp();
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject obj = (JSONObject) jsonArray.get(i);
			if ("sEcho".equals(obj.get("name"))) {
				pageHelp.setsEcho(obj.get("value").toString());
			}
			if ("iDisplayStart".equals(obj.get("name"))) {
				pageHelp.setiDisplayStart(obj.getInt("value"));
			}
			if ("iDisplayLength".equals(obj.get("name"))) {
				pageHelp.setiDisplayLength(obj.getInt("value"));
			}
		}
		return pageHelp;
	}

}

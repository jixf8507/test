package com.qx.common.sql;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author Administrator
 * 
 */
public class JSONSqlMapping {

	// sql查询语句
	protected String select = "";
	// 查询条件列表
	protected List<Condition> conditions;
	// 排序
	protected String order;

	protected JSONObject jsonObject;

	/**
	 * 得到ORDER语句
	 * 
	 * @return
	 */
	public String getOrder() {
		if (StringUtils.isNotBlank(order)) {
			return " " + order;
		}
		return "";
	}

	public void setSelect(String select) {
		this.select = select;
	}

	public void setConditions(List<Condition> conditions) {
		this.conditions = conditions;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public void setJsonObject(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}

	public JSONObject getJsonObject() {
		return jsonObject;
	}

	public List<Condition> getConditions() {
		return conditions;
	}

	public String getSelect() {
		return select;
	}

	@Override
	public String toString() {
		return "JSONSqlMapping [conditions=" + conditions + ", order=" + order
				+ ", select=" + select + "]";
	}

}

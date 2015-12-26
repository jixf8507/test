package com.qx.common.highcharts;

import java.util.ArrayList;
import java.util.List;

/**
 * 图表坐标轴内容对象
 * 
 * @author jixf
 * @2014-10-10
 */
public class AxisBO {

	private List<String> categories = new ArrayList<String>();

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

}

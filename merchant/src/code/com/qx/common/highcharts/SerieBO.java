package com.qx.common.highcharts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 图表显示内容对象
 * 
 * @author jixf
 * @2014-10-11
 * @param <DT>
 */
public class SerieBO<DT extends Object> {

	public static enum SerieType {
		spline, pie, column, area;
	}

	private String name;
	private List<DT> data;
	private String stack;
	private SerieType type;
	private Integer yAxis = 0;

	private Map<String, DT> dataMap;

	public SerieBO() {
		super();
		this.data = new ArrayList<DT>();
		dataMap = new HashMap<String, DT>();
	}

	public SerieBO(String name) {
		this.name = name;
		this.data = new ArrayList<DT>();
		dataMap = new HashMap<String, DT>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<DT> getData() {
		return data;
	}

	public void setData(List<DT> data) {
		this.data = data;
	}

	public String getStack() {
		return stack;
	}

	public void setStack(String stack) {
		this.stack = stack;
	}

	public SerieType getType() {
		return type;
	}

	public void setType(SerieType type) {
		this.type = type;
	}

	public Map<String, DT> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, DT> dataMap) {
		this.dataMap = dataMap;
	}

	@Override
	public String toString() {
		return "SerieBO [data=" + data + ", dataMap=" + dataMap + ", name="
				+ name + ", stack=" + stack + ", type=" + type + "]";
	}

	public Integer getyAxis() {
		return yAxis;
	}

	public void setyAxis(Integer yAxis) {
		this.yAxis = yAxis;
	}

}

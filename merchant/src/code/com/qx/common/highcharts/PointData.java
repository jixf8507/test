package com.qx.common.highcharts;


/**
 * 
 * @author jixf
 * @2014-10-13
 */
public class PointData {

	private String name;
	private Integer y;
	private Integer x;

	public PointData() {
		super();
	}

	public PointData(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

}

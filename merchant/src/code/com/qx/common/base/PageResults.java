package com.qx.common.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author
 */
public class PageResults {
	private List<Map<String, Object>> aaData = new ArrayList<>();
	private int iTotalRecords = 0;
	private int iTotalDisplayRecords = 10;
	private String sEcho ;

	public List<Map<String, Object>> getAaData() {
		return aaData;
	}

	public void setAaData(List<Map<String, Object>> aaData) {
		this.aaData = aaData;
	}

	public int getiTotalRecords() {
		return iTotalRecords;
	}

	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	public String getsEcho() {
		return sEcho;
	}

	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

}

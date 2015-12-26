package com.qx.common.base;

/**
 * 分页帮助类
 * 
 * @author Administrator
 * 
 */
public class PageHelp {

	private String sEcho = null;
	private int iDisplayStart = 0; // 起始索引
	private int iDisplayLength = 0; // 每页显示的行数

	public String getsEcho() {
		return sEcho;
	}

	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	public int getiDisplayStart() {
		return iDisplayStart;
	}

	public void setiDisplayStart(int iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}

	public int getiDisplayLength() {
		return iDisplayLength;
	}

	public void setiDisplayLength(int iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}

}

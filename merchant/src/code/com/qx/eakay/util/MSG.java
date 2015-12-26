package com.qx.eakay.util;

/**
 * 类功能描述
 * 
 * @author xiachengyun xiachengyun@baizhu.cc
 * @version V1.0
 */
public class MSG {
	private boolean isSuccess = true;
	private String info = "成功";
	private int code = 0;

	public MSG() {

	}

	public MSG(String info, int code) {
		super();
		this.isSuccess = false;
		this.info = info;
		this.code = code;
	}

	/**
	 * @return the isSuccess
	 */
	public boolean isSuccess() {
		return isSuccess;
	}

	/**
	 * @param isSuccess
	 *            the isSuccess to set
	 */
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	/**
	 * @return the info
	 */
	public String getInfo() {
		return info;
	}

	/**
	 * @param info
	 *            the info to set
	 */
	public void setInfo(String info) {
		this.info = info;
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "MSG [isSuccess=" + isSuccess + ", info=" + info + ", code="
				+ code + "]";
	}

}

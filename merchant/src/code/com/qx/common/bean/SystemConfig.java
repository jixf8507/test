package com.qx.common.bean;

/**
 * 系统配置文件
 * 
 * @author Administrator
 * 
 */
public class SystemConfig {

	private static SystemConfig config;

	public static SystemConfig newInstance() {
		try {
			if (config == null) {
				config = new SystemConfig();
			}
		} catch (Exception e) {

		}
		return config;
	}

	private String systemName;

	private Integer cdzjkPort;

	private Integer waitTime;
	private Integer outTime;

	private String uploadFile = "/upload/image/";

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		newInstance().systemName = systemName;
	}

	public Integer getCdzjkPort() {
		return cdzjkPort;
	}

	public void setCdzjkPort(Integer cdzjkPort) {
		newInstance().cdzjkPort = cdzjkPort;
	}

	public Integer getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(Integer waitTime) {
		newInstance().waitTime = waitTime;
	}

	public Integer getOutTime() {
		return outTime;
	}

	public void setOutTime(Integer outTime) {
		newInstance().outTime = outTime;
	}

	public String getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(String uploadFile) {
		this.uploadFile = uploadFile;
	}

}

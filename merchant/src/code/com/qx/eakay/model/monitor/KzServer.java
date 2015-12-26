package com.qx.eakay.model.monitor;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 控制系统的对象
 * 
 * @author jixf
 * @2014-12-1
 */
public class KzServer {

	private String ip;// 服务IP地址
	private String onlineStatus = "离线"; // 服务在线状态
	private BigDecimal cpuUseRate; // CPU使用率
	private BigDecimal ramUseRate; // 内存使用率

	public KzServer() {
		super();
	}

	public KzServer(String ip) {
		super();
		this.ip = ip;
	}

	/**
	 * 从Map中设置服务属性
	 * 
	 * @param xnDate
	 */
	public void setRate(Map<String, Object> xnDate) {
		if (xnDate == null) {
			return;
		}
		this.cpuUseRate = new BigDecimal(xnDate.get("cpuUseRate") + "");
		this.ramUseRate = new BigDecimal(xnDate.get("ramUseRate") + "");
		
		this.cpuUseRate = cpuUseRate.setScale(2, ramUseRate.ROUND_HALF_UP) ;
		this.ramUseRate = ramUseRate.setScale(2, ramUseRate.ROUND_HALF_UP) ;
		this.onlineStatus = "在线";
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getOnlineStatus() {
		return onlineStatus;
	}

	public void setOnlineStatus(String onlineStatus) {
		this.onlineStatus = onlineStatus;
	}

	public BigDecimal getCpuUseRate() {
		return cpuUseRate.setScale(2, cpuUseRate.ROUND_HALF_UP);
	}

	public void setCpuUseRate(BigDecimal cupUseRate) {
		this.cpuUseRate = cupUseRate;
	}

	public BigDecimal getRamUseRate() {

		return ramUseRate.setScale(2, ramUseRate.ROUND_HALF_UP);
	}

	public void setRamUseRate(BigDecimal ramUseRate) {
		this.ramUseRate = ramUseRate;
	}

	@Override
	public String toString() {
		return "KzServer [cupUseRate=" + cpuUseRate + ", ip=" + ip
				+ ", onlineStatus=" + onlineStatus + ", ramUseRate="
				+ ramUseRate + "]";
	}

}

package com.qx.eakay.model.monitor;

public class SiteResoucePO {

	private String siteName;// 租赁点名称
	private int totleCar; // 租赁点车辆总数
	private int waitCar;// 空闲车辆总数
	private int faultCar;// 故障车辆总数
	private int chargeCar;// 充电车辆总数
	private int otherCar;// 其他车辆总数
	private int bookOrder;// 预约订单
	private int getOrder;// 取车待审核订单
	private int returnOrder;// 还车待审核订单
	private int chargeOrder;// 长租订单
	private int merchantUseOrder;// 员工用车订单
	private int carDevice;// 安装车载设备数量
	private int onlineDevice;// 在线车载设备数量
	private int outlineDevice;// 离线车载设备数量
	private int totleStake;// 充电桩总数量
	private int waitStake;// 空闲充电桩数量
	private int chargeStake;// 充电充电桩数量
	private int faultStake;
	private int outLineStake;
	
	public int getOtherCar() {
		return otherCar;
	}

	public void setOtherCar(int otherCar) {
		this.otherCar = otherCar;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public int getTotleCar() {
		return totleCar;
	}

	public void setTotleCar(int totleCar) {
		this.totleCar = totleCar;
	}

	public int getWaitCar() {
		return waitCar;
	}

	public void setWaitCar(int waitCar) {
		this.waitCar = waitCar;
	}

	public int getGetOrder() {
		return getOrder;
	}

	public void setGetOrder(int getOrder) {
		this.getOrder = getOrder;
	}

	public int getReturnOrder() {
		return returnOrder;
	}

	public void setReturnOrder(int returnOrder) {
		this.returnOrder = returnOrder;
	}

	public int getChargeOrder() {
		return chargeOrder;
	}

	public void setChargeOrder(int chargeOrder) {
		this.chargeOrder = chargeOrder;
	}

	public int getCarDevice() {
		return carDevice;
	}

	public void setCarDevice(int carDevice) {
		this.carDevice = carDevice;
	}

	public int getOnlineDevice() {
		return onlineDevice;
	}

	public void setOnlineDevice(int onlineDevice) {
		this.onlineDevice = onlineDevice;
	}

	public int getOutlineDevice() {
		return outlineDevice;
	}

	public void setOutlineDevice(int outlineDevice) {
		this.outlineDevice = outlineDevice;
	}

	public int getTotleStake() {
		return totleStake;
	}

	public void setTotleStake(int totleStake) {
		this.totleStake = totleStake;
	}

	public int getWaitStake() {
		return waitStake;
	}

	public void setWaitStake(int waitStake) {
		this.waitStake = waitStake;
	}

	public int getChargeStake() {
		return chargeStake;
	}

	public void setChargeStake(int chargeStake) {
		this.chargeStake = chargeStake;
	}

	public int getFaultStake() {
		return faultStake;
	}

	public void setFaultStake(int faultStake) {
		this.faultStake = faultStake;
	}

	public int getOutLineStake() {
		return outLineStake;
	}

	public void setOutLineStake(int outLineStake) {
		this.outLineStake = outLineStake;
	}

	public int getFaultCar() {
		return faultCar;
	}

	public void setFaultCar(int faultCar) {
		this.faultCar = faultCar;
	}

	public int getMerchantUseOrder() {
		return merchantUseOrder;
	}

	public void setMerchantUseOrder(int merchantUseOrder) {
		this.merchantUseOrder = merchantUseOrder;
	}

	public int getBookOrder() {
		return bookOrder;
	}

	public void setBookOrder(int bookOrder) {
		this.bookOrder = bookOrder;
	}

	public int getChargeCar() {
		return chargeCar;
	}

	public void setChargeCar(int chargeCar) {
		this.chargeCar = chargeCar;
	}

}

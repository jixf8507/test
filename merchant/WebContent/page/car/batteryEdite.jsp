<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript"
	src="${ctx}/js/plugins/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx}/js/page/car/batteryEdite.js"></script>
</head>

<body>
	<form id="form1" class="stdform stdform2" method="post" action=""
		enctype="multipart/form-data">
		<p>
			<label>电池VIN码</label> <span class="field"><input type="text"
				value="${batteryPO.vin}" name="vin" id="vin" class="longinput" /> <input
				type="hidden" name="id" id="id" value="${batteryPO.id}" /> </span>
		</p>
		<p>
			<label>车辆</label> <span class="field"> <input type="text"
				value="${batteryPO.carPO.carNumber}" name="carNumber" id="carNumber"
				class="longinput" /> <input value="${batteryPO.carId}"
				type="hidden" name="carId" id="carId" /><input type="button"
				value="点击获取" onclick="getCar()" />
			</span>
		</p>
		<p>
			<label>电池类型</label> <span class="field"><input type="text"
				value="${batteryPO.batteryType}" name="batteryType" id="batteryType"
				class="longinput" /></span>
		</p>
		<p>
			<label>最高电压</label> <span class="field"><input type="text"
				value="${batteryPO.maxVoltag}" name="maxVoltag" id="maxVoltag"
				class="longinput" />(V)</span>
		</p>
		<p>
			<label>最大电流</label> <span class="field"><input type="text"
				value="${batteryPO.maxCurrent}" name="maxCurrent" id="maxCurrent"
				class="longinput" />(A)</span>
		</p>
		<p>
			<label>最高温度</label> <span class="field"><input type="text"
				value="${batteryPO.maxTemp}" name="maxTemp" id="maxTemp"
				class="longinput" />(℃)</span>
		</p>
		<p>
			<label>最小电压</label> <span class="field"><input type="text"
				value="${batteryPO.minVoltage}" name="minVoltage" id="minVoltage"
				class="longinput" />(V)</span>
		</p>
		<p>
			<label>最小电流</label> <span class="field"><input type="text"
				value="${batteryPO.minCurrent}" name="minCurrent" id="minCurrent"
				class="longinput" />(A)</span>
		</p>
		<p>
			<label>最低温度</label> <span class="field"><input type="text"
				value="${batteryPO.minTemp}" name="minTemp" id="minTemp"
				class="longinput" />(℃)</span>
		</p>
	</form>


</body>
</html>

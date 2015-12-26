<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript"
	src="${ctx}/js/plugins/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx}/js/page/car/batteryAdd.js"></script>
</head>

<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />

		<div class="centercontent">

			<div class="pageheader">
				<h1 class="pagetitle">添加车辆电池信息</h1>
			</div>
			<!--pageheader-->

			<div id="contentwrapper" class="contentwrapper">

				<div id="list" class="subcontent">
					<form id="form1" class="stdform stdform2" method="post"
						action="${ctx}/car/battery/saveAdd.htm?"
						enctype="multipart/form-data">
						<p>
							<label>电池VIN码</label> <span class="field"><input
								type="text" name="vin" id="vin" class="longinput"
								style="width: 200px;" /></span>
						</p>
						<p>
							<label>车辆</label> <span class="field"> <input type="text"
								name="carNumber" id="carNumber" class="longinput"
								onclick="getCar()" style="width: 200px;" /> <input type="hidden"
								name="carId" id="carId" /><input type="button" value="点击获取"
								onclick="getCar()" />
							</span>
						</p>
						<p>
							<label>电池类型</label> <span class="field"><input type="text"
								name="batteryType" id="batteryType" class="longinput"
								style="width: 200px;" /></span>
						</p>
						<p>
							<label>最高电压</label> <span class="field"><input type="text"
								name="maxVoltag" id="maxVoltag" class="longinput"
								style="width: 200px;" />(V)</span>
						</p>
						<p>
							<label>最大电流</label> <span class="field"><input type="text"
								name="maxCurrent" id="maxCurrent" class="longinput"
								style="width: 200px;" />(A)</span>
						</p>
						<p>
							<label>最高温度</label> <span class="field"><input type="text"
								name="maxTemp" id="maxTemp" class="longinput"
								style="width: 200px;" />(℃)</span>
						</p>
						<p>
							<label>最小电压</label> <span class="field"><input type="text"
								name="minVoltage" id="minVoltage" class="longinput"
								style="width: 200px;" />(V)</span>
						</p>
						<p>
							<label>最小电流</label> <span class="field"><input type="text"
								name="minCurrent" id="minCurrent" class="longinput"
								style="width: 200px;" />(A)</span>
						</p>
						<p>
							<label>最低温度</label> <span class="field"><input type="text"
								name="minTemp" id="minTemp" class="longinput"
								style="width: 200px;" />(℃)</span>
						</p>
						<p class="stdformbutton">
							<button class="submit radius2">保存</button>
							<input type="reset" class="reset radius2" value="重置" />
						</p>
					</form>
					<br />
				</div>
				<!-- #add -->
			</div>
			<!--contentwrapper-->
			<br clear="all" />
		</div>
		<!-- centercontent -->
	</div>
	<!--bodywrapper-->

</body>
</html>

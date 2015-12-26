<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript"
	src="${ctx}/js/plugins/chosen.jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/page/car/battery.js"></script>
</head>

<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />

		<div class="centercontent">
			<div class="pageheader">
				<h1 class="pagetitle">车辆电池信息管理</h1>
			</div>
			<!--pageheader-->

			<div id="contentwrapper" class="contentwrapper">

				<div id="list" class="subcontent">
					<div class="overviewhead">
						VIN: &nbsp;<input type="text" id="vin" /> &nbsp; &nbsp;
						<button class="publishbutton radius3" id="queryBtn">查询</button>&nbsp; 
						<button class="publishbutton radius3" id="excelBtn">导出EXCEL</button>
					</div>
					<!--overviewhead-->
					<br clear="all" />
					<!--contenttitle-->
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="batteryTable">
						<colgroup>
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
							<col class="con1" style="width: 8%;"/>
						</colgroup>
						<thead>
							<tr>
								<th class="head0">电池VIN码</th>
								<th class="head1">所在车辆</th>
								<th class="head0">电池类型</th>
								<th class="head1">最大电压(V)</th>
								<th class="head0">最大电流(A)</th>
								<th class="head1">最高温度(℃)</th>
								<th class="head0">最小电压(V)</th>
								<th class="head1">最小电流(A)</th>
								<th class="head0">最低温度(℃)</th>
								<th class="head1">操作</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">电池VIN码</th>
								<th class="head1">所在车辆</th>
								<th class="head0">电池类型</th>
								<th class="head1">最大电压(V)</th>
								<th class="head0">最大电流(A)</th>
								<th class="head1">最高温度(℃)</th>
								<th class="head0">最小电压(V)</th>
								<th class="head1">最小电流(A)</th>
								<th class="head0">最低温度(℃)</th>
								<th class="head1">操作</th>
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>

					<br /> <br />

				</div>

				<!-- #updates -->
			</div>
			<!--contentwrapper-->
			<br clear="all" />
		</div>
		<!-- centercontent -->
	</div>
	<!--bodywrapper-->

</body>
</html>

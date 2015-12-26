<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript" src="${ctx}/js/page/car/carDevice.js"></script>
</head>

<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />

		<div class="centercontent">
			<div class="pageheader">
				<h1 class="pagetitle">车载设备列表</h1>
			</div>
			<!--pageheader-->

			<div id="contentwrapper" class="contentwrapper">
				<div id="list" class="subcontent">
					<div class="overviewhead">
						设备编号: &nbsp;<input type="text" id="deviceNO" /> &nbsp; &nbsp;
						车牌号码: &nbsp;<input type="text" id="carNumber" /> &nbsp; &nbsp;
						<button class="publishbutton radius3" id="queryBtn">查询</button>&nbsp; 
						<button class="publishbutton radius3" id="excelBtn">导出EXCEL</button>
					</div>
					<!--overviewhead-->
					<br clear="all" />
					<!--contenttitle-->
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="carDeviceTable">
						<colgroup>
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
							<col class="con0" style="width: 10%;"/>
						</colgroup>
						<thead>
							<tr>
								<th class="head0">设备编号</th>
								<th class="head1">设备状态</th>
								<th class="head0">车牌号码</th>
								<th class="head1">车辆类型</th>
								<th class="head0">客户姓名</th>
								<th class="head1">手机号码</th>
								<th class="head0">设备卡号</th>
								<th class="head1">最后在线时间</th>
								<th class="head0">操作</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">设备编号</th>
								<th class="head1">设备状态</th>
								<th class="head0">车牌号码</th>
								<th class="head1">车辆类型</th>
								<th class="head0">客户姓名</th>
								<th class="head1">手机号码</th>
								<th class="head0">设备卡号</th>
								<th class="head1">最后在线时间</th>
								<th class="head0">操作</th>
							</tr>
						</tfoot>
						<tbody>
						</tbody>
					</table>
					<br /> <br />
				</div>

			</div>
			<!--contentwrapper-->
			<br clear="all" />
		</div>
		<!-- centercontent -->
	</div>
	<!--bodywrapper-->

</body>
</html>

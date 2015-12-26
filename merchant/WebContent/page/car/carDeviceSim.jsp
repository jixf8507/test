<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript" src="${ctx}/js/page/car/carDeviceSim.js"></script>
</head>

<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />

		<div class="centercontent">
			<div class="pageheader">
				<h1 class="pagetitle">车载设备卡列表</h1>
				<span class="pagedesc">
				</span>
				<ul class="hornav">
					<li class="current"><a href="#list" id="normalDiv">正常</a></li>
					<li><a href="#delList" id="delDiv">废弃</a></li>
				</ul>
			</div>
			<div id="contentwrapper" class="contentwrapper">
				<div id="list" class="subcontent">
					<div class="overviewhead">
						设备卡号: &nbsp;<input type="text" id="simCard" /> &nbsp; &nbsp;
						设备编号: &nbsp;<input type="text" id="deviceNO" /> &nbsp; &nbsp;
						车牌号码: &nbsp;<input type="text" id="carNumber" /> &nbsp; &nbsp;
						<button class="publishbutton radius3" id="queryBtn">查询</button>
						&nbsp;
						<button class="publishbutton radius3" id="excelBtn">导出EXCEL</button>
					</div>
					<br clear="all" />
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="carDeviceSimTable">
						<colgroup>
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
						</colgroup>
						<thead>
							<tr>
								<th class="head0">设备卡号</th>
								<th class="head1">运营商</th>
								<th class="head0">月流量</th>
								<th class="head1">所在设备</th>
								<th class="head0">所在车辆</th>
								<th class="head1">车辆类型</th>
								<th class="head0">设备卡状态</th>
								<th class="head1">操作</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">设备卡号</th>
								<th class="head1">运营商</th>
								<th class="head0">月流量</th>
								<th class="head1">所在设备</th>
								<th class="head0">所在车辆</th>
								<th class="head1">车辆类型</th>
								<th class="head0">设备卡状态</th>
								<th class="head1">操作</th>
							</tr>
						</tfoot>
						<tbody>
						</tbody>
					</table>
					<br /> <br />
				</div>
				
				<div id="delList" class="subcontent" style="display: none;">
					<div class="overviewhead">
						设备卡号: &nbsp;<input type="text" id="simCard1" /> &nbsp; &nbsp;
						<button class="publishbutton radius3" id="queryBtn1">查询</button>
						&nbsp;
						<button class="publishbutton radius3" id="excelBtn1">导出EXCEL</button>
					</div>
					<br clear="all" />
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="carDeviceSimDelTable">
						<colgroup>
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
						</colgroup>
						<thead>
							<tr>
								<th class="head0">设备卡号</th>
								<th class="head1">运营商</th>
								<th class="head0">月流量</th>
								<th class="head1">设备卡状态</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">设备卡号</th>
								<th class="head1">运营商</th>
								<th class="head0">月流量</th>
								<th class="head1">设备卡状态</th>
							</tr>
						</tfoot>
						<tbody>
						</tbody>
					</table>
					<br /> <br />
				</div>

			</div>
			<br clear="all" />
		</div>
	</div>
</body>
</html>

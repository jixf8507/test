<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript" src="${ctx}/js/page/otherOrder/record.js"></script>
</head>
<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />
		<div class="centercontent">

			<div class="pageheader">
				<h1 class="pagetitle">员工用车记录</h1>
				<span class="pagedesc"></span>
				<ul class="hornav">
					<li class="current"><a href="#normal" id="normalDiv">车辆调度</a></li>
					<li><a href="#unit" id="unitDiv">公务用车</a></li>
				</ul>
			</div>
			<div id="contentwrapper" class="contentwrapper">
				<div id="normal" class="subcontent">
					<div class="overviewhead">
						<div class="overviewselect">
							<select id="siteId" name="select" class="chzn-select"
								style="width: 200px;" tabindex="2">
								<option value="">请选择租赁点</option>
							</select>
						</div>
						日期:&nbsp;&nbsp;&nbsp;<input type="text" id="beginTime" />
						&nbsp;&nbsp; 到: &nbsp;&nbsp;<input
							type="text" id="endTime" />&nbsp;&nbsp;&nbsp;姓名:
						&nbsp;<input type="text" id="userName" /> &nbsp; 手机: &nbsp;<input
							type="text" id="mobilePhone" /> &nbsp; &nbsp;<br /> <br />订单号: 
							CLDD_<input type="text" id="orderId" />&nbsp; &nbsp; 车牌: 
							&nbsp;<input type="text" id="carNumber" />&nbsp; &nbsp;取车经办人： &nbsp;<input
							type="text" id="menForGet" />&nbsp; &nbsp;还车经办人： &nbsp;<input type="text"
							id="menForReturn" />&nbsp; &nbsp;
						<button class="publishbutton radius3" id="queryBtn">查询</button>
						&nbsp;&nbsp;
						<button class="publishbutton radius3" id="excelBtn">导出EXCEL</button>
						&nbsp;
					</div>
					<br clear="all" />
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="normalCustomersTable">
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
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
						</colgroup>
						<thead>
							<tr>
								<th class="head0">订单号</th>
								<th class="head1">车牌</th>
								<th class="head0">员工姓名</th>
								<th class="head1">手机</th>
								<th class="head0">取车时间</th>
								<th class="head1">取车租赁点</th>
								<th class="head0">取车经办人</th>
								<th class="head1">还车时间</th>
								<th class="head0">还车租赁点</th>
								<th class="head1">还车经办人</th>
								<th class="head0">行驶公里数</th>
								<th class="head1">查看更多</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">订单号</th>
								<th class="head1">车牌</th>
								<th class="head0">员工姓名</th>
								<th class="head1">手机</th>
								<th class="head0">取车时间</th>
								<th class="head1">取车租赁点</th>
								<th class="head0">取车经办人</th>
								<th class="head1">还车时间</th>
								<th class="head0">还车租赁点</th>
								<th class="head1">还车经办人</th>
								<th class="head0">行驶公里数</th>
								<th class="head1">查看更多</th>
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>
					<br /> <br />
				</div>
				<div id="unit" class="subcontent" style="display: none;">
					<div class="overviewhead">
						<div class="overviewselect">
							<select id="siteId1" name="select" class="chzn-select"
								style="width: 200px;" tabindex="2">
								<option value="">请选择租赁点</option>
							</select>
						</div>
						日期:&nbsp;&nbsp;&nbsp;<input type="text" id="beginTime1" />
						&nbsp;&nbsp; 到: &nbsp;&nbsp;<input
							type="text" id="endTime1" />&nbsp;&nbsp;&nbsp;姓名:
						&nbsp;<input type="text" id="userName1" /> &nbsp; 手机: &nbsp;<input
							type="text" id="mobilePhone1" /> &nbsp; &nbsp;<br /> <br /> 订单号: 
							GWYC_<input type="text" id="orderId1" />&nbsp; &nbsp;车牌: 
							&nbsp;<input type="text" id="carNumber1" />&nbsp; &nbsp;取车经办人： &nbsp;<input
							type="text" id="menForGet1" />&nbsp; &nbsp;还车经办人： &nbsp;<input type="text"
							id="menForReturn1" />&nbsp; &nbsp;
						<button class="publishbutton radius3" id="queryBtn1">查询</button>
						&nbsp;&nbsp;
						<button class="publishbutton radius3" id="excelBtn1">导出EXCEL</button>
						&nbsp;
					</div>
					<br clear="all" />
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="unitCustomersTable">
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
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
						</colgroup>
						<thead>
							<tr>
								<th class="head0">订单号</th>
								<th class="head1">车牌</th>
								<th class="head0">员工姓名</th>
								<th class="head1">手机</th>
								<th class="head0">取车时间</th>
								<th class="head1">取车租赁点</th>
								<th class="head0">取车经办人</th>
								<th class="head1">还车时间</th>
								<th class="head0">还车租赁点</th>
								<th class="head1">还车经办人</th>
								<th class="head0">行驶公里数</th>
								<th class="head1">查看更多</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">订单号</th>
								<th class="head1">车牌</th>
								<th class="head0">员工姓名</th>
								<th class="head1">手机</th>
								<th class="head0">取车时间</th>
								<th class="head1">取车租赁点</th>
								<th class="head0">取车经办人</th>
								<th class="head1">还车时间</th>
								<th class="head0">还车租赁点</th>
								<th class="head1">还车经办人</th>
								<th class="head0">行驶公里数</th>
								<th class="head1">查看更多</th>
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>
					<br /> <br />
				</div>
			</div>
		</div>
	</div>
</body>
</html>

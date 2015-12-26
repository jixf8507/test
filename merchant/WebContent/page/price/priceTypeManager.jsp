<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript"
	src="${ctx}/js/page/price/priceTypeManager.js"></script>
</head>
<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />
		<div class="centercontent">
			<div class="pageheader">
				<h1 class="pagetitle">分时租赁套餐管理</h1>
				<span class="pagedesc"></span>
				<ul class="hornav">
					<li class="current"><a href="#updates">当前可用套餐</a></li>
					<li><a href="#activities" id="overDiv">已结束租赁套餐</a></li>
				</ul>
			</div>
			<div id="contentwrapper" class="contentwrapper">
				<div id="updates" class="subcontent">
					<div class="overviewhead">
						套餐名称: &nbsp;<input type="text" id="preTypeName" /> &nbsp; &nbsp;
						<button class="publishbutton radius3" id="preQueryBtn">查询</button>
						<button class="publishbutton radius3" id="preExcelBtn">导出EXCEL</button>
					</div>
					<br clear="all" />
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="priceTypesTable">
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
								<th class="head0">业务套餐</th>
								<th class="head1">每小时收费</th>
								<th class="head0">晚间收费</th>
								<th class="head1">当日最高收费</th>
								<th class="head0">开始租赁时间</th>
								<th class="head1">结束租赁时间</th>
								<th class="head0">每天最多行驶公里</th>
								<th class="head1">超出公里收费</th>
								<th class="head0">管理</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">业务套餐</th>
								<th class="head1">每小时收费</th>
								<th class="head0">晚间收费</th>
								<th class="head1">当日最高收费</th>
								<th class="head0">开始租赁时间</th>
								<th class="head1">结束租赁时间</th>
								<th class="head0">每天最多行驶公里</th>
								<th class="head1">超出公里收费</th>
								<th class="head0">管理</th>
							</tr>
						</tfoot>
						<tbody>
						</tbody>
					</table>
					<br /> <br />
				</div>
				<div id="activities" class="subcontent" style="display: none;">
					<div class="overviewhead">
						套餐名称: &nbsp;<input type="text" id="overTypeName" /> &nbsp; &nbsp;
						<button class="publishbutton radius3" id="overQueryBtn">查询</button>
						<button class="publishbutton radius3" id="overExcelBtn">导出EXCEL</button>
					</div>
					<br clear="all" />
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="overPriceTable">
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
								<th class="head0">业务套餐</th>
								<th class="head1">每小时收费</th>
								<th class="head0">晚间收费</th>
								<th class="head1">当日最高收费</th>
								<th class="head0">开始租赁时间</th>
								<th class="head1">结束租赁时间</th>
								<th class="head0">每天最多行驶公里</th>
								<th class="head1">超出公里收费</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">业务套餐</th>
								<th class="head1">每小时收费</th>
								<th class="head0">晚间收费</th>
								<th class="head1">当日最高收费</th>
								<th class="head0">开始租赁时间</th>
								<th class="head1">结束租赁时间</th>
								<th class="head0">每天最多行驶公里</th>
								<th class="head1">超出公里收费</th>
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>
				</div>
			</div>
			<br clear="all" />
		</div>
	</div>
</body>
</html>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script src="${ctx}/js/hcharts/highcharts.js"></script>
<script src="${ctx}/js/hcharts/highcharts_theme.js"></script>
<script type="text/javascript"
	src="${ctx}/js/page/statistics/siteLease.js"></script>
</head>

<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />

		<div class="centercontent">
			<div class="pageheader">
				<h1 class="pagetitle">租赁点租赁统计分析</h1>
				<span class="pagedesc"></span>
			</div>
			<div id="contentwrapper" class="contentwrapper">
				<div id="updates" class="subcontent">
				
				<div class="overviewhead">
						<div class="overviewselect"></div>
						<!--floatright-->
						名称: &nbsp;<input type="text" id="querySiteName" />&nbsp; &nbsp;
						日期: &nbsp;<input type="text" id="beginTime" />&nbsp;&nbsp; 
						到: &nbsp;<input type="text" id="endTime" />&nbsp; &nbsp;
						<button class="publishbutton radius3" id="queryBtn">查询</button>&nbsp;
						<button class="publishbutton radius3" id="excelBtn">导出EXCEL</button>
					</div>
				
				<div id="charts" class="subcontent">
				<br clear="all" />
				<div class="one_half" style="width: 47%; min-width: 500px; ">
					<div class="widgetbox">
						<div class="widgetcontent userlistwidget nopadding">
							<a class="more" href="${ctx}/monitor/main/siteGet.htm" target="_blank"
								style="text-align: left; padding-left: 10px;">租赁点取车次数统计</a>
							<div id="getChart" style="height: 400px;"></div>
						</div>
					</div> 
				</div>
  
				<div class="one_half" style="width: 47%; min-width: 500px; ">
					<div class="widgetbox">
						<div class="widgetcontent userlistwidget nopadding">
							<a class="more" href="${ctx}/monitor/main/siteReturn.htm" target="_blank"
								style="text-align: left; padding-left: 10px;">租赁点还车次数统计</a>
							<div id="returnChart" style="height: 400px;"></div>
						</div>
					</div>
				</div>
			</div>
					<br clear="all" />
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="costsTable">
						<colgroup>
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
						</colgroup>
						<thead>
							<tr>
								<th class="head0">租赁点名称</th>
								<th class="head1">类型</th>
								<th class="head0">取车次数</th>
								<th class="head1">还车次数</th>
								<th class="head0">租赁收费</th>
								<th class="head1">分析图表</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">租赁点名称</th>
								<th class="head1">类型</th>
								<th class="head0">取车次数</th>
								<th class="head1">还车次数</th>
								<th class="head0">租赁收费</th>
								<th class="head1">分析图表</th>
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
<br clear="all" />
</body>
</html>

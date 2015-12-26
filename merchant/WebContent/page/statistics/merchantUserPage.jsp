<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript"
	src="${ctx}/js/plugins/chosen.jquery.min.js"></script>
<script src="${ctx}/js/hcharts/highcharts.js"></script>
<script src="${ctx}/js/hcharts/highcharts_theme.js"></script>
<script type="text/javascript"
	src="${ctx}/js/page/statistics/merchantUserPage.js"></script>
</head>

<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />

		<div class="centercontent">
			<div class="pageheader">
				<h1 class="pagetitle">每日业务报表</h1>
				<span class="pagedesc"></span>
			</div>
			<!--pageheader-->
			<div id="contentwrapper" class="contentwrapper">
				<div id="updates" class="subcontent">
					<div class="overviewhead">
						<div class="overviewselect">
							<select id="siteId" name="select" class="chzn-select"
								style="width: 200px;" tabindex="2">
								<option value="">请选择租赁点</option>
							</select>
						</div>
						<!--floatright-->
						日期: &nbsp;<input type="text" id="beginTime" />&nbsp;&nbsp; 到:
					&nbsp;<input type="text" id="endTime" />&nbsp; &nbsp;员工姓名:
						&nbsp;<input type="text" id="name" /> &nbsp; &nbsp;
						<button class="publishbutton radius3" id="queryBtn">查询</button>
						<button class="publishbutton radius3" id="excelBtn">导出EXCEL</button>
					</div>
					<!--overviewhead-->
					<br clear="all" />
					<div class="one_half " style="width: 98%; min-width: 500px;">
						<div class="widgetbox">
							<div class="widgetcontent userlistwidget nopadding">
								<a class="more" href="${ctx}/monitor/main/today.htm" target="_blank"
									style="text-align: left; padding-left: 10px;">取还车时段分布</a>
								<div id="yestoday" style="height: 300px;"></div>
							</div>
						</div>
					</div>
					<!--contenttitle-->
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						align="center" id="table">
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
							<col class="con0" />
						</colgroup>
						<thead>
							<tr>
								<th colspan="13" align="center" id="dateTh">日期</th>
							</tr>
							<tr>
								<th colspan="4" align="center" id="get">取车</th>
								<th colspan="4" align="center" id="return">还车</th>
								<th colspan="5" align="center" id="customer">开户</th>
							</tr>
							<tr>
								<th class="head0">车牌号码</th>
								<th class="head1">取车时间</th>
								<th class="head0">租赁点</th>
								<th class="head1">经办人</th>
								<th class="head0">车牌号码</th>
								<th class="head1">还车时间</th>
								<th class="head0">租赁点</th>
								<th class="head1">经办人</th>
								<th class="head0">客户手机号码</th>
								<th class="head1">姓名</th>
								<th class="head0">开户时间</th>
								<th class="head1">租赁点</th>
								<th class="head0">经办人</th>
							</tr>
						</thead>
						<tbody id="myHtml">
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
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

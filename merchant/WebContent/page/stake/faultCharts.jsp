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
<script type="text/javascript" src="${ctx}/js/page/stake/faultCharts.js"></script>
</head>

<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />

		<div class="centercontent">

			<div class="pageheader">
				<h1 class="pagetitle">充电故障统计图</h1>
			</div>

			<div id="contentwrapper" class="contentwrapper">
				<div class="overviewhead">
					 日期:&nbsp;<input type="text" id="beginTime" />&nbsp; &nbsp;
					  到: &nbsp;<input type="text" id="endTime" />&nbsp; &nbsp;
					<button class="publishbutton radius3" id="queryBtn">查询</button> &nbsp;
				</div>
				<br clear="all" />
				<div id="list" class="subcontent"></div>
			</div>
			<br clear="all" />
		</div>
	</div>

</body>
</html>

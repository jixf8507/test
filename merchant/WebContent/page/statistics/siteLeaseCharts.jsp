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
	src="${ctx}/js/page/statistics/siteLeaseCharts.js"></script>
</head>

<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />

		<div class="centercontent">
			<div class="pageheader">
				<h1 class="pagetitle">${sitePO.siteName}</h1>
				<span class="pagedesc"></span>
			</div>
			<div id="contentwrapper" class="contentwrapper">
				<input type="hidden" id="siteId" value="${sitePO.id}" />
				<div class="one_half " style="width: 47%; min-width: 500px;">
					<div class="widgetbox">
						<div class="widgetcontent userlistwidget nopadding">
							<a class="more" href="#"
								style="text-align: left; padding-left: 10px;">今日</a>
							<div id="today" style="height: 300px;"></div>
						</div>
					</div>
				</div>

				<div class="one_half " style="width: 47%; min-width: 500px;">
					<div class="widgetbox">
						<div class="widgetcontent userlistwidget nopadding">
							<a class="more" href="#"
								style="text-align: left; padding-left: 10px;">昨日</a>
							<div id="yestoday" style="height: 300px;"></div>
						</div>
					</div>
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

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
<script type="text/javascript" src="${ctx}/js/page/statistics/cost.js"></script>
</head>

<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />

		<div class="centercontent">
			<div class="pageheader">
				<h1 class="pagetitle">租赁收费统计</h1>
				<span class="pagedesc"></span>
			</div>
			<div id="contentwrapper" class="contentwrapper">

				<div class="overviewhead">
					<!--floatright-->
					日期: &nbsp;<input type="text" id="beginTime" />&nbsp;&nbsp; 到:
					&nbsp;<input type="text" id="endTime" />&nbsp; &nbsp;
					<button class="publishbutton radius3" id="queryBtn">查询</button>
					&nbsp;
				</div>
				<br clear="all" />
				<div class="one_half " style="width: 47%; min-width: 500px;">
					<div class="widgetbox">
						<div class="widgetcontent userlistwidget nopadding">
							<a class="more" href="${ctx}/monitor/main/siteCharge.htm" target="_blank"
								style="text-align: left; padding-left: 10px;">租赁点租赁收费</a>
							<div id="siteCost" style="height: 300px;"></div>
						</div>
					</div>
				</div>

				<div class="one_half " style="width: 47%; min-width: 500px;">
					<div class="widgetbox">
						<div class="widgetcontent userlistwidget nopadding">
							<a class="more" href="${ctx}/monitor/main/getReturnTime.htm" target="_blank"
								style="text-align: left; padding-left: 10px;">取还车时间段分布统计</a>
							<div id="site" style="height: 300px;"></div>
						</div>
					</div>
				</div>



				<div class="one_half" style="width: 98%; min-width: 500px;">
					<div class="widgetbox">
						<div class="widgetcontent userlistwidget nopadding">
							<a class="more" href="${ctx}/monitor/main/monthCharge.htm" target="_blank"
								style="text-align: left; padding-left: 10px;">月租赁收费</a>
							<div id="month" style="height: 300px;"></div>
						</div>
					</div>
				</div>
				<!--one_half-->

				<br clear="all" /> <br />

				<div class="one_half" style="width: 98%; min-width: 800px;">
					<div class="widgetbox">
						<div class="widgetcontent userlistwidget nopadding">
							<a class="more" href="${ctx}/monitor/main/dayCharge.htm" target="_blank"
								style="text-align: left; padding-left: 10px;">日收益统计图</a>
							<div id="day" style="height: 300px;"></div>
							<br /> <small>每日运营收费变化</small>
						</div>
					</div>
				</div>
				<!--one_half-->

				<br clear="all" />
			</div>
			<!--contentwrapper-->
			<br clear="all" />
		</div>
		<!-- centercontent -->
	</div>
	<!--bodywrapper-->

</body>
</html>

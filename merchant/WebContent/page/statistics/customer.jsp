<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>test</title>
<script src="${ctx}/js/hcharts/highcharts.js"></script>
<script src="${ctx}/js/hcharts/highcharts_theme.js"></script>
<script type="text/javascript" src="${ctx}/js/page/statistics/customer.js"></script>
</head>

<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />

		<div class="centercontent">
			<div class="pageheader">
				<h1 class="pagetitle">客户办卡统计</h1>
				<span class="pagedesc"></span>
			</div>
			<div id="contentwrapper" class="contentwrapper">
			<div id="updates" class="subcontent">
				
					<div class="overviewhead">
					<div class="overviewselect"></div>
						<!--floatright-->
						日期: &nbsp;<input type="text" id="beginTime" />&nbsp;&nbsp; 
						到: &nbsp;<input type="text" id="endTime" />&nbsp; &nbsp;
						<button class="publishbutton radius3" id="queryBtn">查询</button>&nbsp;
					</div>
					<!--one_half-->
					
					<div id="charts" class="subcontent">
					<br clear="all" />
					<div class="one_half" style="width: 47%; min-width: 500px;">
						<div class="widgetbox">
							<div class="widgetcontent userlistwidget nopadding">
								<a class="more" href="#"
									style="text-align: left; padding-left: 10px;">各租赁点开户办卡统计图</a>
								<div id="site" style="height: 300px;"></div>
							</div>
						</div>
					</div>
				</div>
				
				<div class="one_half" style="width: 47%; min-width: 500px;">
					<div class="widgetbox">
						<div class="widgetcontent userlistwidget nopadding">
							<a class="more" href="#"
								style="text-align: left; padding-left: 10px;">各租赁点在使用卡统计图</a>
							<div id="site_normal" style="height: 300px;"></div>
						</div>
					</div>
				</div>
				
				<div class="one_half" style="width: 47%; min-width: 500px;">
					<div class="widgetbox">
						<div class="widgetcontent userlistwidget nopadding">
							<a class="more" href="#"
								style="text-align: left; padding-left: 10px;">各租赁点卡冻结统计图</a>
							<div id="site_frozen" style="height: 300px;"></div>
						</div>
					</div>
				</div>
				
				
				<div class="one_half" style="width: 47%; min-width: 500px;">
					<div class="widgetbox">
						<div class="widgetcontent userlistwidget nopadding">
							<a class="more" href="#"
								style="text-align: left; padding-left: 10px;">各租赁点卡注销统计图</a>
							<div id="site_cancel" style="height: 300px;"></div>
						</div>
					</div>
				</div>
								
				<!--one_half last-->

				<!-- <br clear="all" /> <br />

				<div class="one_half" style="width: 98%; min-width: 800px;">
					<div class="widgetbox">
						<div class="widgetcontent userlistwidget nopadding">
							<a class="more" href="#"
								style="text-align: left; padding-left: 10px;">租赁点每日办卡数</a>
							<div id="day" style="height: 300px;"></div>
							<br />
						</div>
					</div>
				</div> -->
				<!--one_half-->

				<br clear="all" />
				
				
				<div class="one_half" style="width: 98%; min-width: 800px;">
					<div class="widgetbox">
						<div class="widgetcontent userlistwidget nopadding">
							<a class="more" href="#"
								style="text-align: left; padding-left: 10px;">租赁点每日开户注销人数</a>
							<div id="day_count" style="height: 300px;"></div>
							<br />
						</div>
					</div>
				</div>
				<!--one_half-->

				<br clear="all" />
				
				
				
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

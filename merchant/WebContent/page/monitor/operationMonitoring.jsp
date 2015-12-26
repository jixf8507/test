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
<script type="text/javascript" src="${ctx}/js/page/monitor/operationMonitoring.js"></script>
</head>
	<body style="padding-left: 60px;">

	
						<br clear="all" />
						<div class="one_half " style="width: 47%; min-width: 500px;">
							<div class="widgetbox">
								<div class="widgetcontent userlistwidget nopadding">
									<a class="more" href="${ctx}/monitor/main/siteCharge.htm" target="_blank"
										style="text-align: left; padding-left: 10px;">租赁点取车次数比例图</a>
									<div id="getChart" style="height: 300px;"></div>
								</div>
							</div>
						</div>
		
						<div class="one_half " style="width: 47%; min-width: 500px;">
							<div class="widgetbox">
								<div class="widgetcontent userlistwidget nopadding">
									<a class="more" href="${ctx}/monitor/main/getReturnTime.htm" target="_blank"
										style="text-align: left; padding-left: 10px;">租赁点还车次数比例图</a>
									<div id="returnChart" style="height: 300px;"></div>
								</div>
							</div>
						</div>
		
			
						<div class="one_half " style="width: 47%; min-width: 500px;">
							<div class="widgetbox">
								<div class="widgetcontent userlistwidget nopadding">
									<a class="more" href="${ctx}/monitor/main/siteCharge.htm" target="_blank"
										style="text-align: left; padding-left: 10px;">租赁点次数分时段统计柱状图</a>
									<div id="columnChart" style="height: 300px;"></div>
								</div>
							</div>
						</div>
		
						<div class="one_half " style="width: 47%; min-width: 500px;">
							<div class="widgetbox">
								<div class="widgetcontent userlistwidget nopadding">
									<a class="more" href="${ctx}/monitor/main/getReturnTime.htm" target="_blank"
										style="text-align: left; padding-left: 10px;">租赁点次数分时段统计饼状图</a>
									<div id="pieChart" style="height: 300px;"></div>
								</div>
							</div>
						</div>
		
		
						<!--one_half-->
		
						<br clear="all" /> <br />
		
						<div class="one_half" style="width: 98%; min-width: 800px;">
							<div class="widgetbox">
								<div class="widgetcontent userlistwidget nopadding">
									<a class="more" href="${ctx}/monitor/main/dayCharge.htm" target="_blank"
										style="text-align: left; padding-left: 10px;">租赁次数计图</a>
									<div id="day" style="height: 300px;"></div>
									<br /> <small>每日租赁次数变化</small>
								</div>
							</div>
						</div>
						<!--one_half-->
		
						<br clear="all" />


</body>
</html>
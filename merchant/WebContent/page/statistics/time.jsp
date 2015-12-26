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
<script type="text/javascript" src="${ctx}/js/page/statistics/time.js"></script>
</head>

<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />

		<div class="centercontent">
			<div class="pageheader">
				<h1 class="pagetitle">租赁次数分时段统计</h1>
				<span class="pagedesc"></span> 
				
				<div class="overviewhead">
				 		<div class="overviewselect" style="float: left; padding-left: 20px;">
							<select id="siteId" name="select" class="chzn-select"
								style="width: 200px;" tabindex="2">
								<option value="">请选择站点</option>
							</select>
						</div>
				</div>
				
			</div>
			
			<div id="contentwrapper" class="contentwrapper"> 
			
			<div id="charts" class="subcontent">
					
					
				
				<div class="one_half " style="width: 47%; min-width: 450px; ">
					<div class="widgetbox">
						<div class="widgetcontent userlistwidget nopadding">
							<a class="more" href="${ctx}/monitor/main/timeColum.htm" target="_blank"
								style="text-align: left; padding-left: 10px;">租赁次数分时段统计柱状图</a>
							<div id="columnChart" style="height: 400px;"></div>
						</div>
					</div> 
				</div>

				<div class="one_half" style="width: 47%; min-width: 450px; ">
					<div class="widgetbox">
						<div class="widgetcontent userlistwidget nopadding">
							<a class="more" href="${ctx}/monitor/main/timePie.htm" target="_blank"
								style="text-align: left; padding-left: 10px;">租赁次数分时段统计比例图</a>
							<div id="pieChart" style="height: 400px;"></div>
						</div>
					</div>
				</div>
				</div>
			</div>
			<br clear="all" />
		</div>
	</div>

</body>
</html>

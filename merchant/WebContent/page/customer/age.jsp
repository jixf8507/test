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
<script type="text/javascript" src="${ctx}/js/page/customer/age.js"></script>
</head>

<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />

		<div class="centercontent">
			<div class="pageheader">
				<h1 class="pagetitle">用户年龄分时段统计</h1>
				<span class="pagedesc"></span> 
				
				<!-- <div class="overviewhead">
				 		<div class="overviewselect" style="float: left; padding-left: 20px;">
							<select id="merchantId" name="select" class="chzn-select"
								style="width: 200px;" tabindex="2">
								<option value="">请选择商家</option>
							</select>
						</div>
				</div> -->
				
			</div>
			
			<div id="contentwrapper" class="contentwrapper"> 
			
				<div id="charts" class="subcontent">			
					<div class="one_half" style="width: 47%; min-width: 450px; ">
						<div class="widgetbox">
							<div class="widgetcontent userlistwidget nopadding">
								<a class="more" href="#"
									style="text-align: left; padding-left: 10px;">用户人数分年龄段统计柱状图</a>
								<div id="customerColumnChart" style="height: 400px;"></div>
							</div>
						</div>
					</div>
					
					
					<div class="one_half" style="width: 47%; min-width: 450px; ">
						<div class="widgetbox">
							<div class="widgetcontent userlistwidget nopadding">
								<a class="more" href="#"
									style="text-align: left; padding-left: 10px;">用户人数分年龄段统计饼状图</a>
								<div id="customerPieChart" style="height: 400px;"></div>
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

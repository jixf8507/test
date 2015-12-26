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
<script type="text/javascript" src="${ctx}/js/page/statistics/type.js"></script>
</head>

<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />
		<div class="centercontent">
			<div class="pageheader">
				<h1 class="pagetitle">租赁类型统计</h1>
				<span class="pagedesc"></span>
			</div>
			<div id="contentwrapper" class="contentwrapper">
				<div id="updates" class="subcontent">
					<div class="overviewhead">
						<div class="overviewselect">
							<select id="siteId" name="select" class="chzn-select"
								style="width: 200px;" tabindex="2">
								<option value="">请选择站点</option>
							</select>
						</div>
						还车日期: &nbsp;<input type="text" id="beginTime" />&nbsp;&nbsp; 到:
						&nbsp;<input type="text" id="endTime" /> &nbsp; &nbsp;
						<button class="publishbutton radius3" id="queryBtn">查询</button>
					</div>
					<br clear="all" />
					<div class="one_half " style="width: 47%; min-width: 450px;">
						<div class="widgetbox">
							<div class="widgetcontent userlistwidget nopadding">
								<a class="more" href="#" style="text-align: left; padding-left: 10px;">租赁类型统计柱状图</a>
								<div id="columnChart" style="height: 400px;"></div>
							</div>
						</div>
					</div>

					<div class="one_half" style="width: 47%; min-width: 450px;">
						<div class="widgetbox">
							<div class="widgetcontent userlistwidget nopadding">
								<a class="more" href="#" style="text-align: left; padding-left: 10px;">租赁类型统计比例图</a>
								<div id="pieChart" style="height: 400px;"></div>
							</div>
						</div>
					</div>
				</div>
				<br clear="all" />
			</div>
		</div>
	</div>
</body>
</html>

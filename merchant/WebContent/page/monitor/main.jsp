<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>

<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=hvAmVntuPfdU9zzuQvvR5PQq"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/library/DistanceTool/1.2/src/DistanceTool_min.js"></script>
<script type="text/javascript"
	src="http://developer.baidu.com/map/jsdemo/demo/convertor.js"></script>
<script type="text/javascript"
	src="${ctx}/js/page/monitor/merchantResouce.js"></script>
<script type="text/javascript" src="${ctx}/js/page/monitor/mainMaps.js"></script>
<script type="text/javascript" src="${ctx}/js/page/monitor/main.js"></script>
</head>
<style type="text/css">
body,html,#allmap {
	width: 100%;
	height: 100%;
	overflow: visible;
	margin: 0;
}

#l-map {
	height: 100%;
	width: 78%;
	float: left;
	border-right: 2px solid #bcbcbc;
}

#r-result {
	height: 100%;
	width: 20%;
	float: left;
}

.aaa {
	position: relative;
}

.aaa div {
	display: none;
}

.aaa:hover div {
	position: absolute;
	display: block;
	min-width: 400px;
	top: 60px;
	border: 1px solid rgb(91, 185, 233);
	background-color: rgb(228, 246, 255);
	z-index: 999;
}
</style>

<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />
		<div class="centercontent">
			<div class="pageheader">
				<h1 class="pagetitle">监控中心</h1>
				<span class="pagedesc"> 商家资源：<a
					href="${ctx}/monitor/main/resource.htm" target="_blank">点击访问</a>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;网点分布：<a
					href="${ctx}/monitor/main/map.htm" target="_blank">点击访问</a>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;运营监控：<a
					href="${ctx}/monitor/main/operationMonitoring.htm" target="_blank">点击访问</a>

				</span>
				<ul class="hornav">
					<li class="current"><a href="#allResourse,#updates">商家资源</a></li>
					<li><a href="#maps" id="map">网点分布</a></li>
				</ul>
			</div>

			<div id="contentwrapper" class="contentwrapper">
				<div id="allResourse" class="subcontent"></div>
				<div id="updates" class="subcontent">
					<img src="${ctx}/images/loaders/loader10.gif" alt="正在加载数据">
				</div>

				<div id="maps" class="subcontent" style="display: none;">
					<div id="r-result" style="display: inline;">
						&nbsp;&nbsp;<input type="text" id="suggestId" size="20" value=""
							style="width: 200px;" placeholder="输入关键字查找" />
					</div>
					<div id="searchResultPanel"
						style="border: 1px solid #C0C0C0; width: 150px; height: auto; display: none;"></div>
					车牌：  <input type="text" name="carNumber" id="carNumber" value=""></input>&nbsp; &nbsp; 
					<button class="publishbutton radius3" id="queryBtn">查询</button>&nbsp; 
					<br />
					<input type="hidden" name="city" id="city" value="${city }"/> 
					<div id="allmap"
						style="width: 100%; height: 600px; overflow: visible; margin: 0;"></div>

				</div>
			</div>
			<br clear="all" />
		</div>
	</div>

</body>
</html>

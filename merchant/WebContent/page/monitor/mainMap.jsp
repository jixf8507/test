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
<script type="text/javascript" src="${ctx}/js/page/monitor/mainMap.js"></script>
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
 
<input type="hidden" name="carNumber" id="carNumber" value=""></input>
<input type="hidden" name="city" id="city" value="${city }"/> 
<div id="allmap"
	style="width: 100%; height: 100%; overflow: visible; margin: 0;"></div>

</html>

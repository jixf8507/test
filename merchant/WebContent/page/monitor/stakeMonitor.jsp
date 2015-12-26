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
	src="${ctx}/js/plugins/jquery.slimscroll.js"></script>
<script type="text/javascript"
	src="${ctx}/js/page/monitor/stakeMonitor.js"></script>
<script type="text/javascript" src="${ctx}/js/page/monitor/stakeMaps.js"></script>
</head>

<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />
		<div class="centercontent">
			<div class="pageheader">
				<h1 class="pagetitle">充电监控</h1>
				<span class="pagedesc"></span>
				<ul class="hornav">
					<li class="current"><a href="#updates">充电监控</a></li>
					<li class=""><a href="#maps" id="mapsDiv">地图分布</a></li>
				</ul>
			</div>

			<%-- <div id="contentwrapper" class="contentwrapper">
				<div id="updates" class="subcontent">
					<img src="${ctx}/images/loaders/loader10.gif" alt="正在加载数据">
				</div>
			</div> --%>

			<div id="contentwrapper" class="contentwrapper">
				<div id="updates" class="subcontent">
					<div class="overviewhead">
						名称: &nbsp;<input type="text" id="querySiteName" />&nbsp; &nbsp;
						<button class="publishbutton radius3" id="queryBtn">查询</button>
						&nbsp;
					</div>

					<!-- <div class="overviewhead">
						<button class="publishbutton radius3" id="addBtn">新增服务中心</button>
					</div> -->

					<!--overviewhead-->
					<br clear="all" />
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="sitesTable">
						<colgroup>
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
						</colgroup>
						<thead>
							<tr>
								<th class="head1">图片</th>
								<th class="head0">充电站名称</th>
								<th class="head1">具体地址</th>
								<th class="head0">负责人</th>
								<th class="head1">联系电话</th>
								<th class="head0">充电桩数量</th>
								<th class="head1">类型</th>
								<th class="head0">监控</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head1">图片</th>
								<th class="head0">充电站名称</th>
								<th class="head1">具体地址</th>
								<th class="head0">负责人</th>
								<th class="head1">联系电话</th>
								<th class="head0">充电桩数量</th>
								<th class="head1">类型</th>
								<th class="head0">监控</th>
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>
					<br /> <br />
				</div>
				<div id="maps" class="subcontent" style="display: none;">
					<div id="r-result" style="display: inline;">
						&nbsp;&nbsp;<input type="text" id="suggestId" size="20" value=""
							style="width: 200px;" placeholder="输入关键字查找" />
					</div>
					<input type="hidden" name="city" id="city" value="${city }" />
					<div id="searchResultPanel"
						style="border: 1px solid #C0C0C0; width: 150px; height: auto; display: none;"></div>
					<br />
					<div id="stakemap"
						style="width: 100%; height: 600px; overflow: visible; margin: 0;"></div>

				</div>
			</div>

		</div>
		<br clear="all" />
	</div>

</body>
</html>

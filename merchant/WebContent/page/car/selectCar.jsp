<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript" src="${ctx}/js/page/car/selectCar.js"></script>
</head>

<body>
	<input type="hidden" id="noCarDevice" value="${param.noCarDevice}" />
	<div class="overviewhead">
		<div class="overviewselect">
			<select id="useCurSiteId" name="select" class="chzn-select"
				style="width: 200px;" tabindex="2">
				<option value="">请选择站点</option>
			</select>
		</div>
		<!--floatright-->
		车牌号码: &nbsp;<input type="text" id="useCarNumber" /> &nbsp; &nbsp;
		<button class="publishbutton radius3" id="useQueryBtn">查询</button>
	</div>
	<!--overviewhead-->
	<br clear="all" />
	<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
		id="useCarsTable">
		<colgroup>
			<col class="con0" width="10%" />
			<col class="con1" />
			<col class="con0" />
			<col class="con1" />
			<col class="con0" />
			<col class="con1" />
		</colgroup>
		<thead>
			<tr>
				<th class="head0">选择</th>
				<th class="head1">车牌号码</th>
				<th class="head0">商家</th>
				<th class="head1">车型</th>
				<th class="head0">当前站点</th>
				<th class="head1">设备</th>
			</tr>
		</thead>
		<tfoot>
			<tr>
				<th class="head0">选择</th>
				<th class="head1">车牌号码</th>
				<th class="head0">商家</th>
				<th class="head1">车型</th>
				<th class="head0">当前站点</th>
				<th class="head1">设备</th>
			</tr>
		</tfoot>
		<tbody>

		</tbody>
	</table>
</body>
</html>

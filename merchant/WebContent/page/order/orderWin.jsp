<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>

<script type="text/javascript" src="${ctx}/js/page/order/orderWin.js"></script>
</head>

<body>
	<input type="hidden" id="peccancyTime" name="peccancyTime"
		value="${param.peccancyTime}" />
	<div class="overviewhead"> 
		车牌号码: &nbsp;<input type="text" id="prepCarNumber" />
		&nbsp;&nbsp;车牌识别号:  &nbsp;<input type="text" id="vin" />
		&nbsp;&nbsp;<button class="publishbutton radius3" id="prepQueryBtn">查询</button>
	</div>
	<!--contenttitle-->
	<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
		id="prepOrdersTable">
		<colgroup>
			<col class="con0" width="10%" />
			<col class="con1" />
			<col class="con0" />
			<col class="con1" />
			<col class="con0" />
			<col class="con1" />
			<col class="con0" />
			<col class="con1" />
			<col class="con0" />
			<col class="con1" />
		</colgroup>
		<thead>
			<tr>
				<th class="head0">选择</th>
				<th class="head1">订单号</th>
				<th class="head0">车牌</th>
				<th class="head1">车牌识别号</th>
				<th class="head0">姓名</th>
				<th class="head1">手机</th>
				<th class="head0">取车时间</th>
				<th class="head1">取车租赁点</th>
				<th class="head0">还车时间</th>
				<th class="head1">还车租赁点</th>
			</tr>
		</thead>
		<tfoot>
			<tr>
				<th class="head0">选择</th>
				<th class="head1">订单号</th>
				<th class="head0">车牌</th>
				<th class="head1">车牌识别号</th>
				<th class="head0">姓名</th>
				<th class="head1">手机</th>
				<th class="head0">取车时间</th>
				<th class="head1">取车租赁点</th>
				<th class="head0">还车时间</th>
				<th class="head1">还车租赁点</th>
			</tr>
		</tfoot>
		<tbody>
		</tbody>
	</table>
</body>
</html>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript"
	src="${ctx}/js/page/merchant/couponWin.js"></script>
</head>

<body>
	<div class="overviewhead">
		<!-- <div class="overviewselect">
			<select id="status" name="select" class="chzn-select"
				style="width: 200px;" tabindex="2">
				<option value="">请选择优惠券状态</option>
				<option value="待用">待用</option>
				<option value="已用">已用</option>
				<option value="作废">作废</option>
			</select>
		</div> -->
		优惠券编码: &nbsp;ykzc-<input type="text" id="id" /> <input type="hidden" id="couponNo" /> &nbsp; &nbsp;
		<button class="publishbutton radius3" id="useQueryBtn">查询</button>
	</div>
	<br clear="all" />
	<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
		id="couponTable">
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
				<th class="head1">优惠券编码</th>
				<th class="head0">名称</th>
				<th class="head1">金额</th>
				<th class="head0">到期时间</th>
				<th class="head1">优惠券类型</th>
				<th class="head0">状态</th>
			</tr>
		</thead>
		<tfoot>
			<tr>
				<th class="head0">选择</th>
				<th class="head1">优惠券编码</th>
				<th class="head0">名称</th>
				<th class="head1">金额</th>
				<th class="head0">到期时间</th>
				<th class="head1">优惠券类型</th>
				<th class="head0">状态</th>
			</tr>
		</tfoot>
		<tbody>

		</tbody>
	</table>
</body>
</html>

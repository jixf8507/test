<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript"
	src="${ctx}/js/page/customer/selectCustomerCar.js"></script>
</head>
<script type="text/javascript">
	
</script>
<body style="margin-left: 6px;">
	<input type="hidden" id="noCarDevice" value="${param.noCarDevice}" />
	<input type="hidden" id="customerId" value="${param.customerId}" />
	<input type="hidden" id="carId" value="" />
	<div class="overviewhead">
		车牌号码: &nbsp;<input type="text" id="carNumber" /> &nbsp; 手机号码: &nbsp;<input
			type="text" id="normalPhone" /> &nbsp;客户姓名: &nbsp;<input type="text"
			id="normalName" /> &nbsp; &nbsp;
		<button class="publishbutton radius3" id="normalQueryBtn">查询</button>
	</div>
	<!--overviewhead-->

	<br clear="all" />
	<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
		id="normalCustomersTable">
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
				<th class="head1">选择</th>
				<th class="head0">车牌号码</th>
				<th class="head1">手机号码</th>
				<th class="head0">姓名</th>
				<th class="head1">身份证/驾驶证</th>
				<th class="head0">设备编号</th>
			</tr>
		</thead>
		<tfoot>
			<tr>
				<th class="head1">选择</th>
				<th class="head0">车牌号码</th>
				<th class="head1">手机号码</th>
				<th class="head0">姓名</th>
				<th class="head1">身份证/驾驶证</th>
				<th class="head0">设备编号</th>
			</tr>
		</tfoot>
		<tbody>

		</tbody>
	</table>

</body>
</html>

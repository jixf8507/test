<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript"
	src="${ctx}/js/plugins/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="${ctx}/js/page/customer/unitCustomerRefund.js"></script>
</head>

<body>
	<form id="form1" class="stdform stdform2" method="post">

		<input type="hidden" name="id" value="${unitCustomerPO.id}" />
		<p>
			<label>余额</label> <span class="field"><input type="text"
				name="balance" value="${unitCustomerPO.balance}" class="longinput"
				style="width: 200px;" readonly="readonly" />&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>保证金</label> <span class="field"><input type="text"
				name="addBalance" class="longinput" readonly="readonly"
				value="${unitCustomerPO.moneyOfassure}" style="width: 200px;" />&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>开户银行</label> <span class="field"><input type="text"
				value="${unitCustomerPO.bankType}" name="bankType" class="longinput"
				style="width: 200px;" readonly="readonly" />&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>银行卡号</label> <span class="field"><input type="text"
				value="${unitCustomerPO.bankCardNO}" name="bankCardNO"
				class="longinput" style="width: 200px;" readonly="readonly" />&nbsp;&nbsp;</span>
		</p>
	</form>
</body>
</html>

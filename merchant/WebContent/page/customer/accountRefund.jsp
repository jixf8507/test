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
	src="${ctx}/js/page/customer/accountRefund.js"></script>
</head>

<body>
	<form id="form1" class="stdform stdform2" method="post" action="">

		<input type="hidden" name="accountId" value="${accountPO.id}" />
		<p>
			<label>余额</label> <span class="field"><input type="text"
				name="balance" value="${accountPO.balance}" class="longinput"
				style="width: 200px;" readonly="readonly" />&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>保证金</label> <span class="field"><input type="text"
				name="addBalance" class="longinput"
				value="${accountPO.moneyOfassure}" style="width: 200px;" />&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>冻结资金</label> <span class="field"><input type="text" readonly="readonly"
				name="freezeBalance" class="longinput"
				value="${accountPO.freezeBalance}" style="width: 200px;" />&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>退款租赁点</label> <span class="field"><select
				data-placeholder="选择租赁点" name="siteId" id="siteId"
				class="chzn-select" style="width: 200px;" tabindex="2">
					<option value=""></option>
			</select></span>
		</p>
		<p>
			<label>退款方式</label> <span class="field"><input type="radio"
				value="银行卡打款" name="describe" checked="checked" />银行卡打款 &nbsp;
				&nbsp;<input type="radio" value="现金退款" name="describe" /> 现金退款
				&nbsp; &nbsp; </span>
		</p>

	</form>
</body>
</html>

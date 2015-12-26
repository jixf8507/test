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
	src="${ctx}/js/page/customer/accountRepair.js"></script>
</head>

<body>
	<form id="form1" class="stdform stdform2" method="post" action=""> 
		<p>
			<label>余额</label> <span class="field">${accountPO.balance}&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>保证金</label> <span class="field">${accountPO.moneyOfassure}&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>银行卡号</label> <span class="field"><input type="text"
				value="${accountPO.bankCardNO}" name="bankCardNO" class="longinput"
				style="width: 200px;" />&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>银行类型</label> <span class="field"><input type="text"
				value="${accountPO.bankType}" name="bankType" class="longinput"
				style="width: 200px;" />&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>办理站点</label> <span class="field"><select
				data-placeholder="选择" name="siteId" id="siteId" class="chzn-select"
				style="width: 200px;" tabindex="2">
					<option value=""></option>
			</select></span>
		</p>
		<br clear="all"/>  
	</form>
</body>
</html>

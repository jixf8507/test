<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript" src="${ctx}/js/page/customer/checkFee.js"></script>
<script type="text/javascript"
	src="${ctx}/js/plugins/jquery.validate.min.js"></script>
</head>
<body>
	<div id="contentwrapper" class="contentwrapper">
		<form id="form1" class="stdform stdform2">
			<p>
				<label>票据编号</label> <span class="field"><input type="text"
					style="width: 200px;" value="${accountRecordPO.ticket}"
					name="ticket" id="ticket" />&nbsp;&nbsp;</span> <input type="hidden"
					value="${accountRecordPO.id}" name="id" />
			</p>
			<p>
				<label>核对描述</label> <span class="field"><input type="text"
					style="width: 200px;" value="${accountRecordPO.checkDescribe}"
					name="checkDescribe" id="checkDescribe" />&nbsp;&nbsp;</span>
			</p>
			<p>
				<label>交易类型</label> <span class="field">${accountRecordPO.describe}&nbsp;&nbsp;</span>
			</p>
			<p>
				<label>交易金额</label> <span class="field">${accountRecordPO.addBalance}&nbsp;&nbsp;</span>
			</p>
			<p>
				<label>交易时间</label> <span class="field">${accountRecordPO.createdTime}&nbsp;&nbsp;</span>
			</p>
			<p>
				<label>交易经办人</label> <span class="field">${accountRecordPO.transactUser}&nbsp;&nbsp;</span>
			</p>
		</form>
	</div>
</body>
</html>

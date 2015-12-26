<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript"
	src="${ctx}/js/page/customer/accountRecord.js"></script>
</head>

<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />

		<div class="centercontent">
			<div class="pageheader">
				<h1 class="pagetitle">商家账户详情</h1>
				<span class="pagedesc" style="color: red;">账户状态：${merchantPO.status}</span>
			</div>
			<!--pageheader-->
			<div id="contentwrapper" class="contentwrapper">
				<form class="stdform stdform2">
					<p>
						<label>商家</label> <span class="field">${merchantPO.merchantName}&nbsp;</span>
					</p>
					<p>
						<label>商家帐号</label> <span class="field">${merchantPO.email}&nbsp;</span>
					</p>
					<p>
						<label>法人</label> <span class="field">${merchantPO.corporation}&nbsp;</span>
					</p>
					<p>
						<label>身份证</label> <span class="field">${merchantPO.idCard}&nbsp;</span>
					</p>
					<p>
						<label>手机</label> <span class="field">${merchantPO.mobilePhone}&nbsp;</span>
					</p>
					<p>
						<label>客户服务热线</label> <span class="field">${merchantPO.hotTel}&nbsp;</span>
					</p>
					<p>
						<label>账户余额</label> <span class="field">${merchantPO.balance}元&nbsp;</span>
					</p>
					<p>
						<label>冻结资金</label> <span class="field">${merchantPO.freezeBalance}元&nbsp;</span>
					</p>
					<p>
						<label>状态</label> <span class="field">${merchantPO.status}&nbsp;</span>
					</p>
					<p>
						<label>提款企业银行帐号</label> <span class="field">${merchantPO.alipayAccount}&nbsp;</span>
					</p>
					<p>
						<label>提款企业账户名</label> <span class="field">${merchantPO.alipayAccountName}&nbsp;</span>
					</p>
					<p>
						<label>提款银行</label> <span class="field">${merchantPO.bankTypeName}&nbsp;</span>
					</p>
					<p>
						<label>是否同意保证金</label> <span class="field">${merchantPO.agreeSystemMoney}&nbsp;</span>
					</p>
					<p>
						<label>保证金金额</label> <span class="field">${merchantPO.rentalMoney}&nbsp;</span>
					</p>
					<p>
						<label>商家网站地址</label> <span class="field">${merchantPO.linkUrl}&nbsp;</span>
					</p>
					
					<p>
						<label>所在省</label> <span class="field">${merchantPO.province}&nbsp;</span>
					</p>
					<p>
						<label>所在市</label> <span class="field">${merchantPO.city}&nbsp;</span>
					</p>
				</form>
			</div>
			<!--contentwrapper-->
			<br clear="all" />
		</div>
		<!-- centercontent -->
	</div>
	<!--bodywrapper-->

</body>
</html>

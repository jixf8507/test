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
	src="${ctx}/js/page/merchant/merchantEdite.js"></script>
</head>

<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />

		<div class="centercontent">
			<div class="pageheader">
				<h1 class="pagetitle">商家账户信息修改</h1>
				<span class="pagedesc" style="color: red;">账户状态：${merchantPO.status}</span>
			</div>
			<div id="contentwrapper" class="contentwrapper">
				<form class="stdform stdform2" id="form1" method="post"
					action="${ctx}/merchant/merchant/saveEdit.htm?">
					<p>
						<label>商家帐号</label> <span class="field">${merchantPO.email}&nbsp;</span>
						<input type="hidden" name="id" id="id" value="${merchantPO.id}" />
					</p>
					<p>
						<label>商家</label> <span class="field"><input type="text"
							name="merchantName" id="merchantName" class="longinput"
							value="${merchantPO.merchantName}" /></span>
					</p>
					<p>
						<label>法人</label> <span class="field"><input type="text"
							name="corporation" id="corporation" class="longinput"
							value="${merchantPO.corporation}" /></span>
					</p>
					<p>
						<label>身份证</label> <span class="field"><input type="text"
							name="idCard" id="idCard" class="longinput"
							value="${merchantPO.idCard}" /></span>
					</p>
					<p>
						<label>手机</label> <span class="field"><input type="text"
							name="mobilePhone" id="mobilePhone" class="longinput"
							value="${merchantPO.mobilePhone}" /></span>
					</p>
					<p>
						<label>客户服务热线</label> <span class="field"><input
							type="text" name="hotTel" id="hotTel" class="longinput"
							value="${merchantPO.hotTel}" /></span>
					</p>
					<p>
						<label>提款企业银行帐号</label> <span class="field"><input
							type="text" name="alipayAccount" id="alipayAccount"
							class="longinput" value="${merchantPO.alipayAccount}" /></span>
					</p>
					<p>
						<label>提款企业账户名</label> <span class="field"><input
							type="text" name="alipayAccountName" id="alipayAccountName"
							class="longinput" value="${merchantPO.alipayAccountName}" /></span>
					</p>
					<p>
						<label>提款银行</label> <span class="field"><input type="text"
							name="bankTypeName" id="bankTypeName" class="longinput"
							value="${merchantPO.bankTypeName}" /></span>
					</p>
					<p>
						<label>是否同意保证金</label> <span class="field"> <input
							type="radio" value="是" name="agreeSystemMoney"
							id="agreeSystemMoney"
							<c:if test="${merchantPO.agreeSystemMoney==\"是\"}"> checked="checked" </c:if> />是

							<input type="radio" value="否" name="agreeSystemMoney"
							id="agreeSystemMoney"
							<c:if test="${merchantPO.agreeSystemMoney==\"否\"}"> checked="checked" </c:if> />否
						</span>

					</p>

					<p id="demo">
						<label>设置保证金</label> <span class="field"><input type="text"
							name="rentalMoney" id="rentalMoney" class="longinput"
							value="${merchantPO.rentalMoney}" /></span>
					</p>
					<p>
						<label>商家网站地址</label> <span class="field"><input
							type="text" name="linkUrl" id="linkUrl" class="longinput"
							value="${merchantPO.linkUrl}" /></span>
					</p>
					<p>
						<label>所在省</label> <span class="field"><select
							name="province" id="province" class="chzn-select"
							style="width: 200px;" tabindex="2">
								<option value=""></option>
						</select>&nbsp;&nbsp;</span> <input type="hidden" name="hiddenprovince"
							id="hiddenprovince" class="longinput"
							value="${merchantPO.province}" />
					</p>
					<p>
						<label>所在市</label> <span class="field" id="cityDiv"><select
							name="city" id="city" class="chzn-select" style="width: 200px;"
							tabindex="2">
								<option value="${merchantPO.city}"></option>
						</select>&nbsp;&nbsp;</span>
					</p>
					<p class="stdformbutton">
						<!-- <button class="submit radius2" type="submit">保存</button>-->
						<input type="submit" class="submit radius2" value="保存" />
						<input type="reset" class="reset radius2" value="重置" />
					</p>
				</form>
			</div>
			<br clear="all" />
		</div>
	</div>
</body>
</html>
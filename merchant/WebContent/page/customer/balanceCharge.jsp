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
	src="${ctx}/js/page/customer/balanceCharge.js"></script>
</head>

<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />
		<div class="centercontent">
			<div class="pageheader">
				<h1 class="pagetitle">客户账户充值</h1>
				<span class="pagedesc"></span>
			</div>
			<!--pageheader-->
			<div id="contentwrapper" class="contentwrapper">
				<div id="list" class="subcontent">
					<!--contenttitle-->
					<form id="form1" class="stdform stdform2" method="post"
						action="${ctx}/customer/account/saveBalanceCharge.htm?">
						<p>
							<c:if test="${sessionScope.user.systemName == 'stake'}">
								<label>一卡通卡号</label>
								<span class="field"><input type="text" id="cardNO"
									class="longinput" onclick="getCustomer()" style="width: 350px;"
									readonly="readonly" />
							</c:if>
							<c:if test="${sessionScope.user.systemName == 'eakay'}">
								<label>手机号码</label>
								<span class="field"><input type="text" id="phone"  name="phone" 
									class="longinput" onclick="getCustomer()" style="width: 350px;"
									readonly="readonly" />
							</c:if>
							&nbsp;&nbsp;<input type="button" value="点击获取"
								onclick="getCustomer()" /></span> <input type="hidden"
								name="customerId" id="customerId" /> <input type="hidden"
								name="accountId" id="accountId" />
						</p>
						<p>
							<label>客户姓名</label> <span class="field"><input type="text"
								id="name" class="longinput" style="width: 350px;"
								readonly="readonly" />&nbsp;&nbsp;</span>
						</p>
						<p>
							<label>身份证</label> <span class="field"><input type="text"
								id="idCard" class="longinput" style="width: 350px;" name="idCard"
								readonly="readonly" />&nbsp;&nbsp;</span>
						</p>
							<c:if test="${sessionScope.user.systemName == 'stake'}">
								<p>
									<label>手机号码</label>
									<span class="field"><input type="text"
										readonly="readonly" id="phone" name="phone" class="longinput"
										style="width: 350px;" />&nbsp;&nbsp;</span>
								</p>
							</c:if>
						<p>
							<label>余额</label> <span class="field"><input type="text"
								readonly="readonly" id="balance" class="longinput"
								style="width: 350px;" />&nbsp;&nbsp;</span>
						</p>
						<p>
							<label>充值金额</label> <span class="field"><input type="text"
								name="addBalance" id="addBalance" class="longinput"
								style="width: 350px;" />&nbsp;&nbsp;</span>
						</p>
						<p>
							<label>付费方式</label> <span class="field"> &nbsp;<input type="radio" value="刷卡付费"
								name="describe"  checked="checked" /> 刷卡付费 &nbsp; &nbsp;<input
								type="radio" value="现金付费" name="describe"/>
								现金付费 &nbsp; &nbsp; 
							</span>
						</p>
						<p>
							<label>票据编号</label> <span class="field"><input type="text"
								name="ticket" id="ticket" class="longinput"
								style="width: 350px;" />&nbsp;&nbsp;</span>
						</p>
						<p>
							<label>充值租赁点</label> <span class="field"><select
								data-placeholder="选择租赁点" name="siteId" id="siteId"
								class="chzn-select" style="width: 200px;" tabindex="2">
									<option value=""></option>
							</select></span>
						</p>
						<p class="stdformbutton">
							<button class="submit radius2">保存</button>
							<input type="reset" class="reset radius2" value="重置" />
						</p>
					</form>
				</div>
				<!-- #add -->
			</div>
			<!--contentwrapper-->
			<br clear="all" />
		</div>
		<!-- centercontent -->
	</div>
	<!--bodywrapper-->

</body>
</html>

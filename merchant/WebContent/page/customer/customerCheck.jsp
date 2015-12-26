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
	src="${ctx}/js/page/customer/customerCheck.js"></script>
<!--<script type="text/javascript"
	src="${ctx}/js/page/customer/sendVerCode.js"></script>-->
</head>

<body>
	<ul class="hornav">
		<li class="current"><a href="#form">办卡</a></li>
		<li><a href="#table">信息审核</a></li>
	</ul>

	<div id="contentwrapper" class="contentwrapper">
		<div id="table" class="subcontent" style="display: none;">

			<!--contenttitle-->

			<form class="stdform stdform2">
				<p>
					<label>手机号码</label> <span class="field">
						${customerPO.phone}&nbsp; </span>
				</p>
				<p>
					<label>姓名</label> <span class="field">${customerPO.name}&nbsp;</span>
				</p>
				<p>
					<label>身份证/驾驶证号</label> <span class="field">${customerPO.idCard}&nbsp;</span>
				</p>
				<p>
					<label>性别</label> <span class="field">${customerPO.sex}&nbsp;</span>
				</p>
				<p>
					<label>家庭住址</label> <span class="field">${customerPO.address}&nbsp;</span>
				</p>
				<p>
					<label>身份证/驾驶证图片</label> <span class="field"> <a
						<c:if test="${customerPO.idCardImg==''}">
						href="http://manager.eakay.cn/img/car.png"
					</c:if>
						<c:if test="${customerPO.idCardImg!=''}">
						href="http://manager.eakay.cn${customerPO.idCardImg}"
					</c:if>
						target="_blank"><img src="${ctx}${customerPO.idCardImg}"
							alt="汽车图片" onError="imgError(this)" alt="汽车图片" class="uploadImg" /></a>
					</span>
				</p>
			</form>
		</div>
		<!-- #add -->

		<div id="form" class="subcontent">

			<form id="form1" class="stdform stdform2" method="post">

				<c:if test="${sessionScope.user.systemName == 'stake'}">
					<p>
						<label>一卡通卡号 </label> <span class="field"><input
							type="text" name="cardNO" id="cardNO" class="longinput"
							style="width: 200px;" />&nbsp;&nbsp; </span>
					</p>
				</c:if>
				<p>
					<label>开户站点</label> <span class="field"> <select
						class="chzn-select" style="width: 200px;" name="siteId"
						id="siteId">
							<option value="">请选择</option>
					</select>
					</span>
				</p>
				<p>
					<c:if test="${sessionScope.user.systemName == 'eakay'}">
						<input type="hidden" value="${customerPO.idCard}" name="cardNO" />
					</c:if>
					<input type="hidden" name="customerId" value="${customerPO.id}" />
					<label>银行卡号</label> <span class="field"><input type="text"
						name="bankCardNO" class="longinput" style="width: 200px;" />&nbsp;&nbsp;</span>
				</p>
				<p>
					<label>银行类型</label> <span class="field"><input type="text"
						name="bankType" class="longinput" style="width: 200px;" />&nbsp;&nbsp;</span>
				</p>
				<!--<p>
					<label>手机号码</label> <span class="field">
						${customerPO.phone}&nbsp; </span>
				</p>
				<p>
					<label>短信验证码</label> <span class="field"> <input
						type="hidden" id="phone" value="${customerPO.phone}" /> <input
						type="text" name="verCode" id="verCode" class="longinput"
						style="width: 200px;" />&nbsp;&nbsp;<input type="button"
						value="获取验证码" " id="sendVerCode"></input>
					</span>
				</p>-->

			</form>
		</div>
		<!-- #add -->
	</div>

	<br clear="all" />
</body>
</html>

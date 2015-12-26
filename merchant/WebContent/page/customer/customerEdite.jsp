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
<script type="text/javascript" src="${ctx}/js/ajaxfileupload.js"></script>
<script type="text/javascript"
	src="${ctx}/js/page/customer/customerEdite.js"></script>
<!--<script type="text/javascript"
	src="${ctx}/js/page/customer/sendVerCode.js"></script>-->
</head>

<body>
	<form id="form1" class="stdform stdform2" method="post"
		action="${ctx}/customer/customer/saveEdite.htm?">
		<input type="hidden" name="id" value="${customerPO.id}" id="id" /> <input
			type="hidden" name="accountPO.id" id="accountId"
			value="${customerPO.accountPO.id}" />
		<p>
			<label>手机号码</label> <span class="field"> <input type="text"
				value="${customerPO.phone}" name="phone" id="phone"
				class="longinput" style="width: 200px;" />&nbsp;&nbsp;
			</span>
		</p>
		<!--<p id="verCodeInput">
			<label>短信验证码</label> <span class="field"> <input type="text"
				name="verCode" id="verCode" class="longinput" style="width: 200px;" />&nbsp;&nbsp;<input
				type="button" value="获取验证码" id="sendVerCode"></input>
			</span>
		</p>-->
		<p>
			<label>姓名</label> <span class="field"><input type="text"
				value="${customerPO.name}" name="name" class="longinput"
				style="width: 200px;" />&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>身份证/驾驶证号</label> <span class="field"><input type="text"
				value="${customerPO.idCard}" name="idCard" class="longinput"
				id="idCard" style="width: 200px;" />&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>性别</label> <span class="field"><select id="sex"
				data-placeholder="选择性别" name="sex" class="uniformselect"
				tabindex="2">
					<option value="男"
						<c:if test="${customerPO.sex=='男'}">selected="selected"</c:if>>男</option>
					<option value="女"
						<c:if test="${customerPO.sex=='女'}">selected="selected"</c:if>>女</option>
			</select>&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>家庭住址</label> <span class="field"><input type="text"
				value="${customerPO.address}" name="address" class="longinput"
				style="width: 300px;" />&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>工作单位</label> <span class="field"><input type="text"
				name="workUnit" value="${customerPO.workUnit}" class="longinput"
				style="width: 300px;" />&nbsp;&nbsp;</span>
		</p>
		<c:if test="${sessionScope.user.systemName == 'stake'}">
			<p>
				<label> 一卡通卡号 </label> <span class="field"><input type="text"
					value="${customerPO.accountPO.cardNO}" name="accountPO.cardNO"
					id="cardNO" class="longinput" style="width: 200px;" />&nbsp;&nbsp;</span>
			</p>
		</c:if>
		<p>

			<label>银行卡号</label> <span class="field"><input type="text"
				value="${customerPO.accountPO.bankCardNO}"
				name="accountPO.bankCardNO" class="longinput" style="width: 200px;" />&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>银行类型</label> <span class="field"><input type="text"
				value="${customerPO.accountPO.bankType}" name="accountPO.bankType"
				class="longinput" style="width: 200px;" />&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>身份证/驾驶证图片上传</label> <span class="field"> <input
				type="file" id="idCardImgFile" /> <span
				style="color: red; font-size: 14px;">注：只能上传jpg或png文件 <br />
					<br /> <img id="idCardImg" src="${ctx}${customerPO.idCardImg}"
					onError="imgError(this)" alt="身份证/驾驶证图片" class="uploadImg" /> <input
					type="hidden" value="${customerPO.idCardImg}" name="idCardImg"
					id="idCardImgHidden" /><input type="hidden"
					value="${customerPO.idCardImg}" name="driveCardImg"
					id="driveCardImgHidden" />
			</span>
			</span>
		</p>
	</form>
</body>
</html>

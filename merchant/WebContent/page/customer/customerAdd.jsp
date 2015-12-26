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
	src="${ctx}/js/page/customer/customerAdd.js"></script>
<!--  <script type="text/javascript"
	src="${ctx}/js/page/customer/sendVerCode.js"></script>-->
</head>

<body>


	<form id="form1" class="stdform stdform2" method="post" action="">
		<p>
			<label>手机号码</label> <span class="field"> <input
				type="text" name="phone" id="phone" class="longinput"
				style="width: 350px;" />&nbsp;&nbsp;
			</span>
		</p>
		<!--<p>
			<label>短信验证码</label> <span class="field"> <input
				type="text" name="verCode" id="verCode" class="longinput"
				style="width: 350px;" />&nbsp;&nbsp;<input type="button"
				value="获取验证码" " id="sendVerCode"></input>
			</span>
		</p>-->
		<p>
			<label>姓名</label> <span class="field"><input type="text"
				name="name" class="longinput" style="width: 350px;" />&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>身份证/驾驶证号</label> <span class="field"><input
				type="text" name="idCard" class="longinput" id="idCard"
				style="width: 350px;" />&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>性别</label> <span class="field"><select id="sex"
				data-placeholder="请选择性别" name="sex" class="uniformselect"
				tabindex="2">
					<option value="男">男</option>
					<option value="女">女</option>
			</select>&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>家庭住址</label> <span class="field"><input type="text"
				name="address" class="longinput" style="width: 350px;" />&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>工作单位</label> <span class="field"><input type="text"
				name="workUnit" class="longinput" style="width: 350px;" />&nbsp;&nbsp;</span>
		</p>
		<c:if test="${sessionScope.user.systemName == 'stake'}">
			<p>
				<label>一卡通卡号</label><span class="field"><input
					type="text" name="accountPO.cardNO" id="cardNO"
					class="longinput" style="width: 350px;" />&nbsp;&nbsp;</span>
			</p>
		</c:if>

		<p>
			<label>银行卡号</label> <span class="field"><input type="text"
				name="accountPO.bankCardNO" class="longinput"
				style="width: 350px;" />&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>银行类型</label> <span class="field"><input type="text"
				name="accountPO.bankType" class="longinput"
				style="width: 350px;" />&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>开户站点</label> <span class="field"> <select
				class="chzn-select" style="width: 200px;"
				name="accountPO.siteId" id="siteId">
					<option value="">请选择</option>
			</select>
			</span>
		</p>
		<p>
			<label>身份证/驾驶证图片上传</label> <span class="field"> <input
				type="file" id="idCardImgFile" /> <span
				style="color: red; font-size: 14px;">注：只能上传jpg或png文件 <br />
					<br /> <img id="idCardImg" src="${ctx}/img/car.png" alt="汽车图片"
					class="uploadImg" /> <input type="hidden" name="idCardImg"
					id="idCardImgHidden" /><input type="hidden"
					name="driveCardImg" id="driveCardImgHidden" />
			</span>
			</span>
		</p>
	</form>
</body>
</html>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript"
	src="${ctx}/js/plugins/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx}/js/ajaxfileupload.js"></script>
<script type="text/javascript"
	src="${ctx}/js/page/merchant/userEdite.js"></script>
</head>
<body>
	<form id="form1" class="stdform stdform2" method="post" action="">
		<p>
			<label>登录邮箱</label> <span class="field"> <input type="hidden"
				value="${merchantUserPO.id}" name="id" id="id" /> <input
				type="hidden" value="${merchantUserPO.siteId}" id="siteIdHid" /> <input
				type="text" name="email" id="email" class="longinput"
				value="${merchantUserPO.email}" style="width: 200px;" />&nbsp;&nbsp;
			</span>
		</p>
		<p>
			<label>姓名</label> <span class="field"><input type="text"
				name="userName" id="userName" class="longinput"
				value="${merchantUserPO.userName}" style="width: 200px;" />&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>手机号码</label> <span class="field"><input type="text"
				name="mobilePhone" id="mobilePhone" class="longinput"
				value="${merchantUserPO.mobilePhone}" style="width: 200px;" />&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>所属租赁点</label> <span class="field"><select
				data-placeholder="选择站点" name="siteId" id="siteId"
				class="chzn-select" style="width: 200px;" tabindex="2">
					<option value=""></option>
			</select>&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>角色</label> <span class="field"><select
				data-placeholder="" name="rights" id="rights" class="chzn-select"
				style="width: 200px;" tabindex="2">
					<option
						<c:if test="${'站点工作人员' == merchantUserPO.rights}">selected="true"</c:if>
						value="站点工作人员">站点工作人员</option>
					<option
						<c:if test="${'站点管理员'== merchantUserPO.rights}">selected="true"</c:if>
						value="站点管理员">站点管理员</option>
					<option
						<c:if test="${'商家管理员 '== merchantUserPO.rights}">selected="true"</c:if>
						value="商家管理员">商家管理员</option>
			</select>&nbsp;&nbsp;</span>
		</p>
	</form>
</body>
</html>

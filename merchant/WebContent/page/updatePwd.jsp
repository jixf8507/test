<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="common/taglibs.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
</head>
<script type="text/javascript"
	src="${ctx}/js/plugins/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx}/js/page/updatePwd.js"></script>
<body>
	<form id="form1" class="stdform stdform2" method="post">
		<p>
			<label>原密码</label> <span class="field"><input type="password"
				name="yPassword" id="yPassword" style="width: 200px;" /></span>
		</p>
		<p>
			<label>新密码</label> <span class="field"><input type="password"
				name="password" id="password" class="longinput"
				style="width: 200px;" />&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>确认密码</label> <span class="field"><input type="password"
				name="conPassword" id="conPassword" class="longinput"
				style="width: 200px;" />&nbsp;&nbsp;</span>
		</p>
	</form>
	</script>
</html>

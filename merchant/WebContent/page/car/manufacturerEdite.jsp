<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>

<script type="text/javascript"
	src="${ctx}/js/plugins/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="${ctx}/js/page/car/manufacturerEdite.js"></script>
</head>

<body>

	<form id="form1" class="stdform stdform2" method="post"
		action="${ctx}/car/manufacturer/saveEdite.htm?">
		<p>
			<label>供应商名称</label> <span class="field"><input type="text"
				name="manufacturerName" id="manufacturerName"
				value="${manufacturerPO.manufacturerName}" class="longinput" /> <input
				type="hidden" name="id" value="${manufacturerPO.id}" /> </span>
		</p>

		<p>
			<label>供应商全称</label> <span class="field"><input
				value="${manufacturerPO.fullName}" type="text" name="fullName"
				id="fullName" class="longinput" /></span>
		</p>

		<p>
			<label>地址</label> <span class="field"><input type="text"
				value="${manufacturerPO.address}" name="address" id="address"
				class="longinput" /></span>
		</p>
		<p>
			<label>联系人</label> <span class="field"><input type="text"
				value="${manufacturerPO.linkman}" name="linkman" id="linkman"
				class="longinput" /></span>
		</p>
		<p>
			<label>联系电话</label> <span class="field"><input type="text"
				value="${manufacturerPO.linkphone}" name="linkphone" id="linkphone"
				class="longinput" /></span>
		</p>
		<p>
			<label>电子邮箱</label> <span class="field"><input type="text"
				value="${manufacturerPO.email}" name="email" id="email"
				class="longinput" /></span>
		</p>

	</form>

</body>
</html>

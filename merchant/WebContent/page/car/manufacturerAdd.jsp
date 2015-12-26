<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript"
	src="${ctx}/js/plugins/chosen.jquery.min.js"></script>
<script type="text/javascript"
	src="${ctx}/js/plugins/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="${ctx}/js/page/car/manufacturerAdd.js"></script>
</head>

<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />

		<div class="centercontent">
			<div class="pageheader">
				<h1 class="pagetitle">新增供应商</h1>
				<span class="pagedesc"></span>
			</div>
			<!--pageheader-->

			<div id="contentwrapper" class="contentwrapper">
				<div id="updates" class="subcontent">
					<form id="form1" class="stdform stdform2" method="post"
						action="${ctx}/car/manufacturer/saveAdd.htm?">
						<p>
							<label>供应商名称</label> <span class="field"><input
								type="text" name="manufacturerName" id="manufacturerName"
								class="longinput" /></span>
						</p>

						<p>
							<label>供应商全称</label> <span class="field"><input
								type="text" name="fullName" id="fullName" class="longinput" /></span>
						</p>

						<p>
							<label>地址</label> <span class="field"><input type="text"
								name="address" id="address" class="longinput" /></span>
						</p>
						<p>
							<label>联系人</label> <span class="field"><input type="text"
								name="linkman" id="linkman" class="longinput" /></span>
						</p>
						<p>
							<label>联系电话</label> <span class="field"><input type="text"
								name="linkphone" id="linkphone" class="longinput" /></span>
						</p>
						<p>
							<label>电子邮箱</label> <span class="field"><input type="text"
								name="email" id="email" class="longinput" /></span>
						</p>

						<p class="stdformbutton">
							<button class="submit radius2">保存</button>
							<input type="reset" class="reset radius2" value="重置" />
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

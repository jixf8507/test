<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript"
	src="${ctx}/js/page/customer/customerPotential.js"></script>
</head>

<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />

		<div class="centercontent">
			<div class="pageheader">
				<h1 class="pagetitle">客户审核办卡处理</h1>
				<span class="pagedesc"></span>
			</div>
			<div id="contentwrapper" class="contentwrapper">
					<div class="overviewhead">
						身份证: &nbsp;<input type="text" id="idCard" /> &nbsp; 
						手机号码: &nbsp; <input type="text" id="phone" /> &nbsp;客户姓名: &nbsp;
						<input type="text" id="name" /> &nbsp; &nbsp;
						<button class="publishbutton radius3" id="queryBtn">查询</button>
					</div>
				<br clear="all" />
				<div id="normal" class="subcontent">
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="normalCustomersTable">

						<colgroup>
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
						</colgroup>
						<thead>
							<tr>
								<th class="head0">身份证/驾驶证</th>
								<th class="head1">手机号码</th>
								<th class="head0">姓名</th>
								<th class="head1">性别</th>
								<th class="head0">家庭住址</th>
								<th class="head1">审核办卡</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">身份证/驾驶证</th>
								<th class="head1">手机号码</th>
								<th class="head0">姓名</th>
								<th class="head1">性别</th>
								<th class="head0">家庭住址</th>
								<th class="head1">审核办卡</th>
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>
					<br /> <br />
				</div>
				<!-- #use -->
			</div>
			<!--contentwrapper-->
		</div>
		<!-- centercontent -->
	</div>
	<!--bodywrapper-->

</body>
</html>

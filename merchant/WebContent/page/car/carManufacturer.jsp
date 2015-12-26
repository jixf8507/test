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
	src="${ctx}/js/page/car/carManufacturer.js"></script>
</head>

<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />

		<div class="centercontent">

			<div class="pageheader">
				<h1 class="pagetitle">供应商管理</h1>

			</div>
			<!--pageheader-->

			<div id="contentwrapper" class="contentwrapper">

				<div id="list" class="subcontent">
					<div class="overviewhead">
						<!--floatright-->
						供应商名称: &nbsp;<input type="text" id="manufacturerName" /> &nbsp;
						&nbsp;
						<button class="publishbutton radius3" id="queryBtn">查询</button>
						<button class="publishbutton radius3" id="excelBtn">导出EXCEL</button>
					</div>
					<!--overviewhead-->
					<br clear="all" />
					<!--contenttitle-->
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="manufacturerTable">
						<colgroup>
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
						</colgroup>
						<thead>
							<tr>
								<th class="head0">供应商名称</th>
								<th class="head1">供应商全称</th>
								<th class="head0">地址</th>
								<th class="head1">联系人</th>
								<th class="head0">联系电话</th>
								<th class="head1">电子邮箱</th>
								<th class="head0">操作</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">供应商名称</th>
								<th class="head1">供应商全称</th>
								<th class="head0">地址</th>
								<th class="head1">联系人</th>
								<th class="head0">联系电话</th>
								<th class="head1">电子邮箱</th>
								<th class="head0">操作</th>
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>
					<br /> <br />
				</div>
			</div>
			<!--contentwrapper-->
			<br clear="all" />
		</div>
		<!-- centercontent -->

	</div>
	<!--bodywrapper-->

</body>
</html>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript" src="${ctx}/js/page/asset/assetCategoryManage.js"></script>
</head>

<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />

		<div class="centercontent">

			<div class="pageheader">
				<h1 class="pagetitle">资产种类列表</h1>
			</div>
			<div id="contentwrapper" class="contentwrapper">

				<div id="normal" class="subcontent">
					<div class="overviewhead">
						种类: &nbsp;<input type="text" id="name" style="width: 200px;" /> &nbsp; &nbsp;&nbsp; &nbsp;
						<button class="publishbutton radius3" id="queryBtn">查询</button> &nbsp; &nbsp;
						<button class="publishbutton radius3" id="excelBtn">导出EXCEL</button>
					</div>
					<br clear="all" />
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="assetTable">
						<colgroup>
							<col class="con1" />
							<col class="con0" />
						</colgroup>
						<thead>
							<tr>
								<th class="head1">资产种类</th>
								<th class="head0">操作</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head1">资产种类</th>
								<th class="head0">操作</th>
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>
					<br /> <br />
				</div>
			</div>
		</div>
	</div>
</body>
</html>

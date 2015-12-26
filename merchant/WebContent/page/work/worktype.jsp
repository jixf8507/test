<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript"
	src="${ctx}/js/page/work/workType.js"></script>
</head>

<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />

		<div class="centercontent">
			<div class="pageheader">
				<h1 class="pagetitle">工单类型管理</h1>
				<span class="pagedesc"></span>
			</div>
			<div id="contentwrapper" class="contentwrapper">

				<div id="normal" class="subcontent">
					<div class="overviewhead">
						类型名称: &nbsp;<input
							type="text" id="typeName" /> &nbsp; 负责人: &nbsp;<input
							type="text" id="userName" /> &nbsp;负责人手机: &nbsp;<input
							type="text" id="mobilePhone" /> &nbsp; &nbsp;<br></br>
							日期: &nbsp;<input type="text" id="beginTime" /> &nbsp; &nbsp; 到:
						&nbsp;<input type="text" id="endTime" /> &nbsp;&nbsp;
						<button class="publishbutton radius3" id="QueryBtn">查询</button>
						<button class="publishbutton radius3" id="ExcelBtn">导出EXCEL</button>
						
					</div>
					<!--overviewhead-->

					<br clear="all" />
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="workTypeTable">
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
								<th class="head0">类型名称</th>
								<th class="head1">类型描述</th>
								<th class="head0">负责人</th>
								<th class="head1">负责人手机</th>
								<th class="head0">创建时间</th>
								<th class="head1">更多操作</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">类型名称</th>
								<th class="head1">类型描述</th>
								<th class="head0">负责人</th>
								<th class="head1">负责人手机</th>
								<th class="head0">创建时间</th>
								<th class="head1">更多操作</th>
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

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript"
	src="${ctx}/js/plugins/chosen.jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/page/car/carType.js"></script>
</head>

<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />

		<div class="centercontent">

			<div class="pageheader">
				<h1 class="pagetitle">车辆型号管理</h1>
			</div>
			<!--pageheader-->

			<div id="contentwrapper" class="contentwrapper">

				<div id="list" class="subcontent">
					<div class="overviewhead">
						<div class="overviewselect">
							<select class="chzn-select" style="width: 200px;" tabindex="2"
								id="carManufacturerId">
								<option value="">请选择供应商</option>
							</select>
						</div>
						<!--floatright-->
						车辆型号: &nbsp;<input type="text" id="typeName" /> &nbsp; &nbsp;
						<button class="publishbutton radius3" id="queryBtn">查询</button>&nbsp; 
						<button class="publishbutton radius3" id="excelBtn">导出EXCEL</button>
					</div>
					<!--overviewhead-->
					<br clear="all" />
					<!--contenttitle-->
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="typesTable">
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
								<th class="head0">车辆型号</th>
								<th class="head1">供应商全称</th>
								<th class="head0">座位数</th>
								<th class="head1">颜色</th>
								<th class="head0">类型</th>
								<th class="head1">操作</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">车类型名称</th>
								<th class="head1">供应商全称</th>
								<th class="head0">座位数</th>
								<th class="head1">颜色</th>
								<th class="head0">类型</th>
								<th class="head1">操作</th>
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>

					<br /> <br />

				</div>
				<!-- #updates -->
			</div>
			<!--contentwrapper-->
			<br clear="all" />
		</div>
		<!-- centercontent -->
	</div>
	<!--bodywrapper-->

</body>
</html>

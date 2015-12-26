<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript"
	src="${ctx}/js/page/customer/customerCars.js"></script>
</head>

<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />

		<div class="centercontent">

			<div class="pageheader">
				<h1 class="pagetitle">客户车辆信息列表</h1>
				<span class="pagedesc"></span>

			</div>
			<div id="contentwrapper" class="contentwrapper">

				<div id="normal" class="subcontent">
					<div class="overviewhead">
						车牌: &nbsp;<input type="text" id="carNumber" /> &nbsp;
						识别号: &nbsp;<input type="text" id="vin" /> &nbsp; 
						身份证: &nbsp;<input type="text" id="normalIdCard" /> &nbsp;
						手机号码: &nbsp;<input type="text" id="normalPhone" /> &nbsp;
						客户姓名: &nbsp;<input type="text" id="normalName" /> &nbsp; &nbsp;
						<button class="publishbutton radius3" id="normalQueryBtn">查询</button>&nbsp;
						<button class="publishbutton radius3" id="normalExcelBtn">导出EXCEL</button>
					</div>
					<!--overviewhead-->

					<br clear="all" />
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="normalCustomersTable">
						<colgroup>
							<col class="con0" width="10%" />
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
						</colgroup>
						<thead>
							<tr>
								<th class="head0">车牌号码</th>
								<th class="head1">车辆识别号</th>
								<th class="head0">身份证/驾驶证</th>
								<th class="head1">手机号码</th>
								<th class="head0">姓名</th>
								<th class="head1">设备编号</th>
								<th class="head0">操作</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">车牌号码</th>
								<th class="head1">车辆识别号</th>
								<th class="head0">身份证/驾驶证</th>
								<th class="head1">手机号码</th>
								<th class="head0">姓名</th>
								<th class="head1">设备编号</th>
								<th class="head0">操作</th>
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

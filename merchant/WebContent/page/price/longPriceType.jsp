<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript" src="${ctx}/js/page/price/longPriceType.js"></script>
</head>

<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />

		<div class="centercontent">
			<div class="pageheader">
				<h1 class="pagetitle">长期租赁套餐</h1>
				<span class="pagedesc"></span>
				<ul class="hornav">
					<li class="current"><a href="#updates">当前可用套餐</a></li>
					<li><a href="#activities" id="overDiv">已结束租赁套餐</a></li>
				</ul>
			</div>
			<!--pageheader-->

			<div id="contentwrapper" class="contentwrapper">
				<div id="updates" class="subcontent">
					<div class="overviewhead">
						套餐名称: &nbsp;<input type="text" id="preTypeName" /> &nbsp; &nbsp;
						<button class="publishbutton radius3" id="preQueryBtn">查询</button>
						<button class="publishbutton radius3" id="preExcelBtn">导出EXCEL</button>
					</div>
					<!--overviewhead-->
					<br clear="all" />
					<!--contenttitle-->
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="longPriceTypesTable">
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
								<th class="head0">业务套餐</th>
								<th class="head1">月收费</th>
								<th class="head0">日收费</th>
								<th class="head1">小时收费</th>
								<th class="head0">状态</th>
								<th class="head1">管理</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">业务套餐</th>
								<th class="head1">月收费</th>
								<th class="head0">日收费</th>
								<th class="head1">小时收费</th>
								<th class="head0">状态</th>
								<th class="head1">管理</th>
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>

					<br /> <br />

				</div>
				<!-- #updates -->

				<div id="activities" class="subcontent" style="display: none;">
					<div class="overviewhead">
						套餐名称: &nbsp;<input type="text" id="overTypeName" /> &nbsp; &nbsp;
						<button class="publishbutton radius3" id="overQueryBtn">查询</button>
						<button class="publishbutton radius3" id="overExcelBtn">导出EXCEL</button>
					</div>
					<!--overviewhead-->
					<br clear="all" />
					<!--contenttitle-->
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="overLongPriceTable">
						<colgroup>
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
						</colgroup>
						<thead>
							<tr>
								<th class="head0">业务套餐</th>
								<th class="head1">月收费</th>
								<th class="head0">日收费</th>
								<th class="head1">小时收费</th>
								<th class="head0">状态</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">业务套餐</th>
								<th class="head1">月收费</th>
								<th class="head0">日收费</th>
								<th class="head1">小时收费</th>
								<th class="head0">状态</th>
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>
				</div>
				<!-- #activities -->
			</div>
			<!--contentwrapper-->
			<br clear="all" />
		</div>
		<!-- centercontent -->

	</div>
	<!--bodywrapper-->

</body>
</html>

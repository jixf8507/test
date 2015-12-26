<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript"
	src="${ctx}/js/plugins/chosen.jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/page/order/order.js"></script>
</head>

<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />

		<div class="centercontent">

			<div class="pageheader">
				<h1 class="pagetitle">当前订单</h1>
				<span class="pagedesc">当前车辆租赁信息</span>
				<ul class="hornav">
					<li class="current"><a href="#prep" id="ptitleId">待取车...</a></li>
					<li><a href="#get" id="getitleId">已取车...</a></li>
					<li><a href="#return" id="returntitleId">已还车...</a></li>
					<li><a href="#cancel" id="canceltitleId">已取消...</a></li>
				</ul>
			</div>

			<div id="contentwrapper" class="contentwrapper">

				<div id="prep" class="subcontent">

					<div class="overviewhead">
						<div class="overviewselect">
							<select id="prepSiteId" name="select" class="chzn-select"
								style="width: 200px;" tabindex="2">
								<option value="">请选择租赁点</option>
							</select>
						</div>
						<!--floatright-->
						车牌号码: &nbsp;<input type="text" id="prepCarNumber" /> &nbsp;
						&nbsp; 姓名: &nbsp;<input type="text" id="prepUserName" /> &nbsp;
						&nbsp; 手机号码: &nbsp;<input type="text" id="prepUserPhone" />
						&nbsp; &nbsp;
						<button class="publishbutton radius3" id="prepQueryBtn">查询</button>
					</div>
					<br clear="all" />
					<!--contenttitle-->
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="prepOrdersTable">
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
								<th class="head0">订单号</th>
								<th class="head1">车牌号码</th>
								<th class="head0">套餐名称</th>
								<th class="head1">客户姓名</th>
								<th class="head0">手机号码</th>
								<th class="head1">取车时间</th>
								<th class="head0">取车租赁点</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">订单号</th>
								<th class="head1">车牌号码</th>
								<th class="head0">套餐名称</th>
								<th class="head1">客户姓名</th>
								<th class="head0">手机号码</th>
								<th class="head1">取车时间</th>
								<th class="head0">取车租赁点</th>
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>

					<br /> <br />

				</div>
				<!-- #use -->

				<div id="get" class="subcontent" style="display: none;">

					<div class="overviewhead">
						<div class="overviewselect">
							<select id="getSiteId" name="select" class="chzn-select"
								style="width: 200px;" tabindex="2">
								<option value="">请选择租赁点</option>
							</select>
						</div>
						<!--floatright-->
						车牌号码: &nbsp;<input type="text" id="getCarNumber" /> &nbsp; &nbsp;
						姓名: &nbsp;<input type="text" id="getUserName" /> &nbsp; &nbsp;
						手机号码: &nbsp;<input type="text" id="getUserPhone" /> &nbsp; &nbsp;
						<button class="publishbutton radius3" id="getQueryBtn">查询</button>
					</div>
						<br clear="all" />
					<!--contenttitle-->
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="getOrdersTable">
						<colgroup>
							<col class="con0" width="10%" />
							<col class="con1" />
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
								<th class="head0">订单号</th>
								<th class="head1">车牌号码</th>
								<th class="head0">套餐名称</th>
								<th class="head1">客户姓名</th>
								<th class="head0">手机号码</th>
								<th class="head1">取车时间</th>
								<th class="head0">取车租赁点</th>
								<th class="head1">订单状态</th>
								<th class="head0">取车状态</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">订单号</th>
								<th class="head1">车牌号码</th>
								<th class="head0">套餐名称</th>
								<th class="head1">客户姓名</th>
								<th class="head0">手机号码</th>
								<th class="head1">取车时间</th>
								<th class="head0">取车租赁点</th>
								<th class="head1">订单状态</th>
								<th class="head0">取车状态</th>
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>

					<br /> <br />

				</div>
				<!-- #wait -->

				<div id="return" class="subcontent" style="display: none;">

					<div class="overviewhead">
						<div class="overviewselect">
							<select id="returnSiteId" name="select" class="chzn-select"
								style="width: 200px;" tabindex="2">
								<option value="">请选择租赁点</option>
							</select>
						</div>
						<!--floatright-->
						车牌号码: &nbsp;<input type="text" id="returnCarNumber" /> &nbsp;
						&nbsp; 姓名: &nbsp;<input type="text" id="returnUserName" /> &nbsp;
						&nbsp; 手机号码: &nbsp;<input type="text" id="returnUserPhone" />
						&nbsp; &nbsp;
						<button class="publishbutton radius3" id="returnQueryBtn">查询</button>
					</div>
					<br clear="all" />

					<!--contenttitle-->
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="returnOrdersTable">
						<colgroup>
							<col class="con0" width="10%" />
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
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
								<th class="head0">订单号</th>
								<th class="head1">车牌号码</th>
								<th class="head0">套餐名称</th>
								<th class="head1">客户姓名</th>
								<th class="head0">手机号码</th>
								<th class="head1">取车时间</th>
								<th class="head0">取车租赁点</th>
								<th class="head1">还车时间</th>
								<th class="head0">还车租赁点</th>
								<th class="head1">订单状态</th>
								<th class="head0">取车状态</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">订单号</th>
								<th class="head1">车牌号码</th>
								<th class="head0">套餐名称</th>
								<th class="head1">客户姓名</th>
								<th class="head0">手机号码</th>
								<th class="head1">取车时间</th>
								<th class="head0">取车租赁点</th>
								<th class="head1">还车时间</th>
								<th class="head0">还车租赁点</th>
								<th class="head1">订单状态</th>
								<th class="head0">取车状态</th>
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>

					<br /> <br />

				</div>
				<!-- #fault -->

				<div id="cancel" class="subcontent" style="display: none;">

					<div class="overviewhead">
						<div class="overviewselect">
							<select id="cancelSiteId" name="select" class="chzn-select"
								style="width: 200px;" tabindex="2">
								<option value="">请选择租赁点</option>
							</select>
						</div>
						<!--floatright-->
						车牌号码: &nbsp;<input type="text" id="cancelCarNumber" /> &nbsp;
						&nbsp; 姓名: &nbsp;<input type="text" id="cancelUserName" /> &nbsp;
						&nbsp; 手机号码: &nbsp;<input type="text" id="cancelUserPhone" />
						&nbsp; &nbsp;
						<button class="publishbutton radius3" id="cancelQueryBtn">查询</button>
					</div>
						<br clear="all" />
					<!--contenttitle-->
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="cancelOrdersTable">
						<colgroup>
							<col class="con0" width="10%" />
							<col class="con1" />
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
								<th class="head0">订单号</th>
								<th class="head1">车牌号码</th>
								<th class="head0">套餐名称</th>
								<th class="head1">客户姓名</th>
								<th class="head0">手机号码</th>
								<th class="head1">取车时间</th>
								<th class="head0">还车时间</th>
								<th class="head1">订单状态</th>
								<th class="head0">取车状态</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">订单号</th>
								<th class="head1">车牌号码</th>
								<th class="head0">套餐名称</th>
								<th class="head1">客户姓名</th>
								<th class="head0">手机号码</th>
								<th class="head1">取车时间</th>
								<th class="head0">还车时间</th>
								<th class="head1">订单状态</th>
								<th class="head0">取车状态</th>
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>

					<br /> <br />
				</div>
				<!-- #charging -->
			</div>
			<!--contentwrapper-->
		</div>
		<!-- centercontent -->

	</div>
	<!--bodywrapper-->

</body>
</html>

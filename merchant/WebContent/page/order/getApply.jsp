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
<script type="text/javascript" src="${ctx}/js/page/order/getApply.js"></script>
</head>

<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />

		<div class="centercontent">
			<div class="pageheader">
				<h1 class="pagetitle">取车</h1>
				<span class="pagedesc" style="color: red">提示：请核对取车用户的身份证号、姓名和保证金信息。</span>
				<ul class="hornav">
					<li class="current"><a href="#order">预约取车</a></li>
					<li><a href="#wait" id="waitDiv">人工取车</a></li>
				</ul>
			</div>
			<!--pageheader-->
			<div id="contentwrapper" class="contentwrapper">
				<div id="order" class="subcontent">
					<div class="overviewhead">
						<div class="overviewselect">
							<select id="prepCurSiteId" name="select" class="chzn-select"
								style="width: 200px;" tabindex="2">
								<option value="">请选择租赁点</option>								
							</select>
						</div>
						<!--floatright-->
						车牌号码: &nbsp;<input type="text" id="prepCarNumber" /> &nbsp;
						&nbsp; 姓名: &nbsp;<input type="text" id="prepName" /> &nbsp;
						&nbsp; 手机号码: &nbsp;<input type="text" id="prepUserPhone" />
						&nbsp; &nbsp;
						<button class="publishbutton radius3" id="prepQueryBtn">查询</button>
					</div>
					<!--overviewhead-->
					<br clear="all" />
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="prepOrdersTable">
						<colgroup>
							<col class="con0" />
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
							<col class="con1" />
							<col class="con0" />
						</colgroup>
						<thead>
							<tr>
								<th class="head0">汽车图片</th>
								<th class="head1">订单号</th>
								<th class="head0">车牌号码</th>
								<th class="head1">套餐名称</th>
								<th class="head0">客户</th>
								<th class="head1">手机</th>
								<th class="head0">取车时间</th>
								<th class="head1">取车租赁点</th>
								<th class="head0">码表公里数</th>
								<th class="head1">续航里程</th>
								<th class="head0">车载设备</th>
								<th class="head1">设备状态</th>
								<th class="head0">申请取车</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">汽车图片</th>
								<th class="head1">订单号</th>
								<th class="head0">车牌号码</th>
								<th class="head1">套餐名称</th>
								<th class="head0">客户</th>
								<th class="head1">手机</th>
								<th class="head0">取车时间</th>
								<th class="head1">取车租赁点</th>
								<th class="head0">码表公里数</th>
								<th class="head1">续航里程</th>
								<th class="head0">车载设备</th>
								<th class="head1">设备状态</th>
								<th class="head0">申请取车</th>
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>
				</div>
				<!-- #activities -->

				<div id="wait" class="subcontent" style="display: none;">
					<div class="overviewhead">
						<div class="overviewselect">
							<select id="waitCurSiteId" name="select" class="chzn-select"
								style="width: 200px;" tabindex="2">
								<option value="">请选择租赁点</option>
							</select>
						</div>
						<!--floatright-->
						车牌号码: &nbsp;<input type="text" id="waitCarNumber" /> &nbsp;
						&nbsp;
						<button class="publishbutton radius3" id="waitQueryBtn">查询</button>
					</div>
					<!--overviewhead-->
					<br clear="all" />
					<!--contenttitle-->
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="waitCarsTable">
						<colgroup>
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
							<col class="con0" style="width: 10%;"/>
						</colgroup>
						<thead>
							<tr>
								<th class="head0">汽车图片</th>
								<th class="head1">车牌号码</th>
								<th class="head0">商家</th>
								<th class="head1">车型</th>
								<th class="head0">当前租赁点</th>
								<th class="head1">车辆状态</th>
								<th class="head0">码表公里数</th>
								<th class="head1">续航里程</th>
								<th class="head0">车载设备</th>
								<th class="head1">设备状态</th>
								<th class="head0">申请取车</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">汽车图片</th>
								<th class="head1">车牌号码</th>
								<th class="head0">商家</th>
								<th class="head1">车型</th>
								<th class="head0">当前租赁点</th>
								<th class="head1">车辆状态</th>
								<th class="head0">码表公里数</th>
								<th class="head1">续航里程</th>
								<th class="head0">车载设备</th>
								<th class="head1">设备状态</th>
								<th class="head0">申请取车</th>
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>

					<br /> <br />

				</div>
				<!-- #wait -->
			</div>
			<!--contentwrapper-->
			<br clear="all" />
		</div>
		<!-- centercontent -->

	</div>
	<!--bodywrapper-->

</body>
</html>

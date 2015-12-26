<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript" src="${ctx}/js/page/car/fault.js"></script>
</head>

<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />

		<div class="centercontent">
			<div class="pageheader">
				<h1 class="pagetitle">车辆状态</h1>
				<span class="pagedesc">车辆当前状态信息</span>
				<ul class="hornav">
					<li class="current"><a href="#use">使用中...</a></li>
					<li><a href="#wait" id="waitDiv">空闲中...</a></li>
					<li><a href="#fault" id="faultDiv">故障中...</a></li>
					<li><a href="#charging" id="chargeDiv">充电中...</a></li>
				</ul>
			</div>
			<div id="contentwrapper" class="contentwrapper">

				<div id="use" class="subcontent">
					<div class="overviewhead">
						<div class="overviewselect">
							<select id="useCurSiteId" name="select" class="chzn-select"
								style="width: 200px;" tabindex="2">
								<option value="">请选择租赁点</option>
							</select>
						</div>
						<!--floatright-->
						车牌号码: &nbsp;<input type="text" id="useCarNumber" /> &nbsp; &nbsp;
						<button class="publishbutton radius3" id="useQueryBtn">查询</button>
					</div>
					<!--overviewhead-->
					<br clear="all" />
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="useCarsTable">
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
								<th class="head0">汽车图片</th>
								<th class="head1">车牌号码</th>
								<th class="head0">商家</th>
								<th class="head1">车型</th>
								<th class="head0">当前租赁点</th>
								<th class="head1">车辆状态</th>
								<th class="head0">操作</th>
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
								<th class="head0">操作</th>
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>

					<br /> <br />

				</div>
				<!-- #use -->

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
								<th class="head0">汽车图片</th>
								<th class="head1">车牌号码</th>
								<th class="head0">商家</th>
								<th class="head1">车型</th>
								<th class="head0">当前租赁点</th>
								<th class="head1">车辆状态</th>
								<th class="head0">操作</th>
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
								<th class="head0">操作</th>
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>

					<br /> <br />

				</div>
				<!-- #wait -->

				<div id="fault" class="subcontent" style="display: none;">
					<div class="overviewhead">
						<div class="overviewselect">
							<select id="faultCurSiteId" name="select" class="chzn-select"
								style="width: 200px;" tabindex="2">
								<option value="">请选择租赁点</option>
							</select>
						</div>
						<!--floatright-->
						车牌号码: &nbsp;<input type="text" id="faultCarNumber" /> &nbsp;
						&nbsp;
						<button class="publishbutton radius3" id="faultQueryBtn">查询</button>
					</div>
					<!--overviewhead-->
					<br clear="all" />
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="faultCarsTable">
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
								<th class="head0">汽车图片</th>
								<th class="head1">车牌号码</th>
								<th class="head0">商家</th>
								<th class="head1">车型</th>
								<th class="head0">当前租赁点</th>
								<th class="head1">车辆状态</th>
								<th class="head0">操作</th>
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
								<th class="head0">操作</th>
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>
				</div>
				<!-- #fault -->

				<div id="charging" class="subcontent" style="display: none;">
					<div class="overviewhead">
						<div class="overviewselect">
							<select id="chargeCurSiteId" name="select" class="chzn-select"
								style="width: 200px;" tabindex="2">
								<option value="">请选择租赁点</option>
							</select>
						</div>
						<!--floatright-->
						车牌号码: &nbsp;<input type="text" id="chargeCarNumber" /> &nbsp;
						&nbsp;
						<button class="publishbutton radius3" id="chargeQueryBtn">查询</button>
					</div>
					<!--overviewhead-->
					<br clear="all" />
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="chargeCarsTable">
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
								<th class="head0">汽车图片</th>
								<th class="head1">车牌号码</th>
								<th class="head0">商家</th>
								<th class="head1">车型</th>
								<th class="head0">当前租赁点</th>
								<th class="head1">车辆状态</th>
								<th class="head0">操作</th>
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
								<th class="head0">操作</th>
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>
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

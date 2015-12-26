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
<script type="text/javascript" src="${ctx}/js/page/order/orderResuce.js"></script>
</head>

<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />

		<div class="centercontent">
			<div class="pageheader">
				<h1 class="pagetitle">救援信息</h1>
				<span class="pagedesc">租赁救援信息查询</span>
				<ul class="hornav">
					<li class="current"><a href="#new">待处理...</a></li>
					<li><a href="#history" id="hisDiv">已处理...</a></li>
				</ul>
			</div>
			<div id="contentwrapper" class="contentwrapper">
				<div id="new" class="subcontent">
					<div class="overviewhead">
						<!--floatright-->
						车牌号码: &nbsp;<input type="text" id="newCarNumber" /> &nbsp; &nbsp;
						姓名: &nbsp;<input type="text" id="newUserName" /> &nbsp; &nbsp;
						手机号码: &nbsp;<input type="text" id="newUserPhone" /> &nbsp; &nbsp;
						<button class="publishbutton radius3" id="newQueryBtn">查询</button>
					</div>
					<br clear="all" />
					<!--contenttitle-->
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="newResucesTable">
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
								<th class="head0">手机号码</th>
								<th class="head1">客户姓名</th>
								<th class="head0">救援请求</th>
								<th class="head1">救援地点</th>
								<th class="head0">车牌号码</th>
								<th class="head1">请求时间</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">手机号码</th>
								<th class="head1">客户姓名</th>
								<th class="head0">救援请求</th>
								<th class="head1">救援地点</th>
								<th class="head0">车牌号码</th>
								<th class="head1">请求时间</th>
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>
					<br /> <br />
				</div>
				<!-- #new -->

				<div id="history" class="subcontent" style="display: none;">
					<div class="overviewhead">
						<!--floatright-->
						车牌号码: &nbsp;<input type="text" id="hisCarNumber" /> &nbsp; &nbsp;
						姓名: &nbsp;<input type="text" id="hisUserName" /> &nbsp; &nbsp;
						手机号码: &nbsp;<input type="text" id="shiUserPhone" /> &nbsp; &nbsp;
						<button class="publishbutton radius3" id="hisQueryBtn">查询</button>
					</div>
					<br clear="all" />
					<!--contenttitle-->
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="historyResucesTable">
						<colgroup>
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
						</colgroup>
						<thead>
							<tr>
								<th class="head0">手机号码</th>
								<th class="head1">客户姓名</th>
								<th class="head0">救援请求</th>
								<th class="head1">救援地点</th>
								<th class="head0">车牌号码</th>
								<th class="head1">处理人</th>
								<th class="head0">救援费用</th>
								<th class="head1">支付状态</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">手机号码</th>
								<th class="head1">客户姓名</th>
								<th class="head0">救援请求</th>
								<th class="head1">救援地点</th>
								<th class="head0">车牌号码</th>
								<th class="head1">处理人</th>
								<th class="head0">救援费用</th>
								<th class="head1">支付状态</th>
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>
					<br /> <br />
				</div>
				<!-- #history -->
			</div>
			<!--contentwrapper-->
		</div>
		<!-- centercontent -->

	</div>
	<!--bodywrapper-->

</body>
</html>

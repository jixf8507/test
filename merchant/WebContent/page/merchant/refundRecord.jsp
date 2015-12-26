<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript"
	src="${ctx}/js/page/merchant/refundRecord.js"></script>
</head>
<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />
		<div class="centercontent">

			<div class="pageheader">
				<h1 class="pagetitle">退款记录明细</h1>
				<span class="pagedesc"></span>
				<ul class="hornav">
					<li class="current"><a href="#normal" id="normalDiv">普通客户</a></li>
					<li><a href="#unit" id="unitDiv">企业长租</a></li>
				</ul>
			</div>

			<div id="contentwrapper" class="contentwrapper">
				<div id="normal" class="subcontent">
					<div class="overviewhead">
						<div class="overviewselect">
							<select id="type" name="select" class="chzn-select"
								style="width: 200px;">
								<option value="">请选择退款类型</option>
								<option value="余额退款">余额退款</option>
								<option value="保证金退款">保证金退款</option>
							</select><br /> <br /> <select id="siteId" name="select"
								class="chzn-select" style="width: 200px;" tabindex="2">
								<option value="">请选择租赁点</option>
							</select>
						</div>
						身份证: &nbsp;<input type="text" id="idCard" style="width: 200px;" />
						&nbsp; &nbsp; 手机： &nbsp;<input type="text" id="phone"
							style="width: 200px;" /> &nbsp; &nbsp; 姓名: &nbsp;<input
							type="text" id="name" style="width: 200px;" /> &nbsp; &nbsp; <br />
						<br /> 日期: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text"
							id="beginTime" /> &nbsp;&nbsp; &nbsp;&nbsp; 到:
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="endTime" />&nbsp;&nbsp;&nbsp;
						<button class="publishbutton radius3" id="queryBtn">查询</button>
						&nbsp;&nbsp;
						<button class="publishbutton radius3" id="excelBtn">导出EXCEL</button>
						&nbsp;
					</div>
					<br clear="all" />
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="normalCustomersTable">
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
						</colgroup>
						<thead>
							<tr>
								<th class="head0">身份证号</th>
								<th class="head1">手机号码</th>
								<th class="head0">客户姓名</th>
								<th class="head1">退款金额</th>
								<th class="head0">退款类型</th>
								<th class="head1">退款时间</th>
								<th class="head0">经办人</th>
								<th class="head1">退款租赁点</th>
								<th class="head0">退款方式</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">身份证号</th>
								<th class="head1">手机号码</th>
								<th class="head0">客户姓名</th>
								<th class="head1">退款金额</th>
								<th class="head0">退款类型</th>
								<th class="head1">退款时间</th>
								<th class="head0">经办人</th>
								<th class="head1">退款租赁点</th>
								<th class="head0">退款方式</th>
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>
					<br /> <br />
				</div>
				<div id="unit" class="subcontent" style="display: none;">
					<div class="overviewhead">
						<div class="overviewselect">
							<select id="type1" name="select" class="chzn-select"
								style="width: 200px;">
								<option value="">请选择退款类型</option>
								<option value="余额退款">余额退款</option>
								<option value="保证金退款">保证金退款</option>
							</select>
						</div>
						联系人: &nbsp;<input type="text" id="contactPerson"
							style="width: 200px;" /> &nbsp; &nbsp; 单位名称: &nbsp;<input
							type="text" id="enterpriseName" style="width: 200px;" /> &nbsp;
						联系电话: &nbsp;<input type="text" id="contactPhone"
							style="width: 200px;" /> &nbsp; &nbsp; <br /> <br /> 日期:
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="beginTime1" />
						&nbsp;&nbsp; &nbsp;&nbsp; 到: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
							type="text" id="endTime1" />&nbsp;&nbsp;&nbsp;
						<button class="publishbutton radius3" id="queryBtn1">查询</button>
						&nbsp;&nbsp;
						<button class="publishbutton radius3" id="excelBtn1">导出EXCEL</button>
						&nbsp;
					</div>
					<br clear="all" />
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="unitCustomersTable">
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
								<th class="head0">企业名称</th>
								<th class="head1">联系人</th>
								<th class="head0">联系电话</th>
								<th class="head1">退款金额</th>
								<th class="head0">退款类型</th>
								<th class="head1">退款时间</th>
								<th class="head0">经办人</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">企业名称</th>
								<th class="head1">联系人</th>
								<th class="head0">联系电话</th>
								<th class="head1">退款金额</th>
								<th class="head0">退款类型</th>
								<th class="head1">退款时间</th>
								<th class="head0">经办人</th>
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

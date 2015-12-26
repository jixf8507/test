<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript"
	src="${ctx}/js/page/customer/unitCustomerManager.js"></script>
</head>

<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />

		<div class="centercontent">
			<div class="pageheader">
				<h1 class="pagetitle">企业长租管理</h1>
				<span class="pagedesc">
				</span>
				<ul class="hornav">
					<li class="current"><a href="#normal" id="normalDiv">正常...</a></li>
					<li><a href="#freeze" id="freezeDiv">已冻结...</a></li>
					<li><a href="#cancel" id="cancelDiv">已注销...</a></li>
				</ul>
			</div>
			<div id="contentwrapper" class="contentwrapper">
				<!-- normal -->
				<div id="normal" class="subcontent">
					<div class="overviewhead">
						企业名称: &nbsp;<input type="text" id="enterpriseName" style="width: 200px;" /> &nbsp;&nbsp;联系人: &nbsp;&nbsp;<input
							type="text" id="contactPerson" style="width: 200px;" /> &nbsp; &nbsp; 联系电话: &nbsp;&nbsp;<input
							type="text" id="contactPhone" style="width: 200px;" /><br/><br/> 
							开卡日期: &nbsp;<input type="text" id="beginTime" /> &nbsp; &nbsp; &nbsp; 到: &nbsp; &nbsp;
						&nbsp;<input type="text" id="endTime" /> &nbsp;&nbsp;&nbsp;
						<button class="publishbutton radius3" id="queryBtn">查询</button> &nbsp;&nbsp;
						<button class="publishbutton radius3" id="excelBtn">导出EXCEL</button>
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
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
						</colgroup>
						<thead>
							<tr>
								<th class="head0">企业名称</th>
								<th class="head1">联系人</th>
								<th class="head0">联系电话</th>
								<th class="head1">地址</th>
								<th class="head0">账户余额</th>
								<th class="head1">保证金</th>
								<th class="head0">账户状态</th>
								<th class="head1">经办人</th>
								<th class="head0">开卡时间</th>
								<th class="head1">更多操作</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">企业名称</th>
								<th class="head1">联系人</th>
								<th class="head0">联系电话</th>
								<th class="head1">地址</th>
								<th class="head0">账户余额</th>
								<th class="head1">保证金</th>
								<th class="head0">账户状态</th>
								<th class="head1">经办人</th>
								<th class="head0">开卡时间</th>
								<th class="head1">更多操作</th>
							</tr>
						</tfoot>
						<tbody></tbody>
					</table>
					<br /> <br />
				</div>
				<!-- normal -->
				
				<!-- freeze -->
				<div id="freeze" class="subcontent" style="display: none;">
					<div class="overviewhead">
						企业名称: &nbsp;<input type="text" id="freezeEnterpriseName" style="width: 200px;" /> &nbsp;&nbsp;联系人: &nbsp;&nbsp;<input
							type="text" id="freezeContactPerson" style="width: 200px;" /> &nbsp; &nbsp; 联系电话: &nbsp;&nbsp;<input
							type="text" id="freezeContactPhone" style="width: 200px;" /><br/><br/> 
							冻结日期: &nbsp;<input type="text" id="freezeBeginTime" /> &nbsp; &nbsp; &nbsp; 到: &nbsp; &nbsp;
						&nbsp;<input type="text" id="freezeEndTime" /> &nbsp;&nbsp;&nbsp;
						<button class="publishbutton radius3" id="freezeQueryBtn">查询</button> &nbsp;&nbsp;
						<button class="publishbutton radius3" id="freezeExcelBtn">导出EXCEL</button>
					</div>
					<br clear="all" />
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="freezeCustomersTable">
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
						</colgroup>
						<thead>
							<tr>
								<th class="head0">企业名称</th>
								<th class="head1">联系人</th>
								<th class="head0">联系电话</th>
								<th class="head1">地址</th>
								<th class="head0">账户余额</th>
								<th class="head1">保证金</th>
								<th class="head0">账户状态</th>
								<th class="head1">经办人</th>
								<th class="head0">冻结时间</th>
								<th class="head1">更多操作</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">企业名称</th>
								<th class="head1">联系人</th>
								<th class="head0">联系电话</th>
								<th class="head1">地址</th>
								<th class="head0">账户余额</th>
								<th class="head1">保证金</th>
								<th class="head0">账户状态</th>
								<th class="head1">经办人</th>
								<th class="head0">冻结时间</th>
								<th class="head1">更多操作</th>
							</tr>
						</tfoot>
						<tbody></tbody>
					</table>
					<br /> <br />
				</div>
				<!-- freeze -->
				
				<!-- cancel -->
				<div id="cancel" class="subcontent" style="display: none;">
					<div class="overviewhead">
						企业名称: &nbsp;<input type="text" id="cancelEnterpriseName" style="width: 200px;" /> &nbsp;&nbsp;联系人: &nbsp;&nbsp;<input
							type="text" id="cancelContactPerson" style="width: 200px;" /> &nbsp; &nbsp; 联系电话: &nbsp;&nbsp;<input
							type="text" id="cancelContactPhone" style="width: 200px;" /><br/><br/> 
							注销日期: &nbsp;<input type="text" id="cancelBeginTime" /> &nbsp; &nbsp; &nbsp; 到: &nbsp; &nbsp;
						&nbsp;<input type="text" id="cancelEndTime" /> &nbsp;&nbsp;&nbsp;
						<button class="publishbutton radius3" id="cancelQueryBtn">查询</button> &nbsp;&nbsp;
						<button class="publishbutton radius3" id="cancelExcelBtn">导出EXCEL</button>
					</div>
					<br clear="all" />
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="cancelCustomersTable">
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
						</colgroup>
						<thead>
							<tr>
								<th class="head0">企业名称</th>
								<th class="head1">联系人</th>
								<th class="head0">联系电话</th>
								<th class="head1">地址</th>
								<th class="head0">账户余额</th>
								<th class="head1">保证金</th>
								<th class="head0">账户状态</th>
								<th class="head1">经办人</th>
								<th class="head0">注销时间</th>
								<th class="head1">更多操作</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">企业名称</th>
								<th class="head1">联系人</th>
								<th class="head0">联系电话</th>
								<th class="head1">地址</th>
								<th class="head0">账户余额</th>
								<th class="head1">保证金</th>
								<th class="head0">账户状态</th>
								<th class="head1">经办人</th>
								<th class="head0">注销时间</th>
								<th class="head1">更多操作</th>
							</tr>
						</tfoot>
						<tbody></tbody>
					</table>
					<br /> <br />
				</div>
			</div>
		</div>
	</div>
</body>
</html>

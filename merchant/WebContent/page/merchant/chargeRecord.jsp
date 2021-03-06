<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript"
	src="${ctx}/js/page/merchant/chargeRecord.js"></script>
</head>
<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />
		<div class="centercontent">

			<div class="pageheader">
				<h1 class="pagetitle">收费记录明细</h1>
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
							<select id="status" name="select" class="chzn-select"
								style="width: 200px;" tabindex="2">
								<option value="">请选择核对入账状态</option>
								<option value="已入账">已核对入账</option>
								<option value="未入账">未核对入账</option>
							</select> <select id="type" name="select" class="chzn-select"
								style="width: 200px;">
								<option value="">请选择收费类型</option>
								<option value="余额充值">余额充值</option>
								<option value="保证金充值">保证金充值</option>
								<option value="电子钱包充值">电子钱包充值</option>
							</select> <select id="siteId" name="select" class="chzn-select"
								style="width: 200px;" tabindex="2">
								<option value="">请选择租赁点</option>
							</select>
						</div>
						收费日期: <input type="text" id="beginTime" />&nbsp;&nbsp;到:&nbsp;&nbsp;<input
							type="text" id="endTime" /><br /> <br />身份证:&nbsp; &nbsp;<input
							type="text" id="idCard" />&nbsp; &nbsp; 手机: <input type="text"
							id="phone" /> &nbsp; &nbsp;姓名: <input type="text" id="name" />
						&nbsp; &nbsp;收费人: <input type="text" id="transactUser" />&nbsp;&nbsp;&nbsp;核对人:
						&nbsp;<input type="text" id="checkUser" />&nbsp;&nbsp;&nbsp;
						<button class="publishbutton radius3" id="queryBtn">查询</button>
						&nbsp;&nbsp;
						<button class="publishbutton radius3" id="excelBtn">导出EXCEL</button>
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
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
							<col class="con0" style="width: 8%"/>
						</colgroup>
						<thead>
							<tr>
								<th class="head0">身份证/驾驶证</th>
								<th class="head1">手机号码</th>
								<th class="head0">客户姓名</th>
								<th class="head1">收费金额</th>
								<th class="head0">收费类型</th>
								<th class="head1">收费时间</th>
								<th class="head0">收费人</th>
								<th class="head1">收费租赁点</th>
								<th class="head0">付费方式</th>
								<th class="head1">票据编号</th>
								<th class="head0">核对人</th>
								<th class="head1">核对描述</th>
								<th class="head0">核对入账</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">身份证/驾驶证</th>
								<th class="head1">手机号码</th>
								<th class="head0">客户姓名</th>
								<th class="head1">收费金额</th>
								<th class="head0">收费类型</th>
								<th class="head1">收费时间</th>
								<th class="head0">收费人</th>
								<th class="head1">收费租赁点</th>
								<th class="head0">付费方式</th>
								<th class="head1">票据编号</th>
								<th class="head0">核对人</th>
								<th class="head1">核对描述</th>
								<th class="head0">核对入账</th>
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
							<select id="status1" name="select" class="chzn-select"
								style="width: 200px;" tabindex="2">
								<option value="">请选择核对入账状态</option>
								<option value="已入账">已核对入账</option>
								<option value="未入账">未核对入账</option>
							</select> <select id="type1" name="select" class="chzn-select"
								style="width: 200px;">
								<option value="">请选择收费类型</option>
								<option value="余额充值">余额充值</option>
								<option value="保证金充值">保证金充值</option>
							</select> <select id="siteId1" name="select" class="chzn-select"
								style="width: 200px;" tabindex="2">
								<option value="">请选择租赁点</option>
							</select>
						</div>
						收费日期: <input type="text" id="beginTime1" />&nbsp;&nbsp;到:&nbsp;&nbsp;<input type="text"
							id="endTime1" /> <br /><br />
						联系人: <input type="text"
							id="contactPerson" />&nbsp;&nbsp;单位名称: <input
							type="text" id="enterpriseName" /> &nbsp; 联系电话: &nbsp;<input
							type="text" id="contactPhone" />&nbsp;&nbsp;收费人: <input type="text"
							id="transactUser1" />&nbsp;&nbsp;核对人: <input
							type="text" id="checkUser1" />&nbsp;&nbsp;&nbsp;
						<button class="publishbutton radius3" id="queryBtn1">查询</button>
						&nbsp;&nbsp;
						<button class="publishbutton radius3" id="excelBtn1">导出EXCEL</button>
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
							<col class="con0" />
						</colgroup>
						<thead>
							<tr>
								<th class="head0">身份证/驾驶证</th>
								<th class="head1">手机号码</th>
								<th class="head0">客户姓名</th>
								<th class="head1">收费金额</th>
								<th class="head0">收费类型</th>
								<th class="head1">收费时间</th>
								<th class="head0">收费人</th>
								<th class="head1">收费租赁点</th>
								<th class="head0">付费方式</th>
								<th class="head1">票据编号</th>
								<th class="head0">核对人</th>
								<th class="head1">核对描述</th>
								<th class="head0">核对入账</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">身份证/驾驶证</th>
								<th class="head1">手机号码</th>
								<th class="head0">客户姓名</th>
								<th class="head1">收费金额</th>
								<th class="head0">收费类型</th>
								<th class="head1">收费时间</th>
								<th class="head0">收费人</th>
								<th class="head1">收费租赁点</th>
								<th class="head0">付费方式</th>
								<th class="head1">票据编号</th>
								<th class="head0">核对人</th>
								<th class="head1">核对描述</th>
								<th class="head0">核对入账</th>
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

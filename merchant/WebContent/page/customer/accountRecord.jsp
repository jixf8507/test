<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript"
	src="${ctx}/js/page/customer/accountRecord.js"></script>
</head>

<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />

		<div class="centercontent">
			<div class="pageheader">
				<h1 class="pagetitle">客户账户交易明细记录</h1>
				<span class="pagedesc"></span>
				<ul class="hornav">
					<li class="current"><a href="#normal" id="normalDiv">客户账户</a></li>
					<li><a href="#unit" id="unitDiv">企业会员</a></li>
				</ul>
			</div>
			<!--pageheader-->
			<div id="contentwrapper" class="contentwrapper">
				<div id="normal" class="subcontent">
					<div class="overviewhead">
						<div class="overviewselect">
							<select id="type" name="select" class="chzn-select"
								style="width: 200px;">
								<option value="">请选择交易类型</option>
								<c:if test="${sessionScope.user.systemName == 'eakay'}">
									<option value="租赁付费">租赁付费</option>
								</c:if>
								<option value="充电消费">充电消费</option>
								<option value="余额充值">余额充值</option>
								<option value="余额退款">余额退款</option>
								<option value="余额转入">余额转入</option>
								<option value="余额转出">余额转出</option>
								<option value="保证金充值">保证金充值</option>
								<option value="保证金退款">保证金退款</option>
								<option value="保证金转入">保证金转入</option>
								<option value="保证金转出">保证金转出</option>
							</select>
						</div>
						<!--floatright-->
						身份证: &nbsp;<input type="text" id="idCard" style="width: 200px;" />
						&nbsp; &nbsp; 手机: &nbsp;<input type="text" id="phone"
							style="width: 200px;" /> &nbsp; &nbsp; 姓名: &nbsp;<input
							type="text" id="name" style="width: 200px;" /> <br />
						<br /> 日期: &nbsp;&nbsp;&nbsp;&nbsp;<input type="text"
							id="beginTime" /> &nbsp; &nbsp;&nbsp;&nbsp; 到:
						&nbsp;&nbsp;&nbsp;<input type="text" id="endTime" />&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="publishbutton radius3" id="queryBtn">查询</button>
						&nbsp;
						<button class="publishbutton radius3" id="excelBtn">导出EXCEL</button>
					</div>
					<!--overviewhead-->
					<br clear="all" />

					<!--contenttitle-->
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
						</colgroup>
						<thead>
							<tr>
								<th class="head0">身份证号</th>
								<th class="head1">手机号码</th>
								<th class="head0">客户姓名</th>
								<th class="head1">交易金额</th>
								<th class="head0">账户余额</th>
								<th class="head1">交易类型</th>
								<th class="head0">交易时间</th>
								<th class="head1">票据编号</th>
								<th class="head0">经办人</th>
								<th class="head1">付费方式</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">身份证号</th>
								<th class="head1">手机号码</th>
								<th class="head0">客户姓名</th>
								<th class="head1">交易金额</th>
								<th class="head0">账户余额</th>
								<th class="head1">交易类型</th>
								<th class="head0">交易时间</th>
								<th class="head1">票据编号</th>
								<th class="head0">经办人</th>
								<th class="head1">付费方式</th>
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>
					<br /> <br />
				</div>
				<!-- #use -->


				<div id="unit" class="subcontent" style="display: none;">
					<div class="overviewhead">
						<div class="overviewselect">
							<select id="type1" name="select" class="chzn-select"
								style="width: 200px;">
								<option value="">请选择交易类型</option>
								<c:if test="${sessionScope.user.systemName == 'eakay'}">
									<option value="租赁付费">租赁付费</option>
								</c:if>
								<option value="充电消费">充电消费</option>
								<option value="余额充值">余额充值</option>
								<option value="余额退款">余额退款</option>
								<option value="余额转入">余额转入</option>
								<option value="余额转出">余额转出</option>
								<option value="保证金充值">保证金充值</option>
								<option value="保证金退款">保证金退款</option>
								<option value="保证金转入">保证金转入</option>
								<option value="保证金转出">保证金转出</option>
							</select>
						</div>
						<!--floatright-->
						企业名称: &nbsp;<input type="text" id="enterpriseName" style="width: 200px;" /> &nbsp;&nbsp;联系人: &nbsp;&nbsp;<input
							type="text" id="contactPerson" style="width: 200px;" /> &nbsp; &nbsp; 联系电话: &nbsp;&nbsp;<input
							type="text" id="contactPhone" style="width: 200px;" /> &nbsp;&nbsp;  <br />
						<br /> &nbsp;&nbsp;&nbsp;日期:&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;<input type="text"
							id="beginTime1" /> &nbsp; &nbsp;&nbsp;&nbsp; 到:
						&nbsp;&nbsp;&nbsp;<input type="text" id="endTime1" />&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="publishbutton radius3" id="queryBtn1">查询</button>
						&nbsp;
						<button class="publishbutton radius3" id="excelBtn1">导出EXCEL</button>
					</div>
					<!--overviewhead-->
					<br clear="all" />

					<!--contenttitle-->
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
								<th class="head1">交易金额</th>
								<th class="head0">账户余额</th>
								<th class="head1">交易类型</th>
								<th class="head0">交易时间</th>
								<th class="head1">票据编号</th>
								<th class="head0">经办人</th>
								<th class="head1">付费方式</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">企业名称</th>
								<th class="head1">联系人</th>
								<th class="head0">联系电话</th>
								<th class="head1">交易金额</th>
								<th class="head0">账户余额</th>
								<th class="head1">交易类型</th>
								<th class="head0">交易时间</th>
								<th class="head1">票据编号</th>
								<th class="head0">经办人</th>
								<th class="head1">付费方式</th>
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

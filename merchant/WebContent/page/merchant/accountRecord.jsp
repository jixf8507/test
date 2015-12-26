<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript"
	src="${ctx}/js/page/merchant/accountRecord.js"></script>
</head>
<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />

		<div class="centercontent">
			<div class="pageheader">
				<h1 class="pagetitle">商家账户明细</h1>
				<span class="pagedesc"></span>
			</div>
			<!--pageheader-->
			<div id="contentwrapper" class="contentwrapper">

				<div id="normal" class="subcontent">
			<div class="overviewhead">
				<div class="overviewselect">
					<select id="type" name="type" class="chzn-select"
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
					</select>&nbsp; &nbsp; 
					 <select id="siteId" name="select" class="chzn-select"
						style="width: 200px;" tabindex="2">
						<option value="">请选择租赁点</option> 
					</select>
				</div>
				<!--floatright-->
				&nbsp; &nbsp; 日期: &nbsp;<input type="text" id="beginTime" /> &nbsp;
				&nbsp; 到: &nbsp;<input type="text" id="endTime" />&nbsp; &nbsp; 
				<button class="publishbutton radius3" id="queryBtn">查询</button>&nbsp; 
				<button class="publishbutton radius3" id="excelBtn">导出EXCEL</button>
			</div>
			<!--overviewhead-->
			<br clear="all" />

					<!--contenttitle-->
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="drawsTable">
						<colgroup>
							<col class="con0" />
							<col class="con1" />
							<col class="con0"  />
							<col class="con1" />
							<col class="con0"  />
							<col class="con1" />
						</colgroup>
						<thead>
							<tr>

								<th class="head0">交易金额</th>
								<th class="head1">账户余额</th>
								<th class="head0">交易类型</th>
								<th class="head1">站点</th>
								<th class="head0">经办人</th>
								<th class="head1">交易时间</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">交易金额</th>
								<th class="head1">账户余额</th>
								<th class="head0">交易类型</th>
								<th class="head1">站点</th>
								<th class="head0">经办人</th>
								<th class="head1">交易时间</th>
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

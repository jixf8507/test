<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript"
	src="${ctx}/js/plugins/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="${ctx}/js/page/merchant/drawMoney.js"></script>
</head>
<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />
		<div class="centercontent">
			<div class="pageheader">
				<h1 class="pagetitle">商家提款流程</h1>
				<span class="pagedesc">1、提款申请 ==》2、平台审核 ==》 3、平台打款 == 》4、提款成功</span>

				<ul class="hornav">
					<li class="current"><a href="#list">提款记录状态</a></li>
					<%--功能能放到提款记录状态中去 --%>
					<%-- <li><a href="#apply">申请提款</a></li> --%>
				</ul>
			</div>
			<!--pageheader-->

			<div id="contentwrapper" class="contentwrapper">
				<div id="list" class="subcontent">
					<!--pageheader-->
					<div class="overviewhead">
						<div class="overviewselect">
							<select id="status" name="select" class="chzn-select"
								style="width: 200px;">
								<option value="">请选择交易状态</option>
								<option value="申请中">申请中</option>
								<option value="处理中">处理中</option>
								<option value="完成提款">完成提款</option>
							</select>
						</div>
						申请日期: &nbsp;<input type="text" id="beginTime" /> &nbsp; &nbsp;
					 到: &nbsp;<input type="text" id="endTime" /> 
				  &nbsp; &nbsp;<button class="publishbutton radius3" id="queryBtn">查询</button> &nbsp;
						<button class="publishbutton radius3" id="excelBtn">导出EXCEL</button>
					</div>
					<!--overviewhead-->
					<br clear="all" />

					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="drawsTable">
						<colgroup>
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />

						</colgroup>
						<thead>
							<tr>
								<th class="head0">提款金额</th>
								<th class="head1">提款说明</th>
								<th class="head0">状态</th>
								<th class="head1">申请提款时间</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">提款金额</th>
								<th class="head1">提款说明</th>
								<th class="head0">状态</th>
								<th class="head1">申请提款时间</th>
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>
					<br /> <br />
				</div>
				<!-- #list -->

				<%-- <div id="apply" class="subcontent" style="display: none;">

					<!--contenttitle-->

					<form id="form1" class="stdform stdform2" method="post"
						action="${ctx}/merchant/drawMoney/saveAdd.htm?">
						<p>
							<label>当前可取金额</label> <span class="field">${merchantPO.balance}元&nbsp;</span>
							<input type="hidden" id="balance" value="${merchantPO.balance}"/>
						</p>
						<p>
							<label>提款企业银行帐号</label> <span class="field">${merchantPO.alipayAccount}&nbsp;</span>
						</p>
						<p>
							<label>提款企业账户名</label> <span class="field">${merchantPO.alipayAccountName}&nbsp;</span>
						</p>
						<p>
							<label>提款银行</label> <span class="field">${merchantPO.bankTypeName}&nbsp;</span>
						</p>

						<p>
							<label>提款金额</label> <span class="field"><input type="text"
								name="money" id="money" class="longinput" style="width: 200px;" />&nbsp;&nbsp;元</span>
						</p>

						<p>
							<label>提款说明</label> <span class="field"> <textarea
									cols="80" rows="5" name="remarks" id="remarks"
									class="mediuminput"></textarea></span>
						</p>

						<p class="stdformbutton">
							<button class="submit radius2">提交申请</button>
							<input type="reset" class="reset radius2" value="重置" />
						</p>
					</form>

				</div> --%>
				<!-- #apply -->
			</div>
			<!--contentwrapper-->
			<br clear="all" />
		</div>
		<!-- centercontent -->
	</div>
	<!--bodywrapper-->

</body>
</html>

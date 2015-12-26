<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript"
	src="${ctx}/js/page/merchant/familyManager.js"></script>
</head>
<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />
		<div class="centercontent">
			<div class="pageheader">
				<h1 class="pagetitle">服务中心管理</h1>
				<ul class="hornav">
					<li class="current"><a href="#family">普通家庭</a></li>
					<li><a href="#fronzenfamily" id="fronzenfamilyDiv">冻结家庭</a></li>
					<!-- <li><a href="#usedCoupon" id="usedCouponDiv">已用优惠券</a></li>
					<li><a href="#outTimeCoupon" id="outTimeCouponDiv">过期优惠券</a></li> -->
				</ul>
			</div>
			<!--pageheader-->
			<div id="contentwrapper" class="contentwrapper">
				<!-- 普通家庭 -->
				<div id="family" class="subcontent">
					<div class="overviewhead">
						<%--<div class="overviewselect" id="cityDiv">
							<select data-placeholder="选择" name="city" id="city"
								class="chzn-select" style="width: 200px;" tabindex="2">
								<option value=""></option>
							</select>
						</div> 
						<div class="overviewselect">
							<select data-placeholder="选择" name="province" id="province"
								class="chzn-select" style="width: 200px;" tabindex="2">
								<option value=""></option>
							</select>
						</div> --%>
						<!--floatright-->
						户主: &nbsp;<input type="text" id="householder" />&nbsp; &nbsp;
						电话号码: &nbsp;<input type="text" id="homePhone" />&nbsp; &nbsp;
						家庭住址: &nbsp;<input type="text" id="homeAddress" />&nbsp; &nbsp;
						<button class="publishbutton radius3" id="familyQueryBtn">查询</button>
						&nbsp;
						<!-- <button class="publishbutton radius3" id="unUsedExcelBtn">导出EXCEL</button> -->
					</div>
					<!--overviewhead-->
					
					<br clear="all" />
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="familyTable">
						<colgroup>
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
							<!-- <col class="con0" /> -->
						</colgroup>
						<thead>
							<tr>
								<th class="head0">编码</th>
								<th class="head1">负责人</th>
								<th class="head0">家庭住址</th>
								<th class="head1">电话号码</th>
								<th class="head0">创建时间</th>
								<th class="head0">保证金</th>
								<th class="head1">状态</th>
								<th class="head1">操作</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">编码</th>
								<th class="head1">负责人</th>
								<th class="head0">家庭住址</th>
								<th class="head1">电话号码</th>
								<th class="head0">创建时间</th>
								<th class="head0">保证金</th>
								<th class="head1">状态</th>
								<th class="head1">操作</th>
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>
					<br /> <br />
				</div>
				<!-- family -->
				
				
				
				<!-- 冻结家庭家庭 -->
				<div id="fronzenfamily" class="subcontent" style="display:none">
					<div class="overviewhead">
						<%--<div class="overviewselect" id="cityDiv">
							<select data-placeholder="选择" name="city" id="city"
								class="chzn-select" style="width: 200px;" tabindex="2">
								<option value=""></option>
							</select>
						</div> 
						<div class="overviewselect">
							<select data-placeholder="选择" name="province" id="province"
								class="chzn-select" style="width: 200px;" tabindex="2">
								<option value=""></option>
							</select>
						</div> --%>
						<!--floatright-->
						户主: &nbsp;<input type="text" id="householder" />&nbsp; &nbsp;
						电话号码: &nbsp;<input type="text" id="homePhone" />&nbsp; &nbsp;
						家庭住址: &nbsp;<input type="text" id="homeAddress" />&nbsp; &nbsp;
						<button class="publishbutton radius3" id="familyQueryBtn">查询</button>
						&nbsp;
						<!-- <button class="publishbutton radius3" id="unUsedExcelBtn">导出EXCEL</button> -->
					</div>
					<!--overviewhead-->
					
					<br clear="all" />
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="fronzenFamilyTable">
						<colgroup>
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
							<!-- <col class="con0" /> -->
						</colgroup>
						<thead>
							<tr>
								<th class="head0">编码</th>
								<th class="head1">户主</th>
								<th class="head0">家庭住址</th>
								<th class="head1">电话号码</th>
								<th class="head0">创建时间</th>
								<th class="head0">保证金</th>
								<th class="head1">状态</th>
								<th class="head1">操作</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">编码</th>
								<th class="head1">户主</th>
								<th class="head0">家庭住址</th>
								<th class="head1">电话号码</th>
								<th class="head0">创建时间</th>
								<th class="head0">保证金</th>
								<th class="head1">状态</th>
								<th class="head1">操作</th>
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>
					<br /> <br />
				</div>
				<!-- fronzenfamily -->
				
				
				
				
			</div>
			<!--contentwrapper-->
			<br clear="all" />
		</div>
		<!-- centercontent -->
	</div>
	<!--bodywrapper-->

</body>
</html>

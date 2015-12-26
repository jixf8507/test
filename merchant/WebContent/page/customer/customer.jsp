<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript" src="${ctx}/js/page/customer/customer.js"></script>
</head>

<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />

		<div class="centercontent">

			<div class="pageheader">
				<h1 class="pagetitle">客户信息列表</h1>
				<span class="pagedesc"></span>
				<ul class="hornav">
					<li class="current"><a href="#normal">普通用户...</a></li>
					<li><a href="#unit" id="unitDiv">企业用户...</a></li>
					<li><a href="#family" id="familylDiv">家庭用户...</a></li>
				</ul>
			</div>
			<div id="contentwrapper" class="contentwrapper">
				
				<!-- 普通用户 -->
				<div id="normal" class="subcontent">
					<div class="overviewhead">
						身份证: &nbsp;<input type="text" id="normalIdCard" /> &nbsp; 手机号码:
						&nbsp;<input type="text" id="normalPhone" /> &nbsp;客户姓名: &nbsp;<input
							type="text" id="normalName" /> &nbsp; &nbsp;<br/><br/>
							日期: &nbsp;&nbsp;&nbsp;&nbsp;<input type="text" 
							id="beginTime" /> &nbsp; &nbsp;&nbsp;&nbsp; 到:
						&nbsp;&nbsp;&nbsp;<input type="text" id="endTime" />&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="publishbutton radius3" id="normalQueryBtn">查询</button>
						<button class="publishbutton radius3" id="normalExcelBtn">导出EXCEL</button>
					</div>
					<!--overviewhead-->

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
								<th class="head0">身份证/驾驶证</th>
								<th class="head1">手机号码</th>
								<th class="head0">姓名</th>							
								<th class="head1">保证金</th>
								<th class="head0">余额</th>
								<th class="head1">状态</th>
								<th class="head0">开户时间</th>
								<th class="head1">开户人</th>
								<th class="head0">查看详细</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">身份证/驾驶证</th>
								<th class="head1">手机号码</th>
								<th class="head0">姓名</th>							
								<th class="head1">保证金</th>
								<th class="head0">余额</th>
								<th class="head1">状态</th>
								<th class="head0">开户时间</th>
								<th class="head1">开户人</th>
								<th class="head0">查看详细</th>
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>

					<br /> <br />

				</div>
				<!-- #use -->
				
				<!-- 企业用户 -->
				<div id="unit" class="subcontent" style="display: none;">
					<div class="overviewhead">
						身份证: &nbsp;<input type="text" id="unitIdCard" /> &nbsp; 手机号码:
						&nbsp;<input type="text" id="unitPhone" /> &nbsp;客户姓名: &nbsp;<input
							type="text" id="unitName" /> &nbsp;企业名称: &nbsp;<input
							type="text" id="enterpriseName" /> &nbsp; &nbsp;<br/><br/>
							日期: &nbsp;&nbsp;&nbsp;&nbsp;<input type="text" 
							id="beginTime1" /> &nbsp; &nbsp;&nbsp;&nbsp; 到:
						&nbsp;&nbsp;&nbsp;<input type="text" id="endTime1" />&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="publishbutton radius3" id="unitQueryBtn">查询</button>
						<button class="publishbutton radius3" id="unitExcelBtn">导出EXCEL</button>
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
								<th class="head0">身份证/驾驶证</th>
								<th class="head1">手机号码</th>
								<th class="head0">姓名</th>
								<th class="head0">企业名称</th>								
								<th class="head1">保证金</th>
								<th class="head0">余额</th>
								<th class="head0">开户人</th>
								<th class="head1">状态</th>
								<th class="head1">查看详细</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">身份证/驾驶证</th>
								<th class="head1">手机号码</th>
								<th class="head0">姓名</th>
								<th class="head0">企业名称</th>								
								<th class="head1">保证金</th>
								<th class="head0">余额</th>
								<th class="head0">开户人</th>
								<th class="head1">状态</th>
								<th class="head1">查看详细</th>
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>

					<br /> <br />

				</div>
				<!-- #wait -->
				
				<!-- 家庭用户 -->
				<div id="family" class="subcontent" style="display: none;">
					<div class="overviewhead">
					<div class="overviewselect">
					<!-- <select id="subStatus" name="select" class="chzn-select"
						style="width: 200px;">
						<option value="">请选择退款状态</option>						
						<option value="正常">未退款</option>
						<option value="已退款">已退款</option>
					</select> -->
				</div>
						身份证: &nbsp;<input type="text" id="familyIdCard" /> &nbsp; 手机号码:
						&nbsp;<input type="text" id="familyPhone" /> &nbsp;客户姓名: &nbsp;<input
							type="text" id="familyName" /> &nbsp;负责人: &nbsp;<input
							type="text" id="householder" /> &nbsp; &nbsp;<br/><br/>
							日期: &nbsp;&nbsp;&nbsp;&nbsp;<input type="text" 
							id="beginTime2" /> &nbsp; &nbsp;&nbsp;&nbsp; 到:
						&nbsp;&nbsp;&nbsp;<input type="text" id="endTime2" />&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="publishbutton radius3" id="familyQueryBtn">查询</button>
						<button class="publishbutton radius3" id="familyExcelBtn">导出EXCEL</button>
					</div>
					<!--overviewhead-->

					<br clear="all" />
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="familyCustomersTable">
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
								<th class="head0">姓名</th>
								<th class="head0">负责人</th>									
								<th class="head1">保证金</th>
								<th class="head0">余额</th>
								<th class="head1">开户人</th>
								<th class="head1">状态</th>
								<th class="head0">查看详细</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">身份证/驾驶证</th>
								<th class="head1">手机号码</th>
								<th class="head0">姓名</th>
								<th class="head0">负责人</th>									
								<th class="head1">保证金</th>
								<th class="head0">余额</th>
								<th class="head1">开户人</th>
								<th class="head1">状态</th>
								<th class="head0">查看详细</th>
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>

					<br /> <br />
				</div>
			</div>
			<!--contentwrapper-->
		</div>
		<!-- centercontent -->
	</div>
	<!--bodywrapper-->
</body>
</html>

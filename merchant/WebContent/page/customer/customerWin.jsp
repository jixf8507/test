<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>

<script type="text/javascript"
	src="${ctx}/js/page/customer/customerWin.js"></script>
</head>

<body>
	<input type="hidden" id="noUse" value="${param.noUse}" />
	<input type="hidden" id="type" value="${param.type}" />
	<input type="hidden" id="tableName" value="${param.tableName}" />

	<c:if test="${param.tableName == 'wh_hours_price_type'}">
		<ul class="hornav">
			<li class="current"><a href="#normal" id="normalDiv">普通用户</a></li>
			<li><a href="#enterprise" id="enterpriseDiv">企业用户</a></li>
			<li><a href="#family" id="familyDiv">家庭用户</a></li>
		</ul>
	</c:if>
	<c:if test="${param.type == 'all'}">
		<ul class="hornav">
			<li class="current"><a href="#normal" id="normalDiv">普通客户</a></li>
			<li><a href="#unit" id="unitDiv">企业用户</a></li>
		</ul>
	</c:if>
	<div id="contentwrapper" class="contentwrapper">
	<!-- 普通客户 -->
		<div id="normal" class="subcontent"
			<c:if test="${param.tableName == 'month_lease_price_type'}">style="display: none;" </c:if>>
			<div class="overviewhead">
				手机号码: &nbsp; <input type="text" id="phone" /> &nbsp; 身份证: &nbsp; <input
					type="text" id="idCard" /> &nbsp; 客户姓名: &nbsp; <input type="text"
					id="name" /> &nbsp; &nbsp;
				<button class="publishbutton radius3" id="queryBtn">查询</button>
			</div>
			<!--contenttitle-->
			<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
				id="customerTable">
				<colgroup>
					<col class="con0" width="10%" />
					<col class="con1" />
					<col class="con0" />
					<col class="con1" />
					<col class="con0" />
					<col class="con1" />
				</colgroup>
				<thead>
					<tr>
						<th class="head0">选择</th>
						<th class="head1">手机号码</th>
						<th class="head0">姓名</th>
						<th class="head1">身份证/驾驶证</th>
						<th class="head0">租赁保证金</th>
						<th class="head1">账户余额</th>
					</tr>
				</thead>
				<tfoot>
					<tr>
						<th class="head0">选择</th>
						<th class="head1">手机号码</th>
						<th class="head0">姓名</th>
						<th class="head1">身份证/驾驶证</th>
						<th class="head0">租赁保证金</th>
						<th class="head1">账户余额</th>
					</tr>
				</tfoot>
				<tbody>
				</tbody>
			</table>
		</div>
		<!-- 普通客户 -->
		
		<!-- 单位会员 -->
		<c:if test="${param.tableName == 'month_lease_price_type'}">
			<div id="unit" class="subcontent">
				<div class="overviewhead">
					企业名称: &nbsp;<input type="text" id="enterpriseName" />
					&nbsp;&nbsp;联系人: &nbsp;&nbsp;<input type="text" id="contactPerson" />
					&nbsp; &nbsp; 联系电话: &nbsp;&nbsp;<input type="text"
						id="contactPhone" /> &nbsp;&nbsp;
					<button class="publishbutton radius3" id="unitQueryBtn">查询</button>
				</div>
				<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
					id="unitCustomerTable">
					<colgroup>
						<col class="con0" width="10%" />
						<col class="con1" />
						<col class="con0" />
						<col class="con1" />
						<col class="con0" />
						<col class="con1" />
					</colgroup>
					<thead>
						<tr>
							<th class="head0">选择</th>
							<th class="head1">企业名称</th>
							<th class="head0">联系人</th>
							<th class="head1">联系电话</th>
							<th class="head0">账户余额</th>
							<th class="head1">保证金</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<th class="head0">选择</th>
							<th class="head1">企业名称</th>
							<th class="head0">联系人</th>
							<th class="head1">联系电话</th>
							<th class="head0">账户余额</th>
							<th class="head1">保证金</th>
						</tr>
					</tfoot>
					<tbody>
					</tbody>
				</table>
			</div>
		</c:if>
		<!-- 单位会员 -->
		<!-- 单位会员 -->
		<div id="unit" class="subcontent" style="display: none;">
			<div class="overviewhead">
				企业名称: &nbsp;<input type="text" id="enterpriseName" />
				&nbsp;&nbsp;联系人: &nbsp;&nbsp;<input type="text" id="contactPerson" />
				&nbsp; &nbsp; 联系电话: &nbsp;&nbsp;<input type="text" id="contactPhone" />
				&nbsp;&nbsp;
				<button class="publishbutton radius3" id="unitQueryBtn">查询</button>
			</div>
			<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
				id="unitCustomerTable">
				<colgroup>
					<col class="con0" width="10%" />
					<col class="con1" />
					<col class="con0" />
					<col class="con1" />
					<col class="con0" />
					<col class="con1" />
				</colgroup>
				<thead>
					<tr>
						<th class="head0">选择</th>
						<th class="head1">企业名称</th>
						<th class="head0">联系人</th>
						<th class="head1">联系电话</th>
						<th class="head0">账户余额</th>
						<th class="head1">保证金</th>
					</tr>
				</thead>
				<tfoot>
					<tr>
						<th class="head0">选择</th>
						<th class="head1">企业名称</th>
						<th class="head0">联系人</th>
						<th class="head1">联系电话</th>
						<th class="head0">账户余额</th>
						<th class="head1">保证金</th>
					</tr>
				</tfoot>
				<tbody>
				</tbody>
			</table>
		</div>
		<!-- 单位会员 -->
		
		<!-- 企业用户 -->
		<div id="enterprise" class="subcontent" style="display: none;">
			<div class="overviewhead">
				手机号码: &nbsp; <input type="text" id="phone1" /> &nbsp; 身份证: &nbsp; <input
					type="text" id="idCard1" /> &nbsp; 客户姓名: &nbsp; <input type="text"
					id="name1" /> &nbsp; &nbsp;
				<button class="publishbutton radius3" id="queryBtn1">查询</button>
			</div>
			<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
				id="enterpriseTable">
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
						<th class="head0">选择</th>
						<th class="head1">手机号码</th>
						<th class="head0">姓名</th>
						<th class="head1">身份证/驾驶证</th>
						<th class="head0">租赁保证金</th>
						<th class="head1">账户余额</th>
						<th class="head0">企业名称</th>
					</tr>
				</thead>
				<tfoot>
					<tr>
						<th class="head0">选择</th>
						<th class="head1">手机号码</th>
						<th class="head0">姓名</th>
						<th class="head1">身份证/驾驶证</th>
						<th class="head0">租赁保证金</th>
						<th class="head1">账户余额</th>
						<th class="head0">企业名称</th>
					</tr>
				</tfoot>
				<tbody>
				</tbody>
			</table>
		</div>
		<!-- 企业用户 -->

		<!-- 家庭用户 -->
		<div id="family" class="subcontent" style="display: none;">
			<div class="overviewhead">
				手机号码: &nbsp; <input type="text" id="phone2" /> &nbsp; 身份证: &nbsp; <input
					type="text" id="idCard2" /> &nbsp; 客户姓名: &nbsp; <input type="text"
					id="name2" /> &nbsp; &nbsp;
				<button class="publishbutton radius3" id="queryBtn2">查询</button>
			</div>
			<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
				id="familyTable">
				<colgroup>
					<col class="con0" width="10%" />
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
						<th class="head0">选择</th>
						<th class="head1">手机号码</th>
						<th class="head0">姓名</th>
						<th class="head1">身份证/驾驶证</th>
						<th class="head0">租赁保证金</th>
						<th class="head1">账户余额</th>
						<th class="head0">户主</th>
						<th class="head1">户主租赁保证金</th>
					</tr>
				</thead>
				<tfoot>
					<tr>
						<th class="head0">选择</th>
						<th class="head1">手机号码</th>
						<th class="head0">姓名</th>
						<th class="head1">身份证/驾驶证</th>
						<th class="head0">租赁保证金</th>
						<th class="head1">账户余额</th>
						<th class="head0">户主</th>
						<th class="head1">户主租赁保证金</th>
					</tr>
				</tfoot>
				<tbody>
				</tbody>
			</table>
		</div>
		<!-- 家庭用户 -->
		
	</div>
</body>
</html>

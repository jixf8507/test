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
<script type="text/javascript" src="${ctx}/js/ajaxfileupload.js"></script>
<script type="text/javascript"
	src="${ctx}/js/page/customer/unitCustomerEdite.js"></script>
<%-- <script type="text/javascript"
	src="${ctx}/js/page/customer/sendVerCode.js"></script> --%>
</head>
<ul class="hornav">
	<li class="current"><a href="#list" id="listDiv">基本信息</a></li>
	<li><a href="#customer" id="customerDiv">用户信息</a></li>
</ul>
<div id="contentwrapper" class="contentwrapper">
	<div id="list" class="subcontent">
		<form id="form1" class="stdform stdform2" method="post"
			action="${ctx}/customer/customer/saveAdd.htm?">
			<p>
				<label>企业名称</label> <span class="field"> <input
					value="${unitCustomerPO.enterpriseName }" type="text"
					name="enterpriseName" id="enterpriseName" class="longinput"
					style="width: 350px;" />&nbsp;&nbsp; <input type="hidden"
					name="id" id="id" value="${unitCustomerPO.id }" />
				</span>
			</p>
			<p>
				<label>联系人</label> <span class="field"><input type="text"
					value="${unitCustomerPO.contactPerson }" name="contactPerson"
					class="longinput" style="width: 350px;" />&nbsp;&nbsp;</span>
			</p>
			<p>
				<label>联系电话</label> <span class="field"><input
					value="${unitCustomerPO.contactPhone }" type="text"
					name="contactPhone" class="longinput" id="contactPhone"
					style="width: 350px;" />&nbsp;&nbsp;</span>
			</p>
			<p>
				<label>地址</label> <span class="field"><input type="text"
					value="${unitCustomerPO.address }" name="address" class="longinput"
					style="width: 350px;" />&nbsp;&nbsp;</span>
			</p>
			<p>
				<label>开户银行</label> <span class="field"><input type="text"
					value="${unitCustomerPO.bankType }" name="bankType"
					class="longinput" style="width: 350px;" />&nbsp;&nbsp;</span>
			</p>
			<p>
				<label>银行卡号</label> <span class="field"><input type="text"
					value="${unitCustomerPO.bankCardNO }" name="bankCardNO"
					class="longinput" style="width: 350px;" />&nbsp;&nbsp;</span>
			</p>
		</form>
	</div>
	<div id=customer class="subcontent" style="display: none;">
		<div class="overviewhead">
				<div class="overviewselect"></div>
				手机: &nbsp; <input type="text" id="phone" /> &nbsp; 身份证: &nbsp; <input
					type="text" id="idCard" /> &nbsp; 姓名: &nbsp; <input type="text"
					id="name" />&nbsp;&nbsp;
					<button class="publishbutton radius3" id="customerQueryBtn">查询</button>
			</div>
			<br clear="all" />
			<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
				id="customerTable">
				<colgroup>
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
						<th class="head1">手机号码</th>
						<th class="head0">姓名</th>
						<th class="head1">身份证/驾驶证</th>
						<th class="head0">租赁保证金</th>
						<th class="head1">账户余额</th>
						<th class="head0">状态</th>
						<th class="head1">移除</th>
					</tr>
				</thead>
				<tfoot>
					<tr>
						<th class="head1">手机号码</th>
						<th class="head0">姓名</th>
						<th class="head1">身份证/驾驶证</th>
						<th class="head0">租赁保证金</th>
						<th class="head1">账户余额</th>
						<th class="head0">状态</th>
						<th class="head1">移除</th>
					</tr>
				</tfoot>
				<tbody>
				</tbody>
			</table>
	</div>
</div>
</html>

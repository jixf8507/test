<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>

<script type="text/javascript"
	src="${ctx}/js/page/customer/selectCustomer.js"></script>
</head>

<body>
	<input type="hidden" id="id" value="${param.id}" />
	<div id="contentwrapper" class="contentwrapper"> 
		<div id="normal" class="subcontent" >
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
					<col class="con0" />
					<col class="con1" />
				</colgroup>
				<thead>
					<tr>
						<th class="head0"><input style="width: 20px;"
									type="checkbox" class="checkall" id="all" /></th>
						<th class="head1">手机号码</th>
						<th class="head0">姓名</th>
						<th class="head1">身份证/驾驶证</th>
						<th class="head0">租赁保证金</th>
						<th class="head1">账户余额</th>
						<th class="head0">状态</th>
						<th class="head1">企业</th>
					</tr>
				</thead>
				<tfoot>
					<tr>
						<th class="head0"><input style="width: 20px;"
									type="checkbox" class="checkall" id="all" /></th>
						<th class="head1">手机号码</th>
						<th class="head0">姓名</th>
						<th class="head1">身份证/驾驶证</th>
						<th class="head0">租赁保证金</th>
						<th class="head1">账户余额</th>
						<th class="head0">状态</th>
						<th class="head1">企业</th>
					</tr>
				</tfoot>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>

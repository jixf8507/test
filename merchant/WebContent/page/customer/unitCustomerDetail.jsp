<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript"
	src="${ctx}/js/page/customer/unitCustomerDetail.js"></script>
</head>
<body>
	<ul class="hornav">
		<li class="current"><a href="#normal">基本信息</a></li>
		<li><a href="#account" id="accountDiv">账户信息</a></li>
		<li><a href="#customer" id="customerDiv">用户信息</a></li>
		<li><a href="#trade" id="tradeDiv">交易信息</a></li>
		<li><a href="#freeze" id="freezeDiv">冻结/注销原因</a></li>
		<%-- <c:if test="${customerPO.accountPO.describe != ''}">
		<li><a href="#freeze" id="freezeDiv">冻结/注销原因</a></li>
	</c:if> --%>
	</ul>
	<div id="contentwrapper" class="contentwrapper">
		<!-- #normal -->
		<div id="normal" class="subcontent">
			<form id="form1" class="stdform stdform2">
				<p>
					<label>企业名称</label> <span class="field">${unitCustomerPO.enterpriseName}&nbsp;</span>
				</p>
				<p>
					<label>联系人</label> <span class="field">${unitCustomerPO.contactPerson}&nbsp;</span>
				</p>
				<p>
					<label>联系电话</label> <span class="field">${unitCustomerPO.contactPhone }&nbsp;</span>
				</p>
				<p>
					<label>地址</label> <span class="field">${unitCustomerPO.address}&nbsp;</span>
				</p>
				<p>
					<label>创建时间</label> <span class="field">${unitCustomerPO.createdTime}&nbsp;</span>
				</p>
				<p>
					<label>经办人</label> <span class="field">${unitCustomerPO.transactUser}&nbsp;</span>
				</p>
			</form>
		</div>
		<!-- #normal -->

		<!-- #account -->
		<div id="account" class="subcontent" style="display: none;">
			<form id="form1" class="stdform stdform2">
				<p>
					<label>账户余额</label> <span class="field">${unitCustomerPO.balance}&nbsp;</span>
				</p>
				<p>
					<label>保证金</label> <span class="field">${unitCustomerPO.moneyOfassure}&nbsp;</span>
				</p>
				<p>
					<label>开户银行</label> <span class="field">${unitCustomerPO.bankType}&nbsp;</span>
				</p>
				<p>
					<label>银行卡号</label> <span class="field">${unitCustomerPO.bankCardNO}&nbsp;</span>
				</p>
				<p>
					<label>账户状态</label> <span class="field">${unitCustomerPO.status}&nbsp;</span>
				</p>
			</form>
		</div>
		<!-- #account -->

		<!-- #customer -->
		<div id="customer" class="subcontent" style="display: none;">
			<div class="overviewhead">
				<div class="overviewselect"></div>
				手机: &nbsp; <input type="text" id="phone" /> &nbsp; 身份证: &nbsp; <input
					type="text" id="idCard" /> &nbsp; 姓名: &nbsp; <input type="text"
					id="name" />&nbsp;&nbsp;
					<button class="publishbutton radius3" id="customerQueryBtn">查询</button>
				<button class="publishbutton radius3" id="customerExcelBtn">导出EXCEL</button>
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
				</colgroup>
				<thead>
					<tr>
						<th class="head1">手机号码</th>
						<th class="head0">姓名</th>
						<th class="head1">身份证/驾驶证</th>
						<th class="head0">租赁保证金</th>
						<th class="head1">账户余额</th>
						<th class="head0">状态</th>
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
					</tr>
				</tfoot>
				<tbody>
				</tbody>
			</table>
		</div>
		<!-- #customer -->

		<!-- #trade -->
		<div id="trade" class="subcontent" style="display: none;">
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
				交易日期: &nbsp;<input type="text" id="beginTime" /> &nbsp;到: &nbsp;<input
					type="text" id="endTime" /><br />
				<br /> &nbsp; &nbsp;
				<button class="publishbutton radius3" id="queryBtn">查询</button>
				&nbsp;
				<button class="publishbutton radius3" id="excelBtn">导出EXCEL</button>
				<input type="hidden" id="customerId" name="customerId"
					value="${unitCustomerPO.id}" />
			</div>
			<br clear="all" />
			<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
				id="tradeTable">
				<colgroup>
					<col class="con0" />
					<col class="con1" />
					<col class="con0" />
					<col class="con1" />
					<col class="con0" />
					<col class="con1" />
				</colgroup>
				<thead>
					<tr>
						<th class="head0">交易时间</th>
						<th class="head1">交易金额</th>
						<th class="head0">账户余额</th>
						<th class="head1">交易类型</th>
						<th class="head0">付费方式</th>
						<th class="head1">经办人</th>
					</tr>
				</thead>
				<tfoot>
					<tr>
						<th class="head0">交易时间</th>
						<th class="head1">交易金额</th>
						<th class="head0">账户余额</th>
						<th class="head1">交易类型</th>
						<th class="head0">付费方式</th>
						<th class="head1">经办人</th>
					</tr>
				</tfoot>
				<tbody>
				</tbody>
			</table>
		</div>
		<!-- #trade -->

		<!-- #freeze -->
		<div id="freeze" class="subcontent" style="display: none;">
			<form id="form1" class="stdform stdform2">
				<p>
					<label>冻结/注销原因</label> <span class="field">${unitCustomerPO.describe }&nbsp;</span>
				</p>
				<p>
					<label>操作时间</label> <span class="field"> <c:if
							test="${unitCustomerPO.describe != ''}">
				${unitCustomerPO.deletedTime }
				</c:if> &nbsp;
					</span>
				</p>
				<p>
					<label>经办人</label> <span class="field"> <c:if
							test="${unitCustomerPO.describe != ''}">
				${unitCustomerPO.deleteUser }
				</c:if> &nbsp;
					</span>
				</p>
			</form>
		</div>
		<!-- #freeze -->

	</div>
</body>
</html>

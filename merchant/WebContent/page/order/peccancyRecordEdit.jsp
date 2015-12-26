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
	src="${ctx}/js/plugins/jquery.datetimepicker.js"></script>
<script type="text/javascript"
	src="${ctx}/js/page/order/peccancyRecordAdd.js"></script>
</head>
<body>
	<form id="form1" class="stdform stdform2" method="post">
		<p>
			<label>违章时间</label> <span class="field"><input type="text"
				name="peccancyTime" class="longinput hasDatepicker"
				id="peccancyTime" value="${peccancyRecord.peccancyTime }" />&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>车牌号码</label> <span class="field"> <input type="text"
				name="carNumber" class="longinput" id="carNumber"
				style="width: 200px;" readonly="readonly"
				value="${peccancyRecord.carNumber }" />&nbsp;&nbsp;<input
				type="button" value="点击获取" onclick="getOrder()" />
			</span>
		</p>
		<p>
			<label>租赁订单</label> <span class="field"> <input type="text"
				class="longinput" id="orderNO" style="width: 200px;"
				readonly="readonly" value="ZCDD_${peccancyRecord.orderId }" />&nbsp;&nbsp;
				<input type="hidden" name="orderId" id="orderId" value="${peccancyRecord.orderId }"/>
				<input type="hidden" name="id" value="${peccancyRecord.id }"/>
			</span>
		</p>
		<p>
			<label>取车时间</label> <span class="field"><input type="text"
				readonly="readonly" id="getTime" class="longinput"
				style="width: 200px;" value="${peccancyRecord.schemingGetTime }" />&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>还车时间</label> <span class="field"><input type="text"
				readonly="readonly" id="returnTime" class="longinput"
				style="width: 200px;" value="${peccancyRecord.schemingReturnTime }" />&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>客户姓名</label> <span class="field"><input type="text"
				readonly="readonly" id="customerName" class="longinput"
				style="width: 200px;" value="${peccancyRecord.name }" />&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>手机号码</label> <span class="field"><input type="text"
				readonly="readonly" id="phone" class="longinput"
				style="width: 200px;" value="${peccancyRecord.phone }" />&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>地点</label> <span class="field"><input type="text"
				name="address" id="address" class="longinput" style="width: 200px;"
				value="${peccancyRecord.address }" />&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>违章信息描述</label> <span class="field"><input type="text"
				name="info" id="info" class="longinput" style="width: 200px;"
				value="${peccancyRecord.info }" />&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>处理收费</label> <span class="field"><input type="text"
				name="payCost" id="payCost" class="longinput" style="width: 200px;"
				value="${peccancyRecord.payCost }" />&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>处理状态</label> <span class="field"><select
				data-placeholder="选择处理状态" name="payStatus" id="payStatus"
				class="chzn-select" style="width: 100px;" tabindex="2">
					<option value="未处理"
						<c:if test="${peccancyRecord.payStatus == '未处理' }"> selected="selected" </c:if>>未处理</option>
					<option value="已处理"
						<c:if test="${peccancyRecord.payStatus == '已处理' }"> selected="selected" </c:if>>已处理</option>
			</select></span>
		</p>
	</form>

</body>
</html>

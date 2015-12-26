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
	src="${ctx}/js/page/customer/customerCarsEdite.js"></script>
</head>
<body>
	<form id="form1" class="stdform stdform2" method="post" action="">
		<p>
			<label>车牌号码</label> <span class="field"> <input type="text"
				value="${customerCarsPO.carNumber}" name="carNumber" id="carNumber"
				class="longinput" style="width: 200px;" />&nbsp;&nbsp; <input
				type="hidden" name="id" id="id" value="${customerCarsPO.id}" />
			</span>
		</p>
		<p>
			<label>车牌识别号</label> <span class="field"> <input type="text"
				value="${customerCarsPO.vin}" name="vin" id="vin" class="longinput"
				style="width: 200px;" />&nbsp;&nbsp;
			</span>
		</p>
		<p>
			<label>类型</label> <span class="field"><select id="sex"
				data-placeholder="车辆类型" name="carType" id="carType"
				class="uniformselect" tabindex="2">
					<option value="电动"
						<c:if test="${customerCarsPO.carType =='电动'}">selected="selected"</c:if>>电动</option>
					<option value="燃油"
						<c:if test="${customerCarsPO.carType =='燃油'}">selected="selected"</c:if>>燃油</option>
			</select>&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>手机号码</label> <span class="field"><input type="text" name="phone"
				value="${customerCarsPO.customerPO.phone}" readonly="readonly"
				id="phone" class="longinput" style="width: 200px;" onclick="getCustomer()" />&nbsp;&nbsp;<input
				type="button" value="点击获取" onclick="getCustomer()" /></span> <input
				value="${customerCarsPO.customerId}" type="hidden" name="customerId"
				id="customerId" />
		</p>
		<p>
			<label>客户姓名</label> <span class="field"><input type="text"
				value="${customerCarsPO.customerPO.name}" id="name"
				class="longinput" style="width: 200px;" readonly="readonly" />&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>身份证号</label> <span class="field"><input type="text"
				value="${customerCarsPO.customerPO.idCard}" readonly="readonly"
				id="idCard" class="longinput" style="width: 200px;" />&nbsp;&nbsp;</span>
		</p>

	</form>


</body>
</html>

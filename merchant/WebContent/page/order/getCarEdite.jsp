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
<script type="text/javascript" src="${ctx}/js/page/order/getCarEdite.js"></script>
<!--  
<script type="text/javascript" src="${ctx}/js/page/customer/sendVerCode.js"></script>-->
</head>

<body>
	<form id="form1" class="stdform stdform2" method="post">
		<p>
			<label>车牌号码</label> <span class="field">${orderPO.carPO.carNumber}&nbsp;&nbsp;
				<input type="hidden" name="id" value="${orderPO.id}" /> <input
				type="hidden" name="carId" id="carId" value="${orderPO.carId}" /> <input
				type="hidden" name="kmsForGet" value="${orderPO.kmsForGet}" /><input
				type="hidden" name="surplusKmsForGet" value="${orderPO.surplusKmsForGet}" />
			</span>
		</p>
		<p>
			<label>取车租赁点</label> <span class="field">${orderPO.gsitePO.siteName}&nbsp;&nbsp;
				<input type="hidden" name="relityGetSiteId"
				value="${orderPO.relityGetSiteId}" />
			</span>
		</p>
		<p>
			<label>选择租赁套餐</label> <span class="field">
			<c:if test="${orderPO.id==null}">
					<select name="shortPriceTypeId" id="shortPriceTypeId">
					<option value="">请选择</option>
			</select>&nbsp;&nbsp; 
			<input type="hidden" id="priceTypeId" name="priceTypeId" value="${orderPO.priceTypePO.id}" /> 
			</c:if>
			<c:if test="${orderPO.id!=null}">${orderPO.priceTypePO.typeName}&nbsp;&nbsp; 
			<input type="hidden" id="priceTypeId" name="priceTypeId" value="${orderPO.priceTypeId}" /> 
			</c:if>
			
			<input type="hidden" id="priceTypeName" name="priceTypeName" value="${orderPO.priceTypePO.typeName}" /> 
			<input type="hidden" id="priceTypeTableName" name="priceTypeTableName" value="${orderPO.priceTypeTableName}" /> 
			<input type="hidden" id="pid" value="${orderPO.priceTypeId}" /> </span>
		</p>
		<p>
			<label>取车时间</label> <span class="field"><input type="text"
				name="reaGetTime" id="realityGetTime"
				class="longinput hasDatepicker" /> &nbsp;&nbsp;</span>
		</p>
		<p>
			<label>最迟还车时间</label> <span class="field"><input type="text"
				name="scheReturnTime" id="schemingReturnTime"
				class="longinput hasDatepicker" /> &nbsp;&nbsp;</span>
		</p>
		<p>
			<label>手机号码</label> <span class="field"> <input type="text"
				id="phone" name="phone" class="longinput" 
				value="${orderPO.customerPO.phone}" style="width: 200px;"
				readonly="readonly" />&nbsp;&nbsp; <c:if test="${orderPO.id==null}">
					<input type="button" value="点击获取" onclick="getCustomer()" />
				</c:if>
			</span>
		</p>
		<!--  <p>
			<label>短信验证码</label> <span class="field"> <input
					type="text" name="verCode" id="verCode" class="longinput"
					style="width: 200px;" />&nbsp;&nbsp;<input type="button"
					value="获取验证码" id="sendVerCode"></input> </span>							
		</p> -->				
		<p>
			<label>客户姓名</label> <span class="field"><input type="text"
				id="customerName" class="longinput" style="width: 200px;"
				value="${orderPO.customerPO.name}" readonly="readonly" />&nbsp;&nbsp;
				<input type="hidden" id="customerId" name="customerId"
				value="${orderPO.customerId}" /> </span> 
		</p>
		<c:if test="${orderPO.priceTypeTableName == 'wh_hours_price_type'}">
			<p>
				<label>身份证</label> <span class="field"><input type="text"
					id="idCard" class="longinput" style="width: 200px;"
					value="${orderPO.customerPO.idCard}" readonly="readonly" />&nbsp;&nbsp;</span>
			</p>
		</c:if>
		<p>
			<label>保证金</label> <span class="field"><input type="text"
				id="moneyOfassure" class="longinput" style="width: 200px;"
				value="${orderPO.customerPO.accountPO.moneyOfassure}"
				readonly="readonly" />&nbsp;&nbsp;元</span>
		</p>
		<p>
			<label>账户余额</label> <span class="field"><input type="text"
				id="balance" class="longinput" style="width: 200px;"
				value="${orderPO.customerPO.accountPO.balance}" readonly="readonly" />&nbsp;&nbsp;元</span>
		</p>

	</form>
</body>
</html>

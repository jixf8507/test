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
	src="${ctx}/js/page/otherOrder/getCarEdite.js"></script>
</head>

<body>
	<form id="form1" class="stdform stdform2" method="post">
		<p>
			<label>车牌号码</label> <span class="field">${orderPO.carPO.carNumber}&nbsp;&nbsp;
				<input type="hidden" name="carId" id="carId"
				value="${orderPO.carId}" /> <input type="hidden" name="kmsForGet"
				value="${orderPO.kmsForGet}" /> <input type="hidden"
				name="orderType" value="${orderPO.orderType}" /><input
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
			<label>取车时间</label> <span class="field"><input type="text"
				name="reaGetTime" id="realityGetTime"
				class="longinput hasDatepicker" /> &nbsp;&nbsp;</span>
		</p>
		<p>
			<label>用车事由</label> <span class="field"> <textarea  
				name="orderDescribe" id="orderDescribe" class="longinput"
				style="width: 200px;" ></textarea>&nbsp;&nbsp;
			</span>
		</p>
		<p>
			<label>使用人</label> <span class="field"> <input type="text"
				name="userName" id="userName" class="longinput" readonly="readonly"
				style="width: 200px;" onclick="getMerchantUser()" />&nbsp;&nbsp;<input
				type="button" value="点击获取" onclick="getMerchantUser()" /> <input
				type="hidden" id="userId" name="customerId" value="" /></span>
		</p>
		<p>
			<label>手机号码</label> <span class="field"> <input
				readonly="readonly" type="text" name="mobilePhone" id="mobilePhone"
				class="longinput" style="width: 200px;" />&nbsp;&nbsp;
			</span>
		</p>

	</form>
</body>
</html>

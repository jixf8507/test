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
	src="${ctx}/js/page/customer/customerCarsAdd.js"></script>
</head>

<body>
					<form id="form1" class="stdform stdform2" method="post" action="">
						<p>
							<label>车牌号码</label> <span class="field"> <input
								type="text" name="carNumber" id="carNumber" class="longinput"
								style="width: 200px;" />&nbsp;&nbsp;
							</span>
						</p>
						<p>
							<label>车牌识别号</label> <span class="field"> <input
								type="text" name="vin" id="vin" class="longinput"
								style="width: 200px;" />&nbsp;&nbsp;
							</span>
						</p>
						<p>
							<label>类型</label> <span class="field"><select id="sex"
								data-placeholder="车辆类型" name="carType" id="carType"
								class="uniformselect" tabindex="2">
									<option value="电动">电动</option>
									<option value="燃油">燃油</option>
							</select>&nbsp;&nbsp;</span>
						</p>
						<p>
							<label>手机号码</label> <span class="field"><input type="text" name="phone"
								readonly="readonly" id="phone"  class="longinput" onclick="getCustomer()"
								style="width: 200px;" />&nbsp;&nbsp;<input
								type="button" value="点击获取" onclick="getCustomer()" /></span> <input
								type="hidden" name="customerId" id="customerId" />
						</p>
						<p>
							<label>客户姓名</label> <span class="field"><input type="text"
								id="name" class="longinput" style="width: 200px;"
								readonly="readonly" />&nbsp;&nbsp;</span>
						</p>
						<p>
							<label>身份证号</label> <span class="field"><input type="text"
								readonly="readonly" id="idCard" class="longinput"
								style="width: 200px;" />&nbsp;&nbsp;</span>
						</p> 
					</form>
</body>
</html>

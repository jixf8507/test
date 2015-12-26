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
	src="${ctx}/js/page/customer/unitCustomerAdd.js"></script>
<%-- <script type="text/javascript"
	src="${ctx}/js/page/customer/sendVerCode.js"></script> --%>
</head>
<div id="contentwrapper" class="contentwrapper">
	<div id="list" class="subcontent">
		<form id="form1" class="stdform stdform2" method="post">
			<p>
				<label>企业名称</label> <span class="field"> <input type="text"
					name="enterpriseName" id="enterpriseName" class="longinput"
					style="width: 350px;" />&nbsp;&nbsp;
				</span>
			</p>
			<p>
				<label>联系人</label> <span class="field"><input type="text"
					name="contactPerson" class="longinput" style="width: 350px;" />&nbsp;&nbsp;</span>
			</p>
			<p>
				<label>联系电话</label> <span class="field"><input type="text"
					name="contactPhone" class="longinput" id="contactPhone"
					style="width: 350px;" />&nbsp;&nbsp;</span>
			</p>
			<p>
				<label>地址</label> <span class="field"><input type="text"
					name="address" class="longinput" style="width: 350px;" />&nbsp;&nbsp;</span>
			</p>
			<p>
				<label>开户银行</label> <span class="field"><input type="text"
					name="bankType" class="longinput" style="width: 350px;" />&nbsp;&nbsp;</span>
			</p>
			<p>
				<label>银行卡号</label> <span class="field"><input type="text"
					name="bankCardNO" class="longinput" style="width: 350px;" />&nbsp;&nbsp;</span>
			</p>

		</form>
	</div>
</div>
</html>

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
	src="${ctx}/js/page/car/carDeviceSimEdite.js"></script>
</head>

<div id="list" class="subcontent">
	<form id="form1" class="stdform stdform2" method="post">
		<p>
			<label>车载设备</label> <span class="field"> ${simPO.carDevice.deviceNO }&nbsp;
			</span>
		</p>
		<p>
			<label>设备卡号（手机号）</label> <span class="field"><input
				type="text" name="simCard" id="simCard" class="longinput"
				value="${simPO.simCard }" style="width: 200px;" /></span>
		</p>
		<input type="hidden" id="id" name="id" value="${simPO.id }" />
		<p>
			<label>运营商</label> <span class="field"> <select
				name="facilitator" id="facilitator" class="chzn-select"
				style="width: 200px;">
					<option value="">请选择</option>
					<option value="移动"
						<c:if test="${simPO.facilitator == '移动'}">selected="selected"</c:if>>移动</option>
					<option value="电信"
						<c:if test="${simPO.facilitator == '电信'}">selected="selected"</c:if>>电信</option>
					<option value="联通"
						<c:if test="${simPO.facilitator == '联通'}">selected="selected"</c:if>>联通</option>

			</select>
			</span>
		</p>
		<p>
			<label>月流量</label> <span class="field"><input
				value="${simPO.flowOfMonth }" type="text" name="flowOfMonth"
				id="flowOfMonth" class="longinput" style="width: 200px;" /></span>
		</p>
		<p>
			<label>序列号</label> <span class="field"><input type="text"
				name="iccid" id="iccid" class="longinput" value="${simPO.iccid }"
				style="width: 200px;" /></span>
		</p>

	</form>
	<br />
</div>
</html>

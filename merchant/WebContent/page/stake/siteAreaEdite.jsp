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
	src="${ctx}/js/page/stake/siteAreaEdite.js"></script>
</head>

<body>

	<form id="form1" class="stdform stdform2" method="post" action="">
		<input type="hidden" name="id" id="id" value="${areaPO.id}" /> <input
			type="hidden" id="hid_areaCode" value="${areaPO.areaCode}" /> <input
			type="hidden" id="hid_siteCode" value="${areaPO.siteCode}" />
			<p>
			<label>选择充电站</label><span class="field"> <select
				style="width: 200px;" class="chzn-select" name="siteCode"
				id="siteCode">
					<option value="">请选择</option>
			</select>
			</span>
		</p>
		<p>
			<label>区域编号</label> <span class="field"><select
				style="width: 200px;" class="chzn-select" name="areaCode"
				id="areaCode">
					<option value="">请选择</option>
			</select> <input type="hidden" id="areaCodeId" value="${areaPO.areaCode}" /> </span>
		</p>
		<p>
			<label>区域名称</label> <span class="field"> <input type="text"
				style="width: 200px;" name="areaName" id="areaName"
				value="${areaPO.areaName}" class="longinput" />&nbsp;&nbsp;
			</span>
		</p>
		

	</form>

</body>
</html>

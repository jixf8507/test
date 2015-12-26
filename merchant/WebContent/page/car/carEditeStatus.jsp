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
<script type="text/javascript" src="${ctx}/js/page/car/carEditeStatus.js"></script>
</head>

<body>

	<form id="form1" class="stdform stdform2" method="post" action=""
		enctype="multipart/form-data">
		<input type="hidden" name="id" id="id" value="${carPO.id}" /> <input
			type="hidden" id="hid_carTypeId" value="${carPO.carTypeId}" /> <input
			type="hidden" id="hid_carColor" value="${carPO.color}" /> <input
			type="hidden" id="hid_curSiteId" value="${carPO.curSiteId}" />
			<input
			type="hidden" id="hid_carStatus" value="${carPO.status}" />
		<p>
			<label>车状态</label> <span class="field" id="statusDiv"> <select
				name="status" id="carStatus" style="width: 200px;">
					<option value="">请选择</option>
			</select>
			</span>
		</p>
		
		<p>
			<label>原因</label> <span class="field"><%-- <input type="text"
				value="${carPO.describe}" name="describe" id="describe"
				class="longinput" /> --%>
				<textarea name="describe" id="describe" ></textarea>
				</span>
				
		</p>		
	
	</form>


</body>
</html>

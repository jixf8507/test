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
<script type="text/javascript" src="${ctx}/js/page/work/releaseAdd.js"></script>
<script type="text/javascript"
	src="${ctx}/js/page/work/workorderUpdate.js"></script>
<script type="text/javascript"
	src="${ctx}/js/plugins/jquery.datetimepicker.js"></script>
</head>

<body>
	<form id="form1" class="stdform stdform2">
		<p>
			<input type="hidden" name="id" id="id" value="${workOrderPO.id}" />
			<input type="hidden" name="userId" id="userId"
				value="${workOrderPO.userId}" />
		</p>
		<p>
			<label>工单名称</label> <span class="field"><input type="text"
				value="${workOrderPO.workName}" name="workName" id="workName"
				class="longinput" style="width: 200px;" />&nbsp;&nbsp; </span>
		</p>
		<p>
			<label>工单描述</label> <span class="field"><input type="text"
				value="${workOrderPO.workDes}" name="workDes" id="workDes"
				class="longinput" style="width: 200px;" />&nbsp;&nbsp; </span>
		</p>
		<p>
			<label>紧急程度</label> <span class="field"><select
			    name="urgency" id="urgency"
				class="chzn-select" style="width: 200px;">
					<option value="">请选择工单状态</option>
						<option value="一般"
							<c:if test="${workOrderPO.urgency=='一般'}">selected="selected"</c:if>>一般</option>
						<option value="紧急"
							<c:if test="${workOrderPO.urgency=='紧急'}">selected="selected"</c:if>>紧急</option>
						<option value="非常紧急"
							<c:if test="${workOrderPO.urgency=='非常紧急'}">selected="selected"</c:if>>非常紧急</option>
			</select></span>
		</p>
		<p>
			<label>工单类型</label> <span class="field"> <input type="text"
				value="${workOrderPO.typeName}" readonly="readonly" name="typeName"
				id="typeName" class="longinput" style="width: 200px;" />&nbsp;&nbsp;
				<input type="button" value="点击选择" onclick="getWorkType()"></input></span>
		</p>

		<p>
			<label>负责人</label> <span class="field"><input type="text"
				value="${workOrderPO.merchantUserPO.userName}" readonly="readonly"
				name="userName" id="userName" class="longinput"
				style="width: 200px;" />&nbsp;&nbsp; </span>
		</p>

		<p>
			<label>负责人手机</label> <span class="field"><input type="text"
				value="${workOrderPO.merchantUserPO.mobilePhone}"
				readonly="readonly" name="userName" id="userName" class="longinput"
				style="width: 200px;" />&nbsp;&nbsp; </span>
		</p>
		<p>
			<label>发布时间</label> <span class="field"><input type="text"
				value="${workOrderPO.createdTime}" name="created" id="created"
				style="width: 200px;" />&nbsp;&nbsp; </span>
		</p>
		<p>
			<label>发布人</label> <span class="field"><input type="text"
				value="${workOrderPO.transactUser}" name="transactUser"
				id="transactUser" class="longinput" style="width: 200px;" />&nbsp;&nbsp;
			</span>
		</p>



	</form>
</body>
</html>

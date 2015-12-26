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
</head>

<body>
	<form id="form1" class="stdform stdform2" method="post">
		<p>
			<label>工单名称</label> <span class="field"><input type="text"
				name="workName" id="workName" class="longinput"
				style="width: 200px;" /></span>
		</p>
		<p>
			<label>工单描述</label> <span class="field"><textarea
					style="width: 200px;" id="workDes" name="workDes"></textarea></span>
		</p>
		<p>
			<label>紧急程度</label> <span class="field"><select id="urgency"
				name="urgency" class="chzn-select" style="width: 200px;">
					<option value="">请选择紧急程度</option>
					<option value="一般">一般</option>
					<option value="紧急">紧急</option>
					<option value="非常紧急">非常紧急</option>
			</select></span>
		</p>
		<p>
			<label>工单类型选择</label> <span class="field"><input type="text"
				name="typeName" id="typeName" class="longinput" readonly="readonly"
				style="width: 200px;" onclick="getWorkType()" />&nbsp;&nbsp; <input
				type="button" value="点击选择" onclick="getWorkType()"></input> </span>
		</p>
		<p>
			<label>类型描述</label> <span class="field"><textarea
					style="width: 200px;" id="typeDes" name="typeDes" readonly="readonly"></textarea></span>
		</p>
		<p>
			<label>负责人</label> <span class="field"><input type="text"
				name="userName" id="userName" class="longinput" readonly="readonly"
				style="width: 200px;" /> <input type="hidden" name="userId"
				id="userId" /> </span>
		</p>
		<p>
			<label>负责人电话</label> <span class="field"><input type="text"
				name="mobilePhone" id="mobilePhone" class="longinput" readonly="readonly"
				style="width: 200px;" /></span>
		</p>

	</form>
</body>
</html>

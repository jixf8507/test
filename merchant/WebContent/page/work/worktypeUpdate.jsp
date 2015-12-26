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
<script type="text/javascript"src="${ctx}/js/page/work/worktypeUpdate.js"></script>
<script type="text/javascript"src="${ctx}/js/page/work/workTypeAdd.js"></script>
</head>

<body>
	<form id="form1" class="stdform stdform2">

		<p>
			<label>工单类型名称</label> <span class="field"> <input type="text"
				value="${worktypePO.typeName}" name="typeName" id="typeName"
				class="longinput" style="width: 200px;" />&nbsp;&nbsp;
			</span>
		</p>
			<input type="hidden" name="id" id="id" value="${worktypePO.id}" />
			<input type="hidden" value="${worktypePO.userId}" name="userId" id="userId" />
		<p>
			<label>负责人</label> <span class="field"><input type="text"
				value="${worktypePO.merchantUserPO.userName}" readonly="readonly"  id="userName"
				class="longinput"  style="width: 200px;" />&nbsp;&nbsp;
				<input type="button" value="选择负责人" onclick="selectUser()" /> </span>
		</p>
		<p>
			<label>负责人手机</label> <span class="field"> <input type="text"
				value="${worktypePO.merchantUserPO.mobilePhone}" readonly="readonly" name="mobilePhone" id="mobilePhone"
				class="longinput" style="width: 200px;" />&nbsp;&nbsp;
			</span>
		</p>
		
		
		<p>
			<label>类型描述</label> <span class="field"> <textarea rows="8"
					cols="10" name="workDes" id="workDes">${worktypePO.workDes}</textarea>&nbsp;&nbsp;
			</span>
		</p>

	</form>
</body>
</html>

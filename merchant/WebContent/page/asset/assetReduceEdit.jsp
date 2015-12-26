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
	src="${ctx}/js/page/asset/assetReduceEdit.js"></script>
</head>

<form id="form1" class="stdform stdform2" method="post">

	<p>
		<label>处置日期</label> <span class="field"> <input
			value="${assetReducePO.reduceDate }" type="text" name="reduceDate"
			id="reduceDate" class="longinput" style="width: 200px;" />&nbsp;&nbsp;
		</span>
	</p>
	<p>
		<label>处置原因</label> <span class="field"> <input
			value="${assetReducePO.reduceReason }" type="text"
			name="reduceReason" id="reduceReason" class="longinput"
			style="width: 200px;" />&nbsp;&nbsp;
		</span>
	</p>
	<p>
		<label>处置方式选择</label> <span class="field"> <select
			name="reduceStatus" id="reduceStatus">
				<option value="">请选择</option>
				<option value="销毁"
					<c:if test="${assetReducePO.reduceStatus=='销毁' }">selected="selected"</c:if>>销毁</option>
				<option value="出售"
					<c:if test="${assetReducePO.reduceStatus=='出售' }">selected="selected"</c:if>>出售</option>
		</select>
		</span>
	</p>
	<p>
		<label>申请人</label> <span class="field"> <input
			value="${assetReducePO.applyUser }" type="text" name="applyUser"
			id="applyUser" class="longinput" style="width: 200px;" />&nbsp;&nbsp;
		</span>
	</p>
	<p>
		<label>备注说明</label> <span class="field"> <input
			value="${assetReducePO.remarks }" type="text" name="remarks"
			id="remarks" class="longinput" style="width: 200px;" />&nbsp;&nbsp;
		</span>
	</p>
	<input type="hidden" id="id" name="id" value="${assetReducePO.id }" />
	<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
		id="reduceDetailTable">
		<colgroup>
			<col class="con1" />
			<col class="con0" />
			<col class="con1" />
			<col class="con0" />
			<col class="con1" />
		</colgroup>
		<thead>
			<tr>
				<th class="head1">资产编号</th>
				<th class="head0">资产名称</th>
				<th class="head1">资产类型</th>
				<th class="head0">规格型号</th>
				<th class="head1">计量方式</th>
			</tr>
		</thead>
		<tfoot>
			<tr>
				<th class="head1">资产编号</th>
				<th class="head0">资产名称</th>
				<th class="head1">资产类型</th>
				<th class="head0">规格型号</th>
				<th class="head1">计量方式</th>
			</tr>
		</tfoot>
		<tbody>
		</tbody>
	</table>
</form>
</html>

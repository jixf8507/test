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
	src="${ctx}/js/page/asset/assetUseEdit.js"></script>
</head>


	<form id="form1" class="stdform stdform2" method="post">

		<p>
			<label>领用日期</label> <span class="field"> <input
				value="${assetUsePO.useDate }" type="text" name="useDate"
				id="useDate" class="longinput" style="width: 350px;" />&nbsp;&nbsp;
			</span>
		</p>
		<p>
			<label>领入点</label> <span class="field"> <select id="siteId"
				name="siteId" class="chzn-select" style="width: 200px;" tabindex="2">
					<option value="">请选择</option>
			</select>
			</span>
		</p>
		<p>
			<label>领用人</label> <span class="field"> <input
				value="${assetUsePO.userName }" type="text" name="userName"
				id="userName" class="longinput" style="width: 200px;"
				onclick="getMerchantUser()" />&nbsp;&nbsp;<input type="button"
				value="点击获取" onclick="getMerchantUser()" /> <input type="hidden"
				id="userId" name="userId" value="${assetUsePO.userId }" />
			</span>
		</p>
		<p>
			<label>备注说明</label> <span class="field"> <input
				value="${assetUsePO.remarks }" type="text" name="remarks"
				id="remarks" class="longinput" style="width: 200px;" />&nbsp;&nbsp;
			</span>
		</p>
			<input type="hidden" value="${assetUsePO.siteId }" id="hid_siteId" />
			<input type="hidden" id="id" name="id" value="${assetUsePO.id }" />
		
		<!-- <div class="tableoptions">
		 <button class="publishbutton radius3" title="归还" id="operateBtn">归还</button> 
		</div> -->
			<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
				id="useDetailTable">
				<colgroup>
					<col class="con1" />
					<col class="con0" />
					<col class="con1" />
					<col class="con0" />
					<col class="con1" />
					<col class="con0" />
				</colgroup>
				<thead>
					<tr>
						<!-- <th class="head1 "><input style="width: 20px;"
							type="checkbox" class="checkall" id="all" /></th> -->
						<th class="head1">资产编号</th>
						<th class="head0">资产名称</th>
						<th class="head1">资产类型</th>
						<th class="head0">规格型号</th>
						<th class="head1">计量方式</th>
						<th class="head0">资产状态</th>
						<th class="head1">操作</th>
					</tr>
				</thead> 
				<tfoot>
					<tr>
						<!-- <th class="head1 "><input style="width: 20px;"
							type="checkbox" class="checkall" id="all" /></th> -->
						<th class="head1">资产编号</th>
						<th class="head0">资产名称</th>
						<th class="head1">资产类型</th>
						<th class="head0">规格型号</th>
						<th class="head1">计量方式</th>
						<th class="head0">资产状态</th>
						<th class="head1">操作</th>
					</tr>
				</tfoot>
				<tbody>
				</tbody>
			</table>
			</form>
<div class="tableoptions" style="float: right;">
	<button class="publishbutton radius3" title="归还全部" id="returnAllBtn">归还全部</button>
</div>

</html>

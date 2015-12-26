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
	src="${ctx}/js/page/asset/assetRepairEdit.js"></script>
</head>


	<form id="form1" class="stdform stdform2" method="post">

		<p>
			<label>送修日期</label> <span class="field"> <input type="text"
				name="repairDate" id="repairDate" class="longinput"
				value="${assetRepairPO.repairDate }" style="width: 200px;" />&nbsp;&nbsp;
			</span>
		</p>
		<p>
			<label>维修原因</label> <span class="field"> <input type="text"
				name="repairReason" id="repairReason" class="longinput"
				value="${assetRepairPO.repairReason }" style="width: 200px;" />&nbsp;&nbsp;
			</span>
		</p>
		<p>
			<label>维修商选择</label> <span class="field"> <select
				name="manufacturerId" id="manufacturId">
					<option value="">请选择</option>
			</select> <input type="hidden" id="hid_manufacturId"
				value="${assetRepairPO.manufacturerId }" />
			</span>
		</p>
		<p>
			<label>申请人</label> <span class="field"> <input type="text"
				name="applyUser" id="applyUser" class="longinput"
				value="${assetRepairPO.applyUser }" style="width: 200px;" />&nbsp;&nbsp;
			</span>
		</p>
		<p>
			<label>维修费用</label> <span class="field"> <input type="text"
				value="${assetRepairPO.fee }" name="fee" id="fee" class="longinput"
				style="width: 200px;" />&nbsp;&nbsp;
			</span>
		</p>
		<p>
			<label>备注说明</label> <span class="field"> <input type="text"
				value="${assetRepairPO.remarks }" name="remarks" id="remarks"
				class="longinput" style="width: 200px;" />&nbsp;&nbsp;
			</span>
		</p>
		<p>
			<input type="hidden" value="" id="assetIds" name="assetIds" /> <input
				type="hidden" id="id" name="id" value="${assetRepairPO.id }" />

			<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
				id="repairDetailTable">
				<colgroup>
					<col class="con1" />
					<col class="con0" />
					<col class="con1" />
					<col class="con0" />
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
						<th class="head1">送修原因</th>
						<th class="head0">维修状况</th>
						<th class="head1">维修费用</th>
						<th class="head0">配件名称</th>
						<th class="head1">操作</th>
					</tr>
				</thead>
				<tfoot>
					<tr>
						<th class="head1">资产编号</th>
						<th class="head0">资产名称</th>
						<th class="head1">资产类型</th>
						<th class="head0">规格型号</th>
						<th class="head1">送修原因</th>
						<th class="head0">维修状况</th>
						<th class="head1">维修费用</th>
						<th class="head0">配件名称</th>
						<th class="head1">操作</th>
					</tr>
				</tfoot>
				<tbody>
				</tbody>
			</table>
		</p>
	</form>
</html>

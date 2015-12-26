<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript"
	src="${ctx}/js/page/asset/assetRepairDetail.js"></script>
</head>
<body>
	<form id="form1" class="stdform stdform2" method="post">

		<p>
			<label>送修日期</label> <span class="field">${assetRepairPO.repairDate }&nbsp;
			</span>
		</p>
		<p>
			<label>维修原因</label> <span class="field">${assetRepairPO.repairReason }&nbsp;
			</span>
		</p>
		<p>
			<label>维修商选择</label> <span class="field">${assetRepairPO.manufacturerPO.fullName }&nbsp;
			</span>
		</p>
		<p>
			<label>申请人</label> <span class="field">${assetRepairPO.applyUser }&nbsp;
			</span>
		</p>
		<p>
			<label>维修费用</label> <span class="field">${assetRepairPO.fee }&nbsp;
			</span>
		</p>
		<p>
			<label>备注说明</label> <span class="field">${assetRepairPO.remarks }&nbsp;
			</span>
		</p>
	</form>
	<div id="trade" class="subcontent">
		<div class="overviewhead">
			<div class="overviewselect">
				<select id="categoryId" name="categoryId" class="chzn-select"
					style="width: 200px;" tabindex="2">
					<option value="">请选择</option>
				</select>
			</div>
			资产名称: &nbsp;<input type="text" id="assetsName" /> &nbsp; &nbsp;
			<button class="publishbutton radius3" id="queryBtn">查询</button>
			&nbsp;
			<button class="publishbutton radius3" id="excelBtn">导出EXCEL</button>
		</div>
		<input type="hidden" id="id" value="${assetRepairPO.id }" />
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
				</tr>
			</tfoot>
			<tbody>
			</tbody>
		</table>
	</div>

</body>
</html>

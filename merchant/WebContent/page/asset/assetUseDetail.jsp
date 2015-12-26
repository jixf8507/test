<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript"
	src="${ctx}/js/page/asset/assetUseDetail.js"></script>
</head>
<body>
	<form id="form1" class="stdform stdform2" method="post">

		<p>
			<label>领用日期</label> <span class="field">${assetUsePO.useDate }&nbsp;
			</span>
		</p>
		<p>
			<label>领入点</label> <span class="field">${assetUsePO.siteName }&nbsp;
			</span>
		</p>
		<p>
			<label>领用人</label> <span class="field">${assetUsePO.userName }&nbsp;
			</span>
		</p>
		<p>
			<label>备注说明</label> <span class="field">${assetUsePO.remarks }&nbsp;
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
		<input type="hidden" id="id" value="${assetUsePO.id }" />
		<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
			id="useDetailTable">
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
					<th class="head0">资产状态</th>
				</tr>
			</thead>
			<tfoot>
				<tr>
					<th class="head1">资产编号</th>
					<th class="head0">资产名称</th>
					<th class="head1">资产类型</th>
					<th class="head0">规格型号</th>
					<th class="head1">计量方式</th>
					<th class="head0">资产状态</th>
				</tr>
			</tfoot>
			<tbody>
			</tbody>
		</table>
	</div>
		

</body>
</html>

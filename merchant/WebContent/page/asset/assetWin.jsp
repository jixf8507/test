<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>

<script type="text/javascript" src="${ctx}/js/page/asset/assetWin.js"></script>
</head>

<body>
	<div class="overviewhead">
		<div class="overviewselect">
			<select id="categoryId" name="categoryId" class="chzn-select"
				style="width: 200px;" tabindex="2">
				<option value="">请选择</option>
			</select>
		</div>
		资产名称: &nbsp;<input type="text" id="assetsName" /> &nbsp;
		<button class="publishbutton radius3" id="queryBtn">查询</button>
	</div>
	<br clear="all" />
	<input type="hidden" value="${param.assetIds }" id="assetIds"
		name="assetIds" />
	<input type="hidden"
		value="<%String x = request.getParameter("flag");
			x = new String(x.getBytes("iso-8859-1"), "utf-8");
			out.print(x);%>"
		id="flag" name="flag" />
	<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
		id="assetsTable">
		<colgroup>
			<col class="con0" width="10%" />
			<col class="con1" />
			<col class="con0" />
			<col class="con1" />
			<col class="con0" />
			<col class="con1" />
		</colgroup>
		<thead>
			<tr>
				<th class="head0">选择</th>
				<th class="head1">资产名称</th>
				<th class="head0">资产类型</th>
				<th class="head1">规格型号</th>
				<th class="head0">供应商</th>
				<th class="head1">资产价值</th>
			</tr>
		</thead>
		<tfoot>
			<tr>
				<th class="head0">选择</th>
				<th class="head1">资产名称</th>
				<th class="head0">资产类型</th>
				<th class="head1">规格型号</th>
				<th class="head0">供应商</th>
				<th class="head1">资产价值</th>
			</tr>
		</tfoot>
		<tbody>

		</tbody>
	</table>
</body>
</html>

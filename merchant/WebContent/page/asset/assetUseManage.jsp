<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript" src="${ctx}/js/page/asset/assetUseManage.js"></script>
</head>

<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />

		<div class="centercontent">

			<div class="pageheader">
				<h1 class="pagetitle">资产领用信息列表</h1>
			</div>
			<div id="contentwrapper" class="contentwrapper">

				<div id="normal" class="subcontent">
					<div class="overviewhead">
						<div class="overviewselect">
							<select id="siteId" name="siteId" class="chzn-select"
								style="width: 200px;" tabindex="2">
								<option value="">请选择</option>
							</select> &nbsp;
							<select id="flag" name="flag" class="chzn-select"
								style="width: 200px;" tabindex="2">
								<option value="">请选择领用状态</option>
								<option value="在用">在用</option>
								<option value="归还">归还</option>
							</select>
						</div>
						领用日期: &nbsp;<input type="text" id="beginTime" />&nbsp;&nbsp;到:
						&nbsp;&nbsp;&nbsp;<input type="text" id="endTime" /> <br/><br/>
						&nbsp;&nbsp;领用人: &nbsp; &nbsp;<input type="text" id="userName" style="width: 200px;" /> &nbsp; &nbsp;&nbsp; &nbsp;
						<button class="publishbutton radius3" id="queryBtn">查询</button> &nbsp; &nbsp;
						<button class="publishbutton radius3" id="excelBtn">导出EXCEL</button>
					</div>
					<!--overviewhead-->

					<br clear="all" /> 
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="assetUseTable">
						<colgroup>
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
								<th class="head0">单据号</th>
								<th class="head1">领用日期</th>
								<th class="head0">领用人</th>
								<th class="head1">存放地点</th>
								<th class="head0">经办人</th>
								<th class="head1">状态</th>
								<th class="head0">备注</th>
								<th class="head1">操作</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">单据号</th>
								<th class="head1">领用日期</th>
								<th class="head0">领用人</th>
								<th class="head1">存放地点</th>
								<th class="head0">经办人</th>
								<th class="head1">状态</th>
								<th class="head0">备注</th>
								<th class="head1">操作</th>
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>
					<br /> <br />
				</div>
			</div>
		</div>
	</div>
</body>
</html>

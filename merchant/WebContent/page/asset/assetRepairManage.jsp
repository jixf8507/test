<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript" src="${ctx}/js/page/asset/assetRepairManage.js"></script>
</head>

<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />

		<div class="centercontent">

			<div class="pageheader">
				<h1 class="pagetitle">资产维修信息列表</h1>
			</div>
			<div id="contentwrapper" class="contentwrapper">

				<div id="normal" class="subcontent">
					<div class="overviewhead">
						送修日期: &nbsp;<input type="text" id="beginTime" />&nbsp;&nbsp;&nbsp;&nbsp;到:
						&nbsp;&nbsp;&nbsp;<input type="text" id="endTime" />  
						&nbsp;&nbsp;申请人: &nbsp;<input type="text" id="applyUser" style="width: 200px;" /> &nbsp; &nbsp;&nbsp;
						<button class="publishbutton radius3" id="queryBtn">查询</button> &nbsp; &nbsp;
						<button class="publishbutton radius3" id="excelBtn">导出EXCEL</button>
					</div>
					<!--overviewhead--> 

					<br clear="all" /> 
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="assetRepairTable">
						<colgroup>
							<col class="con0" />
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
								<th class="head0">单据号</th>
								<th class="head1">送修日期</th>
								<th class="head0">维修原因</th>
								<th class="head1">维修商</th>
								<th class="head0">申请人</th>
								<th class="head1">经办人</th>
								<th class="head0">维修费用</th>
								<th class="head1">备注</th>
								<th class="head0">操作</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">单据号</th>
								<th class="head1">送修日期</th>
								<th class="head0">维修原因</th>
								<th class="head1">维修商</th>
								<th class="head0">申请人</th>
								<th class="head1">经办人</th>
								<th class="head0">维修费用</th>
								<th class="head1">备注</th>
								<th class="head0">操作</th>
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

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript" src="${ctx}/js/page/asset/assetManage.js"></script>
</head>

<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />

		<div class="centercontent">

			<div class="pageheader">
				<h1 class="pagetitle">资产信息列表</h1>
			</div>
			<div id="contentwrapper" class="contentwrapper">

				<div id="normal" class="subcontent">
					<div class="overviewhead">
						<div class="overviewselect">
							<select id="categoryId" name="categoryId" class="chzn-select"
								style="width: 200px;" tabindex="2">
								<option value="">请选择</option>
							</select> &nbsp;
							<select id="flag" name="flag" class="chzn-select"
								style="width: 200px;" tabindex="2">
								<option value="">请选择资产状态</option>
								<option value="闲置">闲置</option>
								<option value="在用">在用</option>
								<option value="出售">出售</option>
								<option value="销毁">销毁</option>
							</select>
						</div>
						购置日期: &nbsp;<input type="text" id="beginTime" />&nbsp; &nbsp;&nbsp;到:
						&nbsp;&nbsp;&nbsp;<input type="text" id="endTime" /> <br/><br/>
						资产名称: &nbsp;<input type="text" id="assetsName" style="width: 200px;" /> &nbsp; &nbsp;&nbsp; &nbsp;
						<button class="publishbutton radius3" id="queryBtn">查询</button> &nbsp; &nbsp;
						<button class="publishbutton radius3" id="excelBtn">导出EXCEL</button>
					</div>
					<!--overviewhead-->

					<br clear="all" />
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="assetTable">
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
							<col class="con0" />
							<col class="con1" />
						</colgroup>
						<thead>
							<tr>
								<th class="head1">资产编号</th>
								<th class="head0">资产名称</th>
								<th class="head1">资产类型</th>
								<th class="head0">规格型号</th>
								<th class="head1">供应商</th>
								<th class="head0">购置日期</th>
								<th class="head1">增加方式</th>
								<th class="head0">经办人</th>
								<th class="head1">资产价值</th>
								<th class="head0">资产状态</th> 
								<th class="head1">更多操作</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head1">资产编号</th>
								<th class="head0">资产名称</th>
								<th class="head1">资产类型</th>
								<th class="head0">规格型号</th>
								<th class="head1">供应商</th>
								<th class="head0">购置日期</th>
								<th class="head1">增加方式</th>
								<th class="head0">经办人</th>
								<th class="head1">资产价值</th>
								<th class="head0">资产状态</th>
								<th class="head1">更多操作</th>
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>

					<br /> <br />

				</div>
				<!-- #use -->

			</div>
			<!--contentwrapper-->
		</div>
		<!-- centercontent -->
	</div>
	<!--bodywrapper-->
</body>
</html>

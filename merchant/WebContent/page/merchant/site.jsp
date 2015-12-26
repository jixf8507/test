<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript" src="${ctx}/js/page/merchant/site.js"></script>
</head>
<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />
		<div class="centercontent">
			<div class="pageheader">
				<h1 class="pagetitle">服务中心管理</h1>
			</div>
			<!--pageheader-->
			<div id="contentwrapper" class="contentwrapper">
				<div id="list" class="subcontent">
					<div class="overviewhead">
						<div class="overviewselect" id="cityDiv">
							<select data-placeholder="选择" name="city" id="city"
								class="chzn-select" style="width: 200px;" tabindex="2">
								<option value=""></option>
							</select>
						</div>
						<div class="overviewselect">
							<select data-placeholder="选择" name="province" id="province"
								class="chzn-select" style="width: 200px;" tabindex="2">
								<option value=""></option>
							</select>
						</div>
						<!--floatright-->
						名称: &nbsp;<input type="text" id="querySiteName" />&nbsp; &nbsp; 
						<button class="publishbutton radius3" id="queryBtn">查询</button>&nbsp; 
						<button class="publishbutton radius3" id="excelBtn">导出EXCEL</button>
					</div>
					
					<!-- <div class="overviewhead">
						<button class="publishbutton radius3" id="addBtn">新增服务中心</button>
					</div> -->
					
					<!--overviewhead-->
					<br clear="all" />
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="sitesTable">
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
								<th class="head1">图片</th>
								<th class="head0">服务中心名称</th>
								<th class="head1">具体地址</th>
								<th class="head0">负责人</th>
								<th class="head1">联系电话</th>
								<th class="head0">省</th>
								<th class="head1">市</th>
								<th class="head0">经度</th>
								<th class="head1">纬度</th>
								<th class="head0">类型</th>
								<th class="head1">操作</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head1">图片</th>
								<th class="head0">服务中心名称</th>
								<th class="head1">具体地址</th>
								<th class="head0">负责人</th>
								<th class="head1">联系电话</th>
								<th class="head0">省</th>
								<th class="head1">市</th>
								<th class="head0">经度</th>
								<th class="head1">纬度</th>
								<th class="head0">类型</th>
								<th class="head1">操作</th>
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>
					<br /> <br />
				</div>
			</div>
			<!--contentwrapper-->
			<br clear="all" />
		</div>
		<!-- centercontent -->
	</div>
	<!--bodywrapper-->

</body>
</html>

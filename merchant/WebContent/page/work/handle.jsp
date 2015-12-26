<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript" src="${ctx}/js/page/work/handle.js"></script>
</head>
<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />
		<div class="centercontent">
			<div class="pageheader">
				<h1 class="pagetitle">工单处理列表</h1>
				<span class="pagedesc"></span>
				<ul class="hornav">
					<li class="current"><a href="#waitHandle" id="waitDiv">待处理</a></li>
					<li><a href="#onHandle" id="onDiv">处理中</a></li>
					<li><a href="#overHandle" id="overDiv">已处理</a></li>
				</ul>
			</div>
			<div id="contentwrapper" class="contentwrapper">
				<!-- 待处理 -->
				<div id="waitHandle" class="subcontent">
					<div class="overviewhead">
						<div class="overviewselect">
							<select id="urgency2" name="urgency" class="chzn-select"
								style="width: 200px;">
								<option value="">请选择紧急程度</option>
								<option value="一般">一般</option>
								<option value="紧急">紧急</option>
								<option value="非常紧急">非常紧急</option>
							</select>
						</div>
						工单类型: &nbsp;<input type="text" id="typeName2" /> &nbsp; &nbsp;
						工单名称: &nbsp;<input type="text" id="workName2" /> &nbsp; &nbsp;
						负责人: &nbsp;<input type="text" id="userName2" /> &nbsp; &nbsp; 手机:
						&nbsp;<input type="text" id="mobilePhone2" /> &nbsp; &nbsp;<br />
						<br /> 日期: &nbsp;<input type="text" id="beginTime2" /> &nbsp; 到:
						&nbsp;<input type="text" id="endTime2" />&nbsp; &nbsp;
						<button class="publishbutton radius3" id="queryBtn2">查询</button>
						&nbsp; &nbsp;
						<button class="publishbutton radius3" id="excelBtn2">导出EXCEL</button>
					</div>
					<br clear="all" />
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="waitHandleTable">
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
							<col class="con1" />
							<col class="con0" />
						</colgroup>
						<thead>
							<tr>
								<th class="head0">工单编号</th>
								<th class="head1">工单类型</th>
								<th class="head0">工单名称</th>
								<th class="head1">工单描述</th>
								<th class="head0">负责人</th>
								<th class="head1">负责人手机</th>
								<th class="head0">发布时间</th>
								<th class="head1">发布人</th>
								<th class="head0">紧急程度</th>
								<th class="head1">工单状态</th>
								<th class="head0">操作</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">工单编号</th>
								<th class="head1">工单类型</th>
								<th class="head0">工单名称</th>
								<th class="head1">工单描述</th>
								<th class="head0">负责人</th>
								<th class="head1">负责人手机</th>
								<th class="head0">发布时间</th>
								<th class="head1">发布人</th>
								<th class="head0">紧急程度</th>
								<th class="head1">工单状态</th>
								<th class="head0">操作</th>
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>
					<br /> <br />
				</div>
				<!-- 待处理 -->

				<!-- 处理中 -->
				<div id="onHandle" class="subcontent" style="display: none;">
					<div class="overviewhead">
						<div class="overviewselect">
							<select id="urgency" name="urgency" class="chzn-select"
								style="width: 200px;">
								<option value="">请选择紧急程度</option>
								<option value="一般">一般</option>
								<option value="紧急">紧急</option>
								<option value="非常紧急">非常紧急</option>
							</select>
						</div>
						工单类型: &nbsp;<input type="text" id="typeName" /> &nbsp; &nbsp;
						工单名称: &nbsp;<input type="text" id="workName" /> &nbsp; &nbsp;
						负责人: &nbsp;<input type="text" id="userName" /> &nbsp; &nbsp; 手机:
						&nbsp;<input type="text" id="mobilePhone" /> &nbsp; &nbsp;<br />
						<br /> 日期: &nbsp;<input type="text" id="beginTime" /> &nbsp; 到:
						&nbsp;<input type="text" id="endTime" />&nbsp; &nbsp;
						<button class="publishbutton radius3" id="queryBtn">查询</button>
						&nbsp; &nbsp;
						<button class="publishbutton radius3" id="excelBtn">导出EXCEL</button>
					</div>
					<br clear="all" />
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="onHandleTable">
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
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
						</colgroup>
						<thead>
							<tr>
								<th class="head0">工单编号</th>
								<th class="head1">工单类型</th>
								<th class="head0">工单名称</th>
								<th class="head1">工单描述</th>
								<th class="head0">负责人</th>
								<th class="head1">负责人手机</th>
								<th class="head0">发布时间</th>
								<th class="head1">发布人</th>
								<th class="head0">紧急程度</th>
								<th class="head1">处理时间</th>
								<th class="head0">处理描述</th>
								<th class="head1">工单状态</th>
								<th class="head0">操作</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">工单编号</th>
								<th class="head1">工单类型</th>
								<th class="head0">工单名称</th>
								<th class="head1">工单描述</th>
								<th class="head0">负责人</th>
								<th class="head1">负责人手机</th>
								<th class="head0">发布时间</th>
								<th class="head1">发布人</th>
								<th class="head0">紧急程度</th>
								<th class="head1">处理时间</th>
								<th class="head0">处理描述</th>
								<th class="head1">工单状态</th>
								<th class="head0">操作</th>
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>
					<br /> <br />
				</div>
				<!-- 处理中 -->

				<!-- 已处理 -->
				<div id="overHandle" class="subcontent" style="display: none;">
					<div class="overviewhead">
						<div class="overviewselect">
							<select id="urgency1" name="urgency1" class="chzn-select"
								style="width: 200px;">
								<option value="">请选择紧急程度</option>
								<option value="一般">一般</option>
								<option value="紧急">紧急</option>
								<option value="非常紧急">非常紧急</option>
							</select>
						</div>
						工单类型: &nbsp;<input type="text" id="typeName1" /> &nbsp; &nbsp;
						工单名称: &nbsp;<input type="text" id="workName1" /> &nbsp; &nbsp;
						负责人: &nbsp;<input type="text" id="userName1" /> &nbsp; &nbsp; 手机:
						&nbsp;<input type="text" id="mobilePhone1" /> &nbsp; &nbsp;<br />
						<br /> 日期: &nbsp;<input type="text" id="beginTime1" /> &nbsp; 到:
						&nbsp;<input type="text" id="endTime1" />&nbsp; &nbsp;
						<button class="publishbutton radius3" id="queryBtn1">查询</button>
						&nbsp; &nbsp;
						<button class="publishbutton radius3" id="excelBtn1">导出EXCEL</button>
					</div>
					<br clear="all" />
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="overHandleTable">
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
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
						</colgroup>
						<thead>
							<tr>
								<th class="head0">工单编号</th>
								<th class="head1">工单类型</th>
								<th class="head0">工单名称</th>
								<th class="head1">工单描述</th>
								<th class="head0">负责人</th>
								<th class="head1">负责人手机</th>
								<th class="head0">发布时间</th>
								<th class="head1">发布人</th>
								<th class="head0">紧急程度</th>
								<th class="head1">处理时间</th>
								<th class="head0">处理描述</th>
								<th class="head1">工单状态</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">工单编号</th>
								<th class="head1">工单类型</th>
								<th class="head0">工单名称</th>
								<th class="head1">工单描述</th>
								<th class="head0">负责人</th>
								<th class="head1">负责人手机</th>
								<th class="head0">发布时间</th>
								<th class="head1">发布人</th>
								<th class="head0">紧急程度</th>
								<th class="head1">处理时间</th>
								<th class="head0">处理描述</th>
								<th class="head1">工单状态</th>
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>
				</div>
				<!-- 已处理 -->
			</div>
			<br clear="all" />
		</div>
	</div>
</body>
</html>

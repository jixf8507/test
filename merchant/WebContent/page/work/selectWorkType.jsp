<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript"
	src="${ctx}/js/page/work/selectWorkType.js"></script>
</head>


<div id="normal" class="subcontent">
	<div class="overviewhead">
		类型名称: &nbsp;<input type="text" id="typeName" /> &nbsp; 负责人: &nbsp;<input
			type="text" id="userName" /> &nbsp;负责人电话: &nbsp;<input type="text"
			id="mobilePhone" /> &nbsp; &nbsp;
		<button class="publishbutton radius3" id="queryBtn">查询</button>
		<br></br>
	</div>
	<br clear="all" />
	<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
		id="workTypeTable">
		<colgroup>
			<col class="con0" style="width: 10%;"/>
			<col class="con1" />
			<col class="con0" />
			<col class="con1" />
			<col class="con0" />
		</colgroup>
		<thead>
			<tr>
				<th class="head0">选择</th>
				<th class="head1">类型名称</th>
				<th class="head0">类型描述</th>
				<th class="head1">负责人</th>
				<th class="head0">负责人电话</th>
			</tr>
		</thead>
		<tfoot>
			<tr>
				<th class="head0">选择</th>
				<th class="head1">类型名称</th>
				<th class="head0">类型描述</th>
				<th class="head1">负责人</th>
				<th class="head0">负责人电话</th>
			</tr>
		</tfoot>
		<tbody>

		</tbody>
	</table>
	<br /> <br />
</div>
</html>

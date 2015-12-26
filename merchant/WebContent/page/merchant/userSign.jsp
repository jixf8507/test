<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript" src="${ctx}/js/page/merchant/userSign.js"></script>
</head>
<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />
		<div class="centercontent">

			<div class="pageheader">
				<h1 class="pagetitle">员工签到查询</h1>
			</div>
			<!--pageheader-->
			

			<div id="contentwrapper" class="contentwrapper">

				<div id="normal" class="subcontent">
				<div class="overviewhead">
				<div class="overviewselect">
					<select id="status" name="select" class="chzn-select"
						style="width: 200px;" tabindex="2">
						<option value="">请选择签到状态</option>
						<option value="已签到">已签到</option>
						<option value="已签出">已签出</option>
					</select><br /><br />
					<select id="siteId" name="select" class="chzn-select"
						style="width: 200px;" tabindex="2">
						<option value="">请选择租赁点</option>
					</select>
				</div>
				<!--floatright-->
					姓名: &nbsp;<input type="text" id="name" style="width: 200px;"/> &nbsp; &nbsp; 
					手机: &nbsp;<input type="text" id="mobilePhone" style="width: 200px;" /> &nbsp; &nbsp; <br/><br/>
					日期: &nbsp;<input type="text" id="beginTime" /> &nbsp; &nbsp;&nbsp; 
					 到: &nbsp;&nbsp;&nbsp;<input type="text" id="endTime" />
				  &nbsp; &nbsp;<button class="publishbutton radius3" id="queryBtn">查询</button> &nbsp;
				<button class="publishbutton radius3" id="excelBtn">导出EXCEL</button>
			</div>
				<br clear="all" />
				
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="userSignTable">
						<colgroup>
							<col class="con0" style="width: 8%;"/>
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
								<th class="head0">日期</th>
								<th class="head1">姓名</th>
								<th class="head0">手机</th>
								<th class="head1">签到站点</th>
								<th class="head0">签到时间</th>
								<th class="head1">签到备注</th>
								<th class="head0">签出站点</th>
								<th class="head1">签出时间</th>
								<th class="head0">签出备注</th>
								<th class="head1">状态</th>
								<th class="head0">查看坐标</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">日期</th>
								<th class="head1">姓名</th>
								<th class="head0">手机</th>
								<th class="head1">签到站点</th>
								<th class="head0">签到时间</th>
								<th class="head1">签到备注</th>
								<th class="head0">签出站点</th>
								<th class="head1">签出时间</th>
								<th class="head0">签出备注</th>
								<th class="head1">状态</th>
								<th class="head0">查看坐标</th>
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

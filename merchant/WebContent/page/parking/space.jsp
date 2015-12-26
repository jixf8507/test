<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript" src="${ctx}/js/page/parking/space.js"></script>
</head>

<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />

		<div class="centercontent">

			<div class="pageheader">
				<h1 class="pagetitle">停车位列表</h1>
			</div>
			<!--pageheader-->

			<div id="contentwrapper" class="contentwrapper">
				<div id="list" class="subcontent">
					<div class="overviewhead">
						<div class="overviewselect">
							<select id="status" name="select" class="chzn-select"
								style="width: 200px;" tabindex="2">
								<option value="">请选择充电位状态</option>
								<option value="空闲">空闲</option>
								<option value="充电">充电</option>
							</select>&nbsp;&nbsp; <select id="siteId" name="select"
								class="chzn-select" style="width: 200px;" tabindex="2">
								<option value="">请选择</option>
							</select>
						</div>
						<!--floatright-->
						车位编号:&nbsp;<input type="text" id="spaceNO" /> &nbsp; &nbsp;
						出厂编号：&nbsp;<input type="text" id="factoryNo" /> &nbsp; &nbsp;
						<button class="publishbutton radius3" id="queryBtn">查询</button>
						<button class="publishbutton radius3" id="excelBtn">导出EXCEL</button>
					</div>
					<!--overviewhead-->
					<br clear="all" />

					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="sitesTable">
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
								<th class="head0">站点名称</th>
								<th class="head1">车位编号</th>
								<th class="head0">区域</th>
								<th class="head1">出厂编号</th>
								<th class="head0">设备名称</th>
								<th class="head1">铭牌</th>
								<th class="head0">所在充电设备位置</th>
								<th class="head1">充电位状态</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">站点名称</th>
								<th class="head1">车位编号</th>
								<th class="head0">区域</th>
								<th class="head1">出厂编号</th>
								<th class="head0">设备名称</th>
								<th class="head1">铭牌</th>
								<th class="head0">所在充电设备位置</th>
								<th class="head1">充电位状态</th>
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>
					<br /> <br />
				</div>
				<!-- #updates -->
			</div>
			<!--contentwrapper-->
			<br clear="all" />
		</div>
		<!-- centercontent -->
	</div>
	<!--bodywrapper-->

</body>
</html>

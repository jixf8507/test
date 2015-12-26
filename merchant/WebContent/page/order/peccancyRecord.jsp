<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript"
	src="${ctx}/js/plugins/chosen.jquery.min.js"></script>
<script type="text/javascript"
	src="${ctx}/js/page/order/peccancyRecord.js"></script>
</head>

<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />

		<div class="centercontent">
			<div class="pageheader">
				<h1 class="pagetitle">租赁违章记录</h1>
				<span class="pagedesc"></span>
				<ul class="hornav">
					<li class="current"><a href="#unHandle1" id="unHandle">未处理</a></li>
					<li><a href="#handle1" id="handle">已处理</a></li>
				</ul>
			</div>
			<!--pageheader-->
			<div id="contentwrapper" class="contentwrapper">
				<div id="unHandle1" class="subcontent">
					<!--pageheader-->
					<div class="overviewhead">
						车牌: &nbsp;<input type="text" id="carNumber" /> &nbsp; 手机号码:
						&nbsp;<input type="text" id="phone" /> &nbsp; 日期: &nbsp;<input
							type="text" id="beginTime" /> &nbsp; &nbsp; 到: &nbsp;<input
							type="text" id="endTime" />&nbsp;&nbsp;
						<button class="publishbutton radius3" id="queryBtn">查询</button>
						&nbsp;
						<button class="publishbutton radius3" id="excelBtn">导出EXCEL</button>
						<p>
							<button id="loadFile" class="publishbutton radius3">上传文件</button>
							&nbsp&nbsp
							<button id="addBtn">新增违章</button>
							&nbsp <a href="${ctx}/peccancyRecord.xls"><span
								style="font-size: 15px">文件模板下载</span></a>

						</p>
					</div>
					<!-- <p id="test1" onclick="po()">hello</p> -->
					<!--overviewhead-->
					<br clear="all" />
					<!--contenttitle-->
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="peccancyRecordsTable">
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
								<th class="head0">订单号</th>
								<th class="head1">车牌号码</th>
								<th class="head0">违章时间</th>
								<th class="head1">客户姓名</th>
								<th class="head0">手机号码</th>
								<th class="head1">违章地点</th>
								<th class="head0">描述</th>
								<th class="head1">经办人</th>
								<th class="head0">处理收费</th>
								<th class="head1">状态</th>
								<th class="head0">操作</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">订单号</th>
								<th class="head1">车牌号码</th>
								<th class="head0">违章时间</th>
								<th class="head1">客户姓名</th>
								<th class="head0">手机号码</th>
								<th class="head1">违章地点</th>
								<th class="head0">描述</th>
								<th class="head1">经办人</th>
								<th class="head0">处理收费</th>
								<th class="head1">状态</th>
								<th class="head0">操作</th>
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>
					<br /> <br />
				</div>


				<div id="handle1" class="subcontent" style="display: none;">
					<!--pageheader-->
					<div class="overviewhead">
						车牌: &nbsp;<input type="text" id="carNumber1" /> &nbsp; 手机号码:
						&nbsp;<input type="text" id="phone1" /> &nbsp; 日期: &nbsp;<input
							type="text" id="beginTime1" /> &nbsp; &nbsp; 到: &nbsp;<input
							type="text" id="endTime1" />&nbsp;&nbsp;
						<button class="publishbutton radius3" id="queryBtn1">查询</button>
						&nbsp;
						<button class="publishbutton radius3" id="excelBtn1">导出EXCEL</button>
					</div>
					<!--overviewhead-->

					<br clear="all" />

					<!--contenttitle-->
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="peccancyRecordsTable1">
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
						</colgroup>
						<thead>
							<tr>
								<th class="head0">订单号</th>
								<th class="head1">车牌号码</th>
								<th class="head0">违章时间</th>
								<th class="head1">客户姓名</th>
								<th class="head0">手机号码</th>
								<th class="head1">违章地点</th>
								<th class="head0">描述</th>
								<th class="head1">经办人</th>
								<th class="head0">处理收费</th>
								<th class="head1">状态</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">订单号</th>
								<th class="head1">车牌号码</th>
								<th class="head0">违章时间</th>
								<th class="head1">客户姓名</th>
								<th class="head0">手机号码</th>
								<th class="head1">违章地点</th>
								<th class="head0">描述</th>
								<th class="head1">经办人</th>
								<th class="head0">处理收费</th>
								<th class="head1">状态</th>
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

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
<script type="text/javascript" src="${ctx}/js/page/car/car.js"></script>
</head>

<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />

		<div class="centercontent">
			<div class="pageheader">
				<h1 class="pagetitle">车辆列表</h1>
				<span class="pagedesc"></span>
			</div>
			<!--pageheader-->

			<div id="contentwrapper" class="contentwrapper">

				<div id="updates" class="subcontent">

					<div class="overviewhead">
						<div class="overviewselect">
							<select id="curSiteId" name="select" class="chzn-select"
								style="width: 200px;" tabindex="2">
								<option value="">请选择站点</option>
							</select> &nbsp;<select id="carTypeId" name="select" class="chzn-select"
								style="width: 200px;">
								<option value="">请选择车辆型号</option>
							</select>
						</div>
						<!--floatright-->
						车牌号码: &nbsp;<input type="text" id="carNumber" /> &nbsp; &nbsp;
						车牌识别号: &nbsp;<input type="text" id="vin" /> &nbsp; &nbsp;
						<button class="publishbutton radius3" id="queryBtn">查询</button>
						<button class="publishbutton radius3" id="excelBtn">导出EXCEL</button>
					</div>
					<!--overviewhead-->
					<br clear="all" />
					<!--contenttitle-->
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="carTable">
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
								<th class="head0">汽车图片</th>
								<th class="head1">车牌号码</th>
								<th class="head0">车牌识别号</th>
								<th class="head1">汽车型号</th>
								<th class="head0">生产厂商</th>
								<th class="head1">汽车颜色</th>
								<th class="head0">车辆描述</th>
								<th class="head1">所在站点</th>
								<th class="head0">租赁状态</th>
								<th class="head1">车辆状态</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">汽车图片</th>
								<th class="head1">车牌号码</th>
								<th class="head0">车牌识别号</th>
								<th class="head1">汽车型号</th>
								<th class="head0">生产厂商</th>
								<th class="head1">汽车颜色</th>
								<th class="head0">车辆描述</th>
								<th class="head1">所在站点</th>
								<th class="head0">租赁状态</th>
								<th class="head1">车辆状态</th>
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

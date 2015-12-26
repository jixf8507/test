<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript"
	src="${ctx}/js/page/stake/rechargeCost.js"></script>
</head>
<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />
		<div class="centercontent">
			<div class="pageheader">
				<h1 class="pagetitle">充电订单记录</h1>
				<span class="pagedesc"></span>
				<ul class="hornav">
					<li class="current"><a href="#yesList" id="yesDiv">已付费</a></li>
					<li><a href="#noList" id="noDiv">未结算</a></li>
					<li><a href="#bookList" id="bookDiv">预约中</a></li>
					<li><a href="#cancelList" id="cancelDiv">已取消</a></li>
				</ul>
			</div>
			<div id="contentwrapper" class="contentwrapper">
				<!-- 已付费 -->
				<div id="yesList" class="subcontent">
					<div class="overviewhead">
						<div class="overviewselect">
							<select id="siteId" name="select" class="chzn-select"
								style="width: 200px;" tabindex="2">
								<option value="">请选择租赁点</option>
							</select>
						</div>
						出厂编号: &nbsp;<input type="text" id="factoryNo"
							style="width: 200px;" /> &nbsp; &nbsp; 车牌: &nbsp;<input
							type="text" id="CarNo" style="width: 200px;" /> &nbsp; &nbsp; <br />
						<br />开始日期:&nbsp;<input type="text" id="beginTime" />&nbsp;
						&nbsp;&nbsp;&nbsp; 到:&nbsp; &nbsp;&nbsp;&nbsp;<input type="text"
							id="endTime" />&nbsp; &nbsp;
						<button class="publishbutton radius3" id="queryBtn">查询</button>
						<button class="publishbutton radius3" id="excelBtn">导出EXCEL</button>
					</div>
					<br clear="all" />
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="yesTable">
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
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
						</colgroup>
						<thead>
							<tr>
								<th class="head0">充电站</th>
								<th class="head1">出厂编号</th>
								<th class="head0">充电口</th>
								<th class="head1">充电卡号</th>
								<th class="head0">客户</th>
								<th class="head1"><c:if
										test="${sessionScope.user.systemName == 'stake'}">一卡通卡号</c:if>
									<c:if test="${sessionScope.user.systemName == 'eakay'}">租赁卡号</c:if></th>
								<th class="head0">车牌</th>
								<th class="head1">开始时间</th>
								<th class="head0">结束时间</th>
								<th class="head1">开始SOC</th>
								<th class="head0">结束SOC</th>
								<th class="head1">充电量</th>
								<th class="head0">费用</th>
								<th class="head1">充电方式</th>
								<th class="head0">状态</th>
								<th class="head1">票据打印</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">充电站</th>
								<th class="head1">出厂编号</th>
								<th class="head0">充电口</th>
								<th class="head1">充电卡号</th>
								<th class="head0">客户</th>
								<th class="head1"><c:if
										test="${sessionScope.user.systemName == 'stake'}">一卡通卡号</c:if>
									<c:if test="${sessionScope.user.systemName == 'eakay'}">租赁卡号</c:if></th>
								<th class="head0">车牌</th>
								<th class="head1">开始时间</th>
								<th class="head0">结束时间</th>
								<th class="head1">开始SOC</th>
								<th class="head0">结束SOC</th>
								<th class="head1">充电量</th>
								<th class="head0">费用</th>
								<th class="head1">充电方式</th>
								<th class="head0">状态</th>
								<th class="head1">票据打印</th>
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>
					<br /> <br />
				</div>
				<!-- 已付费 -->
				<!-- 未结算 -->
				<div id="noList" class="subcontent" style="display: none">
					<div class="overviewhead">
						<div class="overviewselect">
							<select id="siteId1" name="select" class="chzn-select"
								style="width: 200px;" tabindex="2">
								<option value="">请选择租赁点</option>
							</select>
						</div>
						出厂编号: &nbsp;<input type="text" id="factoryNo1"
							style="width: 200px;" /> &nbsp; &nbsp; 车牌: &nbsp;<input
							type="text" id="CarNo1" style="width: 200px;" /> &nbsp; &nbsp; <br />
						<br />开始日期:&nbsp;<input type="text" id="beginTime1" />&nbsp;
						&nbsp;&nbsp;&nbsp; 到:&nbsp; &nbsp;&nbsp;&nbsp;<input type="text"
							id="endTime1" />&nbsp; &nbsp;
						<button class="publishbutton radius3" id="queryBtn1">查询</button>
						<button class="publishbutton radius3" id="excelBtn1">导出EXCEL</button>
					</div>
					<br clear="all" />
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="noTable">
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
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
						</colgroup>
						<thead>
							<tr>
								<th class="head0">充电站</th>
								<th class="head1">出厂编号</th>
								<th class="head0">充电口</th>
								<th class="head1">充电卡号</th>
								<th class="head0">客户</th>
								<th class="head1"><c:if
										test="${sessionScope.user.systemName == 'stake'}">一卡通卡号</c:if>
									<c:if test="${sessionScope.user.systemName == 'eakay'}">租赁卡号</c:if></th>
								<th class="head0">车牌</th>
								<th class="head1">开始时间</th>
								<th class="head0">结束时间</th>
								<th class="head1">开始SOC</th>
								<th class="head0">结束SOC</th>
								<th class="head1">充电量</th>
								<th class="head0">费用</th>
								<th class="head1">充电方式</th>
								<th class="head0">状态</th>
								<th class="head1">结算</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">充电站</th>
								<th class="head1">出厂编号</th>
								<th class="head0">充电口</th>
								<th class="head1">充电卡号</th>
								<th class="head0">客户</th>
								<th class="head1"><c:if
										test="${sessionScope.user.systemName == 'stake'}">一卡通卡号</c:if>
									<c:if test="${sessionScope.user.systemName == 'eakay'}">租赁卡号</c:if></th>
								<th class="head0">车牌</th>
								<th class="head1">开始时间</th>
								<th class="head0">结束时间</th>
								<th class="head1">开始SOC</th>
								<th class="head0">结束SOC</th>
								<th class="head1">充电量</th>
								<th class="head0">费用</th>
								<th class="head1">充电方式</th>
								<th class="head0">状态</th>
								<th class="head1">结算</th>
							</tr>
						</tfoot>
						<tbody>
						</tbody>
					</table>
					<br /> <br />
				</div>
				<!-- 未结算 -->
				<!-- 预约中 -->
				<div id="bookList" class="subcontent" style="display: none">
					<div class="overviewhead">
						<div class="overviewselect">
							<select id="siteId2" name="select" class="chzn-select"
								style="width: 200px;" tabindex="2">
								<option value="">请选择租赁点</option>
							</select>
						</div>
						出厂编号: &nbsp;<input type="text" id="factoryNo2"
							style="width: 200px;" /> &nbsp; &nbsp; 车牌: &nbsp;<input
							type="text" id="CarNo2" style="width: 200px;" /> &nbsp; &nbsp; <br />
						<br />开始日期:&nbsp;<input type="text" id="beginTime2" />&nbsp;
						&nbsp;&nbsp;&nbsp; 到:&nbsp; &nbsp;&nbsp;&nbsp;<input type="text"
							id="endTime2" />&nbsp; &nbsp;
						<button class="publishbutton radius3" id="queryBtn2">查询</button>
						<button class="publishbutton radius3" id="excelBtn2">导出EXCEL</button>
					</div>
					<br clear="all" />
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="bookTable">
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
								<th class="head0">充电站</th>
								<th class="head1">出厂编号</th>
								<th class="head0">充电口</th>
								<th class="head1">客户</th>
								<th class="head0"><c:if
										test="${sessionScope.user.systemName == 'stake'}">一卡通卡号</c:if>
									<c:if test="${sessionScope.user.systemName == 'eakay'}">租赁卡号</c:if></th>
								<th class="head1">车牌</th>
								<th class="head0">开始时间</th>
								<th class="head1">充电量</th>
								<th class="head0">费用</th>
								<th class="head1">充电方式</th>
								<th class="head0">状态</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">充电站</th>
								<th class="head1">出厂编号</th>
								<th class="head0">充电口</th>
								<th class="head1">客户</th>
								<th class="head0"><c:if
										test="${sessionScope.user.systemName == 'stake'}">一卡通卡号</c:if>
									<c:if test="${sessionScope.user.systemName == 'eakay'}">租赁卡号</c:if></th>
								<th class="head1">车牌</th>
								<th class="head0">开始时间</th>
								<th class="head1">充电量</th>
								<th class="head0">费用</th>
								<th class="head1">充电方式</th>
								<th class="head0">状态</th>
							</tr>
						</tfoot>
						<tbody>
						</tbody>
					</table>
					<br /> <br />
				</div>
				<!-- 预约中 -->
				<!-- 已取消 -->
				<div id="cancelList" class="subcontent" style="display: none">
					<div class="overviewhead">
						<div class="overviewselect">
							<select id="siteId3" name="select" class="chzn-select"
								style="width: 200px;" tabindex="2">
								<option value="">请选择租赁点</option>
							</select>
						</div>
						出厂编号: &nbsp;<input type="text" id="factoryNo3"
							style="width: 200px;" /> &nbsp; &nbsp; 车牌: &nbsp;<input
							type="text" id="CarNo3" style="width: 200px;" /> &nbsp; &nbsp; <br />
						<br />开始日期:&nbsp;<input type="text" id="beginTime3" />&nbsp;
						&nbsp;&nbsp;&nbsp; 到:&nbsp; &nbsp;&nbsp;&nbsp;<input type="text"
							id="endTime3" />&nbsp; &nbsp;
						<button class="publishbutton radius3" id="queryBtn3">查询</button>
						<button class="publishbutton radius3" id="excelBtn3">导出EXCEL</button>
					</div>
					<br clear="all" />
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="cancelTable">
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
								<th class="head0">充电站</th>
								<th class="head1">出厂编号</th>
								<th class="head0">充电口</th>
								<th class="head1">客户</th>
								<th class="head0"><c:if
										test="${sessionScope.user.systemName == 'stake'}">一卡通卡号</c:if>
									<c:if test="${sessionScope.user.systemName == 'eakay'}">租赁卡号</c:if></th>
								<th class="head1">车牌</th>
								<th class="head0">开始时间</th>
								<th class="head1">充电量</th>
								<th class="head0">费用</th>
								<th class="head1">充电方式</th>
								<th class="head0">状态</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">充电站</th>
								<th class="head1">出厂编号</th>
								<th class="head0">充电口</th>
								<th class="head1">客户</th>
								<th class="head0"><c:if
										test="${sessionScope.user.systemName == 'stake'}">一卡通卡号</c:if>
									<c:if test="${sessionScope.user.systemName == 'eakay'}">租赁卡号</c:if></th>
								<th class="head1">车牌</th>
								<th class="head0">开始时间</th>
								<th class="head1">充电量</th>
								<th class="head0">费用</th>
								<th class="head1">充电方式</th>
								<th class="head0">状态</th>
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>
					<br /> <br />
				</div>
				<!-- 已取消 -->
			</div>
			<br clear="all" />
		</div>
	</div>

</body>
</html>

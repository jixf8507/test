<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript" src="${ctx}/js/page/stake/stakeCard.js"></script>
</head>

<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />

		<div class="centercontent">
			<div class="pageheader">
				<h1 class="pagetitle">充电卡管理</h1>
				<span class="pagedesc"></span>
				<ul class="hornav">
					<li class="current"><a href="#list" id="listDiv">在用</a></li>
					<li><a href="#del" id="delDiv">丢失</a></li>
				</ul>
			</div>
			<!--pageheader-->
			<div id="contentwrapper" class="contentwrapper">
				<div id="list" class="subcontent">
					<div class="overviewhead">
						<!--floatright-->
						内置卡号: &nbsp;<input type="text" id="cardID" /> &nbsp; &nbsp;
						<c:if test="${sessionScope.user.systemName == 'stake'}">一卡通卡号</c:if>
						<c:if test="${sessionScope.user.systemName == 'eakay'}">外置卡号</c:if>
						: &nbsp;<input type="text" id="cardNumber" /> &nbsp; &nbsp; 车牌:
						&nbsp;<input type="text" id="carNumber" /> &nbsp; &nbsp;姓名:
						&nbsp;<input type="text" id="name" /> &nbsp; &nbsp;手机:
						&nbsp;<input type="text" id="phone" /> &nbsp; &nbsp;
						<br /><br />开卡日期:&nbsp;<input type="text" id="beginTime" />&nbsp;
						&nbsp;&nbsp;&nbsp; 到:&nbsp; &nbsp;&nbsp;&nbsp;<input type="text"
							id="endTime" />&nbsp; &nbsp;
						<button class="publishbutton radius3" id="queryBtn">查询</button>
						<button class="publishbutton radius3" id="excelBtn">导出EXCEL</button>
					</div>
					<br clear="all"/>
					<!--overviewhead-->
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
						</colgroup>
						<thead>
							<tr>
								<th class="head1">内置卡号</th>
								<th class="head0"><c:if
										test="${sessionScope.user.systemName == 'stake'}">一卡通卡号</c:if>
									<c:if test="${sessionScope.user.systemName == 'eakay'}">外置卡号</c:if></th>
								<!-- <th class="head1">身份证号</th> -->
								<th class="head1">客户姓名</th>
								<th class="head0">手机号码</th>
								<th class="head1">车牌号码</th>
								<th class="head0">账户余额</th>
								<th class="head1">开卡时间</th>
								<th class="head0">操作</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head1">内置卡号</th>
								<th class="head0"><c:if
										test="${sessionScope.user.systemName == 'stake'}">一卡通卡号</c:if>
									<c:if test="${sessionScope.user.systemName == 'eakay'}">外置卡号</c:if></th>
								<!-- <th class="head1">身份证号</th> -->
								<th class="head1">客户姓名</th>
								<th class="head0">手机号码</th>
								<th class="head1">车牌号码</th>
								<th class="head0">账户余额</th>
								<th class="head1">开卡时间</th>
								<th class="head0">操作</th>
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>
					<br /> <br />
				</div>
				<div id="del" class="subcontent" style="display: none;">
					<div class="overviewhead">
						<!--floatright-->
						内置卡号: &nbsp;<input type="text" id="cardID1" /> &nbsp; &nbsp;
						<c:if test="${sessionScope.user.systemName == 'stake'}">一卡通卡号</c:if>
						<c:if test="${sessionScope.user.systemName == 'eakay'}">外置卡号</c:if>
						: &nbsp;<input type="text" id="cardNumber1" /> &nbsp; &nbsp; 车牌:
						&nbsp;<input type="text" id="carNumber1" />&nbsp; &nbsp;姓名:
						&nbsp;<input type="text" id="name1" /> &nbsp; &nbsp;手机:
						&nbsp;<input type="text" id="phone1" /> &nbsp; &nbsp;
						<br /><br />开卡日期:&nbsp;<input type="text" id="beginTime1" />&nbsp;
						&nbsp;&nbsp;&nbsp; 到:&nbsp; &nbsp;&nbsp;&nbsp;<input type="text"
							id="endTime1" />&nbsp; &nbsp;
						<button class="publishbutton radius3" id="queryBtn1">查询</button>
						<button class="publishbutton radius3" id="excelBtn1">导出EXCEL</button>
					</div>
					<br clear="all"/>
					<!--overviewhead-->
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="delTable">
						<colgroup>
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
								<th class="head1">内置卡号</th>
								<th class="head0"><c:if
										test="${sessionScope.user.systemName == 'stake'}">一卡通卡号</c:if>
									<c:if test="${sessionScope.user.systemName == 'eakay'}">外置卡号</c:if></th>
								<!-- <th class="head1">身份证号</th> -->
								<th class="head1">客户姓名</th>
								<th class="head0">手机号码</th>
								<th class="head1">车牌号码</th>
								<th class="head0">账户余额</th>
								<th class="head1">开卡时间</th>
								<th class="head0">操作</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head1">内置卡号</th>
								<th class="head0"><c:if
										test="${sessionScope.user.systemName == 'stake'}">一卡通卡号</c:if>
									<c:if test="${sessionScope.user.systemName == 'eakay'}">外置卡号</c:if></th>
								<!-- <th class="head1">身份证号</th> -->
								<th class="head1">客户姓名</th>
								<th class="head0">手机号码</th>
								<th class="head1">车牌号码</th>
								<th class="head0">账户余额</th>
								<th class="head1">开卡时间</th>
								<th class="head0">操作</th>
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

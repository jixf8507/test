<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript"
	src="${ctx}/js/page/price/chargeRecord.js"></script>
</head>
<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />
		<div class="centercontent">
			<div class="pageheader">
				<h1 class="pagetitle">租赁收费记录</h1>
				<span class="pagedesc"></span>
				<ul class="hornav">
					<li class="current"><a href="#normal" id="normalDiv">普通客户</a></li>
					<li><a href="#unit" id="unitDiv">企业长租</a></li>
				</ul>
			</div>
			<div id="contentwrapper" class="contentwrapper">
				<div id="normal" class="subcontent">
					<div class="overviewhead">
						<div class="overviewselect">
							<select id="payment" name="select" class="chzn-select"
								style="width: 200px;" tabindex="2">
								<option value="">请选择付费方式</option>
								<option value="网络付费">网络付费</option>
								<option value="余额付费">余额付费</option>
								<option value="现金付费">现金付费</option>
								<option value="刷卡付费">刷卡付费</option>
							</select><br /> <br /> <select id="status" name="select"
								class="chzn-select" style="width: 200px;" tabindex="2">
								<option value="">请选择核对入账状态</option>
								<option value="已入账">已核对入账</option>
								<option value="未入账">未核对入账</option>
							</select><br /> <br /> <select id="rSiteId" name="select"
								class="chzn-select" style="width: 200px;" tabindex="2">
								<option value="">请选择</option>
							</select>
						</div>
						还车日期: &nbsp;<input type="text" id="beginTime" /> &nbsp;&nbsp; 到:
						&nbsp;&nbsp;<input type="text" id="endTime" />&nbsp;&nbsp;<br />
						<br />姓名:&nbsp; &nbsp;<input type="text" id="name"
							style="width: 200px;" />&nbsp; &nbsp;&nbsp; 车牌:&nbsp; &nbsp;<input
							type="text" id="carNumber" style="width: 200px;" /> &nbsp;
						&nbsp;&nbsp;手机:&nbsp; &nbsp;<input type="text" id="phone"
							style="width: 200px;" />&nbsp; &nbsp;&nbsp;<br /> <br />订单号
						:&nbsp; &nbsp;ZCDD_<input type="text" id="orderId"
							style="width: 200px;" /> &nbsp;&nbsp;收费人 :&nbsp; &nbsp;<input
							type="text" id="menForCharge" style="width: 200px;" /> &nbsp;
						&nbsp;&nbsp;
						<button class="publishbutton radius3" id="queryBtn">查询</button>
						&nbsp;&nbsp;
						<button class="publishbutton radius3" id="excelBtn">导出EXCEL</button>
					</div>
					<br clear="all" />
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="chargeRecordsTable">
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
							<col class="con0" />
							<col class="con1" width="8%" />
						</colgroup>
						<thead>
							<tr>
								<th class="head0">订单号</th>
								<th class="head1">车牌</th>
								<th class="head0">客户</th>
								<th class="head1">手机号码</th>
								<th class="head0">取车时间</th>
								<th class="head1">取车租赁点</th>
								<th class="head0">还车时间</th>
								<th class="head1">还车租赁点</th>
								<th class="head0">行驶公里数</th>
								<th class="head1">超出公里数费用</th>
								<th class="head0">租赁费用</th>
								<th class="head1">其他费用</th>
								<th class="head0">维修费用</th>
								<th class="head1">优惠金额</th>
								<th class="head0">付费金额</th>
								<th class="head1">收费人</th>
								<th class="head0">付费方式</th>
								<th class="head1">更多操作</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">订单号</th>
								<th class="head1">车牌</th>
								<th class="head0">客户</th>
								<th class="head1">手机号码</th>
								<th class="head0">取车时间</th>
								<th class="head1">取车租赁点</th>
								<th class="head0">还车时间</th>
								<th class="head1">还车租赁点</th>
								<th class="head0">行驶公里数</th>
								<th class="head1">超出公里数费用</th>
								<th class="head0">租赁费用</th>
								<th class="head1">其他费用</th>
								<th class="head0">维修费用</th>
								<th class="head1">优惠金额</th>
								<th class="head0">付费金额</th>
								<th class="head1">收费人</th>
								<th class="head0">付费方式</th>
								<th class="head1">更多操作</th>
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>
					<br /> <br />
				</div>

				<div id="unit" class="subcontent" style="display: none;">
					<div class="overviewhead">
						<div class="overviewselect">
							<select id="payment1" name="select" class="chzn-select"
								style="width: 200px;" tabindex="2">
								<option value="">请选择付费方式</option>
								<option value="网络付费">网络付费</option>
								<option value="余额付费">余额付费</option>
								<option value="现金付费">现金付费</option>
								<option value="刷卡付费">刷卡付费</option>
							</select><br />
							<br /> <select id="status1" name="select" class="chzn-select"
								style="width: 200px;" tabindex="2">
								<option value="">请选择核对入账状态</option>
								<option value="已入账">已核对入账</option>
								<option value="未入账">未核对入账</option>
							</select><br /> <br /> <select id="siteId" name="select"
								class="chzn-select" style="width: 200px;" tabindex="2">
								<option value="">请选择</option>
							</select>
						</div>
						还车日期: &nbsp;<input type="text" id="beginTime1" /> &nbsp;&nbsp; 到:
						&nbsp;&nbsp;<input type="text" id="endTime1" />&nbsp;&nbsp;<br />
						<br />企业:&nbsp; &nbsp;<input type="text" id="enterpriseName"
							style="width: 200px;" />&nbsp; &nbsp;&nbsp; 车牌:&nbsp; &nbsp;<input
							type="text" id="carNumber1" style="width: 200px;" /> &nbsp;
						&nbsp;&nbsp;电话:&nbsp; &nbsp;<input type="text" id="contactPhone"
							style="width: 200px;" />&nbsp; &nbsp;&nbsp;<br /> <br />订单号
						:&nbsp; &nbsp;DWHY_<input type="text" id="orderId1"
							style="width: 200px;" /> &nbsp;&nbsp;收费人 :&nbsp; &nbsp;<input
							type="text" id="menForCharge1" style="width: 200px;" /> &nbsp;
						&nbsp;&nbsp;
						<button class="publishbutton radius3" id="queryBtn1">查询</button>
						&nbsp;&nbsp;
						<button class="publishbutton radius3" id="excelBtn1">导出EXCEL</button>

					</div>
					<br clear="all" />
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="unitRecordsTable">
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
							<col class="con0" />
							<col class="con1" width="8%" />
						</colgroup>
						<thead>
							<tr>
								<th class="head0">订单号</th>
								<th class="head1">车牌</th>
								<th class="head0">企业名称</th>
								<th class="head1">联系电话</th>
								<th class="head0">取车时间</th>
								<th class="head1">取车租赁点</th>
								<th class="head0">还车时间</th>
								<th class="head1">还车租赁点</th>
								<th class="head0">行驶公里数</th>
								<th class="head1">超出公里数费用</th>
								<th class="head0">租赁费用</th>
								<th class="head1">其他费用</th>
								<th class="head0">维修费用</th>
								<th class="head1">优惠金额</th>
								<th class="head0">付费金额</th>
								<th class="head1">收费人</th>
								<th class="head0">付费方式</th>
								<th class="head1">更多操作</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">订单号</th>
								<th class="head1">车牌</th>
								<th class="head0">企业名称</th>
								<th class="head1">联系电话</th>
								<th class="head0">取车时间</th>
								<th class="head1">取车租赁点</th>
								<th class="head0">还车时间</th>
								<th class="head1">还车租赁点</th>
								<th class="head0">行驶公里数</th>
								<th class="head1">超出公里数费用</th>
								<th class="head0">租赁费用</th>
								<th class="head1">其他费用</th>
								<th class="head0">维修费用</th>
								<th class="head1">优惠金额</th>
								<th class="head0">付费金额</th>
								<th class="head1">收费人</th>
								<th class="head0">付费方式</th>
								<th class="head1">更多操作</th>
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>
					<br /> <br />
				</div>
			</div>
			<br clear="all" />
		</div>
	</div>
</body>
</html>

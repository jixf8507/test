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
<script type="text/javascript" src="${ctx}/js/page/order/returnApply.js"></script>
</head>

<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />

		<div class="centercontent">
			<div class="pageheader">
				<h1 class="pagetitle">还车</h1>
				<span class="pagedesc"></span>
				<ul class="hornav">
					<li class="current"><a href="#normal" id="normalDiv">普通客户</a></li>
					<li><a href="#unit" id="unitDiv">企业长租</a></li>
					<li><a href="#control" id="controlDiv">车辆调度</a></li>
					<li><a href="#official" id="officialDiv">公务用车</a></li>
				</ul>
			</div>
			<!--pageheader-->
			<div id="contentwrapper" class="contentwrapper">
				<!-- 普通客户 -->
				<div id="normal" class="subcontent">
					<div class="overviewhead">
						<div class="overviewselect">
							<select id="priceTypeTableName" name="select" class="chzn-select"
								style="width: 200px;" tabindex="2">
								<option value="">请选择租赁类型</option>
								<option value="wh_hours_price_type">分时租赁</option>
								<option value="month_lease_price_type">长期租赁</option>
							</select>&nbsp; &nbsp; <select id="getSiteId" name="select"
								class="chzn-select" style="width: 200px;" tabindex="2">
								<option value="">请选择租赁点</option>
							</select>
						</div>
						<!--floatright-->
						车牌: &nbsp;<input type="text" id="getCarNumber" /> &nbsp; &nbsp;
						姓名: &nbsp;<input type="text" id="getUserName" /> &nbsp; &nbsp;
						手机号码: &nbsp;<input type="text" id="getUserPhone" /> &nbsp; &nbsp;
						<button class="publishbutton radius3" id="getQueryBtn">查询</button>
					</div>
					<br clear="all" />
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="getOrdersTable">
						<colgroup>
							<col class="con0" width="10%" />
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
								<th class="head0">订单号</th>
								<th class="head1">车牌号码</th>
								<th class="head0">套餐名称</th>
								<th class="head1">租赁类型</th>
								<th class="head0">客户</th>
								<th class="head1">手机</th>
								<th class="head0">取车时间</th>
								<th class="head1">取车租赁点</th>
								<th class="head0">订单状态</th>
								<th class="head1">取车经办人</th>
								<th class="head0">车载设备</th>
								<th class="head1">申请还车</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">订单号</th>
								<th class="head1">车牌号码</th>
								<th class="head0">套餐名称</th>
								<th class="head1">租赁类型</th>
								<th class="head0">客户</th>
								<th class="head1">手机</th>
								<th class="head0">取车时间</th>
								<th class="head1">取车租赁点</th>
								<th class="head0">订单状态</th>
								<th class="head1">取车经办人</th>
								<th class="head0">车载设备</th>
								<th class="head1">申请还车</th>
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>
					<br /> <br />
				</div>
					<!-- 普通客户 -->
				<!-- 单位会员 -->
				<div id="unit" class="subcontent" style="display: none;">
					<div class="overviewhead">
						<div class="overviewselect">
							<select id="priceTypeTableName1" name="select"
								class="chzn-select" style="width: 200px;" tabindex="2">
								<option value="">请选择租赁类型</option>
								<option value="wh_hours_price_type">分时租赁</option>
								<option value="month_lease_price_type">长期租赁</option>
							</select>&nbsp; &nbsp; <select id="relityGetSiteId1" name="select"
								class="chzn-select" style="width: 200px;" tabindex="2">
								<option value="">请选择租赁点</option>
							</select>
						</div>
						车牌: &nbsp;<input type="text" id="carNumber1" /> &nbsp; &nbsp;
						单位: &nbsp;<input type="text" id="enterpriseName" /> &nbsp;
						电话: &nbsp;<input type="text" id="contactPhone" /> &nbsp; &nbsp;
						<button class="publishbutton radius3" id="queryBtn1">查询</button>
						<button class="publishbutton radius3" id="excelBtn1">导出EXCEL</button>
					</div>
					<br clear="all" />
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="getUnitOrdersTable">
						<colgroup>
							<col class="con0" width="10%" />
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
								<th class="head0">订单号</th>
								<th class="head1">车牌号码</th>
								<th class="head0">套餐名称</th>
								<th class="head1">租赁类型</th>
								<th class="head0">单位名称</th>
								<th class="head1">联系电话</th>
								<th class="head0">取车时间</th>
								<th class="head1">取车租赁点</th>
								<th class="head0">订单状态</th>
								<th class="head1">取车经办人</th>
								<th class="head0">车载设备</th>
								<th class="head1">申请还车</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">订单号</th>
								<th class="head1">车牌号码</th>
								<th class="head0">套餐名称</th>
								<th class="head1">租赁类型</th>
								<th class="head0">单位名称</th>
								<th class="head1">联系电话</th>
								<th class="head0">取车时间</th>
								<th class="head1">取车租赁点</th>
								<th class="head0">订单状态</th>
								<th class="head1">取车经办人</th>
								<th class="head0">车载设备</th>
								<th class="head1">申请还车</th>
							</tr>
						</tfoot>
						<tbody>
						</tbody>
					</table>
					<br /> <br />
				</div>
				<!-- 单位会员 -->
				<!-- 车辆调度 -->
				<div id="control" class="subcontent" style="display: none;">
					<div class="overviewhead">
						<div class="overviewselect">
							<select id="relityGetSiteId3" name="select" class="chzn-select"
								style="width: 200px;" tabindex="2">
								<option value="">请选择租赁点</option>								
							</select>
						</div>
						车牌: &nbsp;<input type="text" id="carNumber3" /> &nbsp;
						&nbsp; 姓名: &nbsp;<input type="text" id="userName" />
						&nbsp; 手机: &nbsp;<input type="text" id="mobilePhone" />
						&nbsp; &nbsp;
						<button class="publishbutton radius3" id="queryBtn3">查询</button>
						<button class="publishbutton radius3" id="excelBtn3">导出EXCEL</button>
					</div>
					<br clear="all" />
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="getControlOrdersTable">
						<colgroup>
							<col class="con0" width="10%" />
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
								<th class="head0">姓名</th>
								<th class="head1">手机</th>
								<th class="head0">取车时间</th>
								<th class="head1">取车租赁点</th>
								<th class="head0">用车事由</th>
								<th class="head1">订单状态</th>
								<th class="head0">取车经办人</th>
								<th class="head1">车载设备</th>
								<th class="head0">申请还车</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">订单号</th>
								<th class="head1">车牌号码</th>
								<th class="head0">姓名</th>
								<th class="head1">手机</th>
								<th class="head0">取车时间</th>
								<th class="head1">取车租赁点</th>
								<th class="head0">用车事由</th>
								<th class="head1">订单状态</th>
								<th class="head0">取车经办人</th>
								<th class="head1">车载设备</th>
								<th class="head0">申请还车</th>
							</tr>
						</tfoot>
						<tbody> </tbody>
					</table>
					<br /> <br />
				</div>
				<!-- 车辆调度 -->
				<!-- 公务用车-->
				<div id="official" class="subcontent" style="display: none;">
					<div class="overviewhead">
						<div class="overviewselect">
							<select id="relityGetSiteId4" name="select" class="chzn-select"
								style="width: 200px;" tabindex="2">
								<option value="">请选择租赁点</option>								
							</select>
						</div>
						车牌: &nbsp;<input type="text" id="carNumber4" /> &nbsp;
						&nbsp; 姓名: &nbsp;<input type="text" id="userName1" />
						&nbsp; 手机: &nbsp;<input type="text" id="mobilePhone1" />
						&nbsp; &nbsp;
						<button class="publishbutton radius3" id="queryBtn4">查询</button>
						<button class="publishbutton radius3" id="excelBtn4">导出EXCEL</button>
					</div>
					<br clear="all" />
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="getOfficialOrdersTable">
						<colgroup>
							<col class="con0" width="10%" />
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
								<th class="head0">姓名</th>
								<th class="head1">手机</th>
								<th class="head0">取车时间</th>
								<th class="head1">取车租赁点</th>
								<th class="head0">用车事由</th>
								<th class="head1">订单状态</th>
								<th class="head0">取车经办人</th>
								<th class="head1">车载设备</th>
								<th class="head0">申请还车</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">订单号</th>
								<th class="head1">车牌号码</th>
								<th class="head0">姓名</th>
								<th class="head1">手机</th>
								<th class="head0">取车时间</th>
								<th class="head1">取车租赁点</th>
								<th class="head0">用车事由</th>
								<th class="head1">订单状态</th>
								<th class="head0">取车经办人</th>
								<th class="head1">车载设备</th>
								<th class="head0">申请还车</th>
							</tr>
						</tfoot>
						<tbody> </tbody>
					</table>
					<br /> <br />
				</div>
				<!-- 公务用车-->
			</div>
			<br clear="all" />
		</div>
	</div>
</body>
</html>

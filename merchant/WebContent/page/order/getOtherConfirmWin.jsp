<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript" src="${ctx}/js/page/order/getOtherConfirmWin.js"></script>
 
</head>
			<div class="pageheader">
				<ul class="hornav">
					<li class="current"><a href="#control" id="controlDiv">车辆调度</a></li>
					<li><a href="#official" id="officialDiv">公务用车</a></li>
				</ul>
			</div>
			<div id="contentwrapper" class="contentwrapper">
			<input type="hidden" value="${param.siteId }" id="siteId"/>
				<!-- 车辆调度 -->
				<div id="control" class="subcontent">
					<div class="overviewhead">
						<div class="overviewselect">
							<select id="relityGetSiteId3" name="select" class="chzn-select"
								style="width: 200px;" tabindex="2">
								<option value="">请选择租赁点</option>								
							</select>
						</div>
						车牌: &nbsp;<input type="text" id="carNumber3" />&nbsp; &nbsp;
						<input type="hidden" id="userName" />
						<input type="hidden" id="mobilePhone" />
						<button class="publishbutton radius3" id="queryBtn3">查询</button>
						<button class="publishbutton radius3" id="excelBtn3">导出EXCEL</button>
					</div>
					<br clear="all" />
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="getControlOrdersTable">
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
								<th class="head0">姓名</th>
								<th class="head1">手机</th>
								<th class="head0">取车时间</th>
								<th class="head1">取车租赁点</th>
								<th class="head0">用车事由</th>
								<th class="head1">订单状态</th>
								<th class="head0">取车经办人</th>
								<th class="head1">车载设备</th>
								<th class="head0">取车审查</th>
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
								<th class="head0">取车审查</th>
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
						车牌: &nbsp;<input type="text" id="carNumber4" />&nbsp; &nbsp;
						<input type="hidden" id="userName1" />
						<input type="hidden" id="mobilePhone1" />
						<button class="publishbutton radius3" id="queryBtn4">查询</button>
						<button class="publishbutton radius3" id="excelBtn4">导出EXCEL</button>
					</div>
					<br clear="all" />
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="getOfficialOrdersTable">
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
								<th class="head0">姓名</th>
								<th class="head1">手机</th>
								<th class="head0">取车时间</th>
								<th class="head1">取车租赁点</th>
								<th class="head0">用车事由</th>
								<th class="head1">订单状态</th>
								<th class="head0">取车经办人</th>
								<th class="head1">车载设备</th>
								<th class="head0">取车审查</th>
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
								<th class="head0">取车审查</th>
							</tr>
						</tfoot>
						<tbody> </tbody>
					</table>
					<br /> <br />
				</div>
				<!-- 公务用车-->
			</div>
</html>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript" src="${ctx}/js/page/order/getConfirmWin.js"></script>
 
</head>
			<div id="contentwrapper" class="contentwrapper">
			<input type="hidden" value="${param.siteId }" id="siteId"/>
				<!-- 普通客户 -->
				<div id="normal" class="subcontent">
					<div class="overviewhead">
						<div class="overviewselect">
						<select id="priceTypeTableName" name="select" class="chzn-select"
								style="width: 200px;" tabindex="2">
								<option value="">请选择租赁类型</option>								
								<option value="wh_hours_price_type">分时租赁</option>								
								<option value="month_lease_price_type">长期租赁</option>								
							</select>&nbsp; &nbsp;
							<select id="relityGetSiteId" name="select" class="chzn-select"
								style="width: 200px;" tabindex="2">
								<option value="">请选择租赁点</option>								
							</select>
						</div>
						车牌: &nbsp;<input type="text" id="carNumber" />&nbsp; &nbsp;
						<input type="hidden" id="name" />
						<input type="hidden" id="phone" />
						<button class="publishbutton radius3" id="queryBtn">查询</button>
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
								<th class="head0" >订单号</th>
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
								<th class="head1">取车审查</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0" >订单号</th>
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
								<th class="head1">取车审查</th>
							</tr>
						</tfoot>
						<tbody> </tbody>
					</table>
					<br /> <br />
				</div>
				<!-- 普通客户 -->
			</div>
</html>

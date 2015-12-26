<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript" src="${ctx}/js/page/car/statusOtherWin.js"></script>
</head>
<div id="use" class="subcontent">
	<input type="hidden" value="${param.orderType }" id="orderType" />
	<input type="hidden" value="${param.siteId }" id="siteId" />
	<div class="overviewhead">
		<div class="overviewselect">
			<select id="curSiteId" name="select" class="chzn-select"
				style="width: 200px;" tabindex="2">
				<option value="">请选择站点</option>
			</select>&nbsp;&nbsp;
			<div class="overviewselect">
				<select id="curStatus" name="select" class="chzn-select"
					style="width: 200px;" tabindex="2">
					<option value="">请选择车辆租赁类型</option>
					<option value="2"
						<c:if test="${param.orderType == '2' }">selected="selected"</c:if>>长租车辆</option>
					<%-- <option value="使用"
						<c:if test="${param.orderType == '使用' }">selected="selected"</c:if>>已租</option>
					<option value="故障"
						<c:if test="${modelMap.status == '故障' }">selected="selected"</c:if>>故障</option>
					<option value="其他"
						<c:if test="${modelMap.status == '其他' }">selected="selected"</c:if>>其他</option> --%>
				</select>
			</div>
		</div>

		车牌号码: &nbsp;<input type="text" id="carNumber" /> &nbsp; &nbsp;
		<button class="publishbutton radius3" id="queryBtn">查询</button>
	</div>
	<br clear="all" />
	<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
		id="carsTable">
		<colgroup>
			<col class="con0" width="10%" />
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
				<th class="head0">商家</th>
				<th class="head1">车型</th>
				<th class="head0">当前站点</th>
				<th class="head1">码表数</th>
				<th class="head0">续航里程</th>
				<th class="head1">车辆状态</th>
			</tr>
		</thead>
		<tfoot>
			<tr>
				<th class="head0">汽车图片</th>
				<th class="head1">车牌号码</th>
				<th class="head0">商家</th>
				<th class="head1">车型</th>
				<th class="head0">当前站点</th>
				<th class="head1">码表数</th>
				<th class="head0">续航里程</th>
				<th class="head1">车辆状态</th>
			</tr>
		</tfoot>
		<tbody>
		</tbody>
	</table>
	<br /> <br />
</div>
</html>

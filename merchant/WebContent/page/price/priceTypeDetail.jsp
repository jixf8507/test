<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>

</head>

<body>
	<form class="stdform stdform2">
		<p>
			<label>套餐名称</label> <span class="field">${hoursPriceTypePO.typeName}&nbsp;</span>
		</p>
		<p>
			<label>每小时收费</label> <span class="field">${hoursPriceTypePO.oneHoursCost}&nbsp;&nbsp;（元）</span>
		</p>
		<p>
			<label>当日最高收费</label> <span class="field">${hoursPriceTypePO.maxCostForDay}&nbsp;&nbsp;（元）</span>
		</p>
		<p>
			<label>夜间收费</label> <span class="field">${hoursPriceTypePO.nightCost}&nbsp;&nbsp;（元）</span>
		</p>
		<p>
			<label>套餐时间范围</label> <span class="field">
				${hoursPriceTypePO.startTime} &nbsp;&nbsp;到&nbsp;&nbsp;
				${hoursPriceTypePO.endTime} </span>
		</p>
		<p>
			<label>每天取/还车时间范围</label> <span class="field">
				${hoursPriceTypePO.startWorkTime} &nbsp;&nbsp;到&nbsp;&nbsp;
				${hoursPriceTypePO.endWorkTime} </span>
		</p>
		<p>
			<label>每小时最大行驶公里数</label> <span class="field">${hoursPriceTypePO.perHoursKmsForDay}&nbsp;&nbsp;（公里）</span>
		</p>
		<p>
			<label>夜间最大行驶公里数</label> <span class="field">${hoursPriceTypePO.maxKmsForNight}&nbsp;&nbsp;（公里）</span>
		</p>
		<p>
			<label>每天最大行驶公里数</label> <span class="field">${hoursPriceTypePO.maxKmsForDay}&nbsp;&nbsp;（公里）</span>
		</p>
		<p>
			<label>超出每公里单价</label> <span class="field">${hoursPriceTypePO.perKmsPrice}&nbsp;&nbsp;（元）</span>
		</p>
		<p>
			<label>最少租赁天数</label> <span class="field">${hoursPriceTypePO.minDays}&nbsp;&nbsp;（天）</span>
		</p>
		<p>
			<label>最多租赁天数</label> <span class="field">${hoursPriceTypePO.maxDays}&nbsp;&nbsp;（天）</span>
		</p>
		<p>
			<label>预约预留分钟数</label> <span class="field">${hoursPriceTypePO.reservedMinute}&nbsp;&nbsp;（分钟）</span>
		</p>
		<p>
			<label>是否需要预付费</label> <span class="field">
			<c:if test="${hoursPriceTypePO.prepayment == 0 }">否</c:if>
			<c:if test="${hoursPriceTypePO.prepayment == 1 }">是</c:if>
			&nbsp;&nbsp;</span>
		</p>

	</form>
</body>
</html>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>

<script type="text/javascript"
	src="${ctx}/js/plugins/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="${ctx}/js/page/price/priceTypeAdd.js"></script>
</head>

<body>
					<form id="form1" class="stdform stdform2" method="post" action=" ">
						<p>
							<label>套餐名称</label> <span class="field"><input type="text"
								name="typeName" id="typeName" class="longinput"  style="width: 200px;" /></span>
						</p>
						<p>
							<label>每小时收费</label> <span class="field"><input
								type="text" name="oneHoursCost" id="oneHoursCost"
								class="longinput" style="width: 200px;" />&nbsp;&nbsp;（元）</span>
						</p>
						<p>
							<label>夜间收费</label> <span class="field"><input type="text"
								name="nightCost" id="nightCost" class="longinput"
								style="width: 200px;" />&nbsp;&nbsp;（元）</span>
						</p>
						<p>
							<label>当日最高收费</label> <span class="field"><input
								type="text" name="maxCostForDay" id="maxCostForDay"
								class="longinput" style="width: 200px;" />&nbsp;&nbsp;（元）</span>
						</p>
						<p>
							<label>套餐时间范围</label> <span class="field"> <select
								data-placeholder="选择开始时间" name="startTime" id="startTimeForDay"
								class="chzn-select" style="width: 100px;" tabindex="2">
									<option value=""></option>
							</select> &nbsp;&nbsp;到&nbsp;&nbsp; <select data-placeholder="选择结束时间"
								name="endTime" id="endTimeForDay" class="chzn-select"
								style="width: 100px;" tabindex="2">
									<option value=""></option>
							</select>
							</span>
						</p> 
						<p>
							<label>每天取/还车时间范围</label> <span class="field"> <select
								data-placeholder="选择开始时间" name="startWorkTime" id="startTimeForWork"
								class="chzn-select" style="width: 100px;" tabindex="2">
									<option value=""></option>
							</select> &nbsp;&nbsp;到&nbsp;&nbsp; <select data-placeholder="选择结束时间"
								name="endWorkTime" id="endTimeForWork" class="chzn-select"
								style="width: 100px;" tabindex="2">
									<option value=""></option>
							</select>
							</span>
						</p> 
						<p>
							<label>每小时最大行驶公里数</label> <span class="field"><input
								type="text" name="perHoursKmsForDay" id="perHoursKmsForDay"
								class="longinput" style="width: 200px;" />&nbsp;&nbsp;（公里）</span>
						</p>
						<p>
							<label>夜间最大行驶公里数</label> <span class="field"><input
								type="text" name="maxKmsForNight" id="maxKmsForNight"
								class="longinput" style="width: 200px;" />&nbsp;&nbsp;（公里）</span>
						</p>
						<p>
							<label>每天最大行驶公里数</label> <span class="field"><input
								type="text" name="maxKmsForDay" id="maxKmsForDay"
								class="longinput" style="width: 200px;" />&nbsp;&nbsp;（公里）</span>
						</p>
						<p>
							<label>超出每公里单价</label> <span class="field"><input
								type="text" name="perKmsPrice" id="perKmsPrice"
								class="longinput" style="width: 200px;" />&nbsp;&nbsp;（元）</span>
						</p>
						<p>
							<label>最少租赁天数</label> <span class="field"><input
								type="text" name="minDays" id="minDays"
								class="longinput" style="width: 200px;" />&nbsp;&nbsp;（天）</span>
						</p>
						<p>
							<label>最多租赁天数</label> <span class="field"><input
								type="text" name="maxDays" id="maxDays"
								class="longinput" style="width: 200px;" />&nbsp;&nbsp;（天）</span>
						</p>
						<p>
							<label>预约预留分钟数</label> <span class="field"><input
								type="text" name="reservedMinute" id="reservedMinute"
								class="longinput" style="width: 200px;" />&nbsp;&nbsp;（分钟）</span>
						</p>
						<p>
							<label>是否需要预付费</label> <span class="field"> &nbsp;<input type="radio" value="0"
								name="prepayment"  checked="checked" /> 否 &nbsp; &nbsp;<input
								type="radio" value="1" name="prepayment"/>
								是 &nbsp; &nbsp; 
							</span>
						</p>
						
					</form>
</body>
</html>

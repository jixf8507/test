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
	src="${ctx}/js/plugins/jquery.datetimepicker.js"></script>
<script type="text/javascript" src="${ctx}/js/page/work/doWork.js"></script>
</head>

<div id="contentwrapper" class="contentwrapper">
	<div id="customer" class="subcontent">
		<form id="form1" class="stdform stdform2">
			<p>
				<label>工单类型</label> <span class="field">${workOrderPO.typeName}&nbsp;&nbsp;</span>
			</p>
			<p>
				<label>工单名称</label> <span class="field">${workOrderPO.workName}&nbsp;&nbsp;</span>
			</p>
			<p>
				<label>紧急程度</label> <span class="field">${workOrderPO.urgency}&nbsp;&nbsp;</span>
			</p>
			<p>
				<label>工单描述</label> <span class="field">${workOrderPO.workDes}&nbsp;&nbsp;</span>
			</p>
			<p>
				<label>负责人</label> <span class="field">${workOrderPO.merchantUserPO.userName}&nbsp;&nbsp;</span>
			</p>
			<p>
				<label>负责人电话</label> <span class="field">${workOrderPO.merchantUserPO.mobilePhone}&nbsp;&nbsp;</span>
			</p>
			<p>
				<label>发布时间</label> <span class="field">${workOrderPO.createdTime}&nbsp;&nbsp;</span>
			</p>
			<p>
				<label>发布人</label> <span class="field">${workOrderPO.transactUser}&nbsp;&nbsp;</span>
			</p>
			<p>
				<label>选择工单状态</label> <span class="field"> <select
					id="workStatus" name="workStatus" class="chzn-select"
					style="width: 200px;">
						<option value="">请选择工单状态</option>
						<option value="待处理"
							<c:if test="${workOrderPO.workStatus=='待处理'}">selected="selected"</c:if>>待处理</option>
						<option value="处理中"
							<c:if test="${workOrderPO.workStatus=='处理中'}">selected="selected"</c:if>>处理中</option>
						<option value="已处理"
							<c:if test="${workOrderPO.workStatus=='已处理'}">selected="selected"</c:if>>已处理</option>
				</select></span>
			</p>
			<input type="hidden" name="id" value="${workOrderPO.id}" />
			<p>
				<label>处理时间</label> <span class="field"><input type="text"
					name="updated" id="updated" style="width: 200px;"
					<c:if test="${workOrderPO.workStatus=='待处理'}">value=""</c:if>
					<c:if test="${workOrderPO.workStatus!='待处理'}">value="${workOrderPO.updatedTime}"</c:if>></input></span>
			</p>
			<p>
				<label>处理描述</label> <span class="field"><textarea
						style="width: 200px;" id="transactDes" name="transactDes"><c:if
							test="${workOrderPO.workStatus!='待处理'}">${workOrderPO.transactDes}</c:if>
						</textarea></span>
			</p>

		</form>
	</div>
</div>



</html>

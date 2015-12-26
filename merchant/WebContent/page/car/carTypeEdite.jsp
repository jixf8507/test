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
<script type="text/javascript"
	src="${ctx}/js/plugins/jquery.validate.min.js"></script>

</head>
<script type="text/javascript">
	var typeDetail = {
		components : [],
		status : [],
		describe : []
	};
</script>
<c:forEach items="${typePO.detailPOs}" var="detail">
	<script type="text/javascript">
		typeDetail['components'].push('${detail.component}');
		typeDetail['status'].push('${detail.status}');
		typeDetail['describe'].push('${detail.describe}');
	</script>
</c:forEach>
<script type="text/javascript" src="${ctx}/js/page/car/carTypeEdite.js"></script>
<body>
	<form id="form1" class="stdform stdform2" method="post"
		action="${ctx}/car/type/saveEdite.htm?">
		<input type="hidden" value="${typePO.id}" name="id" id="id" /> <input
			type="hidden" value="${typePO.carManufacturerId}"
			id="hiddManufacturerId" />
		<p>
			<label>车辆型号</label> <span class="field"><input type="text"
				value="${typePO.typeName}" name="typeName" id="siteName"
				class="longinput" /></span>
		</p>
		<p>
			<label>供应商选择</label> <span class="field"> <select
				name="carManufacturerId" id="carManufacturerId">
					<option value="">请选择</option>
			</select>
			</span>
		</p>
		<p>
			<label>座位数</label><span class="field"><input type="text"
				value="${typePO.seatCount}" name="seatCount" id="seatCount"
				class="longinput" /></span>
		</p>
		<p>
			<label>车颜色</label> <span class="field"><input type="text"
				value="${typePO.color}" name="color" id="color" class="longinput" /><br /></span>
			<small class="desc">注：多种颜色用","号隔开。</small>
		</p>
		<p>
			<label>供能方式</label> <span class="field"> <select name="energy"
				id="energy">
					<option value="">请选择</option>
					<option value="电动"
						<c:if test="${typePO.energy=='电动'}">selected="selected"</c:if>>电动</option>
					<option value="燃油"
						<c:if test="${typePO.energy=='燃油'}">selected="selected"</c:if>>燃油</option>
			</select>
			</span>
		</p>
		<p>
			<table cellpadding="0" cellspacing="0" border="0" class="stdtable">
				<colgroup>
					<col class="con0" />
					<col class="con1" />
					<col class="con0" />
					<col class="con1" />
				</colgroup>
				<thead>
					<tr>
						<th class="head0">审核检测项</th>
						<th class="head1">当前状态</th>
						<th class="head0">描述</th>
						<th class="head1"><input type="button" value="新增"
							onclick="checkDetailTable.add('','正常','正常')" /></th>
					</tr>
				</thead>
				<tbody id="checkDetailTable">

				</tbody>
			</table>
		</p>
	</form>


</body>
</html>

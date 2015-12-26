<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>

<script type="text/javascript" src="${ctx}/js/page/car/carPriceType.js"></script>
</head>

<body>

	<form action="" id="myForm">
		<input type="hidden" id="carId" name="carId" value="${param.id}" />
		<input type="hidden" id="tableName" name="tableName" value="${param.tableName}" />
		<table cellpadding="0" cellspacing="0" border="0" class="stdtable">
			<thead>
				<tr>
					<th class="head1 "><input type="checkbox" class="checkall"
						id="all" /></th>
					<th class="head0">套餐名称</th>
				</tr>
			</thead>
			<tbody id="prices">

			</tbody>
		</table>
	</form>
</body>
</html>

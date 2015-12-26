<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript"
	src="${ctx}/js/page/order/importResult.js"></script>
<style type="text/css">
.myTb {
	border-top: 1px solid #999;
	border-left: 1px solid #999;
	border-spacing: 0;
}

.myTb td {
	border-bottom: 1px solid #999;
	border-right: 1px solid #999;
	padding: 2px 1px;
}
</style>
</head>
<body>
	<form id="form1" class="stdform stdform2" method="post">
		<table class="myTb" width="100%">
			<c:choose>
				<c:when test="${list.size()==0}">
					<script>
						Dialog.alert("您要上传文件记录条数为:0条", function() {
							window.parent.location.reload();
						});
					</script>

				</c:when>
				<c:when test="${list.size()!=0}">
					<tr>
						<td>车牌号:</td>
						<td>违章时间:</td>
						<td>违章地点:</td>
						<td>违章原因:</td>
						<td>订单号:</td>
						<td>取车时间:</td>
						<td>还车时间:</td>
					</tr>
					<c:forEach var="student" items="${list}">

						<input type="hidden" name="orderId" id="orderId"
							value="${student.orderId}"></input>
						<input type="hidden" name="prccancyRecordTime"
							value="${student.time}"></input>
						<input type="hidden" name="address" value="${student.address}"></input>
						<input type="hidden" name="info" value="${student.info}"></input>
						<tr>
							<td>${student.carNumber}</td>
							<td>${student.time}</td>
							<td>${student.address}</td>
							<td>${student.info}</td>
							<td>${student.orderId}</td>
							<td>${student.schemingGetTime}</td>
							<td>${student.schemingReturnTime}</td>
						</tr>
					</c:forEach>
					<script>
						Dialog.alert("您要上传文件记录条数为:${list.size()}条");
					</script>
				</c:when>

			</c:choose>
		</table>
	</form>
</body>
</html>

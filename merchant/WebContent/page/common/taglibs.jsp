<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${ctx}/css/style.default.css"
	type="text/css" />
<script type="text/javascript" src="${ctx}/js/plugins/jquery-1.7.min.js"></script>
<script type="text/javascript"
	src="${ctx}/js/plugins/jquery-ui-1.8.16.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/js/plugins/jquery.cookie.js"></script>
<script type="text/javascript" src="${ctx}/js/plugins/colorpicker.js"></script>
<script type="text/javascript" src="${ctx}/js/plugins/jquery.jgrowl.js"></script>
<script type="text/javascript" src="${ctx}/js/plugins/jquery.alerts.js"></script>
<script type="text/javascript"
	src="${ctx}/js/plugins/jquery.uniform.min.js"></script>
<script type="text/javascript"
	src="${ctx}/js/plugins/jquery.dataTables.min.js"></script>

<%-- <script type="text/javascript"
	src="${ctx}/js/plugins/jquery.dataTables.js"></script> --%>
	
<script type="text/javascript"
	src="${ctx}/js/plugins/chosen.jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/custom/general.js"></script>
<script type="text/javascript" src="${ctx}/js/pageDateTables.js"></script>
<script type="text/javascript" src="${ctx}/js/comBox.js"></script>
<script type="text/javascript">
	var contextPath = '${ctx}';
	var systemName = '${sessionScope.user.systemName}';
</script>
<script type="text/javascript" src="${ctx}/js/zdialog/zDrag.js"></script>
<script type="text/javascript" src="${ctx}/js/zdialog/zDialog.js"></script>
<c:if test="${sessionScope.user.systemName == 'eakay'}">
	<title>易开租车-商家服务管理中心</title>
</c:if>
<c:if test="${sessionScope.user.systemName == 'stake'}">
	<title>充电桩监控管理系统-${sessionScope.user.merchantPO.merchantName}</title>
</c:if>

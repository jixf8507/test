<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript"
	src="${ctx}/js/plugins/jquery.slimscroll.js"></script>
</head>
	<div id="scroll1">
		<img src="${ctx}/images/loaders/loader10.gif" alt="正在加载数据">
	</div>
	<input type="hidden" value="${param.siteId }" id="siteId" name="siteId" />
<script type="text/javascript">
	jQuery('#scroll1').slimscroll({
		color : '#666',
		size : '10px',
		width : 'auto',
		height : '440px'
	});
</script>
</html>

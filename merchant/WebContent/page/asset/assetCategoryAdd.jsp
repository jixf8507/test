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
	src="${ctx}/js/page/asset/assetCategoryAdd.js"></script>
</head>
<div id="contentwrapper" class="contentwrapper">
	<div id="list" class="subcontent">

		<form id="form2" class="stdform stdform2" method="post">
			<p>
				<label>资产种类</label> <span class="field"> <input type="text"
					value="" name="name" id="name" class="longinput"
					style="width: 200px;" />&nbsp;&nbsp; <input type="hidden" name="id"
					value="-1" id="id" />
				</span>
			</p>
		</form>
	</div>
</div>
</html>

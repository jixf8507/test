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
<script type="text/javascript" src="${ctx}/js/page/asset/assetAdd.js"></script>
</head>

<form id="form2" class="stdform stdform2" method="post">

	<p>
		<label>资产类型</label> <span class="field"> <select
			id="categoryId" name="categoryId" class="chzn-select"
			style="width: 200px;" tabindex="2">
				<option value="">请选择</option>
		</select>&nbsp;&nbsp;
		</span>
	</p>
	<p>
		<label>资产名称</label> <span class="field"> <input type="text"
			name="assetsName" id="assetsName" class="longinput"
			style="width: 200px;" />&nbsp;&nbsp;
		</span>
	</p>
	<p>
		<label>资产型号</label> <span class="field"> <input type="text"
			name="model" id="model" class="longinput" style="width: 200px;" />&nbsp;&nbsp;
		</span>
	</p>
	<p>
		<label>增加方式</label> <span class="field"> <select
			id="increasingMode" name="increasingMode" class="chzn-select"
			style="width: 200px;" tabindex="2">
				<option value="">请选择增加方式</option>
				<option value="外购">外购</option>
				<option value="捐赠">捐赠</option>
		</select>&nbsp;&nbsp;
		</span>
	</p>
	<p>
		<label>计量单位</label> <span class="field"> <select id="unit"
			name="unit" class="chzn-select" style="width: 200px;" tabindex="2">
				<option value="">请选择计量单位</option>
				<option value="个">个</option>
				<option value="台">台</option>
				<option value="辆">辆</option>
				<option value="张">张</option>
				<option value="箱">箱</option>
		</select>&nbsp;&nbsp;
		</span>
	</p>
	<p>
		<label>供应商选择</label> <span class="field"> <select
			name="manufacturId" id="manufacturId" style="width: 200px;">
				<option value="">请选择</option>
		</select>
		</span>
	</p>
	<p>
		<label>购置日期</label> <span class="field"> <input type="text"
			name="purchaseDate" id="purchaseDate" class="longinput"
			style="width: 200px;" />&nbsp;&nbsp;
		</span>
	</p>
	<p>
		<label>费用</label> <span class="field"> <input type="text"
			name="fee" id="fee" class="longinput" style="width: 200px;" />&nbsp;&nbsp;
		</span>
	</p>

</form>
</html>

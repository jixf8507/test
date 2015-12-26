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
	<form id="form1" class="stdform stdform2" method="post">
			
		<p>
			<label>冻结原因</label><span class="field">
			<input type="radio" value="违约" name="describe" checked="checked"/>&nbsp;&nbsp;违约<br/>
			<input type="radio" value="恶意破坏车辆" name="describe"/>&nbsp;&nbsp;恶意破坏车辆<br/>
			<input type="radio" value="违章次数超过3次" name="describe"/>&nbsp;&nbsp;违章次数超过3次<br/>
			<input type="radio" value="其他原因：" name="describe" id="sel"/>&nbsp;&nbsp;其他原因
			</span>
		</p>
		<input type="hidden" name="id" value="${modelMap['id']}" />
		 <input type="hidden" name="status" id="status" value="冻结" /> 
		<p>
			<label>其他原因 </label><span class="field"> <textarea name="other" id="other" ></textarea> </span>
		</p>
	</form>
</body>
</html>

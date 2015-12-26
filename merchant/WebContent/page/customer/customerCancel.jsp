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
			<label>退款原因</label><span class="field">
			<input type="radio" value="车辆问题" name="describe" checked="checked"/>&nbsp;&nbsp;车辆问题<br/>
			<input type="radio" value="手续繁琐" name="describe"/>&nbsp;&nbsp;手续繁琐<br/>
			<input type="radio" value="费用过高" name="describe"/>&nbsp;&nbsp;费用过高<br/>
			<input type="radio" value="充电麻烦" name="describe"/>&nbsp;&nbsp;充电麻烦<br/> 
			<input type="radio" value="自身问题" name="describe"/>&nbsp;&nbsp;自身问题<br/>
			<input type="radio" value="续航里程太短" name="describe"/>&nbsp;&nbsp;续航里程太短<br/> 
			<input type="radio" value="咨询解释不到位" name="describe"/>&nbsp;&nbsp;咨询解释不到位<br/>
			<input type="radio" value="租赁点服务不到位" name="describe"/>&nbsp;&nbsp;租赁点服务不到位 <br/>
			<input type="radio" value="救援过慢或未救援" name="describe"/>&nbsp;&nbsp;救援过慢或未救援 <br/>
			<input type="radio" value="其他原因：" name="describe"/>&nbsp;&nbsp;其他原因
			</span>
		</p>
		<input type="hidden" name="id" value="${modelMap['id']}" />
		 <input type="hidden" name="status" id="status" value="注销" />
		<p>
			<label>其他原因 </label><span class="field"> <textarea name="other" id="other" ></textarea> </span>
		</p>
	</form>
</body>
</html>

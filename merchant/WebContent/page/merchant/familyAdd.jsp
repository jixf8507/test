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
<script type="text/javascript" src="${ctx}/js/ajaxfileupload.js"></script>
<script type="text/javascript"
	src="${ctx}/js/page/merchant/familyAdd.js"></script>
</head>

<body>


	<form id="form1" class="stdform stdform2" method="post" action="">
		<p>
			<label>选择用户</label> 
			<span class="field">
					<input type="hidden"
						name="customerid" id="customerid" class="longinput" style="width: 40px;" /><br/>
					<input
						type="button" id="findCustomerBtn" value="查询用户" /> 	
			</span>

		</p>
		<!--<p>
			<label>短信验证码</label> <span class="field"> <input
				type="text" name="verCode" id="verCode" class="longinput"
				style="width: 350px;" />&nbsp;&nbsp;<input type="button"
				value="获取验证码" " id="sendVerCode"></input>
			</span>
		</p>-->
		<p>
			<label>负责人</label> <span class="field"><input type="text"
				name="householder" id="householder" class="longinput" readonly="readonly" style="width: 350px;" />&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>家庭住址</label> <span class="field"><input type="text"
				name="homeaddress" id="homeaddress" class="longinput" id="idCard"
				style="width: 350px;" />&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>联系方式</label> <span class="field"><input type="text"
				name="homephone" id="homephone" class="longinput" style="width: 350px;" />&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>*核对身份证号码*</label> 
				<span class="field">
			<!-- <input type="text" readonly="readonly"
				 class="longinput" style="width: 350px;" />&nbsp;&nbsp; -->
					<span name="idCard" id="idCard"></span>
				</span>
		</p>
		<p>
			<label>身份证/户口本图片上传</label> 
			<span class="field"> 
				<input
					type="file" id="idFamilyImgFile" /> 
				<span
					style="color: red; font-size: 14px;">注：只能上传jpg或png文件 <br />
						<br /> 
						
						<img id="idFamilyImg" src="${ctx}/img/car.png" alt="汽车图片"
							class="uploadImg" /> 
						
						<input type="hidden" name="idFamilyImgHidden"
							id="idFamilyImgHidden" />
						
						<input type="hidden" name="familyImgHidden"
							id="familyImgHidden" />
				</span>
			</span>
		</p>
	</form>
</body>
</html>

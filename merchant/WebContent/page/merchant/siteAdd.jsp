<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript"
	src="${ctx}/js/plugins/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx}/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${ctx}/js/page/merchant/siteAdd.js"></script>
<body>
	<form id="form1" class="stdform stdform2" method="post" action="">
		<p>
			<label>服务中心名称</label> <span class="field"><input
								type="text" name="siteName" id="siteName" class="longinput" /></span>
		</p>
		<p>
			<label>具体地址</label> <span class="field"><input type="text"
								name="address" id="address" class="longinput" /></span>
		</p>
		<p>
			<label>负责人</label> <span class="field"><input type="text"
								name="principal" id="principal" class="longinput" /></span>
		</p>
		<p>
			<label>联系电话</label> <span class="field"><input type="text"
								name="phone" id="phone" class="longinput" /></span>
		</p>
		<p>
			<label>所在省</label> <span class="field"><select
								data-placeholder="选择" name="province" id="province"
								class="chzn-select" style="width: 200px;" tabindex="2">
									<option value=""></option>
							</select>&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>所在市</label> <span class="field" id="cityDiv"><select
								data-placeholder="选择" name="city" id="city" class="chzn-select"
								style="width: 200px;" tabindex="2">
									<option value=""></option>
							</select>&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>经纬度</label> <span class="field"><input type="text"
								id="xy" name="xy" class="longinput" onclick="showMap()" /> <input
								type="button" value="点击选择" onclick="showMap()" /> <input
								type="hidden" name="lng" id="lng" class="longinput" /> <input
								type="hidden" name="lat" id="lat" class="longinput" /></span>
		</p>

		<p>
			<label>类型</label> <span class="field"><input
								type="checkbox" value="1" name="types" /> 租赁点 &nbsp; &nbsp; <input
								type="checkbox" value="2" name="types" /> 充电站 &nbsp; &nbsp; <input
								type="checkbox" name="types" value="4" /> 停车场 &nbsp; &nbsp; </span>
		</p>
		<p>
			<label>服务中心图片</label> <span class="field"> <input
								type="file" name="upload" id="imgUrlFile" /> <span
								style="color: red; font-size: 14px;">注：只能上传jpg或png文件，大小300*125<br />
									<br /> <img id="imgUrl" src="${ctx}/img/car.png" alt="汽车图片"
									class="uploadImg" /> <input type="hidden" name="imgUrl"
									id="imgUrlHidden" /></span>
							</span>
		</p>
	</form>
</body>
</html>
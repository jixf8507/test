<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<link rel="stylesheet" href="${ctx}/js/imgSwitch/css/style.css"
	type="text/css" />
<script type="text/javascript" src="${ctx}/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${ctx}/js/page/car/carPicture.js"></script>
</head>

<div id="contentwrapper" class="contentwrapper">
	<div id="pic" class="subcontent">
		<form id="imgForm" class="stdform stdform2">
			<p>
				<label>车辆图片上传</label><span class="field"> <input type="file"
					id="carImgFile" /> <input type="hidden" id="fileUrl"
					name="fileUrl" value="" /> <input type="hidden" id="fileName"
					name="fileName" value="" /> <input type="button" value="上传"
					id="uploadBut" /> <span style="color: red; font-size: 14px;">&nbsp;&nbsp;&nbsp;注：只能上传jpg或png文件</span>
					<!-- <input type="button" value="上传" id="newUploadBut" />  -->
					</span>
			</p>
			<!-- 轮播图片 -->
			<div id="home_banner">
				<a id="big_a"><div id="big_img"></div></a>
				<div class="relative">
					<div id="small_img">
						<div class="maxwidth">
							<a id="small_pre"><</a>
							<div id="small_imgs"></div>
							<a id="small_next">></a>
						</div>
					</div>
				</div>
			</div>
			<!-- 轮播图片 -->
			<input type="hidden" name="carId" id="carId" value="${param.carId}" />
			<input type="hidden" name="carNumber" id="carNumber" value="${carPO.carNumber}" />
		</form>
	</div>
</div>
</html>

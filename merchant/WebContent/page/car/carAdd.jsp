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
<script type="text/javascript" src="${ctx}/js/page/car/carAdd.js"></script>
</head>

<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />

		<div class="centercontent">
			<div class="pageheader">
				<h1 class="pagetitle">添加车辆</h1>
			</div>
			<!--pageheader-->

			<div id="contentwrapper" class="contentwrapper">
				<div id="list" class="subcontent">
					<form id="form1" class="stdform stdform2" method="post"
						action="${ctx}/car/car/saveAdd.htm?" enctype="multipart/form-data">
						<p>
							<label>车牌号码</label> <span class="field"><input type="text"
								name="carNumber" id="carNumber" class="longinput" style="width: 350px;"/></span>
						</p>
						<p>
							<label>车类型</label> <span class="field"> <select
								name="carTypeId" id="carTypeId" class="chzn-select"
								style="width: 200px;">
									<option value="">请选择</option>
							</select>
							</span>
						</p>
						<p>
							<label>车颜色</label> <span class="field" id="colorDiv"> <select
								name="color" id="carColor" style="width: 200px;">
									<option value="">请选择</option>
							</select>
							</span>
						</p>
						<p>
							<label>车辆识别号</label> <span class="field"><input
								type="text" name="vin" id="vin" class="longinput" style="width: 350px;"/></span>
						</p>
						<p>
							<label>车辆描述</label> <span class="field"><input type="text"
								name="describe" id="describe" class="longinput" style="width: 350px;"/></span>
						</p>
						<p>
							<label>充满电需要的时间间隔(分钟)</label> <span class="field"><input
								type="text" name="chargeDuration" id="chargeDuration"
								class="longinput" style="width: 350px;"/></span>
						</p>
						<p>
							<label>当前站点</label> <span class="field"> <select
								class="chzn-select" style="width: 200px;" name="curSiteId"
								id="curSiteId">
									<option value="">请选择</option>
							</select>
							</span>
						</p>
						<p>
							<label>汽车图片上传</label> <span class="field"> <input
								type="file" name="upload" id="bigImgFile" /> <span
								style="color: red; font-size: 14px;">注：只能上传jpg或png文件<br />
									<br /> <img id="bigImg" src="${ctx}/img/car.png" alt="汽车图片"
									class="uploadImg" /> <input type="hidden" name="bigIcon"
									id="bigImgHidden" /></span>
							</span>
						</p>
						<p>
							<label>保险起止时间</label> <span class="field"><input
								type="text" name="nsuranceRange" id="nsuranceRange"
								class="longinput" style="width: 350px;"/></span>
						</p>
						<p>
							<label>年审时间</label> <span class="field"><input type="text"
								name="checkYearDate" id="checkYearDate" class="longinput" style="width: 350px;"/></span>
						</p>

						<p class="stdformbutton">
							<button class="submit radius2">保存</button>
							<input type="reset" class="reset radius2" value="重置" />
						</p>
					</form>
					<br />
				</div>
				<!-- #add -->
			</div>
			<!--contentwrapper-->
			<br clear="all" />
		</div>
		<!-- centercontent -->
	</div>
	<!--bodywrapper-->

</body>
</html>

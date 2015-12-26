<!DOCTYPE PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-后台管理中心</title>
<script type="text/javascript" src="${ctx}/js/page/order/loadFile.js"></script>
<script type="text/javascript">
	function displayDate() {
		var filepath = jQuery('#loadFile').val();
		var extStart = filepath.lastIndexOf(".");
		var ext = filepath.substring(extStart, filepath.length).toUpperCase();
		if (jQuery('#loadFile').val() == "") {
			alert("请选择文件");
			return false;
		}
		if ((ext != ".XLS" && ext != ".XLSX")) {
			alert("文件格式解析错误");
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<form method="POST" id="form1" class="stdform stdform2"
		enctype="multipart/form-data" onsubmit="return displayDate()"
		action="${ctx}/order/peccancyRecord/importToExcel.htm?">
		<p>
			<label>违章文件上传</label> <span class="field"> <input type="file"
				name="loadFile" id="loadFile" value="" /> <span
				style="color: red; font-size: 14px;"> 注：请上传excel文件
					<button id="btn" name="btn">上传文件</button>
			</span></span>
		</p>
	</form>
</body>
</html>

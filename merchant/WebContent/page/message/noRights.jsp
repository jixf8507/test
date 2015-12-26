<!DOCTYPE PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-后台管理中心</title>
</head>
<body class="withvernav">
	<div class="bodywrapper">
		<div class="topheader orangeborderbottom5">
			<div class="left">
				<h1 class="logo">
					易开平台.<span></span>
				</h1>
				<span class="slogan">商家服务管理中心</span> <br clear="all" />

			</div>
			<!--left-->

			<div class="right">
				<!--<div class="notification">
                <a class="count" href="notifications.html"><span>9</span></a>
        	</div>
			-->


			</div>
			<!--right-->
		</div>
		<!--topheader-->


		<div class="contentwrapper padding10">
			<div class="errorwrapper error404">
				<div class="errorcontent">
					<h1>您还没有启用任何功能模块权限</h1>
					<h3>请先联系商家管理员给您启用功能权限.</h3>


					<br />
					<button class="stdbtn btn_black" onclick="history.back()">返回上一页</button>
					&nbsp;
					<button onclick="location.href='${ctx}/index.html'"
						class="stdbtn btn_orange">返回主页</button>
				</div>
				<!--errorcontent-->
			</div>
			<!--errorwrapper-->
		</div>


	</div>
	<!--bodywrapper-->

</body>
</html>
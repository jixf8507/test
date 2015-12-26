<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
	var updatePwd = function() {
		var diag = new Dialog();
		diag.Width = 600;
		diag.Height = 300;
		diag.Title = "修改密码";
		diag.URL = contextPath + "/page/updatePwd.jsp";
		diag.MessageTitle = '修改密码';
		diag.OKEvent = function() {
			diag.innerFrame.contentWindow.submit(outLogin);
		};// 点击确定后调用的方法
		diag.show();
	};

	// 保存方法
	var outLogin = function() {
		location.href = contextPath + '/system/login/out.htm';
	};
</script>
<div class="topheader">
	<div class="left">
		<c:if test="${sessionScope.user.systemName == 'eakay'}">
			<h1 class="logo">
				易开租车.<span>商家服务管理中心</span>
			</h1>
			<span class="slogan">${sessionScope.user.merchantPO.merchantName}</span>
		</c:if>
		<c:if test="${sessionScope.user.systemName != 'eakay'}">
			<h1 class="logo">
				充电桩监控管理系统.<span>${sessionScope.user.merchantPO.merchantName}</span>
			</h1>
			<span class="slogan"></span>
		</c:if>
		<br clear="all" />
	</div>
	<!--left-->

	<div class="right">
		<!--<div class="notification">
                <a class="count" href="ajax/notifications.html"><span>9</span></a>
        	</div>-->
		<div class="userinfo">
			<img src="${ctx}/images/thumbs/avatar.png" alt="" /><span>
				${sessionScope.user.name}</span>
		</div>
		<!--userinfo-->

		<div class="userinfodrop">
			<div class="avatar">
				<a href=""><img src="${ctx}/images/thumbs/avatarbig.png" alt="" /></a>
				<div class="changetheme">
					切换主题: <br /> <a class="default"></a> <a class="blueline"></a> <a
						class="greenline"></a> <a class="contrast"></a> <a
						class="custombg"></a>
				</div>
			</div>
			<!--avatar-->
			<div class="userdata">
				<h4>${sessionScope.user.name}</h4>
				<span class="email">${sessionScope.user.name}</span>
				<ul>
					<li><a href="javaScript:updatePwd()">修改密码</a></li>
					 <li><a href="${ctx}/help.html" target="blank">使用帮助</a></li>
					<c:if test="${sessionScope.user.systemName == 'stake'}">
						<li><a href="${ctx}/system/stakeLogin/out.htm">退出</a></li>
					</c:if>
					<c:if test="${sessionScope.user.systemName != 'stake'}">
						<li><a href="${ctx}/system/login/out.htm">退出</a></li>
					</c:if>
				</ul>
			</div>
			<!--userdata-->
		</div>
		<!--userinfodrop-->
	</div>
	<!--right-->
</div>
<!--topheader-->


<div class="header">
	<ul class="headermenu">
		<c:forEach items="${sessionScope.user.fmenus}" var="fMenu">
			<li
				<c:if test="${sessionScope.user.fstMenuCode == fMenu.menuCode}">
				class="current" </c:if>><a
				href="${ctx}/${fMenu.moduleUrl}"><span
					class="icon ${fMenu.icon}"></span>${fMenu.moduleName}</a></li>
		</c:forEach>
		<!--<c:if test="${sessionScope.user.systemName == 'eakay'}">
			<li><a href="${ctx}/page/led/led.html" target="_blank"><span
					class="icon icon-flatscreen"></span>大屏监控</a></li>
		</c:if>-->
	</ul>

</div>

<!--header-->
<div class="vernav2 iconmenu">
	<ul>
		<c:forEach
			items="${sessionScope.user.scdMenuMap[sessionScope.user.fstMenuCode]}"
			var="sMenu">
			<c:set value="${sessionScope.user.thdMenuMap[sMenu.menuCode]}"
				var="sonMenus" />

			<c:if test="${sonMenus==null}">
				<li
					<c:if test="${sessionScope.user.scdMenuCode == sMenu.menuCode}">class="current" </c:if>><a
					href="${ctx}/${sMenu.moduleUrl}" class="elements">${sMenu.moduleName}</a></li>

			</c:if>
			<c:if test="${sonMenus!=null}">
				<li
					<c:if test="${sessionScope.user.scdMenuCode == sMenu.menuCode}">class="current" </c:if>><a
					href="#${sMenu.menuCode}" class="elements">${sMenu.moduleName}</a>
					<span class="arrow"></span>
					<ul id="${sMenu.menuCode}">
						<c:forEach items="${sessionScope.user.thdMenuMap[sMenu.menuCode]}"
							var="tMenu">
							<li
								<c:if test="${sessionScope.user.thdMenuCode == tMenu.menuCode}">class="current" </c:if>><a
								href="${ctx}/${tMenu.moduleUrl}">${tMenu.moduleName}</a></li>
						</c:forEach>
					</ul></li>
			</c:if>

		</c:forEach>
	</ul>
	<a class="togglemenu"></a> <br /> <br />
</div>
<!--leftmenu-->
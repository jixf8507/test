<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>

<script type="text/javascript"
	src="${ctx}/js/page/merchant/userWin.js"></script>
</head>

<body>
					<div class="overviewhead">
						<div class="overviewselect">
							<select id="rights"
								class="chzn-select" style="width: 200px;" tabindex="2">
								<option value="">请选择员工角色</option>
								<option value="商家管理员">商家管理员</option>
								<option value="站点管理员">站点管理员</option>
								<option value="站点工作人员">站点工作人员</option>
							</select>
							<select  id="siteId"
								class="chzn-select" style="width: 200px;" tabindex="2">
								<option value=""></option>
							</select>
						</div>
						姓名: &nbsp;<input type="text" id="userName" /> &nbsp;  
						手机: &nbsp;<input type="text" id="mobilePhone" /> &nbsp;  
						<button class="publishbutton radius3" id="queryBtn">查询</button>  
					</div>
					<br clear="all" />
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="merchantUserTable">
						<colgroup>
					     	<col class="con0" width="10%" />
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
						</colgroup>
						<thead>
							<tr>
								<th class="head0">选择</th>
								<th class="head1">姓名</th>
								<th class="head0">登录邮箱</th>
								<th class="head1">手机号码</th>
								<th class="head0">所属站点</th>
								<th class="head1">角色</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">选择</th>
								<th class="head1">姓名</th>
								<th class="head0">登录邮箱</th>
								<th class="head1">手机号码</th>
								<th class="head0">所属站点</th>
								<th class="head1">角色</th>
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>
</body>
</html>

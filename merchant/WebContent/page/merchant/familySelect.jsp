<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript" src="${ctx}/js/page/merchant/familySelect.js"></script>
</head>

<body >
	
			<div class="pageheader">
				<ul class="hornav">
					<li class="current"><a href="#normal">当前客户...</a></li>
					<%-- <li><a href="#freeze" id="freezeDiv">已冻结客户...</a></li>
					<li><a href="#cancel" id="cancelDiv">已注销客户...</a></li>--%>
				</ul>
			</div>
			<div id="contentwrapper" class="contentwrapper">

				<div id="normal" class="subcontent">
					<div class="overviewhead">
						身份证: &nbsp;<input type="text" id="normalIdCard" /> &nbsp; 手机号码:
						&nbsp;<input type="text" id="normalPhone" /> &nbsp;客户姓名: &nbsp;<input
							type="text" id="normalName" /> &nbsp; &nbsp;<br/><br/>
							日期: &nbsp;&nbsp;&nbsp;&nbsp;<input type="text" 
							id="beginTime" /> &nbsp; &nbsp;&nbsp;&nbsp; 到:
						&nbsp;&nbsp;&nbsp;<input type="text" id="endTime" />&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="publishbutton radius3" id="normalQueryBtn">查询</button>
					</div>
					<!--overviewhead-->

					<br clear="all" />
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="normalCustomersTable">
						<colgroup>
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
						</colgroup>
						<thead>
							<tr>
								<th class="head0">操作</th>
								<th class="head0">身份证/驾驶证</th>
								<th class="head1">手机号码</th>
								<th class="head0">姓名</th>
								<th class="head1">家庭住址</th>							
								<th class="head0">保证金</th>
								<th class="head1">余额</th>
								<th class="head0">状态</th>
								<th class="head1">开户时间</th>
								<th class="head0">开户人</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">操作</th>
								<th class="head0">身份证/驾驶证</th>
								<th class="head1">手机号码</th>
								<th class="head0">姓名</th>
								<th class="head1">家庭住址</th>							
								<th class="head0">保证金</th>
								<th class="head1">余额</th>
								<th class="head0">状态</th>
								<th class="head1">开户时间</th>
								<th class="head0">开户人</th>
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>

					<br /> <br />

				</div>
				<!-- #use -->

				<div id="freeze" class="subcontent" style="display: none;">
					<div class="overviewhead">
						身份证: &nbsp;<input type="text" id="freezeIdCard" /> &nbsp; 手机号码:
						&nbsp;<input type="text" id="freezePhone" /> &nbsp;客户姓名: &nbsp;<input
							type="text" id="freezeName" /> &nbsp; &nbsp;<br/><br/>
							日期: &nbsp;&nbsp;&nbsp;&nbsp;<input type="text" 
							id="beginTime1" /> &nbsp; &nbsp;&nbsp;&nbsp; 到:
						&nbsp;&nbsp;&nbsp;<input type="text" id="endTime1" />&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="publishbutton radius3" id="freezeQueryBtn">查询</button>
					</div>
					<!--overviewhead-->

					<br clear="all" />
					<!--contenttitle-->
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="freezeCustomersTable">
						<colgroup>
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
						</colgroup>
						<thead>
							<tr>
								<th class="head0">身份证/驾驶证</th>
								<th class="head1">手机号码</th>
								<th class="head0">姓名</th>								
								<th class="head1">保证金</th>
								<th class="head0">余额</th>
								<th class="head1">状态</th>
								<th class="head0">冻结原因</th>
								<th class="head1">冻结时间</th>
								<th class="head0">冻结人</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">身份证/驾驶证</th>
								<th class="head1">手机号码</th>
								<th class="head0">姓名</th>								
								<th class="head1">保证金</th>
								<th class="head0">余额</th>
								<th class="head1">状态</th>
								<th class="head0">冻结原因</th>
								<th class="head1">冻结时间</th>
								<th class="head0">冻结人</th>
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>

					<br /> <br />

				</div>
				<!-- #wait -->

				<div id="cancel" class="subcontent" style="display: none;">
					<div class="overviewhead">
					<div class="overviewselect">
					<select id="subStatus" name="select" class="chzn-select"
						style="width: 200px;">
						<option value="">请选择退款状态</option>						
						<option value="正常">未退款</option>
						<option value="已退款">已退款</option>
					</select>
				</div>
						身份证: &nbsp;<input type="text" id="cancelIdCard" /> &nbsp; 手机号码:
						&nbsp;<input type="text" id="cancelPhone" /> &nbsp;客户姓名: &nbsp;<input
							type="text" id="cancelName" /> &nbsp; &nbsp;<br/><br/>
							日期: &nbsp;&nbsp;&nbsp;&nbsp;<input type="text" 
							id="beginTime2" /> &nbsp; &nbsp;&nbsp;&nbsp; 到:
						&nbsp;&nbsp;&nbsp;<input type="text" id="endTime2" />&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="publishbutton radius3" id="cancelQueryBtn">查询</button>
					</div>
					<!--overviewhead-->

					<br clear="all" />
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="cancelCustomersTable">
						<colgroup>
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
						</colgroup>
						<thead>
							<tr>
								<th class="head0">身份证/驾驶证</th>
								<th class="head1">手机号码</th>
								<th class="head0">姓名</th>								
								<th class="head1">保证金</th>
								<th class="head0">余额</th>
								<th class="head1">状态</th>
								<th class="head0">注销原因</th>
								<th class="head1">注销时间</th>
								<th class="head0">注销人</th>
								<th class="head1">退款状态</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head0">身份证/驾驶证</th>
								<th class="head1">手机号码</th>
								<th class="head0">姓名</th>								
								<th class="head1">保证金</th>
								<th class="head0">余额</th>
								<th class="head1">状态</th>
								<th class="head0">注销原因</th>
								<th class="head1">注销时间</th>
								<th class="head0">注销人</th>
								<th class="head1">退款状态</th>
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>

					<br /> <br />
				</div>
			</div>
			<!--contentwrapper-->

</body>
</html>

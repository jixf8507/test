<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript" src="${ctx}/js/page/stake/deviceWin.js"></script>
</head>

<div id="contentwrapper" class="contentwrapper">
	<div id="list" class="subcontent">
		<div class="overviewhead">
			<div class="overviewselect">
				<select id="siteId" name="select" class="chzn-select"
					style="width: 200px;" tabindex="2">
					<option value="">请选择租赁点</option>
				</select>
			</div>
			出厂编号: &nbsp;<input type="text" id="factoryNo" /> &nbsp; &nbsp;
			铭牌: &nbsp;<input type="text" id="nameplate" /> &nbsp; &nbsp;
			<button class="publishbutton radius3" id="queryBtn">查询</button>
		</div>
		<br clear="all" />
		<div></div>
		<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
			id="sitesTable">
			<colgroup>
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
					<th class="head1">选择</th>
					<th class="head0">出厂编号</th>
					<th class="head1">设备名称</th>
					<th class="head0">铭牌</th>
					<th class="head1">所在充电站</th>
					<th class="head0">区域名称</th>
					<th class="head1">设备类型</th>
					<th class="head0">生产厂商</th>
				</tr>
			</thead>
			<tfoot>
				<tr>
					<th class="head1">选择</th>
					<th class="head0">出厂编号</th>
					<th class="head1">设备名称</th>
					<th class="head0">铭牌</th>
					<th class="head1">所在充电站</th>
					<th class="head0">区域名称</th>
					<th class="head1">设备类型</th>
					<th class="head0">生产厂商</th>
				</tr>
			</tfoot>
			<tbody>

			</tbody>
		</table>
		<br /> <br />
	</div>
</div>

</body>
</html>

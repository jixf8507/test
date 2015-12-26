<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript"
	src="${ctx}/js/page/stake/deviceManager.js"></script>
</head>

<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />

		<div class="centercontent">
			<div class="pageheader">
				<h1 class="pagetitle">充电设备管理</h1>
				<span class="pagedesc" style="color: red">提示：未绑定充电费率套餐的充电桩，默认收费为零。</span>
				<ul class="hornav">
					<li class="current"><a href="#other" id="otherDiv">普通充电桩</a></li>
					<li><a href="#normal" id="normalDiv">集群充电桩</a></li>
				</ul>
			</div>
			<!--pageheader-->
			<div id="contentwrapper" class="contentwrapper">
				<div id="other" class="subcontent">
					<div class="overviewhead">
						<div class="overviewselect">
							<select id="isupdateprice" name="isupdateprice"
								class="chzn-select" style="width: 200px;" tabindex="2">
								<option value="">请选择费率下发状态</option>
								<option value="0">未下发</option>
								<option value="1">已下发</option>
							</select>&nbsp;&nbsp; <select id="status" name="status"
								class="chzn-select" style="width: 200px;" tabindex="2">
								<option value="">请选择联网状态</option>
								<option value="正常">正常</option>
								<option value="离线">离线</option>
								<option value="不联网">不联网</option>
							</select>&nbsp;&nbsp; <select id="siteId" name="select"
								class="chzn-select" style="width: 200px;" tabindex="2">
								<option value="">请选择租赁点</option>
							</select>
						</div>
						<!--floatright-->
						出厂编号: &nbsp;<input type="text" id="factoryNo" /> &nbsp; &nbsp;
						<button class="publishbutton radius3" id="queryBtn">查询</button>
						<button class="publishbutton radius3" id="excelBtn">导出EXCEL</button>
					</div>
					<br clear="all" />
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
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
							<col class="con0" style="width: 8%;" />
						</colgroup>
						<thead>
							<tr>
								<th class="head1">图片</th>
								<th class="head0">出厂编号</th>
								<th class="head1">设备名称</th>
								<th class="head0">铭牌</th>
								<th class="head1">所在充电站</th>
								<th class="head0">区域名称</th>
								<th class="head1">充电套餐</th>
								<th class="head0">下发费率</th>
								<th class="head1">设备类型</th>
								<th class="head0">生产厂商</th>
								<th class="head1">功率(kw)</th>
								<th class="head0">电压(v)</th>
								<th class="head1">联网状态</th>
								<th class="head0">操作</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head1">图片</th>
								<th class="head0">出厂编号</th>
								<th class="head1">设备名称</th>
								<th class="head0">铭牌</th>
								<th class="head1">所在充电站</th>
								<th class="head0">区域名称</th>
								<th class="head1">充电套餐</th>
								<th class="head0">下发费率</th>
								<th class="head1">设备类型</th>
								<th class="head0">生产厂商</th>
								<th class="head1">功率(kw)</th>
								<th class="head0">电压(v)</th>
								<th class="head1">联网状态</th>
								<th class="head0">操作</th>
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>
					<br /> <br />
				</div>

				<div id="normal" class="subcontent" style="display: none;">
					<div class="overviewhead">
						<div class="overviewselect">
							<select id="isupdateprice1" name="isupdateprice"
								class="chzn-select" style="width: 200px;" tabindex="2">
								<option value="">请选择费率下发状态</option>
								<option value="0">未下发</option>
								<option value="1">已下发</option>
							</select>&nbsp;&nbsp; <select id="status1" name="status"
								class="chzn-select" style="width: 200px;" tabindex="2">
								<option value="">请选择联网状态</option>
								<option value="正常">正常</option>
								<option value="离线">离线</option>
								<option value="不联网">不联网</option>
							</select>&nbsp;&nbsp; <select id="siteId1" name="select"
								class="chzn-select" style="width: 200px;" tabindex="2">
								<option value="">请选择租赁点</option>
							</select>
						</div>
						<!--floatright-->
						出厂编号: &nbsp;<input type="text" id="factoryNo1" /> &nbsp; &nbsp;
						<button class="publishbutton radius3" id="queryBtn1">查询</button>
						<button class="publishbutton radius3" id="excelBtn1">导出EXCEL</button>
					</div>
					<br clear="all" />
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="normalTable">
						<colgroup>
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
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
							<col class="con0" style="width: 8%;" />
						</colgroup>
						<thead>
							<tr>
								<th class="head1">图片</th>
								<th class="head0">出厂编号</th>
								<th class="head1">设备名称</th>
								<th class="head0">铭牌</th>
								<th class="head1">所在充电站</th>
								<th class="head0">区域名称</th>
								<th class="head1">充电套餐</th>
								<th class="head0">下发费率</th>
								<th class="head1">设备类型</th>
								<th class="head0">生产厂商</th>
								<th class="head1">功率(kw)</th>
								<th class="head0">电压(v)</th>
								<th class="head1">联网状态</th>
								<th class="head0">操作</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head1">图片</th>
								<th class="head0">出厂编号</th>
								<th class="head1">设备名称</th>
								<th class="head0">铭牌</th>
								<th class="head1">所在充电站</th>
								<th class="head0">区域名称</th>
								<th class="head1">充电套餐</th>
								<th class="head0">下发费率</th>
								<th class="head1">设备类型</th>
								<th class="head0">生产厂商</th>
								<th class="head1">功率(kw)</th>
								<th class="head0">电压(v)</th>
								<th class="head1">联网状态</th>
								<th class="head0">操作</th>
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>
					<br /> <br />
				</div>

			</div>
			<br clear="all" />
		</div>
	</div>
</body>
</html>

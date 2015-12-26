<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript" src="${ctx}/js/page/asset/assetDetail.js"></script>
</head>
<body>
<ul class="hornav">
	<li class="current"><a href="#normal">基本信息</a></li>
	<li><a href="#account" id="accountDiv">供货商信息</a></li>
	<li><a href="#use" id="useDiv">使用信息</a></li>
	<li><a href="#repair" id="repairDiv">维修信息</a></li>
	<li><a href="#reduce" id="reduceDiv">处置信息</a></li>
</ul>
<div id="contentwrapper" class="contentwrapper">
	<div id="normal" class="subcontent">
	<!-- #normal -->
		<form id="form1" class="stdform stdform2">
			<p>
				<label>资产名称</label> <span class="field">${assetPO.assetsName}&nbsp;</span>
			</p>
			<p>
				<label>资产类型</label> <span class="field">${assetPO.name}&nbsp;</span>
			</p>
			<p>
				<label>规格型号</label> <span class="field">${assetPO.model }&nbsp;</span>
			</p>
			<p>
				<label>购置日期</label> <span class="field">${assetPO.purchaseDate}&nbsp;</span>
			</p>
			<p>
				<label>增加方式</label> <span class="field">${assetPO.increasingMode}&nbsp;</span>
			</p>
			<p>
				<label>经办人</label> <span class="field">${assetPO.transactUser}&nbsp;</span>
			</p>
			<p>
				<label>资产价值</label> <span class="field">${assetPO.fee}&nbsp;</span>
			</p>
			<p>
				<label>资产状态</label> <span class="field">${assetPO.flag}&nbsp;</span>
			</p>
		</form>
	</div>
	<!-- #normal -->
	
	<!-- #account -->
	<div id="account" class="subcontent" style="display: none;">
		<form id="form1" class="stdform stdform2">
			<p>
				<label>供货商</label> <span class="field">${assetPO.manufacturerPO.manufacturerName}&nbsp;</span>
			</p>
			<p>
				<label>供货商全称</label> <span class="field">${assetPO.manufacturerPO.fullName}&nbsp;</span>
			</p>
			<p>
				<label>地址</label> <span class="field">${assetPO.manufacturerPO.address}&nbsp;</span>
			</p>
			<p>
				<label>邮箱</label> <span class="field">${assetPO.manufacturerPO.email}&nbsp;</span>
			</p>
			<p>
				<label>联系人</label> <span class="field">${assetPO.manufacturerPO.linkman}&nbsp;</span>
			</p>
			<p>
				<label>联系电话</label> <span class="field">${assetPO.manufacturerPO.linkphone}&nbsp;</span>
			</p>
		</form>
	</div>
	<!-- #account -->
	
		<input type="hidden" id="assetsId" name="assetsId" value="${assetPO.id}"/>
	
	<!-- #use -->
	<div id="use" class="subcontent" style="display: none;">
		<div class="overviewhead">
		<div class="overviewselect">
					<select id="siteId" name="siteId" class="chzn-select"
								style="width: 200px;" tabindex="2">
								<option value="">请选择</option>
							</select> 
				</div>
					领用日期: &nbsp;<input type="text" id="useBeginTime" /> &nbsp;到:
						&nbsp;<input type="text" id="useEndTime" />
						&nbsp; &nbsp;<button class="publishbutton radius3" id="useQueryBtn">查询</button>
						&nbsp;<button class="publishbutton radius3" id="useExcelBtn">导出EXCEL</button>
		</div>
		<br clear="all" />
		<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
			id="useTable">
			<colgroup>
				<col class="con0" />
				<col class="con1" />
				<col class="con0" />
				<col class="con1" />
				<col class="con0" />
				<col class="con1" />
			</colgroup>
			<thead>
				<tr>
					<th class="head0">领用日期</th>
					<th class="head1">领用点</th>
					<th class="head0">领用人</th>
					<th class="head1">经办人</th>
					<th class="head0">资产状态</th>
					<th class="head1">备注</th>			
				</tr>
			</thead>
			<tfoot>
				<tr>
					<th class="head0">领用日期</th>
					<th class="head1">领用点</th>
					<th class="head0">领用人</th>
					<th class="head1">经办人</th>
					<th class="head0">资产状态</th>
					<th class="head1">备注</th>	
				</tr>
			</tfoot>
			<tbody> </tbody>
		</table>
	</div>
	<!-- #use -->
	
	<!-- #repair -->
	<div id="repair" class="subcontent" style="display: none;">
		<div class="overviewhead">
			送修日期: &nbsp;<input type="text" id="repairBeginTime" /> &nbsp;到:
						&nbsp;<input type="text" id="repairEndTime" />
						&nbsp; &nbsp;<button class="publishbutton radius3" id="repairQueryBtn">查询</button>
						&nbsp;<button class="publishbutton radius3" id="repairExcelBtn">导出EXCEL</button>		
		</div>
		<br clear="all" />
		<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
			id="repairTable">
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
			</colgroup>
			<thead>
				<tr>
					<th class="head0">送修日期</th>
					<th class="head1">维修原因</th>
					<th class="head0">申请人</th>
					<th class="head1">经办人</th>
					<th class="head0">备注说明</th>
					<th class="head1">维修商</th>		
					<th class="head0">维修费用</th>
					<th class="head1">维修配件</th>	
					<th class="head0">维修状态</th>
				</tr>
			</thead>
			<tfoot>
				<tr>
					<th class="head0">送修日期</th>
					<th class="head1">维修原因</th>
					<th class="head0">申请人</th>
					<th class="head1">经办人</th>
					<th class="head0">备注说明</th>
					<th class="head1">维修商</th>		
					<th class="head0">维修费用</th>
					<th class="head1">维修配件</th>	
					<th class="head0">维修状态</th>
				</tr>
			</tfoot>
			<tbody> </tbody>
		</table>
	</div>
	<!-- #repair -->
	
	<!-- #reduce -->
	<div id="reduce" class="subcontent" style="display: none;">
		<div class="overviewhead">
			处置日期: &nbsp;<input type="text" id="reduceBeginTime" /> &nbsp;到:
						&nbsp;<input type="text" id="reduceEndTime" />
						&nbsp; &nbsp;<button class="publishbutton radius3" id="reduceQueryBtn">查询</button>
						&nbsp;<button class="publishbutton radius3" id="reduceExcelBtn">导出EXCEL</button>		
		</div>
		<br clear="all" />
		<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
			id="reduceTable">
			<colgroup>
				<col class="con0" />
				<col class="con1" />
				<col class="con0" />
				<col class="con1" />
				<col class="con0" />
				<col class="con1" />
			</colgroup>
			<thead>
				<tr>
					<th class="head0">处置日期</th>
					<th class="head1">处置原因</th>
					<th class="head0">处置方式</th>
					<th class="head1">申请人</th>
					<th class="head0">经办人</th>
					<th class="head1">备注说明</th>
				</tr>
			</thead>
			<tfoot>
				<tr>
					<th class="head0">处置日期</th>
					<th class="head1">处置原因</th>
					<th class="head0">处置方式</th>
					<th class="head1">申请人</th>
					<th class="head0">经办人</th>
					<th class="head1">备注说明</th>
				</tr>
			</tfoot>
			<tbody> </tbody>
		</table>
	</div>
	<!-- #reduce -->
</div>
</body>
</html>

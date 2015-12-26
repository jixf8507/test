<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript"
	src="${ctx}/js/page/merchant/couponManager.js"></script>
</head>
<body class="withvernav">
	<div class="bodywrapper">
		<jsp:include page="../common/header.jsp" />
		<div class="centercontent">
			<div class="pageheader">
				<h1 class="pagetitle">优惠券管理</h1>
				<span class="pagedesc"></span>
				<ul class="hornav">
					<li class="current"><a href="#unUsedCoupon">待用优惠券</a></li>
					<li><a href="#bolishCoupon" id="bolishCouponDiv">作废优惠券</a></li>
					<li><a href="#usedCoupon" id="usedCouponDiv">已用优惠券</a></li>
					<li><a href="#outTimeCoupon" id="outTimeCouponDiv">过期优惠券</a></li>
				</ul>
			</div>
			<!--pageheader-->
			<div id="contentwrapper" class="contentwrapper">
				<div id="unUsedCoupon" class="subcontent">
					<div class="overviewhead">
						<%--<div class="overviewselect" id="cityDiv">
							<select data-placeholder="选择" name="city" id="city"
								class="chzn-select" style="width: 200px;" tabindex="2">
								<option value=""></option>
							</select>
						</div> 
						<div class="overviewselect">
							<select data-placeholder="选择" name="province" id="province"
								class="chzn-select" style="width: 200px;" tabindex="2">
								<option value=""></option>
							</select>
						</div> --%>
						<!--floatright-->
						优惠券编码: &nbsp;<input type="text" id="unUsedId" />&nbsp; &nbsp;
						优惠券名称: &nbsp;<input type="text" id="unUsedName" />&nbsp; &nbsp;
						<button class="publishbutton radius3" id="unUsedCouponQueryBtn">查询</button>
						&nbsp;
						<button class="publishbutton radius3" id="unUsedExcelBtn">导出EXCEL</button>
					</div>

					<!-- <div class="overviewhead">
						<button class="publishbutton radius3" id="addBtn">新增服务中心</button>
					</div> -->

					<!--overviewhead-->
					<br clear="all" />
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="unUsedCouponTable">
						<colgroup>
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
						
							<!-- <col class="con0" /> -->
						</colgroup>
						<thead>
							<tr>
								<th class="head1 "><input style="width: 20px;"
									type="checkbox" class="checkall" id="all" /></th>
								<th class="head0">优惠券编码</th>
								<th class="head1">优惠券名</th>
								<th class="head0">金额</th>
								<th class="head1">状态</th>
								<!-- <th class="head1">优惠券编号</th> -->
								<th class="head1">创建时间</th>
								<th class="head0">到期时间</th>
								<th class="head0">类型</th>
								<th class="head1">姓名</th>
								<th class="head1">电话</th>
								<th class="head1">操作</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head1 "><input style="width: 20px;"
									type="checkbox" class="checkall" id="all" /></th>
								<th class="head0">优惠券编码</th>
								<th class="head1">优惠券名</th>
								<th class="head0">金额</th>
								<th class="head1">状态</th>
								<!-- <th class="head1">优惠券编号</th> -->
								<th class="head1">创建时间</th>
								<th class="head0">到期时间</th>
								<th class="head0">类型</th>
								<th class="head1">姓名</th>
								<th class="head1">电话</th>
								<th class="head1">操作</th>
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>
					<br /> <br />
				</div>



				<div id="bolishCoupon" class="subcontent" style="display: none;">
					<div class="overviewhead">
						优惠券编码: &nbsp;<input type="text" id="bolishId" />&nbsp; &nbsp;
						优惠券名称: &nbsp;<input type="text" id="bolishName" />&nbsp; &nbsp;
						<button class="publishbutton radius3" id="bolishCouponQueryBtn">查询</button>
						&nbsp;
						<button class="publishbutton radius3" id="bolishExcelBtn">导出EXCEL</button>
					</div>
					<!--overviewhead-->
					<br clear="all" />
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="bolishTable">
						<colgroup>
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
							<!-- <col class="con0" /> -->
						</colgroup>
						<thead>
							<tr>
								<th class="head1">优惠券编码</th>
								<th class="head0">优惠券名</th>
								<th class="head1">金额</th>
								<th class="head0">状态</th>
								<!-- <th class="head1">优惠券编号</th> -->
								<th class="head0">创建时间</th>
								<th class="head1">到期时间</th>
								<th class="head0">类型</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head1">优惠券编码</th>
								<th class="head0">优惠券名</th>
								<th class="head1">金额</th>
								<th class="head0">状态</th>
								<!-- <th class="head1">优惠券编号</th> -->
								<th class="head0">创建时间</th>
								<th class="head1">到期时间</th>
								<th class="head0">类型</th>
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>
					<br /> <br />
				</div>




				<div id="usedCoupon" class="subcontent" style="display: none;">
					<div class="overviewhead">
						优惠券编码: &nbsp;<input type="text" id="usedId" />&nbsp; &nbsp;
						优惠券名称: &nbsp;<input type="text" id="usedName" />&nbsp; &nbsp;
						<button class="publishbutton radius3" id="usedCouponQueryBtn">查询</button>
						&nbsp;
						<button class="publishbutton radius3" id="usedExcelBtn">导出EXCEL</button>
					</div>
					<!--overviewhead-->
					<br clear="all" />
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="usedCouponTable">
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
								<th class="head1">优惠券编码</th>
								<th class="head0">优惠券名</th>
								<th class="head1">金额</th>
								<th class="head0">状态</th>
								<!-- <th class="head1">优惠券编号</th> -->
								<th class="head0">创建时间</th>
								<th class="head1">到期时间</th>
								<th class="head0">类型</th>
								<th class="head0">姓名</th>
								<th class="head1">电话</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head1">优惠券编码</th>
								<th class="head0">优惠券名</th>
								<th class="head1">金额</th>
								<th class="head0">状态</th>
								<!-- <th class="head1">优惠券编号</th> -->
								<th class="head0">创建时间</th>
								<th class="head1">到期时间</th>
								<th class="head0">类型</th>
								<th class="head1">姓名</th>
								<th class="head1">电话</th>
								
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>
					<br /> <br />
				</div>

				
				
				
					<div id="outTimeCoupon" class="subcontent" style="display: none;">
					<div class="overviewhead">
						优惠券编码: &nbsp;<input type="text" id="outTimeId" />&nbsp; &nbsp;
						优惠券名称: &nbsp;<input type="text" id="outTimeName" />&nbsp; &nbsp;
						<button class="publishbutton radius3" id="outTimeCouponQueryBtn">查询</button>
						&nbsp;
						<button class="publishbutton radius3" id="outTimeExcelBtn">导出EXCEL</button>
					</div>
					<!--overviewhead-->
					<br clear="all" />
					<table cellpadding="0" cellspacing="0" border="0" class="stdtable"
						id="outTimeCouponTable">
						<colgroup>
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
							<col class="con0" />
							<col class="con1" />
							<!-- <col class="con0" /> -->
						</colgroup>
						<thead>
							<tr>
								<th class="head1">优惠券编码</th>
								<th class="head0">优惠券名</th>
								<th class="head1">金额</th>
								<th class="head0">状态</th>
								<!-- <th class="head1">优惠券编号</th> -->
								<th class="head0">创建时间</th>
								<th class="head1">到期时间</th>
								<th class="head0">类型</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th class="head1">优惠券编码</th>
								<th class="head0">优惠券名</th>
								<th class="head1">金额</th>
								<th class="head0">状态</th>
								<!-- <th class="head1">优惠券编号</th> -->
								<th class="head0">创建时间</th>
								<th class="head1">到期时间</th>
								<th class="head0">类型</th>
							</tr>
						</tfoot>
						<tbody>

						</tbody>
					</table>
					<br /> <br />
				</div>




			</div>
			<!--contentwrapper-->
			<br clear="all" />
		</div>
		<!-- centercontent -->
	</div>
	<!--bodywrapper-->

</body>
</html>

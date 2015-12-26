<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<link rel="stylesheet" href="${ctx}/js/imgSwitch/css/style.css"
	type="text/css" />
<script type="text/javascript" src="${ctx}/js/ajaxfileupload.js"></script>
<script type="text/javascript"
	src="${ctx}/js/plugins/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="${ctx}/js/page/otherOrder/returnConfirmEdite.js"></script>

</head>

<body>
	<ul class="hornav">
		<li class="current"><a href="#order">审查情况</a></li>
		<li><a href="#pic" id="picDiv">车辆图片</a></li>
	</ul>
	<div id="contentwrapper" class="contentwrapper">
		<div id="order" class="subcontent">
			<form id="form1" class="stdform" action="" method="post">
				<table cellpadding="0" cellspacing="0" border="0" class="stdtable">
					<colgroup>
						<col class="con0" />
						<col class="con1" />
					</colgroup>
					<thead>
						<tr>
							<th class="head0">收费项</th>
							<th class="head1">详细</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>用车事由</td>
							<td colspan="2">&nbsp;&nbsp;${modelMap.orderPO.orderDescribe}</td>
						</tr>
						<tr>
							<td>取车续航里程</td>
							<td colspan="2">&nbsp;&nbsp;${modelMap.orderPO.surplusKmsForGet}</td>
						</tr>
						<tr>
							<td>取车码表公里数</td>
							<td colspan="2">&nbsp;&nbsp;${modelMap.orderPO.kmsForGet}</td>
						</tr>
						<tr>
							<td>还车租赁点</td>
							<td colspan="2">&nbsp;&nbsp;${modelMap.orderPO.rsitePO.siteName}</td>
						</tr>
						<tr>
							<td>还车续航里程</td>
							<td colspan="2"><input type="text"
								name="surplusKmsForReturn" id="surplusKmsForReturn"
								class="smallinput"
								value="${modelMap.carPO.surplusKms}"
								style="width: 200px;" /> <input type="hidden"
								value="${modelMap.orderPO.orderDescribe}" name="orderDescribe" />
								<input type="hidden"
								value="${modelMap.orderPO.relityReturnSiteId}"
								name="relityReturnSiteId" /></td>
						</tr>
						<tr>
							<td>还车码表公里数</td>
							<td colspan="2"><input type="text" id="kmsForReturn"
								name="kmsForReturn" class="smallinput"
								value="${modelMap.carPO.kms}" style="width: 200px;" />
								<input type="hidden" name="kmsReturn" id="kmsReturn"
								value="${modelMap.orderPO.kmsForGet}" /></td>
						</tr>
					</tbody>
				</table>

				<input type="hidden" name="id" value="${modelMap.orderPO.id}"
					id="id" /> <input type="hidden" name="carId"
					value="${modelMap.orderPO.carId}" /> <input type="hidden"
					name="priceTypeId" value="${modelMap.orderPO.priceTypeId}" /> <input
					type="hidden" name="priceTypeTableName"
					value="${modelMap.orderPO.priceTypeTableName}" /> <input
					type="hidden" id="currentCarStatus" name="currentCarStatus"
					value="${modelMap.orderPO.currentCarStatus}" />

				<%-- <table cellpadding="0" cellspacing="0" border="0" class="stdtable">
					<colgroup>
						<col class="con0" />
						<col class="con1" />
						<col class="con0" />
					</colgroup>
					<thead>
						<tr>
							<th class="head0">检测项</th>
							<th class="head1">当前状态</th>
							<th class="head0">描述</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${modelMap.carDetails}" var="detail">
							<tr>
								<td>${detail.component}<input type="hidden"
									value="${detail.component}" name="components" /><input
									type="hidden" value="${detail.id}" name="ids" /></td>
								<td><input type="text" name="statuss" class="smallinput"
									value="${detail.status}" style="width: 200px;" /></td>
								<td><input type="text" name="describes" class="smallinput"
									value="${detail.describe}" style="width: 300px;" /></td>
							</tr>
						</c:forEach>

					</tbody>
				</table> --%>
			</form>
		</div>


		<div id="pic" class="subcontent" style="display: none;">
			<form id="imgForm" class="stdform stdform2">

				<p>
					<label>还车图片上传</label><span class="field"> <input type="file"
						id="carImgFile" /> <input type="hidden" id="fileUrl"
						name="fileUrl" value="" /> <input type="hidden" id="fileName"
						name="fileName" value="" /> <input type="button" value="上传"
						id="uploadBut" /> <span style="color: red; font-size: 14px;">&nbsp;&nbsp;&nbsp;注：只能上传jpg或png文件</span></span>
				</p>

				<!-- 轮播图片 -->
				<div id="home_banner">

					<a id="big_a"><div id="big_img"></div></a>

					<div class="relative">
						<div id="small_img">
							<div class="maxwidth">
								<a id="small_pre"><</a>
								<div id="small_imgs"></div>
								<a id="small_next">></a>
							</div>
						</div>
					</div>
				</div>
				<!-- 轮播图片 -->

				<input type="hidden" name="tableId" id="tableId"
					value="${modelMap.orderPO.id}" /> <input type="hidden"
					name="tableName" id="tableName" value="order" /> <input
					type="hidden" name="fileType" id="fileType" value="还车" /> <input
					type="hidden" name="carId" id="carId"
					value="${modelMap.orderPO.carId}" />
			</form>
		</div>
	</div>
</body>
</html>

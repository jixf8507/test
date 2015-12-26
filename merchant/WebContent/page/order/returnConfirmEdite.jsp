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
	src="${ctx}/js/page/order/returnConfirmEdite.js"></script>

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
							<td>取车续航里程</td>
							<td colspan="2">&nbsp;&nbsp;${modelMap.orderPO.surplusKmsForGet}</td>
						</tr>
						<tr>
							<td>取车码表公里数</td>
							<td colspan="2">&nbsp;&nbsp;${modelMap.orderPO.kmsForGet}</td>
						</tr>
						<tr>
							<td>选择还车租赁点</td>
							<td colspan="2"><select data-placeholder="选择租赁点"
								id="relityReturnSiteId" name="relityReturnSiteId"
								class="chzn-select" style="width: 200px;" tabindex="2">
									<option value=""></option>
							</select> <input type="hidden" id="hid_relityReturnSiteId"
								value="${modelMap.orderPO.relityReturnSiteId}" /></td>
						</tr>
						<tr>
							<td>还车续航里程</td>
							<td colspan="2"><input type="text"
								name="surplusKmsForReturn" id="surplusKmsForReturn"
								class="smallinput"
								value="${modelMap.carPO.surplusKms}"
								style="width: 200px;" /></td>
						</tr>

						<!-- 普通用户 -->
						<c:if
							test="${modelMap.orderPO.unitCustomerPO.enterpriseName == null}">
							<input type="hidden" id="customerType" value="1" />
							<tr>
								<td>还车码表公里数</td>
								<td colspan="2"><input type="text" id="kmsForReturn"
									name="kmsForReturn" class="smallinput"
									value="${modelMap.carPO.kms}" style="width: 200px;" />
									<input type="hidden" id="kmsReturn"
									value="${modelMap.orderPO.kmsForGet}"></input></td>
							</tr>
							<tr>
								<td>超出公里数</td>
								<td colspan="2"><input type="text" id="perKms"
									name="perKms" class="smallinput" readonly="readonly"
									value="${modelMap.orderPO.perKms}" style="width: 200px;" /></td>
							</tr>
							<tr>
								<td>超出公里收费</td>
								<td colspan="2"><input type="text" id="perKmsCost"
									name="perKmsCost" class="smallinput" readonly="readonly"
									value="${modelMap.orderPO.perKmsCost}" style="width: 200px;" />
								</td>
							</tr>
							<tr>
								<td>租赁收费</td>
								<td colspan="2"><input type="text" id="useCost"
									name="useCost" class="smallinput" readonly="readonly"
									value="${modelMap.orderPO.useCost}" style="width: 200px;" /></td>
							</tr>
							<tr>
								<td>其它收费</td>
								<td colspan="2"><input type="text" id="otherCost"
									name="otherCost" class="smallinput"
									value="${modelMap.orderPO.otherCost}" style="width: 200px;" />
								</td>
							</tr>
							<tr>
								<td>维修收费</td>
								<td colspan="2"><input type="text" id="maintenanceCost"
									name="maintenanceCost" class="smallinput"
									value="${modelMap.orderPO.maintenanceCost}"
									style="width: 200px;" /></td>
							</tr>
						</c:if>
						<!-- 普通用户 -->

						<!-- 企业用户 -->
						<c:if test="${modelMap.orderPO.customerPO.name == null}">
							<input type="hidden" id="customerType" value="2" />
							<input type="hidden" id="perKms" name="perKms" value="0" />
							<input type="hidden" id="perKmsCost" name="perKmsCost" value="0" />
							<tr>
								<td>还车码表公里数</td>
								<td colspan="2"><input type="text" id="kmsForReturn1"
									name="kmsForReturn" class="smallinput"
									value="${modelMap.carPO.kms}" style="width: 200px;" />
									<input type="hidden" id="kmsReturn"
									value="${modelMap.orderPO.kmsForGet}"></input></td>
							</tr>
							<tr>
								<td>租赁收费</td>
								<td colspan="2"><input type="text" id="unitUseCost"
									name="useCost" class="smallinput"
									value="${modelMap.orderPO.useCost}" style="width: 200px;" /></td>
							</tr>
							<tr>
								<td>其它收费</td>
								<td colspan="2"><input type="text" id="unitOtherCost"
									name="otherCost" class="smallinput"
									value="${modelMap.orderPO.otherCost}" style="width: 200px;" />
								</td>
							</tr>
							<tr>
								<td>维修费用</td>
								<td colspan="2"><input type="text" id="maintenanceCost1"
									name="maintenanceCost" class="smallinput"
									value="${modelMap.orderPO.maintenanceCost}"
									style="width: 200px;" /></td>
							</tr>
						</c:if>
						<!-- 企业用户 -->
						<input type="hidden" id="hidPerKmsCost" value="" />
						<tr>
							<td>其它收费说明</td>
							<td colspan="2"><input type="text" id="otherDescribe"
								name="otherDescribe" class="smallinput"
								value="${modelMap.orderPO.otherDescribe}" style="width: 200px;" />
							</td>
						</tr>
						<tr>
							<td>总收费</td>
							<td colspan="2"><input type="text" readonly="readonly"
								id="totalCost" name="totalCost" class="smallinput"
								value="${modelMap.orderPO.totalCost}" style="width: 200px;" />
							</td>
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

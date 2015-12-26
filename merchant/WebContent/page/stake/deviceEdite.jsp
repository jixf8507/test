<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<script type="text/javascript"
	src="${ctx}/js/plugins/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx}/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${ctx}/js/page/stake/deviceEdite.js"></script>
</head>

<body>
	<form id="form1" class="stdform stdform2" method="post">
		<p>
			<label>出厂编号</label> <span class="field"><input type="text"
				value="${deviceNamePO.factoryNo}" style="width: 200px;"
				name="factoryNo" id="factoryNo" class="longinput" /> <input
				type="hidden" value="${deviceNamePO.id}" name="id" id="id"/> <input
				type="hidden" value="${deviceNamePO.siteCode}" id="hidSiteId" /> <input
				type="hidden" value="${deviceNamePO.areaCode}" id="hidAreaCode" />
				<input type="hidden" value="${deviceNamePO.chargePort}"
				id="hidChargePort" /> <input type="hidden"
				value="${deviceNamePO.deviceTypeNo}" id="deviceTypeNo"
				name="deviceTypeNo" /> <input type="hidden"
				value="${deviceNamePO.factoryNo}" id="oldFactoryNo"
				name="oldFactoryNo" /></span>
		</p>
		<p>
			<label>充电站名称</label> <span class="field"> <select
				class="chzn-select" name="siteCode" id="siteId">
					<option value="">请选择</option>
			</select>
			</span>
		</p>
		<p>
			<label>区域名称</label> <span class="field" id="areaDiv"> <select
				class="chzn-select" name="areaCode" id="areaCode">
					<option value="">请选择</option>
			</select>
			</span>
		</p>
		<p>
			<label>充电端口类型</label> <span class="field"> <select
				class="chzn-select" name="chargePort" id="chargePort">
					<option value="">请选择</option>
			</select>
			</span>
		</p>
		<p>
			<label>设备名称</label> <span class="field"><input type="text"
				value="${deviceNamePO.deviceNo}" style="width: 200px;"
				name="deviceNo" id="deviceNo" class="longinput" /></span>
		</p>
		<p>
			<label>铭牌</label> <span class="field"><input type="text"
				value="${deviceNamePO.nameplate}" style="width: 200px;"
				name="nameplate" id="nameplate" class="longinput" /></span>
		</p>
		<p>
			<label>设备类型</label> <span class="field"> <select
				class="chzn-select" name="deviceTypeName" id="deviceTypeName">
					<option value="">请选择</option>
					<option value="旗翔交流"
						<c:if test="${deviceNamePO.deviceTypeName=='旗翔交流'}">selected="selected"</c:if>>旗翔交流</option>
					<option value="旗翔直流"
						<c:if test="${deviceNamePO.deviceTypeName=='旗翔直流'}">selected="selected"</c:if>>旗翔直流</option>
			</select> <input type="hidden" name="deviceKind" id="deviceKind"
				<c:if test="${deviceNamePO.deviceTypeName=='旗翔交流'}">value="交"</c:if>
				<c:if test="${deviceNamePO.deviceTypeName=='旗翔直流'}">value="直"</c:if> />
			</span>
		</p>
		<p>
			<label>生产厂商</label> <span class="field"> <select
				class="chzn-select" name="manufacturer" id="manufacturer">
					<option value="">请选择</option>
					<option value="旗翔科技"
						<c:if test="${deviceNamePO.manufacturer=='旗翔科技'}">selected="selected"</c:if>>旗翔科技</option>
			</select>
			</span>
		</p>
		<p>
			<label>位置坐标</label> <span class="field"> <input
				value="${deviceNamePO.lng},${deviceNamePO.lat}" type="text"
				style="width: 200px;" id="point" class="longinput"
				onclick="showMap()" /> <input type="button" value="点击选择"
				onclick="showMap()" /> <input type="hidden" name="lat" id="lat"
				value="${deviceNamePO.lat}" /> <input type="hidden" name="lng"
				id="lng" value="${deviceNamePO.lng}" />
			</span>
		</p>
		<p>
			<label>电压</label> <span class="field"><input type="text"
				value="${deviceNamePO.fixedVoltage}" style="width: 200px;"
				name="fixedVoltage" id="fixedVoltage" class="longinput" />&nbsp;( v
				)</span>
		</p>
		<p>
			<label>功率</label> <span class="field"><input type="text"
				value="${deviceNamePO.devicePower}" style="width: 200px;"
				name="devicePower" id="devicePower" class="longinput" />&nbsp;( kw
				)</span>
		</p>
		<p>
			<label>联网状态</label> <span class="field"><input type="radio"
				value="正常" name="status"
				<c:if test="${deviceNamePO.status=='正常'}">checked="checked"</c:if> />
				正常 &nbsp; &nbsp;<input type="radio" value="离线" name="status"
				<c:if test="${deviceNamePO.status=='离线'}">checked="checked"</c:if> />
				离线 &nbsp; &nbsp;<input type="radio" value="废弃" name="status"
				<c:if test="${deviceNamePO.status=='废弃'}">checked="checked"</c:if> />
				不联网 &nbsp; &nbsp; </span>
		</p>
		<p>
			<label>是否允许停车</label> <span class="field"> <input type="radio"
				value="1" name="isAllowParking"
				<c:if test="${deviceNamePO.isAllowParking=='1'}">checked="checked"</c:if> />
				允许 &nbsp; &nbsp;<input type="radio"
				<c:if test="${deviceNamePO.isAllowParking=='0'}">checked="checked"</c:if>
				value="0" name="isAllowParking" /> 不允许 &nbsp; &nbsp;
			</span>
		</p>
		<p>
			<label>充电设备图片上传</label> <span class="field"> <input
				type="file" name="upload" id="bigImgFile" /> <span
				style="color: red; font-size: 14px;">注：只能上传jpg或png文件<br /> <br />
					<img id="bigImg" onError="imgError(this)"
					src="${ctx}${deviceNamePO.imgUrl}" alt="充电设备图片" class="uploadImg" />
					<input type="hidden" name="imgUrl" value="${deviceNamePO.imgUrl}"
					id="bigImgHidden" /></span>
			</span>
		</p>
	</form>
</body>
</html>

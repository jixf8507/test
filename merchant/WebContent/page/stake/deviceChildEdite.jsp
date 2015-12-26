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
			<label>出厂编号</label> <span class="field">${deviceNamePO.factoryNo}&nbsp;&nbsp;
				<input type="hidden" value="${deviceNamePO.id}" name="id" /> <input
				type="hidden" value="${deviceNamePO.factoryNo}" name="factoryNo" />
				<input type="hidden" value="${deviceNamePO.groupNo}" name="groupNo" />
				<input type="hidden" value="${deviceNamePO.deviceTypeNo}"
				name="deviceTypeNo" /><input type="hidden" name="deviceTypeName"
				value="${deviceNamePO.deviceTypeName}" /> <input type="hidden"
				name="chargePort" value="${deviceNamePO.chargePort}" /> <input
				type="hidden" name="stakeNum" id="stakeNum" value="30" />
			</span>
		</p>
		<p>
			<label>充电站名称</label> <span class="field">${deviceNamePO.siteName}&nbsp;&nbsp;
			</span>
		</p>
		<p>
			<label>区域名称</label> <span class="field">${deviceNamePO.area_name}&nbsp;&nbsp;
			</span>
		</p>
		<p>
			<label>充电端口类型</label> <span class="field">${deviceNamePO.chargePort}&nbsp;&nbsp;
			</span>
		</p>
		<p>
			<label>设备名称</label> <span class="field">${deviceNamePO.deviceNo}&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>铭牌</label> <span class="field">${deviceNamePO.nameplate}&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>设备类型</label> <span class="field">${deviceNamePO.deviceTypeName}&nbsp;&nbsp;
			</span>
		</p>
		<p>
			<label>生产厂商</label> <span class="field">${deviceNamePO.manufacturer}&nbsp;&nbsp;
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
				readonly="readonly"
				<c:if test="${deviceNamePO.status=='离线'}">checked="checked"</c:if> />
				离线 &nbsp; &nbsp;<input type="radio" value="废弃" name="status"
				readonly="readonly"
				<c:if test="${deviceNamePO.status=='废弃'}">checked="checked"</c:if> />
				不联网 &nbsp; &nbsp; </span>
		</p>
		<p>
			<label>是否允许停车</label> <span class="field"> <input type="radio"
				value="1" name="isAllowParking"
				<c:if test="${deviceNamePO.isAllowParking=='1'}">checked="checked"</c:if> />
				允许 &nbsp; &nbsp;<input type="radio" readonly="readonly"
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

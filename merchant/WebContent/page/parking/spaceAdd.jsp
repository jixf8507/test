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
<script type="text/javascript" src="${ctx}/js/page/parking/spaceAdd.js"></script>
</head>

<div id="contentwrapper" class="contentwrapper">
	<div id="list" class="subcontent">
		<form id="form1" class="stdform stdform2" method="post">
			<p>
				<label>出厂编号</label> <span class="field"> <input type="text"
					onclick="getFactory();" style="width: 200px;" class="longinput"
					name="factoryNo" id="factoryNo" />&nbsp;&nbsp; <input
					type="button" onclick="getFactory();" value="点击获取" />
				</span>
			</p>
			<p>
				<label>充电站</label><span class="field"> <input type="text"
					style="width: 200px;" name="siteName" class="longinput"
					id="siteName" />
				</span>
			</p>
			<p>
				<label>设备名称</label><span class="field"> <input type="text"
					style="width: 200px;" name="deviceNo" class="longinput"
					id="deviceNo" />
				</span>
			</p>
			<p>
				<label>铭牌</label> <span class="field"> <input type="text"
					style="width: 200px;" name="nameplate" id="nameplate"
					class="longinput" /></span>
			</p>
			<p id="leftRight">
				<label>所在充电桩位置</label> <span class="field"> <select
					style="width: 200px;" name="position" id="position">
						<option value="" selected="selected">请选择</option>
						<option value="左">左</option>
						<option value="右">右</option>
				</select>
				</span>
			</p>
			<p style="display: none;" id="left">
				<label>所在充电桩位置</label> <span class="field"> <select
					style="width: 200px;" name="position1" id="position1">
						<option value="" selected="selected">请选择</option>
						<option value="左">左</option>
				</select>
				</span>
			</p>
			<input type="hidden" name="deviceTypeNo" id="deviceTypeNo" /> <input
				type="hidden" name="id" id="id" /> <input type="hidden"
				name="parkingId" id="parkingId" /> <input type="hidden" name="lat"
				id="lat" /> <input type="hidden" name="lng" id="lng" />

			<p>
				<label>车位编号</label> <span class="field"><input type="text"
					style="width: 200px;" style="width: 200px;" name="spaceNO"
					id="spaceNO" class="longinput" /></span>
			</p>
			<p>
				<label>区域</label> <span class="field"><input type="text"
					style="width: 200px;" name="area" id="area" class="longinput" /></span>
			</p>
			<p>
				<label>地址</label> <span class="field"><input type="text"
					style="width: 200px;" name="address" id="address" class="longinput" /></span>
			</p>
			<p>
				<label>是否支持充电</label> <span class="field"><input type="radio"
					value="是" name="isCharge" checked="checked" /> 是 &nbsp; &nbsp;<input
					type="radio" value="否" name="isCharge" /> 否 &nbsp; &nbsp;</span>
			</p>
			<p>
				<label>是否有地锁</label> <span class="field"><input type="radio"
					value="是" name="isLock" checked="checked" /> 是 &nbsp; &nbsp;<input
					type="radio" value="否" name="isLock" /> 否 &nbsp; &nbsp;</span>
			</p>
		</form>
		<br />
	</div>
</div>
</html>

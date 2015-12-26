<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ include file="../common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'readCard.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

</head>
<script type="text/javascript">
	var ComAxCtrl = null;
	function initss() {
		var port =  document.getElementsByTagName("select")[0].value;
		var baudRate =  document.getElementsByTagName("select")[1].value;
		//alert("port: " + port);
		//alert("baudRate: " + baudRate);
		if(port==""){
			alert("提示：请选择COM口");
			return false;
		}
		if(baudRate==""){
			alert("提示：请选择COM口波特率");
			return false;
		}
		
		//document.getElementById("fingerAGM").value = "";
		//document.getElementById("nzkh").value = "";
		var flag = false;
		var flag1 = false;
		if (ComAxCtrl == null) {
			flag = true;
		}
		if (flag) {
			ComAxCtrl = document.getElementById("ComAxCtrl");
			ComAxCtrl.attachEvent("OnCommRecv", OnCommRecv);
			flag1 = true;
		} else {
			if (!ComAxCtrl.IsCommOpen()) {
				flag1 = true;
			}
		}
		if (flag1) {
			var result = ComAxCtrl.CommOpen(port, ""+baudRate+",n,8,1");
			if (result == 0) {
				alert("提示：无法找到读卡设备！");
				return false;
			}
		}
		if (!ComAxCtrl.IsCommOpen()) {
			alert("提示：指纹仪未打开！");
			return false;
		}

		//发送命令   
		//document.getElementById("nzkh").value = "sucess";
		alert("sucess");
		//var cR = ComAxCtrl.CommSend(stringToHex("AA,FF,70,52,27"));   
		//alert(0XAA);
	}

	function jc() {
		var arr = [ 0xAA, 0xFF, 0x70, 0x26 ];
		var ss = arrayToHex(arr);
		//var cR = ComAxCtrl.CommSend("AAFF702603");			
		var cR = ComAxCtrl.CommSend(ss);
	}
	/* 	function dqwzkh() {
	 // ComAxCtrl.CommSend("AAFF100160FFFFFFFFFFFF24");  alert('ddd');
	 var arr = [0xAA,0xFF,0x10,0x01,0x60,0xFF,0xFF,0xFF,0xFF,0xFF,0xFF] ;			
	 var ss = arrayToHex(arr);		
	 var cR = ComAxCtrl.CommSend(ss);
	 } */

	var wzkh_q = '';
	var wzkh_h = '';
	function OnCommRecv(data) {
		alert("data: " + data);

		var dataArr = data.split(" ");
		if (dataArr[0] == 'BB', dataArr[1] == 'FF') {
			if (dataArr[2] == 'A0' || dataArr[2] == 'A1') {
				alert('提示：没有检测到卡信息');
			} else if (dataArr[2] == '00') {
				var nzkh = '';
				for (var i = 3; i < dataArr.length; i++) {
					nzkh += dataArr[i];
				}
				alert("nzkh: " + nzkh);
				nzkh = nzkh.substring(0, 9); 
				//document.getElementById("info").value += '内置卡号：' + nzkh + ',';
				document.getElementById("nzkh").value = nzkh;
			} else if (dataArr[2] == '10') {
				wzkh_q = '';
				for (var i = 3; i < dataArr.length; i++) {
					var ts = String.fromCharCode(parseInt(dataArr[i], 16));
					wzkh_q += ts;
				}
				if (wzkh_h != '') {
					var wzkh = wzkh_q + wzkh_h;
					alert("wzkh: " + wzkh);
					//document.getElementById("info").value += '外置卡号：' + wzkh + ',';
					wzkh_h = '';
					wzkh_q = '';
				}
			}
		} else {
			for (var i = 0; i < dataArr.length - 1; i++) {
				var ts = String.fromCharCode(parseInt(dataArr[i], 16));
				wzkh_h += ts;
			}
			//alert("wzkh_h: " + wzkh_h);
			if (wzkh_q != '') {
				var wzkh = wzkh_q + wzkh_h;
				//document.getElementById("info").value += '外置卡号：' + wzkh + ',';
				wzkh_h = '';
				wzkh_q = '';
			}

		}
	}

	function arrayToHex(arr) {
		var val = "";
		var temp;
		for (var i = 0; i < arr.length; i++) {
			var ts = arr[i].toString(16);
			if (ts.length == 1) {
				ts = '0' + ts;
			}
			val += ts;
			if (i == 0) {
				temp = arr[i];
			} else {
				temp = temp ^ arr[i];
			}

		}
		temp = temp.toString(16);
		temp = temp.length == 1 ? '0' + temp : temp;
		return val + temp;
	}

	function stringToHex(str) {
		var val = "";
		var arr = str.split(',');
		for (var i = 0; i < arr.length; i++) {
			if (val == "")
				val = "0X" + arr[i];
			else
				val += ",0X" + arr[i];
		}
		return val;
	}
	function hexToString(str) {
		var val = "";
		var arr = str.split(" ");
		for (var i = 0; i < arr.length; i++) {
			val += String.fromCharCode("0x" + arr[i]);
		}
		var info = document.getElementById("info").value + val;
		document.getElementById("info").value = info;
		if (info.length == 280) {
			//接收完数据 

		}
	}

	/**
	 * 提交form表单
	 */
	var submit = function(callBack) {
		var number = jQuery('#nzkh').val();
		callBack(number);
	};
</script>
<!-- <body onload="initss()"> -->
<body>
	<object id="ComAxCtrl"
		classid="clsid:1D82E7E4-CDEE-4894-92C2-A3E605D4F84E"
		codebase="<%=basePath%>js/page/ocx/ComAxCtrl.ocx"
		style="width: 1px; height: 1px;"></object>
	<input type="hidden" id="fingerAGM" value="" />
	<form id="form1" class="stdform stdform2" method="post" action="">
		<p></p>
		<p>
			<label>COM口</label> <span class="field"> <!-- <input type="text"
				style="width: 200px;" name="port" id="port" value=""
				class="longinput" /> -->
				<select id="comPort" name="comPort" class="chzn-select" style="width: 100px;">
					<option value="">请选择</option>
					<option value="1">COM1</option>
					<option value="2">COM2</option>
					<option value="3">COM3</option>
					<option value="4">COM4</option>
					<option value="5">COM5</option>
					<option value="6">COM6</option>
					<option value="7">COM7</option>
					<option value="8">COM8</option>
					<option value="9">COM9</option>
					<option value="10">COM10</option>
					<option value="11">COM11</option>
					<option value="12">COM12</option>
					<option value="13">COM13</option>
					<option value="14">COM14</option>
					<option value="15">COM15</option>
					<option value="16">COM16</option>
					<option value="17">COM17</option>
					<option value="18">COM18</option>
					<option value="19">COM19</option>
					<option value="20">COM20</option>
				</select>
				&nbsp;&nbsp;
			</span>
		</p>
		<p>
			<label>COM口波特率</label> <span class="field"> 
				<select id="baudRate" name="baudRate" class="chzn-select" style="width: 100px;">
					<option value="">请选择</option>
					<option value="9600">9600</option>
					<option value="14400">14400</option>
					<option value="19200">19200</option>
					<option value="38400">38400</option>
					<option value="57600">57600</option>
					<option value="115200" selected="selected">115200</option>
					<option value="128000">128000</option>
				</select>
				&nbsp;&nbsp;
			</span>
		</p>
		<p>
			<label>内置卡号</label> <span class="field"> <input type="text"
				style="width: 200px;" name="nzkh" id="nzkh" value=""
				class="longinput" />&nbsp;&nbsp;
			</span>
		</p>
		<p class="stdformbutton">
			<button class="submit radius2" onclick="initss()">读取卡号</button>
		</p>

	</form>

</body>
</html>

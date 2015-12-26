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
<script type="text/javascript"
	src="${ctx}/js/page/stake/stakeCardRecharge.js"></script>
</head>
<script type="text/javascript">
	var ComAxCtrl = null;
	function initss() {
		//初始化
		jQuery('#customerId').val("");
		jQuery('#name').val("");
		jQuery('#phone').val("");
		jQuery('#accountId').val("");
		jQuery('#oldBalance').val("");
		
		if (!window.ActiveXObject) {
			Dialog.alert("提示：使用此功能需通过IE浏览器访问");
			return false;
		}
		var port = document.getElementsByTagName("select")[0].value;
		var baudRate = document.getElementsByTagName("select")[1].value;
		if (port == "") {
			Dialog.alert("提示：请选择COM口");
			return false;
		}
		if (baudRate == "") {
			Dialog.alert("提示：请选择COM口波特率");
			return false;
		}

		var flag = false;
		var flag1 = false;
		if (ComAxCtrl == null) {
			flag = true;
		}
		if (flag) {
			ComAxCtrl = document.getElementById("ComAxCtrl");
			ComAxCtrl.CommClose();
			ComAxCtrl.attachEvent("OnCommRecv", OnCommRecv);
			flag1 = true;
		} else {
			if (!ComAxCtrl.IsCommOpen()) {
				flag1 = true;
			}
		}
		if (flag1) {
			var result = ComAxCtrl.CommOpen(port, "" + baudRate + ",n,8,1");
			if (result == 0) {
				Dialog.alert("提示：无法找到读卡设备！");
				ComAxCtrl.CommClose();
				return false;
			}
		}
		if (!ComAxCtrl.IsCommOpen()) {
			Dialog.alert("提示：指纹仪未打开！");
			ComAxCtrl.CommClose();
			return false;
		}

		//发送命令   
		document.getElementById("nzkh").value = "请刷卡";
	}

	function jc() {
		var arr = [ 0xAA, 0xFF, 0x70, 0x26 ];
		var ss = arrayToHex(arr);
		var cR = ComAxCtrl.CommSend(ss);
	}

	var wzkh_q = '';
	var wzkh_h = '';
	function OnCommRecv(data) {
		var dataArr = data.split(" ");
		if (dataArr[0] == 'BB', dataArr[1] == 'FF') {
			if (dataArr[2] == 'A0' || dataArr[2] == 'A1') {
				Dialog.alert('提示：没有检测到卡信息');
			} else if (dataArr[2] == '00') {
				var nzkh = '';
				for (var i = 3; i < dataArr.length; i++) {
					nzkh += dataArr[i];
				}
				nzkh = nzkh.substring(0, 8);
				document.getElementById("nzkh").value = nzkh;
				ComAxCtrl.CommClose();
				//获取客户信息
				jQuery.ajax({
					cache : true,
					type : "POST",
					url : contextPath + '/stake/card/getCardByCardId.htm?',
					data : "cardID=" + jQuery('#nzkh').val(),
					async : false,
					dataType : 'json',
					error : function(request) {
						Dialog.alert("提示：获取客户信息失败");
					},
					success : function(data) {
						if (data.success) {
							var info = data.info.split(",");
							jQuery('#customerId').val(info[0]);
							jQuery('#name').val(info[1]);
							jQuery('#phone').val(info[2]);
							jQuery('#accountId').val(info[3]);
							jQuery('#oldBalance').val(info[4]);

						} else {
							Dialog.alert("提示： " + data.info);
							jQuery('#customerId').val("");
							jQuery('#name').val("");
							jQuery('#phone').val("");
							jQuery('#accountId').val("");
							jQuery('#oldBalance').val("");
						}
					}
				});
				
			} else if (dataArr[2] == '10') {
				wzkh_q = '';
				for (var i = 3; i < dataArr.length; i++) {
					var ts = String.fromCharCode(parseInt(dataArr[i], 16));
					wzkh_q += ts;
				}
				if (wzkh_h != '') {
					var wzkh = wzkh_q + wzkh_h;
					wzkh_h = '';
					wzkh_q = '';
				}
			}
		} else {
			for (var i = 0; i < dataArr.length - 1; i++) {
				var ts = String.fromCharCode(parseInt(dataArr[i], 16));
				wzkh_h += ts;
			}
			if (wzkh_q != '') {
				var wzkh = wzkh_q + wzkh_h;
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
		//document.getElementById("info").value = info;
		if (info.length == 280) {
			//接收完数据 

		}
	}
</script>
<body>
	<object id="ComAxCtrl"
		classid="clsid:1D82E7E4-CDEE-4894-92C2-A3E605D4F84E"
		codebase="${ctx}/js/page/ocx/ComAxCtrl.ocx"
		style="width: 1px; height: 1px;"></object>
	<form id="form1" class="stdform stdform2" method="post">
		<p>
			<label>COM口</label> <span class="field"> <select id="comPort"
				name="comPort" class="chzn-select" style="width: 100px;">
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
			</select> &nbsp;&nbsp;
			</span>
		</p>
		<p>
			<label>COM口波特率</label> <span class="field"> <select
				id="baudRate" name="baudRate" class="chzn-select"
				style="width: 100px;">
					<option value="">请选择</option>
					<option value="9600">9600</option>
					<option value="14400">14400</option>
					<option value="19200">19200</option>
					<option value="38400">38400</option>
					<option value="57600">57600</option>
					<option value="115200" selected="selected">115200</option>
					<option value="128000">128000</option>
			</select> &nbsp;&nbsp;
			</span>
		</p>
		<p>
			<label>内置卡号</label> <span class="field"> <input type="text"
				style="width: 200px;" name="cardID" id="nzkh" value=""
				class="longinput" />&nbsp;&nbsp;<input type="button" value="读取卡号"
				onclick="initss()" />
			</span>
		</p>
		<p>
			<label>客户姓名</label> <span class="field"> <input type="text"
				style="width: 200px;" id="name" class="longinput" name="name"
				readonly="readonly" /> <input type="hidden" id="customerId"
				name="customerId" /><input type="hidden" id="accountId"
				name="accountId" />
			</span>
		</p>
		<p>
			<label>手机号码</label> <span class="field"> <input
				readonly="readonly" type="text" style="width: 200px;" id="phone"
				class="longinput" />&nbsp;&nbsp;<!-- <input type="button" value="点击获取"
				onclick="getCustomer()" /> -->
			</span>
		</p>
		<p>
			<label>账户余额</label> <span class="field"> <input type="text"
				style="width: 200px;" id="oldBalance" name="oldBalance"
				class="longinput" readonly="readonly" />
			</span>
		</p>
		<p>
			<label>充值金额</label> <span class="field"> <input type="text"
				style="width: 200px;" id="addBalance" name="addBalance"
				class="longinput" />
			</span>
		</p>
		<p>
			<label>付费方式</label> <span class="field"> &nbsp;<input
				type="radio" value="刷卡付费" name="describe" checked="checked" />&nbsp; 刷卡付费
				&nbsp; &nbsp;<input type="radio" value="现金付费" name="describe" />&nbsp;
				现金付费 &nbsp; &nbsp;
			</span>
		</p>
		<p>
			<label>票据编号</label> <span class="field"><input type="text"
				name="ticket" id="ticket" class="longinput" style="width: 200px;" />&nbsp;&nbsp;</span>
		</p>
		<p>
			<label>充值租赁点</label> <span class="field"><select
				data-placeholder="请选择租赁点" name="siteId" id="siteId"
				class="chzn-select" style="width: 200px;" tabindex="2">
					<option value=""></option>
			</select></span>
		</p>
	</form>
</body>
</html>

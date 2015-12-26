var ComAxCtrl = null;
function initss() {
	document.getElementById("fingerAGM").value = "";
	document.getElementById("info").value = "";
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
		var result = ComAxCtrl.CommOpen(1, "115200,n,8,1"); // 1表示com口编号
		if (result == 0) {
			alert("无法找到读卡设备！");
			return false;
		}
	}
	if (!ComAxCtrl.IsCommOpen()) {
		alert("指纹仪未打开！");
		return false;
	}

	// 发送命令
	document.getElementById("info").value = "sucess";
	// var cR = ComAxCtrl.CommSend(stringToHex("AA,FF,70,52,27"));
	// alert(0XAA);
}

function jc() {
	var arr = [ 0xAA, 0xFF, 0x70, 0x26 ];
	var ss = arrayToHex(arr);
	// var cR = ComAxCtrl.CommSend("AAFF702603");
	var cR = ComAxCtrl.CommSend(ss);
}
/*
 * function dqwzkh() { // ComAxCtrl.CommSend("AAFF100160FFFFFFFFFFFF24");
 * alert('ddd'); var arr =
 * [0xAA,0xFF,0x10,0x01,0x60,0xFF,0xFF,0xFF,0xFF,0xFF,0xFF] ; var ss =
 * arrayToHex(arr); var cR = ComAxCtrl.CommSend(ss); }
 */

var wzkh_q = '';
var wzkh_h = '';
function OnCommRecv(data) {
	alert(data);

	var dataArr = data.split(" ");
	if (dataArr[0] == 'BB', dataArr[1] == 'FF') {
		if (dataArr[2] == 'A0' || dataArr[2] == 'A1') {
			alert('没有检测到卡信息');
		} else if (dataArr[2] == '70') {
			var nzkh = '';
			for (var i = 3; i < dataArr.length; i++) {
				nzkh += dataArr[i];
			}
			document.getElementById("info").value += '内置卡号：' + nzkh + ',';
		} else if (dataArr[2] == '10') {
			wzkh_q = '';
			for (var i = 3; i < dataArr.length; i++) {
				var ts = String.fromCharCode(parseInt(dataArr[i], 16));
				wzkh_q += ts;
			}
			if (wzkh_h != '') {
				var wzkh = wzkh_q + wzkh_h;
				document.getElementById("info").value += '外置卡号：' + wzkh + ',';
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
			document.getElementById("info").value += '外置卡号：' + wzkh + ',';
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
		// 接收完数据
	}
}
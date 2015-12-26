<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>

<style type="text/css">
body,html,#allmap {
	width: 100%;
	height: 100%;
	overflow: visible;
	margin: 0;
}

#l-map {
	height: 100%;
	width: 78%;
	float: left;
	border-right: 2px solid #bcbcbc;
}

#r-result {
	height: 100%;
	width: 20%;
	float: left;
}

.aaa {
	position: relative;
}

.aaa div {
	display: none;
}

.aaa:hover div {
	position: absolute;
	display: block;
	min-width: 400px;
	top: 60px;
	border: 1px solid rgb(91, 185, 233);
	background-color: rgb(228, 246, 255);
	z-index: 999;
}
</style>

<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=hvAmVntuPfdU9zzuQvvR5PQq"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/library/DistanceTool/1.2/src/DistanceTool_min.js"></script>
<script type="text/javascript"
	src="http://developer.baidu.com/map/jsdemo/demo/convertor.js"></script>
</head>

<body>
	<input type="hidden" id="lat" value="${map.lat}"> <input
		type="hidden" id="lng" value="${map.lng}"><input
		type="hidden" id="city" value="${map.city}"> &nbsp;&nbsp;
	<input type="text" id="suggestId" size="20" value=""
		style="width: 200px;" placeholder="输入关键字查找" />
	<div id="searchResultPanel"
		style="border: 1px solid #C0C0C0; width: 150px; height: auto; display: none;"></div>
	<div id="allmap"></div>
</body>
<script type="text/javascript">
	jQuery(document).ready(function() {
		baiduMap.init();
	});
	var baiduMap = {
		map : null,
		init : function() {
			// 百度地图API功能
			function G(id) {
				return document.getElementById(id);
			}
			var map = new BMap.Map("allmap");

			var ac = new BMap.Autocomplete( //建立一个自动完成的对象
			{
				"input" : "suggestId",
				"location" : map
			});

			ac.addEventListener("onhighlight", function(e) { //鼠标放在下拉列表上的事件
				var str = "";
				var _value = e.fromitem.value;
				var value = "";
				if (e.fromitem.index > -1) {
					value = _value.province + _value.city + _value.district
							+ _value.street + _value.business;
				}
				str = "FromItem<br />index = " + e.fromitem.index
						+ "<br />value = " + value;

				value = "";
				if (e.toitem.index > -1) {
					_value = e.toitem.value;
					value = _value.province + _value.city + _value.district
							+ _value.street + _value.business;
				}
				str += "<br />ToItem<br />index = " + e.toitem.index
						+ "<br />value = " + value;
				G("searchResultPanel").innerHTML = str;
			});

			var myValue = "";
			ac.addEventListener("onconfirm", function(e) { //鼠标点击下拉列表后的事件
				var _value = e.item.value;
				myValue = _value.province + _value.city + _value.district
						+ _value.street + _value.business;
				G("searchResultPanel").innerHTML = "onconfirm<br />index = "
						+ e.item.index + "<br />myValue = " + myValue;

				setPlace();
			});

			function setPlace() {
				//					map.clearOverlays();    //清除地图上所有覆盖物 
				function myFun() {
					var pp = local.getResults().getPoi(0).point; //获取第一个智能搜索的结果
					map.centerAndZoom(pp, 18);
					//						map.addOverlay(new BMap.Marker(pp));    //添加标注
				}
				var local = new BMap.LocalSearch(map, { //智能搜索
					onSearchComplete : myFun
				});
				local.search(myValue);
			}

			var oldlng = jQuery("#lng").val();
			var oldlat = jQuery("#lat").val();
			if (oldlng != "" && oldlat != "") {
				var gps = new BMap.Point(oldlng, oldlat);
				map.centerAndZoom(gps, 20);
				var marker = new BMap.Marker(gps);
				map.addOverlay(marker);
			} else {
				map.centerAndZoom(jQuery('#city').val(), 12);
			}
			map.addControl(new BMap.NavigationControl());
			map.enableScrollWheelZoom(); // 启用滚轮放大缩小，默认禁用
			map.enableContinuousZoom(); // 启用地图惯性拖拽，默认禁用
			map.addEventListener("click", function(e) {
				getXy(map, e);
			});
			this.map = map;
		}
	};

	var getXy = function(map, e) {
		var x = e.point.lng;
		var y = e.point.lat;
		map.clearOverlays(); // 清除上次的标记
		var point = new BMap.Point(x, y);
		var marker = new BMap.Marker(point);
		map.addOverlay(marker); // 添加标记
		// marker.setAnimation(BMAP_ANIMATION_BOUNCE); // 动画效果

		jQuery("#lng").attr("value", x.toFixed(6));
		jQuery("#lat").attr("value", y.toFixed(6));
	};

	function clickOK(callBack) {
		var lng = jQuery("#lng").val();
		var lat = jQuery("#lat").val();
		callBack(lat, lng);
	}
</script>
</html>

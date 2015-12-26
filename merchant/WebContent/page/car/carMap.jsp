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
</style>

<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=hvAmVntuPfdU9zzuQvvR5PQq"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/library/DistanceTool/1.2/src/DistanceTool_min.js"></script>
<script type="text/javascript"
	src="http://developer.baidu.com/map/jsdemo/demo/convertor.js"></script>
</head>

<body>

	<input type="hidden" id="carId" name="carId" value="${param.carId}">
		<input type="hidden" id="carNumber" name="carNumber" value="">
			<input type="text" id="suggestId" size="20" value=""
			style="width: 200px;" placeholder="输入关键字查找" />
			<div id="searchResultPanel"
				style="border: 1px solid #C0C0C0; width: 150px; height: auto; display: none;"></div>
			<div id="allmap"></div>
</body>
<script type="text/javascript">
	jQuery(document).ready(function() {
		requstSites();

	});

	var requstSites = function() {
		jQuery.ajax({
			type : "POST",
			url : contextPath + '/car/car/ajaxCarPoints.htm?t='
					+ new Date().getTime(),
			data : 'carId=' + jQuery('#carId').val() + '&carNumber=',
			async : true,
			dataType : 'json',
			success : function(data) {
				// 初始化地图
				baiduMap.init(data[0]);
			},
			error : function() {

			}
		});
	};

	var baiduMap = {
		map : null,
		init : function(data) {
			// 百度地图API功能
			function G(id) {
				return document.getElementById(id);
			}
			var map = new BMap.Map("allmap", {
				enableMapClick : false
			});//禁用点击事件

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

			// 租赁点信息
			var lng = data['lng'];
			var lat = data['lat'];

			var myIcon = new BMap.Icon(contextPath + "/img/map/waitCar.PNG",
					new BMap.Size(38, 84));

			if (data['currentCarStatus'] == '已取车') {// 车辆使用状态
				myIcon = new BMap.Icon(contextPath + "/img/map/useCar.PNG",
						new BMap.Size(38, 84));
			}

			var siteContent = '<div class="zld_name"><span>'
				+ data['carNumber']
				+ ' (设备编号: '+data['deviceNO']+') </span></div>'
				+ '<div class="zld_pic"><img id="imgDemo" onError="imgError(this)" src="'
				+ contextPath
				+ data['littleIcon']
				+ '" width="150px" height="100px;"></div>'
				+ '<div class="zld_name_2" style="margin-top:10px"><span>所在租赁点: '
				+ data['siteName']
				+ '</span></div>'
				+ '<div class="zld_name_2"><span>续航里程: '
				+ data['surplusKms'] + '</span></div>'
				+ '<div class="zld_name_2"><span>码表公里数: '
				+ data['curKms'] + '</span></div>'
				+ '<div class="zld_name_2"><span>使用状态: '
				+ data['currentCarStatus'] + '</span></div>';
			 

			var sitegps = new BMap.Point(lng, lat);
			BMap.Convertor.translate(sitegps, 0, function(point) { // 坐标转换完之后的回调函数
				var x1 = point.lng;
				var y1 = point.lat;
				// 转换后百度经纬度
				var point = new BMap.Point(x1, y1);
				var marker = new BMap.Marker(point, {
					icon : myIcon
				});

				map.addOverlay(marker);
				map.centerAndZoom(sitegps, 15);
				var ginfoWindow = new BMap.InfoWindow(siteContent); // 创建信息窗口对象 
				map.openInfoWindow(ginfoWindow, point); //开启信息窗口
				marker.addEventListener("click", function() {
					map.openInfoWindow(ginfoWindow, point);
				});
			});

			map.addControl(new BMap.NavigationControl());
			map.enableScrollWheelZoom(); // 启用滚轮放大缩小，默认禁用
			map.enableContinuousZoom(); // 启用地图惯性拖拽，默认禁用
			this.map = map;
		}
	};

	function imgError(thiz) {
		thiz.src = contextPath + '/img/car.png';
	}
</script>
</html>
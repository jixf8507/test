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
	<input type="hidden" id="type" value="${param.type}" />
	<input type="hidden" id="siteId" value="${param.siteId}" />

	<input type="hidden" id="inLng" value="${userSignPO.inLng}" />
	<input type="hidden" id="inLat" value="${userSignPO.inLat}" />
	<input type="hidden" id="outLng" value="${userSignPO.outLng}" />
	<input type="hidden" id="outLat" value="${userSignPO.outLat}" />
	<input type="hidden" id="inTime" value="${userSignPO.inTime}" />
	<input type="hidden" id="outTime" value="${userSignPO.outTime}" />

	<input type="hidden" id="lat" value="${param.lat}"> <input
		type="hidden" id="lng" value="${param.lng}"> &nbsp;&nbsp;
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
		var siteId = jQuery('#siteId').val();
		var paraData = {
			"siteId" : siteId
		};
		jQuery.ajax({
			type : "POST",
			url : contextPath + '/merchant/site/ajaxSites.htm?t='
					+ new Date().getTime(),
			data : 'paraData=' + encodeURI(JSON.stringify(paraData)),
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
			
			var type = jQuery("#type").val();
			var inLng = jQuery("#inLng").val();
			var inLat = jQuery("#inLat").val();
			var outLng = jQuery("#outLng").val();
			var outLat = jQuery("#outLat").val();
			var inTime = jQuery("#inTime").val();
			var outTime = jQuery("#outTime").val();

			// 租赁点信息
			var siteName = data['siteName'];
			var lng = data['lng'];
			var lat = data['lat'];
			var myIcon = new BMap.Icon(contextPath + "/img/map/site.png",
					new BMap.Size(38, 84));

			var siteContent = '<div class="zld_name"><span>'
					+ siteName
					+ '</span></div>'
					+ '<div class="zld_pic"><img id="imgDemo" onError="imgError(this)" src="'
					+ contextPath + data['imgUrl']
					+ '" width="300px" height="90px;"></div>' + data['address']
					+ '</span></div>' + '<div class="zld_name_2"><span>联系人: '
					+ data['principal'] + '</span></div>'
					+ '<div class="zld_name_2"><span>联系电话: ' + data['phone']
					+ '</span></div>';

			var sitegps = new BMap.Point(lng, lat);
			map.centerAndZoom(sitegps, 13);
			var marker = new BMap.Marker(sitegps, {
				icon : myIcon
			});
			map.addOverlay(marker);

			var ginfoWindow = new BMap.InfoWindow(siteContent); // 创建信息窗口对象 
			map.openInfoWindow(ginfoWindow, sitegps); //开启信息窗口
			marker.addEventListener("click", function() {
				map.openInfoWindow(ginfoWindow, sitegps);
			});
			if (siteName != '' && siteName != null) {
				if (type == 'in') {
					var gps = new BMap.Point(inLng, inLat);
					map.centerAndZoom(gps, 13);
					var marker = new BMap.Marker(gps);
					map.addOverlay(marker);

					var infoWindow = new BMap.InfoWindow("签到点： " + siteName
							+ "<br/>签到时间： " + inTime); // 创建信息窗口对象 
					map.openInfoWindow(infoWindow, gps); //开启信息窗口
					marker.addEventListener("click", function() {
						map.openInfoWindow(infoWindow, gps);
					});

				} else {
					var gps1 = new BMap.Point(outLng, outLat);
					map.centerAndZoom(gps1, 13);
					var marker1 = new BMap.Marker(gps1);
					map.addOverlay(marker1);

					var infoWindow1 = new BMap.InfoWindow("签出点： " + siteName
							+ "<br/>签出时间： " + outTime);
					map.openInfoWindow(infoWindow1, gps1);
					marker1.addEventListener("click", function() {
						map.openInfoWindow(infoWindow1, gps1);
					});
				}
			} else {
				map.centerAndZoom("芜湖市", 12);
			}
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
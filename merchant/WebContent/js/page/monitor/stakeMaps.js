// 百度地图操作对象
var stakeMap = {
	map : null,
	times : 30000,
	sitePO : {
		siteName : '站点名称',
		lat : 0,// 纬度
		lng : 0,// 经度
		imgUrl : '站点图片',
		address : '详细地址',
		phone : '联系电话',
		principal : '联系人',
		type : '类型',
		stakeCount : '充电桩数量'
	},
	devicePO : {
		factoryNo : '出厂编号',
		imgUrl : '充电桩图片',
		chargePort : '充电端口类型',
		lat : 0,// 纬度
		lng : 0,// 经度
		siteId : '所在租赁编码',
		status : '当前充电桩状态'
	},
	createSitePO : function(tables) {// 创建站点对象
		applyif(tables, this.sitePO);
		return tables;
	},
	createDevicePO : function(tables) {// 创建车辆对象
		applyif(tables, this.devicePO);
		return tables;
	},
	sites : {
		siteId : this.sitePO
	},
	devices : {
		deviceId : this.devicePO
	},
	imgSetting : { // 小车图片
		imageOffset : new BMap.Size(10, 10), // 图片的偏移量。为了是图片底部中心对准坐标点。
		imageSize : new BMap.Size(35, 42)
	},
	icons : {
		stakeIcon : new BMap.Icon(contextPath + "/img/map/site.png",
				new BMap.Size(38, 84), this.imgSetting),// 充电站图片
		deviceIcon : new BMap.Icon(contextPath + "/img/map/stake.png",
				new BMap.Size(38, 84), this.imgSetting),// 充电桩图片
	},
	addSiteMarker : function() {// 在百度地图上添加站点图标
		var thiz = this;
		jQuery.each(this.sites, function(index, site) {
			var point = new BMap.Point(site['lng'], site['lat']);
			var marker = new BMap.Marker(point, {
				icon : thiz.icons.stakeIcon
			});
			thiz.map.addOverlay(marker);
			thiz.addSiteClickHandler(site, marker);
		});
	},
	addCarMarker : function() {// 在百度地图上添加充电桩图标
		var thiz = this;
		jQuery.each(this.devices, function(index, devicePO) {
			var point = new BMap.Point(devicePO['lng'], devicePO['lat']);
			var marker = new BMap.Marker(point, {
				icon : thiz.icons.deviceIcon
			});
			thiz.map.addOverlay(marker);
			thiz.addCarClickHandler(devicePO, marker);
		});
	},
	addSiteClickHandler : function(sitePO, marker) {
		var thiz = this;
		marker
				.addEventListener(
						"click",
						function(e) {
							var p = e.target;
							var sContent = '<div class="zld_name"><span>'
									+ sitePO['siteName']
									+ '</span></div>'
									+ '<div class="zld_pic"><img id="imgDemo" onError="imgError(this)" src="'
									+ contextPath + sitePO['imgUrl']
									+ '" width="300px" height="90px;"></div>'
									+ '<div class="zld_name_2"><span>地址: '
									+ sitePO['address'] + '</span></div>'
									+ '<div class="zld_name_2"><span>联系人: '
									+ sitePO['principal'] + '</span></div>'
									+ '<div class="zld_name_2"><span>联系电话: '
									+ sitePO['phone'] + '</span></div>'
									+ '<div class="zld_name_2"><span>充电桩数量: '
									+ sitePO['stakeCount'] + '</span></div>';

							var point = new BMap.Point(p.getPosition().lng, p
									.getPosition().lat);
							var infoWindow = new BMap.InfoWindow(sContent); // 创建信息窗口对象
							thiz.map.openInfoWindow(infoWindow, point); // 开启信息窗口
						});
	},
	addCarClickHandler : function(devicePO, marker) {
		var thiz = this;
		marker
				.addEventListener(
						"click",
						function(e) {
							var status = devicePO['status'];
							if (status == '废弃') {
								status = '不联网';
							}
							var p = e.target;
							var sContent = '<div class="zld_pic" style="float: left;margin-right:30px;"><img id="imgDemo" onError="imgError(this)" src="'
									+ contextPath
									+ devicePO['imgUrl']
									+ '" width="80px" height="230px;"></div>'
									+ '<div class="zld_name_2" style="margin-top:10px;width:300px;"><span>出厂编号: '
									+ devicePO['factoryNo']
									+ '</span></div>'
									+ '<div class="zld_name_2" style="margin-top:8px;"><span>设备名称: '
									+ devicePO['deviceNo']
									+ '</span></div>'
									+ '<div class="zld_name_2" style="margin-top:8px;"><span>设备类型: '
									+ devicePO['deviceTypeName']
									+ '</span></div>'
									+ '<div class="zld_name_2" style="margin-top:8px;"><span>充电端口类型: '
									+ devicePO['chargePort']
									+ '</span></div>'
									+ '<div class="zld_name_2" style="margin-top:8px;"><span>电压: '
									+ devicePO['fixedVoltage']
									+ ' V</span></div>'
									+ '<div class="zld_name_2" style="margin-top:8px;"><span>功率: '
									+ devicePO['devicePower']
									+ ' KW</span></div>'
									+ '<div class="zld_name_2" style="margin-top:8px;"><span>所在充电站: '
									// +
									// thiz.sites[devicePO['siteId']]['siteName']
									+ devicePO['siteName']
									+ '</span></div>'
									+ '<div class="zld_name_2" style="margin-top:8px;"><span>状态: '
									+ status + '</span></div>';

							var point = new BMap.Point(p.getPosition().lng, p
									.getPosition().lat);
							var infoWindow = new BMap.InfoWindow(sContent); // 创建信息窗口对象
							thiz.map.openInfoWindow(infoWindow, point); // 开启信息窗口
						});
	},
	refresh : function() {
		var paraData = {
			"deviceTypeNo" : 2,
			"flag" : "正常"
		};
		var thiz = this;
		thiz.devices = {};
		var loadData = function() {
			jQuery.ajax({
				type : "POST",
				url : contextPath + '/stake/device/ajaxDevicePoints.htm?t='
						+ new Date().getTime(),
				data : 'paraData=' + encodeURI(JSON.stringify(paraData)),
				async : true,
				async : true,
				dataType : 'json',
				success : function(data) {
					jQuery.each(data, function(index, devicePO) {
						thiz.devices[devicePO['id']] = thiz.createDevicePO({
							factoryNo : devicePO['factoryNo'],
							imgUrl : devicePO['imgUrl'],
							chargePort : devicePO['chargePort'],
							deviceNo : devicePO['deviceNo'],
							deviceTypeName : devicePO['deviceTypeName'],
							fixedVoltage : devicePO['fixedVoltage'],
							devicePower : devicePO['devicePower'],
							lat : devicePO['lat'],
							lng : devicePO['lng'],
							siteId : devicePO['site_code'],
							status : devicePO['status'],
							siteName : devicePO['siteName']
						});
					});
					// 清除所有覆盖物
					thiz.map.clearOverlays();
					thiz.addSiteMarker();
					thiz.addCarMarker();
				},
				error : function() {
				}
			});
		};
		loadData();
		// 设置定时刷新
		// setInterval(loadData, this.times);
	},
	init : function(sites) {
		// 百度地图API功能
		function G(id) {
			return document.getElementById(id);
		}

		var map = new BMap.Map("stakemap");
		 map.centerAndZoom(jQuery('#city').val(), 12);
//		map.centerAndZoom(new BMap.Point(118.101228, 31.506247), 12);

		map.addControl(new BMap.NavigationControl());
		map.enableScrollWheelZoom(); // 启用滚轮放大缩小，默认禁用
		map.enableContinuousZoom(); // 启用地图惯性拖拽，默认禁用
		// map.addControl(new BMap.MapTypeControl({mapTypes:
		// [BMAP_NORMAL_MAP,BMAP_HYBRID_MAP]}));//地图类型控件，三维，3D等
		// map.addControl(new BMap.OverviewMapControl({isOpen:true, anchor:
		// BMAP_ANCHOR_BOTTOM_RIGHT}));
		this.map = map;

		var ac = new BMap.Autocomplete( // 建立一个自动完成的对象
		{
			"input" : "suggestId",
			"location" : map
		});

		ac.addEventListener("onhighlight", function(e) { // 鼠标放在下拉列表上的事件
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
		ac.addEventListener("onconfirm", function(e) { // 鼠标点击下拉列表后的事件
			var _value = e.item.value;
			myValue = _value.province + _value.city + _value.district
					+ _value.street + _value.business;
			G("searchResultPanel").innerHTML = "onconfirm<br />index = "
					+ e.item.index + "<br />myValue = " + myValue;

			setPlace();
		});

		function setPlace() {
			// map.clearOverlays(); //清除地图上所有覆盖物
			function myFun() {
				var pp = local.getResults().getPoi(0).point; // 获取第一个智能搜索的结果
				map.centerAndZoom(pp, 18);
				// map.addOverlay(new BMap.Marker(pp)); //添加标注
			}
			var local = new BMap.LocalSearch(map, { // 智能搜索
				onSearchComplete : myFun
			});
			local.search(myValue);
		}

		var thiz = this;
		this.sites = {};
		// 循环租赁点
		jQuery.each(sites, function(index, site) {
			thiz.sites[site.id] = thiz.createSitePO({
				siteName : site['siteName'],
				lat : site['lat'],
				lng : site['lng'],
				imgUrl : site['imgUrl'],
				address : site['address'],
				phone : site['phone'],
				principal : site['principal'],
				type : site['type'],
				stakeCount : site['stakeCount']
			});
		});

		this.addSiteMarker();
		this.refresh();
	}
};

var applyif = function(object, config) {
	if (object) {
		for ( var property in config) {
			if (object[property] === undefined) {
				object[property] = config[property];
			}
		}
	}
	return object;
};

function imgError(thiz) {
	thiz.src = contextPath + '/img/car.png';
}
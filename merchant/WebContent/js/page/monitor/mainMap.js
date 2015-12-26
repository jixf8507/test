// 百度地图操作对象
var baiduMap = {
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
		type : '类型'
	},
	carPO : {
		carNumber : '车牌号码',
		imgUrl : '车辆图片',
		deviceNO : '设备编号',
		lat : 31.0123,// 纬度
		lng : 110.1453,// 经度
		siteId : '所在租赁编码',// 
		status : '当前车辆状态',
		kms : 100
	// 码表公里数
	},
	createSitePO : function(tables) {// 创建站点对象
		applyif(tables, this.sitePO);
		return tables;
	},
	createCarPO : function(tables) {// 创建车辆对象
		applyif(tables, this.carPO);
		return tables;
	},
	sites : {
		siteId : this.sitePO
	},
	cars : {
		carId : this.carPO
	},
	imgSetting : { // 小车图片
		imageOffset : new BMap.Size(0, 0), // 图片的偏移量。为了是图片底部中心对准坐标点。
		imageSize : new BMap.Size(35, 42)
	},
	icons : {
		siteIcon : new BMap.Icon(contextPath + "/img/map/site.png",
				new BMap.Size(38, 84), this.imgSetting),// 租赁点图片
		parkingIcon : new BMap.Icon(contextPath + "/img/map/parking.png",
				new BMap.Size(38, 84), this.imgSetting),// 停车场
		stakeIcon : new BMap.Icon(contextPath + "/img/map/stake.png",
				new BMap.Size(38, 84), this.imgSetting),// 充电站
		waitCar : new BMap.Icon(contextPath + "/img/map/waitCar.PNG",
				new BMap.Size(38, 84), this.imgSetting),// 空闲车辆图片
		useCar : new BMap.Icon(contextPath + "/img/map/useCar.PNG",
				new BMap.Size(38, 84), this.imgSetting),// 使用车辆图片
	},
	addSiteMarker : function() {// 在百度地图上添加站点图标
		var thiz = this;
		jQuery.each(this.sites, function(index, site) {
			var point = new BMap.Point(site['lng'], site['lat']);
			var marker = new BMap.Marker(point, {
				icon : thiz.icons.siteIcon
			});
			if (site['type'] & 1 == 1) {// 是租赁点
				marker = new BMap.Marker(point, {
					icon : thiz.icons.siteIcon
				});
			} else if (site['type'] & 2 == 2) {// 是充电站
				marker = new BMap.Marker(point, {
					icon : thiz.icons.stakeIcon
				});
			} else if (site['type'] & 4 == 4) {// 是停车场
				marker = new BMap.Marker(point, {
					icon : thiz.icons.parkingIcon
				});
			}

			// var label = new BMap.Label(site['siteName'], {
			// offset : new BMap.Size(35, 10)
			// });
			// label.setStyle({
			// color : '#282928',
			// border : 'none',
			// fontWeight : '600',
			// background : '',
			// filter : 'glow(color=white,strength=2)'
			// });
			// marker.setLabel(label); // 添加车牌标注
			thiz.map.addOverlay(marker);
			thiz.addSiteClickHandler(site, marker);
		});
	},
	addCarMarker : function() {// 在百度地图上添加车辆图标
		var thiz = this;
		jQuery.each(this.cars, function(index, carPO) {
			var gps = new BMap.Point(carPO['lng'], carPO['lat']);
			BMap.Convertor.translate(gps, 0, function(point) { // 坐标转换完之后的回调函数
				var x1 = point.lng;
				var y1 = point.lat;
				// 转换后百度经纬度
				var point = new BMap.Point(x1, y1);
				var marker = new BMap.Marker(point, {
					icon : thiz.icons.waitCar
				});
				if (carPO['status'] == '已取车') {// 是租赁点
					marker = new BMap.Marker(point, {
						icon : thiz.icons.useCar
					});
				}
				thiz.map.addOverlay(marker);
				thiz.addCarClickHandler(carPO, marker);
			});
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
									+ contextPath
									+ sitePO['imgUrl']
									+ '" width="300px" height="90px;"></div>'
									+ '<div class="zld_name_2"><span>地址: '
									+ sitePO['address'] + '</span></div>'
									+ '<div class="zld_name_2"><span>联系人: '
									+ sitePO['principal'] + '</span></div>'
									+ '<div class="zld_name_2"><span>联系电话: '
									+ sitePO['phone'] + '</span></div>';

							var point = new BMap.Point(p.getPosition().lng, p
									.getPosition().lat);
							var infoWindow = new BMap.InfoWindow(sContent); // 创建信息窗口对象
							thiz.map.openInfoWindow(infoWindow, point); // 开启信息窗口
							
							openWin(sitePO['id']);
						});
	},
	addCarClickHandler : function(carPO, marker) {
		var thiz = this;
		marker
				.addEventListener(
						"click",
						function(e) {
							var p = e.target;
							var sContent = '<div class="zld_name"><span>'
									+ carPO['carNumber']
									+ '</span></div>'
									+ '<div class="zld_pic"><img id="imgDemo" onError="imgError(this)" src="'
									+ contextPath
									+ carPO['imgUrl']
									+ '" width="150px" height="100px;"></div>'
									+ '<div class="zld_name_2" style="margin-top:10px"><span>所在租赁点: '
									+ thiz.sites[carPO['siteId']]['siteName']
									+ '</span></div>'
									+ '<div class="zld_name_2"><span>使用状态: '
									+ carPO['status'] + '</span></div>'
									+ '<div class="zld_name_2"><span>码表公里数: '
									+ carPO['kms'] + '</span></div>';

							var point = new BMap.Point(p.getPosition().lng, p
									.getPosition().lat);
							var infoWindow = new BMap.InfoWindow(sContent); // 创建信息窗口对象
							thiz.map.openInfoWindow(infoWindow, point); // 开启信息窗口
						});
	},
	refresh : function() {
		var thiz = this;
		thiz.cars = {};
		var loadData = function() {
			var carNumber = jQuery('#carNumber').val();
			jQuery.ajax({
				type : "POST",
				url : contextPath + '/car/car/ajaxCarPoints.htm?t='
				+ new Date().getTime(),
				data : 'carNumber='+carNumber,
				async : true,
				dataType : 'json',
				success : function(data) {
					jQuery.each(data, function(index, carPO) {
						thiz.cars[carPO['id']] = thiz.createCarPO({
							carNumber : carPO['carNumber'],
							imgUrl : carPO['littleIcon'],
							deviceNO : carPO['deviceNO'],
							lat : carPO['lat'],// 纬度
							lng : carPO['lng'],// 经度
							siteId : carPO['curSiteId'],// 
							status : carPO['currentCarStatus'],
							kms : carPO['kms']
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
		setInterval(loadData, this.times); 
	},
	init : function(sites) {
		var map = new BMap.Map("allmap");
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
		
		var thiz = this;
		this.sites = {};
		// 循环租赁点
		jQuery.each(sites, function(index, site) {
			thiz.sites[site.id] = thiz.createSitePO({
				id : site['id'],
				siteName : site['siteName'],
				lat : site['lat'],
				lng : site['lng'],
				imgUrl : site['imgUrl'],
				address : site['address'],
				phone : site['phone'],
				principal : site['principal'],
				type : site['type']
			});
		});

		this.addSiteMarker();
		this.refresh();
	}
};

//车辆列表
var openWin = function(id){
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 500;
	diag.Title = "查看商家车辆资源";
	diag.URL = contextPath + "/car/car/statusWin.htm?siteId=" + id;
	diag.show();
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
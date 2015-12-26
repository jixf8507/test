$(document).ready(function() {
	led.init();
	//租赁点分布
	$(".d_c").toggle(function() {
		$(".fb").css("background", "url(images/car_s.png)");
		$(".d_c").css("color", "#929292");
		$(".c1").css("color", "#929292");
		led.isShowCar = false;
		led.refreshMap();
	}, function() {
		$(".fb").css("background", "url(images/car.png)");
		$(".d_c").css("color", "#fff");
		$(".c1").css("color", "#ee4936");
		led.isShowCar = true;
		led.refreshMap();
	});
	//充电站分布
	$(".d_a").toggle(function() {
		$(".fb2").css("background", "url(images/cd_s.png)");
		$(".d_a").css("color", "#929292");
		$(".c2").css("color", "#929292");
		led.isShowCharge = false;
		led.refreshMap();
	}, function() {
		$(".fb2").css("background", "url(images/cd.png)");
		$(".d_a").css("color", "#fff");
		$(".c2").css("color", "#f5c700");
		led.isShowCharge = true;
		led.refreshMap();
	});
	//停车场分布
	$(".d_b").toggle(function() {
		$(".fb3").css("background", "url(images/tc_s.png)");
		$(".d_b").css("color", "#929292");
		$(".c3").css("color", "#929292");
		led.isShowMerchant = false;
		led.refreshMap();
	}, function() {
		$(".fb3").css("background", "url(images/tc.png)");
		$(".d_b").css("color", "#fff");
		$(".c3").css("color", "#3edb2c");
		led.isShowMerchant = true;
		led.refreshMap();
	});

});

// led显示对象
var led = {
	isShowCar : true,
	isShowMerchant : true,
	isShowCharge : true,
	map : null,// 百度地图
	times : 10000,
	merchantIcon : new BMap.Icon("../../img/map/site.png", new BMap.Size(35, 84), { // 租赁点图片
		imageOffset : new BMap.Size(0, 0), // 图片的偏移量。为了是图片底部中心对准坐标点。
		imageSize : new BMap.Size(35, 35)
	}),
	carIcon : new BMap.Icon("../../img/map/parking.png", new BMap.Size(35, 84), { // 停车场图片
		imageOffset : new BMap.Size(0, 0), // 图片的偏移量。为了是图片底部中心对准坐标点。
		imageSize : new BMap.Size(35, 35)
	}),
	chargeIcon : new BMap.Icon("../../img/map/stake.png", new BMap.Size(35, 84), { // 充电站图片
		imageOffset : new BMap.Size(0, 0), // 图片的偏移量。为了是图片底部中心对准坐标点。
		imageSize : new BMap.Size(35, 35)
	}),
	// 租赁点分页显示结果集
	siteList : [ {
		id : 0,
		siteName : '',
		address : '',
		phone : '',
		lng : 0,
		lat : 0
	} ],
	// 充电站坐标列表
	chargeList : [ {
		id : 0,
		siteName : '',
		address : '',
		phone : '',
		lng : 0,
		lat : 0
	} ],

	// 车辆分页显示结果集
	carList : [ {
		id : 0,
		siteName : '',
		address : '',
		phone : '',
		lng : 0,
		lat : 0
	} ],
	// 加载租赁点数据
	loadSiteDatas : function(name) {

		// 清空数据列表
		this.siteList = [];
		var paraData = {
			type : 4
		};
		var thiz = this;
		$.ajax({
			type : "POST",
			url : "../../merchant/site/ajaxSites.htm?",
			data : 'paraData=' + encodeURI(JSON.stringify(paraData)),
			async : true,
			dataType : 'json',
			success : function(data) {
				$.each(data, function(index, item) {
					// 循环获取数据 ,将返回的租赁点加入到租赁点结果集中
					thiz.siteList.push({
						id : item['id'],
						siteName : item['siteName'],
						address : item['address'],
						phone : item['phone'],
						lng : item['lng'],
						lat : item['lat'],
						imgUrl : item['imgUrl'],
						principal : item['principal'],
						address : item['address']
					});
				});
				$('#merchant_count').html('(' + data.length + ')');
				// 在地图上加入租赁点
				thiz.addSiteToMap();
			},
			error : function() {
//				alert("加载数据失败");
			}
		});
	},
	// 加载充电站数据
	loadChargeDatas : function(name) {
		// 清空数据列表
		this.chargeList = [];
		var paraData = {
			type : 2
		};
		var thiz = this;
		$.ajax({
			type : "POST",
			url : "../../merchant/site/ajaxSites.htm?",
			data : 'paraData=' + encodeURI(JSON.stringify(paraData)),
			async : true,
			dataType : 'json',
			success : function(data) {
				$.each(data, function(index, item) {
					// 循环获取数据 ,将返回的充电站加入到充电站结果集中
					thiz.chargeList.push({
						id : item['id'],
						siteName : item['siteName'],
						address : item['address'],
						phone : item['phone'],
						lng : item['lng'],
						lat : item['lat'],
						imgUrl : item['imgUrl'],
						principal : item['principal'],
						address : item['address']
					});
				});
				$('#charge_count').html('(' + data.length + ')');
				// 在地图上加入充电站
				thiz.addChargeToMap();
			},
			error : function() {
//				alert("加载数据失败");
			}
		});
	},
	// 加载停车场数据
	loadCarDatas : function(name) {
		// 清空数据列表
		this.carList = [];
		var paraData = {
				type : 1
			};
		var thiz = this;
		$.ajax({
			type : "POST",
			url : "../../merchant/site/ajaxSites.htm?",
			data : 'paraData=' + encodeURI(JSON.stringify(paraData)),
			async : true,
			dataType : 'json',
			success : function(data) {
				$.each(data, function(index, item) {
					// 循环获取数据 ,将返回的停车场加入到停车场结果集中
					thiz.carList.push({
						id : item['id'],
						siteName : item['siteName'],
						address : item['address'],
						phone : item['phone'],
						lng : item['lng'],
						lat : item['lat'],
						imgUrl : item['imgUrl'],
						principal : item['principal'],
						address : item['address']
					});
				});
				
				
				$('#car_count').html('(' + data.length + ')');
				
				// 在地图上加载停车场图标
				thiz.addCarToMap();
			},
			error : function() {
//				alert("加载数据失败");
			}
		});
	},
	/*// 加载车辆数据
	loadCarDatas : function(name) {
		// 清空数据列表
		this.carList = [];
		var thiz = this;
		$.ajax({
			type : "POST",
			url : "../../car/car/ajaxCarPoints.htm",
			data : "carNumber=" + encodeURI(encodeURI(name)),
			async : true,
			dataType : 'json',
			success : function(data) {
				$.each(data, function(index, item) {
					// 循环获取数据 ,将返回的租赁点加入到租赁点结果集中
					thiz.carList.push({
						id : item['id'],
						carNumber : item['carNumber'],
						siteName : item['siteId'],
						status : item['status'],
						lng : item['lng'],
						lat : item['lat']
					});
				});

				$('#car_count').html('(' + data.length + ')');

				// 在地图上加载车辆图标
				thiz.addCarToMap(data);
			},
			error : function() {
				alert("加载数据失败");
			}
		});
	},*/
	// 加载系统信息
	loadSystemInfo : function() {
		$.ajax({
			type : "POST",
			url : "../../monitor/main/ajaxSystem.htm?",
			async : true,
			dataType : 'json',
			success : function(data) {
				var kms = data.kms;
				var kms_jt = data.kms_jt;
				$('#wq_jt').html(Math.abs(Math.ceil(kms_jt * 0.4)) + '千克');
				$('#ry_jt').html(Math.abs(Math.ceil(kms_jt * 0.12)) + '升');
				$('#yf_jt').html(Math.abs(Math.ceil(kms_jt * 0.6)) + '元');

				$('#wq_all').html(Math.abs(Math.ceil(kms * 0.4)) + '千克');
				$('#ry_all').html(Math.abs(Math.ceil(kms * 0.12)) + '升');
				$('#yf_all').html(Math.abs(Math.ceil(kms * 0.6)) + '元');

			},
			error : function() {
				Dialog.alert("加载数据失败");
			}
		});
	},
	// 初始化百度地图
	initBdMap : function() {
		var map = new BMap.Map("allmap");
		map.centerAndZoom("芜湖市", 12);
		map.addControl(new BMap.NavigationControl());
		map.enableScrollWheelZoom(); // 启用滚轮放大缩小，默认禁用
		map.enableContinuousZoom(); // 启用地图惯性拖拽，默认禁用

		// map.addControl(new BMap.MapTypeControl({mapTypes:
		// [BMAP_NORMAL_MAP,BMAP_HYBRID_MAP]}));//地图类型控件，三维，3D等
		// map.addControl(new BMap.OverviewMapControl({isOpen:true, anchor:
		// BMAP_ANCHOR_BOTTOM_RIGHT}));
		this.map = map;
	},
	/*// 在地图上加入车辆图层
	addCarToMap : function() {
		if (!this.isShowCar) {
			return;
		}
		var siteContent = '<div class="zld_name"><span>'
			+ data.length
			+ '</span></div>'
			+ '<div class="zld_pic"><img id="imgDemo" onError="imgError(this)" src="'
			+ contextPath + data.length
			+ '" width="300px" height="90px;"></div>' + data.length
			+ '</span></div>' + '<div class="zld_name_2"><span>联系人: '
			+ data.length + '</span></div>'
			+ '<div class="zld_name_2"><span>联系电话: ' + data.length
			+ '</span></div>';
		var thiz = this ;
		for (var i = 0; i < this.carList.length; i++) {
			var carPO = this.carList[i];
			
			var gps = new BMap.Point(carPO['lng'], carPO['lat']);
			BMap.Convertor.translate(gps, 0, function(point) { // 坐标转换完之后的回调函数
				var x1 = point.lng;
				var y1 = point.lat;
				// 转换后百度经纬度
				var point = new BMap.Point(x1, y1);
				var label = new BMap.Label(carPO.carNumber, {
					offset : new BMap.Size(35, 10)
				});
				label.setStyle({
					color : '#282928',
					border : 'none',
					fontWeight : '600',
					background : '',
					filter : 'glow(color=white,strength=2)'
				});
				var gpsSite = new BMap.Point(point.lng, point.lat);
				
				var marker = new BMap.Marker(gpsSite,
						{
							icon : thiz.carIcon
						});
				// marker.setLabel(lableName);
				thiz.map.addOverlay(marker);
				
				var infoWindow = new BMap.InfoWindow(siteContent); // 创建信息窗口对象 
				thiz.map.openInfoWindow(infoWindow, gpsSite); //开启信息窗口
				marker.addEventListener("click", function() {
					thiz.map.openInfoWindow(infoWindow, gpsSite);
				});
			});
		}
	},*/
	// 在地图上加入租赁点图层
	addCarToMap : function() {
		if (!this.isShowCar) {
			return;
		}
		for (var i = 0; i < this.carList.length; i++) {
			var point = this.carList[i];
			var siteName = point.siteName;
			var imgUrl = point.imgUrl;
			var principal = point.principal;
			var phone = point.phone;
			var address = point.address;
			var label = new BMap.Label(point.carNumber, {
				offset : new BMap.Size(35, 10)
			});
			
			var siteContent = '<div class="zld_name"><span>租赁点：'
				+ siteName
				+ '</span></div>'
				+ '<div class="zld_pic"><img id="imgDemo" onError="imgError(this)" src="../..'
				+ imgUrl
				+ '" width="300px" height="90px;"></div>' + address
				+ '</span></div>' + '<div class="zld_name_2"><span>联系人: '
				+ principal + '</span></div>'
				+ '<div class="zld_name_2"><span>联系电话: ' + phone
				+ '</span></div>';
		
			label.setStyle({
				color : '#282928',
				border : 'none',
				fontWeight : '600',
				background : '',
				filter : 'glow(color=white,strength=2)'
			});
			var gpsSite = new BMap.Point(point.lng, point.lat);
			
			var marker = new BMap.Marker(gpsSite,
					{
						icon : this.merchantIcon
					});
			// marker.setLabel(lableName);
			this.map.addOverlay(marker);
			this.addClickHandler(marker,siteContent,gpsSite);
			
		}
	},
	addClickHandler : function(marker,siteContent,gpsSite) {
		var infoWindow = new BMap.InfoWindow(siteContent); // 创建信息窗口对象 
		marker.addEventListener("click", function() {
			this.map.openInfoWindow(infoWindow, gpsSite);
		});
	},
	// 在地图上加入停车场图层
	addSiteToMap : function() {
		if (!this.isShowMerchant) {
			return;
		}
		for (var i = 0; i < this.siteList.length; i++) {
			var point = this.siteList[i];
			var siteName = point.siteName;
			var imgUrl = point.imgUrl;
			var principal = point.principal;
			var phone = point.phone;
			var address = point.address;
			var label = new BMap.Label(point.carNumber, {
				offset : new BMap.Size(35, 10)
			});
			
			var siteContent = '<div class="zld_name"><span>停车场: '
				+ siteName
				+ '</span></div>'
				+ '<div class="zld_pic"><img id="imgDemo" onError="imgError(this)" src="../..'
				+ imgUrl
				+ '" width="300px" height="90px;"></div>' + address
				+ '</span></div>' + '<div class="zld_name_2"><span>联系人: '
				+ principal + '</span></div>'
				+ '<div class="zld_name_2"><span>联系电话: ' + phone
				+ '</span></div>';
			
			label.setStyle({
				color : '#282928',
				border : 'none',
				fontWeight : '600',
				background : '',
				filter : 'glow(color=white,strength=2)'
			});
			var gpsSite = new BMap.Point(point.lng, point.lat);
			
			var marker = new BMap.Marker(gpsSite,
					{
						icon : this.carIcon
					});
			// marker.setLabel(lableName);
			this.map.addOverlay(marker);
			this.addClickHandler(marker,siteContent,gpsSite);
		}
	},
	//在地图上加入充电站图层
	addChargeToMap : function() {
		if (!this.isShowCharge) {
			return;
		}
		for (var i = 0; i < this.chargeList.length; i++) {
			var point = this.chargeList[i];
			var siteName = point.siteName;
			var imgUrl = point.imgUrl;
			var principal = point.principal;
			var phone = point.phone;
			var address = point.address;
			
			var label = new BMap.Label(point.carNumber, {
				offset : new BMap.Size(35, 10)
			});
			
			var siteContent = '<div class="zld_name"><span>充电站：'
				+ siteName
				+ '</span></div>'
				+ '<div class="zld_pic"><img id="imgDemo" onError="imgError(this)" src="../..'
				+ imgUrl
				+ '" width="300px" height="90px;"></div>' + address
				+ '</span></div>' + '<div class="zld_name_2"><span>联系人: '
				+ principal + '</span></div>'
				+ '<div class="zld_name_2"><span>联系电话: ' + phone
				+ '</span></div>';
			
			label.setStyle({
				color : '#282928',
				border : 'none',
				fontWeight : '600',
				background : '',
				filter : 'glow(color=white,strength=2)'
			});
			var gpsSite = new BMap.Point(point.lng, point.lat);
			
			var marker = new BMap.Marker(gpsSite,
					{
						icon : this.chargeIcon
					}); 
			// marker.setLabel(lableName);
			this.map.addOverlay(marker);
			this.addClickHandler(marker,siteContent,gpsSite);
		}
	},
	// 初始化数据
	init : function() {
		// 初始化百度地图
		this.initBdMap();
		// 初始化租赁点
		this.loadSiteDatas('');
		// 初始化充电站
		this.loadChargeDatas('');
		// 初始化租赁点
		this.loadCarDatas('');
		// 加载系统信息
		this.loadSystemInfo();
	},
	// 刷新百度地图
	refreshMap : function() {
		this.map.clearOverlays();
		// 初始化停车场
		this.loadSiteDatas('');
		// 初始化充电站
		this.loadChargeDatas('');
		// 初始化租赁点
		this.loadCarDatas('');
	}
};

function imgError(thiz) {
	thiz.src = '../../img/car.png';
}

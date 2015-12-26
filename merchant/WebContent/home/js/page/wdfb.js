

	
	//启动车辆地图位置刷新
//	baiduMap.refreshCars() ;


// 百度地图操作对象
var baiduMap = {
	map : null,
	times : 10000,	
	init : function() {

	
		var map = new BMap.Map("allmap");
		map.centerAndZoom("芜湖市", 12);
		map.addControl(new BMap.NavigationControl());
		map.enableScrollWheelZoom();    //启用滚轮放大缩小，默认禁用
		map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
		
//		map.addControl(new BMap.MapTypeControl({mapTypes: [BMAP_NORMAL_MAP,BMAP_HYBRID_MAP]}));//地图类型控件，三维，3D等
//		map.addControl(new BMap.OverviewMapControl({isOpen:true, anchor: BMAP_ANCHOR_BOTTOM_RIGHT}));
		this.map = map;
	}
};

//加载百度地图

/**
 * 商家资源
 */
var merchantResouce = {
	sitePO : {// 租赁点对象
		siteName : '',// 租赁点名称
		totleCar : '0', // 租赁点车辆总数
		waitCar : '0', // 空闲车辆总数
		faultCar : '0', // 故障车辆总数
		chargeCar : '0', // 充电车辆总数
		otherCar : '0', // 其他车辆总数
		bookOrder : '0', // 预约车辆总数
		getOrder : '0',// 取车待审核订单
		returnOrder : '0',// 还车待审核订单
		chargeOrder : '0',// 长租订单
		merchantUseOrder : '0',// 员工用车订单
		carDevice : '0',// 安装车载设备数量
		onlineDevice : '0',// 在线车载设备数量
		outlineDevice : '0',// 离线车载设备数量
		totleStake : '0',// 充电桩总数量
		waitStake : '0',// 空闲充电桩数量
		chargeStake : '0',// 充电充电桩数量
		faultStake : '0',
		outLineStake : '0'
	// 故障充电桩数量
	},
	createSitePO : function(tables) {
		applyif(tables, this.sitePO);
		return tables;
	},
	sites : {
		siteId : this.sitePO
	},
	times : 30000,
	writeSitesTable : function() {// 画出租赁点表格
		reloadSiteHtml(this.sites);
	},
	timerRequest : function() {// 定时请求
		var thiz = this;
		var requstResouce = function() {
			var paraData = {};
			jQuery
					.ajax({
						type : "POST",
						url : contextPath
								+ '/monitor/main/ajaxSitesResouce.htm?t='
								+ new Date().getTime(),
						data : 'paraData='
								+ encodeURI(JSON.stringify(paraData)),
						async : false,
						dataType : 'json',
						success : function(data) {
							for ( var siteId in data) {
								var siteResouce = data[siteId];
								if(thiz.sites[siteId]){
									thiz.sites[siteId]['totleCar'] = siteResouce['totleCar'];
									thiz.sites[siteId]['waitCar'] = siteResouce['waitCar'];
									thiz.sites[siteId]['faultCar'] = siteResouce['faultCar'];
									thiz.sites[siteId]['chargeCar'] = siteResouce['chargeCar'];
									thiz.sites[siteId]['otherCar'] = siteResouce['otherCar'];
									thiz.sites[siteId]['bookOrder'] = siteResouce['bookOrder'];
									thiz.sites[siteId]['getOrder'] = siteResouce['getOrder'];
									thiz.sites[siteId]['returnOrder'] = siteResouce['returnOrder'];
									thiz.sites[siteId]['chargeCar'] = siteResouce['chargeCar'];
									thiz.sites[siteId]['chargeOrder'] = siteResouce['chargeOrder'];
									thiz.sites[siteId]['merchantUseOrder'] = siteResouce['merchantUseOrder'];
									thiz.sites[siteId]['carDevice'] = siteResouce['carDevice'];
									thiz.sites[siteId]['onlineDevice'] = siteResouce['onlineDevice'];
									thiz.sites[siteId]['outlineDevice'] = siteResouce['outlineDevice'];
									thiz.sites[siteId]['totleStake'] = siteResouce['totleStake'];
									thiz.sites[siteId]['waitStake'] = siteResouce['waitStake'];
									thiz.sites[siteId]['chargeStake'] = siteResouce['chargeStake'];
									thiz.sites[siteId]['faultStake'] = siteResouce['faultStake'];
									thiz.sites[siteId]['outLineStake'] = siteResouce['outLineStake'];
								}
								
							}
							thiz.writeSitesTable();
						},
						error : function() {

						}
					});
		};
		requstResouce();
		setInterval(requstResouce, this.times);
	},
	init : function(data) {// 初始租赁点数据
		var thiz = this;
		this.sites = {};
		// 循环租赁点
		jQuery.each(data, function(index, site) {
			thiz.sites[site.id] = thiz.createSitePO({
				siteName : site['siteName']
			});
		});
		this.writeSitesTable();
		this.timerRequest();
	}
};

/**
 * 加载租赁点监控表格
 */
var reloadSiteHtml = function(sites) {
	var alltotleCar = 0;
	var allwaitCar = 0;
	var allgetOrder = 0;
	var allbookOrder = 0;
	var allfaultCar = 0;
	var allchargeCar = 0;
	var allotherCar = 0;
	var allreturnOrder = 0;
	var allchargeOrder = 0;
	var allmerchantUseOrder = 0;
	
	jQuery('#updates').html('');
	for ( var siteId in sites) {
		var siteMap = sites[siteId];
		var html = '<div class="one_half" style="width: 47%; min-width: 400px;">';
		html += '<div class="widgetbox">';
		html += '<div class="widgetcontent userlistwidget nopadding">';
		html += '<a class="more" href="#">' + siteMap['siteName'] + '</a>';
		html += '<div class="one_half" style="width: 22%;">';
		html += '<ul>';
		html += '<li>';
		html += '<div class="info c_title">';
		html += '<span>车辆总数 </span>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javaScript:openWin(' + siteId + ',\'\')">'
				+ siteMap['totleCar'] + '</a>';
		html += '</div>';
		html += '</li>';
		html += '<li>';
		html += '<div class="info c_wait">';
		html += '<span>待租车辆</span>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javaScript:openWin(' + siteId + ',\'空闲\')">'
				+ siteMap['waitCar'] + '</a>';
		html += '</div>';
		html += '</li>';		
		html += '<li>';
		html += '<div class="info c_wait">';
		html += '<span>预约车辆</span>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javaScript:openBookWin(' + siteId + ')">'
		+ siteMap['bookOrder'] + '</a>';
		html += '</div>';
		html += '</li>';		
		html += '<li>';
		html += '<div class="info c_apply">';
		html += '<span>已租车辆</span>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javaScript:openUseWin(' + siteId + ')">'
				+ siteMap['getOrder'] + '</a>';
		html += '</div>';
		html += '</li>';
		html += '<li>';
		html += '<div class="info c_apply">';
		html += '<span>待还车辆</span>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javaScript:openOrderWin(' + siteId + ')">'
				+ siteMap['returnOrder'] + '</a>';
		html += '</div>';
		html += '</li>';
		html += '</ul>';
		html += '</div>';
		
		html += '<div class="one_half" style="width: 22%;">';
		html += '<ul>';
		html += '<li>';
		html += '<div class="info c_apply">';
		html += '<span>员工用车</span>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javaScript:openOtherUseWin(' + siteId + ')">'
		+ siteMap['merchantUseOrder'] + '</a>';
		html += '</div>';
		html += '</li>';
		html += '<li>';
		html += '<div class="info c_apply">';
		html += '<span>长租车辆</span>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javaScript:openOtherWin(' + siteId + ',\'2\')">'
				+ siteMap['chargeOrder'] + '</a>';
		html += '</div>';
		html += '</li>';
		html += '<li>';
		html += '<div class="info c_wait">';
		html += '<span>充电车辆</span>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javaScript:openWin(' + siteId + ',\'充电\')">'
		+ siteMap['chargeCar'] + '</a>';
		html += '</div>';
		html += '</li>';
		html += '<li>';
		html += '<div class="info c_wait">';
		html += '<span>其他车辆</span>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javaScript:openWin(' + siteId + ',\'其他\')">'
		+ siteMap['otherCar'] + '</a>';
		html += '</div>';
		html += '</li>';
		html += '<li>';
		html += '<div class="info c_fault">';
		html += '<span>故障车辆</span>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javaScript:openWin(' + siteId + ',\'故障\')">'
		+ siteMap['faultCar'] + '</a>';
		html += '</div>';
		html += '</li>';
		html += '</ul>';
		html += '</div>';

		html += '<div class="one_half" style="width: 22%;">';
		html += '<ul>';
		html += '<li>';
		html += '<div class="info c_title">';
		/*html += '<span>车载设备 </span>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">'
				+ siteMap['carDevice'] + '</a>';*/
		html += '<span>车载设备 </span>&nbsp;&nbsp;&nbsp;&nbsp;<a href='
			+ contextPath
			+ '/monitor/main/carDevice.htm?id='
			+ siteId + ' target="_blank">'+siteMap['carDevice']+'</a>';
		html += '</div>';
		html += '</li>';
		html += '<li>';
		html += '<div class="info c_wait">';
		html += '<span>在线</span>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">'
				+ siteMap['onlineDevice'] + '</a>';
		html += '</div>';
		html += '</li>';
		html += '<li>';
		html += '<div class="info c_fault">';
		html += '<span>离线</span>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">'
				+ siteMap['outlineDevice'] + '</a>';
		html += '</div>';
		html += '</li>';
		html += '</ul>';
		html += '</div>';

		html += '<div class="one_half" style="width: 22%;">';
		html += '<ul>';
		html += '<li>';
		html += '<div class="info c_title">';
		/*html += '<span>充电桩 </span>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">'
				+ siteMap['totleStake'] + '</a>';*/
		html += '<span>充电桩 </span>&nbsp;&nbsp;&nbsp;&nbsp;<a href='
			+ contextPath
			+ '/page/monitor/stakeMonitorWin.jsp?siteId='
			+ siteId + ' target="_blank">'+siteMap['totleStake']+'</a>';
		html += '</div>';
		html += '</li>';
		html += '<li>';

		html += '<div class="info c_wait">';
		html += '<span>空闲</span>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">'
				+ siteMap['waitStake'] + '</a>';
		html += '</div>';
		html += '</li>';
		html += '<li>';
		html += '<div class="info c_fault">';
		html += '<span>充电中</span>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">'
				+ siteMap['chargeStake'] + '</a>';
		html += '</div>';
		html += '</li>';
		html += '<li>';
		html += '<div class="info c_fault">';
		html += '<span>故障中</span>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">'
				+ siteMap['faultStake'] + '</a>';
		html += '</div>';
		html += '</li>';
		html += '<li>';
		html += '<div class="info c_fault">';
		html += '<span>失去连接</span>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">'
				+ siteMap['outLineStake'] + '</a>';
		html += '</div>';
		html += '</li>';
		html += '</ul>';
		html += '</div>';
		html += '</div>';

		html += '</div>';
		html += '</div>';
		jQuery('#updates').append(html);
		
		alltotleCar += Number(siteMap['totleCar']);
		allwaitCar += Number(siteMap['waitCar']);
		allbookOrder += Number(siteMap['bookOrder']); 
		allgetOrder += Number(siteMap['getOrder']);
		allfaultCar += Number(siteMap['faultCar']);
		allotherCar += Number(siteMap['otherCar']);
		allreturnOrder += Number(siteMap['returnOrder']);
		allchargeOrder += Number(siteMap['chargeOrder']);
		allmerchantUseOrder += Number(siteMap['merchantUseOrder']);
		allchargeCar += Number(siteMap['chargeCar']);
	}
	
	var allhtml = '<div class="one_half" style="width: 97%; min-width: 800px;">';
	allhtml += '<div class="widgetbox">';
	allhtml += '<div class="widgetcontent userlistwidget nopadding">';
	allhtml += '<a class="more" href="#">商家合计</a>';
	allhtml += '<br/>';
	allhtml += '<div class="one_half"  style="width: 11%;margin: 0;">';
	allhtml += '<div class="info c_title">';
	allhtml += '&nbsp; &nbsp;<span>车辆总数 </span>&nbsp;&nbsp;&nbsp;<a href="javaScript:openWin(\'\',\'\')">'
		+ alltotleCar + '</a>';
	allhtml += '</div>';
	allhtml += '</div>';
	
	allhtml += '<div class="one_half"  style="width: 10%;margin: 0;">';
	allhtml += '<div class="info c_wait">';
	allhtml += '&nbsp;&nbsp;<span>待租车辆</span>&nbsp;&nbsp;&nbsp;<a href="javaScript:openWin(\'\',\'空闲\')">'
		+ allwaitCar + '</a>';
	allhtml += '</div>';
	allhtml += '</div>';
	
	allhtml += '<div class="one_half"  style="width: 10%;margin: 0;">';
	allhtml += '<div class="info c_wait">';
	allhtml += '&nbsp;&nbsp;<span>预约车辆</span>&nbsp;&nbsp;&nbsp;<a href="javaScript:openBookWin(\'\')">'
		+ allbookOrder + '</a>';
	allhtml += '</div>';
	allhtml += '</div>';
	
	allhtml += '<div class="one_half"  style="width: 10%;margin: 0;">';
	allhtml += '<div class="info c_apply">';
	allhtml += '&nbsp;&nbsp;<span>已租车辆</span>&nbsp;&nbsp;&nbsp;<a href="javaScript:openUseWin(\'\')">'
		+ allgetOrder + '</a>';
	allhtml += '</div>';
	allhtml += '</div>';

	allhtml += '<div class="one_half"  style="width: 10%;margin: 0;">';
	allhtml += '<div class="info c_apply">';
	allhtml += '&nbsp;&nbsp;<span>待还车辆</span>&nbsp;&nbsp;&nbsp;<a href="javaScript:openOrderWin(\'\')">'
		+ allreturnOrder + '</a>';
	allhtml += '</div>';
	allhtml += '</div>'; 
	
	allhtml += '<div class="one_half"  style="width: 10%;margin: 0;">';
	allhtml += '<div class="info c_apply">';
	allhtml += '&nbsp;&nbsp;<span>员工用车</span>&nbsp;&nbsp;&nbsp;<a href="javaScript:openOtherUseWin(\'\')">'
		+ allmerchantUseOrder + '</a>';
	allhtml += '</div>';
	allhtml += '</div>';
	
	allhtml += '<div class="one_half"  style="width: 10%;margin: 0;">';
	allhtml += '<div class="info c_apply">';
	allhtml += '&nbsp;&nbsp;<span>长租车辆</span>&nbsp;&nbsp;&nbsp;<a href="javaScript:openOtherWin(\'\',\'2\')">'
		+ allchargeOrder + '</a>';
	allhtml += '</div>';
	allhtml += '</div>';
	
	allhtml += '<div class="one_half"  style="width: 10%;margin: 0;">';
	allhtml += '<div class="info c_wait">';
	allhtml += '&nbsp;&nbsp;<span>充电车辆</span>&nbsp;&nbsp;&nbsp;<a href="javaScript:openWin(\'\',\'充电\')">'
		+ allchargeCar + '</a>';
	allhtml += '</div>';
	allhtml += '</div>';
	
	allhtml += '<div class="one_half"  style="width: 10%;margin: 0;">';
	allhtml += '<div class="info c_wait">';
	allhtml += '&nbsp;&nbsp;<span>其他车辆</span>&nbsp;&nbsp;&nbsp;<a href="javaScript:openWin(\'\',\'其他\')">'
		+ allotherCar + '</a>';
	allhtml += '</div>';
	allhtml += '</div>';
	
	allhtml += '<div class="one_half"  style="width: 9%;margin: 0;">';
	allhtml += '<div class="info c_fault">';
	allhtml += '&nbsp;&nbsp;<span>故障车辆</span>&nbsp;&nbsp;&nbsp;<a href="javaScript:openWin(\'\',\'故障\')">'
		+ allfaultCar + '</a>';
	allhtml += '</div>';
	allhtml += '</div>';
	
	allhtml += '<br/><br/>';
		
	jQuery('#allResourse').html(allhtml);
	
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

// 商家全部车辆资源
var openWin = function(siteId, status) {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 500;
	diag.Title = "查看商家车辆资源";
	diag.URL = contextPath + "/car/car/statusWin.htm?siteId=" + siteId
			+ "&status=" + encodeURI(encodeURI(status));
	diag.show();
};

// 预约车辆
var openBookWin = function(siteId) {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 500;
	diag.Title = "查看商家预约车辆";
	diag.URL = contextPath+'/page/order/bookWin.jsp?siteId='+siteId;
	diag.show();
};

// 待还车辆查询
var openOrderWin = function(siteId) {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 500;
	diag.Title = "查看商家待还车辆";
	diag.URL = contextPath+'/page/order/returnConfirmWin.jsp?siteId='+siteId;
	diag.show();
};

//  长租车辆查询
var openOtherWin = function(siteId, orderType) {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 500;
	diag.Title = "查看商家长租车辆";
	diag.URL = contextPath + "/page/car/statusOtherWin.jsp?siteId=" + siteId+"&orderType="+orderType;
	diag.show();
};

// 已租车辆查询
var openUseWin = function(siteId) {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 500;
	diag.Title = "查看商家已租车辆";
	diag.URL = contextPath + "/page/order/getConfirmWin.jsp?siteId=" + siteId;
	diag.show();
};

// 员工用车车辆查询
var openOtherUseWin = function(siteId) {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 500;
	diag.Title = "查看商家员工用车车辆";
	diag.URL = contextPath + "/page/order/getOtherConfirmWin.jsp?siteId=" + siteId;
	diag.show();
};

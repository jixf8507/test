jQuery(document).ready(function() {
	requstSites();
	//console.log(jQuery("#siteId").val());
});

var requstSites = function() {
	var paraData = {
					"siteId":jQuery("#siteId").val()
					};
	
	jQuery.ajax({
		type : "POST",
		url : contextPath + '/merchant/site/ajaxSites.htm?t='
				+ new Date().getTime(),
		data : 'paraData=' + encodeURI(JSON.stringify(paraData)),
		async : true,
		dataType : 'json',
		success : function(data) {
			// 初始化商家资源
			monitorObj.init(data);

		},
		error : function() {

		}
	});
};

var monitorObj = {
	times : 30000,
	sitePO : {
		siteName : '站点名称',
		cars : {}
	},
	carPO : {
		carNumber : '车牌号码',
		imgUrl : '车辆图片',
		deviceNO : '设备编号',
		siteId : '所在租赁编码',
		status : '',
		soc : '',
		currentCarStatus : ''
	},
	createSitePO : function(tables) {// 创建站点对象
		applyif(tables, this.sitePO);
		return tables;
	},
	createCarPO : function(tables) {// 创建车辆对象
		applyif(tables, this.carPO);
		return tables;
	},
	sites : {},
	loadCarDevice : function() {
		var thiz = this;
		jQuery.ajax({
			type : "POST",
			url : contextPath + '/car/car/ajaxCarPointsBySite.htm?t='
					+ new Date().getTime()+'&siteId='+jQuery("#siteId").val(),
			data : '',
			async : false,
			dataType : 'json',
			success : function(data) {
				jQuery.each(data, function(index, carPO) {
					var siteId = carPO['curSiteId'] == '' ? 'customerCars'
							: carPO['curSiteId'];
					
						thiz.sites[siteId].cars[carPO['deviceNO']] = thiz
								.createCarPO({
									carNumber : carPO['carNumber'],
									imgUrl : carPO['littleIcon'],
									deviceNO : carPO['deviceNO'],
									status : '',
									soc : '--',
									currentCarStatus : ''
								});
					
				});
				thiz.refresh();
			},
			error : function() {
			}
		});
	},
	refresh : function() {
		var thiz = this;
		var loadCarStatus = function() {
			jQuery
					.ajax({
						type : "POST",
						url : contextPath
								+ '/monitor/main/ajaxCarDeviceStatus.htm?t='
								+ new Date().getTime(),
						data : '',
						async : true,
						dataType : 'json',
						success : function(data) {
							for ( var siteId in thiz.sites) {
								var siteMap = thiz.sites[siteId];
								for ( var deviceNO in siteMap.cars) {
									var carPO = siteMap.cars[deviceNO];
									var carModel = data[deviceNO];
									if (carModel) {
										carPO['status'] = carModel['wgStatus'];
										carPO['currentCarStatus'] = carModel['zcStatus'];
										carPO['soc'] = carModel['soc'] + '%';
										carPO['curkms']= carModel['curkms']+'km';
										carPO['surplusKms'] = carModel['surplusKms']+'km';
									} else {
										carPO['status'] = '离线';
										carPO['currentCarStatus'] = '';
										carPO['soc'] = '--';
									}
								}
							}
							reloadSiteHtml(thiz.sites);
						},
						error : function() {
							for ( var siteId in thiz.sites) {
								var siteMap = thiz.sites[siteId];
								for ( var deviceNO in siteMap.cars) {
									var carPO = siteMap.cars[deviceNO];
									carPO['status'] = '离线';
									carPO['currentCarStatus'] = '';
									carPO['soc'] = '--';
								}
							}
							reloadSiteHtml(thiz.sites);
						}
					});

		};
		loadCarStatus();
		setInterval(loadCarStatus, this.times);
	},
	init : function(sites) {
		var thiz = this;
		this.sites = {};
		// 循环租赁点
		jQuery.each(sites, function(index, site) {
			thiz.sites[site['id']] = thiz.createSitePO({
				siteName : site['siteName'],
				type : site['type'],
				cars : {}
			});
		});
		/*thiz.sites['customerCars'] = thiz.createSitePO({
			siteName : '客户车辆',
			type : '',
			cars : {}
		});*/
		this.loadCarDevice();
	}
};

/**
 * 加载租赁点监控表格
 */
var reloadSiteHtml = function(sites) {
	jQuery('#updates').html('');
	for ( var siteId in sites) {
		var siteMap = sites[siteId];
		var html = '<fieldset style="width: 98%; min-height: 215px; text-align: center; padding-top: 10px; font-size: 14px; font-weight: bold; border: solid 1px #bbb">';
		html += '<legend>' + siteMap['siteName'] + '</legend>';

		for ( var deviceNO in siteMap.cars) {
			var carPO = siteMap.cars[deviceNO];
			html += '<div class="one_half" style="margin-top: 10px;margin-bottom: 10px;">';
			html += '<div class="entry_wrap">';
			html += '<div class="entry_img">';
			html += '<img src="'
					+ contextPath
					+ carPO['imgUrl']
					+ '" onError="imgError(this)" alt="tp" width="100px" height="100px;" />';
			html += '</div>';
			html += '<div class="entry_content" style="text-align: left;margin-left: 120px;">';
			html += '<small>设备编号: <strong>' + carPO['deviceNO']
					+ '</strong></small>';
			html += '<p>车辆状态: <strong>' + carPO['currentCarStatus']
					+ '</strong></p>';
			html += '<p>soc: <strong>' + carPO['soc'] + '</strong></p>';
			if (null!=carPO['curkms']) {
				html += '<p>码表公里数: <strong>' + carPO['curkms'] + '</strong></p>';
			}
			if(null!=carPO['surplusKms']){
				html += '<p>续航里程: <strong>' + carPO['surplusKms'] + '</strong></p>';
			}
			
			html += '<p>';
			if ('正常' == carPO['status']) {
				html += '<strong>'
						+ carPO['status']
						+ '</strong>：<img src="'
						+ contextPath
						+ '/img/SignLight/blue.gif" width="15px;"	height="15px;" complete="complete" />';
			} else {
				html += '<strong>'
						+ carPO['status']
						+ '</strong>：<img src="'
						+ contextPath
						+ '/img/SignLight/wrong.gif" width="15px;"	height="15px;" complete="complete" />';
			}
			html += '</p>';
			html += '</div>';
			html += '</div>';
			html += '<h4><a href="#">' + carPO['carNumber'] + '</a>	</h4>';
			html += '</div>';
		}

		html += '</fieldset>';
		jQuery('#updates').append(html);

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
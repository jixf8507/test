jQuery(document).ready(function() {
	requstSites();

});

var requstSites = function() {
	var paraData = {
		siteId : jQuery('#siteId').val()
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
		devices : {}
	},
	devicePO : {
		deviceNo : '设备名称',
		imgUrl : '设备图片',
		factoryNo : '出厂编号',
		siteId : '所在租赁点编码',
		status : '',
		ports : {}
	},
	portPO : {
		name : '端口名称',
		memo : '查看类型',
		c : '电流',
		v : '电压',
		carNO : '皖WQX8888',
		soc : '0',
		status : '状态',
		info : '',
		chargeTime : ''
	},
	createSitePO : function(tables) {// 创建站点对象
		applyif(tables, this.sitePO);
		return tables;
	},
	createDevicePO : function(tables) {// 创建车辆对象
		applyif(tables, this.carPO);
		return tables;
	},
	createPortPO : function(tables) {// 创建车辆对象
		applyif(tables, this.portPO);
		return tables;
	},
	sites : {},
	loadStakeDevice : function() {
		var thiz = this;
		var paraData = {
			siteId : jQuery('#siteId').val()
		};
		jQuery.ajax({
			type : "POST",
			url : contextPath + '/stake/device/ajaxDevice.htm?t='
					+ new Date().getTime(),
			data : 'paraData=' + encodeURI(JSON.stringify(paraData)),
			async : true,
			dataType : 'json',
			success : function(data) {
				jQuery.each(data, function(index, devicePO) {
					var siteId = devicePO['site_code'];
					var sitePO = thiz.sites[siteId];
					var factoryNo = devicePO['factoryNo'];
					if (sitePO) {
						sitePO.devices[factoryNo] = thiz.createDevicePO({
							deviceNo : devicePO['deviceNo'],
							imgUrl : devicePO['imgUrl'],
							factoryNo : factoryNo,
							siteId : devicePO['site_code'],
							status : '',
							ports : {}
						});
					}

				});
				thiz.loadStakePors();
			},
			error : function() {
			}
		});
	},
	loadStakePors : function() {
		var thiz = this;
		var paraData = {
			siteId : jQuery('#siteId').val()
		};
		jQuery.ajax({
			type : "POST",
			url : contextPath + '/stake/device/ajaxMerchantPorts.htm?t='
					+ new Date().getTime(),
			data : 'paraData=' + encodeURI(JSON.stringify(paraData)),
			async : true,
			dataType : 'json',
			success : function(data) {
				jQuery.each(data, function(index, portPO) {
					var siteId = portPO['site_code'];
					var factoryNo = portPO['factoryNo'];
					var name = portPO['name'];
					var sitePO = thiz.sites[siteId];
					if (sitePO) {
						var devicePO = sitePO.devices[factoryNo];
						devicePO.ports[name] = thiz.createPortPO({
							name : portPO['name'],
							memo : portPO['memo'],
							c : '0',
							v : '0',
							status : '',
							carNO : '',
							soc : '',
							info : ''
						});
					}
				});
				thiz.refresh();
			},
			error : function() {
			}
		});
	},
	refresh : function() {
		var thiz = this;
		var loadStakeDeviceStatus = function() {
			jQuery.ajax({
				type : "POST",
				url : contextPath
						+ '/monitor/main/ajaxStakeDeviceStatus.htm?t='
						+ new Date().getTime(),
				data : '',
				async : true,
				dataType : 'json',
				success : function(data) {
					thiz.updateSites(data);
				},
				error : function() {
					thiz.clearSites();
				}
			});
		};
		// reloadSiteHtml(thiz.sites);
		loadStakeDeviceStatus();
		setInterval(loadStakeDeviceStatus, this.times);
	},
	updateSites : function(data) {// 修改数据
		for ( var siteId in this.sites) {
			var sitePO = this.sites[siteId];
			this.updateSite(sitePO, data);
		}
		reloadSiteHtml(this.sites);
	},
	updateSite : function(sitePO, dm) {// 修改充电站内容
		for ( var deviceNO in sitePO.devices) {
			var devicePO = sitePO.devices[deviceNO];
			var deviceStatus = dm[deviceNO];
			if (deviceStatus) {
				this.updateDevice(devicePO, deviceStatus);
			} else {
				this.clearDevice(devicePO);
			}
		}
	},
	updateDevice : function(devicePO, dm) {// 修改充电设备状态
		devicePO['status'] = dm['status'];
		for ( var portName in devicePO.ports) {
			var portPO = devicePO.ports[portName];
			var curportPO = dm['port'][portName];
			if (dm['port'] && curportPO) {
				this.updatePort(portPO, curportPO);
			} else {
				this.clearPort(portPO);
			}

		}
	},
	updatePort : function(portPO, dm) {// 修改端口的内容
		portPO.status = dm['status'];
		portPO.c = dm['a'];
		portPO.v = dm['v'];
		portPO.carNO = dm['carNO'];
		portPO.soc = dm['soc'] == '' ? '--' : dm['soc'];
		portPO.info = dm['info'];
		if (dm['chargeTime']) {
			portPO.chargeTime = dm['chargeTime'] == '' ? '--'
					: dm['chargeTime'];
		} else {
			portPO.chargeTime = '--';
		}
	},
	clearSites : function() {
		for ( var siteId in this.sites) {
			this.clearSite(this.sites[siteId]);
		}
		reloadSiteHtml(this.sites);
	},
	clearSite : function(sitePO) {// 清空租赁点的状态
		for ( var deviceNO in sitePO.devices) {
			this.clearDevice(siteMap.devices[deviceNO]);
		}
	},
	clearDevice : function(devicePO) { // 清空充电设备状态
		devicePO['status'] = '';
		for ( var portName in devicePO.ports) {
			this.clearPort(devicePO.ports[portName]);
		}
	},
	clearPort : function(portPO) { // 清空设备端口状态
		portPO.status = '';
		portPO.c = '--';
		portPO.v = '--';
		portPO.carNO = '--';
		portPO.soc = '--';
		portPO.info = '';
		portPO.chargeTime = '--';
	},
	init : function(sites) {
		var thiz = this;
		this.sites = {};
		// 循环租赁点
		jQuery.each(sites, function(index, site) {
			thiz.sites[site['id']] = thiz.createSitePO({
				siteName : site['siteName'],
				type : site['type'],
				devices : {}
			});
		});
		this.loadStakeDevice();
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

		for ( var deviceNO in siteMap.devices) {
			var devicePO = siteMap.devices[deviceNO];
			html += '<div class="one_half" style="margin-top: 10px;margin-bottom: 10px;">';
			html += '<div class="entry_wrap" >';
			html += '<div class="entry_img" style="margin-left: 30px;">';
			html += '<img src="'
					+ contextPath
					+ devicePO['imgUrl']
					+ '" onError="imgError(this)" alt="tp" width="45px" height="150px;" onclick="openWin('
					+ siteId + ',\'' + deviceNO + '\')" />';
			html += '</div>';
			html += '<div class="entry_content" style="text-align: left;margin-left: 120px;">';
			html += '<small>设备编号: <strong>' + devicePO['factoryNo']
					+ '</strong></small>';
			html += '<p >';
			html += '<table style="text-align: center; font-size: 12px; width: 250px;">';
			html += '<tr><td>端口</td><td>型号</td><td>状态</td><td>电流</td><td>电压</td></tr>';
			
			flag = 0;
			
			for ( var portName in devicePO.ports) {
				flag = 1;
				var portPO = devicePO.ports[portName];
				html += '<tr>';
				html += '<td>' + portPO['name'] + '</td>';
				html += '<td>' + portPO['memo'] + '</td>';
				if (portPO.status == 'LostConnect') {
					html += '<td><img src="'
							+ contextPath
							+ '/img/SignLight/wrong.gif" width="15px;" height="15px;" complete="complete" /></td>';
				} else if (portPO.status == 'Standby') {
					html += '<td><img src="'
							+ contextPath
							+ '/img/SignLight/green.gif" width="20px;"	height="20px;" complete="complete" /></td>';
				} else if (portPO.status == 'UserUsing') {
					html += '<td><img src="'
							+ contextPath
							+ '/img/SignLight/blue.gif" width="20px;"	height="20px;" complete="complete" /></td>';
				} else if (portPO.status == 'Charging'
						|| portPO.status == 'Parking') {
					html += '<td><img src="'
							+ contextPath
							+ '/img/SignLight/red.gif" width="20px;"	height="20px;" complete="complete" /></td>';
				} else if (portPO.status == 'Fault') {
					html += '<td><img src="'
							+ contextPath
							+ '/img/SignLight/yellow.gif" width="20px;"	height="20px;" complete="complete" /></td>';
				} else {
					html += '<td><img src="'
							+ contextPath
							+ '/img/SignLight/wrong.gif" width="15px;" height="15px;" complete="complete" /></td>';
				}
				html += '<td>' + portPO['c'] + 'A</td>';
				html += '<td>' + portPO['v'] + 'V</td>';
				html += '</tr>';
			}
			if (flag == 0) {
				html += '<tr>';
				html += '<td>&nbsp;</td>';
				html += '<td>&nbsp;</td>';
				html += '<td>&nbsp;</td>';
				html += '<td>&nbsp;</td>';
				html += '</tr>';
				html += '<tr>';
				html += '<td>&nbsp;</td>';
				html += '<td>&nbsp;</td>';
				html += '<td>&nbsp;</td>';
				html += '<td>&nbsp;</td>';
				html += '</tr>';
				html += '<tr>';
				html += '<td>&nbsp;</td>';
				html += '<td>&nbsp;</td>';
				html += '<td>&nbsp;</td>';
				html += '<td>&nbsp;</td>';
				html += '</tr>';
				html += '<tr>';
				html += '<td>&nbsp;</td>';
				html += '<td>&nbsp;</td>';
				html += '<td>&nbsp;</td>';
				html += '<td>&nbsp;</td>';
				html += '</tr>';
				
			}
			html += '</table>';
			html += '</p>';
			html += '</div>';
			html += '<br clear="all" />';
			html += '<h4><a href="javaScript:openWin(' + siteId + ',\''
					+ deviceNO + '\')">' + devicePO['deviceNo']
					+ '</a>&nbsp;&nbsp;';
			if (devicePO.status == 'LostConnect') {
				html += '<img src="'
						+ contextPath
						+ '/img/SignLight/wrong.gif" width="20px;"	height="20px;" complete="complete" />';
			} else if (devicePO.status == 'Standby') {
				html += '<img src="'
						+ contextPath
						+ '/img/SignLight/green.gif" width="20px;"	height="20px;" complete="complete" />';
			} else if (devicePO.status == 'UserUsing') {
				html += '<img src="'
						+ contextPath
						+ '/img/SignLight/blue.gif" width="20px;"	height="20px;" complete="complete" />';
			} else if (devicePO.status == 'Charging'
					|| devicePO.status == 'Parking') {
				html += '<img src="'
						+ contextPath
						+ '/img/SignLight/red.gif" width="20px;"	height="20px;" complete="complete" />';
			} else if (devicePO.status == 'Fault') {
				html += '<img src="'
						+ contextPath
						+ '/img/SignLight/yellow.gif" width="20px;"	height="20px;" complete="complete" />';
			} else {
				html += '<img src="'
						+ contextPath
						+ '/img/SignLight/wrong.gif" width="20px;"	height="20px;" complete="complete" />';
			}
			html += '</h4>';
			html += '</div>';
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
	thiz.src = contextPath + '/img/qxjl.png';
}

var openWin = function(siteId, deviceNO) {
	var devicePO = monitorObj.sites[siteId]['devices'][deviceNO];
	var html = '';
	for ( var portName in devicePO.ports) {
		var portPO = devicePO.ports[portName];
		html += '<div class="one_half" style="width: 47%; min-width:300px;">';
		html += '<div class="widgetbox" style="text-align: left;">';
		html += '<div class="widgetcontent userlistwidget nopadding" >';
		html += '<a class="more" href="#" style="text-align: left;padding-left: 10px;">'
				+ portName + '&nbsp;&nbsp;';
		if (portPO.status == 'LostConnect') {
			html += '<img src="'
					+ contextPath
					+ '/img/SignLight/wrong.gif" width="15px;"	height="15px;" complete="complete" />';
		} else if (portPO.status == 'Standby') {
			html += '<img src="'
					+ contextPath
					+ '/img/SignLight/green.gif" width="20px;"	height="20px;" complete="complete" />';
		} else if (portPO.status == 'UserUsing') {
			html += '<img src="'
					+ contextPath
					+ '/img/SignLight/blue.gif" width="20px;"	height="20px;" complete="complete" />';
		} else if (portPO.status == 'Charging' || portPO.status == 'Parking') {
			html += '<img src="'
					+ contextPath
					+ '/img/SignLight/red.gif" width="20px;"	height="20px;" complete="complete" />';
		} else if (portPO.status == 'Fault') {
			html += '<img src="'
					+ contextPath
					+ '/img/SignLight/yellow.gif" width="20px;"	height="20px;" complete="complete" />';
		} else {
			html += '<img src="'
					+ contextPath
					+ '/img/SignLight/wrong.gif" width="15px;"	height="15px;" complete="complete" />';
		}
		html += '</a>';

		html += '<div class="one_half" style="width: 76px;">';
		html += '<img src="'
				+ contextPath
				+ '/img/car.png" onError="imgCarError(this)" alt="tp" width="75px" height="75px;" />';
		html += '</div>';

		html += '<div class="one_half" style="width: 50%;">';
		html += '<ul>';
		html += '<li>';
		html += '<div class="info c_title">';
		html += '<span>类型 :</span>' + portPO['memo'];
		html += '</div>';
		html += '</li>';
		html += '<li>';
		html += '<div class="info c_title">';
		html += '<span>车牌号码 :</span>' + portPO['carNO'];
		html += '</div>';
		html += '</li>';
		html += '</ul>';
		html += '</div>';

		html += '<div class="one_half" style="width: 40%;">';
		html += '<ul>';
		html += '<li>';
		html += '<div class="info c_wait">';
		html += '<span>电流:</span>' + portPO['c'] + 'A';
		html += '</div>';
		html += '</li>';
		html += '<li>';
		html += '<div class="info c_apply">';
		html += '<span>电压:</span>' + portPO['v'] + 'V';
		html += '</div>';
		html += '</li>';
		html += '</ul>';
		html += '</div>';

		html += '<div class="one_half" style="width: 50%;">';
		html += '<ul>';
		html += '<li>';
		html += '<div class="info c_wait">';
		html += '<span>剩余电量:</span>' + portPO['soc'];
		html += '%</div>';
		html += '</li>';
		html += '<li>';
		html += '<div class="info c_apply">';
		html += '<span>剩余充电时间:</span>' + portPO['chargeTime'];
		html += '分钟</div>';
		html += '</li>';
		html += '</ul>';
		html += '</div>';

		html += '</div>';
		html += '</div>';
		html += '</div>';
	}
	// diag.InnerHtml = '<div id="scroll1"
	// style="height:200px;">'+html+'</div>';
	var diag = new Dialog();
	diag.Width = 700;
	diag.Height = 450;
	diag.URL = contextPath + "/page/monitor/stakeMonitorPortWin.jsp?siteId="
			+ siteId;
	diag.Title = "充电详情";
	diag.MessageTitle = devicePO['deviceNo'] + '&nbsp;&nbsp;&nbsp;&nbsp;('
			+ devicePO['factoryNo'] + ')';
	diag.OnLoad = function() {
		// jQuery('#scroll1').html(html);
		diag.innerFrame.contentWindow.jQuery('#scroll1').html(html);
	};
	diag.show();
};

function imgCarError(thiz) {
	thiz.src = contextPath + '/img/car.png';
}

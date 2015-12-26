jQuery(document).ready(function() {
	jQuery("#beginTime,#endTime").datepicker();
	
	sitesCombox.requestData();

	jQuery('#queryBtn').click(function() {
		load();
	});

	load();
});

var load = function() {
	// 商家月充电收费统计
	monthCostCharts();

	// 商家日充电收费统计
	dayCostCharts();

	// 商家租赁点充电收费统计
	siteCostCharts();

	// 商家租赁点充电次数统计
	siteCountCharts();
};

//选择框
var sitesCombox = new Combox({
	id : 'siteId',
	url : contextPath + '/merchant/site/ajaxSites.htm?t='
			+ new Date().getTime(),
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择',
	change : function() {
		load();
	}
});

var getParam = function() {
	var paraData = {
		"beginTime" : jQuery('#beginTime').val(),
		"endTime" : jQuery('#endTime').val(),
		"siteId" : jQuery('#siteId').val()
	};
	return paraData;
};

// 商家月充电收费统计
var monthCostCharts = function() {
	var paraData = getParam();
	jQuery.ajax({
		type : "POST",
		url : contextPath + "/statistics/stake/ajaxMonthCost.htm?",
		data : 'paraData=' + encodeURI(JSON.stringify(paraData)),
		async : true,
		dataType : 'json',
		success : function(data) {
			drawMonthCost(data);
		},
		error : function() {

		}
	});
};

// 商家日充电收费统计
var dayCostCharts = function() {
	var paraData = getParam();
	jQuery.ajax({
		type : "POST",
		url : contextPath + "/statistics/stake/ajaxDayCost.htm?",
		data : 'paraData=' + encodeURI(JSON.stringify(paraData)),
		async : true,
		dataType : 'json',
		success : function(data) {
			drawDayCost(data);
		},
		error : function() {

		}
	});
};

// 商家租赁点充电收费统计
var siteCostCharts = function() {
	var paraData = getParam();
	jQuery.ajax({
		type : "POST",
		url : contextPath + "/statistics/stake/ajaxSiteCost.htm?",
		data : 'paraData=' + encodeURI(JSON.stringify(paraData)),
		async : true,
		dataType : 'json',
		success : function(data) {
			drawSiteCost(data);
		},
		error : function() {

		}
	});
};

// 商家租赁点充电次数统计
var siteCountCharts = function() {
	var paraData = getParam();
	jQuery.ajax({
		type : "POST",
		url : contextPath + "/statistics/stake/ajaxSiteCount.htm?",
		data : 'paraData=' + encodeURI(JSON.stringify(paraData)),
		async : true,
		dataType : 'json',
		success : function(data) {
			drawSiteCount(data);
		},
		error : function() {

		}
	});
};

// 生成每月收益统计图表
var drawMonthCost = function(data) {
	jQuery('#month').highcharts({
		chart : {
			zoomType : 'x'
		},
		title : {
			text : ''
		},
		subtitle : {
			text : ''
		},
		xAxis : data['xAxis'],
		yAxis : [ {
			allowDecimals : false,
			title : {
				text : '充电收费 （元）',
				style : {
					color : '#4572A7'
				}
			},
			labels : {
				format : '{value} 元',
				style : {
					color : '#4572A7'
				}
			},
			min : 0
		} ,{
			title : {
				text : '充电次数 （次）',
				style : {
					color : '#89A54E'
				}
			},
			labels : {
				format : '{value} 次',
				style : {
					color : '#89A54E'
				}
			},
			opposite : true,
			min : 0
		}],
		legend : {
			enabled : true
		},
		series : data['series']
	});
};

// 生成每日收益统计图表
var drawDayCost = function(data) {
	jQuery('#day').highcharts({
		chart : {
			zoomType : 'x'
		},
		title : {
			text : ''
		},
		subtitle : {
			text : ''
		},
		xAxis : {
			type : 'datetime',
			minRange : 14 * 24 * 3600000
		},
		yAxis : [ { // Primary yAxis
			allowDecimals : false,
			title : {
				text : '充电收费 （元）',
				style : {
					color : '#4572A7'
				}
			},
			labels : {
				format : '{value} 元',
				style : {
					color : '#4572A7'
				}
			},
			min : 0
		}, { // Secondary yAxis
			title : {
				text : '充电次数（次）',
				style : {
					color : '#89A54E'
				}
			},
			labels : {
				format : '{value} 次',
				style : {
					color : '#89A54E'
				}
			},
			opposite : true,
			min : 0
		} ],
		legend : {
			enabled : true
		},
		series : data['series']
	});
};

// 生成每日收益统计图表
var drawSiteCost = function(data) {
	new Highcharts.Chart({
		chart : {
			events : {
				load : function(event) {
					var dataList = this.series[0].data;
					var totle = 0;
					for (var i = 0; i < dataList.length; i++) {
						totle += dataList[i].y;
					}
					this.setTitle({
						text : '充电站收费柱状图(' + totle + '元)'
					});
				}
			},
			renderTo : 'siteCost',
			type : 'column',
			margin : 75,
			marginLeft : 90,
			marginRight : 10,
			options3d : {
				enabled : true,
				alpha : 0,
				beta : 0,
				depth : 90,
				viewDistance : 6
			}
		},
		title : {
			text : '充电站收费柱状图'
		},
		subtitle : {
			text : ''
		},
		xAxis : {
			categories : data['xAxis']['categories'],
			labels : {
				rotation : 45,
				color : '#000',
				font : '11px Trebuchet MS, Verdana, sans-serif'
			}
		},
		yAxis : [ {
			title : {
				text : '',
				style : {
					color : "#ff7e00"
				}
			},
			labels : {
				format : '{value} 元',
				style : {
					color : "#ff7e00"
				}
			}

		} ],
		plotOptions : {
			column : {
				depth : 25,
				color : "#ff7e00"
			},
			series : {
				borderWidth : 0,
				dataLabels : {
					enabled : true,
					format : '{point.y}'
				}
			}
		},
		series : data['series']
	});
};

// 生成租赁点充电次数统计图表
var drawSiteCount = function(data) {
	jQuery('#site')
			.highcharts(
					{
						chart : {
							events : {
								load : function(event) {
									var dataList = this.series[0].data;
									var totle = 0;
									for (var i = 0; i < dataList.length; i++) {
										totle += dataList[i].y;
									}
									this.setTitle({
										text : '充电站充电次数比例图(' + totle + '次)'
									});
								}
							},
							type : 'pie',
							margin : 75,
							options3d : {
								enabled : true,
								alpha : 35,
								beta : 0
							}
						},
						title : {
							text : '充电站充电次数比例图'
						},
						subtitle : {
							text : ''
						},
						tooltip : {
							pointFormat : '{series.name}: <b>{point.percentage:.1f}% </b>'
						},
						plotOptions : {
							pie : {
								allowPointSelect : true,
								cursor : 'pointer',
								showInLegend : true,
								depth : 25,
								dataLabels : {
									enabled : true,
									format : '{point.name}:{point.percentage:.1f} % ({point.y}次)'
								}
							}
						},
						series : data['series']
					});
};

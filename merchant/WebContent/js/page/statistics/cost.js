jQuery(document).ready(function() {
	jQuery("#beginTime,#endTime").datepicker();
	// 点击查询按钮事件
	jQuery('#queryBtn').click(function() {
		load();
	});
	
	load();
});



var load = function (){
	// 商家每月租赁收费统计
	monthCostCharts();

	// 商家租赁点租赁收费统计
	siteCostCharts();

	// 商家租赁点租赁次数统计
	// siteCountCharts();

	timeLine();

	// 商家每日租赁收费统计
	dayCostCharts();
} ;

var getParam = function() {
	var paraData = {
		"beginTime" : jQuery('#beginTime').val(),
		"endTime" : jQuery('#endTime').val() == '' ? ''
				: jQuery('#endTime').val() + ' 23:59:59'
	};
	return paraData;
};

// 商家每月租赁收费统计
var monthCostCharts = function() {
	var paraData = getParam();
	jQuery.ajax({
		type : "POST",
		url : contextPath + "/statistics/cost/ajaxMonthCost.htm?",
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
	
// 商家租赁点租赁收费统计
var siteCostCharts = function() {
	var paraData = getParam();
	jQuery.ajax({
		type : "POST",
		url : contextPath + "/statistics/cost/ajaxSiteCost.htm?",
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

// 商家租赁点租赁次数统计
var siteCountCharts = function() {
	var paraData = getParam();
	jQuery.ajax({
		type : "POST",
		url : contextPath + "/statistics/cost/ajaxSiteCount.htm?",
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

// 请求每日收费统计数据
var dayCostCharts = function() {
	var paraData = getParam();
	jQuery.ajax({
		type : "POST",
		url : contextPath + "/statistics/cost/ajaxDayCost.htm?",
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

// 生成每月收益统计图表
var drawMonthCost = function(data) {
	new Highcharts.Chart({
		chart : {
			renderTo : 'month',
			type : 'spline'
		},
		title : {
			text : ''
		},
		subtitle : {
			text : ''
		},
		xAxis : data['xAxis'],
		yAxis : [ { // Secondary yAxis
			title : {
				text : '',
				style : {
					color : '#0096ff'
				}
			},
			labels : {
				format : '{value}(元)',
				style : {
					color : '#0096ff'
				}
			},
			min : 0
		} ],
		plotOptions : {
			column : {
				depth : 25,
				color : '#0096ff'
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
				text : '租赁收费 (元)',
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
				text : '租赁次数(次)',
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
		plotOptions : {
			column : {
				depth : 10,
				color : "#ff7e00"
			},
			series : {
				borderWidth : 0,
				marker : {
					enabled : true,
					radius : 2, // 曲线点半径，默认是4
					symbol : 'circle' // 曲线点类型："circle", "square", "diamond",
				// "triangle","triangle-down"，默认是"circle"
				}
			}
		},
		series : data['series']
	});
};

// 生成每日收益统计图表
var drawSiteCost = function(data) {
	console.log(data);
	new Highcharts.Chart({
		chart : {
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
			text : ''
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
		yAxis : [ { // Secondary yAxis
			title : {
				text : '',
				style : {
					color : "#ff7e00"
				}
			},
			labels : {
				format : '{value}(元)',
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

// 生成每日收益统计图表
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
										text : '租赁点取车次数比例图(' + totle + '次)'
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
							text : '租赁点次数统计比例图'
						},
						subtitle : {
							text : ''
						},
						tooltip : {
							pointFormat : '{series.name}: <b>{point.percentage:.1f}% </b>'
						},
						plotOptions : {
							pie : {
								// size: 300,
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

// 请求每日收费统计数据
var timeLine = function() {
	var paraData = getParam();
	jQuery.ajax({
		type : "POST",
		url : contextPath + "/statistics/site/ajaxSiteHourCount.htm?",
		data : 'paraData=' + encodeURI(JSON.stringify(paraData)),
		async : true,
		dataType : 'json',
		success : function(data) {
			drawLineCost(data, 'site', '#0096ff');
		},
		error : function() {

		}
	});
};

// 生成线
var drawLineCost = function(data, chartsId, color) {
	jQuery('#' + chartsId).highcharts({
		chart : {
			zoomType : 'x'
		},
		colors : [   '#ED561B','#50B432', '#DDDF00', '#24CBE5',
					'#64E572', '#FF9655', '#FFF263', '#6AF9C4' ],
		title : {
			text : ''
		},
		subtitle : {
			text : ''
		},
		xAxis : data['xAxis'],
		yAxis : {
			allowDecimals : false,
			title : {
				text : '次数 (次)',
				style : {
					color : color
				}
			},
			labels : {
				format : '{value} 次',
				style : {
					color : color
				}
			},
			min : 0
		},
		legend : {
			enabled : true
		},
//		plotOptions : {
//			column : {
//				depth : 25,
//				color : '#0096ff'
//			},
//			series : {
//				borderWidth : 0,
//				dataLabels : {
//					enabled : true,
//					format : '{point.y}'
//				}
//			}
//		},
		series : data['series']
	});
};

jQuery(document).ready(function() {
	// 租赁次数分时段统计
	
	//柱状图
	siteTimeColumnCharts();
	//饼状图
	siteTimePieCharts();
	
	sitesCombox.requestData();
});

//选择框
var sitesCombox = new Combox({ 
	id : 'siteId',
	url : contextPath + '/merchant/site/ajaxSites.htm?t='
			+ new Date().getTime(),
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择租赁点',
	change : function() {
		siteTimePieCharts(); 
		siteTimeColumnCharts();
	}
});


//租赁次数分时段统计饼状图
var siteTimePieCharts = function() {
	var paraData = {
			"siteId" : jQuery('#siteId').val() == '' ? '' : jQuery('#siteId').val()
	};
	jQuery.ajax({
		type : "POST",
		url : contextPath + "/statistics/time/ajaxSiteTimeCountPie.htm?",
		data : 'paraData=' + encodeURI(JSON.stringify(paraData)),
		async : true,
		dataType : 'json',
		success : function(data) {
			drawPieCharts(data);
		},
		error : function() {

		}
	});
};

//租赁次数分时段统计柱状图
var siteTimeColumnCharts = function() {
	var paraData = {
			"siteId" : jQuery('#siteId').val() ? jQuery('#siteId').val() : ''
	};
	jQuery.ajax({
		type : "POST",
		url : contextPath + "/statistics/time/ajaxSiteTimeCountColumn.htm?",
		data : 'paraData=' + encodeURI(JSON.stringify(paraData)),
		async : true,
		dataType : 'json',
		success : function(data) {
			drawColumnCost(data);
		},
		error : function() {

		}
	});
};

//生成租赁次数分时段统计饼状图
var drawPieCharts = function(data) {
	jQuery('#pieChart')
			.highcharts(
					{
						chart : {
							type : 'pie',
							margin : 75,
							options3d : {
								enabled : true,
								alpha : 35,
								beta : 0
							}
						},
						title : {
							text : ''
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

//生成租赁次数分时段统计柱状图
var drawColumnCost = function(data) {
	new Highcharts.Chart({
		chart : {
			renderTo : 'columnChart',
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
				format : '{value}(次)',
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
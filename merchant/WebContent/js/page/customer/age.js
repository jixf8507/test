jQuery(document).ready(function() {
//	//加载商家列表
//	useSitesCombox.requestData();
	
	
	//用户人数统计柱状图
	customerCountColumn();
	//用户人数统计饼状图
	customerCountPie();
	
});

////选择框
//var useSitesCombox = new Combox({
//	id : 'merchantId',
//	url : contextPath + '/merchant/manager/ajaxGetMerchants.htm?t='
//			+ new Date().getTime(),
//	cText : 'merchantName',
//	cValue : 'id',
//	emptyText : '请选择商户',
//	change : function() {
//		siteTimePieCharts(); 
//		siteTimeColumnCharts();
//	}
//});


//用户年龄段统计柱状图。
var customerCountColumn = function(){
	var paraData = {};
	jQuery.ajax({
		type : "POST",
		url : contextPath + "/customer/manager/ajaxCustomerCountColumn.htm?",
		data : 'paraData=' + encodeURI(JSON.stringify(paraData)),
		async : true,
		dataType : 'json',
		success : function(data) {
			drawColumnCost2(data);
		},
		error : function() {
			
		}
	});
};	
	
//用户年龄段统计饼状图。
var customerCountPie = function(){
	var paraData = {};
	jQuery.ajax({
		type : "POST",
		url : contextPath + "/customer/manager/ajaxCustomerCountPie.htm?",
		data : 'paraData=' + encodeURI(JSON.stringify(paraData)),
		async : true,
		dataType : 'json',
		success : function(data) {
			drawPieCharts2(data);
		},
		error : function() {
			
		}
	});	
};


//生成用户年龄分时段统计柱状图
var drawColumnCost2 = function(data) {
	jQuery('#customerColumnChart').highcharts({
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
				format : '{value}(人)',
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


//生成用户年龄时段统计饼状图
var drawPieCharts2 = function(data) {
	jQuery('#customerPieChart')
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
									format : '{point.name}:{point.percentage:.1f} % ({point.y}人)'
								}
							}
						},
						series : data['series']
					});
};








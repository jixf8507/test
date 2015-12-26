jQuery(document).ready(function(){
	jQuery("#beginTime,#endTime").datepicker();
	// 点击查询按钮事件
	jQuery('#queryBtn').click(function() {
		load();
	});
	
	
	load();
});



//原统计报表模块中图表整合
var load = function (){
//租赁点统计分析：租赁点取车次数比例图，租赁点还车次数比例图
	//取车
	getColumnCharts();
	//还车
	returnColumnCharts();
	
//租赁点时间统计：租赁点次数分时段统计柱状图，租赁点次数分时段统计饼状图。
	//柱状图
	siteTimeColumnCharts();
	//饼状图
	siteTimePieCharts();
	
	
//租赁收费统计：日收益统计图表（去掉每日收益的统计）
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




//租赁点取车次数统计
var getColumnCharts = function() {
	var paraData = {
			"siteName" : jQuery('#querySiteName').val(),
			"beginTime" : jQuery('#beginTime').val(),
			"endTime" : jQuery('#endTime').val() == '' ? ''
					: jQuery('#endTime').val() + ' 23:59:59'
	};
	jQuery.ajax({
		type : "POST",
		url : contextPath + "/statistics/site/ajaxSiteGetCountColumn.htm?",
		data : 'paraData=' + encodeURI(JSON.stringify(paraData)),
		async : true,
		dataType : 'json',
		success : function(data) {
			drawColumnGet(data);
		},
		error : function() {

		}
	});
};

//租赁点还车次数统计
var returnColumnCharts = function() {
	var paraData = {
			"siteName" : jQuery('#querySiteName').val(),
			"beginTime" : jQuery('#beginTime').val(),
			"endTime" : jQuery('#endTime').val() == '' ? ''
					: jQuery('#endTime').val() + ' 23:59:59'
	};
	jQuery.ajax({
		type : "POST",
		url : contextPath + "/statistics/site/ajaxSiteReturnCountColumn.htm?",
		data : 'paraData=' + encodeURI(JSON.stringify(paraData)),
		async : true,
		dataType : 'json',
		success : function(data) {
			drawColumnReturn(data);
		},
		error : function() {
			
		}
	});
};




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

//请求每日收费统计数据
var dayCostCharts = function() {
	var paraData = {
			"beginTime" : '',
			"endTime" : ''
	};
	jQuery.ajax({
		type : "POST",
		url : contextPath + "/statistics/cost/ajaxDayCost.htm?",
		data : 'paraData=' + encodeURI(JSON.stringify(paraData)),
		async : true,
		dataType : 'json',
		success : function(data) {
			//移除费用统计的那部分的数据，只统计取车和还车的那部分数据。
			data.series.splice(0,1);
			drawDayCost(data);
		},
		error : function() {

		}
	});
};



//生成租赁点取车次数统计
var drawColumnGet = function(data) {
		jQuery('#getChart')
				.highcharts(
						{
							chart : {
								 events: { 
							        	load: function(event) { 
							        		var dataList= this.series[0].data; 
							        		var totle = 0 ;
							        		for(var i=0;i<dataList.length;i++) { 
							        			totle += dataList[i].y;
							        		}      
							        		this.setTitle({text:'租赁点取车次数比例图(总次数：'+totle+')'});      	                
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
								text : '租赁点取车次数统计比例图'
							},
							subtitle : {
								text : ''
							},
							tooltip : {
								pointFormat : '{series.name}: <b>{point.percentage:.1f}% ({point.y}次)</b>'
							},
							plotOptions : {
								pie : {
									// size: 300,
									allowPointSelect : true,
									cursor : 'pointer',
									showInLegend : false,
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


//生成租赁点还车次数统计
var drawColumnReturn = function(data) {
	jQuery('#returnChart')
	.highcharts(
			{
				chart : {
					 events: { 
				        	load: function(event) { 
				        		var dataList= this.series[0].data; 
				        		var totle = 0;
				        		for(var i=0;i<dataList.length;i++) { 
				        			totle += dataList[i].y;
				        		}      
				        		this.setTitle({text:'租赁点还车次数比例图(总次数：'+totle+')'});      	                
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
					text : '租赁点还车次数统计比例图'
				},
				subtitle : {
					text : ''
				},
				tooltip : {
					pointFormat : '{series.name}: <b>{point.percentage:.1f}% ({point.y}次) </b>'
				},
				plotOptions : {
					pie : {
						// size: 300,
						allowPointSelect : true,
						cursor : 'pointer',
						showInLegend : false,
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


//生成每日收益统计图表
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
			//allowDecimals : false,
			title : {
				text : ""/*'租赁收费 (元)'*/,
				style : {
					/*color : '#4572A7'*/
				}
			},
			labels : {
				format : "" /*'{value} 元'*/,
				style : {
					/*color : '#4572A7'*/
				}
			},
			opposite : true,
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
			//opposite : true,
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






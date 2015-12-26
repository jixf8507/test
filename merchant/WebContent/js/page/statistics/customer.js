jQuery(document).ready(function() {
	
	jQuery("#beginTime,#endTime").datepicker();
	
	
	// 点击查询按钮事件
	jQuery('#queryBtn').click(function() {
		banCostCharts();
		cancleCostCharts();
		normalCostCharts();
		frozenCostCharts();
		//dayGetCostCharts();
		countCustomerDay();
	});
	
	
	
	// 每个租赁点办卡数
	banCostCharts();
	
	//每个租赁点卡的注销数量
	cancleCostCharts();
	
	//每个租赁点正常卡的数量
	normalCostCharts();
	
	//每个租赁点冻结卡的数量
	frozenCostCharts();
	
	// 租赁点每日办卡数
	//dayGetCostCharts();
	
	//商家开户销户统计数据
	countCustomerDay();
});

//租赁点冻结卡数量统计
var frozenCostCharts= function() {
	var paraData = {
			"beginTime" : jQuery('#beginTime').val(),
			"endTime" : jQuery('#endTime').val() == '' ? ''
					: jQuery('#endTime').val() + ' 23:59:59',
			"status":"冻结"
	};
	jQuery.ajax({
		type : "POST",
		url : contextPath + "/statistics/customer/ajaxCustomerCountByCancle.htm?",
		data : 'paraData=' + encodeURI(JSON.stringify(paraData)),
		async : true,
		dataType : 'json',
		success : function(data) {
			frozenSiteCount(data);
		},
		error : function() {

		}
	});
};


//租赁点正常卡数量统计
var normalCostCharts= function() {
	var paraData = {
			"beginTime" : jQuery('#beginTime').val(),
			"endTime" : jQuery('#endTime').val() == '' ? ''
					: jQuery('#endTime').val() + ' 23:59:59',
			"status":"正常"
	};
	jQuery.ajax({
		type : "POST",
		url : contextPath + "/statistics/customer/ajaxCustomerCount.htm?",
		data : 'paraData=' + encodeURI(JSON.stringify(paraData)),
		async : true,
		dataType : 'json',
		success : function(data) {
			normalSiteCount(data);
		},
		error : function() {

		}
	});
};






// 租赁点注销办卡数量统计
var cancleCostCharts= function() {
	var paraData = {
			"beginTime" : jQuery('#beginTime').val(),
			"endTime" : jQuery('#endTime').val() == '' ? ''
					: jQuery('#endTime').val() + ' 23:59:59',
			"status":"注销"
	};
	jQuery.ajax({
		type : "POST",
		url : contextPath + "/statistics/customer/ajaxCustomerCountByCancle.htm?",
		data : 'paraData=' + encodeURI(JSON.stringify(paraData)),
		async : true,
		dataType : 'json',
		success : function(data) {
			cancleSiteCount(data);
		},
		error : function() {

		}
	});
};


//租赁点办卡的数量统计
var banCostCharts =function(){
	var paraData = {
			"beginTime" : jQuery('#beginTime').val(),
			"endTime" : jQuery('#endTime').val() == '' ? ''
					: jQuery('#endTime').val() + ' 23:59:59'
	};
	jQuery.ajax({
		type : "POST",
		url : contextPath + "/statistics/customer/ajaxCustomerCount.htm?",
		data : 'paraData=' + encodeURI(JSON.stringify(paraData)),
		async : true,
		dataType : 'json',
		success : function(data) {
			banSiteCount(data);

		},
		error : function() {

		}
	});
	
};

// 租赁点每日办卡数据
var dayGetCostCharts = function() {
	var paraData = {
			/*"beginTime" : jQuery('#beginTime').val(),
			"endTime" : jQuery('#endTime').val() == '' ? ''
					: jQuery('#endTime').val() + ' 23:59:59'*/
	};
	jQuery.ajax({
		type : "POST",
		url : contextPath + "/statistics/customer/ajaxDayCount.htm?",
		data : 'paraData=' + encodeURI(JSON.stringify(paraData)),
		async : true,
		dataType : 'json',
		success : function(data) {
			dayGetCost(data);
		},
		error : function() {

		}
	});
};

//商家开户销户统计数据
var countCustomerDay=function(){
	var paraData = {
			"beginTime" : jQuery('#beginTime').val(),
			"endTime" : jQuery('#endTime').val() == '' ? ''
					: jQuery('#endTime').val() + ' 23:59:59'
	};
	jQuery.ajax({
		type : "POST",
		url : contextPath + "/customer/accountRecord/countCustomerDay.htm?",
		data : 'paraData=' + encodeURI(JSON.stringify(paraData)),
		async : true,
		dataType : 'json',
		success : function(data) {
			drawDayCount(data);
		},
		error : function() {

		}
	});
};

//生成各冻结卡数量比例图
var frozenSiteCount = function(data) {
	jQuery('#site_frozen')
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
										text : '各租赁点卡冻结比例图(总次数：'+totle+')'
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
							text : '各租赁点卡冻结比例图'
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
								showInLegend : false,
								dataLabels : {
									enabled : true,
									format : '{point.name}:{point.percentage:.1f} % ({point.y}次)'
								}
							}
						},
						series : data['series']
					});
};


//生成各正常卡数量比例图
var normalSiteCount = function(data) {
	jQuery('#site_normal')
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
										text : '各租赁点在使用卡统计图(总次数：'+totle+')'
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
							text : '各租赁点在使用卡统计图'
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
								showInLegend : false,
								dataLabels : {
									enabled : true,
									format : '{point.name}:{point.percentage:.1f} % ({point.y}次)'
								}
							}
						},
						series : data['series']
					});
};




// 生成各租赁点办卡数量比例图
var banSiteCount = function(data) {
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
										text : '各租赁点开户办卡统计图(总次数：'+totle+')'
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
							text : '各租赁点开户办卡统计图'
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
								showInLegend : false,
								dataLabels : {
									enabled : true,
									format : '{point.name}:{point.percentage:.1f} % ({point.y}次)'
								}
							}
						},
						series : data['series']
					});
};

//生成各租赁点注销数量比例图
var cancleSiteCount = function(data) {
	jQuery('#site_cancel')
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
										text : '各租赁点卡注销统计图(总次数：'+totle+')'
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
							text : '各租赁点卡注销统计图'
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
								showInLegend : false,
								dataLabels : {
									enabled : true,
									format : '{point.name}:{point.percentage:.1f} % ({point.y}次)'
								}
							}
						},
						series : data['series']
					});
};

// 生成每日收益统计图表
var dayGetCost = function(data) {
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
		yAxis : { // Primary yAxis
			title : {
				text : '办卡数 (张)'
			},
			labels : {
				format : '{value} 张'
			},
			min : 0
		} ,
		legend : {
			enabled : false
		},
		series : data['series']
	});
};




var drawDayCount = function(data) {
	jQuery('#day_count').highcharts({
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
				text : ''/*'租赁次数(次)'*/,
				style : {
					/*color : '#4572A7'*/
				}
			},
			labels : {
				format : ''/*'{value} 次'*/,
				style : {
					/*color : '#4572A7'*/
				}
			},
			min : 0
		},{ // Secondary yAxis
			title : {
				text : '开户，注销人数(人)',
				style : {
					color : '#89A54E'
				}
			},
			labels : {
				format : '{value} 人',
				style : {
					color : '#89A54E'
				}
			},
			//opposite : true,
			min : 0
		}],
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
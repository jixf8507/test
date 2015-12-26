jQuery(document).ready(function() {

	jQuery("#beginTime,#endTime").datepicker();

	sitesCombox.requestData();

	loadChartData();
	
	jQuery('#queryBtn').click(function(){
		loadChartData();
	});
	jQuery('#siteId').change(function(){
		loadChartData();
	});
});

// 选择框
var sitesCombox = new Combox({
	id : 'siteId',
	url : contextPath + '/merchant/site/ajaxSites.htm?t='
			+ new Date().getTime(),
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择',
	change : function() {
		sitesTable.reloadData();
	}
});

var loadChartData = function() {
	var paraData = {
		"siteId" : jQuery('#siteId').val(),
		"beginTime" : jQuery('#beginTime').val(),
		"endTime" : jQuery('#endTime').val() == '' ? ''
				: jQuery('#endTime').val() + ' 23:59:59',
	};
	jQuery.ajax({
		type : "POST",
		url : contextPath + "/stake/charge/ajaxChangeWayCharts.htm?",
		data : 'paraData=' + encodeURI(JSON.stringify(paraData)),
		async : true,
		dataType : 'json',
		success : function(data) {
			drawChart(data);
		},
		error : function(data) {

		}
	});
};

var drawChart = function(data) {
	new Highcharts.Chart({
		chart : {
			renderTo : 'list',
			type : 'spline', 
//			type : 'areaspline',
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
			text : '充电习惯统计图'
		},
		subtitle : {
			text : ''
		},
		tooltip : {
			pointFormat : '{series.name}:{point.y}(次)'
		},
		xAxis : { 
			categories : data['xAxis']['categories'],
			labels : { 
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
					color : '#0096ff'
				}
			} ,
			min : 0

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
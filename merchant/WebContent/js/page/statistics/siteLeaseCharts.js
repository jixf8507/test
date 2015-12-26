jQuery(document).ready(function() {

	todayLine();

	yestodayLine();
});

// 请求每日收费统计数据
var todayLine = function() {
	var paraData = {
		'siteId' : jQuery('#siteId').val(),
		'day' : getDateStr(0),
	};
	jQuery.ajax({
		type : "POST",
		url : contextPath + "/statistics/site/ajaxSiteHourCount.htm?",
		data : 'paraData=' + encodeURI(JSON.stringify(paraData)),
		async : true,
		dataType : 'json',
		success : function(data) {
			drawLineCost(data, 'today', '#0096ff');
		},
		error : function() {

		}
	});
};

// 请求每日收费统计数据
var yestodayLine = function() {
	var paraData = {
		'siteId' : jQuery('#siteId').val(),
		'day' : getDateStr(-1),
	};
	jQuery.ajax({
		type : "POST",
		url : contextPath + "/statistics/site/ajaxSiteHourCount.htm?",
		data : 'paraData=' + encodeURI(JSON.stringify(paraData)),
		async : true,
		dataType : 'json',
		success : function(data) {
			drawLineCost(data, 'yestoday', '#0096ff');
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
		series : data['series']
	});
};

/**
 * 获取日期
 */
var getDateStr = function(AddDayCount) {
	var dd = new Date();
	dd.setDate(dd.getDate() + AddDayCount);// 获取AddDayCount天后的日期
	var y = dd.getFullYear();
	var m = dd.getMonth() + 1 < 10 ? "0" + (dd.getMonth() + 1)
			: (dd.getMonth() + 1);// 获取当前月份的日期
	var d = dd.getDate() < 10 ? '0' + dd.getDate() : dd.getDate();
	
	return y + "-" + m + "-" + d;
};
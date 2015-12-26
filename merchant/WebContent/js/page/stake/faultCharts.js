jQuery(document).ready(function() {
	
	jQuery("#beginTime,#endTime").datepicker();
	
	loadChartDatas();
	
	jQuery('#queryBtn').click(function(){
		loadChartDatas();
	});
	
});

// 故障统计
var loadChartDatas = function() {
	var paraData = {
			"beginTime" : jQuery('#beginTime').val(),
			"endTime" : jQuery('#endTime').val() == '' ? ''
					: jQuery('#endTime').val() + ' 23:59:59'
	};
	jQuery.ajax({
		type : "POST",
		url : contextPath + "/stake/faultRecord/ajaxFaultChartsData.htm?",
		data : 'paraData=' + encodeURI(JSON.stringify(paraData)),
		async : true,
		dataType : 'json',
		success : function(data) {
			drawPie(data);
		},
		error : function(data) {

		}
	});
};

var drawPie = function(data) {
	jQuery('#list')
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
							text : '充电设备故障比例图'
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
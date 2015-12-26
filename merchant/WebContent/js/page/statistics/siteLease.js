jQuery(document).ready(function() {

	jQuery("#beginTime,#endTime").datepicker();

	siteTable.reloadData();

	// 点击查询按钮事件
	jQuery('#queryBtn').click(function() {
		siteTable.reloadData();
		getColumnCharts();
		returnColumnCharts();
	});

	// 点击查询按钮事件
	jQuery('#excelBtn').click(function() {
		siteTable.exportExcel();
	});
	
	//取车
	getColumnCharts();
	
	//还车
	returnColumnCharts();

});

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



var siteTable = new PageDataTables({
	tableId : 'costsTable',
	ajaxUrl : contextPath + "/statistics/site/ajaxData.htm?t="
			+ new Date().getTime(),
	exportUrl : contextPath + "/statistics/site/exportToExcel.htm?",
	aoColumns : [
			{
				"mDataProp" : "siteName",
				"sClass" : "center"
			},
			{
				"fnRender" : function(obj) {
					var type = obj.aData['type'];
					var typeName = '';
					if ((type & 1) == 1) {
						typeName += '租赁点,';
					}
					if ((type & 2) == 2) {
						typeName += '充电站,';
					}
					if ((type & 4) == 4) {
						typeName += '停车场,';
					}
					return typeName;
				},
				"sClass" : "center"
			},
			{
				"fnRender" : function(obj) {
					var getCount = obj.aData['getCount'];
					return getCount == '' ? '0' : getCount;
				},
				"sClass" : "center"
			},
			{
				"fnRender" : function(obj) {
					var returnCount = obj.aData['returnCount'];
					return returnCount == '' ? '0' : returnCount;
				},
				"sClass" : "center"
			},
			{
				"fnRender" : function(obj) {
					var totleCost = obj.aData['totalCost'];
					return totleCost == '' ? '0' : totleCost;
				},
				"sClass" : "center"
			},
			{
				"fnRender" : function(obj) {
					var id = obj.aData['id'];
					var siteName = obj.aData['siteName'];
					return '<a href="' + contextPath
							+ '/statistics/site/charts.htm?id=' + id
							+ '&siteName=' + encodeURI(encodeURI(siteName))
							+ '" target="_blank">图表分析</a>';
				},
				"sClass" : "center"
			} ],
	beforeload : function() {
		this.paraData = {
			"siteName" : jQuery('#querySiteName').val(),
			"beginTime" : jQuery('#beginTime').val(),
			"endTime" : jQuery('#endTime').val() == '' ? ''
					: jQuery('#endTime').val() + ' 23:59:59'
		};
	}
});

jQuery(document).ready(function() {
	jQuery("#beginTime,#endTime").val(getDateStr(0));
	jQuery("#beginTime,#endTime").datepicker();
	// jQuery("#day").change(function() {
	// requstDatas();
	// yestodayLine();
	// });

	sitesCombox.requestData();

	requstDatas();

	yestodayLine();

	jQuery('#queryBtn').click(function() {
		requstDatas();
		yestodayLine();
	});

	// 点击导出
	jQuery('#excelBtn').click(function() {
		exportExcel();
	});
});

var exportExcel = function() {
	var paraData = getParam();
	window.open(contextPath
			+ '/statistics/merchantUser/exportToExcel.htm?paraData='
			+ encodeURI(encodeURI(JSON.stringify(paraData))));
};
// 选择框
var sitesCombox = new Combox({
	id : 'siteId',
	url : contextPath + '/merchant/site/ajaxSites.htm?t='
			+ new Date().getTime(),
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择',
	change : function() {
		requstDatas();
		yestodayLine();
	}
});

var requstDatas = function() {
	var paraData = getParam();
	jQuery.ajax({
		type : "POST",
		url : contextPath + '/statistics/merchantUser/ajaxData.htm?t='
				+ new Date().getTime(),
		data : 'paraData=' + encodeURI(encodeURI(JSON.stringify(paraData))),
		async : true,
		dataType : 'json',
		success : function(data) {
			writeTable(data);
		},
		error : function() {

		}
	});
};

var getParam = function() {
	var paraData = {
		// day : jQuery('#day').val() == '' ? '' : jQuery('#day').val() + '%',
		"beginTime" : jQuery('#beginTime').val(),
		"endTime" : jQuery('#endTime').val() == '' ? '' : jQuery('#endTime')
				.val()
				+ ' 23:59:59',
		name : jQuery('#name').val() == '' ? '' : '%' + jQuery('#name').val()
				+ '%',
		siteId : jQuery('#siteId').val()
	};
	return paraData;
};

/**
 * 创建表格
 */
var writeTable = function(data) {
	var getCarList = data['getCarList'];
	var returnCarList = data['returnCarList'];
	var createCustomerList = data['createCustomerList'];
	var numbers = [ getCarList.length, returnCarList.length,
			createCustomerList.length ];
	var maxInNumbers = Math.max.apply(Math, numbers);

	jQuery('#myHtml').html('');
	jQuery('#dateTh').html('日期：' + jQuery('#beginTime').val()+"到"+jQuery('#endTime').val());
	jQuery('#get').html('取车(' + getCarList.length + ")");
	jQuery('#return').html('还车(' + returnCarList.length + ")");
	jQuery('#customer').html('开户(' + createCustomerList.length + ")");

	for (var i = 0; i < maxInNumbers; i++) {
		var html = '<tr>';
		var getCarItem = getCarList[i];
		var returnCarItem = returnCarList[i];
		var createCustomerItem = createCustomerList[i];
		if (getCarItem != null) {
			html += '<td>' + getCarItem['carNumber'] + '</td>';
			html += '<td>' + getCarItem['realityGetTime'] + '</td>';
			html += '<td>' + getCarItem['siteName'] + '</td>';
			html += '<td>' + getCarItem['menForGet'] + '</td>';
		} else {
			html += '<td></td>';
			html += '<td></td>';
			html += '<td></td>';
			html += '<td></td>';
		}
		if (returnCarItem != null) {
			html += '<td>' + returnCarItem['carNumber'] + '</td>';
			html += '<td>' + returnCarItem['realityReturnTime'] + '</td>';
			html += '<td>' + returnCarItem['siteName'] + '</td>';
			html += '<td>' + returnCarItem['menForReturn'] + '</td>';
		} else {
			html += '<td></td>';
			html += '<td></td>';
			html += '<td></td>';
			html += '<td></td>';
		}
		if (createCustomerItem != null) {
			html += '<td>' + createCustomerItem['phone'] + '</td>';
			html += '<td>' + createCustomerItem['name'] + '</td>';
			html += '<td>' + createCustomerItem['createdTime'] + '</td>';
			html += '<td>' + createCustomerItem['siteName'] + '</td>';
			html += '<td>' + createCustomerItem['transactUser'] + '</td>';
		} else {
			html += '<td></td>';
			html += '<td></td>';
			html += '<td></td>';
			html += '<td></td>';
			html += '<td></td>';
		}
		html += '</tr>';
		jQuery('#myHtml').append(html);
	}

};

// 请求每日收费统计数据
var yestodayLine = function() {
	var paraData = {
		// day : jQuery('#day').val(),
		"beginTime" : jQuery('#beginTime').val(),
		"endTime" : jQuery('#endTime').val() == '' ? '' : jQuery('#endTime')
				.val()
				+ ' 23:59:59',
		name : jQuery('#name').val() == '' ? '' : '%' + jQuery('#name').val()
				+ '%',
		siteId : jQuery('#siteId').val()
	};
	jQuery.ajax({
		type : "POST",
		url : contextPath + "/statistics/site/ajaxSiteHourCount.htm?",
		data : 'paraData=' + encodeURI(encodeURI(JSON.stringify(paraData))),
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
	jQuery('#' + chartsId).highcharts(
			{
				chart : {
					zoomType : 'x'
				},
				colors : [ '#ED561B', '#50B432', '#DDDF00', '#24CBE5',
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
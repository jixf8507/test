jQuery(document).ready(function() {

	startTimeForDayCombox.requestData();
	endTimeForDayCombox.requestData();
	startTimeForWorkCombox.requestData();
	endTimeForWorkCombox.requestData();
	

	// /// FORM VALIDATION /////
	jQuery("#form1").validate({
		rules : {
			'typeName' : "required",
			'oneHoursCost' : {
				"required" : true,
				"number" : true
			},
			'maxCostForDay' : {
				"required" : true,
				"number" : true
			},
			'nightCost' : {
				"required" : true,
				"number" : true
			},
			'perHoursKmsForDay' : {
				"required" : true,
				"number" : true
			},
			'maxKmsForNight' : {
				"required" : true,
				"number" : true
			},
			'maxKmsForDay' : {
				"required" : true,
				"number" : true
			},
			'perKmsPrice' : {
				"required" : true,
				"number" : true
			},
			'maxDays' : {
				"required" : true,
				"number" : true
			},
			'minDays' : {
				"required" : true,
				"number" : true
			},
			'reservedMinute' : {
				"required" : true,
				"number" : true
			}
		}
	});
	
	jQuery("#form1").submit(function() {
		if (!jQuery("#form1").valid()) {
			return false;
		}
		var startTimeForDay = jQuery("#startTimeForDay").val();
		var endTimeForDay = jQuery("#endTimeForDay").val();
		if (startTimeForDay == '' || endTimeForDay == '') {
			Dialog.alert("提示：请选择每天取/还车时间范围");
			return false;
		}
	});

});

// 选择框
var startTimeForDayCombox = new Combox({
	id : 'startTimeForDay',
	url : contextPath + '/price/priceType/ajaxTimes.htm?t='
	+ new Date().getTime(),
	cText : 'name',
	cValue : 'value',
	emptyText : '请选择开始时间',
	change : function() {

	}
});

var endTimeForDayCombox = new Combox({
	id : 'endTimeForDay',
	url : contextPath + '/price/priceType/ajaxTimes.htm?t='
	+ new Date().getTime(),
	cText : 'name',
	cValue : 'value',
	emptyText : '请选择结束时间',
	change : function() {

	}
});

// 选择框
var startTimeForWorkCombox = new Combox({
	id : 'startTimeForWork',
	url : contextPath + '/price/priceType/ajaxTimes.htm?t='
	+ new Date().getTime(),
	cText : 'name',
	cValue : 'value',
	emptyText : '请选择开始时间',
	change : function() {
		
	}
});

var endTimeForWorkCombox = new Combox({
	id : 'endTimeForWork',
	url : contextPath + '/price/priceType/ajaxTimes.htm?t='
	+ new Date().getTime(),
	cText : 'name',
	cValue : 'value',
	emptyText : '请选择结束时间',
	change : function() {
		
	}
});

/**
 * 提交form表单
 */
var submit = function(callBack) {
	if (jQuery("#form1").valid()) {
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/price/priceType/saveAdd.htm?',
			data : jQuery('#form1').serialize(), 
			async : false,
			dataType : 'json',
			error : function(request) {
				Dialog.alert("提示：操作失败");
			},
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：操作成功",function(){
						callBack();
					});
					
				}
			}
		});
	}
};






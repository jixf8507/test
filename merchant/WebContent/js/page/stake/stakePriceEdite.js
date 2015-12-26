jQuery(document).ready(function() {

	time1.requestData();
	time2.requestData();
	time3.requestData();
	time4.requestData();

	// /// FORM VALIDATION /////
	jQuery("#form1").validate({
		rules : {
			'name' : "required",
			'PriceA' : {
				"required" : true,
				"number" : true
			},
			'PriceB' : {
				"required" : true,
				"number" : true
			},
			'PriceC' : {
				"required" : true,
				"number" : true
			},
			'PriceD' : {
				"required" : true,
				"number" : true
			},
		}
	});

	jQuery('#name').change(function() {
		checkName();
	});
});

// 选择框
var time1 = new Combox({
	id : 'time1',
	url : contextPath + '/price/priceType/ajaxTimes.htm?t='
			+ new Date().getTime(),
	cText : 'name',
	cValue : 'value',
	emptyText : '选择开始时间',
	change : function() {

	},
	beforeload : function() {
		this.emptyValue = jQuery("#oldTime1").val();
	}
});

// 选择框
var time2 = new Combox({
	id : 'time2',
	url : contextPath + '/price/priceType/ajaxTimes.htm?t='
			+ new Date().getTime(),
	cText : 'name',
	cValue : 'value',
	emptyText : '选择开始时间',
	change : function() {

	},
	beforeload : function() {
		this.emptyValue = jQuery("#oldTime2").val();
	}
});

// 选择框
var time3 = new Combox({
	id : 'time3',
	url : contextPath + '/price/priceType/ajaxTimes.htm?t='
			+ new Date().getTime(),
	cText : 'name',
	cValue : 'value',
	emptyText : '选择开始时间',
	change : function() {

	},
	beforeload : function() {
		this.emptyValue = jQuery("#oldTime3").val();
	}
});

// 选择框
var time4 = new Combox({
	id : 'time4',
	url : contextPath + '/price/priceType/ajaxTimes.htm?t='
			+ new Date().getTime(),
	cText : 'name',
	cValue : 'value',
	emptyText : '选择开始时间',
	change : function() {

	},
	beforeload : function() {
		this.emptyValue = jQuery("#oldTime4").val();
	}
});

// 检查内置卡号是否重复
var checkName = function() {
	jQuery.ajax({
		type : "POST",
		url : contextPath + "/stake/price/checkName.htm?",
		data : "name=" + jQuery('#name').val() + "&id=" + jQuery('#id').val(),
		async : true,
		dataType : 'json',
		success : function(data) {
			if (data.success) {

			} else {
				Dialog.alert("提示：充电套餐名称已重复,请重新填写！");
				jQuery('#name').val("");
			}
		},
		error : function() {
			Dialog.alert("错误：验证失败");
		}
	});
};

/**
 * 提交form表单
 */
var submit = function(callBackAdd) {
	if (jQuery("#time1").val() == '' || jQuery("#time2").val() == ''
			|| jQuery("#time3").val() == '' || jQuery("#time4").val() == '') {
		Dialog.alert("提示：开始时间填写不完整");
		return false;
	}
	if (jQuery("#form1").valid()) {
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/stake/price/saveEdite.htm?',
			data : jQuery('#form1').serialize(),
			async : false,
			dataType : 'json',
			error : function(request) {
				Dialog.alert("错误：操作失败");
			},
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：操作成功", function() {
						callBackAdd();
					});

				} else {
					Dialog.alert("提示：操作失败");
				}
			}
		});
	}
};

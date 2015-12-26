jQuery(document).ready(function() {

	jQuery('#contactPhone').change(function() {
		checkPhone(this);
	});

	jQuery('#enterpriseName').change(function() {
		checkName(this);
	});
	
	jQuery('#customerQueryBtn,#customerDiv').click(function() {
		customerTable.bStateSave = false;
		customerTable.reloadData();
	});

	jQuery("#form1").validate({
		rules : {
			'contactPhone' : {
				"required" : true,
				"phone" : true
			},
			'contactPerson' : {
				"required" : true,
				"maxlength" : 20
			},
			'enterpriseName' : {
				"required" : true,
				"maxlength" : 50
			},
			'address' : {
				"required" : true,
				"maxlength" : 150
			},
			'bankType' : {
				"required" : true,
				"maxlength" : 20
			},
			'bankCardNO' : {
				"required" : true,
				"maxlength" : 20
			},
		}
	});

});

var customerTable = new PageDataTables({
	tableId : 'customerTable',
	iDisplayLength : 6,
	bSort : false,
	ajaxUrl : contextPath + "/customer/unit/ajaxCustomerData.htm?t="
			+ new Date().getTime(),
	exportUrl : contextPath + "/customer/unit/exportCustomerToExcel.htm?",
	aoColumns : [
			{
				"mDataProp" : "phone",
				"sClass" : "center"
			},
			{
				"mDataProp" : "name",
				"sClass" : "center"
			},
			{
				"mDataProp" : "idCard",
				"sClass" : "center"
			},
			{
				"mDataProp" : "moneyOfassure",
				"sClass" : "center"
			},
			{
				"mDataProp" : "balance",
				"sClass" : "center"
			},
			{
				"mDataProp" : "status",
				"sClass" : "center"
			},
			{
				"fnRender" : function(obj) {
					var id = obj.aData['id'];
					var name = obj.aData['name'];
					var enterpriseId = obj.aData['enterpriseId'];
					return '<a href="javaScript:deleteFun(' + id + ','
							+ enterpriseId + ',\'' + name + '\');">移除</a>';
				},
				"sClass" : "center"
			} ],
	beforeload : function() {
		this.paraData = {
			"enterpriseId" : jQuery('#id').val(),
			"idCard" : jQuery('#idCard').val() == '' ? '' : '%'
					+ jQuery('#idCard').val() + "%",
			"phone" : jQuery('#phone').val() == '' ? '' : '%'
					+ jQuery('#phone').val() + "%",
			"name" : jQuery('#name').val() == '' ? '' : '%'
					+ jQuery('#name').val() + "%",
			"notStatus" : '注销'
		};
	}
});

// 检查手机号码是否已存在
var checkPhone = function(thiz) {
	var phone = jQuery(thiz);
	jQuery.ajax({
		type : "POST",
		url : contextPath + "/customer/unit/checkPhone.htm?",
		data : {
			phone : phone.val(),
			id : jQuery("#id").val()
		},
		async : true,
		dataType : 'json',
		success : function(data) {
			if (data.success) {

			} else {
				Dialog.alert("提示：该手机号码已经存在");
				phone.attr("value", "");
			}
		},
		error : function() {
			Dialog.error("提示：验证失败");
		}
	});
};

// 检测企业名称是否已存在
var checkName = function(thiz) {
	var name = jQuery(thiz);
	jQuery.ajax({
		type : "POST",
		url : contextPath + "/customer/unit/checkName.htm?",
		data : {
			name : name.val(),
			id : jQuery("#id").val()
		},
		async : true,
		dataType : 'json',
		success : function(data) {
			if (data.success) {

			} else {
				Dialog.alert("提示：该企业名称已经存在");
				name.attr("value", "");
			}
		},
		error : function() {
			Dialog.error("提示：验证失败");
		}
	});
};

/**
 * 提交form表单
 */
var submit = function(callBackEdite) {
	if (jQuery("#form1").valid()) {
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/customer/unit/saveEdite.htm?',
			data : jQuery('#form1').serialize(),
			async : false,
			dataType : 'json',
			error : function(request) {
				Dialog.alert("错误：操作失败");
			},
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：操作成功", function() {
						callBackEdite();
					});

				}
			}
		});
	}
};

var deleteFun = function(id, enterpriseId, name) {
	Dialog.confirm('警告：确认移除客户 [ ' + name + ' ] 吗?', function() {
		jQuery.ajax({
			type : "POST",
			url : contextPath + "/customer/unit/delCustomer.htm?",
			data : "id=" + id + "&enterpriseId=" + enterpriseId,
			async : true,
			dataType : 'json',
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：移除成功", function() {
						location.reload(true);
					});

				} else {
					Dialog.alert("提示：移除失败");
				}
			},
			error : function() {
				Dialog.alert("提示：移除失败");
			}
		});
	});

};
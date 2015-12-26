jQuery(document).ready(function() {

	checkDetailTable.createTable(typeDetail);

	// /// FORM VALIDATION /////
	jQuery("#form1").validate({
		rules : {
			'typeName' : "required",
			'carManufacturerId' : "required",
			'seatCount' : {
				"required" : true,
				"number" : true
			},
			'color' : "required",
			'energy' : "required",
			'components' : "required",
			'statuss' : "required",
			'describes' : "required",
		}
	});

	carManufacturerCombox.requestData();

	jQuery("#energy").chosen();
});

var checkDetailTable = {
	num : 0,
	createTable : function(typeDetail) {
		var components = typeDetail['components'];
		var status = typeDetail['status'];
		var describe = typeDetail['describe'];
		for (var i = 0; i < components.length; i++) {
			this.add(components[i], status[i], describe[i]);
		}
	},
	add : function(component, status, describe) {
		this.num++;
		var html = '<tr id="index_' + this.num + '">';
		html += '<td><input type="text" name="components" value="' + component
				+ '" class="smallinput" style="width: 100px;"/></td>';
		html += '<td><input type="text" name="statuss" class="smallinput" value="'
				+ status + '" style="width: 100px;" /></td>';
		html += '<td><input type="text" name="describes" class="smallinput"value="'
				+ describe + '" style="width: 100px;" /></td>';
		html += '<td><input type="button" value="删除" onclick="checkDetailTable.del('
				+ this.num + ')"/></td>';
		html += '</tr>';
		jQuery("#checkDetailTable").append(html);
	},
	del : function(id) {
		jQuery("tr[id='index_" + id + "']").remove();// 删除当前行
	}
};

// 选择框
var carManufacturerCombox = new Combox({
	id : 'carManufacturerId',
	url : contextPath + '/car/manufacturer/ajaxManufacturer.htm?t='
			+ new Date().getTime(),
	cText : 'manufacturerName',
	cValue : 'id',
	emptyText : '请选择供应商',
	emptyValue : '',
	beforeload : function() {
		this.emptyValue = jQuery("#hiddManufacturerId").val();
	}
});

/**
 * 提交form表单
 */
var submit = function(callBack) {
	if (jQuery("#carManufacturerId").val() == '') {
		Dialog.alert("提示：请选择车辆供应商");
		return;
	}
	if (jQuery("#form1").valid()) {
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/car/type/saveEdite.htm?',
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
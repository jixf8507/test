jQuery(document).ready(function() {


	useCarsTable.reloadData();

	// 点击使用车辆查询按钮
	jQuery('#useQueryBtn').click(function() {
		useCarsTable.reloadData();
	});

});

// 已经使用的车载设备
var useCarsTable = new PageDataTables({
	tableId : 'useCarsTable',
	ajaxUrl : contextPath + "/car/device/ajaxData.htm?t=" + new Date().getTime(),
	aoColumns : [
	     		{
	    			"fnRender" : function(obj) {
	    				var id = obj.aData['id'];
	    				var noSim = jQuery('#noSim').val();
	    				var simCard = obj.aData['simCard'];
	    				var deviceNO = obj.aData['deviceNO'];
	    				if (noSim == 'yes' && simCard != '') {
	    					return '';
	    				}
	    				return '<input style="width: 20px;" type="radio" name="id" id="'
	    						+ id
	    						+ '" value="'
	    						+ id
	    						+ '_'
	    						+ simCard
	    						+ '_' + deviceNO + '" >';
	    			},
	    			"sClass" : "center"
	    		}, {
	    			"mDataProp" : "deviceNO",
	    			"sClass" : "center"
	    		}, {
	    			"mDataProp" : "status",
	    			"sClass" : "center"
	    		}, {
	    			"mDataProp" : "lastOnlineTime",
	    			"sClass" : "center"
	    		}, {
	    			"mDataProp" : "simCard",
	    			"sClass" : "center"
	    		}],
	beforeload : function() {
		this.paraData = {
			"deviceNO" : jQuery('#deviceNO').val() == '' ? '' : '%'
					+ jQuery('#deviceNO').val() + '%'
		};
	}
});

/**
 * 提交form表单
 */
var submit = function(callBack) {
	var selId = '';
	jQuery("input[name=id]").each(function() {
		if (this.checked) {
			selId = jQuery(this).val();
		}
	});
	var arr = selId.split('_');
	var data = {
		deviceId : arr[0]
	};
	jQuery.ajax({
		cache : true,
		type : "POST",
		url : contextPath + '/car/device/updateSim.htm?',
		data : {
			id : jQuery('#simId').val(),
			deviceId : data['deviceId']
		},
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
				
			} else {
				Dialog.alert("提示：操作失败");
			}
		}
	});
};
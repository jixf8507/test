
	var submit = function(callBack) {
		if (jQuery('#orderId').val() == undefined) {
			callBack();
			return;
		}
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/order/peccancyRecord/save_insert.htm?',
			data : jQuery('#form1').serialize(),
			async : false,
			dataType : 'json',
			error : function(request) {
				Dialog.alert("错误：操作失败");
			},
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：操作成功", function() {
						callBack();
					});

				} else {
					Dialog.alert("提示：操作失败");
				}
			}
		});
	};
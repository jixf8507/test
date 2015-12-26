jQuery(document).ready(function() {
	
	requestStakePrice();
});

/**
 * 请求充电桩套餐
 */
var requestStakePrice = function() {
	 jQuery.ajax({
		type : "GET",
		url : contextPath + '/stake/device/ajaxStakeData.htm?t='
				+ new Date().getTime(),
		data : 'id=' + jQuery('#stakeId').val(),
		async : true,
		success : function(data) {
			data = eval('('+data+')');
			if(data.success){
				jQuery('#priceId').val(data['info']);
				requestPrice(data['info']);
			}else{
				Dialog.alert("提示：请求异常");
			}
		},
		error : function() {
			Dialog.alert("提示：请求异常");
		}
	});
};

/**
 * 请求充电套餐列表
 */
var requestPrice = function(priceId) {
	paraData = {
		
	}, jQuery.ajax({
		type : "GET",
		url : contextPath + '/stake/price/ajaxStakePrice.htm?t='
				+ new Date().getTime(),
		data : 'paraData=' + encodeURI(encodeURI(JSON.stringify(paraData))),
		async : true,
		success : function(data) {
			var list = eval('(' + data + ')');
			jQuery.each(list, function(index, item) {
				var html = '<tr>';
				if (item['id'] == priceId) {
					html += '<td><input type="radio" name="id" value="'
							+ item['id'] + '" checked="checked"/></td>';
				} else {
					html += '<td><input type="radio" name="id" value="'
							+ item['id'] + '" /></td>';
				}
				html += '<td>' + item['name'] + '</td>';
				html += '</tr>';
				jQuery('#prices').append(html);
			});
		},
		error : function() {
			Dialog.alert("提示：请求异常");
		}
	});
};

/**
 * 提交form表单
 */
var submit = function(callBack) {
	
	jQuery("input[name=id]").each(function() {
		if (this.checked) {
			id = jQuery(this).val();
		}
	});
	jQuery.ajax({
		cache : true,
		type : "POST",
		url : contextPath + '/stake/price/stakeSelectPrice.htm?',
		data : {
			id : id,
			stakeId : jQuery('#stakeId').val(),
			priceId : jQuery('#priceId').val()
		},
		async : false,
		dataType : 'json',
		error : function(request) {
			Dialog.alert("提示：操作失败");
		},
		success : function(data) {
			if (data.success) {
				Dialog.alert("提示：操作成功！", function() {
					callBack();
				});

			}
		}
	});
};

jQuery(document).ready(function() {

	requestPrice();

	jQuery('#all').click(function() {
		checkALL(this);
	});
});

/**
 * 请求租赁套餐列表
 */
var requestPrice = function() {
	paraData = {
		"carId" : jQuery('#carId').val(),
		"tableName" : jQuery('#tableName').val(),
		"type" : "leftSelect"
	}, jQuery.ajax({
		type : "GET",
		url : contextPath + '/price/typeRelation/ajaxCarTypes.htm?t='
				+ new Date().getTime(),
		data : 'paraData=' + encodeURI(encodeURI(JSON.stringify(paraData))),
		async : true,
		success : function(data) {
			var list = eval('(' + data + ')');
			jQuery.each(list, function(index, item) {
				var html = '<tr>';
				if (item['priceTypeId'] != '') {
					html += '<td><input type="checkbox" name="typeId" value="'
							+ item['id'] + '" checked="checked"/></td>';
				} else {
					html += '<td><input type="checkbox" name="typeId" value="'
							+ item['id'] + '" /></td>';
				}
				html += '<td>' + item['typeName'] + '</td>';
				html += '</tr>';
				jQuery('#prices').append(html);
			});
		},
		error : function() {
			Dialog.alert("提示：请求异常");
		}
	});
};

// 全选
var checkALL = function(thiz) {
	var parentTable = jQuery(thiz).parents('table');
	var ch = parentTable.find('tbody input[name=typeId]');
	if (jQuery(thiz).is(':checked')) {
		ch.each(function() {
			jQuery(this).attr('checked', true);
			jQuery(this).parent().addClass('checked');
			jQuery(this).parents('tr').addClass('selected');
		});
		parentTable.find('.checkall').each(function() {
			jQuery(this).attr('checked', true);
		});
	} else {
		ch.each(function() {
			jQuery(this).attr('checked', false);
			jQuery(this).parent().removeClass('checked');
			jQuery(this).parents('tr').removeClass('selected');
		});
		parentTable.find('.checkall').each(function() {
			jQuery(this).attr('checked', false);
		});
	}
};

/**
 * 提交form表单
 */
var submit = function(callBack) {
	jQuery.ajax({
		cache : true,
		type : "POST",
		url : contextPath + '/price/typeRelation/saveCarTypes.htm?',
		data : jQuery('#myForm').serialize(),
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

jQuery(document).ready(function() {

	jQuery("#form2").validate({
		rules : {
			'name' : "required",
		}
	});
		   
		jQuery("#name").bind("change", function(){
			jQuery.ajax({
				cache : true,
				type : "POST",
				url : contextPath + '/asset/category/checkOnly.htm?',
				data : "name="+jQuery('#name').val()+"&id="+jQuery('#id').val(),
				async : false,
				dataType : 'json',
				error : function(request) {
					Dialog.alert("错误：操作失败");
				},
				success : function(data) {
					if (data.success) {
						 
						
					}else{
						Dialog.alert("提示：该种类已存在，请重新输入");
						jQuery('#name').val("");
					}
				}
			});
		});
	
});

/**
 * 提交form表单
 */
var submit = function(callBackAdd) {
	if (jQuery("#form2").valid()) {
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/asset/category/saveAdd.htm?',
			data : jQuery('#form2').serialize(), 
			async : false,
			dataType : 'json',
			error : function(request) {
				Dialog.alert("错误：操作失败");
			},
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：操作成功",function(){
						callBackAdd();
					});
					
				}else{
					Dialog.alert("提示：操作失败");
				}
			}
		});
	}
};
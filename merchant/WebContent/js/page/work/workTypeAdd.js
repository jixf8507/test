jQuery(document).ready(function() {
	jQuery('#form1').validate({
		rules : {
			typeName : "required",
			workDes : "required"

		},
		messages : {
			typeName : "请填写类型名称",
			workDes : "请填写类型描述"
		}
	});

});

/**
 * 提交form表单
 */
var submit = function(callBack) {
if (jQuery("#form1").valid()) {
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/work/manage/saveAdd.htm?',
			data : jQuery('#form1').serialize(),// 你的formid
			async : false,
			dataType : 'json',
			error : function(request) {
				Dialog.alert("提示：操作失败");
			},
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：操作成功", function() {
						callBack();
					});

				}
			}
		});
		
	}
};

var selectUser = function() {
	var diag = new Dialog();
	diag.Width = 900;
	diag.Height = 500;
	diag.Title = "负责人列表";
	diag.URL = contextPath + '/work/manage/selectUser.htm?',
			diag.MessageTitle = "负责人列表";
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callback);
		diag.close();
	};
	diag.show();
};
// 将数据回写到表单userName
var callback = function(userName, id,mobilePhone) {
	jQuery("#userName").attr("value", userName);
	jQuery("#userId").attr("value", id);
	jQuery("#mobilePhone").attr("value", mobilePhone);
};

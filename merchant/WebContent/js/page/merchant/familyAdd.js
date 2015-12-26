jQuery(document).ready(function() {

	//点击查询用户按钮要显示用户列表，可以里模糊查询，通过单选框选择户主的customer账户关联。
	jQuery('#findCustomerBtn').click(function() {
		selectCustomer();
	});
	
	
	
	
	

//	jQuery('#customerid').live("change",function() {
//		
//	});
//
//	jQuery('#cardNO').change(function() {
//		checkCardNO(this);
//	});
//
//	jQuery('#idCard').change(function() {
//		checkIdCard(this);
//	});

	jQuery('#idFamilyImgFile').change(function() {
		uploadIdCardImg(this);
	});

	jQuery("#form1").validate({
		rules : {
			'homePhone' : {
				"required" : true,
				"phone" : true
			},
			'homeAddress' : "required",
			'householder' : "required"
		}
	});

});


//将数据回写到表单
var callback = function(id,cname,phone,address,idCard){
	//检查用户是否已经是家庭户主的户主，如果是户主则不允许在建立家庭
	jQuery.ajax({
		type : "POST",
		url : contextPath + "/merchant/family/checkHouseHolder.htm?",
		data :"customerid=" + id,
		async : true,
		dataType : 'json',
		success : function(data) {
			if (false ==data.success ) {
				Dialog.alert("提示：该客户已经是家庭"+data.info+" 的负责人，不能添加");
				return false;
			}
			
			jQuery("#customerid").attr("value",id);
			jQuery("#householder").attr("value",cname);
			jQuery("#homephone").attr("value",phone);
			jQuery("#homeaddress").attr("value",address);
			jQuery("#idCard").text(idCard);
		},
		error : function() {
			Dialog.error("提示：验证客户失败");
		}
	});
};
//弹出用户列表
var selectCustomer = function(){
	var diag =new Dialog();
	diag.Width = 900;
	diag.Height = 500;
	diag.Title="选择用户列表";
	diag.URL=contextPath + "/merchant/family/select.htm?t="+new Date().getTime();
	diag.MessageTitle="无";
	diag.OKEvent=function(){
		diag.innerFrame.contentWindow.submit(callback);
		diag.close();
	};
	diag.show();
};


//上传用户身份证，户口本照片
var uploadIdCardImg = function(thiz) {
	var filepath = jQuery(thiz).val();
	var extStart = filepath.lastIndexOf(".");
	var ext = filepath.substring(extStart, filepath.length).toUpperCase();
	if (filepath != "") {
		if (ext != ".PNG" && ext != ".JPG") {
			Dialog.alert("提示：只能上传png,jpg图片");
			jQuery(thiz).outerHTML = jQuery(thiz).outerHTML;
			return;
		}
		jQuery.ajaxFileUpload({
			url : contextPath + '/system/upload/imageUpload.htm?',
			secureuri : false,
			fileElementId : 'idFamilyImgFile',
			dataType : 'json',
			async : true,
			beforeSend : function() {

			},
			success : function(data, status) {
				jQuery("#idFamilyImg").attr("src", contextPath + data.data);
				jQuery("#idFamilyImgHidden").attr("value", data.fileName);
				jQuery("#familyImgHidden").attr("value", data.data);
			},
			error : function() {
				alert(e);
			}
		});
	} else {
		Dialog.alert("提示：请选择图片");
	}
};







// 选择框
var siteCombox = new Combox({
	id : 'siteId',
	url : contextPath + '/merchant/site/ajaxSites.htm?t='
			+ new Date().getTime(),
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择',
	beforeload : function() {
		this.emptyValue = jQuery("#hid_curSiteId").val();
	}
});

// 检查手机号码是否已存在
var checkPhone = function(thiz) {
	var phone = jQuery(thiz);
	jQuery.ajax({
		type : "POST",
		url : contextPath + "/customer/customer/checkPhone.htm?",
		data : "phone=" + phone.val(),
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

// 检测租赁卡号是否已存在
var checkCardNO = function(thiz) {
	var cardNO = jQuery(thiz);
	jQuery.ajax({
		type : "POST",
		url : contextPath + "/customer/customer/checkCardNO.htm?",
		data : "cardNO=" + cardNO.val(),
		async : true,
		dataType : 'json',
		success : function(data) {
			if (data.success) {

			} else {
				Dialog.alert("提示：该租赁卡号已经存在");
				cardNO.attr("value", "");
			}
		},
		error : function() {
			Dialog.error("提示：验证失败");
		}
	});
};

// 检测身份证是否已存在
var checkIdCard = function(thiz) {
	var idCard = jQuery(thiz);
	jQuery.ajax({
		type : "POST",
		url : contextPath + "/customer/customer/checkIdCard.htm?",
		data : "idCard=" + idCard.val(),
		async : true,
		dataType : 'json',
		success : function(data) {
			if (data.success) {

			} else {
				Dialog.alert("提示：该身份证号码已经存在");
				idCard.attr("value", "");
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
var submit = function(callBack) {
	if (jQuery("#form1").valid()) {
		if(jQuery('#siteId').val() == '' ){
			Dialog.alert("提示：请选择开户站点");
			return false;
		}
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/merchant/family/saveAdd.htm?',
			data : jQuery('#form1').serialize(),// 你的formid
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

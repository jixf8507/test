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
	
	
	//删除家庭成员可以批量删除
	jQuery('#deleteBtn').live("click",function() {
		deleteMember();
	});
	
	
	
	jQuery("#member").click(function(){
		memberTable.bStateSave=false;
		memberTable.reloadData();
	});
	
	
	jQuery(".checkall").click(function() {
		var thiz = this;
		jQuery("input[name=customerId]").each(function() {
			jQuery(this).attr('checked', thiz.checked);
		});
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
	
	//改变家庭的户主
	jQuery("#changeHouseHolder").click(function(){
		changeHouseHolder();	
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
			
			jQuery("#newCustomerId").attr("value",id);
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

//修改负责人
var changeHouseHolder =function(){
	//弹出用户列表，选择用户更换家庭户主。
	var diag =new Dialog();
	diag.Width = 1200;
	diag.Height = 700;
	diag.Title="选择用户列表";
	diag.URL=contextPath + "/merchant/family/select.htm?t="+new Date().getTime();
	diag.MessageTitle="无";
	diag.OKEvent=function(){
		diag.innerFrame.contentWindow.submit(callback);
		diag.close();
	};
	diag.show();
};

//取得的所有的复选框id值。
var ids="";
//取得所有选择的复选框
var eachCheckBox =function(){	
	ids=jQuery("input:checkbox[name=customerId]:checked").map(function(index,elem){
		return jQuery(elem).val();
	}).get().join(',');
};


//删除成员
var deleteMember = function(){
	eachCheckBox();
	if (""==ids||null==ids) {
		Dialog.confirm("没有选择数据，请选择数据");
		return false;
	}
	Dialog.confirm("确定要删除这些成员？", function(){
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/merchant/family/saveEditMenber.htm?',
			data : "idsStr="+ids +"&familyId="+jQuery("#familyId").val(),// 你的formid
			async : false,
			dataType : 'json',
			error : function(request) {
				Dialog.alert("提示：操作失败");
			},
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：操作成功",function(){
						memberTable.reloadData();
					});
				}
			}
		});
	});
};




var column = [ // 设定各列宽度
               {
       			"fnRender" : function(obj) {
       				var id = obj.aData['id'];
       				return '<input style="width: 20px;" type="checkBox" name="customerId" id="'
       					+ id
       					+ '" title="'
       					+ id
       					+ '"value="' 
       					+ id
       					+ '" >';
       			},
       			"sWidth" : "4%",
       			"sClass" : "center"
       		},
    		{
    			/*"mDataProp" : "idCard",*/
       			"fnRender" : function(obj) {
       				var idCard = obj.aData['idCard'];
       				var id = obj.aData['id'];
       				return '<span name="idCard" value="'+idCard+'" id="idCard_'+id+'" >'+idCard+'</span>';
       			},
    			"sClass" : "center"
    		},
    		{
    			/*"mDataProp" : "phone",*/
       			"fnRender" : function(obj) {
       				var phone = obj.aData['phone'];
       				var id = obj.aData['id'];
       				return '<span name="phone" value="'+phone+'" id="phone_'+id+'" >'+phone+'</span>';
       			},
    			"sClass" : "center"
    		},
    		{
    			/*"mDataProp" : "name",*/
    			"fnRender" : function(obj) {
       				var cname = obj.aData['name'];
       				var id = obj.aData['id'];
       				return '<span name="cname" value="'+cname+'" id="cname_'+id+'" >'+cname+'</span>';
       			},
    			"sClass" : "center"
    		},
    		{
    			/*"mDataProp" : "address",*/
    			"fnRender" : function(obj) {
       				var address = obj.aData['address'];
       				var id = obj.aData['id'];
       				return '<span name="address" value="'+address+'" id="address_'+id+'" >'+address+'</span>';
       			},
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
    			"mDataProp" : "createdTime",
    			"sClass" : "center"
    		},
    	 ];

//加载家庭成员列表
var memberTable = new PageDataTables({
	tableId : 'memberTable',
	ajaxUrl : contextPath + "/merchant/family/memberAjaxData.htm?t="
			+ new Date().getTime(),// 这个是请求的地址
	exportUrl : contextPath + "/merchant/family/exportToExcel.htm?",// 这个是请求的地址
	aoColumns : column,
	bSort : false,
	addButton :[
	            {"outId":"deleteBtn","value":"删除成员"}          
	],
	beforeload : function() {
		this.paraData = {
			"familyId" : jQuery("#familyId").val(),
			"noStatus" : "注销"
		};
	}
});



//弹出用户列表
var selectCustomer = function(){
	var diag =new Dialog();
	diag.Width = 1200;
	diag.Height = 700;
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
			url : contextPath + '/merchant/family/updateFamilyInfo.htm?',
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

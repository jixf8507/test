jQuery(document).ready(function() {

//	provinceCombox.requestData();
//	cityCombox.requestData();

	familyTable.reloadData();
	
	
	
	// 点击查询按钮事件
	jQuery('#fronzenfamilyDiv').click(function() {
		fronzenFamilyTable.bStateSave=false;
		fronzenFamilyTable.reloadData();
	});
	
	
	
	// 点击查询按钮事件
	jQuery('#familyQueryBtn').click(function() {
		familyTable.bStateSave=false;
		familyTable.reloadData();
	});
	
	
	//新增家庭
	jQuery('#addBtn').live('click',function() {
		addFamily();
	});
	
	
	
	jQuery('#batchBtn').live('click',function() {
		/**
		 * 批量作废
		 */
		batchBolishCoupon();
	});
	
	jQuery(".checkall").click(function() {
		var thiz = this;
		jQuery("input[name=unUsedId]").each(function() {
			jQuery(this).attr('checked', thiz.checked);
		});
	});
	
	
});




//保存方法
var callBack = function() {
	location.reload();
};


var columns = [ // 设定各列宽度
  
		{
			/*"mDataProp" : "id",*/
			"fnRender" : function(obj) {
				var id = obj.aData['id'];
				return "JT_"+id;
			},
			"sClass" : "center"
		},
		{
			"mDataProp" : "householder",
			"sClass" : "center"
		},
		{
			"mDataProp" : "homeAddress",
			"sClass" : "center"
		},
		{
			"mDataProp" : "homePhone",
			"sClass" : "center"
		},
		{
			"mDataProp" : "createdTime",
			"sClass" : "center"
		},
		{
			"mDataProp" : "moneyOfassure",
			"sClass" : "center"
		},
		{
			"mDataProp" : "status",
			"sClass" : "center"
		},
		{
			"fnRender" : function(obj) {
				var id = obj.aData['id'];
				var householder = obj.aData['householder'];
				var html = "";
				html+='<a href="javaScript:addMembers('
					+ id
					+ ','+'\''+householder+'\''
					+')">加入成员</a>|'
					+'<div class="menu" style="margin-right:10px; position:relative;"><ul><li><a class="drop" href="javaScript:openMore('
					+ id
					+ ');">更多</a><ul id="ulList">'
					+'<li><a href="javaScript:editFamily('
					+ id
					+ ','+'\''+householder+'\''
					+')">编辑家庭</a></li>'
					+'<li><a href="javaScript:freezenFamily('
					+ id
					+ ','+'\''+householder+'\''
					+')">冻结家庭</a></li>'
					+'<li><a href="javaScript:deleteFamily('
					+ id
					+ ','+'\''+householder+'\''
					+')">删除家庭</a></li></ul><li></ul></div>';
					

				return html;
			},
			"sClass" : "center"
		} ];





var freezencolumns = [ // 设定各列宽度
                
        		{
        			"mDataProp" : "id",
        			"sClass" : "center"
        		},
        		{
        			"mDataProp" : "householder",
        			"sClass" : "center"
        		},
        		{
        			"mDataProp" : "homeAddress",
        			"sClass" : "center"
        		},
        		{
        			"mDataProp" : "homePhone",
        			"sClass" : "center"
        		},
        		{
        			"mDataProp" : "createdTime",
        			"sClass" : "center"
        		},
        		{
        			"mDataProp" : "moneyOfassure",
        			"sClass" : "center"
        		},
        		{
        			"mDataProp" : "status",
        			"sClass" : "center"
        		},
        		{
        			"fnRender" : function(obj) {
        				var id = obj.aData['id'];
        				var householder = obj.aData['householder'];
        				var html = "";
        				html+='<a href="javaScript:unFreezenFamily('
        					+ id
        					+ ','+'\''+householder+'\''
        					+')">解冻</a>';
        					

        				return html;
        			},
        			"sClass" : "center"
        		} ];









// 家庭列表
var familyTable = new PageDataTables({
	tableId : 'familyTable',
	ajaxUrl : contextPath + "/merchant/family/ajaxData.htm?t="
			+ new Date().getTime(),// 这个是请求的地址
	exportUrl : contextPath + "/merchant/family/exportToExcel.htm?",// 这个是请求的地址
	aoColumns : columns,
	addButton :[
	            {"outId":"addBtn","value":"新增"}          
	],
	beforeload : function() {
		this.paraData = {
			"householder" : jQuery('#householder').val() == '' ? '' : "%"+jQuery('#householder').val()+"%",
			"homePhone" : jQuery('#homePhone').val() == '' ? '' : "%"+jQuery('#homePhone').val()+"%",
			"homeAddress":jQuery('#homeAddress').val() == '' ? '' : "%"+jQuery('#homeAddress').val()+"%",
			"status":"正常",
			"no_aStatus":"注销"//account的状态不等于注销
		};
	}
});


//冻结家庭列表
var fronzenFamilyTable = new PageDataTables({
	tableId : 'fronzenFamilyTable',
	ajaxUrl : contextPath + "/merchant/family/ajaxData.htm?t="
			+ new Date().getTime(),// 这个是请求的地址
	exportUrl : contextPath + "/merchant/family/exportToExcel.htm?",// 这个是请求的地址
	aoColumns : freezencolumns,
	addButton :[
	            {"outId":"addBtn","value":"新增"}          
	],
	beforeload : function() {
		this.paraData = {
			"householder" : jQuery('#householder').val() == '' ? '' : "%"+jQuery('#householder').val()+"%",
			"homePhone" : jQuery('#homePhone').val() == '' ? '' : "%"+jQuery('#homePhone').val()+"%",
			"homeAddress":jQuery('#homeAddress').val() == '' ? '' : "%"+jQuery('#homeAddress').val()+"%",
			"status":"冻结",
			"no_aStatus":"注销"//account的状态不等于注销
		};
	}
});









//点击增加家庭成员按钮
var addMembers = function(id,householder) {
		var diag =new Dialog();
		diag.Width = 900;
		diag.Height = 500;
		diag.Title="新增成员";
		diag.URL=contextPath + "/merchant/family/editAddMember.htm?id="+id;
		diag.MessageTitle="户主："+householder;
		diag.OKEvent=function(){
			diag.innerFrame.contentWindow.submit(callBack);
		};
		diag.show();	
};

//点击编辑家庭按钮，修改家庭信息，
var editFamily = function(id,householder) {
		var diag =new Dialog();
		diag.Width = 1000;
		diag.Height = 700;
		diag.Title="编辑家庭";
		diag.URL=contextPath + "/merchant/family/editMember.htm?id="+id;
		diag.MessageTitle="负责人："+householder;
		diag.OKEvent=function(){
			diag.innerFrame.contentWindow.submit(callBack);
		};
		diag.show();	
};


//点击冻结家庭按钮，给扁家庭的状态为冻结
var freezenFamily = function(id,householder) {
	Dialog.confirm("确认冻结此家庭", function(){
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/merchant/family/freezenFamily.htm?',
			data : "id="+id,// 你的formid
			async : false,
			dataType : 'json',
			error : function(request) {
				Dialog.alert("提示：操作失败");
			},
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：操作成功",function(){
							location.reload();
					});	
				}
			}
		});
	});

};


//点击解冻家庭按钮，给扁家庭的状态为正长
var unFreezenFamily = function(id,householder) {
	Dialog.confirm("确认冻结此家庭", function(){
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/merchant/family/unFreezenFamily.htm?',
			data : "id="+id,// 你的formid
			async : false,
			dataType : 'json',
			error : function(request) {
				Dialog.alert("提示：操作失败");
			},
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：操作成功",function(){
							location.reload();
					});	
				}
			}
		});
	});

};

var deleteFamily = function(id,householder){
	Dialog.confirm("确认删除此家庭（包括所有的家庭成员）", function(){
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/merchant/family/deleteFamily.htm?',
			data : "id="+id,// 你的formid
			async : false,
			dataType : 'json',
			error : function(request) {
				Dialog.alert("提示：操作失败");
			},
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：操作成功",function(){
							location.reload();
					});	
				}
			}
		});
	});
};




//点击弹出新增家庭 按钮 可以上传照片，图片的url地址存到sys_files_urls中的url中。
var addFamily = function(){
	var diag =new Dialog();
	diag.Width = 800;
	diag.Height = 600;
	diag.Title="新增家庭";
	diag.URL=contextPath + "/merchant/family/add.htm";
	diag.MessageTitle="无";
	diag.OKEvent=function(){
		diag.innerFrame.contentWindow.submit(callBack);
	};
	diag.show();
};

















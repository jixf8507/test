jQuery(document).ready(
		function() {

			manufacturerCombox.requestData();

			jQuery("#repairDate").datepicker();

			jQuery("#form2").validate({
				rules : {
					'repairDate' : "required",
					'repairReason' : "required",
					'applyUser' : "required",
					'fee' : {
						"required" : true,
						"number" : true
					},
				}
			});

		});

var manufacturerCombox = new Combox({
	id : 'manufacturId',
	url : contextPath + '/car/manufacturer/ajaxManufacturer.htm?t='
			+ new Date().getTime(),
	cText : 'manufacturerName',
	cValue : 'id',
	emptyText : '请选择维修商',
	change : function() {

	}
});

var openDetailWin = function(id) {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 600;
	diag.Title = "资产领用详细信息";
	diag.MessageTitle = "单据号： " + id;
	diag.URL = contextPath + "/asset/use/detail.htm?id=" + id;
	diag.show();
};

// 获取资产列表
var getAssets = function() {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 500;
	diag.Title = "资产选择";
	diag.URL = contextPath + "/page/asset/assetWin.jsp?flag=&assetIds="
			+ jQuery('#assetIds').val();
	diag.MessageTitle = '选择资产';
	diag.Message = "";
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBackCheckbox);
		diag.close();
	};
	diag.show();
};

var callBackCheckbox = function(obj) {
	var arr = obj.split(',');
	var assetIdArray = new Array();
	var assetsNameArray = new Array();
	var modelArray = new Array();

	var assetsDetail = {
		assetId : assetIdArray,
		assetsName : assetsNameArray,
		model : modelArray
	};

	for (var i = 0; i < arr.length - 1; i++) {

		var arrr = arr[i].split('_');
		assetIdArray.push(arrr[0]);
		assetsNameArray.push(arrr[2]);
		modelArray.push(arrr[3]);
	}
	getAssetTable.createTable(assetsDetail);

	countId();
};

var getAssetTable = {
	num : 0,
	createTable : function(assetsDetail) {
		var assetId = assetsDetail['assetId'];
		var assetsName = assetsDetail['assetsName'];
		var model = assetsDetail['model'];

		for (var i = 1; i <= this.num; i++) {
			this.del(Number(i));
		}
		for (var i = 0; i < assetId.length; i++) {
			this.add(assetId[i], assetsName[i], model[i]);
		}
	},
	add : function(assetId, assetsName, model) {
		this.num++;
		var html = '<tr id="index_' + this.num + '">';
		html += '<td><input type="text" name="assetId" value="'
				+ assetId
				+ '" class="smallinput" style="width: 20px;" readonly="readonly"/></td>';
		html += '<td><input type="text" name="assetsName" value="'
				+ assetsName
				+ '" class="smallinput" style="width: 100px;" readonly="readonly"/></td>';

		html += '<td><input type="text" name="repairReasonDetail" class="smallinput" value="" style="width: 100px;" /></td>';
		html += '<td><input type="text" name="repairStatus" class="smallinput" value="" style="width: 100px;" /></td>';
		html += '<td><input type="text" name="feeDetail" class="smallinput" value="" style="width: 100px;" /></td>';
		html += '<td><input type="text" name="accessories" class="smallinput" value="" style="width: 100px;"  /></td>';

		html += '<td><input type="button" value="删除" onclick="getAssetTable.del('
				+ this.num + ')"/></td>';
		html += '</tr>';
		jQuery("#getAssetTable").append(html);
	},
	del : function(id) {
		jQuery("tr[id='index_" + id + "']").remove(); // 删除当前行
		countId();
	}
};

function countId() {
	var obj = document.getElementsByName('assetId');
	var s = '';
	for (var i = 0; i < obj.length; i++) {
		s += obj[i].value + ',';
	}
	jQuery('#assetIds').val(s);
}


/**
 * 提交form表单
 */
var submit = function(callBackAdd) {
	if (jQuery("#manufacturId").val() == '') {
		Dialog.alert("提示：请选择维修商");
		return false;
	}
	if (jQuery("#assetIds").val() == '') {
		Dialog.alert("提示：请添加维修资产内容");
		return false;
	}

	var repairReason = document
			.getElementsByName('repairReasonDetail');
	var repairStatus = document
			.getElementsByName('repairStatus');
	var fee = document.getElementsByName('feeDetail');
	var accessories = document
			.getElementsByName('accessories');
	for (var i = 0; i < repairReason.length; i++) {
		if (jQuery(repairReason[i]).val() == ''
				|| jQuery(repairStatus[i]).val() == ''
				|| jQuery(fee[i]).val() == ''
				|| jQuery(accessories[i]).val() == '') {
			Dialog.alert("提示：请填写维修详细信息");
			return false;
		}
	}
	if (jQuery("#form2").valid()) {
		
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/asset/repair/saveAdd.htm?',
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
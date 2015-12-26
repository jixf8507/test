jQuery(document).ready(function() {

	// getAssetTable.createTable(assetsDetail);

	siteCombox.requestData();

	jQuery("#useDate").datepicker();

	jQuery("#form2").validate({
		rules : {
			'useDate' : "required",
			'userName' : "required",
		}
	});

});

var siteCombox = new Combox({
	id : 'siteId',
	url : contextPath + '/merchant/site/ajaxSites.htm?t='
			+ new Date().getTime(),
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择租赁点',
	beforeload : function() {

	},
	change : function() {

	}
});

// 获取员工列表
var getMerchantUser = function() {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 500;
	diag.Title = "领用人选择";
	diag.URL = contextPath + "/page/merchant/userWin.jsp";
	diag.MessageTitle = '选择领用人';
	diag.Message = "";
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
		diag.close();
	};
	diag.show();
};

var callBack = function(obj) {
	var arr = obj.split('_');
	jQuery("#userId").attr("value", arr[0]);
	jQuery("#userName").attr("value", arr[1]);
};

// 获取资产列表
var getAssets = function() {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 500;
	diag.Title = "资产选择";
	diag.URL = contextPath + "/page/asset/assetWin.jsp?flag=闲置&assetIds="
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
	var nameArray = new Array();
	var assetsNameArray = new Array();
	var modelArray = new Array();

	var assetsDetail = {
		assetId : assetIdArray,
		name : nameArray,
		assetsName : assetsNameArray,
		model : modelArray
	};

	for (var i = 0; i < arr.length - 1; i++) {

		var arrr = arr[i].split('_');
		assetIdArray.push(arrr[0]);
		nameArray.push(arrr[1]);
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
		var name = assetsDetail['name'];
		var assetsName = assetsDetail['assetsName'];
		var model = assetsDetail['model'];

		for (var i = 1; i <= this.num; i++) {
			this.del(Number(i));
		}
		for (var i = 0; i < assetId.length; i++) {
			this.add(assetId[i], name[i], assetsName[i], model[i]);
		}
	},
	add : function(assetId, name, assetsName, model) {
		this.num++;
		var html = '<tr id="index_' + this.num + '">';
		html += '<td><input type="text" name="assetId" value="'
				+ assetId
				+ '" class="smallinput" style="width: 100px;" readonly="readonly"/></td>';
		html += '<td><input type="text" name="name" value="'
				+ name
				+ '" class="smallinput" style="width: 150px;" readonly="readonly"/></td>';
		html += '<td><input type="text" name="assetsName" value="'
				+ assetsName
				+ '" class="smallinput" style="width: 150px;" readonly="readonly"/></td>';
		html += '<td><input type="text" name="model" class="smallinput" value="'
				+ model + '" style="width: 150px;"  readonly="readonly"/></td>';

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
	if (jQuery("#siteId").val() == '') {
		Dialog.alert("提示：请选择领入点");
		return false;
	}
	if (jQuery("#assetIds").val() == '') {
		Dialog.alert("提示：请添加领用资产内容");
		return false;
	}
	if (jQuery("#form2").valid()) {
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/asset/use/saveAdd.htm?',
			data : jQuery('#form2').serialize(),
			async : false,
			dataType : 'json',
			error : function(request) {
				Dialog.alert("错误：操作失败");
			},
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：操作成功", function() {
						callBackAdd();
					});

				} else {
					Dialog.alert("提示：操作失败");
				}
			}
		});
	}
};
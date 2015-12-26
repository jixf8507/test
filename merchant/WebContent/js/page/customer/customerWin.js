jQuery(document).ready(function() {

	customerTable.reloadData();
	if (jQuery('#tableName').val() == 'month_lease_price_type') {
		unitCustomerTable.reloadData();
	}

	jQuery('#queryBtn,#normalDiv').click(function() {
		customerTable.bStateSave = false;
		customerTable.reloadData();
	});

	jQuery('#unitQueryBtn,#unitDiv').click(function() {
		unitCustomerTable.bStateSave = false;
		unitCustomerTable.reloadData();
	});

	jQuery('#queryBtn1,#enterpriseDiv').click(function() {
		enterpriseTable.bStateSave = false;
		enterpriseTable.reloadData();
	});

	jQuery('#queryBtn2,#familyDiv').click(function() {
		familyTable.bStateSave = false;
		familyTable.reloadData();
	});

});

var customerTable = new PageDataTables(
		{
			tableId : 'customerTable',
			iDisplayLength : 6,
			// bPaginate:false,
			ajaxUrl : contextPath + "/customer/customer/ajaxData.htm?t="
					+ new Date().getTime(),
			exportUrl : contextPath + "/customer/customer/exportToExcel.htm?",
			aoColumns : [
					{
						"fnRender" : function(obj) {
							var id = obj.aData['id'];
							return '<input style="width: 20px;" type="radio" name="id" id="'
									+ id
									+ '" value="'
									+ 1
									+ '_'
									+ obj.aData['id']
									+ '_'
									+ obj.aData['accountId']
									+ '_'
									+ obj.aData['cardNO']
									+ '_'
									+ obj.aData['name']
									+ '_'
									+ obj.aData['phone']
									+ '_'
									+ obj.aData['idCard']
									+ '_'
									+ obj.aData['moneyOfassure']
									+ '_'
									+ obj.aData['balance']
									+ '_'
									+ obj.aData['cmoneyOfassure'] + '" >';
						},
						"sClass" : "center"
					}, {
						"mDataProp" : "phone",
						"sClass" : "center"
					}, {
						"mDataProp" : "name",
						"sClass" : "center"
					}, {
						"mDataProp" : "idCard",
						"sClass" : "center"
					}, {
						"mDataProp" : "moneyOfassure",
						"sClass" : "center"
					}, {
						"mDataProp" : "balance",
						"sClass" : "center"
					} ],
			beforeload : function() {
				this.paraData = {
					"idCard" : jQuery('#idCard').val() == '' ? '' : '%'
							+ jQuery('#idCard').val() + "%",
					"phone" : jQuery('#phone').val() == '' ? '' : '%'
							+ jQuery('#phone').val() + "%",
					"name" : jQuery('#name').val() == '' ? '' : '%'
							+ jQuery('#name').val() + "%",
					"status" : '正常',
					"noUse" : jQuery('#noUse').val()
				};
			}
		});

var unitCustomerTable = new PageDataTables(
		{
			tableId : 'unitCustomerTable',
			iDisplayLength : 6,
			// bPaginate:false,
			ajaxUrl : contextPath + "/customer/unit/ajaxData.htm?t="
					+ new Date().getTime(),
			aoColumns : [
					{
						"fnRender" : function(obj) {
							var id = obj.aData['id'];
							return '<input style="width: 20px;" type="radio" name="id" id="'
									+ id
									+ '" value="'
									+ ''
									+ '_'
									+ obj.aData['id']
									+ '_'
									+ -1
									+ '_'
									+ ''
									+ '_'
									+ obj.aData['enterpriseName']
									+ '_'
									+ obj.aData['contactPhone']
									+ '_'
									+ ''
									+ '_'
									+ obj.aData['moneyOfassure']
									+ '_'
									+ obj.aData['balance'] + '" >';
						},
						"sClass" : "center"
					}, {
						"mDataProp" : "enterpriseName",
						"sClass" : "center"
					}, {
						"mDataProp" : "contactPerson",
						"sClass" : "center"
					}, {
						"mDataProp" : "contactPhone",
						"sClass" : "center"
					}, {
						"mDataProp" : "balance",
						"sClass" : "center"
					}, {
						"mDataProp" : "moneyOfassure",
						"sClass" : "center"
					} ],
			beforeload : function() {
				this.paraData = {
					"enterpriseName" : jQuery('#enterpriseName').val() == '' ? ''
							: '%' + jQuery('#enterpriseName').val() + "%",
					"contactPerson" : jQuery('#contactPerson').val() == '' ? ''
							: '%' + jQuery('#contactPerson').val() + "%",
					"contactPhone" : jQuery('#contactPhone').val() == '' ? ''
							: '%' + jQuery('#contactPhone').val() + "%",
					"status" : '正常',
					"noUse" : jQuery('#noUse').val()
				};
			}
		});

var enterpriseTable = new PageDataTables(
		{
			tableId : 'enterpriseTable',
			iDisplayLength : 6,
			ajaxUrl : contextPath + "/customer/unit/ajaxCustomerData.htm?t="
					+ new Date().getTime(),
			aoColumns : [
					{
						"fnRender" : function(obj) {
							var id = obj.aData['id'];
							return '<input style="width: 20px;" type="radio" name="id" id="'
									+ id
									+ '" value="'
									+ 2
									+ '_'
									+ obj.aData['id']
									+ '_'
									+ obj.aData['accountId']
									+ '_'
									+ obj.aData['cardNO']
									+ '_'
									+ obj.aData['name']
									+ '_'
									+ obj.aData['phone']
									+ '_'
									+ obj.aData['idCard']
									+ '_'
									+ obj.aData['moneyOfassure']
									+ '_'
									+ obj.aData['balance'] + '" >';
						},
						"sClass" : "center"
					}, {
						"mDataProp" : "phone",
						"sClass" : "center"
					}, {
						"mDataProp" : "name",
						"sClass" : "center"
					}, {
						"mDataProp" : "idCard",
						"sClass" : "center"
					}, {
						"mDataProp" : "moneyOfassure",
						"sClass" : "center"
					}, {
						"mDataProp" : "balance",
						"sClass" : "center"
					}, {
						"mDataProp" : "enterpriseName",
						"sClass" : "center"
					} ],
			beforeload : function() {
				this.paraData = {
					"idCard" : jQuery('#idCard1').val() == '' ? '' : '%'
							+ jQuery('#idCard1').val() + "%",
					"phone" : jQuery('#phone1').val() == '' ? '' : '%'
							+ jQuery('#phone1').val() + "%",
					"name" : jQuery('#name1').val() == '' ? '' : '%'
							+ jQuery('#name1').val() + "%",
					"status" : '正常',// 企业状态
					"aStatus" : '正常',// 客户状态
					"noUse" : jQuery('#noUse').val()
				};
			}
		});

var familyTable = new PageDataTables(
		{
			tableId : 'familyTable',
			iDisplayLength : 6,
			ajaxUrl : contextPath + "/merchant/family/ajaxCustomerData.htm?t="
					+ new Date().getTime(),
			aoColumns : [
					{
						"fnRender" : function(obj) {
							var id = obj.aData['id'];
							return '<input style="width: 20px;" type="radio" name="id" id="'
									+ id
									+ '" value="'
									+ 3
									+ '_'
									+ obj.aData['id']
									+ '_'
									+ obj.aData['accountId']
									+ '_'
									+ obj.aData['cardNO']
									+ '_'
									+ obj.aData['name']
									+ '_'
									+ obj.aData['phone']
									+ '_'
									+ obj.aData['idCard']
									+ '_'
									+ obj.aData['familyMoneyOfassure']
									+ '_'
									+ obj.aData['balance']
									+ '_'
									+ obj.aData['cmoneyOfassure'] + '" >';
						},
						"sClass" : "center"
					}, {
						"mDataProp" : "phone",
						"sClass" : "center"
					}, {
						"mDataProp" : "name",
						"sClass" : "center"
					}, {
						"mDataProp" : "idCard",
						"sClass" : "center"
					}, {
						"mDataProp" : "moneyOfassure",
						"sClass" : "center"
					}, {
						"mDataProp" : "balance",
						"sClass" : "center"
					}, {
						"mDataProp" : "familyName",
						"sClass" : "center"
					}, {
						"mDataProp" : "familyMoneyOfassure",
						"sClass" : "center"
					} ],
			beforeload : function() {
				this.paraData = {
					"idCard" : jQuery('#idCard2').val() == '' ? '' : '%'
							+ jQuery('#idCard2').val() + "%",
					"phone" : jQuery('#phone2').val() == '' ? '' : '%'
							+ jQuery('#phone2').val() + "%",
					"name" : jQuery('#name2').val() == '' ? '' : '%'
							+ jQuery('#name2').val() + "%",
					"status" : '正常',// 家庭状态
					"aStatus" : '正常',// 客户状态
					"aaNotStatus" : '注销',
					"noUse" : jQuery('#noUse').val()
				};
			}
		});

/**
 * 提交form表单
 */
var submit = function(callBack) {
	var selId = '';
	jQuery("input[name=id]").each(function() {
		if (this.checked) {
			selId = jQuery(this).val();
		}
	});
	var tableName = jQuery('#tableName').val();
	var type = selId.split('_')[0];
	var moneyOfassure = selId.split('_')[7];
	var cmoneyOfassure = selId.split('_')[9];

	if (tableName != '' && type == 1 || tableName != '' && type == 3) {
		// 判断保证金
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/order/getApply/moneyOfassure.htm?',
			data : "moneyOfassure=" + moneyOfassure + "&cmoneyOfassure="
					+ cmoneyOfassure,
			async : false,
			dataType : 'json',
			error : function(request) {
				Dialog.alert("错误：获取保证金失败");
			},
			success : function(data) {
				if (data.success) {
					callBack(selId);
				} else {
					var msg = data.info;
					if (type == 3) {
						msg = "户主" + msg;
					}
					callBack(msg);
				}
			}
		});
	} else {
		callBack(selId);
	}
};

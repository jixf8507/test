jQuery(document).ready(function() {

	jQuery('.chzn-select').chosen();

	couponTable.bStateSave = false;
	couponTable.reloadData();

	jQuery('#useQueryBtn').click(function() {
		couponTable.bStateSave = false;
		couponTable.reloadData();
	});
	jQuery('#status').change(function() {
		couponTable.bStateSave = false;
		couponTable.reloadData();
	});

});

var couponTable = new PageDataTables(
		{
			tableId : 'couponTable',
			ajaxUrl : contextPath + "/merchant/coupon/ajaxData.htm?t="
					+ new Date().getTime(),
			aoColumns : [
					{
						"fnRender" : function(obj) {
							var id = obj.aData['id'];
							var couponNo = obj.aData['couponNo'];
							var balance = obj.aData['balance'];
							var couponType = obj.aData['couponType'];
							return '<input style="width: 20px;" type="radio" name="id" id="'
									+ id
									+ '" value="'
									+ id
									+ '_'
									+ couponNo
									+ '_' + balance + '_' + couponType + '" >';
						},
						"sClass" : "center"
					},/*
						 * { "mDataProp" : "couponNo", "sClass" : "center" },
						 */{
						"fnRender" : function(obj) {
							var id = obj.aData['id'];
							return 'ykzc-' + id;
						},
						"sClass" : "center"
					}, {
						"mDataProp" : "couponName",
						"sClass" : "center"
					}, {
						"mDataProp" : "balance",
						"sClass" : "center"
					}, {
						"mDataProp" : "toDate",
						"sClass" : "center"
					}, {
						"fnRender" : function(obj) {
							var couponType = obj.aData['couponType'];
							if(couponType==1){
								return '现金优惠券';
							}else{
								return '里程优惠券';
							}
							 
						},
						"sClass" : "center"
					}, {
						"mDataProp" : "status",
						"sClass" : "center"
					} ],
			beforeload : function() {
				this.paraData = {
					"id" : jQuery('#id').val() == '' ? ''
							: Number(jQuery('#id').val()),
					"couponNo" : jQuery('#couponNo').val() == '' ? '' : '%'
							+ jQuery('#couponNo').val() + '%',
					"status" : '待用'
				};
			}
		});

/**
 * 提交form表单
 */
var submit = function(callBackSave) {
	var selId = '';
	jQuery("input[name=id]").each(function() {
		if (this.checked) {
			selId = jQuery(this).val();
		}
	});
	callBackSave(selId);
};
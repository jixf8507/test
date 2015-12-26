jQuery(document).ready(function() {

	merchantUserTable.reloadData();
	jQuery('#rights').chosen();
	siteCombox.requestData();

	jQuery('#queryBtn').click(function() {
		merchantUserTable.reloadData();
	});
	jQuery('#rights').change(function() {
		merchantUserTable.reloadData();
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
		merchantUserTable.reloadData();
}
});

var merchantUserTable = new PageDataTables({
	tableId : 'merchantUserTable',
	iDisplayLength : 6,
	// bPaginate:false,
	ajaxUrl : contextPath + "/merchant/user/ajaxData.htm?t="
			+ new Date().getTime(), 
//	exportUrl : contextPath + "/merchant/user/exportToExcel.htm?", 
	aoColumns : [{
	    			"fnRender" : function(obj) {
	    				var id = obj.aData['id'];
	    				return '<input style="width: 20px;" type="radio" name="id" id="'
	    						+ id
	    						+ '" value="'
	    						+ obj.aData['id']
	    						+ '_'
	    						+ obj.aData['userName']
	    						+ '_'
	    						+ obj.aData['mobilePhone'] + '" >';
	    			},
	    			"sClass" : "center"
	    		}, {
	    			"mDataProp" : "userName", 
	    			"sClass" : "center"
	    		},{
	    			"mDataProp" : "email",
	    			"sClass" : "center"
	    		},  {
	    			"mDataProp" : "mobilePhone",
	    			"sClass" : "center"
	    		}, {
	    			"mDataProp" : "siteName",
	    			"sClass" : "center"
	    		}, {
	    			"mDataProp" : "rights",
	    			"sClass" : "center"
	    		} ],
	beforeload : function() {
		this.paraData = {
			"userName" : jQuery('#userName').val() == '' ? '' : '%'
					+ jQuery('#userName').val() + "%",
			"mobilePhone" : jQuery('#mobilePhone').val() == '' ? '' : '%'
					+ jQuery('#mobilePhone').val() + "%",
			"right" : jQuery('#rights').val(),		
			"siteId" : jQuery('#siteId').val()
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
	callBack(selId);
};

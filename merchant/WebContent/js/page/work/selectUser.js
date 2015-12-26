jQuery(document).ready(function() {
	workUserTable.reloadData();

	jQuery(".chzn-select").chosen();
	// 点击查询按钮事件
	jQuery('#workQueryBtn').click(function() {
		workUserTable.reloadData();
	});
	jQuery('#selectRights').change(function() {
		workUserTable.reloadData();
	});
});

var workUserTable = new PageDataTables(
		{
			tableId : 'workUserTable',
			ajaxUrl : contextPath + "/work/manage/userajaxData.htm?t="
					+ new Date().getTime(),
			aoColumns : [
					{
						"fnRender" : function(obj) {
							var id = obj.aData['id'];
							return '<input style="width: 20px;" type="radio" name="customerId" id="'
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
						/* "mDataProp" : "idCard", */
						"fnRender" : function(obj) {
							var email = obj.aData['email'];
							var id = obj.aData['id'];
							return '<span name="email" value="' + email
									+ '" id="email' + id + '" >' + email
									+ '</span>';
						},
						"sClass" : "center"
					},
					{
						/* "mDataProp" : "idCard", */
						"fnRender" : function(obj) {
							var userName = obj.aData['userName'];
							var id = obj.aData['id'];
							return '<span name="userName" value="' + userName
									+ '" id="userName' + id + '" >' + userName
									+ '</span>';
						},
						"sClass" : "center"
					},
					{
						/* "mDataProp" : "idCard", */
						"fnRender" : function(obj) {
							var mobilePhone = obj.aData['mobilePhone'];
							var id = obj.aData['id'];
							return '<span name="mobilePhone" value="'
									+ mobilePhone + '" id="mobilePhone' + id
									+ '" >' + mobilePhone + '</span>';
						},
						"sClass" : "center"
					},
					{
						/* "mDataProp" : "idCard", */
						"fnRender" : function(obj) {
							var siteName = obj.aData['siteName'];
							var id = obj.aData['id'];
							return '<span name="siteName" value="' + siteName
									+ '" id="siteName' + id + '" >' + siteName
									+ '</span>';
						},
						"sClass" : "center"
					},
					{
						/* "mDataProp" : "idCard", */
						"fnRender" : function(obj) {
							var rights = obj.aData['rights'];
							var id = obj.aData['id'];
							return '<span name="rights" value="' + rights
									+ '" id="rights' + id + '" >' + rights
									+ '</span>';
						},
						"sClass" : "center"
					},
					{
						/* "mDataProp" : "idCard", */
						"fnRender" : function(obj) {
							var status = obj.aData['status'];
							var id = obj.aData['id'];
							return '<span name="status" value="' + status
									+ '" id="status' + id + '" >' + status
									+ '</span>';
						},
						"sClass" : "center"
					} ],
			beforeload : function() {
				this.paraData = {
					"email" : jQuery('#workEmail').val() == '' ? '' : '%'
							+ jQuery('#workEmail').val() + "%",
					"userName" : jQuery('#workName').val() == '' ? '' : '%'
							+ jQuery('#workName').val() + "%",
					"mobilePhone" : jQuery('#workPhone').val() == '' ? '' : '%'
							+ jQuery('#workPhone').val() + "%",
					"rights" : jQuery('#selectRights').val()
				};
			}
		});

/**
 * 提交form表单
 */
var submit = function(callback) {
	var id = jQuery("input:[name=customerId]:checked").val();
	var userName = jQuery("#userName" + id).text();
	var mobilePhone = jQuery("#mobilePhone" + id).text();
	if (id == "" || id == undefined) {
		Dialog.alert("提示：请选择负责人");
		return false;
	}
	callback(userName, id,mobilePhone);
};

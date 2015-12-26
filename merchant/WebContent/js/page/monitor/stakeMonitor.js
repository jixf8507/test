jQuery(document).ready(function() {

	sitesTable.reloadData();

	// 点击查询按钮事件
	jQuery('#queryBtn').click(function() {
		sitesTable.bStateSave = false;
		sitesTable.reloadData();
	});

	jQuery('#mapsDiv').click(function() {
		requstSites();
	});

});

// 充电站列表
var sitesTable = new PageDataTables(
		{
			tableId : 'sitesTable',
			ajaxUrl : contextPath + "/merchant/site/ajaxData.htm?t="
					+ new Date().getTime(),
			aoColumns : [
					{
						"fnRender" : function(obj) {
							return '<img src="'
									+ contextPath
									+ obj.aData['imgUrl']
									+ '" onError="imgError(this)" alt="图片" width="50px;" height="50px;" />';
						},
						"sClass" : "center"
					},
					{
						"mDataProp" : "siteName",
						"sClass" : "center"
					},
					{
						"mDataProp" : "address",
						"sClass" : "center"
					},
					{
						"mDataProp" : "principal",
						"sClass" : "center"
					},
					{
						"mDataProp" : "phone",
						"sClass" : "center"
					},
					{
						"mDataProp" : "stakeCount",
						"sClass" : "center"
					},
					{
						"fnRender" : function(obj) {
							var type = obj.aData['type'];
							var typeName = '';
							if ((type & 1) == 1) {
								typeName += '租赁点，';
							}
							if ((type & 2) == 2) {
								typeName += '充电站，';
							}
							if ((type & 4) == 4) {
								typeName += '停车场，';
							}
							return typeName;
						},
						"sClass" : "center"
					},
					{
						"fnRender" : function(obj) {
							var id = obj.aData['id'];
							return '<a href='
									+ contextPath
									+ '/page/monitor/stakeMonitorWin.jsp?siteId='
									+ id + ' target="_blank">监控</a>';
						},
						"sClass" : "center"
					} ],
			beforeload : function() {
				this.paraData = {
					"province" : jQuery('#province').val(),
					"city" : jQuery('#city').val(),
					"siteName" : jQuery('#querySiteName').val() == '' ? ''
							: '%' + jQuery('#querySiteName').val() + '%',
					"type" : 2

				};
			}
		});

var requstSites = function() {
	var paraData = {
		"type" : 2
	};
	jQuery.ajax({
		type : "POST",
		url : contextPath + '/merchant/site/ajaxSites.htm?t='
				+ new Date().getTime(),
		data : 'paraData=' + encodeURI(JSON.stringify(paraData)),
		async : true,
		dataType : 'json',
		success : function(data) {
			// 初始化地图
			stakeMap.init(data);
		},
		error : function() {

		}
	});
};

var imgError = function(thiz) {
	thiz.src = contextPath + '/img/car.png';
};
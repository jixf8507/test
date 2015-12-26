jQuery(document).ready(function() {

	useSitesCombox.requestData();
	waitSitesCombox.requestData();
	faultSitesCombox.requestData();
	chargeSitesCombox.requestData();

	useCarsTable.reloadData();

	jQuery('#orderType').chosen();
	
	// 点击使用车辆查询按钮
	jQuery('#useQueryBtn,#useDiv').click(function() {
		useCarsTable.bStateSave=false; 
		useCarsTable.reloadData();
	});
	jQuery('#orderType').change(function() {
		useCarsTable.bStateSave=false; 
		useCarsTable.reloadData();
	});

	// 点击查询按钮事件
	jQuery('#waitQueryBtn,#waitDiv').click(function() {
		waitCarsTable.bStateSave=false; 
		waitCarsTable.reloadData();
	});

	// 点击查询按钮事件
	jQuery('#faultQueryBtn,#faultDiv').click(function() {
		faultCarsTable.bStateSave=false; 
		faultCarsTable.reloadData();
	});

	// 点击查询按钮事件
	jQuery('#chargeQueryBtn,#chargeDiv').click(function() {
		chargeCarsTable.bStateSave=false; 
		chargeCarsTable.reloadData();
	});

});

// 选择框
var useSitesCombox = new Combox({
	id : 'useCurSiteId',
	url : contextPath + '/merchant/site/ajaxSites.htm?t='
			+ new Date().getTime(),
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择租赁点',
	change : function() {
		useCarsTable.bStateSave=false; 
		useCarsTable.reloadData();
	}
});

var waitSitesCombox = new Combox({
	id : 'waitCurSiteId',
	url : contextPath + '/merchant/site/ajaxSites.htm?t='
			+ new Date().getTime(),
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择租赁点',
	change : function() {
		waitCarsTable.bStateSave=false; 
		waitCarsTable.reloadData();
	}
});

var faultSitesCombox = new Combox({
	id : 'faultCurSiteId',
	url : contextPath + '/merchant/site/ajaxSites.htm?t='
			+ new Date().getTime(),
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择租赁点',
	change : function() {
		faultCarsTable.bStateSave=false; 
		faultCarsTable.reloadData();
	}
});

var chargeSitesCombox = new Combox({
	id : 'chargeCurSiteId',
	url : contextPath + '/merchant/site/ajaxSites.htm?t='
			+ new Date().getTime(),
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择租赁点',
	change : function() {
		chargeCarsTable.bStateSave=false; 
		chargeCarsTable.reloadData();
	}
});

var imgError = function(thiz) {
	thiz.src = contextPath + '/img/car.png';
};

// 车辆状态列表显示内容
var cloumn = [
		{
			"fnRender" : function(obj) {
				return '<img src="'
						+ contextPath
						+ obj.aData['littleIcon']
						+ '" onError="imgError(this)" alt="汽车图片" width="50px;" height="50px;" />';
			},
			"sClass" : "center"
		}, {
			"mDataProp" : "carNumber",
			"sClass" : "center"
		}, {
			"mDataProp" : "manufacturerName",
			"sClass" : "center"
		}, {
			"mDataProp" : "typeName",
			"sClass" : "center"
		}, {
			"mDataProp" : "siteName",
			"sClass" : "center"
		},{
			"fnRender" : function(obj) {
				var deviceNO = obj.aData['deviceNO'];
				if (deviceNO == 'null' || deviceNO == '') {
					return obj.aData['kms'];
				}else{
					return obj.aData['curKms'];
				}
			},
			"sClass" : "center"
		},
		{
			"fnRender" : function(obj) {
				var deviceNO = obj.aData['deviceNO'];
				if (deviceNO == 'null' || deviceNO == '') {
					return obj.aData['csurplusKms'];
				}else{
					return obj.aData['dsurplusKms'];
				}
			},
			"sClass" : "center"
		},{
			"mDataProp" : "status",
			"sClass" : "center"
		} ];

// 已经使用的车辆
var useCarsTable = new PageDataTables({
	tableId : 'useCarsTable',
	ajaxUrl : contextPath + "/car/car/ajaxData.htm?t=" + new Date().getTime(), 
	aoColumns : [
	     		{
	    			"fnRender" : function(obj) {
	    				return '<img src="'
	    						+ contextPath
	    						+ obj.aData['littleIcon']
	    						+ '" onError="imgError(this)" alt="汽车图片" width="50px;" height="50px;" />';
	    			},
	    			"sClass" : "center"
	    		}, {
	    			"mDataProp" : "carNumber",
	    			"sClass" : "center"
	    		}, {
	    			"mDataProp" : "manufacturerName",
	    			"sClass" : "center"
	    		}, {
	    			"mDataProp" : "typeName",
	    			"sClass" : "center"
	    		}, {
	    			"mDataProp" : "siteName",
	    			"sClass" : "center"
	    		}, {
					"fnRender" : function(obj) {
						var deviceNO = obj.aData['deviceNO'];
						if (deviceNO == 'null' || deviceNO == '') {
							return obj.aData['kms'];
						}else{
							return obj.aData['curKms'];
						}
					},
					"sClass" : "center"
				},
				{
					"fnRender" : function(obj) {
						var deviceNO = obj.aData['deviceNO'];
						if (deviceNO == 'null' || deviceNO == '') {
							return obj.aData['csurplusKms'];
						}else{
							return obj.aData['dsurplusKms'];
						}
					}, 
					"sClass" : "center"
				},{
	    			"mDataProp" : "status",
	    			"sClass" : "center"
	    		},
	    		{
	    			"fnRender" : function(obj) {
	    				var orderType = obj.aData['orderType'];
	    				if(orderType == 'null' || orderType == ''){
	    					return '';
	    				}
	    				if(orderType == 1){
	    					return '客户租车';
	    				}
	    				if(orderType == 2){
	    					return '企业租车';
	    				}
	    				if(orderType == 3){
	    					return '车辆调度';
	    				}
	    				if(orderType == 4){
	    					return '公务用车';
	    				}
	    			},
	    			"sClass" : "center"
	    		}],
	beforeload : function() {
		this.paraData = {
			"carNumber" : jQuery('#useCarNumber').val() == '' ? '' : '%'
					+ jQuery('#useCarNumber').val() + '%',
			"curSiteId" : jQuery('#useCurSiteId').val(),
			"orderType" : jQuery('#orderType').val(),
			"status" : '使用',
			"flag" : '正常'
		};
	}
});

// 空闲的车辆表格
var waitCarsTable = new PageDataTables({
	tableId : 'waitCarsTable',
	ajaxUrl : contextPath + "/car/car/ajaxCarData.htm?t=" + new Date().getTime(), 
	aoColumns : cloumn,
	beforeload : function() {
		this.paraData = {
			"carNumber" : jQuery('#waitCarNumber').val() == '' ? '' : '%'
					+ jQuery('#waitCarNumber').val() + '%',
			"curSiteId" : jQuery('#waitCurSiteId').val(),
			"status" : '空闲',
			"flag" : '正常'
		};
	}
});

// 故障的车辆列表
var faultCarsTable = new PageDataTables({
	tableId : 'faultCarsTable',
	ajaxUrl : contextPath + "/car/car/ajaxCarData.htm?t=" + new Date().getTime(), 
	aoColumns : cloumn,
	beforeload : function() {
		this.paraData = {
			"carNumber" : jQuery('#faultCarNumber').val() == '' ? '' : '%'
					+ jQuery('#faultCarNumber').val() + '%',
			"curSiteId" : jQuery('#faultCurSiteId').val(),
			"status" : '故障',
			"flag" : '正常'
		};
	}
});

// 充电的车辆
var chargeCarsTable = new PageDataTables({
	tableId : 'chargeCarsTable',
	ajaxUrl : contextPath + "/car/car/ajaxCarData.htm?t=" + new Date().getTime(), 
	aoColumns : cloumn,
	beforeload : function() {
		this.paraData = {
			"carNumber" : jQuery('#chargeCarNumber').val() == '' ? '' : '%'
					+ jQuery('#chargeCarNumber').val() + '%',
			"curSiteId" : jQuery('#chargeCurSiteId').val(),
			"status" : '充电',
			"flag" : '正常'
		};
	}
});

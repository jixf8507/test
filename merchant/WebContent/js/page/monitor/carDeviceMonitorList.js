jQuery(document).ready(function() {

//	provinceCombox.requestData();
//	cityCombox.requestData();

	sitesTable.reloadData();
	
	// 点击查询按钮事件
	jQuery('#queryBtn').click(function() {
		sitesTable.bStateSave=false; 
		sitesTable.reloadData();
	});

});

// 选择框
var provinceCombox = new Combox(
		{
			id : 'province',
			url : contextPath + '/merchant/site/ajaxProvinces.htm?t='
					+ new Date().getTime(),
			cText : 'proName',
			cValue : 'proName',
			emptyText : '请选择省',
			change : function() {
				jQuery("#cityDiv")
						.html(
								'<select name="city" id="city" style="width: 200px;"><option value="">请选择</option></select>');
				cityCombox.requestData();
				sitesTable.reloadData();
			}
		});

// 选择框
var cityCombox = new Combox({
	id : 'city',
	url : contextPath + '/merchant/site/ajaxCitys.htm?t='
			+ new Date().getTime(),
	cText : 'cityName',
	cValue : 'cityName',
	emptyText : '请选择市',
	beforeload : function() {
		this.paraData = {
			'pName' : jQuery('#province').val()
		};
	},
	change : function() {
		sitesTable.reloadData();
	}
});

var columns = [ // 设定各列宽度
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
		/*{
			"mDataProp" : "province",
			"sClass" : "center"
		},
		{
			"mDataProp" : "city",
			"sClass" : "center"
		},
		{
			"mDataProp" : "lng",
			"sClass" : "center"
		},
		{
			"mDataProp" : "lat",
			"sClass" : "center"
		},*/
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
				var siteName = obj.aData['siteName'];
				/*return '<a href="javaScript:openUpdateWin('
						+ id
						+ ',\''
						+ siteName
						+ '\')">编辑</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="javaScript:monitor('
						+ id + ')" >监控</a>';*/
				
				/**
				 * 要求改为直接跳转到那个页面。不通过弹出框加载
				 */
				/*return '<a href="javaScript:monitor('
						+ id + ')" >监控</a>';*/
				return '<a href='
					+ contextPath
					+ '/monitor/main/carDevice.htm?id='
					+ id + ' target="_blank">监控</a>';
			},
			"sClass" : "center"
		} ];

// 租赁点列表
var sitesTable = new PageDataTables({
	tableId : 'sitesTable',
	ajaxUrl : contextPath + "/merchant/site/ajaxDeviceMonitorList.htm?t="
			+ new Date().getTime(),// 这个是请求的地址
	exportUrl : contextPath + "/merchant/site/exportToExcel.htm?",// 这个是请求的地址
	aoColumns : columns,
	beforeload : function() {
		this.paraData = {
			"province" : jQuery('#province').val(),
			"city" : jQuery('#city').val(),
			"siteName" : jQuery('#querySiteName').val() == '' ? '' : '%'
					+ jQuery('#querySiteName').val() + '%'

		};
	}
});

// 打开编辑服务中心页面
var openUpdateWin = function(id, siteName) {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 600;
	diag.Title = "服务中心编辑";
	diag.URL = contextPath + "/merchant/site/edite.htm?id=" + id;
	diag.MessageTitle = siteName;
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	}; 
	diag.show();
};

//点击弹出新增服务中心按钮。
var addSite = function(){
	var diag =new Dialog();
	diag.Width = 800;
	diag.Height = 600;
	diag.Title="新增服务中心";
	diag.URL=contextPath + "/merchant/site/add.htm";
	diag.MessageTitle="无";
	diag.OKEvent=function(){
		diag.innerFrame.contentWindow.submit(callBack);
	};
	diag.show();
	
};

// 保存方法
var callBack = function() {
	location.reload();
};

// 点击删除按钮
var deleteFun = function(id) {
	Dialog.confirm('确认删除该租赁点吗?', function() {
		jQuery.ajax({
			type : "POST",
			url : contextPath + "/merchant/site/delete.htm?",
			data : "id=" + id,
			async : true,
			dataType : 'json',
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：删除成功",function(){
						location.reload(true);
					});
				} else {
					Dialog.alert("提示：该租赁点下有车辆，请先移除车辆。");
				}
			},
			error : function() {
				Dialog.alert("提示：删除失败,请重试");
			}
		});
	});
};

var monitor= function(id){
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 600;
	diag.Title = "车载设备监控";
	diag.URL = contextPath + "/monitor/main/carDevice.htm?id="+id;
	diag.MessageTitle = "车载设备监控";
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	}; 
	diag.show();	
};


var imgError = function(thiz) {
	thiz.src = contextPath + '/img/car.png';
};
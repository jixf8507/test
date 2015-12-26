jQuery(document).ready(function() {
	
	checkDetailTable.createTable(typeDetail);

	// /// FORM VALIDATION /////
	jQuery("#form1").validate({
		rules : {
			'typeName' : "required",
			'carManufacturerId' : "required",
			'seatCount' : {
				"required" : true,
				"number" : true
			},
			'color' : "required",
			'energy' : "required",
			'components' : "required",
			'statuss' : "required",
			'describes' : "required",
		}
	});	

	jQuery("#form1").submit(function() {
		if (!jQuery("#form1").valid()) {
			return false;
		}
		if (jQuery("#carManufacturerId").val() == '') {
			Dialog.alert("提示：请选择车辆供应商");
			return false;
		}
	});

	carManufacturerCombox.requestData();

	jQuery("#energy").chosen();
});

var typeDetail = {
	components : [ '车窗玻璃', '室内灯', '车灯', '车窗升降', '转向灯', '故障警示牌', '喇叭', '车灯开关',
			'雨刷器', '手刹', '刹车', '空调', '音响', '点烟器', '烟灰缸', '充电线', '车钥匙', '内饰',
			'方向盘', '座椅（外观调节）', '遮阳板（2块）', '反光镜', '灭火器', '倒车雷达', '安全带', '挡风玻璃',
			'车后视镜', '仪表盘', '车轮（前）', '车轮（后）' ],
	status : [],
	describe : []
};

var checkDetailTable = {
	num : 0,
	createTable : function(typeDetail) {
		var components = typeDetail['components'];
		for (var i = 0; i < components.length; i++) {
			this.add(components[i], '正常', '正常');
		}
	},
	add : function(component, status, describe) {
		this.num++;
		var html = '<tr id="index_' + this.num + '">';
		html += '<td><input type="text" name="components" value="' + component
				+ '" class="smallinput" style="width: 200px;"/></td>';
		html += '<td><input type="text" name="statuss" class="smallinput" value="'
				+ status + '" style="width: 200px;" /></td>';
		html += '<td><input type="text" name="describes" class="smallinput" value="'
				+ describe + '" style="width: 300px;" /></td>';
		html += '<td><input type="button" value="删除" onclick="checkDetailTable.del('
				+ this.num + ')"/></td>';
		html += '</tr>';
		jQuery("#checkDetailTable").append(html);
	},
	del : function(id) {
		jQuery("tr[id='index_" + id + "']").remove();// 删除当前行
	}
};

// 选择框
var carManufacturerCombox = new Combox({
	id : 'carManufacturerId',
	url : contextPath + '/car/manufacturer/ajaxManufacturer.htm?t='
			+ new Date().getTime(),
	cText : 'manufacturerName',
	cValue : 'id',
	emptyText : '请选择供应商',
	change : function() {

	}
});
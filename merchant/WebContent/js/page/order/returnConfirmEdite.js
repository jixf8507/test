jQuery(document).ready(function() {

	sitesCombox.requestData();

	jQuery.validator.addMethod("positiveNumber", function(value, element) {
		return value >= 0;
	}, "请输入正确数值");

	jQuery("#form1").validate({
		rules : {
			'components' : "required",
			'statuss' : "required",
			'describes' : "required",
			'kmsForReturn' : {
				"required" : true,
				"positiveNumber" : true
			},
			'surplusKmsForReturn' : {
				"required" : true,
				"positiveNumber" : true
			},
			'useCost' : {
				"required" : true,
				"number" : true
			},
			'otherCost' : {
				"required" : true,
				"number" : true
			},
			'maintenanceCost' : {
				"required" : true,
				"number" : true
			}
		}
	});
	countKmsCost();
	countCost();
	jQuery('#kmsForReturn').change(function() {
		countKmsCost();
		countCost();
	});
	
	if (jQuery('#customerType').val() == 2) {
		countUnitCost();
	}
	jQuery('#unitUseCost,#unitOtherCost,#maintenanceCost1').change(function() {
		countUnitCost();
	});

	jQuery('#otherCost,#maintenanceCost').change(function() {
		countCost();
	});

	jQuery('#carImgFile').change(function() {
		browseImg(this);
	});

	jQuery('#uploadBut').click(function() {
		uploadImg(this);
	});

	jQuery('#picDiv').click(function() {
		clickDiv();
	});

});

var countCost = function() {
	var otherCost = jQuery("#otherCost").val();
	var useCost = jQuery("#useCost").val();
	var perKmsCost = jQuery("#hidPerKmsCost").val();
	var maintenanceCost = jQuery("#maintenanceCost").val();
	jQuery("#totalCost").attr("value",
			(Number(otherCost) + Number(useCost) + Number(perKmsCost) + Number(maintenanceCost)));
};

var countUnitCost = function() {
	var unitOtherCost = jQuery("#unitOtherCost").val();
	var unitUseCost = jQuery("#unitUseCost").val();
	var maintenanceCost = jQuery("#maintenanceCost1").val();
	jQuery("#totalCost").attr("value",
			(Number(unitOtherCost) + Number(unitUseCost) + Number(maintenanceCost)));
};

var countKmsCost = function() {
	var kmsForReturn = jQuery('#kmsForReturn').val();
	kmsForReturn = kmsForReturn == '' || isNaN(kmsForReturn) ? 0 : kmsForReturn;
	jQuery.ajax({
		type : "POST",
		url : contextPath + '/order/returnConfirm/countKmsCost.htm?',
		data : "orderId=" + jQuery('#id').val() + "&kmsForReturn="
				+ kmsForReturn,
		async : false,
		dataType : 'json',
		error : function(request) {
			Dialog.alert("错误：请求失败");
		},
		success : function(data) {
			if (data.msg.success) {
				jQuery('#perKms').val(data.perKms);
				jQuery('#perKmsCost').val(data.perKmsCost);
				jQuery('#hidPerKmsCost').val(data.perKmsCost1);
				jQuery('#useCost').val(data.useCost);
				jQuery('#totalCost').val(data.totalCost);
			} else {
				Dialog.alert("提示：请求失败");
			}
		}

	});
};

function clickDiv() {
	var paraData = {
		"tableId" : jQuery('#tableId').val(),
		"tableName" : jQuery('#tableName').val(),
		"fileType" : jQuery('#fileType').val(),
//		"carId" : jQuery('#carId').val()
	};
	jQuery
			.ajax({
				cache : true,
				type : "POST",
				url : contextPath + '/system/upload/loadFile.htm?t='
						+ new Date().getTime(),
				data : 'paraData='
						+ encodeURI(encodeURI(JSON.stringify(paraData))),
				dataType : 'json',
				error : function(request) {
					Dialog.alert("错误：操作失败");
				},
				success : function(msg) {
					if (msg.files.length == 0) {

					} else {

						var pic = '';
						for (var i = 0; i < msg.files.length; i++) {
							pic += '<a href="javascript:;" class="item"><div class="img" b="'
									+ contextPath
									+ msg.files[i].fileUrl
									+ '" l="" theid="69"> <img src="'
									+ contextPath
									+ msg.files[i].fileUrl
									+ '" title="'
									+ msg.files[i].fileType
									+ '图片"/></div></a>';
						}
						jQuery('#small_imgs').html(pic);
						allHome();
					}
				}
			});
}

// 预约取车租赁点选择框
var sitesCombox = new Combox({
	id : 'relityReturnSiteId',
	url : contextPath + '/merchant/site/ajaxSites.htm?t='
			+ new Date().getTime(),
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择租赁点',
	beforeload : function() {
		this.emptyValue = jQuery("#hid_relityReturnSiteId").val();

	}
});

var submit = function(callBack) {
	// uploadImg();
	if (jQuery("#form1").valid()) {
	var kmsForReturn1 = jQuery('#kmsForReturn1').val();
	
	
	var kmsForReturn = jQuery('#kmsForReturn').val();
	
	var kmsReturn = jQuery('#kmsReturn').val();
	if (Number(kmsReturn) > Number(kmsForReturn)) {
		Dialog.alert("提示：还车码表数不能小于取车码表数");
		return false;
	}
	if (Number(kmsReturn) > Number(kmsForReturn1)) {
		Dialog.alert("提示：还车码表数不能小于取车码表数");
		return false;
	}
	if (jQuery('#relityReturnSiteId').val() == '') {
		Dialog.alert("提示：请选择还车租赁点");
		return false;
	}
	Dialog.confirm('确定收费正确，确定后不可以再修改', function() {
			jQuery.ajax({
				cache : true,
				type : "POST",
				url : contextPath + '/order/returnConfirm/saveEdite.htm?',
				data : jQuery('#form1').serialize(),
				async : false,
				dataType : 'json',
				/*beforeSend: function () {
					jQuery("#loading").show();
			    },*/
				error : function(request) {
					Dialog.alert("错误：操作失败");
				},
				success : function(data) {
					if (data.success) {
						Dialog.alert("提示：操作成功", function() {
							callBack();
						});
					} else {
						Dialog.alert("提示："+data.info);
					}
				}
			});
	});
	}
};

// 浏览 图片
var browseImg = function(thiz) {
	var filepath = jQuery(thiz).val();
	var extStart = filepath.lastIndexOf(".");
	var ext = filepath.substring(extStart, filepath.length).toUpperCase();
	if (filepath != "") {
		if (ext != ".PNG" && ext != ".JPG") {
			Dialog.alert("提示：只能上传png,jpg图片");
			jQuery(thiz).outerHTML = jQuery(thiz).outerHTML;
			return;
		}
		jQuery.ajaxFileUpload({
			url : contextPath + '/system/upload/imageUpload.htm?',
			type : 'post',
			secureuri : false,
			fileElementId : 'carImgFile',
			dataType : 'json',
			async : true,
			beforeSend : function() {

			},
			success : function(data, status) {
				jQuery('#fileUrl').val(data.data);
				jQuery('#fileName').val(data.fileName);
				jQuery('#small_imgs').html(
						'<a href="javascript:;" class="item"><div class="img" b="'
								+ contextPath + data.data
								+ '" l="" theid="69"> <img src="' + contextPath
								+ data.data + '" /></div></a>');
				allHome();
			},
			error : function() {
				Dialog.alert(e);
			}
		});
	} else {
		Dialog.alert("提示：请选择图片");
	}
};

// 上传 图片
var uploadImg = function(thiz) {
	jQuery.ajax({
		cache : true,
		type : "POST",
		url : contextPath + '/system/upload/browseImgUpload.htm?',
		data : jQuery('#imgForm').serialize(),
		async : false,
		dataType : 'json',
		error : function(request) {
			Dialog.alert("错误：上传失败");
		},
		success : function(data) {
			if (data.success) {
				Dialog.alert("提示：上传成功", function() {
					clickDiv();
					jQuery('#carImgFile').val("");
					jQuery('#fileUrl').val("");
				});
			} else {
				Dialog.alert("提示：" + data['info']);
			}
		}
	});
};

// 图片切换
function allHome() {
	jQuery('a').focus(function() {
		jQuery(this).blur();
	});
	changeImages(5);
	jQuery('#small_imgs a.item:first').find('div.img').addClass('active');
	var allimg = jQuery('#small_imgs .img');
	var big_img = jQuery('#big_img');
	var big_a = jQuery('#big_a');
	var small_next = jQuery('#small_next');
	var stop2 = false;
	allimg.mouseover(function() {
		if (jQuery(this).hasClass('active'))
			return;
		allimg.removeClass('active');
		jQuery(this).addClass('active');
		var img = jQuery(this).attr('b');
		var link = jQuery(this).attr('l');
		var theid = jQuery(this).attr('theid');
		if (big_img.queue('fx').length != 0) {
			big_img.stop(true);
		}
		big_img.animate({
			'opacity' : '0.2'
		}, 400, function() {
			big_img.css('background-image', 'url(' + img + ')');
			if (link == '') {
				big_a.attr('href', 'javascript:;').attr(
						'onClick',
						"pgvSendClick({hottag:'HRTENCENT.HOME.BANNER.BANNER"
								+ theid + "'});").addClass('cdefault');
			} else {
				big_a.attr('href', link).attr(
						'onClick',
						"pgvSendClick({hottag:'HRTENCENT.HOME.BANNER.BANNER"
								+ theid + "'});").removeClass('cdefault');
			}
			big_img.animate({
				'opacity' : '1'
			}, 700);
		});
	});
	allimg.eq(0).removeClass('active');
	allimg.eq(0).mouseover();
	jQuery('#home_banner').mouseover(function() {
		stop2 = true;
	}).mouseout(function() {
		stop2 = false;
	});

	var marquee2 = autoSwitchHomeBanner(allimg);
	MyMar2 = setInterval(marquee2, 6000);
	function autoSwitchHomeBanner(allimg) {
		return (function() {
			if (stop2)
				return;
			var index = 0;
			jQuery('#small_imgs .img').each(function(i) {
				if (jQuery(this).hasClass('active')) {
					index = i;
					return false;
				}
			});
			if (index >= 4) {
				small_next.click();
			}
			var next = jQuery('#small_imgs .img.active:first').parent().next()
					.find('.img');
			if (next.length == 0) {
				next = allimg.eq(0);
			}
			next.mouseover();
			stop2 = false;
		});
	}

}

function changeImages(allowl) {
	var itemall = jQuery('#small_imgs .item');
	iteml = itemall.length;
	if (iteml <= allowl) {
		jQuery('#small_pre,#small_next').css('background', 'none');
		return;
	}
	iteml = ((iteml - allowl) > allowl) ? allowl : (iteml - allowl);
	imagesSwitch33('#small_pre', '#small_next', itemall, 900, iteml);
}

function imagesSwitch33(left, right, items, movetime, num) {
	movetime = (parseInt(movetime)) ? movetime : 400;
	items.parent().css({
		position : 'relative',
		overflow : 'hidden'
	});
	items.parent().wrapInner('<div></div>');
	items.parent().css('position', 'absolute');
	items.parent().css('left', '0');
	var offw = items.eq(0).outerWidth(true);
	var allw = items.outerWidth(true) * (items.length);
	var movew = offw * num;
	items.parent().width(allw + 'px');
	var len = items.length;
	var isclick = false;

	jQuery(left).click(
			function() {
				if (items.parent().queue('fx').length != 0)
					return;
				isclick = true;
				items.parent().prepend(
						items.parent().children().slice(len - num, len));
				items.parent().css('left', '-' + movew + 'px');
				items.parent().animate({
					left : '+=' + movew + 'px'
				}, movetime, function() {
					items.parent().css('left', 0);
				});
			});
	jQuery(right).click(function() {
		if (items.parent().queue('fx').length != 0)
			return;
		isclick = true;
		items.parent().animate({
			left : '-=' + movew + 'px'
		}, movetime, function() {
			items.parent().append(items.parent().children().slice(0, num));
			items.parent().css('left', 0);
		});
	});
}

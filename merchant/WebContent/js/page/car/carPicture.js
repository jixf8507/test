jQuery(document).ready(function() {

	// jQuery('input:file').uniform();

	clickDiv();

	jQuery('#carImgFile').change(function() {
		browseImg(this);
	});

	jQuery('#uploadBut').click(function() {
		uploadImg(this);
	});

	jQuery('#newUploadBut').click(function() {
		newUploadImg(jQuery('#carNumber').val(), jQuery('#carId').val());
	});

});

function clickDiv() {
	var paraData = {
		"carId" : jQuery('#carId').val()
	};
	jQuery
			.ajax({
				cache : true,
				type : "POST",
				url : contextPath + '/system/upload/loadCarImgFile.htm?t='
						+ new Date().getTime(),
				data : 'paraData='
						+ encodeURI(encodeURI(JSON.stringify(paraData))),
				dataType : 'json',
				error : function(request) {
					Dialog.alert("提示：操作失败");
				},
				success : function(msg) {

					if (msg.files.length == 0) {
						jQuery('#small_imgs').html('');
						allHome();
					} else {
						var pic = '';
						for (var i = 0; i < msg.files.length; i++) {
							pic += '<a href="javascript:;" class="item"><div class="img" b="'
									+ contextPath
									+ msg.files[i].fileUrl
									+ '" l="" theid="69"><img class="del'
									+ msg.files[i].id
									+ '" src="../../images/trash.png" style="width:15px;height:15px;position:absolute;margin-left:100px;top:10px;display:none;z-index:999;" title="删除图片" onmouseover="over(\''
									+ msg.files[i].id
									+ '\');" onmouseout="out(\''
									+ msg.files[i].id
									+ '\');" onclick="delPic(\''
									+ msg.files[i].id
									+ '\');"/><img src="'
									+ contextPath
									+ msg.files[i].fileUrl
									+ '" class="img'
									+ msg.files[i].id
									+ '" onmouseover="over(\''
									+ msg.files[i].id
									+ '\');" onmouseout="out(\''
									+ msg.files[i].id
									+ '\');" title="'
									+ msg.files[i].fileType + '图片"/></div></a>';
						}
						jQuery('#small_imgs').html(pic);
						allHome();
					}
				}
			});
}

function delPic(id) {
	Dialog.confirm('警告：确认删除该图片?', function() {
		jQuery.ajax({
			type : "POST",
			url : contextPath + "/system/upload/delete.htm?",
			data : "id=" + id,
			async : true,
			dataType : 'json',
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：删除成功", function() {
						clickDiv();
					});

				} else {
					Dialog.alert("提示：删除失败");
				}
			},
			error : function() {
				Dialog.alert("提示：系统异常");
			}
		});
	});
}

function over(id) {
	// jQuery('.del'+id).attr("src","");
	jQuery('.del' + id).show();
	// jQuery('.img'+id).css({
	// "border":"1px solid red",
	// });
}

function out(id) {
	jQuery('.del' + id).hide();
}

// 浏览 图片
var browseImg = function(thiz) {
	var filepath = jQuery(thiz).val();
	var extStart = filepath.lastIndexOf(".");
	var ext = filepath.substring(extStart, filepath.length).toUpperCase();
	if (filepath != "") {
		if (ext != ".PNG" && ext != ".JPG") {
			Dialog.alert("提示：只能上传png,jpg图片");
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
		url : contextPath + '/system/upload/carImgUpload.htm?',
		data : jQuery('#imgForm').serialize(),
		async : false,
		dataType : 'json',
		error : function(request) {
			Dialog.alert("提示：上传失败");
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

// 上传 图片
var newUploadImg = function(carNumber, id) {
	var diag = new Dialog();
	var paraData = {
		"fileType" : "车辆",
		"tableId" : jQuery('#carId').val(),
		"tableName" : "car"
	};
	diag.Width = 800;
	diag.Height = 600;
	diag.Title = "车辆图片";
	// diag.URL =
	// "http://localhost:8080/imgServer/system/upload/uploadTest.htm?carId="+id;
	diag.URL = contextPath + "/system/upload/page.htm?paraData="
			+ encodeURI(encodeURI(JSON.stringify(paraData)));
	diag.MessageTitle = carNumber;
	diag.CancelEvent = function() {
		diag.close();
		clickDiv();
	};
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
		diag.close();
	};
	diag.show();
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

var callBack = function() {
	location.reload();
};
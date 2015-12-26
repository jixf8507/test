jQuery(document)
		.ready(
				function() {

					var tableId = jQuery('#tableId').val();
					var tableName = jQuery('#tableName').val();

					jQuery('#getPic')
							.click(
									function() {
										var paraData = {
											"tableId" : tableId,
											"tableName" : tableName,
											"fileType" : "取车",
//											"carId" : jQuery('#carId').val()
										};
										jQuery
												.ajax({
													cache : true,
													type : "POST",
													url : contextPath
															+ '/system/upload/loadFile.htm?t='
															+ new Date()
																	.getTime(),
													data : 'paraData='
															+ encodeURI(encodeURI(JSON
																	.stringify(paraData))),
													dataType : 'json',
													error : function(request) {
														Dialog.alert("提示：操作失败");
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
															jQuery(
																	'#small_imgs')
																	.html(pic);
															allHome(
																	'#small_imgs .item',
																	'#small_pre',
																	'#small_next',
																	'#small_imgs a.item:first',
																	'#small_imgs .img',
																	'#big_img',
																	'#big_a',
																	'#home_banner',
																	'#small_imgs .img.active:first');
														}
													}
												});
									});

					jQuery('#returnPic')
							.click(
									function() {
										var paraData = {
											"tableId" : tableId,
											"tableName" : tableName,
											"fileType" : "还车",
//											"carId" : jQuery('#carId').val()
										};
										jQuery
												.ajax({
													cache : true,
													type : "POST",
													url : contextPath
															+ '/system/upload/loadFile.htm?t='
															+ new Date()
																	.getTime(),
													data : 'paraData='
															+ encodeURI(encodeURI(JSON
																	.stringify(paraData))),
													dataType : 'json',
													error : function(request) {
														Dialog.alert("提示：操作失败");
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
															jQuery(
																	'#small_imgs1')
																	.html(pic);
															allHome(
																	'#small_imgs1 .item',
																	'#small_pre1',
																	'#small_next1',
																	'#small_imgs1 a.item:first',
																	'#small_imgs1 .img',
																	'#big_img1',
																	'#big_a1',
																	'#home_banner1',
																	'#small_imgs1 .img.active:first');
														}
													}
												});
									});
				});

// 图片切换
function allHome(item, pre, next, itemFirst, allImg, bigImg, bigA, bannerr,
		activeFirst) {
	jQuery('a').focus(function() {
		jQuery(this).blur();
	});
	changeImages(5, item, pre, next);
	jQuery(itemFirst).find('div.img').addClass('active');
	var allimg = jQuery(allImg);
	var big_img = jQuery(bigImg);
	var big_a = jQuery(bigA);
	var small_next = jQuery(next);
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
								+ theid + "'});").addClass('cdefault1');
			} else {
				big_a.attr('href', link).attr(
						'onClick',
						"pgvSendClick({hottag:'HRTENCENT.HOME.BANNER.BANNER"
								+ theid + "'});").removeClass('cdefault1');
			}
			big_img.animate({
				'opacity' : '1'
			}, 700);
		});
	});
	allimg.eq(0).removeClass('active');
	allimg.eq(0).mouseover();
	jQuery(bannerr).mouseover(function() {
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
			jQuery(allImg).each(function(i) {
				if (jQuery(this).hasClass('active')) {
					index = i;
					return false;
				}
			});
			if (index >= 4) {
				small_next.click();
			}
			var next = jQuery(activeFirst).parent().next().find('.img');
			if (next.length == 0) {
				next = allimg.eq(0);
			}
			next.mouseover();
			stop2 = false;
		});
	}
}

function changeImages(allowl, item, pre, next) {
	var itemall = jQuery(item);
	iteml = itemall.length;
	if (iteml <= allowl) {
		jQuery(pre).css('background', 'none');
		jQuery(next).css('background', 'none');
		return;
	}
	iteml = ((iteml - allowl) > allowl) ? allowl : (iteml - allowl);
	imagesSwitch(pre, next, itemall, 900, iteml);
}

function imagesSwitch(left, right, items, movetime, num) {
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
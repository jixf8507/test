<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>易开租车-商家服务管理中心</title>
<link rel="StyleSheet" href="${ctx}/js/dtree/dtree.css" type="text/css" />
<script type="text/javascript" src="${ctx}/js/dtree/dtree.js"></script>
</head>
<script type="text/javascript">
jQuery(document).ready(function() {
	var hid_stakeId = '';
	jQuery("input[name=stakeId]").each(function() {
		if (this.checked) {
			hid_stakeId += jQuery(this).val() + ',';
		}
	}); 
	jQuery('#hid_stakeId').val(hid_stakeId);
});

	var submit = function(callBack) {
		var stakeId = '';
		jQuery("input[name=stakeId]").each(function() {
			if (this.checked) {
				stakeId += jQuery(this).val() + ',';
			}
		}); 
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/stake/price/priceSelectStake.htm?',
			data : {
				id : jQuery('#id').val(),
				stakeId : stakeId,
				hid_stakeId : jQuery('#hid_stakeId').val()
			},
			async : false,
			dataType : 'json',
			error : function(request) {
				Dialog.alert("提示：操作失败");
			},
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：操作成功",function(){
						callBack();
					});
					
				} else {
					Dialog.alert("提示：操作失败");
				}
			}
		});
	};
</script>
<body style="margin-left: 6px;">
	<from action="" id="form1">
		 <input type="hidden" id="id" name="id" value="${param.id}" />
		 <input type="hidden" id="hid_stakeId" name="hid_stakeId"  />
	<p>
		<a href="javascript: d.openAll();">展开</a> | <a
			href="javascript: d.closeAll();">收起</a>
	</p>
	<script type="text/javascript">
		var checkSite = function() {
			jQuery("input[name=siteId]").each(function() {
				var siteId = jQuery(this).val();
				var checked = false;
				jQuery("." + siteId).each(function() {
					if (this.checked) {
						checked = true;
					}
				});
				jQuery(this).attr('checked', checked);
			});
		};
		var d = new dTree('d');
		d.config.folderLinks = false;
		d.config.useSelection = false;
		d.config.useIcons = false;
		d.config.useLines = false;
		d.config.useSelection = false;
		d.config.useStatusText = true;
		d.config.closeSameLevel = true;
		d.add(0, -1, '');
		var writeTree = function() {
			document.write(d);
			checkSite();
		};
		var drawTree = function() {
			var paraData = {"noChildStake" : "yes"};
			jQuery
					.ajax({
						cache : true,
						type : "POST",
						url : contextPath + '/stake/price/priceTypeStake.htm?',
						data : 'paraData=' + encodeURI(JSON.stringify(paraData)),
						async : false,
						dataType : 'json',
						error : function(request) {

						},
						success : function(data) {

							var sites = data['sites'];
							var stakes = data['stakes'];
							jQuery
									.each(
											sites,
											function(index, site) {
												var cbox = '<input type="checkBox" name="siteId" id="site_'
														+ site['id']
														+ '" value="'
														+ site['id']
														+ '" onclick="selectSite(this)">';
												d
														.add(
																's_'
																		+ site['id'],
																0,
																cbox
																		+ site['siteName'],
																'');
											});

							jQuery
									.each(
											stakes,
											function(index, stake) {
												var cbox = '<input type="checkBox" name="stakeId" class="'
														+ stake['site_code']
														+ '" id="'
														+ stake['id']
														+ '" value="'
														+ stake['id']
														+ '" onclick="selectStake(this)">';
												if (stake['priceid'] == jQuery('#id').val()) {
													cbox = '<input type="checkBox" name="stakeId"  class="'
															+ stake['site_code']
															+ '" id="'
															+ stake['id']
															+ '" value="'
															+ stake['id']
															+ '" checked="true" onclick="selectStake(this)">';
													if (jQuery('#site_'
															+ stake['site_code']).checked == false) {
														jQuery(
																'#site_'
																		+ stake['site_code'])
																.attr(
																		'checked',
																		true);
													}
												}
												d
														.add(
																'c_'
																		+ stake['id'],
																's_'
																		+ stake['site_code'],
																cbox
																		+ stake['factoryNo'] +'&nbsp;&nbsp;&nbsp;'+ stake['chargePort'],
																'');
											});
							writeTree();
						}
					});
		};
		drawTree();

		var selectSite = function(thiz) {
			var siteId = jQuery(thiz).val();
			jQuery("." + siteId).each(function() {
				jQuery(this).attr('checked', thiz.checked);
			});
		};

		var selectStake = function(thiz) {
			var siteId = jQuery(thiz).attr("class");
			var checked = false;
			jQuery("." + siteId).each(function() {
				if (this.checked) {
					checked = true;
				}
			});
			jQuery('#site_' + siteId).attr('checked', checked);
		};
	</script> </from>

</body>

</html>

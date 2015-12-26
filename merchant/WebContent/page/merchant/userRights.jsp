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
	var submit = function(callBack) {
		var modelIds = '';
		jQuery("input[name=moduleId]").each(function() {
			if (this.checked) {
				modelIds += jQuery(this).val() + ',';
			}
		});
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/merchant/user/saveRights.htm?',
			data : {
				id : jQuery('#id').val(),
				modelIds : modelIds
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
	<from action="" id="form1"> <input type="hidden" id="id"
		name="id" value="${param.id}" />
	<p>
		<a href="javascript: d.openAll();">展开</a> | <a
			href="javascript: d.closeAll();">收起</a>
	</p>
	<script type="text/javascript">
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
		};
		var drawTree = function() {
			jQuery
					.ajax({
						cache : true,
						type : "POST",
						url : contextPath + '/merchant/user/rights.htm?',
						data : 'id=' + jQuery('#id').val(),
						async : false,
						dataType : 'json',
						error : function(request) {

						},
						success : function(data) {
							jQuery
									.each(
											data,
											function(index, module) {
												var id = module['id'];
												var pid = module['pid'];
												var moduleId = module['moduleId'];

												var cbox = '<input type="checkBox" name="moduleId" id="cb_'
														+ id
														+ '" value="'
														+ id
														+ '" class="'
														+ pid
														+ '" onclick="selectBox(this)">';
												if (moduleId != '') {
													cbox = '<input type="checkBox" name="moduleId" id="cb_'
															+ id
															+ '" value="'
															+ id
															+ '" class="'
															+ pid
															+ '" onclick="selectBox(this)" checked="true">';
												}
												d.add(id, pid, cbox
														+ module['moduleName'],
														'');
											});

							writeTree();
						}
					});
		};
		drawTree();

		var selectBox = function(thiz) {
			var moduleId = jQuery(thiz).val();
			selectSon(thiz);
			selectParent(moduleId);
		};

		//向下选择,子状态与负状态保证一致
		var selectSon = function(thiz) {
			var moduleId = jQuery(thiz).val();
			jQuery("." + moduleId).each(function() {
				jQuery(this).attr('checked', thiz.checked);
				selectSon(this);
			});
		};

		//向上选择
		var selectParent = function(id) {
			var pid = jQuery('#cb_' + id).attr("class");
			var parentObj = jQuery("#cb_" + pid);
			if (parentObj.length > 0) {
				var checked = false;				
				jQuery("." + pid).each(function() {
					if (this.checked) {
						checked = true;
					}
				});
				parentObj.attr('checked', checked);
				selectParent(pid);
			}
		};
	</script> </from>

</body>

</html>

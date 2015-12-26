jQuery(document).ready(function() {
	
	requstSites();
	
	jQuery('#queryBtn,#map').click(function(){
		requstSites();
	});
	
});

var requstSites = function() {
	var paraData = {};
	jQuery.ajax({
		type : "POST",
		url : contextPath + '/merchant/site/ajaxSites.htm?t='
		+ new Date().getTime(),
		data : 'paraData=' + encodeURI(JSON.stringify(paraData)),
		async : true,
		dataType : 'json',
		success : function(data) {
			// 初始化商家资源
			merchantResouce.init(data);
			// 初始化地图
			baiduMap.init(data);
		},
		error : function() {

		}
	});
};
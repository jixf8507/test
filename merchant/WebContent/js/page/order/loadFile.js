jQuery(document).ready(function() {
	jQuery('#loadFile')
	.change(
			function() {
				var filepath = jQuery('#loadFile').val();//文件名（包括扩展名）
 				var extStart = filepath.lastIndexOf(".");
				var ext = filepath.substring(extStart,filepath.length).toUpperCase();//后缀
				if (filepath != "") {
					if (ext != ".XLS" && ext != ".XLSX") {
						alert("只能上传excel文件！");
						jQuery(this).outerHTML = jQuery(this).outerHTML;
					}
				}
				else {
					alert("请选择文件");
				}
			});
});

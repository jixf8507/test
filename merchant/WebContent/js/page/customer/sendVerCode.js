jQuery(document).ready(function() {

		var wait=30; 
		document.getElementById("sendVerCode").disabled = false;
		function time(o) {
		        if (wait == 0) {
		            o.removeAttribute("disabled");
		            o.value="获取验证码";
		            wait = 30;
		        } else {
		            o.setAttribute("disabled", true);
		            o.value="请等待" + wait + "秒后重新获取";
		            wait--;
		            setTimeout(function() {
		                time(o);
		            },
		            1000);
		        }
		    }
	
	jQuery('#sendVerCode').click(function(){
		
		var phone = jQuery("#phone").val();
		    var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
		    if(!myreg.test(phone)){ 
		    	Dialog.alert('提示：请输入有效的手机号码！'); 
		        return false; 
		    }
		    time(this);
		    jQuery.ajax({
				type : "POST",
				url : contextPath + "/msg/send/updateStatus.htm?",
				data : "phone=" + phone,  
				async : true,
				dataType : 'json',
				success : function(data) {
					if (data.success) {
//						Dialog.alert("发送验证短信成功");
						
					} else {
						Dialog.error("提示：发送验证短信失败");
					}
				},
				error : function() {
					Dialog.error("错误：发送验证短信失败");
				}
			});
		
	});
	
	jQuery('#verCode').change(function(){
		var verCode = jQuery("#verCode").val();
		var phone = jQuery("#phone").val();
		if(phone == ""){
			Dialog.alert("提示：请输入手机号码后点击发送验证码");
			jQuery("#verCode").val("");
			return false;
		}
		jQuery.ajax({
			type : "POST",
			url : contextPath + "/msg/send/check.htm?",
			data : "phone=" + phone,
			async : true,
			dataType : 'json',
			success : function(data) {
				if(data.info != ""){
					if(data.info == verCode){
//						Dialog.alert("验证码正确");
						
					}else{
						Dialog.alert("提示：验证码不正确"); 
						jQuery("#verCode").val("");
					}
				}else{
					Dialog.alert("提示：请点击发送验证短信");
					jQuery("#verCode").val("");
				}
			},
			error : function() {
				Dialog.error("错误：发送验证短信失败");
				jQuery("#verCode").val("");
			}
		});
	});

}); 

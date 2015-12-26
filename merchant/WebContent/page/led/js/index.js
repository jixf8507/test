 window.onload = function(){
	var day = [];
	var wq = [];
	var ry = [];
	
	$.ajax({
		type : "POST",
		url : "../../monitor/main/ajaxDaySystem.htm?",
		async : true,
		dataType : 'json',
		success : function(data) {
			var dataMap =  data.data;
			for(var i = 0 ; i < dataMap.length ; i++){
				day[i] = dataMap[i].day.substring(5);
				wq[i] = Math.abs(Math.ceil(dataMap[i].kms * 0.4));
				ry[i] = Math.abs(Math.ceil(dataMap[i].kms * 0.12));
			}
			draw(day,wq,ry);
		},
		error : function() {
			Dialog.alert("加载数据失败");
		}
	});

var draw = function(day,wq,ry){
	var myData = {
			labels : day,
			datasets : [ {
				fillColor : "rgba(255,255,255,.5)",
				strokeColor : "rgba(255,255,255,1)",
				pointColor : "rgba(220,220,220,1)",
				pointStrokeColor : "#fff",
				data : ry
			}, {
				fillColor : "rgba(90,190,90,.5)",
				strokeColor : "rgba(90,190,90,1)",
				pointColor : "rgba(90,190,90,1)",
				pointStrokeColor : "#fff",
				data : wq
			} ]
		};
	
//	new Chart(document.getElementById('canvas').getContext('2d')).Line(myData);
	new Chart($("#canvas").get(0).getContext("2d")).Line(myData);
};

 };

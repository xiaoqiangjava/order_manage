window.onload = function(){
	startTime();
};
function startTime(){
	var date = new Date();
	var yyyy = date.getFullYear();
	var MM = date.getMonth()+1;
	var dd = date.getDate();
	MM = checkTime(MM);
	dd = checkTime(dd);
	var day;
	if(date.getDay()==0){
		day = "星期日";
	}else if(date.getDay()==1){
		day = "星期一";
	}else if(date.getDay()==2){
		day = "星期二";
	}else if(date.getDay()==3){
		day = "星期三";
	}else if(date.getDay()==4){
		day = "星期四";
	}else if(date.getDay()==5){
		day = "星期五";
	}else if(date.getDay()==6){
		day = "星期六";
	}
	setTimeout("startTime()",1000);
	var time = document.getElementById("time");
	time.innerHTML = yyyy+"-"+MM+"-"+dd+"  "+day;
}
//检查时间格式
function checkTime(time){
	if(time<10){
		time = "0"+time;
	}
	return time;
}

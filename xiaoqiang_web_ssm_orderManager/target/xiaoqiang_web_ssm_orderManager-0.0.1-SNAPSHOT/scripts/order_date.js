$(function(){
	//console.log("页面加载之后执行的函数!");
	var date = {
			elem:'#order_date',
			event:'click',
			format:'YYYY-MM-DD',
			isclear:true,
			istime:true,
			istody:true,
			issure:true,
			min:'1990-01-01',
			max:'2099-12-31'
	};
	var start = {
			elem:'#start_date',
			event:'click',
			format:'YYYY-MM-DD',
			isclear:true,
			istime:true,
			istody:true,
			issure:true,
			min:'1990-01-01',
			max:'2099-12-31',
			choose:function(dates){
				end.min = dates;
				end.start = dates;//设置结束日期的开始日期
			}
	};
	var end = {
			elem:'#end_date',
			event:'click',
			format:'YYYY-MM-DD',
			isclear:true,
			istime:true,
			istody:true,
			issure:true,
			min:'1990-01-01',
			max:'2099-12-31',
			choose:function(dates){
				start.max = dates;
			}
	};
	laydate.skin('molv');
	laydate(date);
	laydate(start);
	laydate(end);
});
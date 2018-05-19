$(function(){
	//console.log("新品上架日期!");
	var date = {
			elem:"#shelf_add_date",
			event:'click',
			format:'YYYY-MM-DD',
			isclear:true,
			istime:true,
			istoday:true,
			issure:true,
			min:'1900-01-01',
			max:'2099-01-01'
	};
	var start = {
			elem:"#shelf_start_date",
			event:'click',
			format:'YYYY-MM-DD',
			isclear:true,
			istime:true,
			istoday:true,
			issure:true,
			min:'1900-01-01',
			max:'2099-01-01',
			choose:function(date){
				end.min = date;
				end.start = date;
			}
	}
	var end = {
			elem:"#shelf_end_date",
			event:'click',
			format:'YYYY-MM-DD',
			isclear:true,
			istime:true,
			istoday:true,
			issure:true,
			min:'1900-01-01',
			max:'2099-01-01',
			choose:function(date){
				start.max = date;
			}
	}
	laydate.skin('molv');
	laydate(date);
	laydate(start);
	laydate(end);
});
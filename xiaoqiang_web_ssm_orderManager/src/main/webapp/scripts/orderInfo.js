//搜索功能
function selectAction(){
	$('#find_button').attr("disabled",true).html("搜索中");
	var url = "order/orderInfo.bsnt?time="+new Date().getTime();
	var status = [];
	$('.checkbox:checked').each(function(){
		status.push(this.value);
		//console.log(status);
	});
	var params = {
			username:$('#login_info').html(),
			orderCode:$('#order_code').val(),
			aliCode:$('#ali_order').val(),
			orderDate:$('#order_date').val(),
			receiverName:$('#receiver_name').val(),
			productName:$('#product_name').val(),
			venderUrl:$('#vender_url').val(),
			logisticsCode:$('#logistics_code').val(),
			productCode:$('#product_code').val(),
			receiverAdd:$('#receiver_add').val(),
			startDate:$('#start_date').val(),
			endDate:$('#end_date').val(),
			payChnl:$('#pay_chnl').val(),		
			payStatus:status,
	};
	
	$.ajax({
		type:"POST",
		url:url,
		data:params,
		traditional:true,//当需要向后台传送数组类型的数据时,将traditional属性设置为true
		success:function(result){
			if(result.status==0){
				//console.log(result.data);
				$('#tbody').empty();
				var orderList = result.data;
				model.page = 1;
				model.selectOrderInfoView(orderList,model.page);
			}else if(result.status==1){
				alert("没有相关记录,请重新输入查询条件!");
				$('#tbody').empty();
			}else if(result.status==6){
				alert(result.message);
				location.href = "login.html";
			}else{
				alert(result.message);
			}
			$('#find_button').attr("disabled",false).html("搜索");
		},
		dataType:"json"
	});
}
//显示订单信息
model.selectOrderInfoView = function(orderList,page){
//	console.log("当前显示的页数:" + page);
	var totalCount = orderList.length;
	var totalPage = totalCount%8==0?totalCount/8:parseInt(totalCount/8+1);
	model.totalPage = totalPage;
//	console.log("总页数:" + totalPage + ";总记录数:" + totalCount);
	//分页中当前页显示的记录数
	var currPageCount = totalCount/page>8?8:totalCount-(page-1)*8;
//	console.log("当前页显示的记录数目:" + currPageCount);
	var offset = (page-1)*8;
//	console.log("当前页开始获取记录时的偏移量:" + offset);
	var end = currPageCount==8?(8*page):currPageCount+(page-1)*8;
//	console.log("当前页获取记录结束时的偏移量: " + end);
	$('#tbody').empty();
	for(var i=offset;i<end;i++){
		if(orderList[i].transfer==null){
			orderList[i].transfer="";
		}
		if(orderList[i].tuikuanbeizhu==null){
			orderList[i].tuikuanbeizhu="";
		}
		if(orderList[i].aliCode==null){
			orderList[i].aliCode = "";
		}
		if(orderList[i].url==null){
			orderList[i].url="";
		}
		if(orderList[i].status==0){
			orderList[i].status="待付款";
		}else if(orderList[i].status==1){
			orderList[i].status="已付款";
		}else if(orderList[i].status==2){
			orderList[i].status="待发货";
		}else if(orderList[i].status==3){
			orderList[i].status="待收货";
		}else if(orderList[i].status==4){
			orderList[i].status="待评价";
		}else if(orderList[i].status==5){
			orderList[i].status="退款/售后";
		}else if(orderList[i].status==6){
			orderList[i].status="退款成功";
		}else{
			orderList[i].status="作废";
		}
		var date = new Date(orderList[i].orderDate);
		date = formatDate(date);
		var price = orderList[i].price;
		price = Number(price).toFixed(2);
		var totalPrice = orderList[i].count*price;
		totalPrice = Number(totalPrice).toFixed(2);
		var firstCost = orderList[i].firstCost;
		firstCost = Number(firstCost).toFixed(2);
		console.log($('#order_table th').size());
		if($('#order_table th').size()==17){
			var template = "<tr><td>"+orderList[i].shopId+"</td><td>"+orderList[i].orderCode+"</td><shiro:hasPermission name='aliCode:view'><td>"+orderList[i].aliCode+"</td></shiro:hasPermission>" +
			"<td>"+orderList[i].status+"</td><td>"+orderList[i].productName+"</td><td>"+orderList[i].size+"</td><td>"+orderList[i].color+"</td>" +
			"<td>"+orderList[i].count+"</td><td>"+price+"</td>" +"<shiro:hasPermission name='firstCost:view'><td>" + firstCost+"</td></shiro:hasPermission>" +
			"<td>"+orderList[i].receiverName+"</td><td class='text_ell'>"+orderList[i].receiverPhone+"</td>" +
			"<td>"+orderList[i].receiverAdd+"</td><td>"+orderList[i].logisticsCompany+"</td><td>"+orderList[i].logisticsCode+"</td>" +
			"<td><a class='url' href='${url}' target='_blank'>"+orderList[i].url+"</a></td>" +
			"<td><a class='order_detail' href='javascript:;'>详情</a>&nbsp;&nbsp;<a class='edit_a' href='javascript:;'>操作</a></td></tr>";	
		}else{
			var template = "<tr><td>"+orderList[i].shopId+"</td><td>"+orderList[i].orderCode +
			"<td>"+orderList[i].status+"</td><td>"+orderList[i].productName+"</td><td>"+orderList[i].size+"</td><td>"+orderList[i].color+"</td>" +
			"<td>"+orderList[i].count+"</td><td>"+price+"</td>" +
			"<td>"+orderList[i].receiverName+"</td><td class='text_ell'>"+orderList[i].receiverPhone+"</td>" +
			"<td>"+orderList[i].receiverAdd+"</td><td>"+orderList[i].logisticsCompany+"</td><td>"+orderList[i].logisticsCode+"</td>";
		}
		var tr = $(template.replace('${url}',orderList[i].url));
		tr.data("orderIndex",i);
//		console.log("orderIndex");
//		console.log(tr.data("orderIndex"));

		$('#tbody').append(tr);
		$('#tbody tr:odd').css({"background-color" : "#dfdfdf"});
	}
	model.orderList = orderList;
	//console.log("model:"+model.orderList);
	//加载分页模块
	$('#page_view').empty();
	$('#page_view').append("<li>&laquo; 上一页</li>");
	if(totalPage>12){
		if(page-5>1){
			if(page+6<=totalPage){
				for(var i=page-5;i<=page+6;i++){
					$('#page_view').append("<li style='border-left:0;'>" + i + "</li>")
				}				
			}else{
				for(var i=totalPage-11;i<=totalPage;i++){
					$('#page_view').append("<li style='border-left:0;'>" + i + "</li>")
				}	
			}
		}else {
			for(var i=1;i<=12;i++){
				$('#page_view').append("<li style='border-left:0;'>" + i + "</li>");
			}
		}
		$('#page_view').append("<li style='border-left:0;'>下一页 &raquo;</li><li style='border:0;'>第" + page + "页/共" + totalPage + "页</li><li style='border:0;><a href='javascript:;'>共<span id='total_count'></span>条记录</a></li>");
		$('#total_count').html(totalCount);
//		$('#page_view li').removeClass("active");
		if(page==1){
			$('#page_view li:eq('+page+')').addClass("active");			
		}
	}else{
		for(var i=1;i<=totalPage;i++){
			$('#page_view').append("<li style='border-left:0;'>" + i + "</li>")
		}
		$('#page_view').append("<li style='border-left:0;'>下一页 &raquo;</li><li style='border:0;'>第" + page + "页/共" + totalPage + "页</li><li style='border:0;><a href='javascript:;'>共<span id='total_count'></span>条记录</a></li>");
		$('#total_count').html(totalCount);
		if(page==1){
			$('#page_view li:eq('+page+')').addClass("active");					
		}
	}
};
//分页查询
function loadOrderInfoAction(){
//	console.log($(this).text());
//	console.log("分页中的总页数: " + model.totalPage);
//	alert(model.totalPage);
	if(isNaN($(this).text())){
		var text = $(this).text();
		var msg = text.substr(text.length-3,3)=="上一页"? (model.page==1?"已经是第一页!":model.page--):(model.page==model.totalPage?"已经是最后一页!":model.page++);
		if(isNaN(msg)){
			alert(msg);
			return;
		}
		model.selectOrderInfoView(model.orderList,model.page);
	}else{
		model.page = parseInt($(this).text());
//		console.log("点击的页数:　" + model.page);
		model.selectOrderInfoView(model.orderList,model.page);			
	}
	$('#page_view li').removeClass("active");
//	console.log("点击的li中的文本:" + $(this).text());
//	console.log("当前页数:" + model.page);
	$('#page_view li').each(function(){
		var textInt = parseInt($(this).text());
		if(textInt == model.page){
			$(this).addClass("active");
		}
	});
}

//日期格式化
function formatDate(date){
	var year = date.getFullYear();
	var month = date.getMonth()+1;
	var day = date.getDate();
	var hours = date.getHours();
	var minutes = date.getMinutes();
	var seconds = date.getSeconds();
	month = checktime(month);
	day = checktime(day);
	hours = checktime(hours);
	minutes = checktime(minutes);
	seconds = checktime(seconds);
	return year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
}
function checktime(time){
	if(time<10){
		time = "0"+time;
	}
	return time;
}

//选中按钮的背景变化
function addClassAction(){
	//console.log("点击了该按钮");
	$('.menu_button').removeClass('checked');
	$(this).addClass('checked');
}





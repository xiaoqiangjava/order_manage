//订单编辑
function editOrderAction(){
	$('.edit_plate').fadeOut();
	$('#edit_order').fadeIn();
	//console.log("定单编辑页面!");
	var orderIndex = $(this).parent().parent().data('orderIndex');
	console.log(orderIndex);
	var productId = model.orderList[orderIndex].shopId;
	//console.log(productId);
	var orderCode = model.orderList[orderIndex].orderCode;
	var aliCode = model.orderList[orderIndex].aliCode;
	var logisticsCode = model.orderList[orderIndex].logisticsCode;
	var receiverName = model.orderList[orderIndex].receiverName;
	var receiverPhone = model.orderList[orderIndex].receiverPhone;
	var receiverAdd = model.orderList[orderIndex].receiverAdd;
	var logisticsCompany = model.orderList[orderIndex].logisticsCompany;
	var transfer = model.orderList[orderIndex].transfer;
	var payStatus = model.orderList[orderIndex].status;
	var orderId = model.orderList[orderIndex].orderId;
	var payChnl = model.orderList[orderIndex].payChnl;
	var productCode = model.orderList[orderIndex].productCode;
	var productName = model.orderList[orderIndex].productName;
	
	$('#edit_shop_id').val(productId);
	$('#edit_order_code').val(orderCode);
	$('#edit_ali_code').val(aliCode);
	$('#edit_logistics_code').val(logisticsCode);
	$('#edit_receiver_name').val(receiverName);
	$('#edit_receiver_phone').val(receiverPhone);
	$('#edit_receiver_add').val(receiverAdd);
	$('#edit_logistics_company').val(logisticsCompany);
	$('#edit_transfer').val(transfer);
	$('#edit_status').val(payStatus);
	$('#edit_order_id').val(orderId);
	$('#edit_pay_chnl').val(payChnl);
	$('#edit_shop_name').val(productName);
	$('#edit_shop_code').val(productCode);
	
	$('.cancle_button').click(function(){
		$('#edit_order').fadeOut();
		$('#order').fadeIn();
	});
}
//修改订单信息
function modifyOrderAction(){
	var msg = "您确定要修改订单信息吗?";
	if(confirm(msg)){
		console.log("修改订单信息!");
		var url = "order/updateOrder.bsnt?date="+new Date().getTime();
		var payStatus = $('#edit_status').val();
		var params = {
				orderId:$('#edit_order_id').val(),
				receiverName:$('#edit_receiver_name').val(),
				receiverPhone:$('#edit_receiver_phone').val(),
				receiverAdd:$('#edit_receiver_add').val(),
				payStatus:payStatus
		};
		$.post(url,params,function(result){
			if(result.status==0){
				//console.log("订单信息修改成功!");
				alert("订单信息修改成功!");
				$('#refresh').click();
			}else if(result.status==1){
				alert("订单信息修改失败,请检查修改内容!");
			}else if(result.status==6){
				alert(result.message);
				location.href = "login.html";
			}else{
				alert(result.message);
			}
		},"json");
	}
}

//修改商品信息
function modifyShopAction(){
	var flag = confirm("您确定要修改商品信息吗?");
	if(flag){
		//console.log("修改商品信息!");
		var url = "product/updateProduct.bsnt?date="+new Date().getTime();
		var params = {
				shopId:$('#edit_shop_id').val(),
				aliCode:$('#edit_ali_code').val(),
				logisticsCode:$('#edit_logistics_code').val(),
				logisticsCompany:$('#edit_logistics_company').val(),
				transfer:$('#edit_transfer').val()
		};
		$.post(url,params,function(result){
			if(result.status==0){
//				console.log("商品信息修改成功!");
				alert("商品信息修改成功!");
				$('#refresh').click();
			}else if(result.status==1){
				alert("商品信息修改失败,请检查修改的内容!");
			}else if(result.status==6){
				alert(result.message);
				location.href = "login.html";
			}else{
				alert(result.message);
			}
		},"json");
	}
}

















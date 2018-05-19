/**
 * 查询商品信息
 * @returns
 */
function findProductAction(){
	//console.log("查询商品信息!");
	$('#shop_find_button').attr("disabled",true).html("搜索中");
	var url = "product/findProduct.bsnt?bsnt="+new Date().getTime();
	var params = {
			productId:$('#shelf_product_id').val(),
			productName:$('#shelf_product_name').val(),
			addDate:$('#shelf_add_date').val(),
			brand:$('#shelf_brand').val(),
			artNumber:$('#shelf_art_number').val(),
			storeCount:$('#shelf_store_count').val(),
			venderUrl:$('#shelf_vender_url').val(),
			startDate:$('#shelf_start_date').val(),
			endDate:$('#shelf_end_date').val()
	};
	$.post(url,params,function(result){
		if(result.status==0){
			//console.log("商品查询成功");
			//console.log(result.data);
			var productInfo = result.data;
			model.productInfo = productInfo;
			model.proPage = 1;
			model.listProductInfoAction(productInfo,model.proPage);
		}else if(result.status==1){
			//console.log("没有相关记录!");
			$('#shop_find_button').attr("disabled",false).html("搜索");
			$('#shop_tbody').empty();
			alert("没有相关记录!");
		}else if(result.status==6){
			alert(result.message);
			location.href = "login.html";
		}else{
			$('#shop_find_button').attr("disabled",false).html("搜索");
			$('#shop_tbody').empty();
			alert(result.message);
		}
	},"json");
}
/**
 * 显示商品信息
 * @param productInfo
 * @returns
 */
model.listProductInfoAction = function(productInfo,page){
	//console.log("查询结果数据!");
	
//	console.log("当前显示的页数:" + page);
	var totalCount = productInfo.length;
	var totalPage = totalCount%9==0?totalCount/9:parseInt(totalCount/9+1);
	model.proTotalPage = totalPage;
//	console.log("总页数:" + totalPage + ";总记录数:" + totalCount);
	//分页中当前页显示的记录数
	var currPageCount = totalCount/page>9?9:totalCount-(page-1)*9;
//	console.log("当前页显示的记录数目:" + currPageCount);
	var offset = (page-1)*9;
//	console.log("当前页开始获取记录时的偏移量:" + offset);
	var end = currPageCount==9?(9*page):currPageCount+(page-1)*9;
//	console.log("当前页获取记录结束时的偏移量: " + end);
	
	$('#shop_tbody').empty();
	for(var i=offset;i<end;i++){
		var product = productInfo[i];
		if(product.venderUrl==null){
			product.venderUrl = "";
		}
		var date = new Date(product.addDate);
		date = formatDate(date);
		var price = Number(product.price).toFixed(2);
		var complet = "<tr><td><input type='checkbox' name='shop_box' value='#{id}'></td><td>"+product.productId+"</td><td>"+product.productName+"</td>" +
		"<td>"+date+"</td><td>"+product.brand+"</td><td>"+product.artNumber+"</td><td>"+product.size+"</td>" +
		"<td>"+product.color+"</td><td>"+price+"</td><td>"+product.storeCount+"</td><td><a href='#{href}' target='_blank'>"+product.venderUrl+"</a></td>" +
		"<td><a href='javascript:;' class='shop_shelf_edit'>编辑</a></td></tr>";
		
		var trReplace = complet.replace("#{id}",product.productId);
		var tr = $(trReplace.replace("#{href}",product.venderUrl));
		tr.data("productIndex",i);
		$('#shop_tbody').append(tr);
		$('#shop_tbody tr:odd').addClass("highlighting");
	}
	$('#shop_find_button').attr("disabled",false).html("搜索");
	var flag = $('#shop_info').is(":hidden");
	if(flag){
		$('#update_shop_info').fadeOut();
		$('#delete_div').fadeIn();
		$('#shop_info').fadeIn();
		$('#proPages').fadeIn();
	}
	
	$('#proPage_view').empty();
	$('#proPage_view').append("<li>&laquo; 上一页</li>");
	if(totalPage>12){
		if(page-5>1){
			if(page+6<=totalPage){
				for(var i=page-5;i<=page+6;i++){
					$('#proPage_view').append("<li style='border-left:0;'>" + i + "</li>")
				}				
			}else{
				for(var i=totalPage-11;i<=totalPage;i++){
					$('#proPage_view').append("<li style='border-left:0;'>" + i + "</li>")
				}	
			}
		}else {
			for(var i=1;i<=12;i++){
				$('#proPage_view').append("<li style='border-left:0;'>" + i + "</li>");
			}
		}
		$('#proPage_view').append("<li style='border-left:0;'>下一页 &raquo;</li><li style='border:0;'>第" + page + "页/共" + totalPage + "页</li><li style='border:0;><a href='javascript:;'>共<span id='pro_total_count'></span>条记录</a></li>");
		$('#pro_total_count').html(totalCount);
//		$('#page_view li').removeClass("active");
		if(page==1){
			$('#proPage_view li:eq('+page+')').addClass("active");			
		}
	}else{
		for(var i=1;i<=totalPage;i++){
			$('#proPage_view').append("<li style='border-left:0;'>" + i + "</li>")
		}
		$('#proPage_view').append("<li style='border-left:0;'>下一页 &raquo;</li><li style='border:0;'>第" + page + "页/共" + totalPage + "页</li><li style='border:0;><a href='javascript:;'>共<span id='pro_total_count'></span>条记录</a></li>");
		$('#pro_total_count').html(totalCount);
		if(page==1){
			$('#proPage_view li:eq('+page+')').addClass("active");					
		}
	}
	
};

//分页查询
function loadProductInfoAction(){
//	console.log($(this).text());
//	console.log("分页中的总页数: " + model.proTotalPage);
//	alert(model.totalPage);
	if(isNaN($(this).text())){
		var text = $(this).text();
		var msg = text.substr(text.length-3,3)=="上一页"? (model.proPage==1?"已经是第一页!":model.proPage--):(model.proPage==model.proTotalPage?"已经是最后一页!":model.proPage++);
		if(isNaN(msg)){
			alert(msg);
			return;
		}
		model.listProductInfoAction(model.productInfo,model.proPage);
	}else{
		model.proPage = parseInt($(this).text());
//		console.log("点击的页数:　" + model.proPage);
		model.listProductInfoAction(model.productInfo,model.proPage);			
	}
	$('#proPage_view li').removeClass("active");
//	console.log("点击的li中的文本:" + $(this).text());
//	console.log("当前页数:" + model.proPage);
	$('#proPage_view li').each(function(){
		var textInt = parseInt($(this).text());
		if(textInt == model.proPage){
			$(this).addClass("active");
		}
	});
}
/**
 * 修改商品库存信息数据展示
 * @returns
 */
function editShopInfoAction(){
	$('#update_shop_info').fadeIn();
	$('#delete_div').fadeOut();
	$('#shop_info').fadeOut();
	$('#proPages').fadeOut();
	var productIndex = $(this).parent().parent().data("productIndex");
	//console.log(productIndex);
	var productId =  model.productInfo[productIndex].productId;
	var productPriceId = model.productInfo[productIndex].productPriceId;
	console.log("商品套餐表中的id: " + productPriceId);
	model.productPriceId = productPriceId;
	var productName = model.productInfo[productIndex].productName;
	var brand = model.productInfo[productIndex].brand;
	var artNumber = model.productInfo[productIndex].artNumber;
	var color = model.productInfo[productIndex].color;
	var size = model.productInfo[productIndex].size;
	var storeCount = model.productInfo[productIndex].storeCount;
	var venderUrl = model.productInfo[productIndex].venderUrl;
	var taobaoUrl = model.productInfo[productIndex].taobaoUrl;
	var kingUrl = model.productInfo[productIndex].kingUrl;
	var price = model.productInfo[productIndex].price;
	price = Number(price).toFixed(2);
	$('#update_shop_id').val(productId);
	$('#update_shop_name').val(productName);
	$('#update_brand').val(brand);
	$('#update_art_number').val(artNumber);
	$('#update_color').val(color);
	$('#update_size').val(size);
	$('#update_store_count').val(storeCount);
	$('#update_vender_url').val(venderUrl);
	$('#update_taobao_url').val(taobaoUrl);
	$('#update_king_url').val(kingUrl);
	$('#update_price').val(price);
}
/**
 * 修改商品信息-->修改按钮单击事件
 * @returns
 */
function updateProductAction(){
	//console.log("修改商品信息!");
	var url = "product/updateProductInfo.bsnt?bsnt="+new Date().getTime();
	var params = {
			productId:$('#update_shop_id').val(),
			productPriceId:model.productPriceId,
			productName:$('#update_shop_name').val(),
			brand:$('#update_brand').val(),
			artNumber:$('#update_art_number').val(),
			size:$('#update_size').val(),
			color:$('#update_color').val(),
			storeCount:$('#update_store_count').val(),
			venderUrl:$('#update_vender_url').val(),
			taobaoUrl:$('#update_taobao_url').val(),
			kingUrl:$('#update_king_url').val(),
			price:$('#update_price').val(),
			channelPrice:$('#update_channel_price').val(),
			farePrice:$('#update_fare_price').val()	
	};
	$.post(url,params,function(result){
		if(result.status==0){
			//console.log("商品信息修改成功!");
			alert("商品信息修改成功!");
			$('#update_channel_price').val("");
			$('#update_fare_price').val("");
			$('#refresh_shop').click();
		}else if(result.status==1){
			alert("商品信息修改失败!请重新输入信息!");
		}else if(result.status==6){
			alert(result.message);
			location.href = "login.html";
		}else{
			alert(result.message);
		}
	},"json");
	
}
/**
 * 商品下架,批量删除商品信息
 */
function deleteProductInfoAction(){
	//console.log("删除商品信息!");
	var flag = confirm("您确定要删除该商品的所有记录吗?");
	if(flag==false){
		return;
	}
	//console.log("确定删除!");
	var ids = [];
	$('#shop_tbody input:checked').each(function(){
		ids.push(this.value);
	});
	console.log("需要删除的商品的ID: " + ids);
	var url = "productInfo/deleteProducts.bsnt?bsnt="+new Date().getTime();
	var params = {
			ids:ids
	};
	$.ajax({
		type:"POST",
		url:url,
		data:params,
		traditional:true,
		success:function(result){
			if(result.status==0){
				//console.log("商品信息删除成功!");
				var number = result.data;
				//console.log(number);
				alert("已成功删除"+number+"条记录!");
				$('#refresh_shop').click();
				//如果是是一次性选中了所有的记录,则删除后将该复选框改为未选中状态
				if($('#shop_thead input').is(":checked")){
					$('#shop_thead input').click();
				}
				
			}else if(result.status==6){
				alert(result.message);
				location.href = "login.html";
			}else{
				alert(result.message);
			}
		},
		dataType:"json"
	
	});
}






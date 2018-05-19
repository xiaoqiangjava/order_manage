/**
 * 刷新商品信息
 * @returns
 */
function refreshProductAction(){
	//console.log("查询商品信息!");
	$('#refresh_shop').attr("disabled",true);
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
			model.listRefreshProductInfoAction(productInfo,model.proPage);
			$('#refresh_shop').attr("disabled",false);
		}else if(result.status==1){
			//console.log("没有相关记录!");
			$('#refresh_shop').attr("disabled",false);
			$('#shop_tbody').empty();
		}else if(result.status==6){
			alert(result.message);
			location.href = "login.html";
		}else{
			alert(result.message);
			$('#refresh_shop').attr("disabled",false);
			$('#shop_tbody').empty();
		}
	},"json");
}
/**
 * 显示商品信息
 * @param productInfo
 * @returns
 */
model.listRefreshProductInfoAction = function(productInfo,page){
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
	$('#proPage_view li').each(function(){
		var textInt = parseInt($(this).text());
		if(textInt == model.proPage){
			$(this).addClass("active");
		}
	});
	
};
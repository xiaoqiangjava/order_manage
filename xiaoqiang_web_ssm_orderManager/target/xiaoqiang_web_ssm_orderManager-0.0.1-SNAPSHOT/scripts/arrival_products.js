/**
 * 新品上架,品质惠购
 * @returns
 */
function newProductArrivalAction(){
	//console.log("新品上架产品提交!");
	$('#new_submit').attr("disabled",true).html("提交中");
	var url = "productInfo/saveProductInfo.bsnt?bsnt="+new Date().getTime();
	var formData = new FormData();
	formData.append("productName",$('#new_product_name').val());
	formData.append("lable",$('#new_lable option:selected').val());
	formData.append("productClass",$('#new_product_class').val());
	formData.append("brand",$('#new_brand').val());
	formData.append("artNumber",$('#new_art_number').val());
	formData.append("channelPrice",$('#new_channel_price').val());
	formData.append("farePrice",$('#new_fare_price').val());
	formData.append("price",$('#new_price').val());
	formData.append("oldPrice",$('#new_old_price').val());
	formData.append("size",$('#new_size').val());
	formData.append("color",$('#new_color').val());
	formData.append("venderUrl",$('#new_vender_url').val());
	for(var i=0;i<$('#new_picture_0')[0].files.length;i++){
		formData.append("picture0", $('#new_picture_0')[0].files[i]);		
	}
	formData.append("picture1",$('#new_picture_1')[0].files[0]);
	formData.append("picture2",$('#new_picture_2')[0].files[0]);
	formData.append("picture3",$('#new_picture_3')[0].files[0]);
	formData.append("picture4",$('#new_picture_4')[0].files[0]);
	//用户选择的文件都存在FileList对象中,其中每个File对象都对应一个文件
	var number = $('#new_picture_5').get(0).files.length;
//	console.log(number);
	for(var i=0;i<$('#new_picture_5')[0].files.length;i++){
		formData.append("picture5", $('#new_picture_5')[0].files[i]);		
	}

	$.ajax({
		url:url,
		type:"POST",
		cache:false,
		data:formData,
		processData:false,
		contentType:false,
		xhr: function(){
            var xhr = $.ajaxSettings.xhr();
            if(onprogress && xhr.upload) {
                xhr.upload.addEventListener("progress" , onprogress, false);
                return xhr;
            }
        },
		success:function(result){
			if(result.status==0){
				//console.log("新品上架上架成功!");
				alert("商品信息添加成功!");
				$('#new_submit').attr("disabled",false).html("提交");
				$('#son').html("");
			}else if(result.status==1){
				alert("注意!!!商品信息添加失败,请检查输入信息!");
				$('#new_submit').attr("disabled",false).html("提交");
				$('#son').html("");
			}else{
				alert(result.message);
				$('#new_submit').attr("disabled",false).html("提交");
				$('#son').html("");
			}
		}
	});
	function onprogress(evt){
		var loaded = evt.loaded;                  //已经上传大小情况 
        var tot = evt.total;                      //附件总大小
        var size = Number(tot/(1024*1024)).toFixed(2);
        var per = Math.floor(100*loaded/tot);     //已经上传的百分比  
        $("#son").html( "文件总大小:" + size + "MB, 已经上传的百分比: " + per +"%" );
        if(per<20){
        	$("#son").css("width", 30 + "%");
        }else{
        	$("#son").css("width" , per +"%");        	
        }
        if(per==100){
        	$('#new_submit').attr("disabled",false).html("提交");
        }
	}
}
/**
 * 品质惠购
 * @returns
 */
function qualityPruchasingAction(){
	//console.log("品质惠购产品提交!");
	$('#quality_submit').attr("disabled",true).html("提交中");
	var url = "productInfo/saveProductInfo.bsnt?bsnt="+new Date().getTime();
	var formData = new FormData();
	formData.append("productName",$('#quality_product_name').val());
	formData.append("lable",$('#quality_lable option:selected').val());
	formData.append("productClass",$('#quality_product_class').val());
	formData.append("firstPrice",$('#quality_first_price').val());
	formData.append("price",$('#quality_price').val());
	formData.append("oldPrice",$('#quality_old_price').val());
	formData.append("taobaoUrl",$('#quality_taobao_url').val());
	formData.append("kingUrl",$('#quality_king_url').val());
	formData.append("picture0",$('#quality_picture_0')[0].files[0]);
	formData.append("picture1",$('#quality_picture_1')[0].files[0]);
	formData.append("picture2",$('#quality_picture_2')[0].files[0]);
	formData.append("picture3",$('#quality_picture_3')[0].files[0]);
	formData.append("picture4",$('#quality_picture_4')[0].files[0]);
	formData.append("picture5",$('#quality_picture_5')[0].files[0]);	
	$.ajax({
		url:url,
		type:"POST",
		cache:false,
		data:formData,
		processData:false,
		contentType:false,
		success:function(result){
			if(result.status==0){
				//console.log("新品上架上架成功!");
				alert("商品信息添加成功!");
				$('#quality_submit').attr("disabled",false).html("提交");
			}else if(result.status==1){
				alert("注意!!!商品信息添加失败,请检查输入信息!");
				$('#quality_submit').attr("disabled",false).html("提交");
			}else{
				alert(result.message);
				$('#quality_submit').attr("disabled",false).html("提交");
			}
		}
	});
}
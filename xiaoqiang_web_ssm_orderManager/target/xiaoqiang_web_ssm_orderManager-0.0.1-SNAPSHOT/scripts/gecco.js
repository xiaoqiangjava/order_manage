/**
 * 网络爬虫按钮的单击事件
 * @returns
 */
function geccoCrawlerAction(){
//	console.log("提交爬虫地址!");
	$('#gecco_crawler').attr("disabled",true).html("抓&nbsp;取&nbsp;中")
	var url = "gecco/productDetail.bsnt?bsnt="+new Date().getTime();
	var param = {url:$('#gecco_url').val()};
	$.post(url,param,function(result){
		if(result.status==0){
//			console.log("爬取商品成功!");
			alert("获取商品信息成功!");
			$('#gecco_crawler').attr("disabled",false).html("抓&nbsp;&nbsp;&nbsp;取");
		}else{
			alert(result.message);
			$('#gecco_crawler').attr("disabled",false).html("抓&nbsp;&nbsp;&nbsp;取");
		}
	});
}
/**
 * 下载爬取的图片文件到本地
 * @returns
 */
function geccoDownloadAction(){
//	console.log("下载爬取的图片!");
	$('#gecco_submit').submit();
}
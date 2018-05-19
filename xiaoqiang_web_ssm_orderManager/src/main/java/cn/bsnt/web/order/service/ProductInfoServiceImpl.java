package cn.bsnt.web.order.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bsnt.web.order.dao.ProductInfoDao;
import cn.bsnt.web.order.util.BCConvert;
import cn.bsnt.web.order.util.DateFormatUtil;
import cn.bsnt.web.order.util.FirstPriceUtil;
/**
 * 商品信息业务层方法类
 * @author xiaoqiang
 *
 */
@Service("productInfoService")
public class ProductInfoServiceImpl implements ProductInfoService {
	@Resource
	private ProductInfoDao productInfoDao;
	/**
	 * 批量删除商品信息
	 * 包括商品表,套餐表,图片表中的所有信息
	 */
	@Transactional
	public int deleteProduts(String... ids) {
		if(ids == null || ids.length==0){
			throw new RuntimeException("请选择您要删除的产品!");
		}
		for(String id:ids){
			Map<String,String> productInfo = productInfoDao.findProductById(id);
			if(productInfo!=null){
				String productCode = productInfo.get("productCode");
				//System.out.println(productCode);
				productInfoDao.deletePriceByCode(productCode);
				productInfoDao.deletePictureByCode(productCode);
			}
		}
		Map<String ,Object> params = new HashMap<String,Object>();
		params.put("idList", ids);
		int n = productInfoDao.deleteProductByParams(params);
		return n;
	}
	/**
	 * 新品上架,品质惠购
	 * @param productName 商品名称
	 * @param lable 大类分类
	 * @param productClas 小类分类
	 * @param brand 品牌
	 * @param artNumber 型号
	 * @param firstPrice 出厂价
	 * @param price 单件售价
	 * @param oldPrice 单件原件
	 * @param size 规格尺寸
	 * @param color 颜色样式
	 * @param venderUrl 厂家地址
	 * @param taobaoUrl 淘宝地址
	 * @param kingUrl 代金券地址
	 * @param picture0 主图
	 * @param picture1 副图1
	 * @param picture2 副图2
	 * @param picture3 副图3
	 * @param picture4 副图4
	 * @param picture5 详情图
	 * @return
	 */
	@Transactional
	public boolean saveProductInfo(String productName,String lable,String productClass, String brand, String artNumber, String firstPrice, String price,
			String channelPrice,String farePrice,String oldPrice, String size, String color, String venderUrl, String taobaoUrl, String kingUrl,
			String picture0, String picture1, String picture2, String picture3, String picture4, String picture5) {
		Map<String,Object> productInfo = new HashMap<String,Object>();
		Map<String,Object> productPrice = new HashMap<String,Object>();
		Map<String,Object> productPicture = new HashMap<String,Object>();
		List<Map<String,Object>> products = productInfoDao.findProductByName(productName);
		for(Map<String,Object> product : products){
			if(product.get("productCode")!=null){
				throw new RuntimeException("该商品已经存在,不能重复上传商品!");
			}
		}
		String productCode = UUID.randomUUID().toString();
		productPrice.put("productCode", productCode);
		Date date = new Date();
		String addDate = DateFormatUtil.dateFormat(date);
		productInfo.put("addDate", addDate);
		if(productName!=null && !productName.trim().isEmpty()){
			productInfo.put("productCode", productCode);
			productInfo.put("productName", productName.trim());
		}else{
			throw new RuntimeException("您输入的信息有误,请核对后重新提交!");
		}
		if(lable!=null && !lable.trim().isEmpty() && !"0".equals(lable)){
			productInfo.put("lable", lable);
		}else{
			throw new RuntimeException("您输入的信息有误,请核对后重新提交!");
		}
		if(productClass!=null && !productClass.trim().isEmpty()){
			productInfo.put("productClass", productClass);
			productPrice.put("productClass", productClass);
		}else{
			throw new RuntimeException("您输入的信息有误,请核对后重新提交!");
		}
		if(brand!=null && !brand.trim().isEmpty()){
			productInfo.put("brand", brand.trim());
		}else{
			brand = "无";
			productInfo.put("brand", brand);
		}
		if(artNumber!=null && !artNumber.trim().isEmpty()){
			productInfo.put("artNumber", artNumber.trim());
		}else{
			artNumber = "无";
			productInfo.put("artNumber", artNumber);
		}
		if(price!=null && !price.trim().isEmpty()){
			price = BCConvert.qj2bj(price);
			String[] prices = price.split(",");
			List<Double> list = new ArrayList<Double>();
			for(String pri:prices){
				list.add(Double.valueOf(pri));
			}
			Collections.sort(list);
			productInfo.put("price", list.get(0));
		}else{
			throw new RuntimeException("您输入的信息有误,请核对后重新提交!");
		}
		if(!"2".equals(lable)){
			if(channelPrice==null || channelPrice.trim().isEmpty()){
				throw new RuntimeException("您输入的信息有误,请核对后重新提交!");
			}
			if(farePrice==null || farePrice.trim().isEmpty()){
				throw new RuntimeException("您输入的信息有误,请核对后重新提交!");
			}
		}
		//System.out.println(farePrice);
		if(firstPrice!=null && !firstPrice.trim().isEmpty()){
			productInfo.put("firstPrice", firstPrice);
		}else if(!"2".equals(lable)){
			String[] channelArry = channelPrice.split(",");
			List<Double> list = new ArrayList<Double>(); 
			for(String chanel:channelArry){
				list.add(Double.valueOf(chanel));
			}
			Collections.sort(list);
			productInfo.put("firstPrice",FirstPriceUtil.calFirstPrice(list.get(0)+"", farePrice, productInfo.get("price")+""));
		}else{
			throw new RuntimeException("您输入的信息有误,请核对后重新提交!");
		}
		
		if(oldPrice!=null && !oldPrice.trim().isEmpty()){
			productInfo.put("oldPrice", oldPrice);
		}else{
			oldPrice = "0.01";
			productInfo.put("oldPrice", oldPrice);
		}
		if(venderUrl!=null && !venderUrl.trim().isEmpty()){
			productInfo.put("venderUrl", venderUrl.trim());
		}
		if(taobaoUrl!=null && !taobaoUrl.trim().isEmpty()){
			productInfo.put("taobaoUrl", taobaoUrl.trim());
		}
		if(kingUrl!=null && !kingUrl.trim().isEmpty()){
			productInfo.put("kingUrl", kingUrl.trim());
		}
		if(color!=null && !color.trim().isEmpty()){
			String[] colorClass = BCConvert.qj2bj(color.trim()).split(";");
			List<String> list = new ArrayList<String>();
			for(int i=0;i<colorClass.length;i++){
				if(!list.contains(colorClass[i])){
					list.add(colorClass[i]);
				}
			}
			String colorInfo = "";
			for(String colors : list){
				colorInfo += colors+",";
			}
			colorInfo = colorInfo.substring(0, colorInfo.length()-1);
			productInfo.put("color", colorInfo);
		}else{
			color = "无";
			productInfo.put("color", color.trim());
		}
		if(size!=null && !size.trim().isEmpty()){
			String[] sizeClass = BCConvert.qj2bj(size.trim()).split(";");
			List<String> list = new ArrayList<String>();
			for(int i=0;i<sizeClass.length;i++){
				if(!list.contains(sizeClass[i])){
					list.add(sizeClass[i]);
				}
			}
			String sizeInfo = "";
			for(String sizes:list){
				sizeInfo += sizes+",";
			}
			sizeInfo = sizeInfo.substring(0, sizeInfo.length()-1);
			productInfo.put("size", sizeInfo);
		}else{
			size = "无";
			productInfo.put("size", size.trim());
		}
		productInfo.put("storeCount", "有");
		//定义一个图片数组,用来封装所有的图片
		String[] pictures = new String[6];
		if(picture0!=null && !picture0.trim().isEmpty()){
			//System.out.println(picture0);
			pictures[0] = picture0.trim();	
		}else{
			throw new RuntimeException("您输入的信息有误,请核对后重新提交!");
		}
		if(picture1!=null && !picture1.trim().isEmpty()){
			pictures[1] = picture1.trim();
		}else{
			throw new RuntimeException("您输入的信息有误,请核对后重新提交!");
		}
		if(picture2!=null && !picture2.trim().isEmpty()){
			pictures[2] = picture2.trim();		
		}else{
			throw new RuntimeException("您输入的信息有误,请核对后重新提交!");
		}
		if(picture3!=null && !picture3.trim().isEmpty()){
			pictures[3] = picture3.trim();	
		}else{
			throw new RuntimeException("您输入的信息有误,请核对后重新提交!");
		}
		if(picture4!=null && !picture4.trim().isEmpty()){
			pictures[4] = picture4.trim();
		}else{
			throw new RuntimeException("您输入的信息有误,请核对后重新提交!");
		}
		if(picture5!=null && !picture5.trim().isEmpty()){
			pictures[5] = picture5.trim();	
		}else{
			pictures[5] = "http://xiangqing-html.oss-cn-beijing.aliyuncs.com/%E6%B7%98%E5%AE%9D%E7%9B%B4%E8%BF%9E%E5%95%86%E5%93%81%E8%AF%A6%E6%83%85/%E5%93%81%E8%B4%A8%E6%83%A0%E8%B4%AD%E6%8C%87%E5%8D%97.jpg";
		}
		//将图片信息插入到商品图片表中
		int number = 0;
		for(int i=0;i<pictures.length;i++){
			productPicture.put("productCode", productCode);
			productPicture.put("productClass", productClass);
			productPicture.put("pictureUrl", pictures[i]);
			if(i==0){
				productPicture.put("mainPicture", 0);
			}else if(i==5){
				productPicture.put("mainPicture", 2);
			}else{
				productPicture.put("mainPicture", 1);
			}
			productInfoDao.saveProductPicture(productPicture);
			number+=1;
		}
		if(number!=6){
			throw new RuntimeException("添加图片信息失败!");
		}
		//将商品信息插入到商品信息表中
		int n = productInfoDao.saveProductInfo(productInfo);
		//将商品套餐插入到套餐表中
		String[] colorClass = null;
		String[] sizeClass = null;
		String[] priceArry = price.split(",");
		String[] channelArry = null;
		if(!"2".equals(lable)){
			channelArry = channelPrice.split(",");			
		}
		String[] sizesArry  = null;
		String[] colorsArry = null;
		color = BCConvert.qj2bj(color);
		size = BCConvert.qj2bj(size);
		if(!"2".equals(lable) && priceArry.length!=channelArry.length){
			throw new RuntimeException("您输入的信息有误,请核对后重新提交!");
		}
		if((!"无".equals(color) || !"无".equals(size)) && priceArry.length>1 && !"2".equals(lable)){
			price = BCConvert.qj2bj(price);
			colorClass = color.split(";");
			sizeClass = size.split(";");
			if(colorClass.length!=sizeClass.length || sizeClass.length!=priceArry.length){
				throw new RuntimeException("您输入的信息不匹配,请重新输入!");
			}
			for(int i=0;i<priceArry.length;i++){
				String sizes = sizeClass[i].trim();
				String colors = colorClass[i].trim();
				sizesArry  = sizes.split(",");
				colorsArry = colors.split(",");
				productPrice.put("identify", i);
				productPrice.put("firstPrice", FirstPriceUtil.calFirstPrice(channelArry[i], farePrice, priceArry[i]));
				for(int j=0;j<sizesArry.length;j++){
					for(int k=0;k<colorsArry.length;k++){
						productPrice.put("size", sizesArry[j].trim());
						productPrice.put("color", colorsArry[k].trim());
						productPrice.put("price", priceArry[i].trim());
						productPrice.put("storeCount", "有");
						productInfoDao.saveProductPrice(productPrice);
					}
				}
			}
		}else{
			int m = 0;
			String[] sizeArry = size.split(",");
			String[] colorArry = color.split(",");
			productPrice.put("price", price);
			if(priceArry.length>1){
				throw new RuntimeException("您输入的信息有误,请核对后再提交!");
			}
			productPrice.put("identify", 1);
			if("2".equals(lable)){
				productPrice.put("firstPrice", firstPrice);
			}else{
				productPrice.put("firstPrice", FirstPriceUtil.calFirstPrice(channelPrice, farePrice, price));				
			}
			for(int i=0;i<sizeArry.length;i++){
				for(int j=0;j<colorArry.length;j++){
					productPrice.put("size", sizeArry[i].trim());
					productPrice.put("color", colorArry[j].trim());
					productPrice.put("storeCount", "有");
					productInfoDao.saveProductPrice(productPrice);
					m+=1;
				}
			}
			if(m!=sizeArry.length*colorArry.length){
				throw new RuntimeException("添加商品信息失败!");
			}
		}
		return n==1 && number==6;
	}

}

package cn.bsnt.web.order.service;
/**
 * 商品信息业务层接口,封装了商品信息相关的所有业务层方法
 * @author xiaoqiang
 *
 */
public interface ProductInfoService {
	int deleteProduts(String... ids);
	
	boolean saveProductInfo(String productName,String lable,String productClass,String brand,String artNumber,String firstPrice,String price,
			String channelPrice,String farePrice,String oldPrice,String size,String color,String venderUrl,String taobaoUrl,String kingUrl,
			String picture0,String picture1,String picture2,String picture3,String picture4,String picture5);
}

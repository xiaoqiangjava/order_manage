package cn.bsnt.web.order.service;

import java.util.List;
import java.util.Map;
/**
 * 订单表中商品信息业务层接口,为所有订单中的商品信息提供了统一的访问入口
 * @author xiaoqiang
 *
 */
public interface ProductService {
	boolean updateProduct(String shopId,String aliCode,String logisticsCode,String logisticsCompany,String transfer);
	List<Map<String,Object>> listProductInfo(String productId,String productName,String addDate,String brand,
			String artNumber,String storeCount,String venderUrl,String startDate,String endDate);
	
	boolean updateProductInfo(String productId, String productPriceId, String productName, String color, String size, String brand, String artNumber, String storeCount,
			String venderUrl,String taobaoUrl,String kingUrl,String price, String channelPrice, 
			String farePrice);
}

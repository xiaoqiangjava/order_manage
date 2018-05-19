package cn.bsnt.web.order.dao;

import java.util.List;
import java.util.Map;
/**
 * 订单管理模块商品信息DAO接口
 * @author xiaoqiang
 *
 */
public interface ProductDao {
	int updateProductByParams(Map<String,Object> params);
	List<Map<String,Object>> findProductByParams(Map<String,Object> params);
	
	int updateProductInfoByParams(Map<String,Object> params);
	Map<String,String> findProductInfoById(String id);
	int updateProductPriceByParams(Map<String, Object> productPrice);
	List<Map<String, Integer>> findProductPriceIdByProductCode(String productCode);
	List<Map<String,Integer>> findProductInfoByPrice(Map<String, String> products);
	Map<String, Object> findProductPriceByPriceId(String id);
	List<Integer> findProductPriceIdByOther(Map<String, Object> priceParams);
}

package cn.bsnt.web.order.dao;

import java.util.List;
import java.util.Map;
/**
 * 该类用于封装商品表中的增删改查方法
 * 商品管理模块DAO 接口
 * @author xiaoqiang
 *
 */
public interface ProductInfoDao {
	int deleteProductByParams(Map<String,Object> params);

	Map<String, String> findProductById(String id);

	int deletePriceByCode(String productCode);

	int deletePictureByCode(String productCode);

	int saveProductPicture(Map<String, Object> productPicture);

	int saveProductInfo(Map<String, Object> productInfo);

	int saveProductPrice(Map<String, Object> productPrice);

	List<Map<String, Object>> findProductByName(String productName);
}

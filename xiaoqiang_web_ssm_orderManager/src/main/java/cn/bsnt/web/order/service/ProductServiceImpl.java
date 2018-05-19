package cn.bsnt.web.order.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bsnt.web.order.dao.ProductDao;
import cn.bsnt.web.order.util.FirstPriceUtil;
/**
 * 订单表中商品信息业务层方法
 * @author xiaoqiang
 *
 */
@Service("productService")
public class ProductServiceImpl implements ProductService{

	@Resource
	private ProductDao productDao;
	/**
	 * 修改订单商品信息
	 * @param shopId 商品id
	 * @param aliCode 阿里单号
	 * @param logisticsCode 物流号
	 * @param logisticsCompany 物流公司
	 * @param transfer 物流中转
	 * @return 是否更新成功
	 */
	@Transactional
	public boolean updateProduct(String shopId, String aliCode, String logisticsCode, String logisticsCompany,
			String transfer) {
		if(shopId==null || shopId.trim().isEmpty()){
			throw new RuntimeException("商品Id不能为空!");
		}
		Map<String ,Object> params = new HashMap<String, Object>();
		params.put("shopId", shopId);
		if(aliCode!=null && !aliCode.trim().isEmpty()){
			params.put("aliCode", aliCode);
		}
		if(logisticsCode!=null && !logisticsCode.trim().isEmpty()){
			params.put("logisticsCode", logisticsCode);
		}
		if(logisticsCompany!=null && !logisticsCompany.trim().isEmpty()){
			params.put("logisticsCompany", logisticsCompany);
		}
		if(transfer!=null && !transfer.trim().isEmpty()){
			params.put("transfer", transfer);
		}
		int n = productDao.updateProductByParams(params);
		
		return n==1;
	}
	/**
	 * 动态参数查询商品信息 
	 * @param productId 商品id
	 * @param productName 商品名称
	 * @param addDate 添加日期
	 * @param brand 品牌
	 * @param artNumber 型号
	 * @param storeCount 库存	
	 * @param venderUrl 上架地址
	 * @param startDate	开始日期
	 * @param endDate 结束日期
	 * @return 查询结果数据
	 */
	@Transactional(readOnly=true)
	public List<Map<String, Object>> listProductInfo(String productId,String productName,String addDate,String brand,
									String artNumber,String storeCount,String venderUrl,String startDate,String endDate) {
		Map<String,Object> params = new HashMap<String,Object>();
		if(productId!=null && !productId.trim().isEmpty()){
			params.put("productId", productId);
		}
		if(productName!=null && !productName.trim().isEmpty()){
			params.put("productName", "%"+productName+"%");
		}
		if(addDate!=null && !addDate.trim().isEmpty()){
			addDate = addDate+"%";
			params.put("addDate", addDate);
		}
		if(brand!=null && !brand.trim().isEmpty()){
			params.put("brand", brand);
		}
		if(artNumber!=null && !artNumber.trim().isEmpty()){
			params.put("artNumber", artNumber);
		}
		if(storeCount!=null && !storeCount.trim().isEmpty()){
			params.put("storeCount", storeCount);
		}
		if(venderUrl!=null && !venderUrl.trim().isEmpty()){
			params.put("venderUrl", venderUrl);
		}
		if(startDate!=null && !startDate.trim().isEmpty()){
			params.put("startDate",startDate);
		}
		if(endDate!=null && !endDate.trim().isEmpty()){
			params.put("endDate",endDate);
		}
		List<Map<String,Object>> products = productDao.findProductByParams(params);
		return products;
	}
	
	/**
	 * 修改商品表中的商品信息
	 * @param productId 商品id
	 * @param storeCount 库存
	 * @param venderUrl 商家地址
	 * @param price 商品价格
	 * @return 是否更新成功
	 */
	@Transactional
	public boolean updateProductInfo(String productId, String productPriceId, String productName, String color, String size, String brand, String artNumber, String storeCount, String venderUrl,String taobaoUrl,String kingUrl,
									 String price,String channelPrice,String farePrice) {
		Map<String,Object> params = new HashMap<String, Object>();
		if(productId!=null && !productId.trim().isEmpty()){
			params.put("productId", productId);
		}else{
			throw new RuntimeException("商品Id不能为空!");
		}
		if(productName!=null && !productName.trim().isEmpty()){
			params.put("productName", productName);
		}
		if(brand!=null && !brand.trim().isEmpty()){
			params.put("brand", brand);
		}
		if(artNumber!=null && !artNumber.trim().isEmpty()){
			params.put("artNumber", artNumber);
		}
		if(venderUrl!=null && !venderUrl.trim().isEmpty()){
			params.put("venderUrl", venderUrl);
		}
		if(price!=null && !price.trim().isEmpty()){
			params.put("price", price);
		}
		
		if(channelPrice!=null && !channelPrice.trim().isEmpty() && farePrice!=null && !farePrice.trim().isEmpty()){
			params.put("firstPrice", FirstPriceUtil.calFirstPrice(channelPrice, farePrice, price));			
		}
		if(taobaoUrl!=null && !taobaoUrl.trim().isEmpty()){
			params.put("taobaoUrl", taobaoUrl);
		}
		if(kingUrl!=null && !kingUrl.trim().isEmpty()){
			params.put("kingUrl", kingUrl);
		}
		
		int n = productDao.updateProductInfoByParams(params);

		//修改套餐表中的商品信息
		Map<String ,Object> productPrice = new HashMap<String ,Object>();
		
		if(productPriceId!=null && !productPriceId.trim().isEmpty()){
			productPrice.put("productPriceId", productPriceId);
//			System.out.println("套餐表中的Id: " + productPriceId);
		}else{
			throw new RuntimeException("套餐表中的id不能为空!");
		}
		
		if(storeCount!=null && !storeCount.trim().isEmpty()){
			productPrice.put("storeCount", storeCount);
		}
		
		if(size!=null && !size.trim().isEmpty()){
			productPrice.put("productSize", size);
		}
		
		if(color!=null && !color.trim().isEmpty()){
			productPrice.put("color", color);
		}
		
		if(price!=null && !price.trim().isEmpty()){
			productPrice.put("price", price);
		}
		
		if(channelPrice!=null && !channelPrice.trim().isEmpty() && farePrice!=null && !farePrice.trim().isEmpty()){
			productPrice.put("firstPrice", FirstPriceUtil.calFirstPrice(channelPrice, farePrice, price));			
		}
		
		int m = productDao.updateProductPriceByParams(productPrice);
		return n==1 && m==1;
	}
}

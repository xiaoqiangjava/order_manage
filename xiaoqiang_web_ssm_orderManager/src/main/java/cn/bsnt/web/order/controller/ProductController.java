package cn.bsnt.web.order.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bsnt.web.order.service.ProductService;
import cn.bsnt.web.order.util.JsonResult;
/**
 * 产品控制器类,包括对订单表和商品表中产品信息的操作
 * @author xiaoqiang
 *
 */
@Controller
@RequestMapping("product")
public class ProductController extends AbstractController {
	@Resource
	private ProductService productService;
	
	/**
	 * 修改订单商品信息
	 * @param shopId 商品id
	 * @param aliCode 阿里单号
	 * @param logisticsCode 物流号
	 * @param logisticsCompany 物流公司
	 * @param transfer 物流中转
	 * @return 是否更新成功
	 */
	@RequiresPermissions(value="product:update")
	@RequestMapping("updateProduct.bsnt")
	@ResponseBody
	public JsonResult updateProduct(String shopId,String aliCode,String logisticsCode,String logisticsCompany,String transfer){
		//System.out.println(transfer);
		boolean flag = productService.updateProduct(shopId, aliCode, logisticsCode, logisticsCompany, transfer);
		if(flag){
			return new JsonResult(0,flag);
		}else{
			return new JsonResult(1,flag);
		}
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
	@RequiresPermissions(value="productInfo:view")
	@RequestMapping("findProduct.bsnt")
	@ResponseBody
	public JsonResult findProduct(String productId,String productName,String addDate,String brand,
			String artNumber,String storeCount,String venderUrl,String startDate,String endDate){
		List<Map<String,Object>> productList = productService.listProductInfo(productId, productName, 
				addDate, brand, artNumber, storeCount, venderUrl,startDate,endDate);
		if(productList!=null && productList.size()>0){
			return new JsonResult(0,productList);
		}else{
			return new JsonResult(1,productList);
		}
	}
	
	/**
	 * 修改商品表中的商品信息
	 * @param productId 商品id
	 * @param storeCount 库存
	 * @param venderUrl 商家地址
	 * @param price 商品价格
	 * @return 是否更新成功
	 */
	@RequiresPermissions(value="productInfo:update")
	@RequestMapping("updateProductInfo.bsnt")
	@ResponseBody
	public JsonResult updateProductInfo(String productId, String productPriceId, String productName, String color, String size, String brand, String artNumber, 
										String storeCount,String venderUrl,String taobaoUrl,String kingUrl,String price,
										String channelPrice,String farePrice){
		System.out.println("套餐表中的id: " + productPriceId);
		boolean success = productService.updateProductInfo(productId, productPriceId, productName, color, size, brand, artNumber, storeCount, venderUrl,taobaoUrl,kingUrl,price,channelPrice,farePrice);
		if(success){
			return new JsonResult(0,success);
		}else{
			return new JsonResult(1,success);
		}
	}
	
	
}

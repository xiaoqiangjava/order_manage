package cn.bsnt.web.order.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import cn.bsnt.web.order.service.OrderService;
import cn.bsnt.web.order.util.JsonResult;
/**
 * 该类是订单管理的控制器
 * @author xiaoqiang
 *
 */
@Controller
@RequestMapping("order")
public class OrderController extends AbstractController {
	@Resource
	private OrderService orderService;
	
	/**
	 * 查询订单信息
	 * @param orderCode 订单号
	 * @param aliCode 阿里单号
	 * @param orderDate 订单日期
	 * @param receiverName 收件人姓名
	 * @param productName 产品名称
	 * @param logisticsCode 物流号
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @param receiverAdd 收件人地址
	 * @param productCode 产品编码
	 * @param payChnl 支付方式
	 * @param payStatus 支付状态
	 * @param venderUrl 上架地址
	 * @return 结果集json字符串
	 */
	@RequiresPermissions(value="order:vwie")
	@RequestMapping("orderInfo.bsnt")
	@ResponseBody
	public JsonResult listOrderInfo(String username, String orderCode, String aliCode, String orderDate,
			String receiverName, String productName, String logisticsCode, String startDate, String endDate,
			String receiverAdd,String productCode, String payChnl, String[] payStatus, String venderUrl){
//		System.out.println("username:" + username);
		List<Map<String,Object>> orderInfo = orderService.listOrderInfo(username, orderCode, aliCode, orderDate, receiverName, 
				productName, logisticsCode, startDate, endDate, receiverAdd, productCode, payChnl, payStatus, venderUrl);
		if(orderInfo.size()>0){
			return new JsonResult(0,orderInfo);	
		}else{
			return new JsonResult(1,orderInfo);
		}
	}
	/**
	 * 修改订单信息
	 * @param orderId 订单id
	 * @param receiverName 买家姓名
	 * @param receiverPhone 买家电话
	 * @param receiverAdd 买家地址
	 * @return
	 */
	@RequiresPermissions(value="order:update")
	@RequestMapping("updateOrder.bsnt")
	@ResponseBody
	public JsonResult updateOrder(String orderId,String payStatus,String receiverName,
								  String receiverPhone,String receiverAdd){
		//System.out.println("支付状态:"+payStatus);
		//System.out.println("买家地址:"+receiverAdd);
		boolean flag = orderService.updateOrder(orderId,payStatus, receiverName, receiverPhone, receiverAdd);
		if(flag){
			return new JsonResult(0,flag);
		}else{
			return new JsonResult(1,flag);
		}
	}
}

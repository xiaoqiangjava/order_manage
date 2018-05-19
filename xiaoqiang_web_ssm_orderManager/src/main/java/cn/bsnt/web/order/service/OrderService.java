package cn.bsnt.web.order.service;

import java.util.List;
import java.util.Map;
/**
 * 订单管理业务层接口,封装了所有订单管理相关的业务层方法
 * @author xiaoqiang
 *
 */
public interface OrderService {
	List<Map<String,Object>> listOrderInfo(String username, String orderCode, String aliCode, String orderDate,
			String receiverName, String productName, String logisticsCode, String startDate, String endDate,
			String receiverAdd,String productCode, String payChnl, String[] payStatus, String venderUrl);
	
	boolean updateOrder(String orderId,String payStatus,String receiverName,String receiverPhone,String receiverAdd);
}

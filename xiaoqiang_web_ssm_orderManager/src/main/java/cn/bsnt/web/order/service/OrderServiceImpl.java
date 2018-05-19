package cn.bsnt.web.order.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bsnt.web.order.dao.OrderDao;
import cn.bsnt.web.order.dao.UserDao;
import cn.bsnt.web.order.entity.User;
/**
 * 订单管理模块业务层防范
 * @author xiaoqiang
 *
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

	@Resource
	private OrderDao orderDao;
	@Resource
	private UserDao userDao;
	/**
	 * 动态参数查询订单信息
	 */
	@Transactional(readOnly=true)
	public List<Map<String, Object>> listOrderInfo(String username, String orderCode, String aliCode, String orderDate,
			String receiverName, String productName, String logisticsCode, String startDate, String endDate,
			String receiverAdd,String productCode, String payChnl, String[] payStatus, String venderUrl) {
		Map<String,Object> params = new HashMap<String, Object>();
		if(username!=null && !username.trim().isEmpty()){
			User user = userDao.findUserByUsername(username);
			String area = user.getArea();
			if(area==null || area.trim().isEmpty()){
				throw new RuntimeException("您的信息不完善,请联系管理员填写地址信息!");
			}
			if(!area.equals("百事农通")){
				params.put("area", "%" + area + "%");				
			}
		}else{
			throw new RuntimeException("未登录,请重新登录!");
		}
		if(orderCode!=null && !orderCode.trim().isEmpty()){
			params.put("orderCode", orderCode);	
		}
		if(aliCode!=null && !aliCode.trim().isEmpty()){
			//System.out.println(aliCode);
			params.put("aliCode", aliCode);
		}
		if(orderDate!=null && !orderDate.trim().isEmpty()){
			params.put("orderDate", orderDate+"%");			
		}
		if(receiverName!=null && !receiverName.trim().isEmpty()){
			params.put("receiverName", receiverName);
		}
		if(productName!=null && !productName.trim().isEmpty()){
			params.put("productName", "%"+productName+"%");
		}
		if(logisticsCode!=null && !logisticsCode.trim().isEmpty()){
			params.put("logisticsCode", logisticsCode);
		}
		if(startDate!=null && !startDate.trim().isEmpty()){
			params.put("startDate", startDate);
		}
		if(endDate!=null && !endDate.trim().isEmpty()){
			params.put("endDate", endDate);
		}
		if(receiverAdd!=null && !receiverAdd.trim().isEmpty()){
			params.put("receiverAdd", "%"+receiverAdd+"%");
		}
		if(productCode!=null && !productCode.trim().isEmpty()){
			params.put("productCode", productCode);
		}
		if(payChnl!=null && !payChnl.trim().isEmpty()){
			params.put("payChnl", payChnl);
		}
		if(payStatus!=null && payStatus.length!=0){
			for(int i=0;i<payStatus.length;i++){
				String status = payStatus[i];
				params.put("status"+i, status);
				//System.out.println(status);
			}
		}
		if(venderUrl!=null && !venderUrl.trim().isEmpty()){
			params.put("venderUrl", venderUrl);
		}
		List<Map<String,Object>> orderInfo = orderDao.findOrderInfoByParams(params);
		return orderInfo;
	}
	/**
	 * 动态参数修改订单信息
	 */
	@Transactional
	public boolean updateOrder(String orderId,String payStatus, String receiverName, String receiverPhone, String receiverAdd) {
		if(orderId==null || orderId.trim().isEmpty()){
			throw new RuntimeException("订单ID不能为空!");
		}
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("orderId", orderId);
		if(receiverName!=null && !receiverName.trim().isEmpty()){
			params.put("receiverName", receiverName);
		}
		if(receiverPhone!=null && !receiverPhone.trim().isEmpty()){
			params.put("receiverPhone", receiverPhone);
		}
		if(receiverAdd !=null && !receiverAdd.trim().isEmpty()){
			params.put("receiverAdd", receiverAdd);
		}
		if(payStatus!=null && !payStatus.trim().isEmpty()){
			if(payStatus.trim().equals("待付款")){
				payStatus = "0";
			}else if(payStatus.trim().equals("已付款")){
				payStatus = "1";
			}else if(payStatus.trim().equals("待发货")){
				payStatus = "2";
			}else if(payStatus.trim().equals("待收货")){
				payStatus = "3";
			}else if(payStatus.trim().equals("待评价")){
				payStatus = "4";
			}else if(payStatus.trim().equals("退款/售后")){
				payStatus = "5";
			}else if(payStatus.trim().equals("退款成功")){
				payStatus = "6";
			}else if(payStatus.trim().equals("作废")){
				payStatus = "7";
			}else{
				throw new RuntimeException("您输入的信息有误,不能修改订单!");
			}
			params.put("payStatus", payStatus);
		}
		int n = orderDao.updateOrderByParams(params);
		return n==1;
	}

}

package cn.bsnt.web.order.dao;

import java.util.List;
import java.util.Map;
/**
 * 订单管理模块DAO接口
 * @author xiaoqiang
 *
 */
public interface OrderDao {
	List<Map<String,Object>> findOrderInfoByParams(Map<String,Object> params);
	int updateOrderByParams(Map<String,Object> params);
}

package cn.bsnt.web.order.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.bsnt.web.order.dao.CodeDao;
import cn.bsnt.web.order.dao.OrderDao;
import cn.bsnt.web.order.dao.PermissionDao;
import cn.bsnt.web.order.dao.ProductDao;
import cn.bsnt.web.order.dao.ProductInfoDao;
import cn.bsnt.web.order.dao.RoleDao;
import cn.bsnt.web.order.dao.RolePermissionDao;
import cn.bsnt.web.order.dao.UserDao;
import cn.bsnt.web.order.dao.UserRoleDao;
import cn.bsnt.web.order.entity.Permission;
import cn.bsnt.web.order.entity.Role;
import cn.bsnt.web.order.entity.RolePermission;
import cn.bsnt.web.order.entity.User;
import cn.bsnt.web.order.entity.UserRole;
import cn.bsnt.web.order.service.CodeService;
import cn.bsnt.web.order.service.OrderService;
import cn.bsnt.web.order.service.PermissionService;
import cn.bsnt.web.order.service.ProductInfoService;
import cn.bsnt.web.order.service.ProductService;
import cn.bsnt.web.order.service.RoleService;
import cn.bsnt.web.order.service.UserService;

public class TestCase {
	ClassPathXmlApplicationContext ctx;
	@Before
	public void init(){
		ctx = new ClassPathXmlApplicationContext("spring-mvc.xml","spring-service.xml","spring-mybatis.xml","spring-shiro.xml");
	}
	@Test
	public void testFindUserByUserId(){
		UserDao userDao = ctx.getBean("userDao",UserDao.class);
		Integer userId= 1;
		User user = userDao.findUserByUserId(userId);
		System.out.println(user);
	}
	@Test
	public void testFindUserByUsername(){
		UserDao userDao = ctx.getBean("userDao",UserDao.class);
		String username = "admin";
		User user = userDao.findUserByUsername(username);
		System.out.println(user);
	}
	@Test
	public void testLogin(){
		UserService userService = ctx.getBean("userService",UserService.class);
		String username = "admin";
		String password = "123456";
		User user = userService.login(username, password);
		System.out.println(user);
	}
	@Test
	public void testAddUser(){
		UserDao userDao = ctx.getBean("userDao",UserDao.class);
		User user = new User();
		user.setName("name");
		user.setPassword("123456");
		user.setNick("");
		System.out.println(user);
		int n = userDao.addUser(user);
		System.out.println(n);
	}
	@Test
	public void testRegist(){
		UserService service = ctx.getBean("userService",UserService.class);
		String username="test1";
		String nick = "";
		String password = "1234";
		String confirm = "1234";
		User user = service.regist(username, nick, password, confirm, null);
		System.out.println(user);
		
	}
	@Test
	public void testFindOrderInfoByParams(){
		OrderDao orderDao = ctx.getBean("orderDao",OrderDao.class);
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("payStatus","0");
		params.put("startDate", "2017-02-25");
		params.put("endDate", "2017-02-27");
		//params.put("orderDate", "2017-02-25%");
		List<Map<String,Object>> list = orderDao.findOrderInfoByParams(params);
		for(Map<String,Object> order:list){
			System.out.println(order);
		}
		System.out.println(null+"%");
	}
	@Test
	public void testListOrderInfo(){
		OrderService orderService = ctx.getBean("orderService",OrderService.class);
		String orderCode = null;
		String aliCode = null;
		String orderDate = "2017-02-25";
		String receiverName = null;
		String productName = null;
		String logisticsCode = null;
		String startDate = null;
		String endDate = null;
		String receiverAdd = null;
		String payChnl = null;
		String status = null;
		String[] venderUrl = null;
		String productCode = null;
		List<Map<String,Object>> list = orderService.listOrderInfo(null, orderCode, aliCode, orderDate, receiverName, 
				productName, logisticsCode, startDate, endDate, receiverAdd, 
				payChnl, status, venderUrl, productCode);
		for(Map<String,Object> order : list){
			System.out.println(order);
		}
	}
	
	@Test
	public void testUpdateOrderByParams(){
		OrderDao orderDao = ctx.getBean("orderDao",OrderDao.class);
		Map<String,Object> params = new HashMap<String,Object>();
		
		String receiverName = "张强龙";
		params.put("receiverName", receiverName);
		String receiverPhone = "15095328593";
		params.put("receiverPhone", receiverPhone);
		String receiverAdd = "甘肃银行";
		params.put("receiverAdd", receiverAdd);
		params.put("orderId", "2");
		params.put("payStatus", "7");
		int n = orderDao.updateOrderByParams(params);
		System.out.println(n);
	}
	
	@Test
	public void testUpdateProductByParams(){
		ProductDao productDao = ctx.getBean("productDao",ProductDao.class);
		Map<String ,Object> params = new HashMap<String,Object>();
		params.put("shopId", "1");
		params.put("aliCode", "123456778313");
		params.put("logisticsCompany", "中通");
		int n = productDao.updateProductByParams(params);
		System.out.println(n);
	}
	
	@Test
	public void testFindProductByParams(){
		ProductDao productDao = ctx.getBean("productDao",ProductDao.class);
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("startDate", "2016-07-18");
		params.put("endDate", "2016-07-19");
		List<Map<String,Object>> map = productDao.findProductByParams(params);
		for(Map<String,Object> product : map){
			System.out.println(product);
		}
	}
//	@Test
//	public void testUpdateProductInfoByParams(){
//		ProductService productService = ctx.getBean("productService",ProductService.class);
//		String productId = "7253";
//		String storeCount = "有";
//		String venderUrl = "无";
//		String price = "296.00";
//		String channelPrice="289.00";
//		String farePrice = "14.00";
//		String frimePirce = "";
//		String nowPrice = "312.00";
////		boolean n = productService.updateProductInfo(null,null,productId, storeCount, venderUrl,price, channelPrice, farePrice,frimePirce,nowPrice);
////		System.out.println(n);
//	}
	
	@Test
	public void testDeleteProductByParams(){
		ProductInfoDao productInfoDao = ctx.getBean("productInfoDao",ProductInfoDao.class);
		Map<String,Object> params = new HashMap<String,Object>();
		String[] idList = {"6","7","8","asdf12","9"};
		params.put("idList", idList);
		int n = productInfoDao.deleteProductByParams(params);
		System.out.println(n);
	}
	
	@Test
	public void testDeleteProducts(){
		ProductInfoService service = ctx.getBean("productInfoService",ProductInfoService.class);
		String[] ids = {"1","2","3"};
		int n = service.deleteProduts(ids);
		System.out.println(n);
	}
	@Test
	public void testSaveProductInfo(){
		ProductInfoService service = ctx.getBean("productInfoService",ProductInfoService.class);
		String productName = "甘肃百事农通品质惠购测试产品asas无sdfsdf";
		String lable = "2";
		String productClass = "27";
		String brand = "哈哈测试";
		String artNumber = "我要成功啦";
		String firstPrice = "188.00";
		String price = "66.00";
		String oldPrice = "";
		String channelPrice = "45.00";
		String farePrice = "20.00";
		String size = "无";
		String color = "无";
		String venderUrl = "https://detail.1688.com/offer/539896956900.html?spm=a2615.2177701.0.0.Pn1q11";
		String taobaoUrl = "";
		String kingUrl = "";
		String picture0 = "http://bsnt20151208.oss-cn-beijing.aliyuncs.com/%E6%96%87%E4%BD%93%E5%81%A5%E8%BA%AB%2F%E5%81%A5%E8%BA%AB%E5%99%A8%E6%9D%90%2F%E5%9B%BE%E7%89%87%2F%E9%A3%9E%E5%B0%94%E9%A1%BF5%2F%E4%B8%BB%E5%9B%BE%20%20(1).jpg";
		String picture1 = "http://bsnt20151208.oss-cn-beijing.aliyuncs.com/%E6%96%87%E4%BD%93%E5%81%A5%E8%BA%AB%2F%E5%81%A5%E8%BA%AB%E5%99%A8%E6%9D%90%2F%E5%9B%BE%E7%89%87%2F%E9%A3%9E%E5%B0%94%E9%A1%BF5%2F%E4%B8%BB%E5%9B%BE%20%20(2).jpg";
		String picture2 = "http://bsnt20151208.oss-cn-beijing.aliyuncs.com/%E6%96%87%E4%BD%93%E5%81%A5%E8%BA%AB%2F%E5%81%A5%E8%BA%AB%E5%99%A8%E6%9D%90%2F%E5%9B%BE%E7%89%87%2F%E9%A3%9E%E5%B0%94%E9%A1%BF5%2F%E4%B8%BB%E5%9B%BE%20%20(3).jpg";
		String picture3 = "http://bsnt20151208.oss-cn-beijing.aliyuncs.com/%E6%96%87%E4%BD%93%E5%81%A5%E8%BA%AB%2F%E5%81%A5%E8%BA%AB%E5%99%A8%E6%9D%90%2F%E5%9B%BE%E7%89%87%2F%E9%A3%9E%E5%B0%94%E9%A1%BF5%2F%E4%B8%BB%E5%9B%BE%20%20(4).jpg";
		String picture4 = "http://bsnt20151208.oss-cn-beijing.aliyuncs.com/%E6%96%87%E4%BD%93%E5%81%A5%E8%BA%AB%2F%E5%81%A5%E8%BA%AB%E5%99%A8%E6%9D%90%2F%E5%9B%BE%E7%89%87%2F%E9%A3%9E%E5%B0%94%E9%A1%BF5%2F%E4%B8%BB%E5%9B%BE%20%20(5).jpg";
		String picture5 = "http://bsnt20151208.oss-cn-beijing.aliyuncs.com/%E6%96%87%E4%BD%93%E5%81%A5%E8%BA%AB%2F%E5%81%A5%E8%BA%AB%E5%99%A8%E6%9D%90%2F%E5%9B%BE%E7%89%87%2F%E9%A3%9E%E5%B0%94%E9%A1%BF5%2F%E8%AF%A6%E6%83%85%E6%95%B4%E5%BC%A0.jpg";
		boolean success = service.saveProductInfo(productName,lable, productClass, brand, artNumber, firstPrice, price,channelPrice,farePrice, oldPrice, 
				size, color, venderUrl, taobaoUrl,kingUrl, picture0, picture1, picture2, picture3, picture4, picture5);
		System.out.println(success);
	}
	
	@Test
	public void testSplit(){
		String str = "我们都有一个家;adb;addewe;";
		String[] strArry = str.split(";");
		System.out.println(Arrays.toString(strArry));
		List<Double> list = new ArrayList<Double>();
		list.add(23.00);
		list.add(34.00);
		list.add(14.00);
		Collections.sort(list);
		System.out.println(list.get(0));
		String[] prices = {"23.00","54.00","12.00"};
		List<Double> lists = new ArrayList<Double>();
		for(String pri:prices){
			lists.add(Double.valueOf(pri));
		}
		Collections.sort(lists);
		System.out.println(lists.get(0));
	}
	@Test
	public void testFindCode(){
		CodeDao codeDao = ctx.getBean("codeDao",CodeDao.class);
		List<Map<String,Object>> codes = codeDao.findAllCodes();
		for(Map<String,Object> code : codes){
			System.out.println(code);
		}
	}
	
//	@Test
//	public void testFindUserRole(){
//		CodeDao codeDao = ctx.getBean("codeDao", CodeDao.class);
//		List<Map<String, Object>> userRoles = codeDao.findUserRole();
//		for(Map<String, Object> userRole : userRoles){
//			System.out.println(userRole);
//		}
//		
//		List<Map<String, Object>> rolePermissions = codeDao.findRolePermission();
//		for(Map<String, Object> rolePermission : rolePermissions){
//			System.out.println(rolePermission);
//		}
//	}
	
	@Test
	public void testFindUserRoles(){
		CodeService codeService = ctx.getBean("codeService", CodeService.class);
		
	}
	@Test
	public void testFindRolesByUsername(){
		RoleDao roleDao = ctx.getBean("roleDao", RoleDao.class);
		String username = "xiaoqiang";
		List<Role> list = roleDao.findRolesByUsername(username);
		System.out.println(list.size());
		for(Role role : list){
			System.out.println(role);
		}
	}
	
	@Test
	public void testFindPermissionsByRoleId(){
		PermissionDao permissionDao = ctx.getBean("permissionDao", PermissionDao.class);
		Integer roleId = 1;
		List<Permission> list = permissionDao.findPermissionsByRoleId(roleId);
		for(Permission permission : list){
			System.out.println(permission);
		}
		
	}
	
	@Test
	public void testFindAllUserRoles(){
		UserRoleDao userRoleDao = ctx.getBean("userRoleDao", UserRoleDao.class);
		List<UserRole> userRoles = userRoleDao.findAllUserRoles(null);
		for(UserRole userRole : userRoles){
			System.out.println("用户角色信息:" + userRole);
		}
	}
	@Test
	public void testFindAllRolePermissions(){
		RolePermissionDao rolePermissionDao = ctx.getBean("rolePermissionDao", RolePermissionDao.class);
		List<RolePermission> rolePermissions = rolePermissionDao.findAllRolePermissions(null);
		for(RolePermission rolePermission : rolePermissions){
			System.out.println("角色权限信息: " + rolePermission);
		}
	}
	
	@Test
	public void testCreateRole(){
		RoleService roleService = ctx.getBean("roleService", RoleService.class);
		String roleName = "areaManager";
		String roleDesc = "区域管理员,主要管理自己的区域";
		boolean n = roleService.createRole(roleName, roleDesc);
		System.out.println(n);
	}
	
	@Test
	public void testUpdatePermission(){
		PermissionService permissionService = ctx.getBean("permissionService", PermissionService.class);
		String permissionId = "1";
		String permissionName = "order:view";
		String permissionDesc = "该权限用于查询订单时的权限控制标识符";
		boolean n = permissionService.updatePermission(permissionId, permissionName, permissionDesc);
		System.out.println(n);
	}

	@Test
	public void testUpdateProductPrice(){
		ProductDao productDao = ctx.getBean("productDao", ProductDao.class);
		Map<String, Object> productPrice = new HashMap<String, Object>();
		productPrice.put("productSize", "大");
		productPrice.put("productPriceId", 48);
		int n = productDao.updateProductPriceByParams(productPrice);
		System.out.println(n);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}

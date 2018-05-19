package cn.bsnt.web.order.service;

import cn.bsnt.web.order.entity.User;
/**
 * 用户信息业务层接口,为所有的用户信息操作提供了统一的接口,封装了其相关的业务层方法
 * @author xiaoqiang
 *
 */
public interface UserService {
	User login(String username,String password);

	User regist(String username, String nick, String password, String confirm, String area);

	boolean checkToken(Integer userId, String token);
}

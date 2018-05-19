package cn.bsnt.web.order.dao;


import java.util.Map;

import cn.bsnt.web.order.entity.User;

public interface UserDao {
	User findUserByUserId(Integer userId);
	User findUserByUsername(String username);
	int addUser(User user);
	int updateUserByParams(Map<String, Object> userInfo);
}

package cn.bsnt.web.order.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bsnt.web.order.dao.UserDao;
import cn.bsnt.web.order.dao.UserRoleDao;
import cn.bsnt.web.order.entity.User;
import cn.bsnt.web.order.exception.PasswordException;
import cn.bsnt.web.order.exception.UserNameException;
import cn.bsnt.web.order.util.Md5Util;
/**
 * 账号信息业务层方法
 * @author xiaoqiang
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserDao userDao;
	
	@Resource
	private UserRoleDao userRoleDao;
	/**
	 * 用户登录方法
	 */
	@Transactional
	public User login(String username, String password) {
		if(username==null || username.trim().isEmpty()){
			throw new UserNameException("用户名不能为空!");
		}
		if(password==null || password.trim().isEmpty()){
			throw new PasswordException("密码不能为空!");
		}
		User user = userDao.findUserByUsername(username);
		if(user==null){
			throw new UserNameException("该用户不存在!");
		}
		if(Md5Util.md5Salt(password).equals(user.getPassword())){
			String token = UUID.randomUUID().toString();
			user.setToken(token);
			Map<String,Object> userInfo = new HashMap<String,Object>();
			userInfo.put("token", token);
			userInfo.put("userId", user.getId());
			userDao.updateUserByParams(userInfo);
			return user;
		}
		throw new PasswordException("密码不正确!");
	}
	/**
	 * 用户注册方法
	 */
	@Transactional
	public User regist(String username, String nick, String password, String confirm, String area) {
		if(username==null || username.trim().isEmpty()){
			throw new UserNameException("账号不能为空!");
		}
		if(nick==null || nick.trim().isEmpty()){
			nick = username;
		}
		if(password==null || password.trim().isEmpty()){
			throw new PasswordException("密码不能为空!");
		}
		if(confirm==null || password.trim().isEmpty()){
			throw new PasswordException("请确认密码!");
		}
		if(!password.equals(confirm)){
			throw new PasswordException("两次密码不一致!");
		}
		if(area==null || area.trim().isEmpty()){
			throw new RuntimeException("所属区域必须填写!");
		}
		User user = userDao.findUserByUsername(username);
		if(user!=null){
			throw new UserNameException("该用户已存在!");
		}
		password = Md5Util.md5Salt(password);
		user = new User();
		String token = "";
		Timestamp creatTime = new Timestamp(new Date().getTime());
		user.setName(username);
		user.setPassword(password);
		user.setNick(nick);
		user.setToken(token);
		user.setArea(area);
		user.setCreatTime(creatTime);
		userDao.addUser(user);
		Integer userId = userDao.findUserByUsername(username).getId();
//		System.out.println("注册时该用户的Id: " + userId);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("roleId", 3);
		userRoleDao.insertUserRole(params);
		return userDao.findUserByUsername(username);
	}
	/**
	 * 检查该用户携带的token与数据库中保存的token是否是同一个token
	 */
	@Transactional(readOnly=true)
	public boolean checkToken(Integer userId, String token) {
		if(userId!=null && token!=null){
			User user = userDao.findUserByUserId(userId);
			if(user!=null && user.getToken().equals(token)){
				return true;
			}
		}
		return false;
	}

}

package cn.bsnt.web.order.controller;


import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bsnt.web.order.entity.User;
import cn.bsnt.web.order.exception.PasswordException;
import cn.bsnt.web.order.exception.UserNameException;
import cn.bsnt.web.order.service.UserService;
import cn.bsnt.web.order.util.JsonResult;
import cn.bsnt.web.order.util.Md5Util;
/**
 * 用户模块控制器,包括登录和注册方法
 * @author xiaoqiang
 *
 */
@Controller
@RequestMapping("/user")
public class UserController extends AbstractController{
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
	@Resource
	private UserService userService;
	
	@Resource
	private org.apache.shiro.mgt.SecurityManager securityManager;
	/**
	 * 登录方法
	 * @param username
	 * @param password
	 * @return 登录的用户信息
	 */
	@RequestMapping("login.bsnt")
	@ResponseBody
	public JsonResult login(String username,String password){
		SecurityUtils.setSecurityManager(securityManager);
		LOG.debug("username:" + username);
		User user = userService.login(username, password);
//		LOG.debug("password:" + password);
		String shiroPassword = Md5Util.md5Salt(password);
		UsernamePasswordToken token = new UsernamePasswordToken(username, shiroPassword);
		SecurityUtils.getSubject().login(token);
		return new JsonResult(0,user);
	}
	
	/**
	 * 注册方法
	 * @param username 用户名
	 * @param nick 昵称
	 * @param password 密码
	 * @param confirm 确认密码
	 * @return 注册的用户信息
	 */
	@RequestMapping("regist.bsnt")
	@ResponseBody
	public JsonResult regist(String username,String nick,String password,String confirm,String area){
		User user = userService.regist(username,nick,password,confirm, area);
		return new JsonResult(0,user);
	}
	/**
	 * 捕获用户名异常
	 * @param e 用户名异常
	 * @return 异常信息
	 */
	@ExceptionHandler(UserNameException.class)
	@ResponseBody
	public JsonResult usernameExp(UserNameException e){
		e.printStackTrace();
		LOG.error(e.getMessage(), e);
		return new JsonResult(1,e);
	}
	/**
	 * 捕获密码异常
	 * @param 密码异常
	 * @return 异常信息
	 */
	@ExceptionHandler(PasswordException.class)
	@ResponseBody
	public JsonResult pwdExp(PasswordException e){
		e.printStackTrace();
		LOG.error(e.getMessage(), e);
		return new JsonResult(2,e);
	}
	
}

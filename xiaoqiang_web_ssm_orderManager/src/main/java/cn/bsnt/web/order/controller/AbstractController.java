package cn.bsnt.web.order.controller;

import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bsnt.web.order.util.JsonResult;
/**
 * 该类是一个抽象类,用来捕获所有控制器抛出的异常
 * @author xiaoqiang
 *
 */
public abstract class AbstractController {
	private static final Logger LOG = LoggerFactory.getLogger(AbstractController.class);
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public JsonResult exp(Exception e){
		e.printStackTrace();
		LOG.error(e.getMessage(), e);
		return new JsonResult(3,e);
	}
	
	@ExceptionHandler(UnauthorizedException.class)
	@ResponseBody
	public JsonResult exp(UnauthorizedException e){
		e.printStackTrace();
		LOG.error(e.getMessage(), e);
		return new JsonResult(3,new UnauthorizedException("哥,您访问的资源需要授权,请联系系统管理员获取相应的权限!"));
	}
	
	@ExceptionHandler(UnauthenticatedException.class)
	@ResponseBody
	public JsonResult exp(UnauthenticatedException e){
		e.printStackTrace();
		LOG.error(e.getMessage(), e);
		return new JsonResult(6, new UnauthenticatedException("哥,您的身份没有通过系统认证或者认证信息已过期,请提交账号/密码进行身份验证!"));
	}

}

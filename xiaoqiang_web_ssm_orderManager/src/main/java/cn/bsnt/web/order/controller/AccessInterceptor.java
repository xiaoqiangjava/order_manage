package cn.bsnt.web.order.controller;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.bsnt.web.order.service.UserService;
/**
 * 拦截器,用户拦截非法访问的uri
 * @author xiaoqiang
 *
 */
@Component
public class AccessInterceptor implements HandlerInterceptor {
	@Resource
	private UserService userService;
	public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object arg2, Exception arg3)
			throws Exception {

	}

	public void postHandle(HttpServletRequest req, HttpServletResponse res, Object arg2, ModelAndView arg3)
			throws Exception {

	}

	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object arg2) throws Exception {
		Cookie[] cookies = req.getCookies();
		Integer userId = null;
		String token = null;
		for(Cookie cookie : cookies){
			if("userId".equals(cookie.getName())){
				userId = Integer.valueOf(cookie.getValue());
			}
			if("token".equals(cookie.getName())){
				token = cookie.getValue();
			}
		}
		String json = "{\"status\":6,\"data\":null,\"message\":\"您的账号已在其他地方登录,请重新登录!\"}";
		if(token==null || userId==null){
			res.setContentType("text/html;charset=utf-8");
			res.getWriter().println(json);
			return false;
		}
		boolean pass = userService.checkToken(userId, token);
		if(pass){
			return true;
		}else{
			res.setContentType("text/html;charset=utf-8");
			res.getWriter().println(json);
			return false;
		}
	}

}

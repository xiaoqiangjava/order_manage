package cn.bsnt.web.order.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.bsnt.web.order.service.UserService;
/**
 * 过滤器类,用于授权,拦截未登陆的用户
 * @author xiaoqiang
 *
 */
public class AccessFilter implements Filter {

	public void destroy() {
		

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		Integer userId = null;
		String token = null;
		Cookie[] cookies = req.getCookies();
		if(cookies!=null){
			for(Cookie cookie:cookies){
				//System.out.println(cookie.getName()+":"+cookie.getValue());
				if("userId".equals(cookie.getName())){
					userId = Integer.parseInt(cookie.getValue());
				}
				if("token".equals(cookie.getName())){
					token = cookie.getValue();
				}
			}	
		}
		if(userId==null || token==null){
			res.sendRedirect("login.html");
			return;
		}
		UserService service = ctx.getBean("userService",UserService.class);
		boolean pass = service.checkToken(userId,token);
		if(pass){
			chain.doFilter(req, res);
		}else{
			res.sendRedirect("login.html");
		}
	}
	private WebApplicationContext ctx;
	public void init(FilterConfig config) throws ServletException {
		//获取当前spring容器
		ctx = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());

	}

}

package cn.wzz.BookManager.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import cn.wzz.BookManager.model.Ticket;
import cn.wzz.BookManager.service.TicketService;
import cn.wzz.BookManager.utils.CookieUtils;

@Component
public class LoginInterceptor implements HandlerInterceptor {

	@Autowired
	private TicketService ticketservice;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		
		//没有票去登录
		String t = CookieUtils.getCookie("t", request);
		if(StringUtils.isEmpty(t)) {
			response.sendRedirect("/users/login");
			return false;
		}
		//无效t票，去登陆
		Ticket ticket = ticketservice.getTicket(t);
		if(ticket == null) {
			response.sendRedirect("/users/login");
			return false;
		}
		
		//过期票，去登陆
		if(ticket.getExpiredAt().before(new Date())) {
			response.sendRedirect("/users/login");
			return false;
		}
		return true;
	}
	
	

}

package cn.wzz.BookManager.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import cn.wzz.BookManager.model.Ticket;
import cn.wzz.BookManager.model.User;
import cn.wzz.BookManager.service.TicketService;
import cn.wzz.BookManager.service.UserService;
import cn.wzz.BookManager.utils.ConcurrentUtils;
import cn.wzz.BookManager.utils.CookieUtils;

@Component
public class HostInfoInterceptor implements HandlerInterceptor {

	@Autowired
	private TicketService ticketservice;
	
	@Autowired
	private UserService userservice;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		String t = CookieUtils.getCookie("t", request);
		if(!StringUtils.isEmpty(t)) {
			Ticket ticket = ticketservice.getTicket(t);
			if(ticket != null && ticket.getExpiredAt().before(new Date())) {
				User host = userservice.getUser(ticket.getUserId());
				ConcurrentUtils.setHost(host);
			}
		
		}
		return true;
	}
	
	

}

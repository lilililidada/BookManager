package cn.wzz.BookManager.biz;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wzz.BookManager.model.Ticket;
import cn.wzz.BookManager.model.User;
import cn.wzz.BookManager.model.exceptions.LoginRegisterException;
import cn.wzz.BookManager.service.TicketService;
import cn.wzz.BookManager.service.UserService;
import cn.wzz.BookManager.utils.ConcurrentUtils;
import cn.wzz.BookManager.utils.MD5;
import cn.wzz.BookManager.utils.TicketUtils;

@Service
public class LoginBiz {

	/**
	 * 登录逻辑，先检查邮箱和密码，然后更新t票。
	 * @return 返回最新t票
	 * @throws Exception 账号密码错误
	 * 
	 * */

	@Autowired
	private UserService us;
	@Autowired
	private TicketService ts;
	
	public String login(String email, String password) throws Exception{
		User user = us.getUser(email);
		
		//登录信息
		if(user == null) 
			throw new LoginRegisterException("邮箱不存在");
		if(!StringUtils.equals(MD5.next(password), user.getPassword()))
			throw new LoginRegisterException("密码错误");
		
		//检查ticket
		Ticket t = ts.getTicket(user.getId());
		//如果没有t票，生成一个
		if(t == null) {
			t = TicketUtils.next(user.getId());
			ts.addTicket(t);
			return t.getTicket();
		}
		if(t.getExpiredAt().before(new Date())) {
			//删除
			ts.deleteTicket(user.getId());
		}
		
		t = TicketUtils.next(user.getId());
		ts.addTicket(t);
		
		ConcurrentUtils.setHost(user);
		return t.getTicket();
	}
}

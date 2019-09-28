package cn.wzz.BookManager.service;

import org.springframework.stereotype.Service;

import cn.wzz.BookManager.model.User;
import cn.wzz.BookManager.utils.ConcurrentUtils;

@Service
public class HostHolder {

	public User getUser() {
		return ConcurrentUtils.getHost();
	}
	
	public void setUser(User user) {
		ConcurrentUtils.setHost(user);
	}
	

}

package cn.wzz.BookManager.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wzz.BookManager.dao.UserDAO;
import cn.wzz.BookManager.model.User;

@Service
public class UserService {

	@Autowired
	private UserDAO dao;
	
	private static final Logger logger = LoggerFactory.getLogger(BookService.class);
	
	public int addUser(User user) {
		try {
			return dao.addUser(user);
		}catch(Exception e) {
			logger.error(e.getMessage());
			return 0;
		}
		
	}
	
	public User getUser(int id) {
		try {
			return dao.selectById(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	
	public User getUser(String email) {
		try {
			return dao.selectByEmail(email);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	public void uodatePassword(User user) {
		try {
			dao.updatePassword(user);
		} catch (Exception e) {
			logger.error(e.getMessage());// TODO: handle exception
			return;
		}
	}
}

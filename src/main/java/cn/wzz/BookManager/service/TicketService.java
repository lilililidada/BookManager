package cn.wzz.BookManager.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wzz.BookManager.dao.TicketDAO;
import cn.wzz.BookManager.model.Ticket;

@Service
public class TicketService {

	@Autowired
	private TicketDAO dao;
	
	private static final Logger logger = LoggerFactory.getLogger(TicketService.class);
	
	public void addTicket(Ticket ticket) {
		try {
			dao.addTicket(ticket);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	public Ticket getTicket(String ticket) {
		try {
			return dao.selectByTicket(ticket);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	public Ticket getTicket(int userId) {
		try {
			return dao.selectByUserid(userId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	public void deleteTicket(int id) {
		try {
			dao.deleteById(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	public void deleteTicket(String ticket) {
		try {
			dao.deleteByTicket(ticket);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	

}

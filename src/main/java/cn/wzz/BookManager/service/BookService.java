package cn.wzz.BookManager.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wzz.BookManager.dao.BookDAO;
import cn.wzz.BookManager.model.Book;
import cn.wzz.BookManager.model.enums.BookStatusEnum;

@Service
public class BookService {

	@Autowired private BookDAO dao;
	
	private static final Logger logger = LoggerFactory.getLogger(BookService.class);
	
	public List<Book> getAllBooks(){
		try {
			return dao.selectAll();
		}catch(Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	public Book getBook(int id) {
		try {
			return dao.selectBookById(id);
		}catch(Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	public Book getBook(String name) {
		try {
			return dao.selectBookByName(name);
		}catch(Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	
	public int addBook(Book book) {
		try {
			return dao.addBook(book);
		}catch(Exception e) {
			logger.error(e.getMessage());
			return 0;
		}
	}
	
	public void deleteBook(int id , int status) {
		dao.updateBookStatus(id, BookStatusEnum.DELETE.getValue());
	}
	
	public void recoverBook(int id , int status) {
		dao.updateBookStatus(id, BookStatusEnum.NORMAL.getValue());
	}

}

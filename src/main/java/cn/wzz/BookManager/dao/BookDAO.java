package cn.wzz.BookManager.dao;

import org.apache.ibatis.annotations.Insert;

import cn.wzz.BookManager.model.Book;

public interface BookDAO {

	String table_name = "BookManager";
	String insert_field = "name, author, price";
	String select_field = "id, status, "+insert_field;
	
	@Insert({"insert into", table_name, "(", insert_field, ") values (#{name},#{author},#{price})"})
	int addBook(Book book);
}

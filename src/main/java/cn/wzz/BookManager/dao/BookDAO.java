package cn.wzz.BookManager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.wzz.BookManager.model.Book;

@Mapper
public interface BookDAO {

	String table_name = "BookManager";
	String insert_field = "name, author, price";
	String select_field = "id, status, "+insert_field;
	
	@Insert({"insert into ", table_name, "(", insert_field, ") values (#{name},#{author},#{price})"})
	public int addBook(Book book);
	
	@Select({"Select * form ",table_name," where id= #{id}"})
	public Book selectBookById(int id);
	
	@Select({"select * form ",table_name," where name=#{name}"})
	public Book selectBookByName(String name);
	
	@Update({"update ",table_name," set status=#{status} where id=#{id}"})
	public void updateBookStatus(@Param("id") int id, @Param("status") int status);
	
	@Select({"select * from ",table_name})
	public List<Book> selectAll();
}

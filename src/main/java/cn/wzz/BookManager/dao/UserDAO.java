package cn.wzz.BookManager.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.wzz.BookManager.model.User;

@Mapper
public interface UserDAO {

	String table_name = "user";
	String insert_field = "name, email, password";
	
	@Insert({"inset into ",table_name," ( ",insert_field," ) ","values (#{name},#{email},#{password})"})
	public int addUser(User user);
	
	@Select({"select * from ",table_name," where id = #{id}"})
	public User selectById(int id);
	
	@Select({"select * from ",table_name," where name = #{name}"})
	public User selectByName(String name);
	
	@Select({"select * from ",table_name," where email = #{email}"})
	public User selectByEmail(String email);
	
	@Update({"update ",table_name," set password = #{password} where id = #{id}"})
	public void updatePassword(User user);
}

package cn.wzz.BookManager.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import cn.wzz.BookManager.model.Ticket;

@Mapper
public interface TicketDAO {

	String table_name = " ticket ";
	String insert_field = " user_id, ticket, expired_at ";
	
	@Insert({"insert into", table_name, "(", insert_field,") values (#{userId},#{ticket},#{expiredAt})"})
	public int addTicket(Ticket ticket);
	
	@Select({"Select * from",table_name,"where ticket = #{ticket}"})
	public Ticket selectByTicket(String ticket);
	
	@Select({"Select * from",table_name,"where user_id = #{userId}"})
	public Ticket selectByUserid(int userId);
	
	@Delete({"Delete from",table_name,"where id = #{id}"})
	public void deleteById(int id);
	
	@Delete({"Delete from",table_name,"where ticket = #{ticket}"})
	public void deleteByTicket(String ticket);
}

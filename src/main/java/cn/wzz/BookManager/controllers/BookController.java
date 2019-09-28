package cn.wzz.BookManager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.wzz.BookManager.model.User;
import cn.wzz.BookManager.service.BookService;
import cn.wzz.BookManager.service.HostHolder;

@Controller
public class BookController {

	@Autowired
	private BookService bookservice;
	
	@Autowired
	private HostHolder hostholder;
	
	@RequestMapping(path = {"/index"}, method = {RequestMethod.GET})
	public String bookList(Model model) {
		User host = hostholder.getUser();
		if(host != null) {
			model.addAttribute("host",host);
		}
		loadAllBooksView(model);
		return "book/books";
	}

	
	
	
	
	private void loadAllBooksView(Model model) {
		model.addAttribute("books", bookservice.getAllBooks());
		
	}

}

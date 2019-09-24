package cn.wzz.BookManager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.wzz.BookManager.service.BookService;

@Controller
public class BookController {

	@Autowired
	private BookService bookservice;
	
	@RequestMapping(path = {"/index"}, method = {RequestMethod.GET})
	public String bookList(Model model) {
		loadAllBokksView(model);
		return "book/books";
	}

	
	
	
	
	private void loadAllBokksView(Model model) {
		model.addAttribute("books", bookservice.getAllBooks());
		
	}

}

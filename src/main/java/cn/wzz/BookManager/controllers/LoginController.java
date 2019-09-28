package cn.wzz.BookManager.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.wzz.BookManager.biz.LoginBiz;
import cn.wzz.BookManager.utils.CookieUtils;

@Controller
public class LoginController {

	@Autowired
	private LoginBiz loginbiz;
	
	@RequestMapping(path = {"/user/login/do"}, method = {RequestMethod.POST})
	public String doLogin(
			Model model,
			HttpServletResponse response,
			@RequestParam("email") String email,
			@RequestParam("password")String password
			) {
		try {
			String t = loginbiz.login(email, password);
			CookieUtils.writeCookie("t", t, response);
			return "redirect:/index";
		}catch(Exception e) {
			model.addAttribute("error", e.getMessage());
			return "404";
		}
	}

}

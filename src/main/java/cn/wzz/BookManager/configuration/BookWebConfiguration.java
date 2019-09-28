package cn.wzz.BookManager.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import cn.wzz.BookManager.interceptor.HostInfoInterceptor;
import cn.wzz.BookManager.interceptor.LoginInterceptor;

@Component
public class BookWebConfiguration implements WebMvcConfigurer {

	@Autowired
	private LoginInterceptor logininterceptor;
	
	@Autowired
	private HostInfoInterceptor hostinfointerceptor;
	
	
	/**
	 * 添加拦截器
	 * 
	 */
	
	@Bean
	public WebMvcConfigurer webMvcConfigurer() {
		return new WebMvcConfigurer() {
			/*
			 * 添加拦截器
			 * */
			@Override
			public void addInterceptors(InterceptorRegistry registry) {
				registry.addInterceptor(hostinfointerceptor).addPathPatterns("/**");
		        registry.addInterceptor(logininterceptor).addPathPatterns("/books/**");
			}
		};
	}
	

}

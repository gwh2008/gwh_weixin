package com.tencent.wxcloudrun.config;

import com.tencent.wxcloudrun.interceptor.CorsInterceptor;
import com.tencent.wxcloudrun.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Bean
	LoginInterceptor loginInterceptor(){
		return new LoginInterceptor();
	}

	@Bean
	CorsInterceptor corsInterceptor(){
		return  new CorsInterceptor();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/upload/**").addResourceLocations("file:/usr/local/software/upload/");
//		registry.addResourceHandler("/upload/**").addResourceLocations("file:D://upload/");

	}


	@Override
	public void addInterceptors(InterceptorRegistry registry) {


		registry.addInterceptor(corsInterceptor()).addPathPatterns("/**");

		// 拦截后台管理系统 小程序接口不拦截
		registry.addInterceptor(loginInterceptor()).addPathPatterns("/api/v1/**")
				// 不拦截哪些路径   斜杠一定要加
				.excludePathPatterns("/api/v1/user/login","/api/v1/menu/getRouter");
		WebMvcConfigurer.super.addInterceptors(registry);
	}
}

package com.eoptech.shopdoda.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.eoptech.shopdoda.utils.PathConstant;

//-> là 1 bean mà spring hiểu được
// bean này dùng để cấu hình.
@Configuration
public class MVCConf implements WebMvcConfigurer {

	/**
	 * cấu hình các static resources.
	 */
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/css/");
		registry.addResourceHandler("/js/**").addResourceLocations("classpath:/js/");
		registry.addResourceHandler("/vendor/**").addResourceLocations("classpath:/vendor/");
		registry.addResourceHandler("/images/**").addResourceLocations("classpath:/images/");
		registry.addResourceHandler("/img/**").addResourceLocations("classpath:/img/");
		registry.addResourceHandler("/fonts/**").addResourceLocations("classpath:/fonts/");
		registry.addResourceHandler("/summernote/**").addResourceLocations("classpath:/summernote/");
		registry.addResourceHandler("/upload/**").addResourceLocations("file:"+PathConstant.ROOT_UPLOAD_PATH);
	}
	
	// cấu hình cho MVC biết chỗ folder để lấy view trả về cho Browser.
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setViewClass(JstlView.class);
		bean.setPrefix("/WEB-INF/views/"); // thư mục gốc chứa các file jsp.
		bean.setSuffix(".jsp");
		return bean;
	}

//	@Override
//	  public void addResourceHandlers(ResourceHandlerRegistry registry) {
//	    registry.addResourceHandler("/resources/bootstrap/**")
//	        .addResourceLocations("classpath:/META-INF/resources/webjars/bootstrap/4.0.0/");
//	    registry.addResourceHandler("/resources/jquery/**")
//	        .addResourceLocations("classpath:/META-INF/resources/webjars/jquery/3.0.0/");
//	    registry.addResourceHandler("/resources/css/**").addResourceLocations("classpath:/static/css/");
//	  }
}

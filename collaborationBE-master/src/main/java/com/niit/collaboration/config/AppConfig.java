package com.niit.collaboration.config;

import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
@Configuration
@ComponentScan(basePackages= {"com.niit.collaboration"})
@EnableWebMvc
public class AppConfig extends WebMvcConfigurerAdapter
{
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(AppConfig.class);
	@Bean
    public ViewResolver viewResolver() {
		log.debug("Start of method view resolver");
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".html");
        viewResolver.setExposeContextBeansAsAttributes(true);
        log.debug("End of method view resolver");
        return viewResolver;
    }
 
 //MultiPart Resolver
 @Bean(name="multipartResolver") 
 public CommonsMultipartResolver getMultiResolver(){
	 CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver();
	 multipartResolver.setMaxUploadSize(100000);
	 return multipartResolver;
 }
 
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
		configurer.enable();
	}
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry)
	{
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/images/");
	}
	}


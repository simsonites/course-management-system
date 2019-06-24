package com.softpager.cms.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


@Configuration
//@EnableWebMvc
public class CMSWebMvcConfiguration extends WebMvcConfigurationSupport {


/*

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(5242880);
        return multipartResolver;
    }
*/


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**")
                .addResourceLocations("classpath:/static/bootstrap/css/");

        registry.addResourceHandler("/js/**")
                .addResourceLocations("classpath:/static/bootstrap/js/");

        registry.addResourceHandler("/fonts/**")
                .addResourceLocations("classpath:/static/fonts/");

        registry.addResourceHandler("/images/**")
                .addResourceLocations("classpath:/static/images/");

        registry.addResourceHandler("/bg/**")
                .addResourceLocations("classpath:/static/images/bg/");

        registry.addResourceHandler("/courses/**")
                .addResourceLocations("classpath:/static/images/courses/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
    }

}

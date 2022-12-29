package com.example.watchigo.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// http경로 변경(긴 경로 줄이기, 외부경로 지정 등등)
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    // "/img_file/" 사용시 윈도우 images 폴더 지정
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/file/**") // 사용할 경로명
                .addResourceLocations("file:///D:/LeeYJ/images/"); // 이동할 경로
    }

}

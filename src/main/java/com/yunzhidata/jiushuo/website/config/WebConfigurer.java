package com.yunzhidata.jiushuo.website.config;

import com.yunzhidata.jiushuo.website.help.xlsgenelate.xlschildstyle.FirstStyle;
import com.yunzhidata.jiushuo.website.help.xlsgenelate.xlschildstyle.TwoStyle;
import com.yunzhidata.jiushuo.website.help.xlsgenelate.xlsstyle.XHSSFStyleFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {
    /**
     * 配置静态资源访问路径
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
       // System.out.println("--------------------first--------------------");

        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

        //配置获取style的责任链
        XHSSFStyleFactory factory=XHSSFStyleFactory.newInstance();
        factory.build(new FirstStyle()).build(new TwoStyle());
    }
}

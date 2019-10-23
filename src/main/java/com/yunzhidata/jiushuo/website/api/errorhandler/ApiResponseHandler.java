package com.yunzhidata.jiushuo.website.api.errorhandler;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ApiResponseHandler implements ResponseBodyAdvice {

    /**
     *判断哪些需要拦截
     * */
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        System.out.println("-----------------------------------supports");
        return true;
    }

    /**
     * 设置返回
     * */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        Map<String,Object> responseMap=new HashMap<>();
        responseMap.put("flag",true);
        responseMap.put("info","没有数据");
        if(body!=null){
            System.out.println("----------"+body.getClass().getName());
            responseMap.put("info","请求成功");
            responseMap.put("data",body);
        }
        return responseMap;
    }
}

package com.yunzhidata.jiushuo.website.config;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@Component
public class JsonWebTokenFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse respone, FilterChain filter) throws ServletException, IOException {
        String requestUrl=request.getRequestURI();
        requestUrl=requestUrl.replaceAll("/+","/");
        String filterUrl="/loadfile/";
        if(requestUrl.startsWith(filterUrl)){
            String imgName=requestUrl.replace(filterUrl,"");
            request.setAttribute("fileName",imgName);
            request.getRequestDispatcher("/loadfile").forward(request,respone);
        }else{
            filter.doFilter(request,respone);
        }
    }
}

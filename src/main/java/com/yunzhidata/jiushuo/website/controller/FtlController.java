package com.yunzhidata.jiushuo.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FtlController {

    @GetMapping("/file/index")
    public ModelAndView index(ModelAndView mv){
        mv.setViewName("index");
        mv.addObject("from","第一次就好");
        mv.addObject("htmlFormat","<h1>见识到了开发商的</h1>");
        return mv;
    }

    @GetMapping("/file/upfile")
    public ModelAndView upfile(ModelAndView mv){
        mv.setViewName("imgdir/saveimg");
        return mv;
    }

}

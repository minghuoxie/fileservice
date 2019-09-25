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

    //上传文件
    @GetMapping("/file/upfile")
    public ModelAndView upfile(ModelAndView mv){
        mv.setViewName("imgdir/saveimg");
        return mv;
    }

    //减少质量
    @GetMapping("/file/reduceQuaity")
    public ModelAndView reduceQuaity(ModelAndView mv){
        mv.setViewName("testCon/reduceQuaity");
        return mv;
    }

    //图像的裁剪
    @GetMapping("/file/tailoring")
    public ModelAndView tailoring(ModelAndView mv){
        mv.setViewName("testCon/tailoring");
        return mv;
    }

    @GetMapping("/file/iscmykorrgb")
    public ModelAndView iscmykorrgb(ModelAndView mv){
        mv.setViewName("testCon/isCmykOrRgb");
        return mv;
    }

}

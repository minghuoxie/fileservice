package com.yunzhidata.jiushuo.website.controller;

import com.yunzhidata.jiushuo.website.dto.ImageDto;
import com.yunzhidata.jiushuo.website.input.ImgInputType;
import com.yunzhidata.jiushuo.website.util.ICmykOrRgb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class CmykOrRgbController {

    @Autowired
    private ICmykOrRgb iCmykOrRgb;

    @RequestMapping(value = "/cmyk/iscmykorrgb",method = RequestMethod.POST)
    @ResponseBody
    public ImageDto iscmykorrgb(ImgInputType input){
        ImageDto dto=new ImageDto();
        iCmykOrRgb.isCmykOrRgb(input);
        return dto;
    }
}

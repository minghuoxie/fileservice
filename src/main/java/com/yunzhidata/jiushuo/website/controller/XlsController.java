package com.yunzhidata.jiushuo.website.controller;

import com.yunzhidata.jiushuo.website.dto.ImageDto;
import com.yunzhidata.jiushuo.website.dto.MapDto;
import com.yunzhidata.jiushuo.website.input.ImgInputType;
import com.yunzhidata.jiushuo.website.util.IXlsService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

@Controller
public class XlsController {
    @Autowired
    private IXlsService xlsService;

    //本地测试保存
    @RequestMapping(value = "/xls/savelocal",method = RequestMethod.GET)
    @ResponseBody
    public MapDto imgDispatch(ImgInputType input){
        MapDto dto=new MapDto();
        xlsService.excelDemoLocal();
        dto.setAttributes(true,"来了，手机打开了");
        return dto;
    }
}

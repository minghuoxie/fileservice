package com.yunzhidata.jiushuo.website.controller;

import com.yunzhidata.jiushuo.website.dto.ImageDto;
import com.yunzhidata.jiushuo.website.dto.MapDto;
import com.yunzhidata.jiushuo.website.help.xlsneedpro.XlsMap;
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

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping(value = "/xls/baexport",method = RequestMethod.GET)
    @ResponseBody
    public MapDto baexport(ImgInputType input){
        MapDto dto=new MapDto();
        xlsService.testBeforeAndAter();
        dto.setAttributes(true,"来来来来来来来来");
        return dto;
    }

    @RequestMapping(value = "/xls/export",method = RequestMethod.GET)
    @ResponseBody
    public void export(HttpServletResponse response){
        xlsService.export(response);
    }

    @RequestMapping(value = "/xls/exportmap",method = RequestMethod.GET)
    @ResponseBody
    public void exportmap(HttpServletResponse response){
        //xlsForMap(String sheetName, String topTile, String ms, Map<String, XlsMap> head, List<Map<String,Object>> datas,HttpServletResponse response)
        Map<String, XlsMap> head=new HashMap<>();
        head.put("one",new XlsMap(0,true,"第一"));
        head.put("two",new XlsMap(1,true,"第二"));
        List<Map<String,Object>> list=new ArrayList<>();
        Map<String,Object> mapOne=new HashMap<>();
        mapOne.put("one","撒打发回家");
        mapOne.put("two","100");

        Map<String,Object> mapTwo=new HashMap<>();
        mapTwo.put("one","手动阀手动阀");
        mapTwo.put("two","120");
        Map<String,Object> mapSan=new HashMap<>();
        mapSan.put("one","手动阀手动阀");
        mapSan.put("two","130");

        Map<String,Object> mapSi=new HashMap<>();
        mapSi.put("one","手动阀手动阀");
        mapSi.put("two","140");
        Map<String,Object> mapWu=new HashMap<>();
        mapWu.put("one","再来一次");
        mapWu.put("two","120");
        Map<String,Object> mapLiu=new HashMap<>();
        mapLiu.put("one","再来一次");
        mapLiu.put("two","120");
        Map<String,Object> mapQi=new HashMap<>();
        mapQi.put("one","哈哈哈end");
        mapQi.put("two","120");
        list.add(mapOne);
        list.add(mapTwo);
        list.add(mapSan);
        list.add(mapSi);
        list.add(mapWu);
        list.add(mapLiu);
        list.add(mapQi);
        xlsService.xlsForMap("二二人","但考虑到法国","sdfsdgfdgdfg",head,list,response);
    }
}

package com.yunzhidata.jiushuo.website.controller;

import com.yunzhidata.jiushuo.website.api.errorhandler.ApiResponse;
import com.yunzhidata.jiushuo.website.dto.MapDto;
import com.yunzhidata.jiushuo.website.input.ImgInputType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ErrorHandlerController {
    //本地测试保存
    @RequestMapping(value = "/erroe/erTest",method = RequestMethod.GET)
    @ResponseBody
   // public Map<String,String> erTest(ModelMap modelMap){
    public Map<String,String> erTest(@ModelAttribute("author") String author){
       // System.out.println("----------------erTest");
        Map<String,String> map=new HashMap<>();
        map.put("one","hahah");
        map.put("author",author);
        return map;
    }
    //没有返回值
    @RequestMapping(value = "/erroe/getvoid",method = RequestMethod.GET)
    @ResponseBody
    public void getvoid(){

    }

    @RequestMapping(value = "/erroe/getmap",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,String> getmap(){
        Map<String,String> map=new HashMap<>();
        map.put("one","hahah");
        map.put("two","ggggg");
        return map;
    }

    //本地测试保存  返回对象的时候可以   只有Map不能
    @RequestMapping(value = "/erroe/obj",method = RequestMethod.GET)
    @ResponseBody
   // public ApiResponse obj(){
    public List<Map<String,String>> obj(){
        //ApiResponse res=new ApiResponse(1,"haha");  yes

//        List<ApiResponse> list=new ArrayList<>();   yes
//        ApiResponse one=new ApiResponse(1,"haha");
//        ApiResponse two=new ApiResponse(2,"sdfsdffghgf");
//        list.add(one);
//        list.add(two);

//        List<String> list=new ArrayList<>();
//        list.add("one");
//        list.add("two");

        List<Map<String,String>> listMap=new ArrayList<>();
        Map<String,String> map=new HashMap<>();
        map.put("mapOne","one");
        map.put("mapTwo","two");
        Map<String,String> mapTwo=new HashMap<>();
        mapTwo.put("mapTwoOne","one");
        mapTwo.put("mapTwoTwo","two");
        listMap.add(map);
        listMap.add(mapTwo);
        return listMap;
    }

    @RequestMapping(value = "/erroe/erError",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,String> erError(){
        int k=Integer.parseInt("1.1");
        Map<String,String> map=new HashMap<>();
        map.put("one","erError");
        return map;
    }

    @RequestMapping(value = "/erroe/erNullError",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,String> erNullError(){
        Map<String,String> map=null;
        map.put("one","erError");
        return map;
    }
}

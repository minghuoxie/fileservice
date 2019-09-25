//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//
package com.yunzhidata.jiushuo.website.controller;


import com.yunzhidata.jiushuo.website.dto.ImageDto;
import com.yunzhidata.jiushuo.website.input.ImgInputType;
import com.yunzhidata.jiushuo.website.util.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class ImageController {
    @Value("${imgpath}")
    private String path;
    @Value("${fwq}")
    private String fwq;

    @Autowired
    private UtilService utilService;


    /**
     * 图片的质量压缩   只能处理png格式的图片
     * */
    @RequestMapping(value = "/imgDispatch",method = RequestMethod.POST)
    @ResponseBody
    public ImageDto imgDispatch(ImgInputType input){
        return utilService.imgDispatch(input);
    }
    /**
     * 上传图片  java的JDK只支持PNG格式   问题大
     * */
    @RequestMapping(value = "/upfile",method = RequestMethod.POST)
    @ResponseBody
    public ImageDto upfile(MultipartFile file){
        ImageDto dto= new ImageDto();
        if(file!=null&&file.getOriginalFilename().endsWith(".png")||file.getOriginalFilename().endsWith(".jpg")){
            String fileType=".jpg";
            if(file.getOriginalFilename().endsWith(".png")){
                fileType=".png";
            }
            File saveFile=utilService.getSaveFileName(fileType);

            //先将文件原样保存 在做处理
            InputStream inputStream=null;
            OutputStream outputStream=null;
            try {
                inputStream=file.getInputStream();
                outputStream=new FileOutputStream(saveFile);
                byte[] buf=new byte[1024*10];
                int len=0;
                while((len=inputStream.read(buf))!=-1){
                    outputStream.write(buf,0,len);
                    outputStream.flush();
                    buf=new byte[1024*10];
                }

//                D:\data\imgpath\201909   D:\\data\\imgpath\\201909\\1569224545002.jpg   imgpath=D:/data/imgpath/
                String saveFilePath=saveFile.getAbsolutePath().replaceAll("\\\\+","/").replaceAll(path,"");
                dto.setAttributes(true,"保存成功",fwq+"/loadfile/"+saveFilePath);
            } catch (IOException e) {
                e.printStackTrace();
            }finally{
                if(inputStream!=null){
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if(outputStream!=null){
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return dto;
    }

    /**
     * 访问图片
     * */
    @GetMapping("/loadfile")
    public void loadfile(HttpServletRequest request,HttpServletResponse response){
        String fileName=request.getAttribute("fileName")+"";
        File file=new File(path+fileName);
        if(file.exists()){
            response.setContentType("image/pjpeg");
            response.setHeader("Cache-Control","max-age=604800");
            FileInputStream fis = null;
            OutputStream outputStream = null;
            try {
                fis = new FileInputStream(file);
                outputStream = response.getOutputStream();
                int len=0;
                byte[] buf=new byte[1024*10];
                while((len=fis.read(buf))!=-1){
                    outputStream.write(buf,0,len);
                    outputStream.flush();
                    buf=new byte[1024*10];
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

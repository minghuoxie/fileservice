//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//
package com.yunzhidata.jiushuo.website.controller;


import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ImageController {
    //图像处理库的测试  localhost:8089/image/testImages
    //D:\data\images\new\san\aaaa.jpg
    @RequestMapping(value = {"image/testImages"},method = {RequestMethod.GET})
    @ResponseBody
    public Map<String,String> testImages(){
        Map<String,String> map=new HashMap<>();
        map.put("one","dfsdfsdf");
        try {
          //  File saveFile=new File("D:\\data\\images\\new\\san\\tnew.png");
            File orgFile=new File("D:\\data\\images\\new\\san\\createss.jpg");
//            saveFile.createNewFile();
//            Thumbnails.of(orgFile)
//                    .size(bufImage.getWidth(),bufImage.getHeight())
//                    .toFile(saveFile);

            InputStream in=new FileInputStream(orgFile);
            BufferedImage bufImage = ImageIO.read(in);
            Thumbnails.of(in)
                    .size(bufImage.getWidth(),bufImage.getHeight())
                    .toOutputStream(null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }
}

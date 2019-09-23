package com.yunzhidata.jiushuo.website.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class Util implements UtilService {
    @Value("${imgpath}")
    private String path;

    @Value("${imgpre}")
    private String imgpre;
    @Value("${imgnxt}")
    private String imgnxt;

    /**
     * 获取时间格式的字符串  yyyy-MM-dd hh-mm-ss    yyyyMM
     * */
    public String getFormatTime(String formatTime){
        Date date=new Date();
        DateFormat format = new SimpleDateFormat(formatTime);
        return format.format(date);
    }

    /**
     * 获取保存的文件名
     *
     * */
    @Override
    public File getSaveFileName(String endName){
        //目录不存在 则创建目录
        File dirPath=new File(path+getFormatTime("yyyyMM")+"/");
        if(!dirPath.exists()){
            dirPath.mkdirs();
        }
        String fileName=dirPath.getAbsolutePath()+"/"+imgpre+System.currentTimeMillis()+imgnxt+endName;
        File file=new File(fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}

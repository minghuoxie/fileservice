package com.yunzhidata.jiushuo.website.help.automapper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
    //yyyy-MM-dd hh:mm:ss
    public static String getForMatTime(String forMat, Date date){
        SimpleDateFormat sf = new SimpleDateFormat(forMat);
        return sf.format(date);
    }
}

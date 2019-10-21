package com.yunzhidata.jiushuo.website.help.xlsgenelate.xlsstyle;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.HashMap;
import java.util.Map;

public class XHSSFStyleFactory {
    private HSSFGetStyle firstStyle;
    private static XHSSFStyleFactory factory;
    private XHSSFStyleFactory(){}

    public static synchronized XHSSFStyleFactory newInstance(){
        if(factory==null){
            factory=new XHSSFStyleFactory();
        }
        return factory;
    }
    public synchronized XHSSFStyleFactory build(HSSFGetStyle style){
        if(this.firstStyle==null){
            this.firstStyle=style;
        }else{
            style.setNextStyle(this.firstStyle);
            this.firstStyle=style;
        }
        return this;
    }
    public HSSFCellStyle getStyle(HSSFWorkbook workbook, Class cls){
        return firstStyle.getStyle(workbook,cls);
    }
}

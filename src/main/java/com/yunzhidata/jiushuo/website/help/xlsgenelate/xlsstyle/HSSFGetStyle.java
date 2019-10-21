package com.yunzhidata.jiushuo.website.help.xlsgenelate.xlsstyle;

import com.yunzhidata.jiushuo.website.help.xlsgenelate.PoiException;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public abstract class HSSFGetStyle {

    protected HSSFGetStyle nextStyle;
    public HSSFGetStyle(){}
    public HSSFGetStyle(HSSFGetStyle nextStyle){
        this.nextStyle=nextStyle;
    }

    public void setNextStyle(HSSFGetStyle nextStyle){
        this.nextStyle=nextStyle;
    }

    public HSSFCellStyle getStyle(HSSFWorkbook workbook,Class cls){
        if(this.getClass()==cls){
            return this.getStyle(workbook);
        }else if(nextStyle!=null){
           return nextStyle.getStyle(workbook, cls);
        }
        throw new PoiException("没有找到这个拦截方法!");
    }
    public abstract HSSFCellStyle getStyle(HSSFWorkbook workbook);
}

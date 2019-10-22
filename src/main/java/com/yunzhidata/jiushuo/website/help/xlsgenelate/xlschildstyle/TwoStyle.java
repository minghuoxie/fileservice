package com.yunzhidata.jiushuo.website.help.xlsgenelate.xlschildstyle;

import com.yunzhidata.jiushuo.website.help.xlsgenelate.xlsstyle.HSSFGetStyle;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

public class TwoStyle extends HSSFGetStyle {
    @Override
    public HSSFCellStyle getStyle(HSSFWorkbook workbook) {
        HSSFFont headFont=workbook.createFont();
        headFont.setFontName("楷体");
        headFont.setFontHeightInPoints((short)10);
        headFont.setColor(HSSFColor.GREY_25_PERCENT.index);
       // headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗

        HSSFCellStyle headStyle=workbook.createCellStyle();
        headStyle.setFont(headFont);
        headStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        headStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中
        headStyle.setFillPattern((short)0);  //设置填充样式 0代表空填  1代表实填
        //headStyle.setFillBackgroundColor(HSSFColor.BLUE.index);
        headStyle.setFillForegroundColor(HSSFColor.WHITE.index);  //设置背景色

//        headStyle.setBorderBottom((short)1);
//        headStyle.setBottomBorderColor(HSSFColor.RED.index);
//        headStyle.setLocked(true); //列宽固定
//        headStyle.setWrapText(true);//自动换行
        return headStyle;
    }
}

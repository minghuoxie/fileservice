package com.yunzhidata.jiushuo.website.help.xlsgenelate.xlschildstyle;

import com.yunzhidata.jiushuo.website.help.xlsgenelate.xlsstyle.HSSFGetStyle;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

public class FirstStyle extends HSSFGetStyle {
    @Override
    public HSSFCellStyle getStyle(HSSFWorkbook workbook) {
        HSSFFont headFont=workbook.createFont();
        headFont.setFontName("宋体");
        headFont.setFontHeightInPoints((short)10);
        headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗

        HSSFCellStyle headStyle=workbook.createCellStyle();
        headStyle.setFont(headFont);
        headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        headStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中
        headStyle.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
        headStyle.setBottomBorderColor(HSSFColor.RED.index);
//        headStyle.setLocked(true); //列宽固定
//        headStyle.setWrapText(true);//自动换行
        return headStyle;
    }
}

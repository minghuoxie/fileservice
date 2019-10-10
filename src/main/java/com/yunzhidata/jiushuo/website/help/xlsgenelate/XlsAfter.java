package com.yunzhidata.jiushuo.website.help.xlsgenelate;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public interface XlsAfter {
    void after(HSSFWorkbook workbook, HSSFSheet sheet,int indexRow);
}

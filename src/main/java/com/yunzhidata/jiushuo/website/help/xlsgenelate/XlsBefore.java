package com.yunzhidata.jiushuo.website.help.xlsgenelate;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public interface XlsBefore {
    int before(HSSFWorkbook workbook, HSSFSheet sheet,int indexRow);
}

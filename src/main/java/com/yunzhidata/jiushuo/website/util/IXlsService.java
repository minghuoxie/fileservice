package com.yunzhidata.jiushuo.website.util;

import com.yunzhidata.jiushuo.website.help.xlsneedpro.XlsMap;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface IXlsService {
    void xlsExport();
    void headSheetOne();
    void excelDemoLocal();
    void testAnnotationXls();


    void testBeforeAndAter();

    void export(HttpServletResponse response);
    void exportstyle(HttpServletResponse response);
    void xlsForMap(String sheetName, String topTile, String ms, Map<String, XlsMap> head, List<Map<String,Object>> datas,HttpServletResponse response);
}

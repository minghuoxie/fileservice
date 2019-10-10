package com.yunzhidata.jiushuo.website.util;

import javax.servlet.http.HttpServletResponse;

public interface IXlsService {
    void xlsExport();
    void headSheetOne();
    void excelDemoLocal();
    void testAnnotationXls();


    void testBeforeAndAter();

    void export(HttpServletResponse response);
}

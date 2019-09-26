package com.yunzhidata.jiushuo.website.help.xlsannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ExcelWorkBook {
    String sheetName() default "sheet0";
    String title() default "";
    String fond() default "宋体";
    Aligment alignment() default Aligment.CENTER;
    VerticalAlignment verticalAlignment() default VerticalAlignment.CENTER;
    int fontSize() default 10;
    int rowHeight() default 300;
    int titleHeight() default 750;
    int columnHeight() default 600;
}

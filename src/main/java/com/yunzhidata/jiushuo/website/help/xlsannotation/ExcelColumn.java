package com.yunzhidata.jiushuo.website.help.xlsannotation;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ExcelColumn {
    String title() default "";

    int column() default 0;

    Aligment alignment() default Aligment.CENTER;

    VerticalAlignment verticalAlignment() default VerticalAlignment.CENTER;

    int width() default 5000;

    String fondName() default "楷体";
    int fondSize() default 10;
    boolean isBlowd() default false;

    boolean isMerge() default  false; //是否合并列   最好不要使用
}

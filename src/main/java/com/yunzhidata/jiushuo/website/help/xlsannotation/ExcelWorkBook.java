package com.yunzhidata.jiushuo.website.help.xlsannotation;

import com.yunzhidata.jiushuo.website.help.xlsgenelate.xlsstyle.HSSFGetStyle;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ExcelWorkBook {
    //sheet的名称
    String sheetName() default "sheet0";
    //头部的标题
    String title() default "";
    //默认字体
    String fond() default "宋体";
    //相对的位置的水平位置
    Aligment alignment() default Aligment.CENTER;
    //相对的垂直的位置
    VerticalAlignment verticalAlignment() default VerticalAlignment.CENTER;
    //字体大小
    int fontSize() default 10;
    //第一行的高
    int rowHeight() default 300;
    // 第二行的高
    int titleHeight() default 750;
    // cell的行高
    int columnHeight() default 600;

}

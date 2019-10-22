package com.yunzhidata.jiushuo.website.help.xlsannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ExcelImg {

    //起始单元格的x偏移量 起始位置距单元格左侧的距离
    int dx1() default 0;

    //起始单元格的y偏移量 起始位置距单元格上侧的距离
    int dy1() default 0;

    //终止单元格的x偏移量 1023表示直线起始位置距单元格左侧的距离
    int dx2() default 1023;

    //终止单元格的y偏移量 150表示直线起始位置距单元格上侧的距离
    int dy2() default 150;

    //起始单元格列序号，从0开始计算
    int col1() default 0;

    //起始单元格行序号，从0开始计算
    int row1() default 0;

    //终止单元格列序号，从0开始计算
    int col2() default 0;

    //终止单元格行序号，从0开始计算
    int row2() default 0;
}

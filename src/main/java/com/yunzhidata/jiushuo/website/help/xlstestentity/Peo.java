package com.yunzhidata.jiushuo.website.help.xlstestentity;

import com.yunzhidata.jiushuo.website.help.xlsannotation.ExcelColumn;
import com.yunzhidata.jiushuo.website.help.xlsannotation.ExcelImg;
import com.yunzhidata.jiushuo.website.help.xlsannotation.ExcelWorkBook;

import java.io.InputStream;

@ExcelWorkBook(sheetName = "人员导出表",title = "测试人员导出表格",fontSize = 10,rowHeight = 900)
public class Peo {

    @ExcelColumn(title = "序号",column = 8,firstNumber = 11,isNumberOrder = true)
    private Long id;

    @ExcelColumn(title = "名字",column=10,width=6000)
    private String name;

    @ExcelColumn(title="序号",column = 11,width = 6000)
    private Integer number;

    @ExcelColumn(title="年龄",column = 12,width = 6000)
    private Integer age;

    @ExcelColumn(title="地址",column = 9,width = 6000,isMerge = true)
    private String addr;

    @ExcelColumn(title="班级",column = 13,width = 6000,isMerge = true,comp = {"一班", "二班","三班","四班","五班"})
    private String banji;

    @ExcelImg(col1 = 16,col2 = 16,row1 = 1,row2 = 1)
    private InputStream inputStream;

    public Peo(){}

    public Peo(String name, Integer number, Integer age, String addr,String banji) {
        this.name = name;
        this.number = number;
        this.age = age;
        this.addr = addr;
        this.banji=banji;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getBanji() {
        return banji;
    }

    public void setBanji(String banji) {
        this.banji = banji;
    }
}

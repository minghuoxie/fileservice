package com.yunzhidata.jiushuo.website.help.xlstestentity;

import com.yunzhidata.jiushuo.website.help.xlsannotation.ExcelColumn;
import com.yunzhidata.jiushuo.website.help.xlsannotation.ExcelWorkBook;

@ExcelWorkBook(sheetName = "人员导出表",title = "测试人员导出表格",fontSize = 10,rowHeight = 900)
public class Peo {
    @ExcelColumn(title = "名字",column=9,width=6000,isMerge = true)
    private String name;

    @ExcelColumn(title="序号",column = 11,width = 6000,isMerge = true)
    private Integer number;

    @ExcelColumn(title="年龄",column = 12,width = 6000,isMerge = true)
    private Integer age;

    @ExcelColumn(title="地址",column = 10,width = 6000,isMerge = true)
    private String addr;

    public Peo(){}

    public Peo(String name, Integer number, Integer age, String addr) {
        this.name = name;
        this.number = number;
        this.age = age;
        this.addr = addr;
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
}

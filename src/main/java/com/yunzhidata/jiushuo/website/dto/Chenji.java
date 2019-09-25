package com.yunzhidata.jiushuo.website.dto;

public class Chenji {
    private Integer year;
    private Integer month;
    private Float math;
    private Float chinese;
    private Float english;
    private Float wuli;
    public Chenji(){}
    public Chenji(Integer year, Integer month, Float math, Float chinese, Float english, Float wuli) {
        this.year = year;
        this.month = month;
        this.math = math;
        this.chinese = chinese;
        this.english = english;
        this.wuli = wuli;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Float getMath() {
        return math;
    }

    public void setMath(Float math) {
        this.math = math;
    }

    public Float getChinese() {
        return chinese;
    }

    public void setChinese(Float chinese) {
        this.chinese = chinese;
    }

    public Float getEnglish() {
        return english;
    }

    public void setEnglish(Float english) {
        this.english = english;
    }

    public Float getWuli() {
        return wuli;
    }

    public void setWuli(Float wuli) {
        this.wuli = wuli;
    }
}

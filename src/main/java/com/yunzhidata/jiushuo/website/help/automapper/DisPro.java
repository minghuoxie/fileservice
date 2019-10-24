package com.yunzhidata.jiushuo.website.help.automapper;

import java.util.Date;

public class DisPro {
    private int id;
    private String age;
    private String name;
    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStrDate(){
        return TimeUtil.getForMatTime("yyyy-MM-dd hh:mm:ss",this.date);
    }

    @Override
    public String toString() {
        return "DisPro{" +
                "id=" + id +
                ", age='" + age + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

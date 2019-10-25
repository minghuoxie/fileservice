package com.yunzhidata.jiushuo.website.help.jdkeightnew.getfieldstr;

public class FieldPro {
    private int age;
    private int agss;
    private String name;
    private String addrs;
    public FieldPro(){}

    public FieldPro(int age,int agss, String name, String addrs) {
        this.agss=agss;
        this.age = age;
        this.name = name;
        this.addrs = addrs;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddrs() {
        return addrs;
    }

    public void setAddrs(String addrs) {
        this.addrs = addrs;
    }

    public int getAgss() {
        return agss;
    }

    public void setAgss(int agss) {
        this.agss = agss;
    }

    @Override
    public String toString() {
        return "FieldPro{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", addrs='" + addrs + '\'' +
                '}';
    }
}

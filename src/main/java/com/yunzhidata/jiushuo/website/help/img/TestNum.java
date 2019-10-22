package com.yunzhidata.jiushuo.website.help.img;

public enum TestNum {
    DEPT_ONE(1,"社区服务中心"),
    DEPT_TWO(2,"垂管社区");

    private int value;
    private String name;

    TestNum(int value,String name) {
        this.name = name;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

}

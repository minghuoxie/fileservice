package com.yunzhidata.jiushuo.website.aspect;

public enum AsEnum {
    ONE("com.yunzhidata.jiushuo.website.util.export", 1),
    TWO("com.yunzhidata.jiushuo.website.util.xlsForMap",2);

    private int value;
    private String name;

    AsEnum(String name, int value) {
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

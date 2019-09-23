package com.yunzhidata.jiushuo.website.dto;

public class MapDto<T>{
    private boolean flag;
    private String info;
    private T result;

    public MapDto(){
        this.flag=false;
        this.info="请求失败";
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public void setAttributes(boolean flag,String info){
        this.flag=false;
        this.info=info;
    }
    public void setAttributes(boolean flag,String info,T t){
        setAttributes(flag,info);
        this.result=t;
    }
}

package com.yunzhidata.jiushuo.website.api.errorhandler;

public class ApiResponseEntity {
    private int code;
    private boolean flag;
    private String info;
    private Object datas;

    public ApiResponseEntity(){
    }

    public ApiResponseEntity(int code,boolean flag,String info,Object datas){
        this.code=code;
        this.flag=flag;
        this.info=info;
        this.datas=datas;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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

    public Object getDatas() {
        return datas;
    }

    public void setDatas(Object datas) {
        this.datas = datas;
    }
}

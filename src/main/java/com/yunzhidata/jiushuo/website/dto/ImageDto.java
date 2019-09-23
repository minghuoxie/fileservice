package com.yunzhidata.jiushuo.website.dto;

public class ImageDto extends MapDto{
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //设置属性
    public void setAttributes(boolean flag,String info,String url){
        this.setFlag(false);
        this.setInfo(info);
        this.setUrl(url);
    }
}

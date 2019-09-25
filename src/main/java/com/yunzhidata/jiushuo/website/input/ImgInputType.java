package com.yunzhidata.jiushuo.website.input;

public class ImgInputType {

    /**
     * 图片请求的路径
     * */
    private String url;
    /**
     * 1 代表质量压缩
     * 2 代表图片的裁剪
     * */
    private Integer type;

    private Integer pointX;
    private Integer pointY;
    private Integer wigth;
    private Integer height;

    public Integer getPointX() {
        return pointX;
    }

    public void setPointX(Integer pointX) {
        this.pointX = pointX;
    }

    public Integer getPointY() {
        return pointY;
    }

    public void setPointY(Integer pointY) {
        this.pointY = pointY;
    }

    public Integer getWigth() {
        return wigth;
    }

    public void setWigth(Integer wigth) {
        this.wigth = wigth;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}

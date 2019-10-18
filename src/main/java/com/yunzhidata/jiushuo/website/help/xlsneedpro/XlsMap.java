package com.yunzhidata.jiushuo.website.help.xlsneedpro;

public class XlsMap {
    private int colnum;
    private boolean isMerge;
    private String topName;
    private int colWidth=5000;

    public XlsMap(int colnum,boolean isMerge,String topName){
        this.colnum=colnum;
        this.isMerge=isMerge;
        this.topName=topName;
    }

    public int getColWidth() {
        return colWidth;
    }

    public void setColWidth(int colWidth) {
        this.colWidth = colWidth;
    }

    public String getTopName() {
        return topName;
    }

    public void setTopName(String topName) {
        this.topName = topName;
    }

    public int getColnum() {
        return colnum;
    }

    public void setColnum(int colnum) {
        this.colnum = colnum;
    }

    public boolean isMerge() {
        return isMerge;
    }

    public void setMerge(boolean merge) {
        isMerge = merge;
    }
}

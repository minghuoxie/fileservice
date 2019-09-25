package com.yunzhidata.jiushuo.website.dto;

public class RangeHelp {
    //合并
    private int firstRow;
    private int lastRow;
    private int firstCol;
    private int lastCol;
    private String rangLabel;

    public String getRangLabel() {
        return rangLabel;
    }

    public void setRangLabel(String rangLabel) {
        this.rangLabel = rangLabel;
    }

    public int getFirstRow() {
        return firstRow;
    }

    public void setFirstRow(int firstRow) {
        this.firstRow = firstRow;
    }

    public int getLastRow() {
        return lastRow;
    }

    public void setLastRow(int lastRow) {
        this.lastRow = lastRow;
    }

    public int getFirstCol() {
        return firstCol;
    }

    public void setFirstCol(int firstCol) {
        this.firstCol = firstCol;
    }

    public int getLastCol() {
        return lastCol;
    }

    public void setLastCol(int lastCol) {
        this.lastCol = lastCol;
    }
}

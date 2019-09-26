package com.yunzhidata.jiushuo.website.help.xlsgenelate;

import com.yunzhidata.jiushuo.website.help.xlsannotation.Aligment;
import com.yunzhidata.jiushuo.website.help.xlsannotation.ExcelColumn;
import com.yunzhidata.jiushuo.website.help.xlsannotation.ExcelWorkBook;
import com.yunzhidata.jiushuo.website.help.xlsannotation.VerticalAlignment;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.lang.reflect.Field;
import java.util.*;

public class XlsGenete<T> {

    private ExcelWorkBook headTitle;
    private List<ExcelColumn> excelColumns;
    private HSSFWorkbook workbook;
    private Map<Integer,HSSFCellStyle> mapCellStyle;

    public HSSFWorkbook createWorkBook(List<T> datas,Class book){
         headTitle=(ExcelWorkBook)book.getAnnotation(ExcelWorkBook.class);
        if(headTitle==null){
            throw new PoiException("初始化Excel的工作簿信息失败，请检查Excel实体是否标注 ExcelWorkBook 注解");
        }else{
            workbook=new HSSFWorkbook();
            HSSFSheet sheet=workbook.createSheet(headTitle.sheetName());
            Field[] declaredFields = book.getDeclaredFields();
            if (declaredFields != null && declaredFields.length != 0) {
                excelColumns=new ArrayList<>();
                for(Field field:declaredFields){
                    if(field.isAnnotationPresent(ExcelColumn.class)){
                        excelColumns.add((ExcelColumn)field.getAnnotation(ExcelColumn.class));
                    }
                }
                //设置头  样式固定
                createHead(sheet);
                //填充数据
                if(datas!=null&&datas.size()>0){
                    try {
                        addDatas(sheet,datas,2);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                return workbook;
            }else{
                throw new PoiException("初始化Excel的工作簿信息失败，请检查Excel属性是否标注 ExcelColumn 注解");
            }
        }
    }

    //填充数据  需要开始的行 和数据 方便今后拓展
    private void addDatas(HSSFSheet sheet,List<T> datas,int indexRow) throws IllegalAccessException {
        Map<String,XlsRange> tangeMap=new HashMap<>();
        List<XlsRange> rangList=new ArrayList<>();
        //合并操作
        for(ExcelColumn rangeCol:excelColumns){
            if(rangeCol.isMerge()){
                XlsRange range=new XlsRange();
                range.setFirstRow(indexRow);
                range.setFirstCol(rangeCol.column());
                tangeMap.put(rangeCol.column()+"",range);
            }
        }

        for(T t:datas){
            Field[] declaredFields = t.getClass().getDeclaredFields();
            if(declaredFields!=null&&declaredFields.length>0){
                HSSFRow rowi=sheet.createRow(indexRow);
                rowi.setHeight((short) headTitle.columnHeight());
                HSSFCell celli =null;
                for(Field field:declaredFields){
                    if(field.isAnnotationPresent(ExcelColumn.class)){
                        ExcelColumn linExcelColumn=(ExcelColumn)field.getAnnotation(ExcelColumn.class);
                        celli=rowi.createCell(linExcelColumn.column());
                        field.setAccessible(true);
                        String val=field.get(t)+"";
                        celli.setCellValue(new HSSFRichTextString(val));
                        celli.setCellStyle(mapCellStyle.get(Integer.parseInt(linExcelColumn.column()+"")));

                        //合并
                        if(linExcelColumn.isMerge()){
                            XlsRange rg=tangeMap.get(linExcelColumn.column()+"");
                            if(rg.getRangLabel()==null){
                                rg.setRangLabel(val);
                            }else if(!val.equals(rg.getRangLabel())){
                                rg.setLastCol(rg.getFirstCol());
                                rg.setLastRow(indexRow-1);
                                rangList.add(rg);

                                XlsRange newRg=new XlsRange();
                                newRg.setFirstCol(linExcelColumn.column());
                                newRg.setFirstRow(indexRow);
                                newRg.setRangLabel(val);
                                tangeMap.put(linExcelColumn.column()+"",newRg);
                            }
                        }
                    }
                }
                indexRow++;
            }
        }
        Iterator<String> ite=tangeMap.keySet().iterator();
        while(ite.hasNext()){
            String key=ite.next();
            XlsRange lastRange=tangeMap.get(key);
            lastRange.setLastCol(lastRange.getFirstCol());
            lastRange.setLastRow(indexRow-1);
            rangList.add(lastRange);
        }

        merge(rangList,sheet);
    }

    //合并行数
    private void merge(List<XlsRange> rangesList,HSSFSheet sheet){
        if(rangesList!=null&&rangesList.size()>0){
            for(XlsRange rangHelp:rangesList) {
                CellRangeAddress rangeDefault = new CellRangeAddress(rangHelp.getFirstRow(), rangHelp.getLastRow(), rangHelp.getFirstCol(), rangHelp.getLastCol());
                sheet.addMergedRegion(rangeDefault);
            }
        }
    }

    //创建工作簿的头
    private void createHead(HSSFSheet sheet){
        HSSFCellStyle headStyie=setHeadTitleStyle(1);
        //设置第二行  头部的标题头
        HSSFRow row2 = sheet.createRow(1);
        row2.setHeight((short) headTitle.titleHeight());
        HSSFCell cell =null;

        //计算出工作簿的列数  并且设置列宽
        int minCol=99999999;
        int maxCol=-1;
        HSSFCellStyle headStyieT=setHeadTitleStyle(0);
        mapCellStyle=new HashMap<>();
        for(ExcelColumn column:excelColumns){

            minCol=minCol<column.column()?minCol:column.column();
            maxCol=maxCol>column.column()?maxCol:column.column();
            sheet.setColumnWidth(column.column(),column.width());

            cell=row2.createCell(column.column());
            cell.setCellValue(column.title());
            cell.setCellStyle(headStyieT);

            //添加cell的样式
            mapCellStyle.put(Integer.parseInt(column.column()+""),setCellStyle(column));
        }
        //合并标题行 创建标题行
        CellRangeAddress titleRange = new CellRangeAddress(0, 0, minCol, maxCol);
        sheet.addMergedRegion(titleRange);

        // 创建第一行
        HSSFRow rowTitle = sheet.createRow(0);
        // 设置行高
        rowTitle.setHeight((short) headTitle.rowHeight());
        // 创建第一列
        HSSFCell cell0 = rowTitle.createCell(minCol);
        cell0.setCellValue(new HSSFRichTextString(headTitle.title()));
        cell0.setCellStyle(headStyie);
    }



    //设置cell的样式
    private HSSFCellStyle setCellStyle(ExcelColumn ecolumn){
        HSSFCellStyle cellStyle=workbook.createCellStyle();
        HSSFFont cellFont=workbook.createFont();
        cellFont.setFontName(ecolumn.fondName());
        cellFont.setFontHeightInPoints((short)ecolumn.fondSize());
        if(ecolumn.isBlowd()){
            cellFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        }

        cellStyle.setFont(cellFont);
        if(ecolumn.alignment()== Aligment.CENTER) {
            cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//左右剧中
        }else if(ecolumn.alignment()==Aligment.LEFT){
            cellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        }else if(ecolumn.alignment()==Aligment.RIGHT){
            cellStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        }
        if(ecolumn.verticalAlignment()== VerticalAlignment.CENTER) {
            cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中
        }else if(ecolumn.verticalAlignment()==VerticalAlignment.TOP){
            cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
        }else if(ecolumn.verticalAlignment()==VerticalAlignment.BOTTOM){
            cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_BOTTOM);
        }

        cellStyle.setLocked(true); //列宽固定
        cellStyle.setWrapText(true);//自动换行
        return cellStyle;
    }


    //设置风格 头的风
    private HSSFCellStyle setHeadTitleStyle(int type){
        //设置字体
        HSSFFont headFont=workbook.createFont();
        headFont.setFontName(headTitle.fond());
        if(type==0) {
            headFont.setFontHeightInPoints((short) headTitle.fontSize());//字体的大小
        }else if(type==1){
            headFont.setFontHeightInPoints((short) (2*headTitle.fontSize()));//字体的大小
        }
        headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗

        HSSFCellStyle headStyle=workbook.createCellStyle();
        headStyle.setFont(headFont);
        if(headTitle.alignment()== Aligment.CENTER) {
            headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//左右剧中
        }else if(headTitle.alignment()==Aligment.LEFT){
            headStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        }else if(headTitle.alignment()==Aligment.RIGHT){
            headStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        }
        if(headTitle.verticalAlignment()== VerticalAlignment.CENTER) {
            headStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中
        }else if(headTitle.verticalAlignment()==VerticalAlignment.TOP){
            headStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
        }else if(headTitle.verticalAlignment()==VerticalAlignment.BOTTOM){
            headStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_BOTTOM);
        }
        headStyle.setLocked(true); //列宽固定
        headStyle.setWrapText(true);//自动换行
        return headStyle;
    }
}

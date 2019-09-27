package com.yunzhidata.jiushuo.website.util;

import com.yunzhidata.jiushuo.website.dto.Chenji;
import com.yunzhidata.jiushuo.website.dto.RangeHelp;
import com.yunzhidata.jiushuo.website.help.xlsgenelate.XlsGenete;
import com.yunzhidata.jiushuo.website.help.xlstestentity.Peo;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class XlsServiceImpl implements IXlsService {
    //https://www.cnblogs.com/pocketbook/articles/7736531.html

    @Value("${imgpath}")
    private String path;
    @Override
    public void xlsExport(){
        HSSFWorkbook workbook = new HSSFWorkbook();// 创建一个Excel文件
        HSSFSheet sheet=workbook.createSheet(); //创建一个Excel的Sheet
        sheet.createFreezePane(1,3); //冻结
        //设置列宽
        sheet.setColumnWidth(0,1000);
        sheet.setColumnWidth(1,3500);
        sheet.setColumnWidth(2,3500);
        sheet.setColumnWidth(3,6500);
        sheet.setColumnWidth(4,6500);
        sheet.setColumnWidth(5,6500);
        sheet.setColumnWidth(6,6500);
        sheet.setColumnWidth(7,2500);

        //sheet的样式
        HSSFCellStyle sheetStyle=workbook.createCellStyle();
        // 背景色的设定
        sheetStyle.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
        // 前景色的设定
        sheetStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        //填充模式
        sheetStyle.setFillPattern(HSSFCellStyle.FINE_DOTS);

        //设置每一列的样式
        for(int i=0;i<=14;i++){
            sheet.setDefaultColumnStyle((short)i,sheetStyle);
        }

        //设置字体
        HSSFFont headFont=workbook.createFont();
        headFont.setFontName("宋体");
        headFont.setFontHeightInPoints((short)22);//字体的大小
        headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗

        //另一个样式
        HSSFCellStyle headStyle=workbook.createCellStyle();
        headStyle.setFont(headFont);
        headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//左右剧中
        headStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中
        headStyle.setLocked(true); //列宽固定
        headStyle.setWrapText(true);//自动换行

        //另一个字体样式
        HSSFFont columnHeadFont=workbook.createFont();
        columnHeadFont.setFontName("宋体");
        columnHeadFont.setFontHeightInPoints((short)10);
        columnHeadFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

        //列头的样式
        HSSFCellStyle columnHeadStyle=workbook.createCellStyle();
        columnHeadStyle.setFont(columnHeadFont);
        columnHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        columnHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        columnHeadStyle.setLocked(true);
        columnHeadStyle.setWrapText(true);

        columnHeadStyle.setLeftBorderColor(HSSFColor.BLACK.index);//左边框的颜色
        columnHeadStyle.setBorderLeft((short)1);//左边框的大小
        columnHeadStyle.setRightBorderColor(HSSFColor.BLUE.index);//右边框的颜色
        columnHeadStyle.setBorderRight((short)1);
        columnHeadStyle.setBottomBorderColor(HSSFColor.BLACK.index);
        columnHeadStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//设置单元格的边框为粗体

        columnHeadStyle.setFillForegroundColor(HSSFColor.WHITE.index); //设置单元格的背景色为白色

        HSSFFont font=workbook.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 10);

        // 普通单元格样式
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        style.setAlignment(HSSFCellStyle.ALIGN_LEFT);// 左右居中
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);// 上下居中
        style.setWrapText(true);
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        style.setBorderLeft((short) 1);
        style.setRightBorderColor(HSSFColor.BLACK.index);
        style.setBorderRight((short) 1);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体
        style.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色．
        style.setFillForegroundColor(HSSFColor.WHITE.index);// 设置单元格的背景颜色．

        //另一个样式
        HSSFCellStyle centerstyle = workbook.createCellStyle();
        centerstyle.setFont(font);
        centerstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
        centerstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
        centerstyle.setWrapText(true);
        centerstyle.setLeftBorderColor(HSSFColor.BLACK.index);
        centerstyle.setBorderLeft((short) 1);
        centerstyle.setRightBorderColor(HSSFColor.BLACK.index);
        centerstyle.setBorderRight((short) 1);
        centerstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体
        centerstyle.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色．
        centerstyle.setFillForegroundColor(HSSFColor.WHITE.index);// 设置单元格的背景颜色．

        try{
            // 创建第一行
            HSSFRow row0 = sheet.createRow(0);
            // 设置行高
            row0.setHeight((short) 900);
            // 创建第一列
            HSSFCell cell0 = row0.createCell(0);
            cell0.setCellValue(new HSSFRichTextString("中非发展基金投资项目调度会工作落实情况对照表"));
            cell0.setCellStyle(headStyle);
            /**
             * 合并单元格
             * 第一个参数：第一个单元格的行数（从0开始）
             * 第二个参数：第二个单元格的行数（从0开始）
             * 第三个参数：第一个单元格的列数（从0开始）
             * 第四个参数：第二个单元格的列数（从0开始）
             */
            CellRangeAddress range = new CellRangeAddress(0, 0, 0, 7);
            sheet.addMergedRegion(range);
            // 创建第二行
            HSSFRow row1 = sheet.createRow(1);
            HSSFCell cell1 = row1.createCell(0);
            cell1.setCellValue(new HSSFRichTextString("本次会议时间：2009年8月31日       前次会议时间：2009年8月24日"));
            cell1.setCellStyle(centerstyle);
            // 合并单元格
            range = new CellRangeAddress(1, 2, 0, 7);
            sheet.addMergedRegion(range);

            // 第三行
            HSSFRow row2 = sheet.createRow(3);
            row2.setHeight((short) 750);
            HSSFCell cell = row2.createCell(0);
            cell.setCellValue(new HSSFRichTextString("责任者"));
            cell.setCellStyle(columnHeadStyle);
            cell = row2.createCell(1);
            cell.setCellValue(new HSSFRichTextString("成熟度排序"));
            cell.setCellStyle(columnHeadStyle);
            cell = row2.createCell(2);
            cell.setCellValue(new HSSFRichTextString("事项"));
            cell.setCellStyle(columnHeadStyle);
            cell = row2.createCell(3);
            cell.setCellValue(new HSSFRichTextString("前次会议要求/n/新项目的项目概要"));
            cell.setCellStyle(columnHeadStyle);
            cell = row2.createCell(4);
            cell.setCellValue(new HSSFRichTextString("上周工作进展"));
            cell.setCellStyle(columnHeadStyle);
            cell = row2.createCell(5);
            cell.setCellValue(new HSSFRichTextString("本周工作计划"));
            cell.setCellStyle(columnHeadStyle);
            cell = row2.createCell(6);
            cell.setCellValue(new HSSFRichTextString("问题和建议"));
            cell.setCellStyle(columnHeadStyle);
            cell = row2.createCell(7);
            cell.setCellValue(new HSSFRichTextString("备 注"));
            cell.setCellStyle(columnHeadStyle);
            // 访问数据库，得到数据集


            String filename = "hhh.xls";//设置下载时客户端Excel的名称
            File file=new File(path+filename);
            file.createNewFile();
            OutputStream outputStream=new FileOutputStream(file);
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();



//            filename = Util.encodeFilename(filename, request);
//            response.setContentType("application/vnd.ms-excel");
//            response.setHeader("Content-disposition", "attachment;filename=" + filename);
//            OutputStream ouputStream = response.getOutputStream();
//            workbook.write(ouputStream);
//            ouputStream.flush();
//            ouputStream.close();

        }catch (Exception e){

        }
    }


    //单元各的样式和合并等
    @Override
    public void headSheetOne(){
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();// 创建一个Excel文件
            HSSFSheet sheet = workbook.createSheet(); //创建一个Excel的Sheet

            //设置列宽
            sheet.setColumnWidth(0, 6500);
            sheet.setColumnWidth(1, 6500);
            sheet.setColumnWidth(2, 6500);
            sheet.setColumnWidth(3, 6500);
            sheet.setColumnWidth(4, 6500);
            sheet.setColumnWidth(5, 6500);
            sheet.setColumnWidth(6, 6500);
            sheet.setColumnWidth(7, 6500);

            //设置字体
            HSSFFont headFont=workbook.createFont();
            headFont.setFontName("宋体");
            headFont.setFontHeightInPoints((short)22);//字体的大小
            headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗

            HSSFCellStyle headStyle=workbook.createCellStyle();
            headStyle.setFont(headFont);
            headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//左右剧中
            headStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中
            headStyle.setLocked(true); //列宽固定
            headStyle.setWrapText(true);//自动换行



            // 创建第一行
            HSSFRow row0 = sheet.createRow(0);
            // 设置行高
            row0.setHeight((short) 900);
            // 创建第一列
            HSSFCell cell0 = row0.createCell(0);
            cell0.setCellValue(new HSSFRichTextString("中非发展基金投资项目调度会工作落实情况对照表"));
            cell0.setCellStyle(headStyle);
            /**
             * 合并单元格
             * 第一个参数：第一个单元格的行数（从0开始）
             * 第二个参数：第二个单元格的行数（从0开始）
             * 第三个参数：第一个单元格的列数（从0开始）
             * 第四个参数：第二个单元格的列数（从0开始）
             */
            CellRangeAddress range = new CellRangeAddress(0, 0, 0, 7);
            sheet.addMergedRegion(range);


            // 第二行

            //列头标题的样式
            HSSFFont columnHeadFont=workbook.createFont();
            columnHeadFont.setFontName("宋体");
            columnHeadFont.setFontHeightInPoints((short)10);
            columnHeadFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

            HSSFCellStyle columnHeadStyle=workbook.createCellStyle();
            columnHeadStyle.setFont(columnHeadFont);
            columnHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            columnHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            columnHeadStyle.setLocked(true);
            columnHeadStyle.setWrapText(true);

            columnHeadStyle.setLeftBorderColor(HSSFColor.BLACK.index);//左边框的颜色
            columnHeadStyle.setBorderLeft((short)1);//左边框的大小
            columnHeadStyle.setRightBorderColor(HSSFColor.BLUE.index);//右边框的颜色
            columnHeadStyle.setBorderRight((short)1);
            columnHeadStyle.setBottomBorderColor(HSSFColor.BLACK.index);
            columnHeadStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//设置单元格的边框为粗体

            columnHeadStyle.setFillForegroundColor(HSSFColor.WHITE.index); //设置单元格的背景色为白色

            HSSFRow row2 = sheet.createRow(1);
            row2.setHeight((short) 750);
            HSSFCell cell = row2.createCell(0);
            cell.setCellValue(new HSSFRichTextString("责任者"));
            cell.setCellStyle(columnHeadStyle);
            cell = row2.createCell(1);
            cell.setCellValue(new HSSFRichTextString("成熟度排序"));
            cell.setCellStyle(columnHeadStyle);
            cell = row2.createCell(2);
            cell.setCellValue(new HSSFRichTextString("事项"));
            cell.setCellStyle(columnHeadStyle);
            cell = row2.createCell(3);
            cell.setCellValue(new HSSFRichTextString("前次会议要求/n/新项目的项目概要"));
            cell.setCellStyle(columnHeadStyle);
            cell = row2.createCell(4);
            cell.setCellValue(new HSSFRichTextString("上周工作进展"));
            cell.setCellStyle(columnHeadStyle);
            cell = row2.createCell(5);
            cell.setCellValue(new HSSFRichTextString("本周工作计划"));
            cell.setCellStyle(columnHeadStyle);
            cell = row2.createCell(6);
            cell.setCellValue(new HSSFRichTextString("问题和建议"));
            cell.setCellStyle(columnHeadStyle);
            cell = row2.createCell(7);
            cell.setCellValue(new HSSFRichTextString("备 注"));
            cell.setCellStyle(columnHeadStyle);


            String filename = "headSheetOne.xls";//设置下载时客户端Excel的名称
            File file = new File(path + filename);
            file.createNewFile();
            OutputStream outputStream = new FileOutputStream(file);
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //表格的简单的demo   xxx的考试成绩
    @Override
    public void excelDemoLocal(){
        try{
            HSSFWorkbook workbook = new HSSFWorkbook();// 创建一个Excel文件
            HSSFSheet sheet = workbook.createSheet(); //创建一个Excel的Sheet

            //设置列宽
            sheet.setColumnWidth(0, 5000);
            sheet.setColumnWidth(1, 5000);
            sheet.setColumnWidth(2, 5000);
            sheet.setColumnWidth(3, 5000);
            sheet.setColumnWidth(4, 5000);
            sheet.setColumnWidth(5, 5000);


            //设置字体
            HSSFFont headFont=workbook.createFont();
            headFont.setFontName("宋体");
            headFont.setFontHeightInPoints((short)22);//字体的大小
            headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗

            HSSFCellStyle headStyle=workbook.createCellStyle();
            headStyle.setFont(headFont);
            headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//左右剧中
            headStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中
            headStyle.setLocked(true); //列宽固定
            headStyle.setWrapText(true);//自动换行



            // 创建第一行
            HSSFRow row0 = sheet.createRow(0);
            // 设置行高
            row0.setHeight((short) 900);
            // 创建第一列
            HSSFCell cell0 = row0.createCell(0);
            cell0.setCellValue(new HSSFRichTextString("张好的成绩详情"));
            cell0.setCellStyle(headStyle);
            /**
             * 合并单元格
             * 第一个参数：第一个单元格的行数（从0开始）
             * 第二个参数：第二个单元格的行数（从0开始）
             * 第三个参数：第一个单元格的列数（从0开始）
             * 第四个参数：第二个单元格的列数（从0开始）
             */
            CellRangeAddress range = new CellRangeAddress(0, 0, 0, 5);
            sheet.addMergedRegion(range);


            // 第二行

            //列头标题的样式
            HSSFFont columnHeadFont=workbook.createFont();
            columnHeadFont.setFontName("宋体");
            columnHeadFont.setFontHeightInPoints((short)10);
            columnHeadFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

            HSSFCellStyle columnHeadStyle=workbook.createCellStyle();
            columnHeadStyle.setFont(columnHeadFont);
            columnHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            columnHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            columnHeadStyle.setLocked(true);
            columnHeadStyle.setWrapText(true);

            columnHeadStyle.setLeftBorderColor(HSSFColor.BLACK.index);//左边框的颜色
            columnHeadStyle.setBorderLeft((short)1);//左边框的大小
            columnHeadStyle.setRightBorderColor(HSSFColor.BLUE.index);//右边框的颜色
            columnHeadStyle.setBorderRight((short)1);
            columnHeadStyle.setBottomBorderColor(HSSFColor.BLACK.index);
            columnHeadStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//设置单元格的边框为粗体

            columnHeadStyle.setFillForegroundColor(HSSFColor.WHITE.index); //设置单元格的背景色为白色

            HSSFRow row2 = sheet.createRow(1);
            row2.setHeight((short) 750);
            HSSFCell cell = row2.createCell(0);
            cell.setCellValue(new HSSFRichTextString("学年"));
            cell.setCellStyle(columnHeadStyle);
            cell = row2.createCell(1);
            cell.setCellValue(new HSSFRichTextString("月份"));
            cell.setCellStyle(columnHeadStyle);
            cell = row2.createCell(2);
            cell.setCellValue(new HSSFRichTextString("数学"));
            cell.setCellStyle(columnHeadStyle);
            cell = row2.createCell(3);
            cell.setCellValue(new HSSFRichTextString("语文"));
            cell.setCellStyle(columnHeadStyle);
            cell = row2.createCell(4);
            cell.setCellValue(new HSSFRichTextString("英语"));
            cell.setCellStyle(columnHeadStyle);
            cell = row2.createCell(5);
            cell.setCellValue(new HSSFRichTextString("物理"));
            cell.setCellStyle(columnHeadStyle);
            cell = row2.createCell(6);

            CellRangeAddressList regions = new CellRangeAddressList(1,40,0,0);

            //生成下拉框内容
            String[] arrs={"one","two"};
            DVConstraint constraint = DVConstraint.createExplicitListConstraint(arrs);

            //绑定下拉框和作用区域
            HSSFDataValidation data_validation = new HSSFDataValidation(regions,constraint);

            //对sheet页生效
            sheet.addValidationData(data_validation);


            HSSFCellStyle defaultStyle=workbook.createCellStyle();
            defaultStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            defaultStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            defaultStyle.setLocked(true);
            defaultStyle.setWrapText(true);

            //填充成绩
          //  List<Chenji> list=getList();
            List<Chenji> list=null;
            if(list!=null&&list.size()>0){
                List<RangeHelp> ranges=new ArrayList<>();
                int i=0;
                RangeHelp ran=null;
                for(;i<list.size();i++){
                    Chenji chengji=list.get(i);
                    HSSFRow rowi=sheet.createRow(i+2);
                    rowi.setHeight((short)700);
                    if(ran==null){
                        ran=new RangeHelp();
                        ran.setFirstRow(i+2);
                        ran.setFirstCol(0);
                        ran.setRangLabel(chengji.getYear()+"");
                    }else{
                        if(!ran.getRangLabel().equals(chengji.getYear()+"")){
                            ran.setLastRow(i+1);
                            ran.setLastCol(0);
                            ranges.add(ran);

                            ran=new RangeHelp();
                            ran.setFirstRow(i+2);
                            ran.setFirstCol(0);
                            ran.setRangLabel(chengji.getYear()+"");
                        }
                    }

                    HSSFCell celli = rowi.createCell(0);
                    celli.setCellValue(chengji.getYear());
                    celli.setCellStyle(defaultStyle);

                    celli = rowi.createCell(1);
                    celli.setCellValue(chengji.getMonth());
                    celli.setCellStyle(defaultStyle);

                    celli = rowi.createCell(2);
                    celli.setCellValue(chengji.getMath());
                    celli.setCellStyle(defaultStyle);

                    celli = rowi.createCell(3);
                    celli.setCellValue(chengji.getChinese());
                    celli.setCellStyle(defaultStyle);

                    celli = rowi.createCell(4);
                    celli.setCellValue(chengji.getEnglish());
                    celli.setCellStyle(defaultStyle);

                    celli = rowi.createCell(5);
                    celli.setCellValue(chengji.getWuli());
                    celli.setCellStyle(defaultStyle);
                }
                //添加最后一个合并
                if(ran!=null){
                    ran.setLastRow(i+1);
                    ran.setLastCol(0);
                    ranges.add(ran);
                }

                //按年份合并
                if(ranges!=null&&ranges.size()>0){
                    for(RangeHelp rangHelp:ranges){
                        CellRangeAddress rangeDefault = new CellRangeAddress(rangHelp.getFirstRow(), rangHelp.getLastRow(), rangHelp.getFirstCol(), rangHelp.getLastCol());
                        sheet.addMergedRegion(rangeDefault);
                    }
                }
                //CellRangeAddress rangeDefault = new CellRangeAddress(2, 5, 0, 0);
                //sheet.addMergedRegion(rangeDefault);


                CellRangeAddress rangeLast = new CellRangeAddress(i+2, i+2, 1, 5);
                sheet.addMergedRegion(rangeLast);

                HSSFRow rowLast=sheet.createRow(i+2);
                rowLast.setHeight((short)700);
                HSSFCell cellLast = rowLast.createCell(0);
                cellLast.setCellValue(new HSSFRichTextString("李靖老师"));
                cellLast.setCellStyle(defaultStyle);
                cellLast = rowLast.createCell(1);
                cellLast.setCellValue(new HSSFRichTextString("原来原差了，明天叫家长。"));
                cellLast.setCellStyle(defaultStyle);
            }


            String filename = "张好的成绩单.xls";//设置下载时客户端Excel的名称
            File file = new File(path + filename);
            file.createNewFile();
            OutputStream outputStream = new FileOutputStream(file);
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //成绩列表  模拟数据库查询
    private List<Chenji> getList(){
        List<Chenji> list=new ArrayList<>();
        Chenji yjOne=new Chenji(2019,1,78.5f,87.5f,68f,87f);
        Chenji yjTwo=new Chenji(2019,2,88.5f,90f,75.5f,83f);
        Chenji yjSan=new Chenji(2019,3,82.5f,79f,69f,67f);
        Chenji yjSi=new Chenji(2019,4,66f,79f,86f,88f);

        Chenji ybOne=new Chenji(2018,1,98f,97f,94f,92f);
        Chenji ybTwo=new Chenji(2018,2,89f,68f,78f,77f);
        Chenji ybSan=new Chenji(2018,3,86f,88f,65f,89f);
        Chenji ybSi=new Chenji(2018,4,63f,76f,76f,96f);

        list.add(ybOne);
        list.add(ybTwo);
        list.add(ybSan);
        list.add(ybSi);

        list.add(yjOne);
        list.add(yjTwo);
        list.add(yjSan);
        list.add(yjSi);
        return list;
    }


    //测试使用注解的方式
    @Override
    public void testAnnotationXls(){
        XlsGenete<Peo> xlsGenete=new XlsGenete<Peo>();
        HSSFWorkbook workbook=xlsGenete.createWorkBook(list(),Peo.class);

        String filename = "annotation合并多列1.xls";//设置下载时客户端Excel的名称
        File file = new File(path + filename);
        try {
            file.createNewFile();
            OutputStream outputStream = new FileOutputStream(file);
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private List<Peo> list(){
        List<Peo> list=new ArrayList<>();
        //String name, Integer number, Integer age, String addr
        Peo peoOne=new Peo("李四",110,5,"贵州惠水","一班");
        Peo peoTwo=new Peo("张三",111,5,"六盘水","一班");
        Peo peoSan=new Peo("张三",119,5,"六盘水","一班");
        Peo peoSi=new Peo("张三",119,5,"贵州惠水","一班");
        Peo peoWu=new Peo("张三",114,6,"贵州惠水","一班");
        Peo peoLiu=new Peo("张三",118,6,"贵州惠水","一班");
        Peo peoQi=new Peo("王五",118,7,"六盘水","一班");
        list.add(peoOne);
        list.add(peoTwo);
        list.add(peoSan);
        list.add(peoSi);
        list.add(peoWu);
        list.add(peoLiu);
        list.add(peoQi);
        return list;
    }
}

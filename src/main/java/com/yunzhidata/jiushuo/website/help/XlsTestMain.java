package com.yunzhidata.jiushuo.website.help;

import com.yunzhidata.jiushuo.website.dto.Chenji;
import com.yunzhidata.jiushuo.website.dto.RangeHelp;
import com.yunzhidata.jiushuo.website.help.img.ImgTest;
import com.yunzhidata.jiushuo.website.help.xlsgenelate.XlsGenete;
import com.yunzhidata.jiushuo.website.help.xlstestentity.Peo;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class XlsTestMain {




    private static InputStream getInputStream(){
        try {
            InputStream inputStream=new FileInputStream(new File("D:\\data\\imgpath\\testimg\\ttwo.jpg"));
            return inputStream;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void createFormatDate(){
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


            String filename = "测试生成时间.xls";//设置下载时客户端Excel的名称
            File file = new File("D:/data/imgpath/" + filename);
            file.createNewFile();
            OutputStream outputStream = new FileOutputStream(file);
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    //https://www.cnblogs.com/atao/archive/2009/11/15/1603528.html  NPOI
    /**
     * 插入图片
     * dx1：起始单元格的x偏移量 起始位置距单元格左侧的距离，如例子中的255表示直线起始位置距A1单元格左侧的距离；
     * dy1：起始单元格的y偏移量 起始位置距单元格上侧的距离，如例子中的125表示直线起始位置距A1单元格上侧的距离；
     * dx2：终止单元格的x偏移量 1023表示直线起始位置距单元格左侧的距离，如例子中的1023表示直线起始位置距C3单元格左侧的距离；
     * dy2：终止单元格的y偏移量 150表示直线起始位置距单元格上侧的距离，如例子中的150表示直线起始位置距C3单元格上侧的距离；
     * col1：起始单元格列序号，从0开始计算；
     * row1：起始单元格行序号，从0开始计算，如例子中col1=0,row1=0就表示起始单元格为A1；
     * col2：终止单元格列序号，从0开始计算；
     * row2：终止单元格行序号，从0开始计算，如例子中col2=2,row2=2就表示起始单元格为C3；
     *
     * */
    private static void npoiInsertImg(){
        File imgPath=new File("D:\\data\\imgpath\\testimg\\aaaa.jpg");
        try {
            HSSFWorkbook hssfworkbook = new HSSFWorkbook();// 创建一个Excel文件
            InputStream inputStream=new FileInputStream(imgPath);
            byte[] bytes=new byte[inputStream.available()];
             inputStream.read(bytes);
            int pictureIdx = hssfworkbook.addPicture(bytes,HSSFWorkbook.PICTURE_TYPE_JPEG);
            HSSFSheet sheet = hssfworkbook.createSheet("Sheet1");
            HSSFPatriarch patriarch = sheet.createDrawingPatriarch();

            //add a picture   (int dx1, int dy1, int dx2, int dy2, short col1, int row1, short col2, int row2)
            HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1023, 0, (short)10, 10, (short)10, 10);
            HSSFPicture pict = patriarch.createPicture(anchor, pictureIdx);
            pict.resize();

            String filename = "xls插入图片.xls";//设置下载时客户端Excel的名称
            File file = new File("D:/data/imgpath/" + filename);
            file.createNewFile();
            OutputStream outputStream = new FileOutputStream(file);
            hssfworkbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private static List<Peo> list(){
        List<Peo> list=new ArrayList<>();
        //String name, Integer number, Integer age, String addr
        Peo peoOne=new Peo("李四",110,23,"贵州惠水","一班");
        Peo peoTwo=new Peo("张三",111,25,"六盘水","一班");
        Peo peoSan=new Peo("张三",119,26,"六盘水","三班");
        Peo peoSi=new Peo("张三",119,21,"贵州惠水","三班");
        Peo peoWu=new Peo("张三",114,29,"贵州惠水","三班");
        Peo peoLiu=new Peo("张三",118,31,"贵州惠水","一班");
        Peo peoQi=new Peo("王五",118,33,"六盘水","一班");
        peoQi.setInputStream(getInputStream());
        list.add(peoOne);
        list.add(peoTwo);
        list.add(peoSan);
        list.add(peoSi);
        list.add(peoWu);
        list.add(peoLiu);
        list.add(peoQi);
        return list;
    }

    /**
     * 绘制图形
     *
     *
     *
     * IVIEW  图形化
     * */


    public static void main(String[] args){

        ImgTest.test_gxian();


//        XlsGenete<Peo> xlsGenete=new XlsGenete<Peo>();
//        HSSFWorkbook workBook=xlsGenete.createWorkBook(list(),Peo.class);
//        String filename = "annotation测试comp.xls";//设置下载时客户端Excel的名称
//        try {
//            File file = new File("D:/data/imgpath/" + filename);
//            file.createNewFile();
//            OutputStream outputStream = new FileOutputStream(file);
//            workBook.write(outputStream);
//            outputStream.flush();
//            outputStream.close();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }
}

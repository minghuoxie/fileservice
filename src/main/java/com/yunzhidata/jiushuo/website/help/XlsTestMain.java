package com.yunzhidata.jiushuo.website.help;

import com.yunzhidata.jiushuo.website.help.xlsgenelate.XlsGenete;
import com.yunzhidata.jiushuo.website.help.xlstestentity.Peo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class XlsTestMain {

//    public static void main(String[] args){
//        XlsGenete<Peo> xlsGenete=new XlsGenete<Peo>();
//        HSSFWorkbook workBook=xlsGenete.createWorkBook(list(),Peo.class);
//        String filename = "测试annotation的接口.xls";//设置下载时客户端Excel的名称
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
//    }

    private static List<Peo> list(){
        List<Peo> list=new ArrayList<>();
        //String name, Integer number, Integer age, String addr
        Peo peoOne=new Peo("李四",110,5,"贵州惠水");
        Peo peoTwo=new Peo("张三",111,5,"六盘水");
        Peo peoSan=new Peo("张三",119,5,"六盘水");
        Peo peoSi=new Peo("张三",119,5,"贵州惠水");
        Peo peoWu=new Peo("张三",114,6,"贵州惠水");
        Peo peoLiu=new Peo("张三",118,6,"贵州惠水");
        Peo peoQi=new Peo("王五",118,7,"六盘水");
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

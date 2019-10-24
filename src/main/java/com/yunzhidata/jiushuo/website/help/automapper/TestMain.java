package com.yunzhidata.jiushuo.website.help.automapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestMain {
    public static void main(String[] args){
        List<SourcePro> listSources=new ArrayList<>();
        SourcePro pro=new SourcePro();
        pro.setId(1);
        pro.setAge("23");
        pro.setName("账号");
        pro.setDate(new Date());

        SourcePro proTwo=new SourcePro();
        proTwo.setId(2);
        proTwo.setAge("53");
        proTwo.setName("牛号");
        proTwo.setDate(new Date());

        SourcePro proSi=new SourcePro();
        proSi.setId(3);
        proSi.setAge("103");
        proSi.setName("真好");
        proSi.setDate(new Date());

        listSources.add(pro);
        listSources.add(proTwo);
        listSources.add(proSi);

        try {
            DisPro disPro=AutoMapper.mapTo(pro,DisPro.class);
            System.out.println("SourcePro:"+pro+"--date:"+pro.getStrDate());
            System.out.println("DisPro:"+disPro+"--date:"+disPro.getStrDate());

            List<DisPro> list=AutoMapper.mapToList(listSources,DisPro.class);
            list.stream().forEach((action)->{
                System.out.println("DisPro--:"+action+"--date:"+action.getStrDate());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.yunzhidata.jiushuo.website.help.jdkeightnew;

import com.yunzhidata.jiushuo.website.help.jdkeightnew.consumerandpredicate.PredicateAndConsumerDemo;
import com.yunzhidata.jiushuo.website.help.jdkeightnew.getfieldstr.BaseGetName;
import com.yunzhidata.jiushuo.website.help.jdkeightnew.getfieldstr.FieldPro;

public class TestMain {

    public static void main(String[] args){
//        IFunction<Integer,String> function=String::valueOf;
//        String str=function.apply(10);
//        System.out.println(str);

        FieldPro pro=new FieldPro(1,1,"李斯特","李斯特");
        FieldPro proTwo=new FieldPro(1,1,"李斯特","李斯特");
        BaseGetName<FieldPro> getName=new BaseGetName();
        String s= null;
        try {
            s = getName.getFunctionName(FieldPro::getAddrs);
            System.out.println("---"+s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void test(String s){
        String s2="hahah3";
        System.out.println(s==s2);
    }

    /**
     * 方法的引用
     * */
    private static void testMethod(){
        MethodYingYong.use();
    }

    /**
     * Consumer的使用
     * */
    private static void testUseConsumer(){
        NewConsumer.testConsumer();
    }

    /**
     * Consumer和Predicate的使用
     * */
    private static void testUseConsumerAndPredicate(){
        PredicateAndConsumerDemo.test();
    }
}

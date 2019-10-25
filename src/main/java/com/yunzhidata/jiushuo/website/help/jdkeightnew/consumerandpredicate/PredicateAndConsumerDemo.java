package com.yunzhidata.jiushuo.website.help.jdkeightnew.consumerandpredicate;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class PredicateAndConsumerDemo {
    public static StuPro updateStuFee(StuPro stu, Predicate<StuPro> predicate, Consumer<StuPro> consumer){
        if(predicate.test(stu)){
            consumer.accept(stu);
        }
        return stu;
    }

    public static void test(){
        StuPro stu1=new StuPro("ak","ak47",9.5);
        stu1=PredicateAndConsumerDemo.updateStuFee(stu1, new Predicate<StuPro>() {

            @Override
            public boolean test(StuPro stuPro) {
                return stuPro.grade>8.5;
            }
        }, new Consumer<StuPro>() {
            @Override
            public void accept(StuPro stuPro) {
                stuPro.feeDiscount=30.0;
            }
        });
        stu1.printFee();

        StuPro stu2=new StuPro("ak","ak47",8.0);
        stu2=PredicateAndConsumerDemo.updateStuFee(stu2,stu->stu.grade>=8,stu->stu.feeDiscount=20.0);
        stu2.printFee();
    }
}

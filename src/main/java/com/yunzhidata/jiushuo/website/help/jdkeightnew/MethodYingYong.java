package com.yunzhidata.jiushuo.website.help.jdkeightnew;

import org.apache.logging.log4j.util.Supplier;

import java.util.Arrays;
import java.util.List;

public class MethodYingYong {
    /**
     * java8新特性的方法引用
     * 1，方法引用通过方法的名字来指向一个方法
     * 2，方法引用使用一对::
     * */

    private String name;
    public static MethodYingYong create(final Supplier<MethodYingYong> supplier){
        return supplier.get();
    }

    public static void collide(final MethodYingYong car){
        System.out.println("Collided "+car.toString());
    }

    public void follow(final MethodYingYong another){
        System.out.println("Following the "+another.toString());
    }

    public void repair(){
        System.out.println("repair "+this.toString());
    }


    public static void use(){
        //构造器引用，语法是Class::new
        final MethodYingYong meth=MethodYingYong.create(MethodYingYong::new);
        //将meth添加到集合中
        final List<MethodYingYong> cars= Arrays.asList(meth);
        //静态方法的引用：语法是Class::static_method
        cars.forEach(MethodYingYong::collide);
        //特定类的任意方法对象的方法引用：语法是Class::method
        cars.forEach(MethodYingYong::repair);
        //特定对象的方法引用，语法是instance::method
        final MethodYingYong tmeth=MethodYingYong.create(MethodYingYong::new);
        cars.forEach(tmeth::follow);
    }
}

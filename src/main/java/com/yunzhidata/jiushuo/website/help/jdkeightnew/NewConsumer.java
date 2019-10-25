package com.yunzhidata.jiushuo.website.help.jdkeightnew;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NewConsumer {

    public static void testConsumer(){
        Consumer<Integer> consumer=x->{
          int a=x+2;
          System.out.println(a);
          System.out.println(a+"_");
        };

        consumer.accept(11);
        consumer.accept(12);
    }

    public static void testConsumerTwo(){
        Consumer<String> consumer=x->{
          System.out.println("--"+x);
        };

        Stream<String> stream=Stream.of("aa","bb","cc");
        stream.forEach(consumer);
        stream=Stream.of("gg","hh","bb");
        stream.forEach((y)->{
            System.out.println("--hhh"+y);
        });
    }

    public static void testPredicate(){
        Stream<Integer> stream=Stream.of(1,2,3,4,5,6,7,8,9,10);
//        Predicate<Integer> predicate=x->{
//          return x%2==0;
//        };
//        List<Integer> newStream=stream.filter(predicate).collect(Collectors.toList());
//        System.out.println(newStream);

        List<Integer> list=stream.filter(x->x%2==0).collect(Collectors.toList());
        System.out.println(list);
    }

    public static void testFunction(){
        Function<String,Integer> function=new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                if(s!=null){
                    return s.length();
                }
                return null;
            }
        };

        Stream<String> strStream= Stream.of("a","bb","ccddd");
        Stream<Integer> intStream=strStream.map(function);
        intStream.forEach((x)->{
            System.out.println(x);
        });

//        Stream<String> strStream= Stream.of("a","bb","ccddd");
//        Stream<Integer> intStream=strStream.map((x)->{
//            return x.length();
//        });
//        intStream.forEach(x->System.out.println(x));
    }


    /**
     * java8的新特性之规约  reduce
     * T reduce(T t,BinaryOperator b)  可以将流中元素反复结合起来，得到一个值。返回T
     * reduce(BinaryOperator b)  可以将流中元素反复结合起来，得到一个值。返回Optional<T>
     *
     * */
    public static void testReduce(){
        Stream<Integer> stream=Stream.of(1,6,5,3,1,3,4,6,7,5);
        Integer re=stream.reduce(0,(x,y)->x+y);
        System.out.println(re);
    }

    public static void testReduceTwo(){
        Stream<Integer> stream=Stream.of(1,6,5,3,1,3,4,6,7,5);
        Optional<Integer> optional =stream.reduce((x, y)->x+y);
        Integer re=optional.get();
        System.out.println(re);
    }

    /**
     * Stream的操作
     * */
    public static void testStream(){
       // List<Integer> list= Stream.of(1,2,3,4,5,7,34,45,234,456,23,1,4,456).collect(Collectors.toList());
        List<Integer> list=Arrays.asList(1,2,3,4,5,7,34,45,234,456,23,1,4,456);
        //将return的结果赋值给y  下一个结果给x
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                //o1-o2表示正序
                //o2-o1表示倒序
                return o2-o1;
            }
        });

        System.out.println(list);
    }

}

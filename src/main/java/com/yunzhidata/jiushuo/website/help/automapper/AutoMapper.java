package com.yunzhidata.jiushuo.website.help.automapper;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

public class AutoMapper {
    /**
     * 使用java反射完成自动赋值功能 ，两个类的属性字段必须要一致
     * */
    //自动类型赋值 单个对象
    public static <TSource,TDestination> TDestination mapTo(final TSource source, final Class<TDestination> destType) throws Exception {
        Field[] declaredFields=source.getClass().getDeclaredFields();
        TDestination dis=destType.newInstance();
      //  Field[] disFields=destType.getDeclaredFields();
        if(declaredFields!=null&&declaredFields.length>0){
            for(Field field:declaredFields){
                Field disField=destType.getDeclaredField(field.getName());
                field.setAccessible(true);
                disField.setAccessible(true);
                disField.set(dis,field.get(source));

//                Method method=destType.getMethod(field.getName(),destType);
//                method.invoke(dis,field.get(source));

//                for(Field disField:disFields){
//                    if(field.getName()!=null&&field.getName().equals(disField.getName())){
//                        field.setAccessible(true);
//                        disField.setAccessible(true);
//                        disField.set(dis,field.get(source));
//                    }
//                }
            }
        }
        return dis;
    }

    //list集合
    public static <TSource,TDestination> List<TDestination> mapToList(final List<TSource> source,final Class<TDestination> destType)throws Exception{
        List<TDestination> list=new LinkedList<>();
        if(source!=null&&source.size()>0){
            for(TSource sour:source){
                list.add(mapTo(sour,destType));
            }
        }
        return list;
    }
}

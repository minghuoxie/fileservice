package com.yunzhidata.jiushuo.website.help.jdkeightnew.getfieldstr;


import java.beans.Introspector;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;

public class BaseGetName<T> {
    /**
     * 获取字段的名字字符串   根据getXXX来或者XXX
     * */
    public String getFunctionName(IFunction<T, ?> function) throws Exception {
        Method method = function.getClass().getDeclaredMethod("writeReplace");
        method.setAccessible(true);
        SerializedLambda serializedLambda = (SerializedLambda) method.invoke(function);
        String getter = serializedLambda.getImplMethodName();
        String fieldName = Introspector.decapitalize(getter.replace("get", ""));


        //System.out.println("---::"+fieldName);
//        Object s=function.apply(t);
//        System.out.println("s:"+s);
//        Field[] fields=t.getClass().getDeclaredFields();
//        for(Field field:fields){
//            field.setAccessible(true);
//            Object obj=field.get(t);
//            System.out.println(field.getName()+"-obj:"+obj);
//        }
        return fieldName;
    }
}

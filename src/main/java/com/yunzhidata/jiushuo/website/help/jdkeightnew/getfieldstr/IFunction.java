package com.yunzhidata.jiushuo.website.help.jdkeightnew.getfieldstr;

import java.io.Serializable;

/**
 * 继承Serializable，IFunction会包含有一个SerializedLambda记录Lambda的属性
 * */
@FunctionalInterface
public interface IFunction<T, R> extends Serializable {
    R apply(T var1);
}

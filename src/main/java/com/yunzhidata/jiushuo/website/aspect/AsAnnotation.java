package com.yunzhidata.jiushuo.website.aspect;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AsAnnotation {
    AsEnum num();
    String str() default "hahah";
}

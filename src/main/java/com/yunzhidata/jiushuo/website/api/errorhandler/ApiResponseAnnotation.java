package com.yunzhidata.jiushuo.website.api.errorhandler;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiResponseAnnotation {
}

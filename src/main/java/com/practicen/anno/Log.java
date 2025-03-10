package com.practicen.anno;

import java.lang.annotation.*;

@Target(ElementType.METHOD) //作用范围
@Documented //@Log will be visible in the documentation(Javadocs)
@Retention(RetentionPolicy.RUNTIME) //生效时间
public @interface Log {
}

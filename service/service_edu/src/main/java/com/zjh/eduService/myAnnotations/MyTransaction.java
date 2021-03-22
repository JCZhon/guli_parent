package com.zjh.eduService.myAnnotations;

import java.lang.annotation.*;

/**
 * 针对自定义处理事务（靶子，标注这个注解的方法开启自定义事务处理）
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
public @interface MyTransaction {

}

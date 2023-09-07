package com.quick.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: 用于权限控制的切面
 * @author: chenhao
 * @date: 2023-7-20 18:20
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PermissionAuth {
    /**
     * 推送权限code
     * @return
     */
    String pushCode() default "";

    /**
     * 是否需要停用权限验证
     * @return
     */
    boolean needAuth() default true;
}

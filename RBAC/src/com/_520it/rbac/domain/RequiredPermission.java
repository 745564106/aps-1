package com._520it.rbac.domain;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
//注解保存的时期（自定义注解一般保存到运行时期）
@Retention(RetentionPolicy.RUNTIME)
//注解贴的位置
@Target(ElementType.METHOD)
public @interface RequiredPermission {

	String value();
	
}

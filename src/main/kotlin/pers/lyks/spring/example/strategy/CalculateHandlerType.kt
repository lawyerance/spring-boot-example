package pers.lyks.spring.example.strategy

import java.lang.annotation.*

@Target(AnnotationTarget.CLASS, AnnotationTarget.FILE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
annotation class CalculateHandlerType(val value: String = "")

package pers.lyks.spring.example.strategy;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CalculateHandlerType {
    String value() default "";
}

package com.seguoer.annotation;

import com.seguoer.config.FirstAutoConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({FirstAutoConfig.class})
public @interface AutoMyFristConfig {
    boolean required() default true;
}

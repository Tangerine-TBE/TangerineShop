package com.tangerine.ecannotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface PayEntryGenerator {
    String packageName();
    Class<?> payEntryTemplate();
}

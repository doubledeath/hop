package com.github.doubledeath.hop.api.base;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by doubledeath on 2/20/17.
 */
@Qualifier
@Retention(RUNTIME)
@Target({TYPE, METHOD, FIELD})
public @interface Mapper {

    enum Type {
        IMPL_1,
        IMPL_2
    }

    Type value() default Type.IMPL_1;

}

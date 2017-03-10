package com.github.doubledeath.hop.api.service.impl.tag;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by doubledeath on 3/7/17.
 */
@Qualifier
@Retention(RUNTIME)
@Target({TYPE, METHOD, FIELD, PARAMETER})
public @interface TagServiceImpl {

    Name value();

    enum Name {

        NUMBER_TWO_DIGITS,
        NUMBER_FIVE_DIGITS

    }

}

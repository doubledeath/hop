package com.github.doubledeath.hop.api.service.impl.tag;

import com.github.doubledeath.hop.api.service.TagService;
import org.jetbrains.annotations.NotNull;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by doubledeath on 4/03/17.
 */
@SuppressWarnings("unused")
@Stateless(name = "numberTwoDigits")
public class TwoDigitsTagService extends NumberTagService implements TagService {

    public TwoDigitsTagService() {
        super(1L);
    }

    @TransactionAttribute
    @NotNull
    @Override
    public String create() {
        return super.create();
    }

    @TransactionAttribute
    @Override
    public void delete(@NotNull String tag) {
        super.delete(tag);
    }

    @Qualifier
    @Retention(RUNTIME)
    @Target({TYPE, METHOD, FIELD, PARAMETER})
    public @interface Impl {

    }

}

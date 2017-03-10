package com.github.doubledeath.hop.api.service.impl.tag;

import com.github.doubledeath.hop.api.service.TagService;
import org.jetbrains.annotations.NotNull;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.enterprise.inject.Default;

/**
 * Created by doubledeath on 4/03/17.
 */
@SuppressWarnings("unused")
@Stateless(name = "numberFiveDigits")
public class FiveDigitsTagService extends NumberTagService implements TagService {

    public FiveDigitsTagService() {
        super(5L);
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

}

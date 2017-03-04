package com.github.doubledeath.hop.api.service.impl;

import com.github.doubledeath.hop.api.service.TagService;
import org.jetbrains.annotations.NotNull;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

/**
 * Created by doubledeath on 4/03/17.
 */
@SuppressWarnings("unused")
@Stateless(name = "light")
public class LightTagService extends NumberTagService implements TagService {

    public LightTagService() {
        super(2L);
    }

    @TransactionAttribute
    @NotNull
    @Override
    public String generate() {
        return super.generate();
    }

    @TransactionAttribute
    @Override
    public void delete(@NotNull String tag) {
        super.delete(tag);
    }

}

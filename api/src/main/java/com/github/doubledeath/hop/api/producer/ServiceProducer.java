package com.github.doubledeath.hop.api.producer;

import com.github.doubledeath.hop.api.service.TagService;
import com.github.doubledeath.hop.api.service.impl.tag.Impl;
import com.github.doubledeath.hop.api.service.impl.tag.TagServiceImpl;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

/**
 * Created by doubledeath on 3/7/17.
 */
@SuppressWarnings("unused")
@ApplicationScoped
public class ServiceProducer {

    @EJB(beanName = "two")
    private TagService twoDigitsTagService;
    @EJB(beanName = "five")
    private TagService fiveDigitsTagService;

    @TagServiceImpl(Impl.NUMBER_TWO_DIGITS)
    @Produces
    public TagService twoDigitsTagService() {
        return twoDigitsTagService;
    }

    @TagServiceImpl(Impl.NUMBER_FIVE_DIGITS)
    @Produces
    public TagService fiveDigitsTagService() {
        return fiveDigitsTagService;
    }

}

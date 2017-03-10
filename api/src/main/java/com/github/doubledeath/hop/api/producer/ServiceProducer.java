package com.github.doubledeath.hop.api.producer;

import com.github.doubledeath.hop.api.service.TagService;
import com.github.doubledeath.hop.api.service.impl.tag.TagServiceImpl;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.New;
import javax.enterprise.inject.Produces;

/**
 * Created by doubledeath on 3/7/17.
 */
@SuppressWarnings("unused")
@ApplicationScoped
public class ServiceProducer {

    @EJB(beanName = "numberTwoDigits")
    private TagService twoDigitsTagService;
    @EJB(beanName = "numberFiveDigits")
    private TagService fiveDigitsTagService;

    @TagServiceImpl(TagServiceImpl.Name.NUMBER_TWO_DIGITS)
    @Produces
    public TagService twoDigitsTagService() {
        return twoDigitsTagService;
    }

    @TagServiceImpl(TagServiceImpl.Name.NUMBER_FIVE_DIGITS)
    @Produces
    public TagService tagService() {
        return fiveDigitsTagService;
    }

}

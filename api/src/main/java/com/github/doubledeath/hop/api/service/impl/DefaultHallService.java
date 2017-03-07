package com.github.doubledeath.hop.api.service.impl;

import com.github.doubledeath.hop.api.model.Hall;
import com.github.doubledeath.hop.api.repo.HallRepo;
import com.github.doubledeath.hop.api.service.HallService;
import com.github.doubledeath.hop.api.service.TagService;
import com.github.doubledeath.hop.api.service.impl.tag.Impl;
import com.github.doubledeath.hop.api.service.impl.tag.TagServiceImpl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by doubledeath on 3/5/17.
 */
@SuppressWarnings("unused")
@Stateless
public class DefaultHallService implements HallService {

    @Inject
    private HallRepo hallRepo;
    @TagServiceImpl(Impl.NUMBER_FIVE_DIGITS)
    @Inject
    private TagService tagService;

    @Override
    public void test() {
//        Hall hall = hallRepo.create(tagService.create(), "1");
//        hall = hallRepo.findByTag(hall.getTag());

        Set<Hall> firstSet = IntStream
                .range(0, 10)
                .mapToObj(i -> hallRepo.create(tagService.create(), String.valueOf(i)))
                .collect(Collectors.toSet());
    }

}

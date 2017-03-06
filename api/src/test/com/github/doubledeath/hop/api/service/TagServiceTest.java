package com.github.doubledeath.hop.api.service;

import com.github.doubledeath.hop.api.TestBuilder;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by doubledeath on 3/4/17.
 */
@SuppressWarnings("unused")
@RunWith(Arquillian.class)
public class TagServiceTest {

    private static final int TAG_COUNT = 101;

    @EJB(beanName = "light")
    private TagService tagService;

    @Deployment
    public static Archive createDeployment() {
        return TestBuilder.buildDeployment();
    }

    @Test
    public void full() {
        Set<String> firstSet = IntStream
                .range(0, TAG_COUNT)
                .mapToObj(i -> tagService.create())
                .collect(Collectors.toSet());

        Assert.assertTrue(firstSet.size() == TAG_COUNT);

        firstSet.forEach(tagService::delete);

        Set<String> secondSet = IntStream
                .range(0, TAG_COUNT)
                .mapToObj(i -> tagService.create())
                .collect(Collectors.toSet());

        Assert.assertTrue(secondSet.size() == TAG_COUNT);

        Set<String> resultSet = Stream
                .concat(firstSet.stream(), secondSet.stream())
                .collect(Collectors.toSet());

        Assert.assertTrue(resultSet.size() < TAG_COUNT * 2);
    }

}


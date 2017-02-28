package com.github.doubledeath.hop.api.service;

import com.github.doubledeath.hop.api.TestBuilder;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.util.stream.LongStream;

/**
 * Created by doubledeath on 2/28/17.
 */
@SuppressWarnings("unused")
@RunWith(Arquillian.class)
public class TagServiceTest {

    @EJB
    private TagService tagService;

    @Deployment
    public static Archive createDeployment() {
        return TestBuilder.buildDeployment();
    }

    @Test
    public void generate() {
        LongStream.range(1, 6)
                .forEach(this::assertExpectedEqualsGenerated);
    }

    private void assertExpectedEqualsGenerated(Long expected) {
        Long generated = tagService.generate();

        System.out.println("expected: " + expected + ", generated: " + generated);

        Assert.assertEquals(expected, generated);
    }

}

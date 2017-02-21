package com.github.doubledeath.hop.api2.model.builder;

import com.github.doubledeath.hop.api2.model.ComplexTag;
import com.github.doubledeath.hop.api2.model.SimpleTag;

/**
 * Created by doubledeath on 2/20/17.
 */
public class TagBuilder {

    private static final String COMPLEX_TAG_SIGN = "#";

    public SimpleTag buildSimpleTag(Long tag) {
        if (tag == null) {
            return null;
        }

        SimpleTag simpleTag = new SimpleTag();

        simpleTag.setValue(tag);

        return simpleTag;
    }

    public SimpleTag buildSimpleTag(ComplexTag complexTag) {
        if (complexTag == null) {
            return null;
        }

        SimpleTag simpleTag = new SimpleTag();

        simpleTag.setValue(Long.valueOf(complexTag.getValue().substring(complexTag.getValue().lastIndexOf(COMPLEX_TAG_SIGN) + COMPLEX_TAG_SIGN.length())));

        return simpleTag;
    }

    public ComplexTag buildComplexTag(SimpleTag simpleTag, String target) {
        if (simpleTag == null || target == null) {
            return null;
        }

        ComplexTag complexTag = new ComplexTag();

        complexTag.setValue(target + COMPLEX_TAG_SIGN + simpleTag.getValue());

        return complexTag;
    }

}

package com.github.doubledeath.hop.api.helper;

/**
 * Created by doubledeath on 2/17/17.
 */
public final class TagHelper {

    private static final String COMPLEX_TAG_MATCHER = "#";

    public static String getComplexTag(String login, Long tag) {
        return login + COMPLEX_TAG_MATCHER + tag;
    }

    public static Long getSimpleTag(String complexTag) {
        return Long.valueOf(complexTag.substring(complexTag.lastIndexOf(COMPLEX_TAG_MATCHER) + COMPLEX_TAG_MATCHER.length()));
    }

}

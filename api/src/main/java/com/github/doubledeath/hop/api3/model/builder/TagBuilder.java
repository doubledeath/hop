package com.github.doubledeath.hop.api3.model.builder;

import com.github.doubledeath.hop.api3.model.Tag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by doubledeath on 2/21/17.
 */
public class TagBuilder {

    private static final String PREFIX_SEPARATOR = "#";

    @NotNull
    public Tag build(@NotNull Long seed, @Nullable String prefix) {
        Tag tag = new Tag(seed);

        tag.setComplexValue(prefix == null ? null : prefix + PREFIX_SEPARATOR + seed);

        return tag;
    }

    @NotNull
    public Tag build(@NotNull String complexValue) {
        Long seed = Long.valueOf(complexValue.substring(complexValue.lastIndexOf(PREFIX_SEPARATOR) + PREFIX_SEPARATOR.length()));

        Tag tag = new Tag(seed);

        tag.setComplexValue(complexValue);

        return tag;
    }

}

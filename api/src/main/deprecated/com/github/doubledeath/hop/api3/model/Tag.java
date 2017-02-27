package com.github.doubledeath.hop.api3.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by doubledeath on 2/21/17.
 */
public class Tag {

    private Long simpleValue;
    private String complexValue;

    public Tag(@NotNull Long simpleValue) {
        this.simpleValue = simpleValue;
    }

    @NotNull
    public Long getSimpleValue() {
        return simpleValue;
    }

    @Nullable
    public String getComplexValue() {
        return complexValue;
    }

    public void setComplexValue(String complexValue) {
        this.complexValue = complexValue;
    }

    @Override
    public boolean equals(Object object) {
        return this == object || object instanceof Tag && simpleValue.equals(((Tag) object).simpleValue);
    }

}

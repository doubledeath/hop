package com.github.doubledeath.hop.api3.model;

import org.jetbrains.annotations.NotNull;

/**
 * Created by doubledeath on 2/21/17.
 */
public class Key {

    private String value;

    public Key(@NotNull String value) {
        this.value = value;
    }

    @NotNull
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object object) {
        return this == object || object instanceof Key && value.equals(((Key) object).value);
    }

}

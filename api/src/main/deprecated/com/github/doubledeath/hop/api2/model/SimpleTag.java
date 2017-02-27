package com.github.doubledeath.hop.api2.model;

/**
 * Created by doubledeath on 2/20/17.
 */
public class SimpleTag {

    private Long value;

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object target) {
        return this == target || (target instanceof SimpleTag && value.equals(((SimpleTag) target).value));
    }

}

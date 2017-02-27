package com.github.doubledeath.hop.api3.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by doubledeath on 2/21/17.
 */
public class User {

    private Tag tag;
    private String displayName;
    private String description;

    public User(@NotNull Tag tag, @NotNull String displayName) {
        this.tag = tag;
        this.displayName = displayName;
    }

    @NotNull
    public Tag getTag() {
        return tag;
    }

    @NotNull
    public String getDisplayName() {
        return displayName;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object object) {
        return this == object || object instanceof User && tag.equals(((User) object).tag);
    }

}

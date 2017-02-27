package com.github.doubledeath.hop.api3.service.form;

import com.github.doubledeath.hop.api3.model.Key;
import com.github.doubledeath.hop.api3.model.Tag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by doubledeath on 2/21/17.
 */
public class CreateHallForm {

    private Tag owner;
    private Long size;
    private String displayName;
    private Key key;
    private String description;

    public CreateHallForm(@NotNull Tag owner, @NotNull Long size, @NotNull String displayName) {
        this.owner = owner;
        this.size = size;
        this.displayName = displayName;
    }

    @NotNull
    public Tag getOwner() {
        return owner;
    }

    @NotNull
    public Long getSize() {
        return size;
    }

    @NotNull
    public String getDisplayName() {
        return displayName;
    }

    @Nullable
    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

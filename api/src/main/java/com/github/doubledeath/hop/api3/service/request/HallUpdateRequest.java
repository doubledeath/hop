package com.github.doubledeath.hop.api3.service.request;

import com.github.doubledeath.hop.api3.model.Key;
import org.jetbrains.annotations.Nullable;

/**
 * Created by doubledeath on 2/21/17.
 */
public class HallUpdateRequest {

    private Long size;
    private String displayName;
    private Key key;
    private String description;

    @Nullable
    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    @Nullable
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
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

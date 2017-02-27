package com.github.doubledeath.hop.api.endpoint.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by doubledeath on 2/17/17.
 */
public class UpdateUserRequest {

    @JsonProperty("display_name")
    private String displayName;
    private String description;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

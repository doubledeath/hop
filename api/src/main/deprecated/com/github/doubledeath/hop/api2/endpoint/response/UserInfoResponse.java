package com.github.doubledeath.hop.api2.endpoint.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by doubledeath on 2/21/17.
 */
public class UserInfoResponse {

    private Long tag;
    @JsonProperty("display_name")
    private String displayName;
    private String description;

    public Long getTag() {
        return tag;
    }

    public void setTag(Long tag) {
        this.tag = tag;
    }

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

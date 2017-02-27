package com.github.doubledeath.hop.api.endpoint.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by doubledeath on 2/19/17.
 */
public class HallInfoResponse {

    private Long tag;
    private String visibility;
    @JsonProperty("display_name")
    private String displayName;
    private String description;
    @JsonProperty("current_user_count")
    private Long currentUserCount;
    @JsonProperty("max_user_count")
    private Long maxUserCount;
    @JsonProperty("owner_tag")
    private Long ownerTag;
    @JsonProperty("owner_display_name")
    private String ownerDisplayName;

    public Long getTag() {
        return tag;
    }

    public void setTag(Long tag) {
        this.tag = tag;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
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

    public Long getCurrentUserCount() {
        return currentUserCount;
    }

    public void setCurrentUserCount(Long currentUserCount) {
        this.currentUserCount = currentUserCount;
    }

    public Long getMaxUserCount() {
        return maxUserCount;
    }

    public void setMaxUserCount(Long maxUserCount) {
        this.maxUserCount = maxUserCount;
    }

    public Long getOwnerTag() {
        return ownerTag;
    }

    public void setOwnerTag(Long ownerTag) {
        this.ownerTag = ownerTag;
    }

    public String getOwnerDisplayName() {
        return ownerDisplayName;
    }

    public void setOwnerDisplayName(String ownerDisplayName) {
        this.ownerDisplayName = ownerDisplayName;
    }

}

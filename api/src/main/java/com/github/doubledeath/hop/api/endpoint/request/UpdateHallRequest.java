package com.github.doubledeath.hop.api.endpoint.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by doubledeath on 2/18/17.
 */
public class UpdateHallRequest {

    @NotNull
    @Pattern(regexp = "^(public|private)$")
    private String visibility = "private";
    @NotNull
    private String displayName;
    @NotNull
    @Min(1L)
    private Long size = 1L;
    @NotNull
    private String description;

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

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

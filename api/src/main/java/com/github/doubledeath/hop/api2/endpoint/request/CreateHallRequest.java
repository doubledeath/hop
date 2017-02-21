package com.github.doubledeath.hop.api2.endpoint.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by doubledeath on 2/18/17.
 */
public class CreateHallRequest {

    @NotNull
    @Pattern(regexp = "^(public|private)$")
    private String visibility;
    @NotNull
    @Size(min = 4)
    private String displayName;
    @NotNull
    @Min(1L)
    private Long size = 1L;

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

}

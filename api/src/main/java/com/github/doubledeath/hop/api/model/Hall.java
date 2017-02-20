package com.github.doubledeath.hop.api.model;

import com.github.doubledeath.hop.api.database.entity.HallEntity;

import java.util.List;

/**
 * Created by doubledeath on 2/17/17.
 */
public class Hall {

    public enum Visibility {
        PRIVATE, PUBLIC
    }

    private Long tag;
    private Long owner;
    private Visibility visibility;
    private String displayName;
    private String description;
    private Long size;
    private List<Long> userList;
    private List<Long> bannedUserList;

    public Long getTag() {
        return tag;
    }

    public void setTag(Long tag) {
        this.tag = tag;
    }

    public Long getOwner() {
        return owner;
    }

    public void setOwner(Long owner) {
        this.owner = owner;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
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

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public List<Long> getUserList() {
        return userList;
    }

    public void setUserList(List<Long> userList) {
        this.userList = userList;
    }

    public List<Long> getBannedUserList() {
        return bannedUserList;
    }

    public void setBannedUserList(List<Long> bannedUserList) {
        this.bannedUserList = bannedUserList;
    }

}

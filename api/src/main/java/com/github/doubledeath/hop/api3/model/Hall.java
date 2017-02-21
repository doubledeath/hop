package com.github.doubledeath.hop.api3.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by doubledeath on 2/21/17.
 */
public class Hall {

    private Tag tag;
    private User owner;
    private Long size;
    private String displayName;
    private Key key;
    private String description;
    private List<User> userList = new ArrayList<>();
    private List<User> userBanlist = new ArrayList<>();

    public Hall(@NotNull Tag tag, @NotNull User owner, @NotNull Long size, @NotNull String displayName) {
        this.tag = tag;
        this.owner = owner;
        this.size = size;
        this.displayName = displayName;
    }

    @NotNull
    public Tag getTag() {
        return tag;
    }

    @NotNull
    public User getOwner() {
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

    @NotNull
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(@NotNull List<User> userList) {
        this.userList = userList;
    }

    @NotNull
    public List<User> getUserBanlist() {
        return userBanlist;
    }

    public void setUserBanlist(@NotNull List<User> userBanlist) {
        this.userBanlist = userBanlist;
    }

}

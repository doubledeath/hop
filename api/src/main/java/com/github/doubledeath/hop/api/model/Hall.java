package com.github.doubledeath.hop.api.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by doubledeath on 2/27/17.
 */
public class Hall {

    private Long tag;
    private Long owner;
    private Long size;
    private String displayName;
    private String key;
    private String description;
    private List<Long> userList = new ArrayList<>();
    private List<Long> userBanlist = new ArrayList<>();

    public Hall(@NotNull Long tag, @NotNull Long owner, @NotNull Long size, @NotNull String displayName) {
        this.tag = tag;
        this.owner = owner;
        this.size = size;
        this.displayName = displayName;
    }

    @NotNull
    public Long getTag() {
        return tag;
    }

    @NotNull
    public Long getOwner() {
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
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
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
    public List<Long> getUserList() {
        return userList;
    }

    public void setUserList(@NotNull List<Long> userList) {
        this.userList = userList;
    }

    @NotNull
    public List<Long> getUserBanlist() {
        return userBanlist;
    }

    public void setUserBanlist(@NotNull List<Long> userBanlist) {
        this.userBanlist = userBanlist;
    }

    @Override
    public boolean equals(Object object) {
        return this == object || object instanceof Hall && tag.equals(((Hall) object).tag);
    }

}

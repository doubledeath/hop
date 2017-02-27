package com.github.doubledeath.hop.api3.database.entity;

import com.github.doubledeath.hop.api3.database.entity.converter.ListLongToJsonbConverter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by doubledeath on 2/22/17.
 */
@Entity
public class HallEntity implements Serializable {

    private static final long serialVersionUID = -5546544266724763540L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private Long tag;
    @Column(nullable = false)
    private Long owner;
    @Column(nullable = false)
    private Long size;
    @Column(columnDefinition = "text", nullable = false)
    private String displayName;
    @Column(columnDefinition = "text")
    private String key;
    @Column(columnDefinition = "text")
    private String description;
    @Column(columnDefinition = "jsonb default '[]'", nullable = false)
    @Convert(converter = ListLongToJsonbConverter.class)
    private List<Long> userList;
    @Column(columnDefinition = "jsonb default '[]'", nullable = false)
    @Convert(converter = ListLongToJsonbConverter.class)
    private List<Long> userBanlist;

    @NotNull
    public Long getId() {
        return id;
    }

    public void setId(@NotNull Long id) {
        this.id = id;
    }

    @NotNull
    public Long getTag() {
        return tag;
    }

    public void setTag(@NotNull Long tag) {
        this.tag = tag;
    }

    @NotNull
    public Long getOwner() {
        return owner;
    }

    public void setOwner(@NotNull Long owner) {
        this.owner = owner;
    }

    @NotNull
    public Long getSize() {
        return size;
    }

    public void setSize(@NotNull Long size) {
        this.size = size;
    }

    @NotNull
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(@NotNull String displayName) {
        this.displayName = displayName;
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

}

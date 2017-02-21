package com.github.doubledeath.hop.api2.database.entity;

import com.github.doubledeath.hop.api2.database.entity.converter.ListLongToJsonbConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by doubledeath on 2/20/17.
 */
public class HallEntity implements Serializable {

    private static final long serialVersionUID = 762000276544377469L;

    public enum Visibility {
        PRIVATE, PUBLIC
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private Long tag;
    private Long owner;
    @Enumerated(EnumType.STRING)
    private Visibility visibility;
    @Column(columnDefinition = "text")
    private String displayName;
    private String description;
    private Long size;
    @Column(columnDefinition = "jsonb")
    @Convert(converter = ListLongToJsonbConverter.class)
    private List<Long> userList;
    @Column(columnDefinition = "jsonb")
    @Convert(converter = ListLongToJsonbConverter.class)
    private List<Long> userBanList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<Long> getUserBanList() {
        return userBanList;
    }

    public void setUserBanList(List<Long> userBanList) {
        this.userBanList = userBanList;
    }

}

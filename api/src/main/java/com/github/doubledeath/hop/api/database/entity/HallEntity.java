package com.github.doubledeath.hop.api.database.entity;

import com.github.doubledeath.hop.api.database.entity.converter.HallUserListConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by doubledeath on 2/17/17.
 */
@Entity
public class HallEntity implements Serializable {

    private static final long serialVersionUID = -527771479450577294L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "text")
    private String displayName;
    private Long tag;
    private Long owner;
    @Column(columnDefinition = "jsonb")
    @Convert(converter = HallUserListConverter.class)
    private List<Long> userList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
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

    public List<Long> getUserList() {
        return userList;
    }

    public void setUserList(List<Long> userList) {
        this.userList = userList;
    }

}

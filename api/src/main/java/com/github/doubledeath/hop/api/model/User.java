package com.github.doubledeath.hop.api.model;

import java.io.Serializable;

/**
 * Created by doubledeath on 2/28/17.
 */
public class User implements Serializable {

    private static final long serialVersionUID = 5419527569248262260L;

    private String id;
    private Long tag;
    private String login;
    private String displayName;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getTag() {
        return tag;
    }

    public void setTag(Long tag) {
        this.tag = tag;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    @Override
    public boolean equals(Object object) {
        return this == object || object instanceof User && id.equals(((User) object).id);
    }

}

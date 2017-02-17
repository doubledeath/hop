package com.github.doubledeath.hop.api.model;

/**
 * Created by doubledeath on 2/16/17.
 */
public class User {

    public enum Role {
        GAME_MASTER, PLAYER, GUEST
    }

    public enum Attribute {
        DISPLAY_NAME, DESCRIPTION
    }

    private String id;
    private String login;
    private Long simpleTag;
    private String complexTag;
    private Role role;
    //attributes
    private String displayName;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getSimpleTag() {
        return simpleTag;
    }

    public void setSimpleTag(Long simpleTag) {
        this.simpleTag = simpleTag;
    }

    public String getComplexTag() {
        return complexTag;
    }

    public void setComplexTag(String complexTag) {
        this.complexTag = complexTag;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

}

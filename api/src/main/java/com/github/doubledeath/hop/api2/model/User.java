package com.github.doubledeath.hop.api2.model;

/**
 * Created by doubledeath on 2/20/17.
 */
public class User {

    public enum Role {
        GAME_MASTER, PLAYER, GUEST
    }

    private String id;
    private String login;
    private SimpleTag simpleTag;
    private ComplexTag complexTag;
    private Role role;
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

    public SimpleTag getSimpleTag() {
        return simpleTag;
    }

    public void setSimpleTag(SimpleTag simpleTag) {
        this.simpleTag = simpleTag;
    }

    public ComplexTag getComplexTag() {
        return complexTag;
    }

    public void setComplexTag(ComplexTag complexTag) {
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

package com.github.doubledeath.hop.api.model;

import com.github.doubledeath.hop.api.model.converter.ListToJsonbConverter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by doubledeath on 3/5/17.
 */
@Entity
public class Hall implements Serializable {

    private static final long serialVersionUID = 4654465950886206387L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, columnDefinition = "text")
    private String tag;
    @Column(nullable = false, columnDefinition = "text")
    private String owner;
    @Column(columnDefinition = "text")
    private String description;
    @Convert(converter = ListToJsonbConverter.class)
    @Column(nullable = false, columnDefinition = "jsonb default '[]'")
    private List<String> pendingUserList;
    @Convert(converter = ListToJsonbConverter.class)
    @Column(nullable = false, columnDefinition = "jsonb default '[]'")
    private List<String> enteredUserList;

    @NotNull
    public Long getId() {
        return id;
    }

    public void setId(@NotNull Long id) {
        this.id = id;
    }

    @NotNull
    public String getTag() {
        return tag;
    }

    public void setTag(@NotNull String tag) {
        this.tag = tag;
    }

    @NotNull
    public String getOwner() {
        return owner;
    }

    public void setOwner(@NotNull String owner) {
        this.owner = owner;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull
    public List<String> getPendingUserList() {
        return pendingUserList;
    }

    public void setPendingUserList(@NotNull List<String> pendingUserList) {
        this.pendingUserList = pendingUserList;
    }

    @NotNull
    public List<String> getEnteredUserList() {
        return enteredUserList;
    }

    public void setEnteredUserList(@NotNull List<String> enteredUserList) {
        this.enteredUserList = enteredUserList;
    }

}
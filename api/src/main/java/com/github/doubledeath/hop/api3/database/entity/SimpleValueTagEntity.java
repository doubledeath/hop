package com.github.doubledeath.hop.api3.database.entity;

import org.jetbrains.annotations.NotNull;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by doubledeath on 2/22/17.
 */
public class SimpleValueTagEntity implements Serializable {

    private static final long serialVersionUID = -6245920770509234905L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long value;

    @NotNull
    public Long getId() {
        return id;
    }

    public void setId(@NotNull Long id) {
        this.id = id;
    }

    @NotNull
    public Long getValue() {
        return value;
    }

    public void setValue(@NotNull Long value) {
        this.value = value;
    }

}

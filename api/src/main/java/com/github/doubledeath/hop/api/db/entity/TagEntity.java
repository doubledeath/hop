package com.github.doubledeath.hop.api.db.entity;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by doubledeath on 2/27/17.
 */
@Entity
public class TagEntity implements Serializable {

    private static final long serialVersionUID = -3294059528481589810L;

    @Id
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

    @Override
    public boolean equals(Object object) {
        return this == object || object instanceof TagEntity && id.equals(((TagEntity) object).id);
    }

}

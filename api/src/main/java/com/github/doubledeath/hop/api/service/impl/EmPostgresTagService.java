package com.github.doubledeath.hop.api.service.impl;

import com.github.doubledeath.hop.api.service.TagService;
import org.jetbrains.annotations.NotNull;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by doubledeath on 2/28/17.
 */
@SuppressWarnings("unused")
@Stateless
public class EmPostgresTagService implements TagService {

    private static final Long ID = 1L;
    private static final Long INIT_VALUE = 1L;
    private static final Long STEP_VALUE = 1L;

    @PersistenceContext
    private EntityManager entityManager;

    @NotNull
    @Override
    public Long generate() {
        String[] columnArray = {
                "id",
                "value"
        };
        Object[] valueArray = {
                ID,
                INIT_VALUE
        };
        String columns = Arrays.stream(columnArray)
                .collect(Collectors.joining(", "));
        String values = Arrays.stream(valueArray)
                .map(String::valueOf)
                .collect(Collectors.joining(", "));

        entityManager.createNativeQuery(
                "insert into tag (" + columns + ") " +
                        "values (" + values + ") " +
                        "on conflict (id) do update set value = tag.value + " + STEP_VALUE + ";"
        ).executeUpdate();

        return entityManager.find(Tag.class, ID).getValue();
    }

    @Entity
    @Table(name = "Tag")
    static class Tag implements Serializable {

        private static final long serialVersionUID = -3294059528481589810L;

        @Id
        private Long id;
        @Column(nullable = false)
        private Long value;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getValue() {
            return value;
        }

        public void setValue(Long value) {
            this.value = value;
        }

    }

}

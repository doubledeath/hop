package com.github.doubledeath.hop.api.service.impl;

import com.github.doubledeath.hop.api.service.TagService;
import org.jetbrains.annotations.NotNull;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Random;

/**
 * Generate 5+ digit number unique random tag.
 * Tags can be deleted, when they are not needed, so they can be generated again.
 * More then 5 digits only when all 5 digit values are taken,
 * algorithm is just add another random 5 digit value until unique value.
 */
@SuppressWarnings("unused")
@Stateless
public class DefaultTagService implements TagService {

    private static final Long MIN_VALUE = 10000L;
    private static final Long MAX_VALUE = 100000L;

    @PersistenceContext
    private EntityManager entityManager;
    private Random random = new Random();

    @TransactionAttribute
    @NotNull
    @Override
    public String generate() {
        Tag tag;
        Long value = 0L;

        do {
            value += random.longs(1, MIN_VALUE, MAX_VALUE).sum();

            try {
                tag = (Tag) entityManager.createNativeQuery(
                        "select * from tag where value = " + value
                ).getSingleResult();
            } catch (NoResultException exception) {
                tag = null;
            }
        } while (tag != null);

        entityManager.createNativeQuery(
                "insert into tag (value) values (" + value + ");"
        ).executeUpdate();

        return String.valueOf(value);
    }

    @Entity
    @Table(name = "Tag")
    static class Tag implements Serializable {

        private static final long serialVersionUID = -501795484068445961L;

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(nullable = false, unique = true)
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

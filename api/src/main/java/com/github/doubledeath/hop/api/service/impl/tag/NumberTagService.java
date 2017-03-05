package com.github.doubledeath.hop.api.service.impl.tag;

import com.github.doubledeath.hop.api.service.TagService;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Random;
import java.util.stream.LongStream;

/**
 * Generate N+ digit number unique random tag.<br/>
 * ---(2 <= N <= 7)---<br/>
 * ---(10 <= generated < {@link Long#MAX_VALUE})---<br/>
 * Tags can be deleted, so they can be generated again.
 */
@SuppressWarnings("unused")
abstract class NumberTagService implements TagService {

    @PersistenceContext
    private EntityManager entityManager;
    private Random random = new Random();
    private Long min = 1L;
    private Long max = 1L;

    NumberTagService(@NotNull Long n) {
        if (n < 2 || n > 7) {
            throw new IllegalArgumentException();
        }

        LongStream
                .range(0, n - 1)
                .forEach(value -> min *= 10);

        max = min * 10;
    }

    @NotNull
    @Override
    public String create() {
        Object tag;
        Long value = 0L;

        do {
            value += random.longs(1, min, max).sum();

            try {
                tag = entityManager
                        .createNativeQuery(
                                "select * from Tag where value = " + value)
                        .getSingleResult();
            } catch (NoResultException exception) {
                tag = null;
            }
        } while (tag != null);

        entityManager
                .createNativeQuery(
                        "insert into Tag (value) values (" + value + ")")
                .executeUpdate();

        return String.valueOf(value);
    }

    @Override
    public void delete(@NotNull String tag) {
        entityManager
                .createNativeQuery(
                        "delete from Tag where value = " + tag)
                .executeUpdate();
    }

    @Entity
    @Table(name = "Tag")
    private static final class Tag implements Serializable {

        private static final long serialVersionUID = 4141525733107721413L;

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

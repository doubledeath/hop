package com.github.doubledeath.hop.api3.model.mapper;

import com.github.doubledeath.hop.api3.common.Mapper;
import com.github.doubledeath.hop.api3.database.entity.HallEntity;
import com.github.doubledeath.hop.api3.model.Hall;
import com.github.doubledeath.hop.api3.model.Key;
import com.github.doubledeath.hop.api3.model.User;
import com.github.doubledeath.hop.api3.model.builder.TagBuilder;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

/**
 * Created by doubledeath on 2/23/17.
 */
@SuppressWarnings("unused")
@Dependent
public class HallToEntityMapper extends Mapper<Hall, HallEntity> {

    @Inject
    private Mapper<User, Long> mapper;
    private TagBuilder tagBuilder = new TagBuilder();

    @NotNull
    @Override
    protected Hall fromImpl(@NotNull HallEntity from) {
        Hall hall = new Hall(
                tagBuilder.build(from.getTag(), null),
                mapper.from(from.getOwner()),
                from.getSize(),
                from.getDisplayName()
        );

        if (from.getKey() != null) {
            hall.setKey(new Key(from.getKey()));
        }

        hall.setDescription(from.getDescription());
        hall.setUserList(mapper.fromList(from.getUserList()));
        hall.setUserBanlist(mapper.fromList(from.getUserBanlist()));

        return hall;
    }

    @NotNull
    @Override
    protected HallEntity toImpl(@NotNull Hall to) {
        HallEntity hallEntity = new HallEntity();

        hallEntity.setTag(to.getTag().getSimpleValue());
        hallEntity.setOwner(mapper.to(to.getOwner()));
        hallEntity.setSize(to.getSize());
        hallEntity.setDisplayName(to.getDisplayName());

        if (to.getKey() != null) {
            hallEntity.setKey(to.getKey().getValue());
        }

        hallEntity.setDescription(to.getDescription());
        hallEntity.setUserList(mapper.toList(to.getUserList()));
        hallEntity.setUserBanlist(mapper.toList(to.getUserBanlist()));

        return hallEntity;
    }

}

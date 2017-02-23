package com.github.doubledeath.hop.api3.service.impl;

import com.github.doubledeath.hop.api3.model.Hall;
import com.github.doubledeath.hop.api3.model.Tag;
import com.github.doubledeath.hop.api3.service.HallService;
import com.github.doubledeath.hop.api3.service.request.HallCreateRequest;
import com.github.doubledeath.hop.api3.service.request.HallUpdateRequest;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;

/**
 * Created by doubledeath on 2/22/17.
 */
@SuppressWarnings("unused")
@Dependent
public class RepoHallService implements HallService {

    @NotNull
    @Override
    public Hall create(@NotNull HallCreateRequest hallCreateRequest) {
        return null;
    }

    @NotNull
    @Override
    public Hall find(@NotNull Tag tag) {
        return null;
    }

    @NotNull
    @Override
    public Hall update(@NotNull Tag hallTag, @NotNull HallUpdateRequest hallUpdateRequest) {
        return null;
    }

    @Override
    public void delete(@NotNull Tag hallTag) {

    }

    @Override
    public void enter(@NotNull Tag hallTag, @NotNull Tag userTag) {

    }

    @Override
    public void leave(@NotNull Tag hallTag, @NotNull Tag userTag) {

    }

    @Override
    public void ban(@NotNull Tag hallTag, @NotNull Tag userTag) {

    }

    @Override
    public void unban(@NotNull Tag hallTag, @NotNull Tag userTag) {

    }

    @Override
    public boolean isOwner(@NotNull Tag hallTag, @NotNull Tag userTag) {
        return false;
    }

}

package com.github.doubledeath.hop.api3.service;

import com.github.doubledeath.hop.api3.model.Hall;
import com.github.doubledeath.hop.api3.model.Tag;
import com.github.doubledeath.hop.api3.service.request.HallCreateRequest;
import com.github.doubledeath.hop.api3.service.request.HallUpdateRequest;
import org.jetbrains.annotations.NotNull;

/**
 * Created by doubledeath on 2/21/17.
 */
public interface HallService {

    Hall create(@NotNull HallCreateRequest hallCreateRequest);

    Hall find(@NotNull Tag tag);

    Hall update(@NotNull Tag hallTag, @NotNull HallUpdateRequest hallUpdateRequest);

    void delete(@NotNull Tag hallTag);

    void enter(@NotNull Tag hallTag, @NotNull Tag userTag);

    void leave(@NotNull Tag hallTag, @NotNull Tag userTag);

    void ban(@NotNull Tag hallTag, @NotNull Tag userTag);

    void unban(@NotNull Tag hallTag, @NotNull Tag userTag);

    boolean isOwner(@NotNull Tag hallTag, @NotNull Tag userTag);

}

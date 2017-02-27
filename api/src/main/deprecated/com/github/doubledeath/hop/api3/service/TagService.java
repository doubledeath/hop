package com.github.doubledeath.hop.api3.service;

import com.github.doubledeath.hop.api3.model.Tag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by doubledeath on 2/21/17.
 */
public interface TagService {

    @NotNull
    Tag create(@Nullable String prefix);

}

package com.github.doubledeath.hop.api3.database.repo;

import com.github.doubledeath.hop.api3.database.entity.SimpleValueTagEntity;
import org.jetbrains.annotations.NotNull;

/**
 * Created by doubledeath on 2/22/17.
 */
public interface SimpleValueTagRepo {

    @NotNull
    SimpleValueTagEntity update();

}

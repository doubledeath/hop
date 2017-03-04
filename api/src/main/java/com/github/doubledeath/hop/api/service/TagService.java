package com.github.doubledeath.hop.api.service;

import org.jetbrains.annotations.NotNull;

import javax.ejb.Local;

/**
 * Created by doubledeath on 3/3/17.
 */
@Local
public interface TagService {

    @NotNull
    String generate();

}

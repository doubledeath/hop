package com.github.doubledeath.hop.api.service;

import org.jetbrains.annotations.NotNull;

import javax.ejb.Local;

/**
 * Created by doubledeath on 2/28/17.
 */
@Local
public interface TagService {

    @NotNull
    Long generate();

}

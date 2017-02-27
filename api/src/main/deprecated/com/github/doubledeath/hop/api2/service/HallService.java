package com.github.doubledeath.hop.api2.service;

import com.github.doubledeath.hop.api2.model.Hall;
import com.github.doubledeath.hop.api2.model.User;

/**
 * Created by doubledeath on 2/20/17.
 */
public interface HallService {

    Hall findOneByTagValue(Long tag);

    Hall create(Long owner, Hall.Visibility visibility, String displayName, Long size);

    Hall update(Hall hall);

    void delete(Hall hall);

    void enter(Hall hall, User user);

    void leave(Hall hall, User user);

    void ban(Hall hall, User user);

    void unban(Hall hall, User user);

    boolean isUserOwner(Hall hall, User user);

}

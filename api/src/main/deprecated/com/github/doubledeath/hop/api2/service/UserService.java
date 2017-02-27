package com.github.doubledeath.hop.api2.service;

import com.github.doubledeath.hop.api2.model.SimpleTag;
import com.github.doubledeath.hop.api2.model.User;

/**
 * Created by doubledeath on 2/20/17.
 */
public interface UserService {

    User findOneBySimpleTag(SimpleTag simpleTag);

    User signUp(String login, String password);

    User updateInfo(User user);

}

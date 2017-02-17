package com.github.doubledeath.hop.api.service;

import com.github.doubledeath.hop.api.model.User;

/**
 * Created by doubledeath on 2/16/17.
 */
public interface UserService {

    enum SignUpResult {
        SUCCESS, USER_EXISTS_ERROR, UNKNOWN_ERROR
    }

    SignUpResult signUp(String login, String password);

    User findBySimpleTag(Long simpleTag);

    User updateInfo(User user);

}

package com.github.doubledeath.hop.api3.model.builder;

import com.github.doubledeath.hop.api3.model.Key;
import org.jetbrains.annotations.NotNull;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by doubledeath on 2/21/17.
 */
public class KeyBuilder {

    private static final String MESSAGE_DIGEST_ALGORITHM = "SHA-1";

    public Key build(@NotNull String seed) {
        Key key = new Key();

        try {
            MessageDigest messageDigest = MessageDigest.getInstance(MESSAGE_DIGEST_ALGORITHM);

            messageDigest.update(seed.getBytes());

            key.setValue(new String(messageDigest.digest()));

            return key;
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            throw new RuntimeException(noSuchAlgorithmException);
        }
    }

}

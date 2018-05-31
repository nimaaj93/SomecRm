package com.farafarin.mata.common.util;

/**
 * Created by K550 VX on 12/4/2017.
 */
public abstract class AuthorizationCodeUtil {

    public static String generateRandomAuthorizationCode(int length) {
        if (length < 1) {
            throw new IllegalArgumentException();
        }

        String code = "";

        for (int i=0 ; i < length ; i++) {
            String digit = String.valueOf ((int)(Math.random() * 10));
            code += digit;
        }

        return code;
    }
}

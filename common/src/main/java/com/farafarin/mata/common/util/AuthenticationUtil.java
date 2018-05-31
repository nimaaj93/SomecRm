package com.farafarin.mata.common.util;

import com.farafarin.mata.common.security.model.UserProfile;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by Asus on 11/12/2017.
 */
public class AuthenticationUtil {

    public static String hashPassword(String password) {
        try {
                java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
                byte[] array = md.digest(password.getBytes());
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < array.length; ++i) {
                    sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
                }
                return sb.toString();
            } catch (java.security.NoSuchAlgorithmException e) {

        }

        return null;

    }

    public static UserProfile getCurrentUser(){
        UserProfile userProfile = (UserProfile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userProfile;
    }

}

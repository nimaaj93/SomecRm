package com.farafarin.mata.common.security.util;

import com.farafarin.mata.common.constant.UserStatus;
import com.farafarin.mata.common.security.model.UserProfile;
import com.farafarin.mata.common.util.DateUtil;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by K550 VX on 11/13/2017.
 */
public class CustomTokenEnhancer extends JwtAccessTokenConverter {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken,
                                     OAuth2Authentication oAuth2Authentication) {

        Object authObj = oAuth2Authentication.getUserAuthentication().getPrincipal();

        if (authObj instanceof UserProfile) {

            UserProfile userProfile = (UserProfile) oAuth2Authentication.getUserAuthentication().getPrincipal();

            Map<String, Object> additionalInfo = new HashMap<>();

            additionalInfo.put("userinfo", userProfile.getUserInformation());
            additionalInfo.put("authorities", userProfile.getAuthorities());

            String statusStr = UserStatus.getInstanceFromCode(userProfile.getStatus()).name();
            String expireDateStr = DateUtil.convertGregorianDateToJalaliString(userProfile.getExpireDate());

            additionalInfo.put("status", statusStr);
            additionalInfo.put("expiredate", expireDateStr);

            ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(additionalInfo);
        }

        return super.enhance(oAuth2AccessToken,oAuth2Authentication);
    }
}

package com.farafarin.mata.web.back.service.impl;

import com.farafarin.mata.common.dao.UserAddressDao;
import com.farafarin.mata.common.dao.UserAuthenticationDao;
import com.farafarin.mata.common.dao.UserDao;
import com.farafarin.mata.common.dto.UserPersonalDto;
import com.farafarin.mata.common.entity.UserAddressEntity;
import com.farafarin.mata.common.entity.UserPersonalEntity;
import com.farafarin.mata.common.security.model.UserProfile;
import com.farafarin.mata.common.util.AuthenticationUtil;
import com.farafarin.mata.web.back.dto.UserContactInformationDto;
import com.farafarin.mata.web.back.service.UserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Asus on 11/25/2017.
 */
@Component
public class UserInformationServiceImpl implements UserInformationService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserAddressDao userAddressDao;
    @Autowired
    private UserAuthenticationDao userAuthenticationDao;

    @Override
    public UserPersonalDto getUserPersonalInformation(String nationalCode) {
        return userDao.retrieveUserDtoByNationalCode(nationalCode);
    }

    @Override
    public UserContactInformationDto getUserContactInformation() {
        UserProfile currentUser = AuthenticationUtil.getCurrentUser();
        UserPersonalEntity userPersonalEntity = userDao.retrieveUserByNationalCode(currentUser.getNationalCode());
        List<UserAddressEntity> userAddressList = userAddressDao.retrieveUserAddresses(currentUser.getUserId());
        UserContactInformationDto userContactInformationDto =
                prepareUserContactInformation(userPersonalEntity,userAddressList);
        return userContactInformationDto;
    }

    @Override
    public void resetPassword(String newPass) {
        Long userId = AuthenticationUtil.getCurrentUser().getUserId();

        if (userId == null) {
            throw new IllegalStateException("User authentication not available for reset password.");
        }

        String hashedPassword = AuthenticationUtil.hashPassword(newPass);

        userAuthenticationDao.resetPassword(userId,hashedPassword);
    }

    private UserContactInformationDto prepareUserContactInformation(UserPersonalEntity userPersonalEntity,
                                                                    List<UserAddressEntity> userAddressList){
        UserContactInformationDto userContactInformationDto = new UserContactInformationDto();
        userContactInformationDto.setPhoneNumber(userPersonalEntity.getPhoneNumber());
        userContactInformationDto.setEmail(userPersonalEntity.getEmail());
        userContactInformationDto.setUserAddressEntity(userAddressList);
        return userContactInformationDto;

    }
}

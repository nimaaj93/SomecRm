package com.farafarin.mata.web.back.service;

import com.farafarin.mata.common.dto.UserPersonalDto;
import com.farafarin.mata.common.entity.UserAddressEntity;
import com.farafarin.mata.common.entity.UserPersonalEntity;
import com.farafarin.mata.web.back.dto.UserContactInformationDto;

import java.util.List;

/**
 * Created by Asus on 11/25/2017.
 */
public interface UserInformationService {
    UserPersonalDto getUserPersonalInformation(String nationalCode);
    UserContactInformationDto getUserContactInformation();
    void resetPassword(String newPass);
}

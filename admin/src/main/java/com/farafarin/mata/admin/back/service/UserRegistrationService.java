package com.farafarin.mata.admin.back.service;

import com.farafarin.mata.admin.back.model.SignUpModel;
import com.farafarin.mata.admin.back.model.UserPersonalModel;

/**
 * Created by Asus on 11/2/2017.
 */
public interface UserRegistrationService {
    void insertUser(SignUpModel signUpModel);
    UserPersonalModel getUserByNationalCode(String nationalCode);
}

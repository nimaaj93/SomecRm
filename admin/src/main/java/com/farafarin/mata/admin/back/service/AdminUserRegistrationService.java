package com.farafarin.mata.admin.back.service;

import com.farafarin.mata.admin.back.model.AdminSignUpModel;
import com.farafarin.mata.admin.back.model.UserPersonalModel;

/**
 * Created by Asus on 11/11/2017.
 */
public interface AdminUserRegistrationService {
    void insertAdminUser(AdminSignUpModel adminSignUpModel);
    UserPersonalModel getAdminUserByNationalCode(String nationalCode);
}

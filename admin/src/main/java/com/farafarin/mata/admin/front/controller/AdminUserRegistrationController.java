package com.farafarin.mata.admin.front.controller;

import com.farafarin.mata.admin.back.model.AdminSignUpModel;
import com.farafarin.mata.admin.back.model.SignUpModel;
import com.farafarin.mata.admin.back.model.UserPersonalModel;
import com.farafarin.mata.admin.back.service.AdminUserRegistrationService;
import com.farafarin.mata.admin.back.service.UserRegistrationService;
import com.farafarin.mata.admin.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by Asus on 11/11/2017.
 */
@RestController
@RequestMapping("s/adminuser")
public class AdminUserRegistrationController {

    @Autowired
    private AdminUserRegistrationService adminUserRegistrationService;

    @RequestMapping(value = "/adminsignup", method = RequestMethod.POST)
    public void doSignUp(@Valid @RequestBody AdminSignUpModel adminSignUpModel) {
        adminUserRegistrationService.insertAdminUser(adminSignUpModel);
    }

    @RequestMapping(value = "/userinfo",method = RequestMethod.GET)
    public UserPersonalModel getUserInfoByNationalCode(@NotNull @RequestParam("nationalCode") String nationalCode) {
        UserPersonalModel userPersonalModel = adminUserRegistrationService.getAdminUserByNationalCode(nationalCode);
        return userPersonalModel;
    }
}

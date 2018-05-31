package com.farafarin.mata.admin.front.controller;

import com.farafarin.mata.admin.back.model.SignUpModel;
import com.farafarin.mata.admin.back.model.UserPersonalModel;
import com.farafarin.mata.admin.back.service.UserRegistrationService;
import com.farafarin.mata.admin.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by Asus on 11/1/2017.
 */

@RestController
@RequestMapping("s/user")
public class UserRegistrationController {


    @Autowired
    private UserRegistrationService userRegistrationService;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public void doSignUp(@RequestBody @Valid SignUpModel signUpModel) {
        userRegistrationService.insertUser(signUpModel);
    }

    @RequestMapping(value = "/userinfo",method = RequestMethod.GET)
    public UserPersonalModel getUserInfoByNationalCode(@NotNull @RequestParam("nationalCode") String nationalCode) {
        UserPersonalModel userPersonalModel = userRegistrationService.getUserByNationalCode(nationalCode);
        return userPersonalModel;
    }

}

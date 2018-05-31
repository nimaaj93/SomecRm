package com.farafarin.mata.admin.front.controller;

import com.farafarin.mata.admin.back.model.SignUpModel;
import com.farafarin.mata.admin.back.model.UserPersonalModel;
import com.farafarin.mata.admin.back.service.UserManagementService;
import com.farafarin.mata.admin.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by Asus on 11/6/2017.
 */
@RestController
@RequestMapping("s/useredit")
public class UserEditController {

    @Autowired
    private UserManagementService userManagementService;

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String editUserPersonalInfo(@RequestBody @Valid UserPersonalModel userPersonalModel) {
        userManagementService.editUserPersonalInfo(userPersonalModel);
        return "okay";
    }
}

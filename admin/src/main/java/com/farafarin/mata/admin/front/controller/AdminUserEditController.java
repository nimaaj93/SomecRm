package com.farafarin.mata.admin.front.controller;

import com.farafarin.mata.admin.back.model.UserPersonalModel;
import com.farafarin.mata.admin.back.service.AdminUserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by Asus on 11/12/2017.
 */
@RestController
@RequestMapping("s/adminuseredit")
public class AdminUserEditController {

    @Autowired
    private AdminUserManagementService adminUserManagementService;

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void editUserPersonalInfo(@RequestBody @Valid UserPersonalModel userPersonalModel) {
        adminUserManagementService.editUserPersonalInfo(userPersonalModel);
    }
}

package com.farafarin.mata.admin.front.controller;

import com.farafarin.mata.common.dto.UserPersonalDto;
import com.farafarin.mata.admin.back.service.AdminUserManagementService;
import com.farafarin.mata.common.searchobject.UserSO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Asus on 11/12/2017.
 */
@RestController
@RequestMapping("s/adminusermangement")
public class AdminUserManagementController {

    @Autowired
    private AdminUserManagementService adminUserManagementService;

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public Long getUserCounts(@RequestParam( value = "nationalCode", required = false) String nationalCode,
                              @RequestParam (value = "phoneNumber", required = false) String  phoneNumber,
                              @RequestParam(value = "firstNameFa",required = false) String firstNameFa,
                              @RequestParam(value = "lastNameFa",required = false) String lastNameFa){
        UserSO userSO = new UserSO();
        userSO.setNationalCode(nationalCode);
        userSO.setPhoneNumber(phoneNumber);
        userSO.setFirstNameFa(firstNameFa);
        userSO.setLastNameFa(lastNameFa);
        return adminUserManagementService.getAdminUserCount(userSO);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<UserPersonalDto> retrieveUsers(@RequestParam("page") @NotNull Integer pageNumber,
                                               @RequestParam("size") @NotNull Integer pageSize,
                                               @RequestParam( value = "nationalCode", required = false) String nationalCode,
                                               @RequestParam (value = "phoneNumber", required = false) String  phoneNumber,
                                               @RequestParam(value = "firstNameFa",required = false) String firstNameFa,
                                               @RequestParam(value = "lastNameFa",required = false) String lastNameFa ) {
        UserSO userSO = prepareUserSearchObject(nationalCode,phoneNumber,pageNumber,pageSize,firstNameFa,lastNameFa);
        return adminUserManagementService.retrieveAdminUsers(userSO);
    }

    @RequestMapping(value = "/disable",method = RequestMethod.POST)
    public void disableUser(@NotNull @RequestParam String nationalCode){
        adminUserManagementService.disableUser(nationalCode);
    }

    @RequestMapping(value = "/enable",method = RequestMethod.POST)
    public void enableUser(@NotNull @RequestParam String nationalCode){
        adminUserManagementService.enableUser(nationalCode);
    }

    @RequestMapping(value = "/resetpassword",method = RequestMethod.POST)
    public void resetPassword(@NotNull @RequestParam String newPassword,
                              @NotNull @RequestParam String confirmPassword,@NotNull @RequestParam String nationalCode){
        adminUserManagementService.resetAdminUserPassword(newPassword,confirmPassword,nationalCode);
    }

    private UserSO prepareUserSearchObject(String nationalCode,String phoneNumber,Integer pageNumber,
                                           Integer pageSize,String firstNameFa,String lastNameFa){
        UserSO userSO = new UserSO();
        userSO.setNationalCode(nationalCode);
        userSO.setPhoneNumber(phoneNumber);
        userSO.setPageNumber(pageNumber);
        userSO.setPageSize(pageSize);
        userSO.setFirstNameFa(firstNameFa);
        userSO.setLastNameFa(lastNameFa);
        return userSO;
    }
}

package com.farafarin.mata.admin.front.controller;

import com.farafarin.mata.common.dto.UserSignUpRequestDto;
import com.farafarin.mata.common.searchobject.UserSO;
import com.farafarin.mata.admin.back.service.UserSignUpRequestManagementService;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Asus on 11/8/2017.
 */
@RestController
@RequestMapping("/s/signuprequest")
public class UserSignUpRequestManagementController {

    @Autowired
    private UserSignUpRequestManagementService userSignUpRequestManagementService;

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public Long getUserCounts(@RequestParam(value = "phoneNumber", required = false) String phoneNumber,
                              @RequestParam(value = "firstNameFa",required = false) String firstNameFa,
                              @RequestParam(value = "lastNameFa",required = false) String lastNameFa) {
        return userSignUpRequestManagementService.getUserSignUpRequestCount(phoneNumber,firstNameFa,lastNameFa);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<UserSignUpRequestDto> getUserSignUpRequestList(@RequestParam("page") @NotNull Integer pageNumber,
                                                               @RequestParam("size") @NotNull Integer pageSize,
                                                               @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
                                                               @RequestParam(value = "firstNameFa",required = false) String firstNameFa,
                                                               @RequestParam(value = "lastNameFa",required = false) String lastNameFa) {

        UserSO userSO = prepareUserSearchObject(phoneNumber, pageNumber, pageSize,firstNameFa,lastNameFa);
        List<UserSignUpRequestDto> userSignUpRequestList = userSignUpRequestManagementService.getUserSignUpRequestList(userSO);
        return userSignUpRequestList;
    }

    @RequestMapping(value = "/seen", method = RequestMethod.POST)
    public void changeUserSignUpRequestStatusToSeen(@RequestParam("phoneNumber") @NotNull String phoneNumber,
                                              @RequestParam("signUpRequestId") @NotNull Long signUpRequestId){
        userSignUpRequestManagementService.changeUserSignUpRequestToSeen(phoneNumber,signUpRequestId);
    }

    @RequestMapping(value = "/done", method = RequestMethod.POST)
    public void changeUserSignUpRequestStatusToDone(@RequestParam("phoneNumber") @NotNull String phoneNumber,
                                                    @RequestParam("signUpRequestId") @NotNull Long signUpRequestId){
        userSignUpRequestManagementService.changeUserSignUpRequestToDone(phoneNumber,signUpRequestId);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void deleteUserSignUpRequest(@RequestParam("phoneNumber") @NotNull String phoneNumber,
                                        @RequestParam("signUpRequestId") @NotNull Long signUpRequestId ){
        userSignUpRequestManagementService.deleteUserSignUpRequest(phoneNumber,signUpRequestId);
    }



    private UserSO prepareUserSearchObject(String phoneNumber
            , Integer pageNumber, Integer pageSize,String firstNameFa,String lastNameFa) {
        UserSO userSO = new UserSO();
        userSO.setPhoneNumber(phoneNumber);
        userSO.setPageNumber(pageNumber);
        userSO.setPageSize(pageSize);
        userSO.setFirstNameFa(firstNameFa);
        userSO.setLastNameFa(lastNameFa);
        return userSO;
    }
}

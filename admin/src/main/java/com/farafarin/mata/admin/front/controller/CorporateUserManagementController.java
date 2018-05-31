package com.farafarin.mata.admin.front.controller;

import com.farafarin.mata.admin.back.model.CorporateUserSignUpModel;
import com.farafarin.mata.admin.back.model.CorporateUserUpdateModel;
import com.farafarin.mata.admin.back.service.CorporateUserSignUpService;
import com.farafarin.mata.common.dto.CorporateUserDto;
import com.farafarin.mata.common.dto.UserPersonalDto;
import com.farafarin.mata.common.entity.CorporateUserEntity;
import com.farafarin.mata.common.entity.UserPersonalEntity;
import com.farafarin.mata.common.searchobject.CorporateUserSO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Asus on 12/20/2017.
 */
@RestController
@RequestMapping("s/corporateuser")
public class CorporateUserManagementController {

    @Autowired
    private CorporateUserSignUpService corporateUserSignUpService;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public CorporateUserEntity doSignUp(@RequestBody @Valid CorporateUserSignUpModel corporateUserSignUpModel){
        CorporateUserEntity corporateUserEntity = corporateUserSignUpService.insertCorporateUser(corporateUserSignUpModel);
        return corporateUserEntity;
    }

    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public CorporateUserDto getCorporateUserDetails(@NotNull @RequestParam("corporateUserId") Long corporateUserId){
        return corporateUserSignUpService.getCorporateUser(corporateUserId);
    }

    @RequestMapping(value = "/activate", method = RequestMethod.POST)
    public void activate(@NotNull @RequestParam("corporateUserId") Long corporateUserId) {
        corporateUserSignUpService.activate(corporateUserId);
    }

    @RequestMapping(value = "/inactivate", method = RequestMethod.POST)
    public void inactivate(@NotNull @RequestParam("corporateUserId") Long corporateUserId) {
        corporateUserSignUpService.inactivate(corporateUserId);
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public Integer countCorporateUsers(CorporateUserSO corporateUserSO) {
        return corporateUserSignUpService.countCorporateUsers(corporateUserSO);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<CorporateUserDto> getCorporateUsersList(@Valid CorporateUserSO corporateUserSO) {
        return corporateUserSignUpService.getCorporateUsersList(corporateUserSO);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void updateCorporateUser(@RequestBody @Valid CorporateUserUpdateModel corporateUserUpdateModel) {
        corporateUserSignUpService.updateCorporateUser(corporateUserUpdateModel);
    }

}

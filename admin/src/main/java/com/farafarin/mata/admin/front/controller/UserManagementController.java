package com.farafarin.mata.admin.front.controller;

import com.farafarin.mata.common.dto.UserPersonalDto;
import com.farafarin.mata.admin.back.service.UserManagementService;
import com.farafarin.mata.common.model.SearchQuery;
import com.farafarin.mata.common.searchobject.UserSO;
import com.farafarin.mata.common.util.Empty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Asus on 11/6/2017.
 */
@RestController
@RequestMapping("s/usermangement")
public class UserManagementController {

    @Autowired
    private UserManagementService userManagementService;

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public Long getUserCounts(@RequestParam( value = "nationalCode", required = false) String nationalCode,
                              @RequestParam (value = "phoneNumber", required = false) String  phoneNumber,
                              @RequestParam(value = "firstNameFa",required = false) String firstNameFa,
                              @RequestParam(value = "lastNameFa",required = false) String lastNameFa,
                              @RequestParam(value = "corporateUserId",required = false) Long corporateUserId){
        UserSO userSO = new UserSO();
        userSO.setNationalCode(nationalCode);
        userSO.setPhoneNumber(phoneNumber);
        userSO.setFirstNameFa(firstNameFa);
        userSO.setLastNameFa(lastNameFa);
        userSO.setCorporateUserId(corporateUserId);
        return userManagementService.getUsersCount(userSO);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<UserPersonalDto> retrieveUsers(@RequestParam("page") @NotNull Integer pageNumber,
                                               @RequestParam("size") @NotNull Integer pageSize,
                                               @RequestParam( value = "nationalCode", required = false) String nationalCode,
                                               @RequestParam (value = "phoneNumber", required = false) String  phoneNumber ,
                                               @RequestParam(value = "firstNameFa",required = false) String firstNameFa,
                                               @RequestParam(value = "lastNameFa",required = false) String lastNameFa,
                                               @RequestParam(value = "corporateUserId",required = false) Long corporateUserId) {
        UserSO userSO = prepareUserSearchObject(nationalCode,phoneNumber,pageNumber,pageSize,firstNameFa,lastNameFa);
        userSO.setCorporateUserId(corporateUserId);
        return userManagementService.retrieveUsers(userSO);
    }

    @RequestMapping(value = "/disable",method = RequestMethod.POST)
    public void disableUser(@RequestParam("nationalCode") String nationalCode){
        userManagementService.disableUser(nationalCode);
    }

    @RequestMapping(value = "/enable",method = RequestMethod.POST)
    public void enableUser(@RequestParam("nationalCode") String nationalCode){
        userManagementService.enableUser(nationalCode);
    }

    private UserSO prepareUserSearchObject(String nationalCode,String phoneNumber,
                                           Integer pageNumber,Integer pageSize,String firstNameFa,String lastNameFa){
        UserSO userSO = new UserSO();
        userSO.setNationalCode(nationalCode);
        userSO.setPhoneNumber(phoneNumber);
        userSO.setPageNumber(pageNumber);
        userSO.setPageSize(pageSize);
        userSO.setFirstNameFa(firstNameFa);
        userSO.setLastNameFa(lastNameFa);
        return userSO;
    }

    @RequestMapping(value = "/search")
    public List<UserPersonalDto> searchUsers(@RequestParam(name = "q",required = false) String query) {
        if (Empty.isNotEmpty(query)) {
            query = query.trim();
        }
        SearchQuery searchQuery = new SearchQuery();
        searchQuery.setQuery(query);
        return userManagementService.searchUsers(searchQuery);
    }

}

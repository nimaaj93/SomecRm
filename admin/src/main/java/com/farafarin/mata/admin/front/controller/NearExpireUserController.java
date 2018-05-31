package com.farafarin.mata.admin.front.controller;

import com.farafarin.mata.common.dto.UserPersonalDto;
import com.farafarin.mata.admin.back.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Asus on 11/13/2017.
 */
@RestController
@RequestMapping(value = "s/userexpire")
public class NearExpireUserController {

    @Autowired
    private UserManagementService userManagementService;

    @RequestMapping(value = "/count", method = RequestMethod.GET)
   public Long getNearExpireUsersCount(){
       return userManagementService.getNearExpireUsersCount();
   }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<UserPersonalDto> getNearExpireMataUser(@RequestParam("page") @NotNull Integer pageNumber,
                                                       @RequestParam("size") @NotNull Integer pageSize){
        return userManagementService.getNearExpireMataUsers(pageNumber,pageSize);
    }
}

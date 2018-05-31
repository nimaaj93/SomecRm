package com.farafarin.mata.web.front.controller;

import com.farafarin.mata.common.dto.UserPersonalDto;
import com.farafarin.mata.common.entity.UserAddressEntity;
import com.farafarin.mata.common.entity.UserPersonalEntity;
import com.farafarin.mata.common.model.UserAddressModel;
import com.farafarin.mata.common.security.model.UserProfile;
import com.farafarin.mata.common.service.NotificationService;
import com.farafarin.mata.common.service.UserAddressService;
import com.farafarin.mata.common.util.AuthenticationUtil;
import com.farafarin.mata.web.back.dto.UserContactInformationDto;
import com.farafarin.mata.web.back.service.UserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Asus on 11/24/2017.
 */
@RestController
@RequestMapping(value = "/s/userinformation")
public class UserInformationController {

    @Autowired
    private UserInformationService userInformationService;
    @Autowired
    private UserAddressService userAddressService;
    @Autowired
    private NotificationService notificationService;

    @RequestMapping(value = "userinformation", method = RequestMethod.GET)
    public UserPersonalDto getUserPersonalInfo(){
        UserProfile currentUser = AuthenticationUtil.getCurrentUser();
        return userInformationService.getUserPersonalInformation(currentUser.getNationalCode());
    }

    @RequestMapping(value = "contactinformation", method = RequestMethod.GET)
    public UserContactInformationDto getUserContactInformation(){
        return userInformationService.getUserContactInformation();
    }

    @RequestMapping(value = "submitaddress", method = RequestMethod.POST)
    public String submitNewAddress(@RequestBody @Valid UserAddressModel userAddressModel){
        UserProfile currentUser = AuthenticationUtil.getCurrentUser();
        userAddressService.insertUserAddress(userAddressModel,currentUser.getUserId());
        return "OK";
    }

    @RequestMapping(value = "deleteaddress", method = RequestMethod.POST)
    public String deleteAddress(@RequestParam("addressId") Long addressId) {
        userAddressService.deleteAddress(addressId);
        return "OK";
    }

    @RequestMapping(value = "/changeinfo", method = RequestMethod.POST)
    public void sendUserInformationChangeNotificationToAdmin(){
        notificationService.sendInformationChnageNotification();
    }


}

package com.farafarin.mata.web.front.controller;

import com.farafarin.mata.web.back.model.UserSignUpRequestModel;
import com.farafarin.mata.web.back.service.UserSignUpRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by Asus on 11/7/2017.
 */
@RestController
@RequestMapping("/ns/signup")
public class UserSignUpRequestController {

    @Autowired
    private UserSignUpRequestService userSignUpRequestService;

    @RequestMapping(value = "/request", method = RequestMethod.POST)
    public void doSignUpRequest(@RequestBody @Valid UserSignUpRequestModel userSignUpRequestModel){
        userSignUpRequestService.submitUserSignUpRequest(userSignUpRequestModel);
    }
}

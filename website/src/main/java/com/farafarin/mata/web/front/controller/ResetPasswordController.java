package com.farafarin.mata.web.front.controller;

import com.farafarin.mata.common.exception.InvalidInputDataException;
import com.farafarin.mata.common.util.Empty;
import com.farafarin.mata.web.back.service.UserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by K550 VX on 1/21/2018.
 */
@RestController
@RequestMapping("/s/resetpass")
public class ResetPasswordController {

    @Autowired
    private UserInformationService userInformationService;

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public ResponseEntity resetPassword(@RequestParam(value = "newPass") String newPass) {
        if (!Empty.isNotEmpty(newPass) || newPass.length() < 6) {
            throw new InvalidInputDataException();
        }
        userInformationService.resetPassword(newPass);
        return new ResponseEntity(HttpStatus.OK);
    }

}

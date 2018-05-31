package com.farafarin.mata.web.front.controller;

import com.farafarin.mata.common.exception.UsernameNotFoundException;
import com.farafarin.mata.common.security.model.UserProfile;
import com.farafarin.mata.web.back.service.UserAuthorizationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by K550 VX on 10/14/2017.
 */
@RestController
@RequestMapping("/ns/login")
public class LoginController {


    @Autowired
    private UserAuthorizationCodeService userAuthorizationCodeService;

    @RequestMapping(value = "/verify",method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public String verifyUsername(@RequestParam("username") @NotNull String username) {
        return userAuthorizationCodeService.createAuthorizationCode(username);
    }

}

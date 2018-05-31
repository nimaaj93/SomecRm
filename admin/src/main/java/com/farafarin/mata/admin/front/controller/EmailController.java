package com.farafarin.mata.admin.front.controller;

import com.farafarin.mata.common.dao.UserDao;
import com.farafarin.mata.common.dto.UserPersonalDto;
import com.farafarin.mata.common.model.EmailModel;
import com.farafarin.mata.common.security.model.UserProfile;
import com.farafarin.mata.common.service.MailService;
import com.farafarin.mata.common.util.AuthenticationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by Asus on 5/22/2018.
 */
@RestController
@RequestMapping("s/email")
public class EmailController {

    @Autowired
    private MailService mailService;
    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public void sendEmail(@RequestBody @Valid EmailModel emailModel) {
        UserProfile currentUser = AuthenticationUtil.getCurrentUser();
        UserPersonalDto user = userDao.retrieveUserDtoByNationalCode(currentUser.getNationalCode());
        mailService.sendEmail(user.getEmail(), emailModel.getReceiver(), emailModel.getSubject(), emailModel.getMessage());
    }
}

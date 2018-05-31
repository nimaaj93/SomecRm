package com.farafarin.mata.admin.front.controller;

import com.farafarin.mata.admin.back.service.AdminBalanceService;
import com.farafarin.mata.common.dto.UserBalanceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by K550 VX on 1/16/2018.
 */
@RestController
@RequestMapping("s/balance")
public class AdminBalanceController {

    @Autowired
    private AdminBalanceService adminBalanceService;

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public UserBalanceDto getBalanceByNationalCode(@RequestParam("nationalCode") String nationalCode) {
        return adminBalanceService.getBalanceByNationalCode(nationalCode);
    }

}

package com.farafarin.mata.web.front.controller;

import com.farafarin.mata.common.dto.UserTransactionDto;
import com.farafarin.mata.common.searchobject.UserTransactionSO;
import com.farafarin.mata.web.back.service.UserTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by K550 VX on 1/16/2018.
 */
@RestController
@RequestMapping("/s/transactions")
public class UserTransactionController {

    @Autowired
    private UserTransactionService userTransactionService;

    @RequestMapping(value = "/count",method = RequestMethod.GET)
    public Long getUserTransactionsCount(UserTransactionSO so) {
        return userTransactionService.countUserTransactions(so);
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<UserTransactionDto> getUserTransactions(@Valid UserTransactionSO so) {
        return userTransactionService.getUserTransactions(so);
    }

}

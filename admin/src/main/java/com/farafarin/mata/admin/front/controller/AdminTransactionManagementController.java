package com.farafarin.mata.admin.front.controller;

import com.farafarin.mata.admin.back.service.AdminTransactionService;
import com.farafarin.mata.common.dto.UserTransactionDto;
import com.farafarin.mata.common.model.AdminUserTransactionModel;
import com.farafarin.mata.common.searchobject.UserTransactionSO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by K550 VX on 1/15/2018.
 */
@RestController
@RequestMapping("s/transactions")
public class AdminTransactionManagementController {

    @Autowired
    private AdminTransactionService adminTransactionService;

    @RequestMapping(value = "/count",method = RequestMethod.GET)
    public Long countTransactions(UserTransactionSO so) {
        return adminTransactionService.countTransactions(so);
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<UserTransactionDto> getTransactions(@Valid UserTransactionSO so) {
        return adminTransactionService.getTransactions(so);
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public void createTransaction(@RequestBody @Valid AdminUserTransactionModel model) {
        adminTransactionService.createTransaction(model);
    }

}

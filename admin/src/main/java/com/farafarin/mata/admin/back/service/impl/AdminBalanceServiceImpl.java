package com.farafarin.mata.admin.back.service.impl;

import com.farafarin.mata.admin.back.service.AdminBalanceService;
import com.farafarin.mata.common.dao.UserCreditDao;
import com.farafarin.mata.common.dto.UserBalanceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by K550 VX on 1/16/2018.
 */
@Component
public class AdminBalanceServiceImpl implements AdminBalanceService {

    @Autowired
    private UserCreditDao userCreditDao;

    @Override
    public UserBalanceDto getBalanceByNationalCode(String nationalCode) {
        return userCreditDao.getUserBalanceByNationalCode(nationalCode);
    }
}

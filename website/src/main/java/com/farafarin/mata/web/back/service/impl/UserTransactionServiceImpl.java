package com.farafarin.mata.web.back.service.impl;

import com.farafarin.mata.common.dao.UserTransactionDao;
import com.farafarin.mata.common.dto.UserTransactionDto;
import com.farafarin.mata.common.searchobject.UserTransactionSO;
import com.farafarin.mata.common.util.AuthenticationUtil;
import com.farafarin.mata.web.back.service.UserTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by K550 VX on 1/16/2018.
 */
@Component
public class UserTransactionServiceImpl implements UserTransactionService {

    @Autowired
    private UserTransactionDao userTransactionDao;

    @Override
    public Long countUserTransactions(UserTransactionSO so) {
        Long userId = AuthenticationUtil.getCurrentUser().getUserId();
        so.setUserId(userId);
        return userTransactionDao.countTransactions(so);
    }

    @Override
    public List<UserTransactionDto> getUserTransactions(UserTransactionSO so) {
        Long userId = AuthenticationUtil.getCurrentUser().getUserId();
        so.setUserId(userId);
        return userTransactionDao.getTransactions(so);
    }
}

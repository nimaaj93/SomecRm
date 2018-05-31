package com.farafarin.mata.admin.back.service.impl;

import com.farafarin.mata.admin.back.service.AdminTransactionService;
import com.farafarin.mata.common.constant.TransactionStatus;
import com.farafarin.mata.common.constant.TransactionType;
import com.farafarin.mata.common.dao.UserCreditDao;
import com.farafarin.mata.common.dao.UserTransactionDao;
import com.farafarin.mata.common.dto.UserTransactionDto;
import com.farafarin.mata.common.entity.UserCreditEntity;
import com.farafarin.mata.common.entity.UserTransactionEntity;
import com.farafarin.mata.common.model.AdminUserTransactionModel;
import com.farafarin.mata.common.searchobject.UserTransactionSO;
import com.farafarin.mata.common.util.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by K550 VX on 1/15/2018.
 */
@Component
public class AdminTransactionServiceImpl implements AdminTransactionService {

    @Autowired
    private UserTransactionDao userTransactionDao;
    @Autowired
    private UserCreditDao userCreditDao;

    @Override
    public List<UserTransactionDto> getTransactions(UserTransactionSO so) {
        return userTransactionDao.getTransactions(so);
    }

    @Override
    public Long countTransactions(UserTransactionSO so) {
        return userTransactionDao.countTransactions(so);
    }

    @Override
    @Transactional
    public void createTransaction(AdminUserTransactionModel model) {
        UserTransactionEntity entity = new UserTransactionEntity();
        BeanUtils.copyProperties(model,entity);
        //calculate last balance of user
        UserCreditEntity userCreditEntity = userCreditDao.retrieveCreditForUser(entity.getUserId());
        Long initialBalance = userCreditEntity.getBalance();
        Long newBalance = initialBalance + entity.getAmount();
        entity.setTransactionDate(DateUtil.convertStringJalahiDateToGregorianDate(model.getTransactionDate()));
        entity.setTransactionType(TransactionType.ADMIN_CREATED.getId());
        entity.setTransactionStatus(TransactionStatus.SUCCESSFUL.getId());
        entity.setLastBalance(newBalance);
        entity.setDebit(false);

        userTransactionDao.createTransaction(entity);

        userCreditEntity.setBalance(newBalance);
        userCreditDao.updateCredit(userCreditEntity);

    }
}

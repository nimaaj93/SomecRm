package com.farafarin.mata.admin.back.service;

import com.farafarin.mata.common.dto.UserTransactionDto;
import com.farafarin.mata.common.model.AdminUserTransactionModel;
import com.farafarin.mata.common.searchobject.UserTransactionSO;

import java.util.List;

/**
 * Created by K550 VX on 1/15/2018.
 */
public interface AdminTransactionService {

    List<UserTransactionDto> getTransactions(UserTransactionSO so);

    Long countTransactions(UserTransactionSO so);

    void createTransaction(AdminUserTransactionModel model);

}

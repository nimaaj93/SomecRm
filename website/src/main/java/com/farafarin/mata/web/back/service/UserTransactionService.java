package com.farafarin.mata.web.back.service;

import com.farafarin.mata.common.dto.UserTransactionDto;
import com.farafarin.mata.common.searchobject.UserTransactionSO;

import java.util.List;

/**
 * Created by K550 VX on 1/16/2018.
 */
public interface UserTransactionService {

    Long countUserTransactions(UserTransactionSO so);

    List<UserTransactionDto> getUserTransactions(UserTransactionSO so);


}

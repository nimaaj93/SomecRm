package com.farafarin.mata.common.dao;

import com.farafarin.mata.common.dto.UserTransactionDto;
import com.farafarin.mata.common.entity.UserTransactionEntity;
import com.farafarin.mata.common.searchobject.UserTransactionSO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by K550 VX on 1/15/2018.
 */
@Component
public interface UserTransactionDao {

    List<UserTransactionDto> getTransactions(@Param("so") UserTransactionSO so);

    Long countTransactions(@Param("so") UserTransactionSO so);

    void createTransaction(@Param("entity") UserTransactionEntity entity);


}

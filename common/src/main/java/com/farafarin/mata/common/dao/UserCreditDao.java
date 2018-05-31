package com.farafarin.mata.common.dao;

import com.farafarin.mata.common.dto.UserBalanceDto;
import com.farafarin.mata.common.entity.UserCreditEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * Created by K550 VX on 1/16/2018.
 */
@Component
public interface UserCreditDao {

    void createCreditForUser(@Param("entity") UserCreditEntity entity);

    UserCreditEntity retrieveCreditForUser(@Param("userId") Long userId);

    void updateCredit(@Param("entity") UserCreditEntity entity);

    UserBalanceDto getUserBalanceByNationalCode(@Param("nationalCode") String nationalCode);
}

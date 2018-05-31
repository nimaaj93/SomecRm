package com.farafarin.mata.admin.back.service;

import com.farafarin.mata.common.dto.UserBalanceDto;

/**
 * Created by K550 VX on 1/16/2018.
 */
public interface AdminBalanceService {

    UserBalanceDto getBalanceByNationalCode(String nationalCode);

}

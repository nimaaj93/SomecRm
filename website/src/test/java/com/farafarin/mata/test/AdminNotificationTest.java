package com.farafarin.mata.test;

import com.farafarin.mata.common.dao.AdminNotificationDao;
import com.farafarin.mata.common.dao.AdminNotificationTypeDao;
import com.farafarin.mata.common.dao.UserSignUpRequestDao;
import com.farafarin.mata.common.model.UserOrderModel;
import com.farafarin.mata.common.service.UserOrderService;
import com.farafarin.mata.test.config.DaoConfig;
import com.farafarin.mata.test.config.RootConfig;
import com.farafarin.mata.web.back.model.UserSignUpRequestModel;
import com.farafarin.mata.web.back.service.UserSignUpRequestService;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Asus on 12/2/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class, DaoConfig.class})
public class AdminNotificationTest {

    @Autowired
    private UserOrderService userOrderService;


    @Test
    public void doTest(){
        UserOrderModel userOrderModel = new UserOrderModel();
        userOrderModel.setAddressId(1L);
        userOrderModel.setCategoryId(3L);
        userOrderModel.setDescription("zdfzsdfsdfsdfsefse");

        userOrderService.submitUserOrderRequest(userOrderModel);

    }


}

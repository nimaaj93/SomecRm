package com.farafarin.mata;

import com.farafarin.mata.admin.back.service.UserManagementService;
import com.farafarin.mata.admin.config.DaoConfig;
import com.farafarin.mata.admin.config.RootConfig;
import com.farafarin.mata.common.searchobject.UserSO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Asus on 11/25/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class, DaoConfig.class})
public class UserManagementTest {

    @Autowired
    private UserManagementService userManagementService;

    @Test
    public void doTest(){
        UserSO userSO = new UserSO();
        userSO.setFirstNameFa("آران");
        userSO.setPageNumber(0);
        userSO.setPageSize(10);
        userManagementService.retrieveUsers(userSO);
    }
}

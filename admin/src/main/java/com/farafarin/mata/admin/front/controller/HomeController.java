package com.farafarin.mata.admin.front.controller;

import com.farafarin.mata.common.dto.AdminNotificationDto;
import com.farafarin.mata.admin.back.dto.HomePageSummaryDto;
import com.farafarin.mata.admin.back.service.OfferManagementService;
import com.farafarin.mata.admin.back.service.UserManagementService;
import com.farafarin.mata.admin.back.service.UserOrderManagementService;
import com.farafarin.mata.admin.back.service.UserSignUpRequestManagementService;
import com.farafarin.mata.common.constant.UserOrderStatus;
import com.farafarin.mata.common.searchobject.AdminNotificationSO;
import com.farafarin.mata.common.searchobject.UserOrderSO;
import com.farafarin.mata.common.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by K550 VX on 10/31/2017.
 */
@RestController
@RequestMapping("/s/home")
public class HomeController {

    @Autowired
    private UserOrderManagementService userOrderManagementService;
    @Autowired
    private UserSignUpRequestManagementService userSignUpRequestManagementService;
    @Autowired
    private UserManagementService userManagementService;
    @Autowired
    private OfferManagementService offerManagementService;
    @Autowired
    private NotificationService notificationService;

    @RequestMapping(value = "/summary",method = RequestMethod.GET)
    public HomePageSummaryDto getHomePageSummary() {
        HomePageSummaryDto summaryDto = new HomePageSummaryDto();

        Long newOrdersCount = getOrdersCount();
        Long newRegistrationRequestsCount = getSignUpRequests();
        Long activeUserCount = getActiveUserCount();
        Long activeOffersCount = getActiveOffersCount();

        summaryDto.setNewOrdersCount(newOrdersCount);
        summaryDto.setNewRegistrationRequestsCount(newRegistrationRequestsCount);
        summaryDto.setActiveUsersCount(activeUserCount);
        summaryDto.setActiveOffersCount(activeOffersCount);

        return summaryDto;
    }

    @RequestMapping(value = "/notification/list",method = RequestMethod.GET)
    public List<AdminNotificationDto> getNotifications(AdminNotificationSO adminNotificationSO){
        return notificationService.getNotifications(adminNotificationSO);
    }

    @RequestMapping(value = "/notification/count")
    public Integer getNotificationsCount() {
        return notificationService.getNotificationsCount();
    }

    private Long getOrdersCount() {
        UserOrderSO userOrderSO = new UserOrderSO();
        userOrderSO.setOrderStatus(UserOrderStatus.SIGN.getUserOrderStatus());
        return userOrderManagementService.getUserOrderCount(userOrderSO);
    }

    private Long getSignUpRequests() {
        return userSignUpRequestManagementService.getNewUserSignUpRequestCount();
    }

    private Long getActiveUserCount() {
        return userManagementService.getActiveUsersCount();
    }

    private Long getActiveOffersCount() {
        return offerManagementService.getActiveOffersCount();
    }
}

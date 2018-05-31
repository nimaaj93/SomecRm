package com.farafarin.mata.web.front.controller;

import com.farafarin.mata.common.constant.UserOrderStatus;
import com.farafarin.mata.common.dto.UserOrderReportDto;
import com.farafarin.mata.web.back.service.UserOrderReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Asus on 11/23/2017.
 */
@RestController
@RequestMapping(value = "/s/orderreport")
public class UserOrderReportController {

    @Autowired
    private UserOrderReportService userOrderReportService;

    @RequestMapping(value = "count", method = RequestMethod.GET)
    public Long getUserOrderCount(@RequestParam(value = "tabindex") Integer tabindex) {
        List<Long> statusList = getStatusFromTabIndex(tabindex);
        return userOrderReportService.getUserOrderCount(statusList);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public List<UserOrderReportDto> getUserOrders(@RequestParam(value = "tabindex") @NotNull Integer tabindex,
                                                  @RequestParam(value = "pageSize") @NotNull Integer pageSize,
                                                  @RequestParam(value = "pageNumber") @NotNull Integer pageNumber) {
        List<Long> statusList = getStatusFromTabIndex(tabindex);
        return userOrderReportService.getUserOrders(statusList, pageSize, pageNumber);
    }

    private List<Long> getStatusFromTabIndex(Integer tabindex) {
        List<Long> statusList = new ArrayList<>();
        if (tabindex == 1) {
            statusList = Arrays.asList(UserOrderStatus.SIGN.getUserOrderStatus());
        } else if (tabindex == 2) {
            statusList = Arrays.asList(UserOrderStatus.DONE.getUserOrderStatus(),
                    (UserOrderStatus.SEND.getUserOrderStatus()),(UserOrderStatus.VERIFIED.getUserOrderStatus()));
        } else if (tabindex == 3) {
            statusList = Arrays.asList(UserOrderStatus.CANCELED.getUserOrderStatus());
        } else {
            throw new IllegalArgumentException();
        }
        return statusList;
    }

    @RequestMapping(value = "cancel",method = RequestMethod.POST)
    public void cancelOrder(@RequestParam(value = "orderId",required = true) Long orderId) {
        userOrderReportService.cancelOrder(orderId);
    }

}

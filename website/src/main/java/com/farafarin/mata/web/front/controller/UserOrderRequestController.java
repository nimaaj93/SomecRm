package com.farafarin.mata.web.front.controller;

import com.farafarin.mata.common.entity.OrderCategoryEntity;
import com.farafarin.mata.common.entity.UserAddressEntity;
import com.farafarin.mata.common.model.UserOrderModel;
import com.farafarin.mata.common.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Asus on 11/17/2017.
 */
@RestController
@RequestMapping(value = "/s/order")
public class UserOrderRequestController {

    @Autowired
    private UserOrderService userOrderService;

    @RequestMapping(value = "submitrequest", method = RequestMethod.POST)
    public void submitOrderRequest(UserOrderModel userOrderModel) {
        userOrderService.submitUserOrderRequest(userOrderModel);
    }

    @RequestMapping(value = "useraddresses", method = RequestMethod.GET)
    public List<UserAddressEntity> getUserAddresses() {
        return userOrderService.getUserAddresses();
    }

    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public List<OrderCategoryEntity> getCategories(@RequestParam(value = "categoryId", required = false) Long categoryId) {
        return userOrderService.getCategories(categoryId);
    }
}
